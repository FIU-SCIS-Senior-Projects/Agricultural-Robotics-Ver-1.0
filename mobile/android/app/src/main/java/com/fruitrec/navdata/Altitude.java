package com.fruitrec.navdata;

import java.util.Arrays;


public class Altitude
{
  private int altitude_vision;
  private float altitude_vz;
  private int altitude_ref;
  private int altitude_raw;
  private float obs_accZ;
  private float obs_alt;
  private float[] obs_x;
  private int obs_state;
  private float[] est_vb;
  private int est_state;
  
  public Altitude(int altitude_vision, float altitude_vz, int altitude_ref, int altitude_raw, float obs_accZ, float obs_alt, float[] obs_x, int obs_state, float[] est_vb, int est_state)
  {
    this.altitude_vision = altitude_vision;
    this.altitude_vz = altitude_vz;
    this.altitude_ref = altitude_ref;
    this.altitude_raw = altitude_raw;
    this.obs_accZ = obs_accZ;
    this.obs_alt = obs_alt;
    this.obs_x = obs_x;
    this.obs_state = obs_state;
    this.est_vb = est_vb;
    this.est_state = est_state;
  }
  


  public int getVision()
  {
    return this.altitude_vision;
  }
  


  public float getZVelocity()
  {
    return this.altitude_vz;
  }
  


  public int getRef()
  {
    return this.altitude_ref;
  }
  


  public int getRaw()
  {
    return this.altitude_raw;
  }
  


  public float getObsAccZ()
  {
    return this.obs_accZ;
  }
  


  public float getObsAlt()
  {
    return this.obs_alt;
  }
  


  public float[] getObsX()
  {
    return this.obs_x;
  }
  


  public int getObsState()
  {
    return this.obs_state;
  }
  


  public float[] getEstVb()
  {
    return this.est_vb;
  }
  



  public int getEstState()
  {
    return this.est_state;
  }
  



  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("Altitude [altitude_vision=");
    builder.append(this.altitude_vision);
    builder.append(", altitude_vz=");
    builder.append(this.altitude_vz);
    builder.append(", altitude_ref=");
    builder.append(this.altitude_ref);
    builder.append(", altitude_raw=");
    builder.append(this.altitude_raw);
    builder.append(", obs_accZ=");
    builder.append(this.obs_accZ);
    builder.append(", obs_alt=");
    builder.append(this.obs_alt);
    builder.append(", obs_x=");
    builder.append(Arrays.toString(this.obs_x));
    builder.append(", obs_state=");
    builder.append(this.obs_state);
    builder.append(", est_vb=");
    builder.append(Arrays.toString(this.est_vb));
    builder.append(", est_state=");
    builder.append(this.est_state);
    builder.append("]");
    return builder.toString();
  }
}
