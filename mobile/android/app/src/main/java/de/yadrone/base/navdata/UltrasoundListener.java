package de.yadrone.base.navdata;

import java.util.EventListener;

public abstract interface UltrasoundListener
  extends EventListener
{
  public abstract void receivedRawData(UltrasoundData paramUltrasoundData);
}
