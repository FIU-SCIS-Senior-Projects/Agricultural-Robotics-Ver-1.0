package com.fruitrec.command;

public enum GroundStripeColor {
  ORANGE_GREEN(16),  YELLOW_BLUE(17);
  
  private int value;
  
  private GroundStripeColor(int value) { this.value = value; }
  
  public int getValue()
  {
    return this.value;
  }
}
