package de.yadrone.base.video;

import de.yadrone.base.command.CommandManager;
import de.yadrone.base.command.VideoBitRateMode;
import de.yadrone.base.manager.AbstractTCPManager;
//import java.awt.image.BufferedImage;
import de.yadrone.base.shim.BufferedImage;
import java.net.InetAddress;
import java.util.ArrayList;


public class VideoManager extends AbstractTCPManager implements ImageListener
{
  private VideoDecoder decoder;
  private CommandManager manager = null;
  
  private ArrayList<ImageListener> listener = new ArrayList();
  
  public VideoManager(InetAddress inetaddr, CommandManager manager, VideoDecoder decoder) {
    super(inetaddr);
    this.manager = manager;
    this.decoder = decoder;
  }
  
  public void addImageListener(ImageListener listener) {
    this.listener.add(listener);
    if (this.listener.size() == 1)
      this.decoder.setImageListener(this);
  }
  
  public void removeImageListener(ImageListener listener) {
    this.listener.remove(listener);
    if (this.listener.size() == 0) {
      this.decoder.setImageListener(null);
    }
  }
  
  public void imageUpdated(BufferedImage image)
  {
    for (int i = 0; i < this.listener.size(); i++)
    {
      ((ImageListener)this.listener.get(i)).imageUpdated(image);
    }
  }
  
  public boolean connect(int port) {
    if (this.decoder == null) {
      return false;
    }
    return super.connect(port);
  }
  
  public void run()
  {
    if (this.decoder == null)
      return;
    connect(5555);
    ticklePort(5555);
    
    this.manager.setVideoBitrateControl(VideoBitRateMode.DISABLED);
    
    this.decoder.decode(getInputStream());
    
    close();
  }
  
  public void close()
  {
    if (this.decoder == null) {
      return;
    }
    super.close();
  }
}
