package com.fruitrec.navdata;

import java.util.EventListener;

public abstract interface WifiListener
  extends EventListener
{
  public abstract void received(long paramLong);
}
