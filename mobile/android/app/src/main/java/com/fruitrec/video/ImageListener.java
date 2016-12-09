package com.fruitrec.video;

//import java.awt.image.BufferedImage;
import com.fruitrec.shim.BufferedImage;

public abstract interface ImageListener
{
  public abstract void imageUpdated(BufferedImage paramBufferedImage);
}
