package com.fruitrec.navdata;

import android.util.Log;

import com.fruitrec.command.CommandManager;
import com.fruitrec.command.DetectionType;
import com.fruitrec.manager.AbstractManager;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.zip.CRC32;

public class NavDataManager
  extends AbstractManager
{
  private static final int NB_ACCS = 3;
  private static final int NB_GYROS = 3;
  private static final int NB_CORNER_TRACKERS_WIDTH = 5;
  private static final int NB_CORNER_TRACKERS_HEIGHT = 4;
  private static final int DEFAULT_NB_TRACKERS_WIDTH = 6;
  private static final int DEFAULT_NB_TRACKERS_HEIGHT = 5;
  private static final int NB_NAVDATA_DETECTION_RESULTS = 4;
  private static final int NAVDATA_MAX_CUSTOM_TIME_SAVE = 20;
  private static final int MAX_PACKET_SIZE = 2048;
  private CommandManager manager = null;
  
  private ArrayList<AttitudeListener> attitudeListener = new ArrayList();
  private ArrayList<AltitudeListener> altitudeListener = new ArrayList();
  private ArrayList<StateListener> stateListener = new ArrayList();
  private ArrayList<VelocityListener> velocityListener = new ArrayList();
  private ArrayList<BatteryListener> batteryListener = new ArrayList();
  private ArrayList<TimeListener> timeListener = new ArrayList();
  private ArrayList<VisionListener> visionListener = new ArrayList();
  private ArrayList<MagnetoListener> magnetoListener = new ArrayList();
  private ArrayList<AcceleroListener> acceleroListener = new ArrayList();
  private ArrayList<GyroListener> gyroListener = new ArrayList();
  private ArrayList<UltrasoundListener> ultrasoundListener = new ArrayList();
  private ArrayList<WatchdogListener> watchdogListener = new ArrayList();
  private ArrayList<AdcListener> adcListener = new ArrayList();
  private ArrayList<CounterListener> counterListener = new ArrayList();
  private ArrayList<PressureListener> pressureListener = new ArrayList();
  private ArrayList<TemperatureListener> temperatureListener = new ArrayList();
  private ArrayList<WindListener> windListener = new ArrayList();
  private ArrayList<VideoListener> videoListener = new ArrayList();
  private ArrayList<WifiListener> wifiListener = new ArrayList();
  private ArrayList<Zimmu3000Listener> zimmu3000Listener = new ArrayList();
  private ArrayList<PWMlistener> pwmlistener = new ArrayList();
  private ArrayList<ReferencesListener> referencesListener = new ArrayList();
  private ArrayList<TrimsListener> trimsListener = new ArrayList();
  private ArrayList<GPSListener> gpsListener = new ArrayList();
  
  private long lastSequenceNumber = 1L;
  
  private int mask = 0;
  private boolean maskChanged = true;
  private int checksum = 0;
  private static final int CKS_TAG = -1;
  
  public NavDataManager(InetAddress inetaddr, CommandManager manager) { super(inetaddr);
    this.manager = manager;
  }
  
  private void setMask(boolean reset, int[] tags) {
    int newmask = 0;
    for (int n = 0; n < tags.length; n++) {
      newmask |= 1 << tags[n];
    }
    if (reset) {
      this.mask &= (newmask ^ 0xFFFFFFFF);
    } else {
      this.mask |= newmask;
    }
    this.maskChanged = true;
  }
  
  public void addAttitudeListener(AttitudeListener attitudeListener) {
    this.attitudeListener.add(attitudeListener);
    setMask(this.attitudeListener.size() == 1, new int[] { 0, 5, 23 });
  }
  
  public void removeAttitudeListener(AttitudeListener attitudeListener) {
    this.attitudeListener.remove(attitudeListener);
    setMask(this.attitudeListener.size() == 0, new int[] { 0, 5, 23 });
  }
  
  public void addAltitudeListener(AltitudeListener altitudeListener) {
    this.altitudeListener.add(altitudeListener);
    setMask(this.altitudeListener.size() == 1, new int[] { 0, 10 });
  }
  
  public void removeAltitudeListener(AltitudeListener altitudeListener) {
    this.altitudeListener.remove(altitudeListener);
    setMask(this.altitudeListener.size() == 0, new int[] { 0, 10 });
  }
  
  public void addBatteryListener(BatteryListener batteryListener) {
    this.batteryListener.add(batteryListener);
    setMask(this.batteryListener.size() == 1, new int[] { 0, 2 });
  }
  
  public void removeBatteryListener(BatteryListener batteryListener) {
    this.batteryListener.remove(batteryListener);
    setMask(this.batteryListener.size() == 0, new int[] { 0, 2 });
  }
  
  public void addTimeListener(TimeListener timeListener) {
    this.timeListener.add(timeListener);
    setMask(this.timeListener.size() == 1, new int[] { 1 });
  }
  
  public void removeTimeListener(TimeListener timeListener) {
    this.timeListener.remove(timeListener);
    setMask(this.timeListener.size() == 0, new int[] { 1 });
  }
  
  public void addStateListener(StateListener stateListener) {
    this.stateListener.add(stateListener);
    setMask(this.stateListener.size() == 1, new int[1]);
  }
  
  public void removeStateListener(StateListener stateListener) {
    this.stateListener.remove(stateListener);
    setMask(this.stateListener.size() == 0, new int[1]);
  }
  
  public void addVelocityListener(VelocityListener velocityListener) {
    this.velocityListener.add(velocityListener);
    setMask(this.velocityListener.size() == 1, new int[1]);
  }
  
  public void removeVelocityListener(VelocityListener velocityListener) {
    this.velocityListener.remove(velocityListener);
    setMask(this.velocityListener.size() == 0, new int[1]);
  }
  
  public void addVisionListener(VisionListener visionListener) {
    this.visionListener.add(visionListener);
    setMask(this.visionListener.size() == 1, new int[] { 0, 15, 16, 12, 
      13, 14, 11 });
  }
  
  public void removeVisionListener(VisionListener visionListener) {
    this.visionListener.remove(visionListener);
    setMask(this.visionListener.size() == 0, new int[] { 0, 15, 16, 12, 
      13, 14, 11 });
  }
  
  public void addMagnetoListener(MagnetoListener magnetoListener) {
    this.magnetoListener.add(magnetoListener);
    setMask(this.magnetoListener.size() == 1, new int[] { 22 });
  }
  
  public void removeMagnetoListener(MagnetoListener magnetoListener) {
    this.magnetoListener.remove(magnetoListener);
    setMask(this.magnetoListener.size() == 0, new int[] { 22 });
  }
  
  public void addAcceleroListener(AcceleroListener acceleroListener) {
    this.acceleroListener.add(acceleroListener);
    setMask(this.acceleroListener.size() == 1, new int[] { 3, 2 });
  }
  
  public void removeAcceleroListener(AcceleroListener acceleroListener) {
    this.acceleroListener.remove(acceleroListener);
    setMask(this.acceleroListener.size() == 0, new int[] { 3, 2 });
  }
  
  public void addGyroListener(GyroListener gyroListener) {
    this.gyroListener.add(gyroListener);
    setMask(this.gyroListener.size() == 1, new int[] { 4, 3, 2 });
  }
  
  public void removeGyroListener(GyroListener gyroListener) {
    this.gyroListener.remove(gyroListener);
    setMask(this.gyroListener.size() == 0, new int[] { 4, 3, 2 });
  }
  
  public void addUltrasoundListener(UltrasoundListener ultrasoundListener) {
    this.ultrasoundListener.add(ultrasoundListener);
    setMask(this.ultrasoundListener.size() == 1, new int[] { 2 });
  }
  
  public void removeUltrasoundListener(UltrasoundListener ultrasoundListener) {
    this.ultrasoundListener.remove(ultrasoundListener);
    setMask(this.ultrasoundListener.size() == 0, new int[] { 2 });
  }
  
  public void addAdcListener(AdcListener adcListener) {
    this.adcListener.add(adcListener);
    setMask(this.adcListener.size() == 1, new int[] { 18 });
  }
  
  public void removeAdcListener(AdcListener adcListener) {
    this.adcListener.remove(adcListener);
    setMask(this.adcListener.size() == 0, new int[] { 18 });
  }
  
  public void addCounterListener(CounterListener counterListener) {
    this.counterListener.add(counterListener);
    setMask(this.counterListener.size() == 1, new int[] { 20 });
  }
  
  public void removeCounterListener(CounterListener counterListener) {
    this.counterListener.remove(counterListener);
    setMask(this.counterListener.size() == 0, new int[] { 20 });
  }
  
  public void addPressureListener(PressureListener pressureListener) {
    this.pressureListener.add(pressureListener);
    setMask(this.pressureListener.size() == 1, new int[] { 24, 21 });
  }
  
  public void removePressureListener(PressureListener pressureListener) {
    this.pressureListener.remove(pressureListener);
    setMask(this.pressureListener.size() == 0, new int[] { 24, 21 });
  }
  
  public void addTemperatureListener(TemperatureListener temperatureListener) {
    this.temperatureListener.add(temperatureListener);
    setMask(this.temperatureListener.size() == 1, new int[] { 21 });
  }
  
  public void removeTemperatureListener(TemperatureListener temperatureListener) {
    this.temperatureListener.remove(temperatureListener);
    setMask(this.temperatureListener.size() == 0, new int[] { 21 });
  }
  
  public void addWindListener(WindListener windListener) {
    this.windListener.add(windListener);
    setMask(this.windListener.size() == 1, new int[] { 23 });
  }
  
  public void removeWindListener(WindListener windListener) {
    this.windListener.remove(windListener);
    setMask(this.windListener.size() == 0, new int[] { 23 });
  }
  
  public void addVideoListener(VideoListener videoListener) {
    this.videoListener.add(videoListener);
    setMask(this.videoListener.size() == 1, new int[] { 25, 19 });
  }
  
  public void removeVideoListener(VideoListener videoListener) {
    this.videoListener.remove(videoListener);
    setMask(this.videoListener.size() == 0, new int[] { 25, 19 });
  }
  
  public void addWifiListener(WifiListener wifiListener) {
    this.wifiListener.add(wifiListener);
    setMask(this.wifiListener.size() == 1, new int[] { 26 });
  }
  
  public void removeWifiListener(WifiListener wifiListener) {
    this.wifiListener.remove(wifiListener);
    setMask(this.wifiListener.size() == 0, new int[] { 26 });
  }

  @Deprecated
  public void addZimmu3000Listener(Zimmu3000Listener zimmu3000Listener) {
    this.zimmu3000Listener.add(zimmu3000Listener);
    setMask(this.zimmu3000Listener.size() == 1, new int[] { 27 });
  }

  @Deprecated
  public void removeZimmu3000Listener(Zimmu3000Listener zimmu3000Listener) {
    this.zimmu3000Listener.remove(zimmu3000Listener);
    setMask(this.zimmu3000Listener.size() == 0, new int[] { 27 });
  }

  public void addGPSListener(GPSListener gpsListener) {
    this.gpsListener.add(gpsListener);
    setMask(this.gpsListener.size() == 1, new int[] { 27 });
  }

  public void removeGPSListener(GPSListener gpsListener) {
    this.gpsListener.remove(gpsListener);
    setMask(this.gpsListener.size() == 0, new int[] { 27 });
  }
  
  public void addPWMlistener(PWMlistener pwmlistener) {
    this.pwmlistener.add(pwmlistener);
    setMask(this.pwmlistener.size() == 1, new int[] { 9 });
  }
  
  public void removePWMlistener(PWMlistener pwmlistener) {
    this.pwmlistener.remove(pwmlistener);
    setMask(this.pwmlistener.size() == 0, new int[] { 9 });
  }
  
  public void addReferencesListener(ReferencesListener referencesListener) {
    this.referencesListener.add(referencesListener);
    setMask(this.referencesListener.size() == 1, new int[] { 8, 6 });
  }
  
  public void removeReferencesListener(ReferencesListener referencesListener) {
    this.referencesListener.remove(referencesListener);
    setMask(this.referencesListener.size() == 0, new int[] { 8, 6 });
  }
  
  public void addTrimsListener(TrimsListener trimsListener) {
    this.trimsListener.add(trimsListener);
    setMask(this.trimsListener.size() == 1, new int[] { 7 });
  }
  
  public void removeTrimsListener(TrimsListener trimsListener) {
    this.trimsListener.remove(trimsListener);
    setMask(this.trimsListener.size() == 0, new int[] { 7 });
  }
  
  public void run()
  {
    connect(5554);
    ticklePort(5554);
    boolean bootstrapping = true;
    boolean controlAck = false;
    
    DatagramPacket packet = new DatagramPacket(new byte['ࠀ'], 2048);
    while (!this.doStop)
    {
      try
      {
        this.socket.receive(packet);
        ByteBuffer buffer = ByteBuffer.wrap(packet.getData(), 0, packet.getLength());
        
        DroneState s = parse(buffer);
        



        if (bootstrapping) {
          controlAck = s.isControlReceived();
          this.manager.setControlAck(controlAck);
          if (s.isNavDataBootstrap())
          {
            this.manager.setNavDataDemo(true);
            System.out.println("Navdata Bootstrapped");
          } else {
            System.out.println("Navdata was already bootstrapped");
          }
          bootstrapping = false;
        }
        

        boolean newcontrolAck = s.isControlReceived();
        if (newcontrolAck != controlAck) {
          this.manager.setControlAck(newcontrolAck);
          controlAck = newcontrolAck;
        }
        

        if (s.isCommunicationProblemOccurred()) {
          this.manager.resetCommunicationWatchDog();
        }
        

        if ((!bootstrapping) && (this.maskChanged)) {
          this.manager.setNavDataDemo(false);
          this.manager.setNavDataOptions(this.mask);
          this.maskChanged = false;
        }
      } catch (SocketTimeoutException t) {
        System.err.println("Navdata reception timeout");
      }
      catch (Throwable t) {
        t.printStackTrace();
      }
    }
    close();
    System.out.println("Stopped " + getClass().getSimpleName());
  }
  
  public DroneState parse(ByteBuffer b) throws NavDataException
  {
    b.order(ByteOrder.LITTLE_ENDIAN);
    int magic = b.getInt();
    

    int state = b.getInt();
    long sequence = getUInt32(b);
    int vision = b.getInt();
    





    this.lastSequenceNumber = sequence;
    
    DroneState s = new DroneState(state, vision);
    for (int i = 0; i < this.stateListener.size(); i++) {
      ((StateListener)this.stateListener.get(i)).stateChanged(s);
    }
    

    while (b.position() < b.limit()) {
      int tag = b.getShort() & 0xFFFF;
      int payloadSize = (b.getShort() & 0xFFFF) - 4;
      ByteBuffer optionData = b.slice().order(ByteOrder.LITTLE_ENDIAN);
      try{
        optionData.limit(payloadSize);
        parseOption(tag, optionData);
        b.position(b.position() + payloadSize);
      } catch(Exception e) {

      } finally {

      }
    }
    

    if (this.checksum != 0) {
      checkEqual(getCRC(b, 0, b.limit() - 4), this.checksum, "Checksum does not match");
      this.checksum = 0;
    }
    return s;
  }
  

  private static final int DEMO_TAG = 0;
  private static final int TIME_TAG = 1;
  private static final int RAW_MEASURES_TAG = 2;
  private static final int PHYS_MEASURES_TAG = 3;
  private static final int GYROS_OFFSETS_TAG = 4;
  private static final int EULER_ANGLES_TAG = 5;
  private static final int REFERENCES_TAG = 6;
  private static final int TRIMS_TAG = 7;
  private static final int RC_REFERENCES_TAG = 8;
  private static final int PWM_TAG = 9;
  private static final int ALTITUDE_TAG = 10;
  private static final int VISION_RAW_TAG = 11;
  private static final int VISION_OF_TAG = 12;
  private static final int VISION_TAG = 13;
  private static final int VISION_PERF_TAG = 14;
  private static final int TRACKERS_SEND_TAG = 15;
  private static final int VISION_DETECT_TAG = 16;
  private static final int WATCHDOG_TAG = 17;
  private static final int ADC_DATA_FRAME_TAG = 18;
  private static final int VIDEO_STREAM_TAG = 19;
  private static final int GAMES_TAG = 20;
  private static final int PRESSURE_RAW_TAG = 21;
  private static final int MAGNETO_TAG = 22;
  private static final int WIND_TAG = 23;
  private static final int KALMAN_PRESSURE_TAG = 24;
  private static final int HDVIDEO_STREAM_TAG = 25;
  private static final int WIFI_TAG = 26;
  private static final int ZIMMU_3000_TAG = 27;
  private void parseOption(int tag, ByteBuffer optionData)
  {
    Log.v("parseOption:", "tag==" + tag);
    switch (tag) {
    case -1:
      parseCksOption(optionData);
      break;
    case 0: 
      parseDemoOption(optionData);
      break;
    case 1: 
      parseTimeOption(optionData);
      break;
    case 2: 
      parseRawMeasuresOption(optionData);
      break;
    case 3: 
      parsePhysMeasuresOption(optionData);
      break;
    case 4: 
      parseGyrosOffsetsOption(optionData);
      break;
    case 5: 
      parseEulerAnglesOption(optionData);
      break;
    case 6: 
      parseReferencesOption(optionData);
      break;
    case 7: 
      parseTrimsOption(optionData);
      break;
    case 8: 
      parseRcReferencesOption(optionData);
      break;
    case 9: 
      parsePWMOption(optionData);
      break;
    case 10: 
      parseAltitudeOption(optionData);
      break;
    case 11: 
      parseVisionRawOption(optionData);
      break;
    case 12: 
      parseVisionOfOption(optionData);
      break;
    case 13: 
      parseVisionOption(optionData);
      break;
    case 14: 
      parseVisionPerfOption(optionData);
      break;
    case 15: 
      parseTrackersSendOption(optionData);
      break;
    case 16: 
      parseVisionDetectOption(optionData);
      break;
    case 17: 
      parseWatchdogOption(optionData);
      break;
    case 18: 
      parseAdcDataFrameOption(optionData);
      break;
    case 19: 
      parseVideoStreamOption(optionData);
      break;
    case 20: 
      parseGamesOption(optionData);
      break;
    case 21: 
      parsePressureOption(optionData);
      break;
    case 22: 
      parseMagnetoDataOption(optionData);
      break;
    case 23: 
      parseWindOption(optionData);
      break;
    case 24: 
      parseKalmanPressureOption(optionData);
      break;
    case 25: 
      parseHDVideoSteamOption(optionData);
      break;
    case 26:
      parseWifiOption(optionData);
      break;
    case 27:
      parseGPSOption(optionData);
      //parseZimmu3000Option(optionData);
      break;
    }
    
  }
  
  private void parseCksOption(ByteBuffer b)
  {
    this.checksum = b.getInt();
  }
  
  private void parseZimmu3000Option(ByteBuffer b) {
    if (this.zimmu3000Listener.size() > 0)
    {
      int vzimmuLSB = b.getInt();
      float vzfind = b.getFloat();
      
      for (int i = 0; i < this.zimmu3000Listener.size(); i++) {
        ((Zimmu3000Listener)this.zimmu3000Listener.get(i)).received(vzimmuLSB, vzfind);
      }
    }
  }

  private void parseGPSOption(ByteBuffer b){
    if (this.gpsListener.size() > 0){
      double latitude = b.getDouble();
      double longitude = b.getDouble();
      double elevation = b.getDouble();
      double hdop = b.getDouble();
      long dataAvailable = getUInt32(b);
      boolean zeroValidated = getBoolean(b);
      boolean wptValidated = getBoolean(b);
      double latitudeZero = b.getDouble();
      double longitudeZero = b.getDouble();
      double latitudeFused = b.getDouble();
      double longitudeFuzed = b.getDouble();
      long gpsState = getUInt32(b);
      float xTrajectory = b.getFloat();
      float yTrajectory = b.getFloat();
      float xReference = b.getFloat();
      float yReference = b.getFloat();

      for (int i = 0; i < this.gpsListener.size(); i++) {
        ((GPSListener)this.gpsListener.get(i)).received(
          latitude,
          longitude,
          elevation,
          hdop,
          dataAvailable,
          zeroValidated,
          wptValidated,
          latitudeZero,
          longitudeZero,
          latitudeFused,
          longitudeFuzed,
          gpsState,
          xTrajectory,
          yTrajectory,
          xReference,
          yReference
        );
      }
    }
  }

  private void parseWifiOption(ByteBuffer b)
  {
    if (this.wifiListener.size() > 0) {
      long link_quality = getUInt32(b);
      
      for (int i = 0; i < this.wifiListener.size(); i++) {
        ((WifiListener)this.wifiListener.get(i)).received(link_quality);
      }
    }
  }
  
  private void parseHDVideoSteamOption(ByteBuffer b) {
    if (this.videoListener.size() > 0)
    {
      HDVideoState hdvideo_state = HDVideoState.fromInt(b.getInt());
      

      int storage_fifo_nb_packets = b.getInt();
      

      int storage_fifo_size = b.getInt();
      


      int usbkey_size = b.getInt();
      


      int usbkey_freespace = b.getInt();
      



      int frame_number = b.getInt();
      

      int usbkey_remaining_time = b.getInt();
      
      HDVideoStreamData d = new HDVideoStreamData(hdvideo_state, storage_fifo_nb_packets, storage_fifo_size, 
        usbkey_size, usbkey_freespace, frame_number, usbkey_remaining_time);
      
      for (int i = 0; i < this.videoListener.size(); i++)
        ((VideoListener)this.videoListener.get(i)).receivedHDVideoStreamData(d);
    }
  }
  
  private void parseKalmanPressureOption(ByteBuffer b) {
    if (this.pressureListener.size() > 0)
    {
      float offset_pressure = b.getFloat();
      float est_z = b.getFloat();
      float est_zdot = b.getFloat();
      float est_bias_PWM = b.getFloat();
      float est_biais_pression = b.getFloat();
      float offset_US = b.getFloat();
      float prediction_US = b.getFloat();
      float cov_alt = b.getFloat();
      float cov_PWM = b.getFloat();
      float cov_vitesse = b.getFloat();
      boolean effet_sol = getBoolean(b);
      float somme_inno = b.getFloat();
      boolean rejet_US = getBoolean(b);
      float u_multisinus = b.getFloat();
      float gaz_altitude = b.getFloat();
      boolean multisinus = getBoolean(b);
      boolean multisinus_debut = getBoolean(b);
      
      KalmanPressureData d = new KalmanPressureData(offset_pressure, est_z, est_zdot, est_bias_PWM, 
        est_biais_pression, offset_US, prediction_US, cov_alt, cov_PWM, cov_vitesse, effet_sol, somme_inno, 
        rejet_US, u_multisinus, gaz_altitude, multisinus, multisinus_debut);
      
      for (int i = 0; i < this.pressureListener.size(); i++)
        ((PressureListener)this.pressureListener.get(i)).receivedKalmanPressure(d);
    }
  }
  
  private void parseWindOption(ByteBuffer b) {
    if ((this.attitudeListener.size() > 0) || (this.windListener.size() > 0))
    {

      float wind_speed = b.getFloat();
      


      float wind_angle = b.getFloat();
      
      float wind_compensation_theta = b.getFloat();
      float wind_compensation_phi = b.getFloat();
      
      float[] state = getFloat(b, 6);
      
      float[] magneto = getFloat(b, 3);
      
      for (int i = 0; i < this.attitudeListener.size(); i++) {
        ((AttitudeListener)this.attitudeListener.get(i)).windCompensation(wind_compensation_theta, wind_compensation_phi);
      }
      for (int i = 0; i < this.windListener.size(); i++)
      {
        WindEstimationData d = new WindEstimationData(wind_speed, wind_angle, state, magneto);
        ((WindListener)this.windListener.get(i)).receivedEstimation(d);
      }
    }
  }
  
  private void parsePressureOption(ByteBuffer b)
  {
    if ((this.pressureListener.size() > 0) || (this.temperatureListener.size() > 0))
    {
      int up = b.getInt();
      short ut = b.getShort();
      int temperature_meas = b.getInt();
      int pression_meas = b.getInt();
      
      for (int i = 0; i < this.pressureListener.size(); i++)
      {
        Pressure d = new Pressure(up, pression_meas);
        ((PressureListener)this.pressureListener.get(i)).receivedPressure(d);
      }
      
      for (int i = 0; i < this.temperatureListener.size(); i++)
      {
        Temperature d = new Temperature(ut, temperature_meas);
        ((TemperatureListener)this.temperatureListener.get(i)).receivedTemperature(d);
      }
    }
  }
  
  private void parseGamesOption(ByteBuffer b) {
    if (this.counterListener.size() > 0)
    {
      long double_tap_counter = getUInt32(b);
      long finish_line_counter = getUInt32(b);
      
      Counters d = new Counters(double_tap_counter, finish_line_counter);
      for (int i = 0; i < this.counterListener.size(); i++) {
        ((CounterListener)this.counterListener.get(i)).update(d);
      }
    }
  }
  
  private void parseVideoStreamOption(ByteBuffer b) {
    if (this.videoListener.size() > 0)
    {


      byte quant = b.get();
      


      int frame_size = b.getInt();
      


      int frame_number = b.getInt();
      


      int atcmd_ref_seq = b.getInt();
      


      int atcmd_mean_ref_gap = b.getInt();
      
      float atcmd_var_ref_gap = b.getInt();
      


      int atcmd_ref_quality = b.getInt();
      




      int out_bitrate = b.getInt();
      


      int desired_bitrate = b.getInt();
      

      int[] temp_data = getInt(b, 5);
      


      int tcp_queue_level = b.getInt();
      
      int fifo_queue_level = b.getInt();
      
      VideoStreamData d = new VideoStreamData(quant, frame_size, frame_number, atcmd_ref_seq, atcmd_mean_ref_gap, 
        atcmd_var_ref_gap, atcmd_ref_quality, out_bitrate, desired_bitrate, temp_data, tcp_queue_level, 
        fifo_queue_level);
      
      for (int i = 0; i < this.videoListener.size(); i++)
        ((VideoListener)this.videoListener.get(i)).receivedVideoStreamData(d);
    }
  }
  
  private void parseAdcDataFrameOption(ByteBuffer b) {
    if (this.adcListener.size() > 0)
    {
      int version = b.getInt();
      

      byte[] data_frame = new byte[32];
      b.get(data_frame);
      
      AdcFrame d = new AdcFrame(version, data_frame);
      for (int i = 0; i < this.adcListener.size(); i++)
        ((AdcListener)this.adcListener.get(i)).receivedFrame(d);
    }
  }
  
  private void parseWatchdogOption(ByteBuffer b) {
    if (this.watchdogListener.size() > 0) {
      int watchdog = b.getInt();
      
      for (int i = 0; i < this.watchdogListener.size(); i++)
        ((WatchdogListener)this.watchdogListener.get(i)).received(watchdog);
    }
  }
  
  private void parseTrackersSendOption(ByteBuffer b) {
    if (this.visionListener.size() > 0)
    {




      int[][][] trackers = new int[6][5][3];
      for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 5; j++) {
          trackers[i][j][0] = b.getInt();
        }
      }
      
      for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 5; j++) {
          trackers[i][j][1] = b.getInt();
          trackers[i][j][2] = b.getInt();
        }
      }
      

      for (int i = 0; i < this.visionListener.size(); i++)
        ((VisionListener)this.visionListener.get(i)).trackersSend(new TrackerData(trackers));
    }
  }
  
  private void parseVisionPerfOption(ByteBuffer b) {
    if (this.visionListener.size() > 0) {
      float time_szo = b.getFloat();
      float time_corners = b.getFloat();
      float time_compute = b.getFloat();
      float time_tracking = b.getFloat();
      float time_trans = b.getFloat();
      float time_update = b.getFloat();
      float[] time_custom = getFloat(b, 20);
      
      VisionPerformance d = new VisionPerformance(time_szo, time_corners, time_compute, time_tracking, 
        time_trans, time_update, time_custom);
      
      for (int i = 0; i < this.visionListener.size(); i++)
        ((VisionListener)this.visionListener.get(i)).receivedPerformanceData(d);
    }
  }
  
  private void parseVisionOption(ByteBuffer b) {
    if (this.visionListener.size() > 0) {
      int vision_state = b.getInt();
      int vision_misc = b.getInt();
      float vision_phi_trim = b.getFloat();
      float vision_phi_ref_prop = b.getFloat();
      float vision_theta_trim = b.getFloat();
      float vision_theta_ref_prop = b.getFloat();
      int new_raw_picture = b.getInt();
      float theta_capture = b.getFloat();
      float phi_capture = b.getFloat();
      float psi_capture = b.getFloat();
      int altitude_capture = b.getInt();
      
      int time_capture = b.getInt();
      int time_capture_seconds = getSeconds(time_capture);
      int time_capture_useconds = getUSeconds(time_capture);
      float[] body_v = getFloat(b, 3);
      float delta_phi = b.getFloat();
      float delta_theta = b.getFloat();
      float delta_psi = b.getFloat();
      int gold_defined = b.getInt();
      int gold_reset = b.getInt();
      float gold_x = b.getFloat();
      float gold_y = b.getFloat();
      
      VisionData d = new VisionData(vision_state, vision_misc, vision_phi_trim, vision_phi_ref_prop, 
        vision_theta_trim, vision_theta_ref_prop, new_raw_picture, theta_capture, phi_capture, psi_capture, 
        altitude_capture, time_capture_seconds, time_capture_useconds, body_v, delta_phi, delta_theta, 
        delta_psi, gold_defined, gold_reset, gold_x, gold_y);
      
      for (int i = 0; i < this.visionListener.size(); i++) {
        ((VisionListener)this.visionListener.get(i)).receivedData(d);
      }
    }
  }
  
  private void parseVisionOfOption(ByteBuffer b) {
    if (this.visionListener.size() > 0) {
      float[] of_dx = getFloat(b, 5);
      float[] of_dy = getFloat(b, 5);
      
      for (int i = 0; i < this.visionListener.size(); i++) {
        ((VisionListener)this.visionListener.get(i)).receivedVisionOf(of_dx, of_dy);
      }
    }
  }
  
  private void parseVisionRawOption(ByteBuffer b) {
    if (this.visionListener.size() > 0) {
      float[] vision_raw = getFloat(b, 3);
      
      for (int i = 0; i < this.visionListener.size(); i++)
        ((VisionListener)this.visionListener.get(i)).receivedRawData(vision_raw);
    }
  }
  
  private void parseAltitudeOption(ByteBuffer b) {
    if (this.altitudeListener.size() > 0) {
      int altitude_vision = b.getInt();
      float altitude_vz = b.getFloat();
      int altitude_ref = b.getInt();
      int altitude_raw = b.getInt();
      

      float obs_accZ = b.getFloat();
      float obs_alt = b.getFloat();
      
      float[] obs_x = getFloat(b, 3);
      
      int obs_state = b.getInt();
      

      float[] est_vb = getFloat(b, 2);
      
      int est_state = b.getInt();
      
      Altitude d = new Altitude(altitude_vision, altitude_vz, altitude_ref, altitude_raw, obs_accZ, obs_alt, 
        obs_x, obs_state, est_vb, est_state);
      
      for (int i = 0; i < this.altitudeListener.size(); i++)
        ((AltitudeListener)this.altitudeListener.get(i)).receivedExtendedAltitude(d);
    }
  }
  
  private void parsePWMOption(ByteBuffer b) {
    if (this.pwmlistener.size() > 0) {
      short[] motor = getUInt8(b, 4);
      short[] sat_motor = getUInt8(b, 4);
      float gaz_feed_forward = b.getFloat();
      float gaz_altitude = b.getFloat();
      float altitude_integral = b.getFloat();
      float vz_ref = b.getFloat();
      
      int[] u_pry = getInt(b, 3);
      float yaw_u_I = b.getFloat();
      
      int[] u_planif_pry = getInt(b, 3);
      float u_gaz_planif = b.getFloat();
      int[] current_motor = getUInt16(b, 4);
      
      float altitude_prop = b.getFloat();
      float altitude_der = b.getFloat();
      
      PWMData d = new PWMData(motor, sat_motor, gaz_feed_forward, gaz_altitude, altitude_integral, vz_ref, u_pry, 
        yaw_u_I, u_planif_pry, u_gaz_planif, current_motor, altitude_prop, altitude_der);
      
      for (int i = 0; i < this.pwmlistener.size(); i++)
        ((PWMlistener)this.pwmlistener.get(i)).received(d);
    }
  }
  
  private void parseRcReferencesOption(ByteBuffer b) {
    if (this.referencesListener.size() > 0) {
      int[] rc_ref = getInt(b, 5);
      for (int i = 0; i < this.referencesListener.size(); i++)
        ((ReferencesListener)this.referencesListener.get(i)).receivedRcReferences(rc_ref);
    }
  }
  
  private void parseTrimsOption(ByteBuffer b) {
    if (this.trimsListener.size() > 0) {
      float angular_rates_trim_r = b.getFloat();
      float euler_angles_trim_theta = b.getFloat();
      float euler_angles_trim_phi = b.getFloat();
      
      for (int i = 0; i < this.trimsListener.size(); i++)
        ((TrimsListener)this.trimsListener.get(i)).receivedTrimData(angular_rates_trim_r, euler_angles_trim_theta, euler_angles_trim_phi);
    }
  }
  
  private void parseReferencesOption(ByteBuffer b) {
    if (this.referencesListener.size() > 0) {
      int ref_theta = b.getInt();
      int ref_phi = b.getInt();
      int ref_theta_I = b.getInt();
      int ref_phi_I = b.getInt();
      int ref_pitch = b.getInt();
      int ref_roll = b.getInt();
      int ref_yaw = b.getInt();
      int ref_psi = b.getInt();
      
      float[] v_ref = getFloat(b, 2);
      float theta_mod = b.getFloat();
      float phi_mod = b.getFloat();
      float[] k_v = getFloat(b, 2);
      
      int k_mode = b.getInt();
      
      float ui_time = b.getFloat();
      float ui_theta = b.getFloat();
      float ui_phi = b.getFloat();
      float ui_psi = b.getFloat();
      float ui_psi_accuracy = b.getFloat();
      int ui_seq = b.getInt();
      
      ReferencesData d = new ReferencesData(ref_theta, ref_phi, ref_theta_I, ref_phi_I, ref_pitch, ref_roll, 
        ref_yaw, ref_psi, v_ref, theta_mod, phi_mod, k_v, k_mode, ui_time, ui_theta, ui_phi, ui_psi, 
        ui_psi_accuracy, ui_seq);
      
      for (int i = 0; i < this.referencesListener.size(); i++) {
        ((ReferencesListener)this.referencesListener.get(i)).receivedReferences(d);
      }
    }
  }
  
  private void parseRawMeasuresOption(ByteBuffer b) {
    if ((this.batteryListener.size() > 0) || (this.acceleroListener.size() > 0) || (this.gyroListener.size() > 0) || (this.ultrasoundListener.size() > 0))
    {

      int[] raw_accs = getUInt16(b, 3);
      


      short[] raw_gyros = getShort(b, 3);
      


      short[] raw_gyros_110 = getShort(b, 2);
      


      int vbat_raw = b.getInt();
      


      int us_echo_start = getUInt16(b);
      


      int us_echo_end = getUInt16(b);
      



      int us_association_echo = getUInt16(b);
      


      int us_distance_echo = getUInt16(b);
      



      int us_cycle_time = getUInt16(b);
      



      int us_cycle_value = getUInt16(b);
      


      int us_cycle_ref = getUInt16(b);
      int flag_echo_ini = getUInt16(b);
      int nb_echo = getUInt16(b);
      long sum_echo = getUInt32(b);
      int alt_temp_raw = b.getInt();
      short gradient = b.getShort();
      
      for (int i = 0; i < this.batteryListener.size(); i++) {
        ((BatteryListener)this.batteryListener.get(i)).voltageChanged(vbat_raw);
      }
      for (int i = 0; i < this.acceleroListener.size(); i++) {
        AcceleroRawData d = new AcceleroRawData(raw_accs);
        ((AcceleroListener)this.acceleroListener.get(i)).receivedRawData(d);
      }
      
      for (int i = 0; i < this.gyroListener.size(); i++) {
        GyroRawData d = new GyroRawData(raw_gyros, raw_gyros_110);
        ((GyroListener)this.gyroListener.get(i)).receivedRawData(d);
      }
      
      if (this.ultrasoundListener.size() > 0) {
        UltrasoundData d = new UltrasoundData(us_echo_start, us_echo_end, us_association_echo, 
          us_distance_echo, us_cycle_time, us_cycle_value, us_cycle_ref, flag_echo_ini, nb_echo, 
          sum_echo, alt_temp_raw, gradient);
        
        for (int i = 0; i < this.ultrasoundListener.size(); i++)
          ((UltrasoundListener)this.ultrasoundListener.get(i)).receivedRawData(d);
      }
    }
  }
  
  private void parsePhysMeasuresOption(ByteBuffer b) {
    if ((this.acceleroListener.size() > 0) || (this.gyroListener.size() > 0)) {
      float accs_temp = b.getFloat();
      int gyro_temp = getUInt16(b);
      
      float[] phys_accs = getFloat(b, 3);
      
      float[] phys_gyros = getFloat(b, 3);
      



      int alim3V3 = b.getInt() & 0xFF;
      


      int vrefEpson = b.getInt() & 0xFF;
      


      int vrefIDG = b.getInt() & 0xFF;
      
      for (int i = 0; i < this.acceleroListener.size(); i++) {
        AcceleroPhysData d = new AcceleroPhysData(accs_temp, phys_accs, alim3V3);
        ((AcceleroListener)this.acceleroListener.get(i)).receivedPhysData(d);
      }
      
      for (int i = 0; i < this.gyroListener.size(); i++) {
        GyroPhysData d = new GyroPhysData(gyro_temp, phys_gyros, alim3V3, vrefEpson, vrefIDG);
        ((GyroListener)this.gyroListener.get(i)).receivedPhysData(d);
      }
    }
  }
  
  private void parseGyrosOffsetsOption(ByteBuffer b)
  {
    if (this.gyroListener.size() > 0) {
      float[] offset_g = getFloat(b, 3);
      
      for (int i = 0; i < this.gyroListener.size(); i++)
        ((GyroListener)this.gyroListener.get(i)).receivedOffsets(offset_g);
    }
  }
  
  private void parseEulerAnglesOption(ByteBuffer b) {
    if (this.attitudeListener.size() > 0) {
      float theta_a = b.getFloat();
      float phi_a = b.getFloat();
      
      for (int i = 0; i < this.attitudeListener.size(); i++) {
        ((AttitudeListener)this.attitudeListener.get(i)).attitudeUpdated(theta_a, phi_a);
      }
    }
  }
  
  private void parseDemoOption(ByteBuffer b) {
    if ((this.stateListener.size() > 0) || (this.batteryListener.size() > 0) || (this.attitudeListener.size() > 0) || (this.altitudeListener.size() > 0) || 
      (this.velocityListener.size() > 0) || (this.visionListener.size() > 0)) {
      int controlState = b.getInt();
      

      int batteryPercentage = b.getInt();
      
      float theta = b.getFloat();
      float phi = b.getFloat();
      float psi = b.getFloat();
      
      int altitude = b.getInt();
      
      float[] v = getFloat(b, 3);
      

      long num_frames = getUInt32(b);
      

      float[] detection_camera_rot = getFloat(b, 9);
      

      float[] detection_camera_trans = getFloat(b, 3);
      

      long detection_tag_index = getUInt32(b);
      
      int detection_camera_type = b.getInt();
      


      float[] drone_camera_rot = getFloat(b, 9);
      

      float[] drone_camera_trans = getFloat(b, 3);
      
      if ((this.visionListener.size() > 0) && (detection_camera_type != 0)) {
        for (int i = 0; i < this.visionListener.size(); i++) {
          ((VisionListener)this.visionListener.get(i)).typeDetected(detection_camera_type);
        }
      }
      for (int i = 0; i < this.stateListener.size(); i++) {
        ((StateListener)this.stateListener.get(i)).controlStateChanged(ControlState.fromInt(controlState >> 16));
      }
      for (int i = 0; i < this.batteryListener.size(); i++) {
        ((BatteryListener)this.batteryListener.get(i)).batteryLevelChanged(batteryPercentage);
      }
      for (int i = 0; i < this.attitudeListener.size(); i++) {
        ((AttitudeListener)this.attitudeListener.get(i)).attitudeUpdated(theta, phi, psi);
      }
      for (int i = 0; i < this.altitudeListener.size(); i++) {
        ((AltitudeListener)this.altitudeListener.get(i)).receivedAltitude(altitude);
      }
      for (int i = 0; i < this.velocityListener.size(); i++)
        ((VelocityListener)this.velocityListener.get(i)).velocityChanged(v[0], v[1], v[2]);
    }
  }
  
  private void parseTimeOption(ByteBuffer b) {
    if (this.timeListener.size() > 0) {
      int time = b.getInt();
      
      int useconds = getUSeconds(time);
      int seconds = getSeconds(time);
      
      for (int i = 0; i < this.timeListener.size(); i++) {
        ((TimeListener)this.timeListener.get(i)).timeReceived(seconds, useconds);
      }
    }
  }
  


  private int getSeconds(int time)
  {
    int seconds = time >>> 11;
    return seconds;
  }
  



  private int getUSeconds(int time)
  {
    int useconds = time & 0x1FFFFF;
    return useconds;
  }
  
  private void parseVisionDetectOption(ByteBuffer b) {
    if (this.visionListener.size() > 0) {
      int ndetected = b.getInt();
      
      if (ndetected > 0)
      {
        int[] type = getInt(b, 4);
        

        int[] xc = getInt(b, 4);
        

        int[] yc = getInt(b, 4);
        

        int[] width = getInt(b, 4);
        

        int[] height = getInt(b, 4);
        

        int[] dist = getInt(b, 4);
        
        float[] orientation_angle = getFloat(b, 4);
        

        float[][][] rotation = new float[4][3][3];
        for (int i = 0; i < 4; i++) {
          for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
              rotation[i][r][c] = b.getFloat();
            }
          }
        }
        

        float[][] translation = new float[4][3];
        for (int i = 0; i < 4; i++) {
          for (int r = 0; r < 3; r++) {
            translation[i][r] = b.getFloat();
          }
        }
        



        int[] camera_source = getInt(b, 4);
        
        VisionTag[] tags = new VisionTag[ndetected];
        for (int i = 0; i < ndetected; i++)
        {
          VisionTag tag = new VisionTag(type[i], xc[i], yc[i], width[i], height[i], dist[i], 
            orientation_angle[i], rotation[i], translation[i], DetectionType.fromInt(camera_source[i]));
          tags[i] = tag;
        }
        
        for (int i = 0; i < this.visionListener.size(); i++)
          ((VisionListener)this.visionListener.get(i)).tagsDetected(tags);
      }
    }
  }
  
  private void parseMagnetoDataOption(ByteBuffer b) {
    if (this.magnetoListener.size() > 0) {
      short[] m = getShort(b, 3);
      
      float[] mraw = getFloat(b, 3);
      
      float[] mrectified = getFloat(b, 3);
      
      float[] m_ = getFloat(b, 3);
      
      float heading_unwrapped = b.getFloat();
      float heading_gyro_unwrapped = b.getFloat();
      float heading_fusion_unwrapped = b.getFloat();
      byte calibration_ok = b.get();
      int state = b.getInt();
      float radius = b.getFloat();
      float error_mean = b.getFloat();
      float error_var = b.getFloat();
      
      MagnetoData md = new MagnetoData(m, mraw, mrectified, m_, heading_unwrapped, heading_gyro_unwrapped, 
        heading_fusion_unwrapped, calibration_ok, state, radius, error_mean, error_var);
      
      for (int i = 0; i < this.magnetoListener.size(); i++) {
        ((MagnetoListener)this.magnetoListener.get(i)).received(md);
      }
    }
  }
  



  private float[] getFloat(ByteBuffer b, int n)
  {
    float[] f = new float[n];
    for (int k = 0; k < f.length; k++) {
      f[k] = b.getFloat();
    }
    return f;
  }
  




  private int[] getInt(ByteBuffer b, int n)
  {
    int[] i = new int[n];
    for (int k = 0; k < i.length; k++) {
      i[k] = b.getInt();
    }
    return i;
  }
  




  private short[] getShort(ByteBuffer b, int n)
  {
    short[] s = new short[n];
    for (int k = 0; k < s.length; k++) {
      s[k] = b.getShort();
    }
    return s;
  }
  
  private int[] getUInt16(ByteBuffer b, int n) {
    int[] i = new int[n];
    for (int k = 0; k < i.length; k++) {
      i[k] = getUInt16(b);
    }
    return i;
  }
  
  private short[] getUInt8(ByteBuffer b, int n) {
    short[] s = new short[n];
    for (int k = 0; k < s.length; k++) {
      s[k] = getUInt8(b);
    }
    return s;
  }
  
  private boolean getBoolean(ByteBuffer b) {
    return b.getInt() == 1;
  }
  


  private short getUInt8(ByteBuffer b)
  {
    return (short)(b.get() & 0xFF);
  }
  


  private int getUInt16(ByteBuffer b)
  {
    return b.getShort() & 0xFFFF;
  }
  


  private long getUInt32(ByteBuffer b)
  {
    return b.getInt() & 0xFFFFFFFF;
  }
  
  private void checkEqual(int expected, int actual, String message) throws NavDataException {
    if (expected != actual) {
      throw new NavDataException(message + " : expected " + expected + ", was " + actual);
    }
  }
  
  private int getCRC(byte[] b, int offset, int length) {
    CRC32 cks = new CRC32();
    cks.update(b, offset, length);
    return (int)(cks.getValue() & 0xFFFFFFFF);
  }
  
  private int getCRC(ByteBuffer b, int offset, int length) {
    return getCRC(b.array(), b.arrayOffset() + offset, length);
  }
}
