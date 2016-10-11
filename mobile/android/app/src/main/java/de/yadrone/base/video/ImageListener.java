package de.yadrone.base.video;

//import java.awt.image.BufferedImage;
import de.yadrone.base.shim.BufferedImage;

public abstract interface ImageListener
{
  public abstract void imageUpdated(BufferedImage paramBufferedImage);
}
