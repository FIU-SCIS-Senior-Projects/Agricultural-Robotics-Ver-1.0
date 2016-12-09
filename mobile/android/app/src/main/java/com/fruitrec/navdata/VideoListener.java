package com.fruitrec.navdata;

import java.util.EventListener;

public abstract interface VideoListener
  extends EventListener
{
  public abstract void receivedHDVideoStreamData(HDVideoStreamData paramHDVideoStreamData);
  
  public abstract void receivedVideoStreamData(VideoStreamData paramVideoStreamData);
}
