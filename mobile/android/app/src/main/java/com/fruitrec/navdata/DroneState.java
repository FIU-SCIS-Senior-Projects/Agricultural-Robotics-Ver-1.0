package com.fruitrec.navdata;


public class DroneState
{
  public static final int ARDRONE10 = 0;
  
  public static final int ARDRONE20 = 1;
  
  public static final int VERSION = 1;
  
  private final int state;
  
  private final int vision;
  

  public DroneState(int state, int vision)
  {
    this.state = state;
    this.vision = vision;
  }
  
  public int getStateBits() {
    return this.state;
  }
  
  public boolean isVisionDefined() {
    return this.vision == 1;
  }
  
  public boolean isFlying() {
    return (this.state & 0x1) != 0;
  }
  
  public boolean isVideoEnabled() {
    return (this.state & 0x2) != 0;
  }
  
  public boolean isVisionEnabled() {
    return (this.state & 0x4) != 0;
  }
  
  public ControlAlgorithm getControlAlgorithm() {
    return (this.state & 0x8) != 0 ? ControlAlgorithm.ANGULAR_SPEED_CONTROL : 
      ControlAlgorithm.EULER_ANGELS_CONTROL;
  }
  
  public boolean isAltitudeControlActive() {
    return (this.state & 0x10) != 0;
  }
  
  public boolean isUserFeedbackOn() {
    return (this.state & 0x20) != 0;
  }
  
  public boolean isControlReceived() {
    return (this.state & 0x40) != 0;
  }
  
  public boolean isTrimReceived() {
    return (this.state & 0x80) != 0;
  }
  
  public boolean isCameraReady() {
    return (this.state & 0x80) != 0;
  }
  
  public boolean isTrimRunning() {
    return (this.state & 0x100) != 0;
  }
  
  public boolean isTravellingMask() {
    return (this.state & 0x100) != 0;
  }
  
  public boolean isTrimSucceeded() {
    return (this.state & 0x200) != 0;
  }
  
  public boolean isUsbKeyReady() {
    return (this.state & 0x200) != 0;
  }
  
  public boolean isNavDataDemoOnly() {
    return (this.state & 0x400) != 0;
  }
  
  public boolean isNavDataBootstrap() {
    return (this.state & 0x800) != 0;
  }
  
  public boolean isMotorsDown() {
    return (this.state & 0x1000) != 0;
  }
  
  public boolean isCommunicationLost() {
    return (this.state & 0x2000) != 0;
  }
  
  public boolean isGyrometersDown()
  {
    return (this.state & 0x4000) != 0;
  }
  
  public boolean isSoftwareFaultDetected() {
    return (this.state & 0x4000) != 0;
  }
  
  public boolean isBatteryTooLow() {
    return (this.state & 0x8000) != 0;
  }
  
  public boolean isBatteryTooHigh() {
    return (this.state & 0x10000) != 0;
  }
  
  public boolean isUserEmergencyLanding() {
    return (this.state & 0x10000) != 0;
  }
  
  public boolean isTimerElapsed() {
    return (this.state & 0x20000) != 0;
  }
  
  public boolean isNotEnoughPower() {
    return (this.state & 0x40000) != 0;
  }
  
  public boolean isMagnetoCalibrationNeeded() {
    return (this.state & 0x40000) != 0;
  }
  
  public boolean isAngelsOutOufRange() {
    return (this.state & 0x80000) != 0;
  }
  
  public boolean isTooMuchWind() {
    return (this.state & 0x100000) != 0;
  }
  
  public boolean isUltrasonicSensorDeaf() {
    return (this.state & 0x200000) != 0;
  }
  
  public boolean isCutoutSystemDetected() {
    return (this.state & 0x400000) != 0;
  }
  
  public boolean isPICVersionNumberOK() {
    return (this.state & 0x800000) != 0;
  }
  
  public boolean isATCodedThreadOn() {
    return (this.state & 0x1000000) != 0;
  }
  
