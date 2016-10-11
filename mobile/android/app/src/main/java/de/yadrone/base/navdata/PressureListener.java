package de.yadrone.base.navdata;

import java.util.EventListener;

public abstract interface PressureListener
  extends EventListener
{
  public abstract void receivedKalmanPressure(KalmanPressureData paramKalmanPressureData);
  
  public abstract void receivedPressure(Pressure paramPressure);
}
