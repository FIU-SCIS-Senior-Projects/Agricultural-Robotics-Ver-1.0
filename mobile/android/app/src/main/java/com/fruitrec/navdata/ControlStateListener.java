package com.fruitrec.navdata;

import java.util.EventListener;

public abstract interface ControlStateListener
  extends EventListener
{
  public abstract void controlStateChanged(ControlState paramControlState);
}
