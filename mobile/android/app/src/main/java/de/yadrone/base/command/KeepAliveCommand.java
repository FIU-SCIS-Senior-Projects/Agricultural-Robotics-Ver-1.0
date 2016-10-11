package de.yadrone.base.command;

public class KeepAliveCommand extends ATCommand
{
  protected String getID()
  {
    return "COMWDG";
  }
  
  protected Object[] getParameters()
  {
    return new Object[0];
  }
  
  public Priority getPriority()
  {
    return Priority.VERY_HIGH_PRIORITY;
  }
}
