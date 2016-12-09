package com.fruitrec.navdata;

import java.util.Arrays;

public class GyroRawData
{
  private short[] raw_gyros;
  private short[] raw_gyros_110;
  
  public GyroRawData(short[] raw_gyros, short[] raw_gyros_110) {
    this.raw_gyros = raw_gyros;
    this.raw_gyros_110 = raw_gyros_110;
  }
  


  public short[] getRawGyros()
  {
    return this.raw_gyros;
  }
  


  public short[] getRawGyros110()
  {
    return this.raw_gyros_110;
  }
  



  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("GyroRawData [raw_gyros=");
    builder.append(Arrays.toString(this.raw_gyros));
    builder.append(", raw_gyros_110=");
    builder.append(Arrays.toString(this.raw_gyros_110));
    builder.append("]");
    return builder.toString();
  }
}
