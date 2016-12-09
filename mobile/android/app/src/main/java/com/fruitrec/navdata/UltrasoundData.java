package com.fruitrec.navdata;


public class UltrasoundData
{
  private int us_debut_echo;
  
  private int us_fin_echo;
  private int us_association_echo;
  private int us_distance_echo;
  private int us_courbe_temps;
  private int us_courbe_valeur;
  private int us_courbe_ref;
  private int flag_echo_ini;
  private int nb_echo;
  private long sum_echo;
  private int alt_temp_raw;
  private short gradient;
  
  public UltrasoundData(int us_debut_echo, int us_fin_echo, int us_association_echo, int us_distance_echo, int us_courbe_temps, int us_courbe_valeur, int us_courbe_ref, int flag_echo_ini, int nb_echo, long sum_echo, int alt_temp_raw, short gradient)
  {
    this.us_debut_echo = us_debut_echo;
    this.us_fin_echo = us_fin_echo;
    this.us_association_echo = us_association_echo;
    this.us_distance_echo = us_distance_echo;
    this.us_courbe_temps = us_courbe_temps;
    this.us_courbe_valeur = us_courbe_valeur;
    this.us_courbe_ref = us_courbe_ref;
    this.flag_echo_ini = flag_echo_ini;
    this.nb_echo = nb_echo;
    this.sum_echo = sum_echo;
    this.alt_temp_raw = alt_temp_raw;
    this.gradient = gradient;
  }
  


  public int getDebutEcho()
  {
    return this.us_debut_echo;
  }
  


  public int getFinEcho()
  {
    return this.us_fin_echo;
  }
  


  public int getAssociationEcho()
  {
    return this.us_association_echo;
  }
  


  public int getDistanceEcho()
  {
    return this.us_distance_echo;
  }
  


  public int getCourbeTemps()
  {
    return this.us_courbe_temps;
  }
  


  public int getCourbeValeur()
  {
    return this.us_courbe_valeur;
  }
  


  public int getCourbeRef()
  {
    return this.us_courbe_ref;
  }
  


  public int getFlagEchoIni()
  {
    return this.flag_echo_ini;
  }
  


  public int getNbEcho()
  {
    return this.nb_echo;
  }
  


  public long getSumEcho()
  {
    return this.sum_echo;
  }
  


  public int getAltTempRaw()
  {
    return this.alt_temp_raw;
  }
  


  public short getGradient()
  {
    return this.gradient;
  }
  



  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("UltrasoundData [us_debut_echo=");
    builder.append(this.us_debut_echo);
    builder.append(", us_fin_echo=");
    builder.append(this.us_fin_echo);
    builder.append(", us_association_echo=");
    builder.append(this.us_association_echo);
    builder.append(", us_distance_echo=");
    builder.append(this.us_distance_echo);
    builder.append(", us_courbe_temps=");
    builder.append(this.us_courbe_temps);
    builder.append(", us_courbe_valeur=");
    builder.append(this.us_courbe_valeur);
    builder.append(", us_courbe_ref=");
    builder.append(this.us_courbe_ref);
    builder.append(", flag_echo_ini=");
    builder.append(this.flag_echo_ini);
    builder.append(", nb_echo=");
    builder.append(this.nb_echo);
    builder.append(", sum_echo=");
    builder.append(this.sum_echo);
    builder.append(", alt_temp_raw=");
    builder.append(this.alt_temp_raw);
    builder.append(", gradient=");
    builder.append(this.gradient);
    builder.append("]");
    return builder.toString();
  }
}
