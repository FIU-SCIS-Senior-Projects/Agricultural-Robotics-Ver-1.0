package de.yadrone.base.navdata;

import java.util.EventListener;

public abstract interface Zimmu3000Listener
  extends EventListener
{
  public abstract void received(int paramInt, float paramFloat);
}
