package de.yadrone.base.video;

import java.io.InputStream;

public abstract interface VideoDecoder
{
  public abstract void decode(InputStream paramInputStream);
  
  public abstract void setImageListener(ImageListener paramImageListener);
}
