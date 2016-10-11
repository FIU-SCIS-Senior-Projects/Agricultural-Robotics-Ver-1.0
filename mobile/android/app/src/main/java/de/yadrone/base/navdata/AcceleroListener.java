package de.yadrone.base.navdata;

import java.util.EventListener;

public abstract interface AcceleroListener
  extends EventListener
{
  public abstract void receivedRawData(AcceleroRawData paramAcceleroRawData);
  
  public abstract void receivedPhysData(AcceleroPhysData paramAcceleroPhysData);
}
