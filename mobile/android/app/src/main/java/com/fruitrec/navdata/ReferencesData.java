package com.fruitrec.navdata;

import java.util.Arrays;


public class ReferencesData
{
  private int ref_theta;
  private int ref_phi;
  private int ref_theta_I;
  private int ref_phi_I;
  private int ref_pitch;
  private int ref_roll;
  private int ref_yaw;
  private int ref_psi;
  private float[] v_ref;
  private float theta_mod;
  private float phi_mod;
  private float[] k_v;
  private int k_mode;
  private float ui_time;
  private float ui_theta;
  private float ui_phi;
  private float ui_psi;
  private float ui_psi_accuracy;
  private int ui_seq;
  
  public ReferencesData(int ref_theta, int ref_phi, int ref_theta_I, int ref_phi_I, int ref_pitch, int ref_roll, int ref_yaw, int ref_psi, float[] v_ref, float theta_mod, float phi_mod, float[] k_v, int k_mode, float ui_time, float ui_theta, float ui_phi, float ui_psi, float ui_psi_accuracy, int ui_seq)
  {
    this.ref_theta = ref_theta;
    this.ref_phi = ref_phi;
    this.ref_theta_I = ref_theta_I;
    this.ref_phi_I = ref_phi_I;
    this.ref_pitch = ref_pitch;
    this.ref_roll = ref_roll;
    this.ref_yaw = ref_yaw;
    this.ref_psi = ref_psi;
    this.v_ref = v_ref;
    this.theta_mod = theta_mod;
    this.phi_mod = phi_mod;
    this.k_v = k_v;
    this.k_mode = k_mode;
    this.ui_time = ui_time;
    this.ui_theta = ui_theta;
    this.ui_phi = ui_phi;
    this.ui_psi = ui_psi;
    this.ui_psi_accuracy = ui_psi_accuracy;
    this.ui_seq = ui_seq;
  }
  


  public int getRefTheta()
  {
    return this.ref_theta;
  }
  


  public int getRefPhi()
  {
    return this.ref_phi;
  }
  


  public int getRefThetaI()
  {
    return this.ref_theta_I;
  }
  


  public int getRefPhiI()
  {
    return this.ref_phi_I;
  }
  


  public int getRefPitch()
  {
    return this.ref_pitch;
  }
  


  public int getRefRoll()
  {
    return this.ref_roll;
  }
  


  public int getRefYaw()
  {
    return this.ref_yaw;
  }
  


  public int getRefPsi()
  {
    return this.ref_psi;
  }
  


  public float[] getVRef()
  {
    return this.v_ref;
  }
  


  public float getThetaMod()
  {
    return this.theta_mod;
  }
  


  public float getPhiMod()
  {
    return this.phi_mod;
  }
  


  public float[] getKV()
  {
    return this.k_v;
  }
  


  public int getKMode()
  {
    return this.k_mode;
  }
  


  public float getUiTime()
  {
    return this.ui_time;
  }
  


  public float getUiTheta()
  {
    return this.ui_theta;
  }
  


  public float getUiPhi()
  {
    return this.ui_phi;
  }
  


  public float getUiPsi()
  {
    return this.ui_psi;
  }
  


  public float getUiPsiAccuracy()
  {
    return this.ui_psi_accuracy;
  }
  


  public int getUiSeq()
  {
    return this.ui_seq;
  }
  



  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("ReferencesData [ref_theta=");
    builder.append(this.ref_theta);
    builder.append(", ref_phi=");
    builder.append(this.ref_phi);
    builder.append(", ref_theta_I=");
    builder.append(this.ref_theta_I);
    builder.append(", ref_phi_I=");
    builder.append(this.ref_phi_I);
    builder.append(", ref_pitch=");
    builder.append(this.ref_pitch);
    builder.append(", ref_roll=");
    builder.append(this.ref_roll);
    builder.append(", ref_yaw=");
    builder.append(this.ref_yaw);
    builder.append(", ref_psi=");
    builder.append(this.ref_psi);
    builder.append(", v_ref=");
    builder.append(Arrays.toString(this.v_ref));
    builder.append(", theta_mod=");
    builder.append(this.theta_mod);
    builder.append(", phi_mod=");
    builder.append(this.phi_mod);
    builder.append(", k_v=");
    builder.append(Arrays.toString(this.k_v));
    builder.append(", k_mode=");
    builder.append(this.k_mode);
    builder.append(", ui_time=");
    builder.append(this.ui_time);
    builder.append(", ui_theta=");
    builder.append(this.ui_theta);
    builder.append(", ui_phi=");
    builder.append(this.ui_phi);
    builder.append(", ui_psi=");
    builder.append(this.ui_psi);
    builder.append(", ui_psi_accuracy=");
    builder.append(this.ui_psi_accuracy);
    builder.append(", ui_seq=");
    builder.append(this.ui_seq);
    builder.append("]");
    return builder.toString();
  }
}
