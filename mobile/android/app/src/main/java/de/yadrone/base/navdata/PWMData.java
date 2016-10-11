package de.yadrone.base.navdata;


public class PWMData
{
  private short[] motor;
  private short[] sat_motor;
  private float gaz_feed_forward;
  private float gaz_altitude;
  private float altitude_integral;
  private float vz_ref;
  private int[] u_pry;
  private float yaw_u_I;
  private int[] u_planif_pry;
  private float u_gaz_planif;
  private int[] current_motor;
  private float altitude_prop;
  private float altitude_der;
  
  public PWMData(short[] motor, short[] sat_motor, float gaz_feed_forward, float gaz_altitude, float altitude_integral, float vz_ref, int[] u_pry, float yaw_u_I, int[] u_planif_pry, float u_gaz_planif, int[] current_motor, float altitude_prop, float altitude_der)
  {
    this.motor = motor;
    this.sat_motor = sat_motor;
    this.gaz_feed_forward = gaz_feed_forward;
    this.gaz_altitude = gaz_altitude;
    this.altitude_integral = altitude_integral;
    this.vz_ref = vz_ref;
    this.u_pry = u_pry;
    this.yaw_u_I = yaw_u_I;
    this.u_planif_pry = u_planif_pry;
    this.u_gaz_planif = u_gaz_planif;
    this.current_motor = current_motor;
    this.altitude_prop = altitude_prop;
    this.altitude_der = altitude_der;
  }
  


  public short[] getMotor()
  {
    return this.motor;
  }
  


  public short[] getSatMotor()
  {
    return this.sat_motor;
  }
  


  public float getGazFeedForward()
  {
    return this.gaz_feed_forward;
  }
  


  public float getGazAltitude()
  {
    return this.gaz_altitude;
  }
  


  public float getAltitudeIntegral()
  {
    return this.altitude_integral;
  }
  


  public float getVzRef()
  {
    return this.vz_ref;
  }
  


  public int[] getUPRY()
  {
    return this.u_pry;
  }
  


  public float getYawUI()
  {
    return this.yaw_u_I;
  }
  


  public int[] getUPlanifPRY()
  {
    return this.u_planif_pry;
  }
  


  public float getUGazPlanif()
  {
    return this.u_gaz_planif;
  }
  


  public int[] getCurrentMotor()
  {
    return this.current_motor;
  }
  


  public float getAltitudeProp()
  {
    return this.altitude_prop;
  }
  


  public float getAltitudeDer()
  {
    return this.altitude_der;
  }
}
