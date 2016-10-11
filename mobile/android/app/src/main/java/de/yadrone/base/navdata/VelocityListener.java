package de.yadrone.base.navdata;

import java.util.EventListener;

public abstract interface VelocityListener
  extends EventListener
{
  public abstract void velocityChanged(float paramFloat1, float paramFloat2, float paramFloat3);
}
