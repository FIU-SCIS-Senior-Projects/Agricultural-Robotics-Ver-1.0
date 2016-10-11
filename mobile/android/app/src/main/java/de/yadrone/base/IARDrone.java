package de.yadrone.base;

import de.yadrone.base.command.CommandManager;
import de.yadrone.base.configuration.ConfigurationManager;
import de.yadrone.base.navdata.NavDataManager;
import de.yadrone.base.video.VideoManager;

public abstract interface IARDrone
{
  public abstract CommandManager getCommandManager();
  
  public abstract NavDataManager getNavDataManager();
  
  public abstract VideoManager getVideoManager();
  
  public abstract ConfigurationManager getConfigurationManager();
  
  public abstract void start();
  
  public abstract void stop();
  
  public abstract void setHorizontalCamera();
  
  public abstract void setVerticalCamera();
  
  public abstract void setHorizontalCameraWithVertical();
  
  public abstract void setVerticalCameraWithHorizontal();
  
  public abstract void toggleCamera();
  
  public abstract void landing();
  
  public abstract void takeOff();
  
  public abstract void reset();
  
  public abstract void forward();
  
  public abstract void backward();
  
  public abstract void spinRight();
  
  public abstract void spinLeft();
  
  public abstract void up();
  
  public abstract void down();
  
  public abstract void goRight();
  
  public abstract void goLeft();
  
  public abstract void freeze();
  
  public abstract void hover();
  
  public abstract int getSpeed();
  
  public abstract void setSpeed(int paramInt);
  
  public abstract void addSpeedListener(ARDrone.ISpeedListener paramISpeedListener);
  
  public abstract void removeSpeedListener(ARDrone.ISpeedListener paramISpeedListener);
  
  public abstract void setMaxAltitude(int paramInt);
  
  public abstract void setMinAltitude(int paramInt);
  
  public abstract void move3D(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
}
