package de.yadrone.base.navdata;

import java.util.EventListener;

public abstract interface AdcListener
  extends EventListener
{
  public abstract void receivedFrame(AdcFrame paramAdcFrame);
}
