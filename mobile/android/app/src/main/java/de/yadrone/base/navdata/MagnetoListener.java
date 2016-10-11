package de.yadrone.base.navdata;

import java.util.EventListener;

public abstract interface MagnetoListener
  extends EventListener
{
  public abstract void received(MagnetoData paramMagnetoData);
}
