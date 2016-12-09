package com.fruitrec.navdata;

import java.util.Arrays;

public class GyroPhysData
{
  private float[] phys_gyros;
  private int alim3v3;
  private int vrefEpson;
  private int vrefIDG;
  int gyro_temp;
  
  public GyroPhysData(int gyro_temp, float[] phys_gyros, int alim3v3, int vrefEpson, int vrefIDG)
  {
    this.gyro_temp = gyro_temp;
    this.phys_gyros = phys_gyros;
    this.alim3v3 = alim3v3;
    this.vrefEpson = vrefEpson;
    this.vrefIDG = vrefIDG;
  }
  


  public float[] getPhysGyros()
  {
    return this.phys_gyros;
  }
  


  public int getAlim3v3()
  {
    return this.alim3v3;
  }
  


  public int getVrefEpson()
  {
    return this.vrefEpson;
  }
  


  public int getVrefIDG()
  {
    return this.vrefIDG;
  }
  


  public int getGyroTemp()
  {
    return this.gyro_temp;
  }
  



  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("GyroPhysData [phys_gyros=");
    builder.append(Arrays.toString(this.phys_gyros));
    builder.append(", alim3v3=");
    builder.append(this.alim3v3);
    builder.append(", vrefEpson=");
    builder.append(this.vrefEpson);
    builder.append(", vrefIDG=");
    builder.append(this.vrefIDG);
    builder.append(", gyro_temp=");
    builder.append(this.gyro_temp);
    builder.append("]");
    return builder.toString();
  }
}
