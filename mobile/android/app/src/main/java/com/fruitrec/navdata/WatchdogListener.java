package com.fruitrec.navdata;

import java.util.EventListener;

public abstract interface WatchdogListener
  extends EventListener
{
  public abstract void received(int paramInt);
}
