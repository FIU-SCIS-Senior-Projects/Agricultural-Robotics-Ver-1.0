package de.yadrone.base.navdata;

import java.util.Arrays;




public class VisionData
{
  private int vision_state;
  private int vision_misc;
  private float vision_phi_trim;
  private float vision_phi_ref_prop;
  private float vision_theta_trim;
  private float vision_theta_ref_prop;
  private int new_raw_picture;
  private float theta_capture;
  private float phi_capture;
  private float psi_capture;
  private int altitude_capture;
  private int time_capture_seconds;
  private int time_capture_useconds;
  private float[] body_v;
  private float delta_phi;
  private float delta_theta;
  private float delta_psi;
  private int gold_defined;
  private int gold_reset;
  private float gold_x;
  private float gold_y;
  
  public VisionData(int vision_state, int vision_misc, float vision_phi_trim, float vision_phi_ref_prop, float vision_theta_trim, float vision_theta_ref_prop, int new_raw_picture, float theta_capture, float phi_capture, float psi_capture, int altitude_capture, int time_capture_seconds, int time_capture_useconds, float[] body_v, float delta_phi, float delta_theta, float delta_psi, int gold_defined, int gold_reset, float gold_x, float gold_y)
  {
    this.vision_state = vision_state;
    this.vision_misc = vision_misc;
    this.vision_phi_trim = vision_phi_trim;
    this.vision_phi_ref_prop = vision_phi_ref_prop;
    this.vision_theta_trim = vision_theta_trim;
    this.vision_theta_ref_prop = vision_theta_ref_prop;
    this.new_raw_picture = new_raw_picture;
    this.theta_capture = theta_capture;
    this.phi_capture = phi_capture;
    this.psi_capture = psi_capture;
    this.altitude_capture = altitude_capture;
    this.time_capture_seconds = time_capture_seconds;
    this.time_capture_useconds = time_capture_useconds;
    this.body_v = body_v;
    this.delta_phi = delta_phi;
    this.delta_theta = delta_theta;
    this.delta_psi = delta_psi;
    this.gold_defined = gold_defined;
    this.gold_reset = gold_reset;
    this.gold_x = gold_x;
    this.gold_y = gold_y;
  }
  


  public int getVisionState()
  {
    return this.vision_state;
  }
  


  public int getVisionMisc()
  {
    return this.vision_misc;
  }
  


  public float getVisionPhiTrim()
  {
    return this.vision_phi_trim;
  }
  


  public float getVisionPhiRefProp()
  {
    return this.vision_phi_ref_prop;
  }
  


  public float getVisionThetaTrim()
  {
    return this.vision_theta_trim;
  }
  


  public float getVisionThetaRefProp()
  {
    return this.vision_theta_ref_prop;
  }
  


  public int getNewRawPicture()
  {
    return this.new_raw_picture;
  }
  


  public float getThetaCapture()
  {
    return this.theta_capture;
  }
  


  public float getPhiCapture()
  {
    return this.phi_capture;
  }
  


  public float getPsiCapture()
  {
    return this.psi_capture;
  }
  


  public int getAltitudeCapture()
  {
    return this.altitude_capture;
  }
  


  public int getTimeCaptureSeconds()
  {
    return this.time_capture_seconds;
  }
  


  public int getTimeCaptureUseconds()
  {
    return this.time_capture_useconds;
  }
  


  public float[] getBodyV()
  {
    return this.body_v;
  }
  


  public float getDeltaPhi()
  {
    return this.delta_phi;
  }
  


  public float getDeltaTheta()
  {
    return this.delta_theta;
  }
  


  public float getDeltaPsi()
  {
    return this.delta_psi;
  }
  


  public int getGoldDefined()
  {
    return this.gold_defined;
  }
  


  public int getGoldReset()
  {
    return this.gold_reset;
  }
  


  public float getGoldX()
  {
    return this.gold_x;
  }
  


  public float getGoldY()
  {
    return this.gold_y;
  }
  



  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("VisionData [vision_state=");
    builder.append(this.vision_state);
    builder.append(", vision_misc=");
    builder.append(this.vision_misc);
    builder.append(", vision_phi_trim=");
    builder.append(this.vision_phi_trim);
    builder.append(", vision_phi_ref_prop=");
    builder.append(this.vision_phi_ref_prop);
    builder.append(", vision_theta_trim=");
    builder.append(this.vision_theta_trim);
    builder.append(", vision_theta_ref_prop=");
    builder.append(this.vision_theta_ref_prop);
    builder.append(", new_raw_picture=");
    builder.append(this.new_raw_picture);
    builder.append(", theta_capture=");
    builder.append(this.theta_capture);
    builder.append(", phi_capture=");
    builder.append(this.phi_capture);
    builder.append(", psi_capture=");
    builder.append(this.psi_capture);
    builder.append(", altitude_capture=");
    builder.append(this.altitude_capture);
    builder.append(", time_capture_seconds=");
    builder.append(this.time_capture_seconds);
    builder.append(", time_capture_useconds=");
    builder.append(this.time_capture_useconds);
    builder.append(", body_v=");
    builder.append(Arrays.toString(this.body_v));
    builder.append(", delta_phi=");
    builder.append(this.delta_phi);
    builder.append(", delta_theta=");
    builder.append(this.delta_theta);
    builder.append(", delta_psi=");
    builder.append(this.delta_psi);
    builder.append(", gold_defined=");
    builder.append(this.gold_defined);
    builder.append(", gold_reset=");
    builder.append(this.gold_reset);
    builder.append(", gold_x=");
    builder.append(this.gold_x);
    builder.append(", gold_y=");
    builder.append(this.gold_y);
    builder.append("]");
    return builder.toString();
  }
}
