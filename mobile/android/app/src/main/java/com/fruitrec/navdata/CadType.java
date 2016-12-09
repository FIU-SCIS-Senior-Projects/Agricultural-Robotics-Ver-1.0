package com.fruitrec.navdata;





public enum CadType
{
  HORIZONTAL, 
  
  VERTICAL, 
  
  VISION, 
  
  NONE, 
  
  COCARDE, 
  
  ORIENTED_COCARDE, 
  
  STRIPE, 
  
  H_COCARDE, 
  
  H_ORIENTED_COCARDE, 
  
  STRIPE_V, 
  
  MULTIPLE_DETECTION_MODE, 
  
  TYPE_CAP, 
  
  ORIENTED_COCARDE_BW, 
  
  VISION_V2, 
  
  TOWER_SIDE;
  
  public static CadType fromInt(int v)
  {
    CadType[] values = values();
    if ((v < 0) || (v > values.length)) {
      return null;
    }
    return values[v];
  }
  

  public static CadType fromMask(int types)
  {
    CadType[] values = values();
    
    for (int n = 0; n < values.length; n++) {
      if ((types & 0x1) != 0)
      {
        return values[n];
      }
      types >>>= 1;
    }
    
    return null;
  }
}
