package de.yadrone.base.navdata;

import java.util.EventListener;

public abstract interface TimeListener
  extends EventListener
{
  public abstract void timeReceived(int paramInt1, int paramInt2);
}
