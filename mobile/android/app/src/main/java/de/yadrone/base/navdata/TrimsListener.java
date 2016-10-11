package de.yadrone.base.navdata;

import java.util.EventListener;

public abstract interface TrimsListener
  extends EventListener
{
  public abstract void receivedTrimData(float paramFloat1, float paramFloat2, float paramFloat3);
}
