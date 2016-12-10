package com.fruitrec.command;

public class FlatTrimCommand
  extends ATCommand
{
  protected String getID()
  {
    return "FTRIM";
  }

  protected Object[] getParameters()
  {
    return new Object[0];
  }
}
