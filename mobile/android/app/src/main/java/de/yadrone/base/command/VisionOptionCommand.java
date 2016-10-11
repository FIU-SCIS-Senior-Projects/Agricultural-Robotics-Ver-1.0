package de.yadrone.base.command;

public class VisionOptionCommand extends ATCommand
{
  private int option;
  
  public VisionOptionCommand(int option)
  {
    this.option = option;
  }
  
  protected String getID()
  {
    return "VISO";
  }
  
  protected Object[] getParameters()
  {
    return new Object[] { Integer.valueOf(this.option) };
  }
}
