package com.fruitrec.command;

public enum UltrasoundFrequency {
  F22Hz(7),  F25Hz(8);
  
  private int value;
  
  private UltrasoundFrequency(int value) {
    this.value = value;
  }
  
  public int getValue() {
    return this.value;
  }
}
