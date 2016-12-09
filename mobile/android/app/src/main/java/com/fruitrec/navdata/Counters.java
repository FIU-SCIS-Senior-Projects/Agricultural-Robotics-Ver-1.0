package com.fruitrec.navdata;

public class Counters
{
  private long double_tap_counter;
  private long finish_line_counter;
  
  public Counters(long double_tap_counter, long finish_line_counter) {
    this.double_tap_counter = double_tap_counter;
    this.finish_line_counter = finish_line_counter;
  }
  


  public long getNrOfDoubleTaps()
  {
    return this.double_tap_counter;
  }
  


  public long getNrOfFinishLines()
  {
    return this.finish_line_counter;
  }
  



  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("Counters [double_tap_counter=");
    builder.append(this.double_tap_counter);
    builder.append(", finish_line_counter=");
    builder.append(this.finish_line_counter);
    builder.append("]");
    return builder.toString();
  }
}
