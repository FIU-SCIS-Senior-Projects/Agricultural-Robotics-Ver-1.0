package com.fruitrec.command;

public class ConfigureCommand extends ATCommand {
  protected String name;
  protected String value;
  
  public ConfigureCommand(String name, String value) {
    this.name = name;
    this.value = value;
  }
  
  public ConfigureCommand(String name, int value) {
    this(name, String.valueOf(value));
  }
  
  public ConfigureCommand(String name, long l) {
    this(name, String.valueOf(l));
  }
  
  public ConfigureCommand(String name, double d) {
    this(name, Double.doubleToLongBits(d));
  }
  
  public ConfigureCommand(String name, boolean b) {
    this(name, String.valueOf(b));
  }
  
  protected String getID()
  {
    return "CONFIG";
  }
  
  protected Object[] getParameters()
  {
    return new Object[] { this.name, this.value };
  }
  
  public boolean needControlAck()
  {
    return true;
  }

  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("ConfigureCommand [name=");
    builder.append(this.name);
    builder.append(", value=");
    builder.append(this.value);
    builder.append(", qorder=");
    builder.append(this.qorder);
    builder.append("]");
    return builder.toString();
  }
}
