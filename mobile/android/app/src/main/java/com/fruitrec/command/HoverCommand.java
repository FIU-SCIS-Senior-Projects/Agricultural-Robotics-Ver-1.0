package com.fruitrec.command;

public class HoverCommand extends PCMDCommand {
  public HoverCommand() {
    super(true, false, 0.0F, 0.0F, 0.0F, 0.0F);
  }

  public boolean clearSticky()
  {
    return true;
  }
}
