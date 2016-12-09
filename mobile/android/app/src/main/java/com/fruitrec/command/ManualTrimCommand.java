package com.fruitrec.command;

public class ManualTrimCommand extends ATCommand
{
  private float pitch;
  private float roll;
  private float yaw;
  
  public ManualTrimCommand(float pitch, float roll, float yaw)
  {
    this.pitch = pitch;
    this.roll = roll;
    this.yaw = yaw;
  }

  protected String getID()
  {
    return "MTRIM";
  }

  protected Object[] getParameters()
  {
    return new Object[] { Float.valueOf(this.pitch), Float.valueOf(this.roll), Float.valueOf(this.yaw) };
  }
}
