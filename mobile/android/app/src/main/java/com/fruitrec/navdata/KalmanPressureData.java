package com.fruitrec.navdata;


public class KalmanPressureData
{
  private float offset_pressure;
  
  private float est_z;
  
  private float est_zdot;
  
  private float est_bias_PWM;
  private float est_biais_pression;
  private float offset_US;
  private float prediction_US;
  private float cov_alt;
  private float cov_PWM;
  private float cov_vitesse;
  private boolean effet_sol;
  private float somme_inno;
  private boolean rejet_US;
  private float u_multisinus;
  private float gaz_altitude;
  private boolean multisinus;
  private boolean multisinus_debut;
  
  public KalmanPressureData(float offset_pressure, float est_z, float est_zdot, float est_bias_PWM, float est_biais_pression, float offset_US, float prediction_US, float cov_alt, float cov_PWM, float cov_vitesse, boolean effet_sol, float somme_inno, boolean rejet_US, float u_multisinus, float gaz_altitude, boolean multisinus, boolean multisinus_debut)
  {
    this.offset_pressure = offset_pressure;
    this.est_z = est_z;
    this.est_zdot = est_zdot;
    this.est_bias_PWM = est_bias_PWM;
    this.est_biais_pression = est_biais_pression;
    this.offset_US = offset_US;
    this.prediction_US = prediction_US;
    this.cov_alt = cov_alt;
    this.cov_PWM = cov_PWM;
    this.cov_vitesse = cov_vitesse;
    this.effet_sol = effet_sol;
    this.somme_inno = somme_inno;
    this.rejet_US = rejet_US;
    this.u_multisinus = u_multisinus;
    this.gaz_altitude = gaz_altitude;
    this.multisinus = multisinus;
    this.multisinus_debut = multisinus_debut;
  }
  


  public float getOffsetPressure()
  {
    return this.offset_pressure;
  }
  


  public float getEstZ()
  {
    return this.est_z;
  }
  


  public float getEstZdot()
  {
    return this.est_zdot;
  }
  


  public float getEstBiasPWM()
  {
    return this.est_bias_PWM;
  }
  


  public float getEstBiaisPression()
  {
    return this.est_biais_pression;
  }
  


  public float getOffsetUS()
  {
    return this.offset_US;
  }
  


  public float getPredictionUS()
  {
    return this.prediction_US;
  }
  


  public float getCovAlt()
  {
    return this.cov_alt;
  }
  


  public float getCovPWM()
  {
    return this.cov_PWM;
  }
  


  public float getCovVitesse()
  {
    return this.cov_vitesse;
  }
  


  public boolean isEffetSol()
  {
    return this.effet_sol;
  }
  


  public float getSommeInno()
  {
    return this.somme_inno;
  }
  


  public boolean isRejetUS()
  {
    return this.rejet_US;
  }
  


  public float getUMultisinus()
  {
    return this.u_multisinus;
  }
  


  public float getGazAltitude()
  {
    return this.gaz_altitude;
  }
  


  public boolean isMultisinus()
  {
    return this.multisinus;
  }
  


  public boolean isMultisinusDebut()
  {
    return this.multisinus_debut;
  }
  





  public String toString()
  {
    return 
    



      "KalmanPressureData [offset_pressure=" + this.offset_pressure + ", est_z=" + this.est_z + ", est_zdot=" + this.est_zdot + ", est_bias_PWM=" + this.est_bias_PWM + ", est_biais_pression=" + this.est_biais_pression + ", offset_US=" + this.offset_US + ", prediction_US=" + this.prediction_US + ", cov_alt=" + this.cov_alt + ", cov_PWM=" + this.cov_PWM + ", cov_vitesse=" + this.cov_vitesse + ", effet_sol=" + this.effet_sol + ", somme_inno=" + this.somme_inno + ", rejet_US=" + this.rejet_US + ", u_multisinus=" + this.u_multisinus + ", gaz_altitude=" + this.gaz_altitude + ", multisinus=" + this.multisinus + ", multisinus_debut=" + this.multisinus_debut + "]";
  }
}
