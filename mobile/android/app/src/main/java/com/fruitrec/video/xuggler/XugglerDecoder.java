package com.fruitrec.video.xuggler;

import com.xuggle.xuggler.Global;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IPacket;
import com.xuggle.xuggler.IStream;
import com.xuggle.xuggler.IStreamCoder;
import com.xuggle.xuggler.IVideoPicture;
import com.xuggle.xuggler.IVideoResampler;
import com.xuggle.xuggler.Utils;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IPixelFormat;

//import java.awt.image.BufferedImage;
import com.fruitrec.shim.BufferedImage;
import java.io.InputStream;

import com.fruitrec.video.ImageListener;
import com.fruitrec.video.VideoDecoder;

public class XugglerDecoder
  implements VideoDecoder
{
  private ImageListener listener;
  
  public void decode(InputStream is)
  {
    if (!IVideoResampler.isSupported(IVideoResampler.Feature.FEATURE_COLORSPACECONVERSION)) {
      throw new RuntimeException("you must install the GPL version of Xuggler (with IVideoResampler support) for this to work");
    }
    
    IContainer container = IContainer.make();
    

    if (container.open(is, null) < 0) {
      throw new IllegalArgumentException("could not open inpustream");
    }
    
    int numStreams = container.getNumStreams();
    

    int videoStreamId = -1;
    IStreamCoder videoCoder = null;
    for (int i = 0; i < numStreams; i++)
    {

      IStream stream = container.getStream(i);
      
      IStreamCoder coder = stream.getStreamCoder();
      
      if (coder.getCodecType() == ICodec.Type.CODEC_TYPE_VIDEO)
      {
        videoStreamId = i;
        videoCoder = coder;
        break;
      }
    }
    if (videoStreamId == -1) {
      throw new RuntimeException("could not find video stream");
    }
    



    if (videoCoder.open() < 0) {
      throw new RuntimeException("could not open video decoder for container");
    }
    IVideoResampler resampler = null;
    if (videoCoder.getPixelType() != IPixelFormat.Type.BGR24)
    {


      resampler = IVideoResampler.make(videoCoder.getWidth(), videoCoder.getHeight(), IPixelFormat.Type.BGR24, videoCoder.getWidth(), videoCoder.getHeight(), videoCoder.getPixelType());
      if (resampler == null) {
        throw new RuntimeException("could not create color space resampler.");
      }
    }
    


    IPacket packet = IPacket.make();
    long firstTimestampInStream = Global.NO_PTS;
    long systemClockStartTime = 0L;
    while (container.readNextPacket(packet) >= 0)
    {



      if (packet.getStreamIndex() == videoStreamId)
      {



        IVideoPicture picture = IVideoPicture.make(videoCoder.getPixelType(), videoCoder.getWidth(), videoCoder.getHeight());
        
        try
        {
          int offset = 0;
          while (offset < packet.getSize())
          {



            int bytesDecoded = videoCoder.decodeVideo(picture, packet, offset);
            if (bytesDecoded < 0)
              throw new RuntimeException("got an error decoding single video frame");
            offset += bytesDecoded;
            






            if (picture.isComplete())
            {
              IVideoPicture newPic = picture;
              




              if (resampler != null)
              {

                newPic = IVideoPicture.make(resampler.getOutputPixelFormat(), picture.getWidth(), picture.getHeight());
                if (resampler.resample(newPic, picture) < 0)
                  throw new RuntimeException("could not resample video");
              }
              if (newPic.getPixelType() != IPixelFormat.Type.BGR24) {
                throw new RuntimeException("could not decode video as BGR 24 bit data");
              }
              
















              if (firstTimestampInStream == Global.NO_PTS)
              {

                firstTimestampInStream = picture.getTimeStamp();
                
                systemClockStartTime = System.currentTimeMillis();
              }
              else
              {
                long systemClockCurrentTime = System.currentTimeMillis();
                long millisecondsClockTimeSinceStartofVideo = systemClockCurrentTime - systemClockStartTime;
                



                long millisecondsStreamTimeSinceStartOfVideo = (picture.getTimeStamp() - firstTimestampInStream) / 1000L;
                long millisecondsTolerance = 50L;
                long l1 = millisecondsStreamTimeSinceStartOfVideo - (millisecondsClockTimeSinceStartofVideo + 50L);
              }
              













              //BufferedImage javaImage = Utils.videoPictureToImage(newPic);
              BufferedImage javaImage = new BufferedImage();

              if (this.listener != null) {
                this.listener.imageUpdated(javaImage);
              }
            }
          }
        }
        catch (Exception localException) {}
      }
    }
    













    System.out.println("XugglerDecoder: clean up and close stream ...");
    




    if (videoCoder != null)
    {
      videoCoder.close();
      videoCoder = null;
    }
    if (container != null)
    {
      container.close();
      container = null;
    }
  }
  
  public void setImageListener(ImageListener listener)
  {
    this.listener = listener;
  }
}
