package de.yadrone.base;

import de.yadrone.base.command.CommandManager;
import de.yadrone.base.command.VideoChannel;
import de.yadrone.base.configuration.ConfigurationManager;
import de.yadrone.base.navdata.NavDataManager;
import de.yadrone.base.video.VideoDecoder;
import de.yadrone.base.video.VideoManager;
import de.yadrone.base.video.xuggler.XugglerDecoder;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;



















public class ARDrone
  implements IARDrone
{
  private static final String IP_ADDRESS = "192.168.1.1";
  private String ipaddr = null;
  private InetAddress inetaddr = null;
  private VideoDecoder videoDecoder = null;
  

  private CommandManager commandManager = null;
  private VideoManager videoManager = null;
  private NavDataManager navdataManager = null;
  private ConfigurationManager configurationManager = null;
  



  private int speed = 25;
  private Set<ISpeedListener> speedListener = null;
  
  public ARDrone()
  {
    this("192.168.1.1");
  }
  
  public ARDrone(String ipaddr) {
    this(ipaddr, new XugglerDecoder());
  }
  




  public ARDrone(String ipaddr, VideoDecoder videoDecoder)
  {
    this.ipaddr = ipaddr;
    this.videoDecoder = videoDecoder;
    this.speedListener = new HashSet();
  }
  
  public synchronized CommandManager getCommandManager() {
    if (this.commandManager == null) {
      InetAddress ia = getInetAddress();
      this.commandManager = new CommandManager(ia);
    }
    return this.commandManager;
  }
  
  public synchronized NavDataManager getNavDataManager() {
    if (this.navdataManager == null) {
      InetAddress ia = getInetAddress();
      CommandManager cm = getCommandManager();
      this.navdataManager = new NavDataManager(ia, cm);
    }
    return this.navdataManager;
  }
  

  public synchronized VideoManager getVideoManager()
  {
    if (this.videoDecoder == null) {
      return null;
    }
    if (this.videoManager == null) {
      InetAddress ia = getInetAddress();
      CommandManager cm = getCommandManager();
      this.videoManager = new VideoManager(ia, cm, this.videoDecoder);
    }
    return this.videoManager;
  }
  
  public synchronized ConfigurationManager getConfigurationManager() {
    if (this.configurationManager == null) {
      InetAddress ia = getInetAddress();
      CommandManager cm = getCommandManager();
      this.configurationManager = new ConfigurationManager(ia, cm);
    }
    return this.configurationManager;
  }
  
  public void stop()
  {
    freeze();
    landing();
    CommandManager cm = getCommandManager();
    cm.stop();
    ConfigurationManager cfgm = getConfigurationManager();
    cfgm.close();
    NavDataManager nm = getNavDataManager();
    nm.stop();
    VideoManager vm = getVideoManager();
    if (vm != null) {
      vm.close();
    }
  }
  
  public void start() {
    CommandManager cm = getCommandManager();
    cm.start();
    ConfigurationManager cfgm = getConfigurationManager();
    cfgm.start();
    NavDataManager nm = getNavDataManager();
    nm.start();
    VideoManager vm = getVideoManager();
    if (vm != null) {
      vm.start();
    }
  }
  
  public void setHorizontalCamera() {
    if (this.commandManager != null) {
      this.commandManager.setVideoChannel(VideoChannel.HORI);
    }
  }
  
  public void setVerticalCamera() {
    if (this.commandManager != null) {
      this.commandManager.setVideoChannel(VideoChannel.VERT);
    }
  }
  
  public void setHorizontalCameraWithVertical() {
    if (this.commandManager != null) {
      this.commandManager.setVideoChannel(VideoChannel.LARGE_HORI_SMALL_VERT);
    }
  }
  
  public void setVerticalCameraWithHorizontal() {
    if (this.commandManager != null) {
      this.commandManager.setVideoChannel(VideoChannel.LARGE_VERT_SMALL_HORI);
    }
  }
  
  public void toggleCamera() {
    if (this.commandManager != null) {
      this.commandManager.setVideoChannel(VideoChannel.NEXT);
    }
  }
  
  public void landing() {
    if (this.commandManager != null) {
      this.commandManager.landing();
    }
  }
  
  public void takeOff() {
    if (this.commandManager != null) {
      this.commandManager.takeOff();
    }
  }
  
  public void reset() {
    if (this.commandManager != null) {
      this.commandManager.emergency();
    }
  }
  
  public void forward() {
    if (this.commandManager != null) {
      this.commandManager.forward(this.speed);
    }
  }
  
  public void backward() {
    if (this.commandManager != null) {
      this.commandManager.backward(this.speed);
    }
  }
  
  public void spinRight() {
    if (this.commandManager != null) {
      this.commandManager.spinRight(this.speed);
    }
  }
  
  public void spinLeft() {
    if (this.commandManager != null) {
      this.commandManager.spinLeft(this.speed);
    }
  }
  
  public void up() {
    if (this.commandManager != null) {
      this.commandManager.up(this.speed);
    }
  }
  
  public void down() {
    if (this.commandManager != null) {
      this.commandManager.down(this.speed);
    }
  }
  
  public void goRight() {
    if (this.commandManager != null) {
      this.commandManager.goRight(this.speed);
    }
  }
  
  public void goLeft() {
    if (this.commandManager != null) {
      this.commandManager.goLeft(this.speed);
    }
  }
  
  public void freeze() {
    if (this.commandManager != null)
      this.commandManager.freeze();
  }
  
  public void hover() {
    if (this.commandManager != null) {
      this.commandManager.hover();
    }
  }
  
  public void setMaxAltitude(int altitude) {
    if (this.commandManager != null) {
      this.commandManager.setMaxAltitude(altitude);
    }
  }
  
  public void setMinAltitude(int altitude) {
    if (this.commandManager != null) {
      this.commandManager.setMinAltitude(altitude);
    }
  }
  
  public void move3D(int speedX, int speedY, int speedZ, int speedSpin) {
    if (this.commandManager != null) {
      this.commandManager.move(speedX, speedY, speedZ, speedSpin);
    }
  }
  
  public void setSpeed(int speed) {
    if (this.speed != speed)
    {
      this.speed = speed;
      

      Iterator<ISpeedListener> iter = this.speedListener.iterator();
      while (iter.hasNext())
      {
        ((ISpeedListener)iter.next()).speedUpdated(speed);
      }
    }
    else
    {
      this.speed = speed;
    }
  }
  





  public int getSpeed()
  {
    return this.speed;
  }
  
  public void addSpeedListener(ISpeedListener speedListener)
  {
    this.speedListener.add(speedListener);
  }
  
  public void removeSpeedListener(ISpeedListener speedListener)
  {
    this.speedListener.remove(speedListener);
  }
  










  public static void error(String message, Object obj)
  {
    System.err.println("[" + obj.getClass() + "] " + message);
  }
  
  private InetAddress getInetAddress() {
    if (this.inetaddr == null) {
      StringTokenizer st = new StringTokenizer(this.ipaddr, ".");
      byte[] ipBytes = new byte[4];
      if (st.countTokens() == 4) {
        for (int i = 0; i < 4; i++) {
          ipBytes[i] = ((byte)Integer.parseInt(st.nextToken()));
        }
      } else {
        error("Incorrect IP address format: " + this.ipaddr, this);
        return null;
      }
      try {
        this.inetaddr = InetAddress.getByAddress(ipBytes);
      } catch (UnknownHostException e) {
        e.printStackTrace();
      }
    }
    return this.inetaddr;
  }
  
  public static abstract interface ISpeedListener
  {
    public abstract void speedUpdated(int paramInt);
  }
}
