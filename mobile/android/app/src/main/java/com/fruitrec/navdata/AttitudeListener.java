package com.fruitrec.navdata;

import java.util.EventListener;

public abstract interface AttitudeListener
  extends EventListener
{
  public abstract void attitudeUpdated(float paramFloat1, float paramFloat2, float paramFloat3);
  
  public abstract void attitudeUpdated(float paramFloat1, float paramFloat2);
  
  public abstract void windCompensation(float paramFloat1, float paramFloat2);
}
