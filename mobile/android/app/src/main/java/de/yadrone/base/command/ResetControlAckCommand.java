package de.yadrone.base.command;

public class ResetControlAckCommand extends ControlCommand
{
  public ResetControlAckCommand() {
    super(ControlMode.ACK, 0);
  }
  





  public Priority getPriority()
  {
    return Priority.MAX_PRIORITY;
  }
}