  public boolean isNavDataThreadOn() {
    return (this.state & 0x2000000) != 0;
  }
  
  public boolean isVideoThreadOn() {
    return (this.state & 0x4000000) != 0;
  }
  
  public boolean isAcquisitionThreadOn() {
    return (this.state & 0x8000000) != 0;
  }
  
  public boolean isControlWatchdogDelayed() {
    return (this.state & 0x10000000) != 0;
  }
  
  public boolean isADCWatchdogDelayed() {
    return (this.state & 0x20000000) != 0;
  }
  
  public boolean isCommunicationProblemOccurred() {
    return (this.state & 0x40000000) != 0;
  }
  
  public boolean isEmergency() {
    return (this.state & 0x80000000) != 0;
  }
  
  public boolean equals(Object o) {
    if ((o == null) || (o.getClass() != getClass()))
      return false;
    return (this.state == ((DroneState)o).state) && (this.vision == ((DroneState)o).vision);
  }
  
  public int hashCode() {
    return 31 * this.state + 15 * this.vision;
  }
  
  public String toString() {
    StringBuffer sb = new StringBuffer();
    
    sb.append("isVisionDefined: " + isVisionDefined() + "\n");
    sb.append("isFlying: " + isFlying() + "\n");
    sb.append("isVideoEnabled: " + isVideoEnabled() + "\n");
    sb.append("isVisionEnabled: " + isVisionEnabled() + "\n");
    sb.append("controlAlgo: " + getControlAlgorithm() + "\n");
    sb.append("AltitudeControlActive: " + isAltitudeControlActive() + "\n");
    sb.append("isUserFeedbackOn: " + isUserFeedbackOn() + "\n");
    sb.append("ControlReceived: " + isControlReceived() + "\n");
    
    sb.append("isCameraReady: " + isCameraReady() + "\n");
    sb.append("isTravellingMask: " + isTravellingMask() + "\n");
    sb.append("isUsbKeyReady: " + isUsbKeyReady() + "\n");
    sb.append("isSoftwareFaultDetected: " + isSoftwareFaultDetected() + 
      "\n");
    sb.append("isUserEmergencyLanding: " + isUserEmergencyLanding() + 
      "\n");
    sb.append("isMagnetoCalibrationNeeded: " + 
      isMagnetoCalibrationNeeded() + "\n");
    








    sb.append("isNavDataDemoOnly: " + isNavDataDemoOnly() + "\n");
    sb.append("isNavDataBootstrap: " + isNavDataBootstrap() + "\n");
    sb.append("isMotorsDown: " + isMotorsDown() + "\n");
    sb.append("isBatteryLow: " + isBatteryTooLow() + "\n");
    sb.append("isTimerElapsed: " + isTimerElapsed() + "\n");
    sb.append("isAngelsOutOufRange: " + isAngelsOutOufRange() + "\n");
    sb.append("isTooMuchWind: " + isTooMuchWind() + "\n");
    sb.append("isUltrasonicSensorDeaf: " + isUltrasonicSensorDeaf() + "\n");
    sb.append("isCutoutSystemDetected: " + isCutoutSystemDetected() + "\n");
    sb.append("isPICVersionNumberOK: " + isPICVersionNumberOK() + "\n");
    sb.append("isATCodedThreadOn: " + isATCodedThreadOn() + "\n");
    sb.append("isNavDataThreadOn: " + isNavDataThreadOn() + "\n");
    sb.append("isVideoThreadOn: " + isVideoThreadOn() + "\n");
    sb.append("isAcquisitionThreadOn: " + isAcquisitionThreadOn() + "\n");
    sb.append("isControlWatchdogDelayed: " + isControlWatchdogDelayed() + 
      "\n");
    sb.append("isADCWatchdogDelayed: " + isADCWatchdogDelayed() + "\n");
    sb.append("isCommunicationProblemOccurred: " + 
      isCommunicationProblemOccurred() + "\n");
    sb.append("IsEmergency: " + isEmergency() + "\n");
    


    return sb.toString();
  }
}
