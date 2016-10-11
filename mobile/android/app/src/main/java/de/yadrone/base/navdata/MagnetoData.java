package de.yadrone.base.navdata;

import java.util.Arrays;



public class MagnetoData
{
  private short[] m;
  private float[] mraw;
  private float[] mrectified;
  private float[] m_;
  private float heading_unwrapped;
  private float heading_gyro_unwrapped;
  private float heading_fusion_unwrapped;
  private byte calibration_ok;
  private int state;
  private float radius;
  private float error_mean;
  private float error_var;
  
  public MagnetoData(short[] m, float[] mraw, float[] mrectified, float[] m_, float heading_unwrapped, float heading_gyro_unwrapped, float heading_fusion_unwrapped, byte calibration_ok, int state, float radius, float error_mean, float error_var)
  {
    this.m = m;
    this.mraw = mraw;
    this.mrectified = mrectified;
    this.m_ = m_;
    this.heading_unwrapped = heading_unwrapped;
    this.heading_gyro_unwrapped = heading_gyro_unwrapped;
    this.heading_fusion_unwrapped = heading_fusion_unwrapped;
    this.calibration_ok = calibration_ok;
    this.state = state;
    this.radius = radius;
    this.error_mean = error_mean;
    this.error_var = error_var;
  }
  


  public short[] getM()
  {
    return this.m;
  }
  


  public float[] getMraw()
  {
    return this.mraw;
  }
  


  public float[] getMrectified()
  {
    return this.mrectified;
  }
  


  public float[] getM_()
  {
    return this.m_;
  }
  


  public float getHeadingUnwrapped()
  {
    return this.heading_unwrapped;
  }
  


  public float getHeadingGyroUnwrapped()
  {
    return this.heading_gyro_unwrapped;
  }
  


  public float getHeadingFusionUnwrapped()
  {
    return this.heading_fusion_unwrapped;
  }
  


  public byte getCalibrationOk()
  {
    return this.calibration_ok;
  }
  


  public int getState()
  {
    return this.state;
  }
  


  public float getRadius()
  {
    return this.radius;
  }
  


  public float getErrorMean()
  {
    return this.error_mean;
  }
  


  public float getErrorVar()
  {
    return this.error_var;
  }
  



  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("MagnetoData [m=");
    builder.append(Arrays.toString(this.m));
    builder.append(", mraw=");
    builder.append(Arrays.toString(this.mraw));
    builder.append(", mrectified=");
    builder.append(Arrays.toString(this.mrectified));
    builder.append(", m_=");
    builder.append(Arrays.toString(this.m_));
    builder.append(", heading_unwrapped=");
    builder.append(this.heading_unwrapped);
    builder.append(", heading_gyro_unwrapped=");
    builder.append(this.heading_gyro_unwrapped);
    builder.append(", heading_fusion_unwrapped=");
    builder.append(this.heading_fusion_unwrapped);
    builder.append(", calibration_ok=");
    builder.append(this.calibration_ok);
    builder.append(", state=");
    builder.append(this.state);
    builder.append(", radius=");
    builder.append(this.radius);
    builder.append(", error_mean=");
    builder.append(this.error_mean);
    builder.append(", error_var=");
    builder.append(this.error_var);
    builder.append("]");
    return builder.toString();
  }
}
