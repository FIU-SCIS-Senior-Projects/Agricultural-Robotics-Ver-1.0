package de.yadrone.base.command;


public abstract class DroneCommand
{
  protected static final byte DEFAULT_STICKY_RATE_MS = 100;
  protected long qorder;
  
  public abstract Priority getPriority();
  
  public long getQorder()
  {
    return this.qorder;
  }
  
  public void setQorder(long qorder) {
    this.qorder = qorder;
  }
  
  public boolean isSticky() {
    return false;
  }
  


  public boolean clearSticky()
  {
    return false;
  }
  


  public long getStickyRate()
  {
    return 100L;
  }
}
