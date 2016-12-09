package com.fruitrec.navdata;

import java.util.EventListener;

public abstract interface GyroListener
  extends EventListener
{
  public abstract void receivedRawData(GyroRawData paramGyroRawData);
  
  public abstract void receivedPhysData(GyroPhysData paramGyroPhysData);
  
  public abstract void receivedOffsets(float[] paramArrayOfFloat);
}
