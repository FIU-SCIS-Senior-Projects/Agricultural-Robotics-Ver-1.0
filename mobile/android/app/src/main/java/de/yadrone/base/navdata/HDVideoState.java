package de.yadrone.base.navdata;



public enum HDVideoState
{
  STORAGE_FIFO_IS_FULL(1),  USBKEY_IS_PRESENT(256),  USBKEY_IS_RECORDING(512),  USBKEY_IS_FULL(1024);
  
  public final int value;
  
  private HDVideoState(int value) {
    this.value = value;
  }
  
  public int getValue() {
    return this.value;
  }
  
  public static HDVideoState fromInt(int v) {
    switch (v) {
    case 1: 
      return STORAGE_FIFO_IS_FULL;
    case 256: 
      return USBKEY_IS_PRESENT;
    case 512: 
      return USBKEY_IS_RECORDING;
    case 1024: 
      return USBKEY_IS_FULL;
    }
    return null;
  }
}
