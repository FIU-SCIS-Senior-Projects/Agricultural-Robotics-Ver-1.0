package de.yadrone.base.video;
















public class MacroBlock
{
  short[][] DataBlocks;
  















  MacroBlock()
  {
    this.DataBlocks = new short[6][];
    
    for (int index = 0; index < 6; index++) {
      this.DataBlocks[index] = new short[64];
    }
  }
}
