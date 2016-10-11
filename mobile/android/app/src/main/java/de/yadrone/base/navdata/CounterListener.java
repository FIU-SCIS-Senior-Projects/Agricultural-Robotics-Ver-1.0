package de.yadrone.base.navdata;

import java.util.EventListener;

public abstract interface CounterListener
  extends EventListener
{
  public abstract void update(Counters paramCounters);
}
