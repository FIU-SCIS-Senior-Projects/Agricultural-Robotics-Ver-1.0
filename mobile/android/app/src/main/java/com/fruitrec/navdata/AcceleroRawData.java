package com.fruitrec.navdata;

import java.util.Arrays;

public class AcceleroRawData
{
  private int[] raw_accs;
  
  public AcceleroRawData(int[] raw_accs) {
    this.raw_accs = raw_accs;
  }

  public int[] getRawAccs()
  {
    return this.raw_accs;
  }

  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("AcceleroRawData [raw_accs=");
    builder.append(Arrays.toString(this.raw_accs));
    builder.append("]");
    return builder.toString();
  }
}
