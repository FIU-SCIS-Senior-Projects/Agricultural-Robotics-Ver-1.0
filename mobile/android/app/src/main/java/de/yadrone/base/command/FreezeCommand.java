package de.yadrone.base.command;

public class FreezeCommand extends PCMDCommand
{
  public FreezeCommand() {
    super(false, false, 0.0F, 0.0F, 0.0F, 0.0F);
  }
  





  public boolean isSticky()
  {
    return false;
  }
  



  public boolean clearSticky()
  {
    return true;
  }
}
