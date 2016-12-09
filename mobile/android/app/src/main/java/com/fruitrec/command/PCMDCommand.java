package com.fruitrec.command;

public class PCMDCommand
  extends ATCommand
{
  protected boolean hover;
  
  protected boolean combined_yaw_enabled;
  
  protected float left_right_tilt;
  
  protected float front_back_tilt;
  
  protected float vertical_speed;
  protected float angular_speed;
  protected int mode;
  
  public PCMDCommand(boolean hover, boolean combined_yaw_enabled, float left_right_tilt, float front_back_tilt, float vertical_speed, float angular_speed)
  {
    this.hover = hover;
    this.combined_yaw_enabled = combined_yaw_enabled;
    this.left_right_tilt = left_right_tilt;
    this.front_back_tilt = front_back_tilt;
    this.vertical_speed = vertical_speed;
    this.angular_speed = angular_speed;
    this.mode = 0;
    
    if (!hover) {
      this.mode |= 0x1;
    }
    
    if (combined_yaw_enabled) {
      this.mode |= 0x2;
    }
  }

  public boolean isSticky()
  {
    return ((this.mode & 0x1) != 0) && ((this.left_right_tilt != 0.0F) || (this.front_back_tilt != 0.0F) || (this.vertical_speed != 0.0F) || (this.angular_speed != 0.0F));
  }
  
  protected String getID()
  {
    return "PCMD";
  }
  
  protected Object[] getParameters()
  {
    return new Object[] { Integer.valueOf(this.mode), Float.valueOf(this.left_right_tilt), Float.valueOf(this.front_back_tilt), Float.valueOf(this.vertical_speed), Float.valueOf(this.angular_speed) };
  }
}
