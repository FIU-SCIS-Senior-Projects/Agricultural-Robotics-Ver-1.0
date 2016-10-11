package de.yadrone.base.command;



public class PlayAnimationCommand
  extends ATCommand
{
  protected int animation_no;
  
  protected int duration;
  

  public PlayAnimationCommand(int animation_no, int duration)
  {
    this.animation_no = animation_no;
    this.duration = duration;
  }
  

  protected String getID()
  {
    return "ANIM";
  }
  

  protected Object[] getParameters()
  {
    return new Object[] { Integer.valueOf(this.animation_no), Integer.valueOf(this.duration) };
  }
}
