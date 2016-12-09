package com.fruitrec.navdata;

import java.util.EventListener;

public abstract interface StateListener
  extends EventListener
{
  public abstract void stateChanged(DroneState paramDroneState);
  
  public abstract void controlStateChanged(ControlState paramControlState);
}
