package com.fruitrec.navdata;

import java.util.Arrays;

public class TrackerData
{
  private int[][][] trackers;
  
  public TrackerData(int[][][] trackers) {
    this.trackers = trackers;
  }
  




  public int[][][] getTrackers()
  {
    return this.trackers;
  }
  





  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("TrackersData [trackers=");
    for (int i = 0; i < this.trackers.length; i++) {
      builder.append("[");
      for (int j = 0; j < this.trackers[i].length; j++) {
        builder.append("[");
        builder.append(Arrays.toString(this.trackers[i][j]));
        builder.append("]");
      }
      builder.append("]");
    }
    builder.append("]");
    return builder.toString();
  }
}
