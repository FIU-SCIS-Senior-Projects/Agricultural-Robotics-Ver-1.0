package de.yadrone.base.video;

//import java.awt.image.BufferedImage;
import de.yadrone.base.shim.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ReadRawFileImage
{
  public BufferedImage readUINT_RGBImage(byte[] rawData)
    throws FileNotFoundException, IOException
  {
    int length = 0;
    try {
      byte[] processedData = process(rawData);
      
      int[] pixelData = new int[processedData.length / 3];
      int pixel = 0;int j = 0;
      for (int i = 0; i < pixelData.length; i++) {
        pixel = 0;
        int raw = processedData[(j++)] & 0xFF;
        pixel |= raw << 16;
        raw = processedData[(j++)] & 0xFF;
        pixel |= raw << 8;
        raw = processedData[(j++)] & 0xFF;
        pixel |= raw << 0;
        
        pixelData[i] = pixel;
      }
      
      length = pixelData.length;
      
      if (length == 76800) {
        BufferedImage image = new BufferedImage(320, 240, 
          1);
        
        image.setRGB(0, 0, 320, 240, pixelData, 0, 320);
        return image; }
      if (length == 25344) {
        BufferedImage image = new BufferedImage(176, 144, 
          1);
        
        image.setRGB(0, 0, 176, 144, pixelData, 0, 176);
        return image;
      }
      

    }
    catch (ArrayIndexOutOfBoundsException e)
    {

      e.printStackTrace();
    }
    
    return null;
  }
  
  private byte[] process(byte[] rawData) {
    BufferedVideoImage image = new BufferedVideoImage();
    image.AddImageStream(ByteBuffer.wrap(rawData));
    
    uint[] outData = image.getPixelData();
    
    ByteBuffer buffer = ByteBuffer.allocate(outData.length * 3);
    
    for (int i = 0; i < outData.length; i++) {
      int myInt = outData[i].intValue();
      buffer.put((byte)(myInt >> 16 & 0xFF));
      buffer.put((byte)(myInt >> 8 & 0xFF));
      buffer.put((byte)(myInt & 0xFF));
    }
    
    return buffer.array();
  }
}
