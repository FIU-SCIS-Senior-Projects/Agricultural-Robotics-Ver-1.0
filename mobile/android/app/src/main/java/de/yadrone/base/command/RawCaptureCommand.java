package de.yadrone.base.command;

public class RawCaptureCommand extends ATCommand
{
  private int picture;
  private int video;
  
  public RawCaptureCommand(boolean picture, boolean video)
  {
    this.picture = (picture ? 1 : 0);
    this.video = (video ? 1 : 0);
  }
  
  protected String getID()
  {
    return "CAP";
  }
  
  protected Object[] getParameters()
  {
    return new Object[] { Integer.valueOf(this.picture), Integer.valueOf(this.video) };
  }
}
