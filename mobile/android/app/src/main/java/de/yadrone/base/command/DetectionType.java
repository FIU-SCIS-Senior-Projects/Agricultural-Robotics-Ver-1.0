package de.yadrone.base.command;

public enum DetectionType {
  HORIZONTAL(
    "detections_select_h"), 
  VERTICAL(
    "detections_select_v"), 
  VERTICAL_HSYNC(
    "detections_select_v_hsync");
  
  private String cmdSuffix;
  
  private DetectionType(String cmdSuffix) {
    this.cmdSuffix = cmdSuffix;
  }
  


  public String getCmdSuffix()
  {
    return this.cmdSuffix;
  }
  
  public static DetectionType fromInt(int v) {
    DetectionType[] values = values();
    if ((v < 0) || (v > values.length)) {
      return null;
    }
    return values[v];
  }
}
