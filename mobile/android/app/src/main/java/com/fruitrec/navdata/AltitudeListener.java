package com.fruitrec.navdata;

import java.util.EventListener;

public abstract interface AltitudeListener
  extends EventListener
{
  public abstract void receivedAltitude(int paramInt);
  
  public abstract void receivedExtendedAltitude(Altitude paramAltitude);
}
