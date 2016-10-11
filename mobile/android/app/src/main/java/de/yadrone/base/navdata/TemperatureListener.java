package de.yadrone.base.navdata;

import java.util.EventListener;

public abstract interface TemperatureListener
  extends EventListener
{
  public abstract void receivedTemperature(Temperature paramTemperature);
}
