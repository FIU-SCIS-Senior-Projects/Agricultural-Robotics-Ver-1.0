package com.fruitrec.command;

public class ControlCommand
  extends ATCommand
{
  protected ControlMode mode;
  
  protected int arg2;

  public ControlCommand(ControlMode mode, int arg2)
  {
    this.mode = mode;
    this.arg2 = arg2;
  }
  
  protected String getID()
  {
    return "CTRL";
  }
  
  protected Object[] getParameters()
  {
    return new Object[] { Integer.valueOf(this.mode.ordinal()), Integer.valueOf(this.arg2) };
  }
}
