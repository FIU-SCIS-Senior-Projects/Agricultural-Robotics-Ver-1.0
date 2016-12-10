package com.fruitrec.navdata;

import java.util.EventListener;

public abstract interface VisionListener
  extends EventListener
{
  public abstract void tagsDetected(VisionTag[] paramArrayOfVisionTag);
  
  public abstract void trackersSend(TrackerData paramTrackerData);
  
  public abstract void receivedPerformanceData(VisionPerformance paramVisionPerformance);
  
  public abstract void receivedRawData(float[] paramArrayOfFloat);
  
  public abstract void receivedData(VisionData paramVisionData);
  
  public abstract void receivedVisionOf(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2);
  
  public abstract void typeDetected(int paramInt);
}
