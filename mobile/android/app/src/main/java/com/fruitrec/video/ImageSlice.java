package com.fruitrec.video;

public class ImageSlice
{
  MacroBlock[] MacroBlocks;

  ImageSlice(int macroBlockCount)
  {
    this.MacroBlocks = new MacroBlock[macroBlockCount];
    
    for (int index = 0; index < macroBlockCount; index++) {
      this.MacroBlocks[index] = new MacroBlock();
    }
  }
}
