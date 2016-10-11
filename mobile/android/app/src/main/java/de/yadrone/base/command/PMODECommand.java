package de.yadrone.base.command;

public class PMODECommand extends ATCommand {
  protected int mode;
  
  public PMODECommand(int mode) {
    this.mode = mode;
  }
  
  protected String getID()
  {
    return "PMODE";
  }
  
  protected Object[] getParameters()
  {
    return new Object[] { Integer.valueOf(this.mode) };
  }
  
  public Priority getPriority()
  {
    return Priority.MAX_PRIORITY;
  }
}
