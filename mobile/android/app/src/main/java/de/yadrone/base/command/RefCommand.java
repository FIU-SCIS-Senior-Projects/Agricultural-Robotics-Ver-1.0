package de.yadrone.base.command;

public class RefCommand extends ATCommand {
  protected int value;
  
  protected RefCommand(boolean takeoff, boolean emergency) {
    this.value = 290717696;
    
    if (emergency) {
      this.value |= 0x100;
    }
    
    if (takeoff) {
      this.value |= 0x200;
    }
  }
  



  public boolean clearSticky()
  {
    return true;
  }
  
  protected String getID()
  {
    return "REF";
  }
  
  protected Object[] getParameters()
  {
    return new Object[] { Integer.valueOf(this.value) };
  }
  
  public Priority getPriority()
  {
    return Priority.HIGH_PRIORITY;
  }
}
