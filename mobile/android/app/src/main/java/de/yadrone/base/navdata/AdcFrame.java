package de.yadrone.base.navdata;

import java.util.Arrays;

public class AdcFrame
{
  private int version;
  private byte[] data_frame;
  
  public AdcFrame(int version, byte[] data_frame) {
    this.version = version;
    this.data_frame = data_frame;
  }
  


  public int getVersion()
  {
    return this.version;
  }
  


  public byte[] getData_frame()
  {
    return this.data_frame;
  }
  



  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("AdcData [version=");
    builder.append(this.version);
    builder.append(", data_frame=");
    builder.append(Arrays.toString(this.data_frame));
    builder.append("]");
    return builder.toString();
  }
}
