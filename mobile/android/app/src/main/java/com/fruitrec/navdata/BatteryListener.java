package com.fruitrec.navdata;

import java.util.EventListener;

public abstract interface BatteryListener
  extends EventListener
{
  public abstract void batteryLevelChanged(int paramInt);
  
  public abstract void voltageChanged(int paramInt);
}
