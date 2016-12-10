package com.fruitrec.command;

import com.fruitrec.manager.AbstractManager;
import com.fruitrec.navdata.CadType;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class CommandManager
  extends AbstractManager
{
  private CommandQueue q;
  private static int seq = 1;
  
  public CommandManager(InetAddress inetaddr) {
    super(inetaddr);
    this.q = new CommandQueue(100);
    initARDrone();
  }
  
  public void resetCommunicationWatchDog() {
    this.q.add(new KeepAliveCommand());
  }
  
  public void setVideoChannel(VideoChannel c) {
    this.q.add(new VideoChannelCommand(c));
  }
  
  public void landing() {
    this.q.add(new LandCommand());
  }
  
  public void flatTrim() {
    this.q.add(new FlatTrimCommand());
  }
  
  public void manualTrim(float pitch, float roll, float yaw) {
    this.q.add(new ManualTrimCommand(pitch, roll, yaw));
  }
  
  public void takeOff() {
    flatTrim();
    this.q.add(new TakeOffCommand());
  }

  public void emergency()
  {
    this.q.add(new EmergencyCommand());
  }
  
  public void forward(int speed) {
    move(0.0F, -perc2float(speed), 0.0F, 0.0F);
  }
  
  public void backward(int speed) {
    move(0.0F, perc2float(speed), 0.0F, 0.0F);
  }
  
  public void spinRight(int speed) {
    move(0.0F, 0.0F, 0.0F, perc2float(speed));
  }
  
  public void spinLeft(int speed) {
    move(0.0F, 0.0F, 0.0F, -perc2float(speed));
  }
  
  public void up(int speed) {
    move(0.0F, 0.0F, perc2float(speed), 0.0F);
  }
  
  public void down(int speed) {
    move(0.0F, 0.0F, -perc2float(speed), 0.0F);
  }
  
  public void goRight(int speed) {
    move(perc2float(speed), 0.0F, 0.0F, 0.0F);
  }
  
  public void goLeft(int speed) {
    move(-perc2float(speed), 0.0F, 0.0F, 0.0F);
  }
  
  public void move(float lrtilt, float fbtilt, float vspeed, float aspeed, float magneto_psi, float magneto_psi_accuracy)
  {
    lrtilt = limit(lrtilt, -1.0F, 1.0F);
    fbtilt = limit(fbtilt, -1.0F, 1.0F);
    vspeed = limit(vspeed, -1.0F, 1.0F);
    aspeed = limit(aspeed, -1.0F, 1.0F);
    magneto_psi = limit(magneto_psi, -1.0F, 1.0F);
    magneto_psi_accuracy = limit(magneto_psi_accuracy, -1.0F, 1.0F);
    this.q.add(new PCMDMagCommand(false, false, true, lrtilt, fbtilt, vspeed, aspeed, magneto_psi, magneto_psi_accuracy));
  }
  
  public void move(float lrtilt, float fbtilt, float vspeed, float aspeed) {
    lrtilt = limit(lrtilt, -1.0F, 1.0F);
    fbtilt = limit(fbtilt, -1.0F, 1.0F);
    vspeed = limit(vspeed, -1.0F, 1.0F);
    aspeed = limit(aspeed, -1.0F, 1.0F);
    
    this.q.add(new MoveCommand(false, lrtilt, fbtilt, vspeed, aspeed));
  }
  
  public void move(int speedX, int speedY, int speedZ, int speedSpin) {
    move(-perc2float(speedY), -perc2float(speedX), -perc2float(speedZ), -perc2float(speedSpin));
  }
  
  public void freeze() {
    this.q.add(new FreezeCommand());
  }
  
  public void hover() {
    this.q.add(new HoverCommand());
  }
  
  private float perc2float(int speed) {
    return speed / 100.0F;
  }

  public void setVideoCodecFps(int fps)
  {
    fps = limit(fps, 15, 30);
    this.q.add(new ConfigureCommand("video:codec_fps", fps));
  }

  public void setVideoBitrateControl(VideoBitRateMode mode)
  {
    this.q.add(new ConfigureCommand("video:bitrate_control_mode", mode.ordinal()));
  }

  public void setVideoBitrate(int rate)
  {
    rate = limit(rate, 250, 4000);
    this.q.add(new ConfigureCommand("video:bitrate", rate));
  }

  public void setMaxVideoBitrate(int rate)
  {
    rate = limit(rate, 250, 4000);
    this.q.add(new ConfigureCommand("video:max_bitrate", rate));
  }
  
  public void setVideoCodec(VideoCodec c)
  {
    this.q.add(new ConfigureCommand("video:video_codec", c.getValue()));
  }

  public void setVideoOnUsb(boolean b)
  {
    this.q.add(new ConfigureCommand("video:video_on_usb", b));
  }

  public void setNavDataDemo(boolean b)
  {
    this.q.add(new ConfigureCommand("general:navdata_demo", b ? "TRUE" : "FALSE"));
  }

  public void setNavDataOptions(int mask)
  {
    this.q.add(new ConfigureCommand("general:navdata_options", mask));
  }

  public void setLedsAnimation(LEDAnimation anim, float freq, int duration)
  {
    this.q.add(new LEDAnimationCommand(anim, freq, duration));
  }

  public void setDetectEnemyWithoutShell(boolean b)
  {
    this.q.add(new ConfigureCommand("detect:enemy_without_shell", b ? "1" : "0"));
  }

  public void setEnemyColors(EnemyColor c)
  {
    this.q.add(new ConfigureCommand("detect:enemy_colors", c.getValue()));
  }

  public void setDetectionType(CadType type)
  {
    int t = type.ordinal();
    this.q.add(new ConfigureCommand("detect:detect_type", t));
  }

  public void setDetectionType(DetectionType dt, VisionTagType[] tagtypes)
  {
    int mask = VisionTagType.getMask(tagtypes);
    this.q.add(new ConfigureCommand("detect:" + dt.getCmdSuffix(), mask));
  }
  
  public void setVisionParameters(int coarse_scale, int nb_pair, int loss_per, int nb_tracker_width, int nb_tracker_height, int scale, int trans_max, int max_pair_dist, int noise)
  {
    this.q.add(new VisionParametersCommand(coarse_scale, nb_pair, loss_per, nb_tracker_width, nb_tracker_height, scale, 
      trans_max, max_pair_dist, noise));
  }
  
  public void setVisionOption(int option)
  {
    this.q.add(new VisionOptionCommand(option));
  }
  

  public void setGains(int pq_kp, int r_kp, int r_ki, int ea_kp, int ea_ki, int alt_kp, int alt_ki, int vz_kp, int vz_ki, int hovering_kp, int hovering_ki, int hovering_b_kp, int hovering_b_ki)
  {
    this.q.add(new GainsCommand(pq_kp, r_kp, r_ki, ea_kp, ea_ki, alt_kp, alt_ki, vz_kp, vz_ki, hovering_kp, hovering_ki, 
      hovering_b_kp, hovering_b_ki));
  }
  
  public void setRawCapture(boolean picture, boolean video)
  {
    this.q.add(new RawCaptureCommand(picture, video));
  }

  public void setEnableCombinedYaw(boolean b)
  {
    int level = 1;
    if (b) {
      level |= 0x4;
    }
    this.q.add(new ConfigureCommand("control:control_level", level));
  }

  public void setFlyingMode(FlyingMode mode)
  {
    this.q.add(new ConfigureCommand("control:flying_mode", mode.ordinal()));
  }

  public void setHoveringRange(int range)
  {
    this.q.add(new ConfigureCommand("control:hovering_range", range));
  }

  public void setMaxEulerAngle(float angle)
  {
    setMaxEulerAngle(Location.CURRENT, angle);
  }

  public void setMaxEulerAngle(Location l, float angle)
  {
    angle = limit(angle, 0.0F, 0.52F);
    System.out.println("CommandManager: setMaxEulerAngle (bendingAngle): " + angle + " rad");
    String command = "control:" + l.getCommandPrefix() + "euler_angle_max";
    this.q.add(new ConfigureCommand(command, String.valueOf(angle)));
  }

  public void setMaxAltitude(int altitude)
  {
    setMaxAltitude(Location.CURRENT, altitude);
  }

  public void setMaxAltitude(Location l, int altitude)
  {
    altitude = limit(altitude, 0, 100000);
    System.out.println("CommandManager: setMaxAltitude: " + altitude + " mm");
    String command = "control:" + l.getCommandPrefix() + "altitude_max";
    this.q.add(new ConfigureCommand(command, altitude));
  }

  public void setMinAltitude(int altitude)
  {
    setMinAltitude(Location.CURRENT, altitude);
  }

  public void setMinAltitude(Location l, int altitude)
  {
    altitude = limit(altitude, 0, 100000);
    String command = "control:" + l.getCommandPrefix() + "altitude_min";
    this.q.add(new ConfigureCommand(command, altitude));
  }

  public void setMaxVz(int speed)
  {
    setMaxVz(Location.CURRENT, speed);
  }

  public void setMaxVz(Location l, int speed)
  {
    speed = limit(speed, 0, 2000);
    System.out.println("CommandManager: setMaxVz (verticalSpeed): " + speed + " mm");
    String command = "control:" + l.getCommandPrefix() + "control_vz_max";
    this.q.add(new ConfigureCommand(command, speed));
  }

  public void setMaxYaw(float speed)
  {
    setMaxYaw(Location.CURRENT, speed);
  }

  public void setMaxYaw(Location l, float speed)
  {
    speed = limit(speed, 0.7F, 6.11F);
    String command = "control:" + l.getCommandPrefix() + "control_yaw";
    this.q.add(new ConfigureCommand(command, speed));
  }
  
  public void setCommand(ATCommand command) {
    this.q.add(command);
  }

  public void setOutdoor(boolean flying_outdoor, boolean outdoor_hull)
  {
    System.out.println("CommandManager: setOutdoor(flyingOutdoor,usingOutdoorHull) = " + flying_outdoor + "," + outdoor_hull);
    this.q.add(new ConfigureCommand("control:outdoor", flying_outdoor));
    this.q.add(new ConfigureCommand("control:flight_without_shell", outdoor_hull));
  }

  public void animate(FlightAnimation a)
  {
    this.q.add(new FlightAnimationCommand(a));
  }
  
  public void setPosition(double latitude, double longitude, double altitude) {
    this.q.add(new ConfigureCommand("gps:latitude", latitude));
    this.q.add(new ConfigureCommand("gps:longitude", longitude));
    this.q.add(new ConfigureCommand("gps:altitude", altitude));
  }

  public void setUltrasoundFrequency(UltrasoundFrequency f)
  {
    this.q.add(new ConfigureCommand("pic:ultrasound_freq", f.getValue()));
  }

  public void setSSIDSinglePlayer(String ssid)
  {
    this.q.add(new ConfigureCommand("network:ssid_single_player", ssid));
  }

  public void setSSIDMultiPlayer(String ssid)
  {
    this.q.add(new ConfigureCommand("network:ssid_multi_player", ssid));
  }

  public void setWifiMode(WifiMode mode)
  {
    this.q.add(new ConfigureCommand("network:wifi_mode", mode.ordinal()));
  }

  public void setOwnerMac(String mac)
  {
    this.q.add(new ConfigureCommand("network:owner_mac", mac));
  }
  
  public void startRecordingNavData(String dirname) {
    this.q.add(new ConfigureCommand("userbox:userbox_cmd", String.valueOf(UserBox.START.ordinal()) + "," + dirname));
  }
  
  public void cancelRecordingNavData() {
    this.q.add(new ConfigureCommand("userbox:userbox_cmd", UserBox.CANCEL.ordinal()));
  }
  
  public void stopRecording() {
    this.q.add(new ConfigureCommand("userbox:userbox_cmd", UserBox.STOP.ordinal()));
  }
  
  private static final SimpleDateFormat USERBOXFORMAT = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US);
  
  public void startRecordingPictures(int delay, int nshots) {
    Date d = new Date();
    String label = USERBOXFORMAT.format(d);
    this.q.add(new ConfigureCommand("userbox:userbox_cmd", String.valueOf(UserBox.SCREENSHOT.ordinal()) + "," + 
      String.valueOf(delay) + "," + String.valueOf(nshots) + "," + label));
  }

  private void sendMisc(int p1, int p2, int p3, int p4)
  {
    this.q.add(new MiscCommand(p1, p2, p3, p4));
  }

  private void sendPMode(int mode)
  {
    this.q.add(new PMODECommand(mode));
  }

  public void run()
  {
    connect(5556);
    
    ATCommand cs = null;
    ATCommand cAck = new ResetControlAckCommand();
    ATCommand cAlive = new KeepAliveCommand();
    long t0 = 0L;
    while (!this.doStop) {
      try {
        long dt;
        if (cs == null)
        {
          dt = 40L;
        }
        else {
          long t = System.currentTimeMillis();
          dt = t - t0;
        }
        ATCommand c = (ATCommand)this.q.poll(dt, TimeUnit.MILLISECONDS);
        
        if (c == null) {
          if (cs == null) {
            c = cAlive;
          } else {
            c = cs;
            t0 = System.currentTimeMillis();
          }
        }
        else if (c.isSticky())
        {
          cs = c;
          t0 = System.currentTimeMillis();
        } else if (c.clearSticky())
        {
          cs = null;
        }
        
        if (c.needControlAck()) {
          waitForControlAck(false);
          sendCommand(c);
          waitForControlAck(true);
          sendCommand(cAck);
        } else {
          sendCommand(c);
        }
      } catch (InterruptedException e) {
        this.doStop = true;
      } catch (Throwable t) {
        t.printStackTrace();
      }
    }
    close();
    System.out.println("doStop() called ? " + this.doStop + " ... Stopped " + getClass().getSimpleName());
  }
  
  private void initARDrone()
  {
    sendPMode(2);
    sendMisc(2, 20, 2000, 3000);
    freeze();
    landing();
    
    setOutdoor(false, false);
    setMaxAltitude(10000);
    setMaxVz(1000);
    setMaxEulerAngle(0.25F);
  }
  
  private synchronized void sendCommand(ATCommand c) throws InterruptedException, IOException {
    if(c instanceof KeepAliveCommand);
    

    byte[] buffer = c.getPacket(seq++);
    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, this.inetaddr, 5556);
    this.socket.send(packet);
  }
  
  private int limit(int i, int min, int max) {
    return i < min ? min : i > max ? max : i;
  }
  
  private float limit(float f, float min, float max) {
    return f < min ? min : f > max ? max : f;
  }

  private Object controlAckLock = new Object();
  private boolean controlAck = false;
  
  public void setControlAck(boolean b) {
    synchronized (this.controlAckLock) {
      this.controlAck = b;
      this.controlAckLock.notifyAll();
    }
  }
  
  private void waitForControlAck(boolean b) throws InterruptedException {
    if (this.controlAck != b) {
      int n = 1;
      synchronized (this.controlAckLock) {
        do {
          this.controlAckLock.wait(50L);
          n--;
          if (n <= 0) break; } while (this.controlAck != b);
      }
      


      if ((n == 0) && (this.controlAck != b)) {
        System.err.println("Control ack timeout " + String.valueOf(b));
      }
    }
  }
}
