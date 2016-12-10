package com.fruitrec.navdata;

import java.util.Arrays;

public class VisionPerformance
{
  private float time_szo;
  private float time_corners;
  private float time_compute;
  private float time_tracking;
  private float time_trans;
  private float time_update;
  private float[] time_custom;
  
  public VisionPerformance(float time_szo, float time_corners, float time_compute, float time_tracking, float time_trans, float time_update, float[] time_custom)
  {
    this.time_szo = time_szo;
    this.time_corners = time_corners;
    this.time_compute = time_compute;
    this.time_tracking = time_tracking;
    this.time_trans = time_trans;
    this.time_update = time_update;
    this.time_custom = time_custom;
  }

  public float getTimeSzo()
  {
    return this.time_szo;
  }

  public float getTimeCorners()
  {
    return this.time_corners;
  }

  public float getTimeCompute()
  {
    return this.time_compute;
  }

  public float getTimeTracking()
  {
    return this.time_tracking;
  }

  public float getTimeTrans()
  {
    return this.time_trans;
  }

  public float getTimeUpdate()
  {
    return this.time_update;
  }

  public float[] getTimeCustom()
  {
    return this.time_custom;
  }

  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("VisionPerormance [time_szo=");
    builder.append(this.time_szo);
    builder.append(", time_corners=");
    builder.append(this.time_corners);
    builder.append(", time_compute=");
    builder.append(this.time_compute);
    builder.append(", time_tracking=");
    builder.append(this.time_tracking);
    builder.append(", time_trans=");
    builder.append(this.time_trans);
    builder.append(", time_update=");
    builder.append(this.time_update);
    builder.append(", time_custom=");
    builder.append(Arrays.toString(this.time_custom));
    builder.append("]");
    return builder.toString();
  }
}
