package de.yadrone.base.command;

public class MiscCommand extends ATCommand {
  protected int arg1;
  protected int arg2;
  protected int arg3;
  protected int arg4;
  
  public MiscCommand(int arg1, int arg2, int arg3, int arg4) {
    this.arg1 = arg1;
    this.arg2 = arg2;
    this.arg3 = arg3;
    this.arg4 = arg4;
  }
  
  protected String getID()
  {
    return "MISC";
  }
  
  protected Object[] getParameters()
  {
    return new Object[] { Integer.valueOf(this.arg1), Integer.valueOf(this.arg2), Integer.valueOf(this.arg3), Integer.valueOf(this.arg4) };
  }
  
  public Priority getPriority()
  {
    return Priority.MAX_PRIORITY;
  }
}
