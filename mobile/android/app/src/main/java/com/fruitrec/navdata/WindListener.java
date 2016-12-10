package com.fruitrec.navdata;

import java.util.EventListener;

public abstract interface WindListener
  extends EventListener
{
  public abstract void receivedEstimation(WindEstimationData paramWindEstimationData);
}
