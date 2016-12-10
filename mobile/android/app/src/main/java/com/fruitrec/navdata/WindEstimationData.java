package com.fruitrec.navdata;

import java.util.Arrays;

public class WindEstimationData
{
  float estimatedSpeed;
  float estimatedAngle;
  float[] state;
  float[] magneto;

  public WindEstimationData(float estimatedSpeed, float estimatedAngle, float[] state, float[] magneto)
  {
    this.estimatedSpeed = estimatedSpeed;
    this.estimatedAngle = estimatedAngle;
    this.state = state;
    this.magneto = magneto;
  }

  public float getEstimatedSpeed()
  {
    return this.estimatedSpeed;
  }

  public float getEstimatedAngle()
  {
    return this.estimatedAngle;
  }

  public float[] getState()
  {
    return this.state;
  }

  public float[] getMagneto()
  {
    return this.magneto;
  }

  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("WindEstimationData [estimatedSpeed=");
    builder.append(this.estimatedSpeed);
    builder.append(", estimatedAngle=");
    builder.append(this.estimatedAngle);
    builder.append(", state=");
    builder.append(Arrays.toString(this.state));
    builder.append(", magneto=");
    builder.append(Arrays.toString(this.magneto));
    builder.append("]");
    return builder.toString();
  }
}
