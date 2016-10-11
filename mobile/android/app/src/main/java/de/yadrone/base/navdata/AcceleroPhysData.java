package de.yadrone.base.navdata;

import java.util.Arrays;

public class AcceleroPhysData
{
  private float accs_temp;
  private float[] phys_accs;
  private int alim3v3;
  
  public AcceleroPhysData(float accs_temp, float[] phys_accs, int alim3v3)
  {
    this.accs_temp = accs_temp;
    this.phys_accs = phys_accs;
    this.alim3v3 = alim3v3;
  }
  


  public float getAccsTemp()
  {
    return this.accs_temp;
  }
  


  public float[] getPhysAccs()
  {
    return this.phys_accs;
  }
  


  public int getAlim3v3()
  {
    return this.alim3v3;
  }
  



  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("AcceleroPhysData [accs_temp=");
    builder.append(this.accs_temp);
    builder.append(", phys_accs=");
    builder.append(Arrays.toString(this.phys_accs));
    builder.append(", alim3v3=");
    builder.append(this.alim3v3);
    builder.append("]");
    return builder.toString();
  }
}
