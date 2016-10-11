package de.yadrone.base.command;

public class QuitCommand extends DroneCommand
{
  public Priority getPriority() {
    return Priority.MAX_PRIORITY;
  }
}
