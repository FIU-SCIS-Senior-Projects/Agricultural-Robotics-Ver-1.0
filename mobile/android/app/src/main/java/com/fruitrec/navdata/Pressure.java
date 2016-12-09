package com.fruitrec.navdata;

public class Pressure
{
  private int pressure;
  private short temperature;
  private int temperature_meas;
  private int pressure_meas;
  
  public Pressure(int pressure, int pressure_meas) {
    this.pressure = pressure;
    this.pressure_meas = pressure_meas;
  }
  


  public int getValue()
  {
    return this.pressure;
  }
  


  public int getMeasurement()
  {
    return this.pressure_meas;
  }
  



  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("Pressure [pressure=");
    builder.append(this.pressure);
    builder.append(", temperature=");
    builder.append(this.temperature);
    builder.append(", temperature_meas=");
    builder.append(this.temperature_meas);
    builder.append(", pressure_meas=");
    builder.append(this.pressure_meas);
    builder.append("]");
    return builder.toString();
  }
}
