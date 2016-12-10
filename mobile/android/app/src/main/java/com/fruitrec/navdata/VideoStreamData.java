package com.fruitrec.navdata;

import java.util.Arrays;



public class VideoStreamData
{
  private byte quant;
  private int frame_size;
  private int frame_number;
  private int atcmd_ref_seq;
  private int atcmd_mean_ref_gap;
  private float atcmd_var_ref_gap;
  private int atcmd_ref_quality;
  private int out_bitrate;
  private int desired_bitrate;
  private int[] temp_data;
  private int tcp_queue_level;
  private int fifo_queue_level;
  
  public VideoStreamData(byte quant, int frame_size, int frame_number, int atcmd_ref_seq, int atcmd_mean_ref_gap, float atcmd_var_ref_gap, int atcmd_ref_quality, int out_bitrate, int desired_bitrate, int[] temp_data, int tcp_queue_level, int fifo_queue_level)
  {
    this.quant = quant;
    this.frame_size = frame_size;
    this.frame_number = frame_number;
    this.atcmd_ref_seq = atcmd_ref_seq;
    this.atcmd_mean_ref_gap = atcmd_mean_ref_gap;
    this.atcmd_var_ref_gap = atcmd_var_ref_gap;
    this.atcmd_ref_quality = atcmd_ref_quality;
    this.out_bitrate = out_bitrate;
    this.desired_bitrate = desired_bitrate;
    this.temp_data = temp_data;
    this.tcp_queue_level = tcp_queue_level;
    this.fifo_queue_level = fifo_queue_level;
  }
  


  public byte getQuant()
  {
    return this.quant;
  }
  


  public int getFrameSize()
  {
    return this.frame_size;
  }
  


  public int getFrameNumber()
  {
    return this.frame_number;
  }
  


  public int getAtcmdRefSeq()
  {
    return this.atcmd_ref_seq;
  }
  


  public int getAtcmdMeanRefGap()
  {
    return this.atcmd_mean_ref_gap;
  }
  


  public float getAtcmdVarRefGap()
  {
    return this.atcmd_var_ref_gap;
  }
  


  public int getAtcmdRefQuality()
  {
    return this.atcmd_ref_quality;
  }
  


  public int getOutBitrate()
  {
    return this.out_bitrate;
  }
  


  public int getDesiredBitrate()
  {
    return this.desired_bitrate;
  }
  


  public int[] getTempData()
  {
    return this.temp_data;
  }
  


  public int getTcpQueueLevel()
  {
    return this.tcp_queue_level;
  }
  


  public int getFifoQueueLevel()
  {
    return this.fifo_queue_level;
  }
  



  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("VideoStreamData [quant=");
    builder.append(this.quant);
    builder.append(", frame_size=");
    builder.append(this.frame_size);
    builder.append(", frame_number=");
    builder.append(this.frame_number);
    builder.append(", atcmd_ref_seq=");
    builder.append(this.atcmd_ref_seq);
    builder.append(", atcmd_mean_ref_gap=");
    builder.append(this.atcmd_mean_ref_gap);
    builder.append(", atcmd_var_ref_gap=");
    builder.append(this.atcmd_var_ref_gap);
    builder.append(", atcmd_ref_quality=");
    builder.append(this.atcmd_ref_quality);
    builder.append(", out_bitrate=");
    builder.append(this.out_bitrate);
    builder.append(", desired_bitrate=");
    builder.append(this.desired_bitrate);
    builder.append(", temp_data=");
    builder.append(Arrays.toString(this.temp_data));
    builder.append(", tcp_queue_level=");
    builder.append(this.tcp_queue_level);
    builder.append(", fifo_queue_level=");
    builder.append(this.fifo_queue_level);
    builder.append("]");
    return builder.toString();
  }
}
