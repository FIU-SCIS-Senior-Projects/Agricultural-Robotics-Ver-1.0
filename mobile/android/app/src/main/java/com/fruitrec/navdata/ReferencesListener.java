package com.fruitrec.navdata;

import java.util.EventListener;

public abstract interface ReferencesListener
  extends EventListener
{
  public abstract void receivedRcReferences(int[] paramArrayOfInt);
  
  public abstract void receivedReferences(ReferencesData paramReferencesData);
}
