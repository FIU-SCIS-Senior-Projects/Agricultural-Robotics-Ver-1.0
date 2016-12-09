package com.fruitrec.command;

public class CalibrationCommand
  extends ATCommand
{
  protected Device device;

  public CalibrationCommand(Device device)
  {
    this.device = device;
  }
  
  protected String getID()
  {
    return "CALIB";
  }
  
  protected Object[] getParameters()
  {
    return new Object[] { Integer.valueOf(this.device.ordinal()) };
  }
}
