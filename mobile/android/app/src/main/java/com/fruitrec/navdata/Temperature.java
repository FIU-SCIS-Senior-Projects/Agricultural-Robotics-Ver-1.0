package com.fruitrec.navdata;

public class Temperature
{
  private short temperature;
  private int temperature_meas;
  
  public Temperature(short temperature, int temperature_meas) {
    this.temperature = temperature;
    this.temperature_meas = temperature_meas;
  }
  


  public short getValue()
  {
    return this.temperature;
  }
  


  public int getMeasurement()
  {
    return this.temperature_meas;
  }
  



  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("Temperature [temperature=");
    builder.append(this.temperature);
    builder.append(", temperature_meas=");
    builder.append(this.temperature_meas);
    builder.append("]");
    return builder.toString();
  }
}
