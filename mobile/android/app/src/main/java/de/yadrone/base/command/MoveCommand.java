package de.yadrone.base.command;

public class MoveCommand extends PCMDCommand
{
  public MoveCommand(boolean combined_yaw_enabled, float left_right_tilt, float front_back_tilt, float vertical_speed, float angular_speed) {
    super(false, combined_yaw_enabled, left_right_tilt, front_back_tilt, vertical_speed, angular_speed);
  }
  





  public boolean isSticky()
  {
    return true;
  }
}
