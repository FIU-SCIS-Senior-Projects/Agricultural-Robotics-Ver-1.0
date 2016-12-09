package com.fruitrec.navdata;

import java.util.EventListener;

public abstract interface AdcListener
  extends EventListener
{
  public abstract void receivedFrame(AdcFrame paramAdcFrame);
}
