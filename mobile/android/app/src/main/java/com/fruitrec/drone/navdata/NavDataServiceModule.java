package com.fruitrec.drone.navdata;

import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.navdata.AltitudeListener;
import de.yadrone.base.navdata.GPSListener;
import de.yadrone.base.navdata.GyroListener;
import de.yadrone.base.navdata.NavDataManager;

public class NavDataServiceModule extends ReactContextBaseJavaModule {

    private static String DEFAULT_INET_ADDRESS = "192.168.1.1";
    private static int MASK_ALL_OPTIONS = 777060865;

    private IARDrone drone;
    private ArrayList<ReactEventEmitter> registeredSubscriptions;

    public NavDataServiceModule(ReactApplicationContext reactContext) {
        super(reactContext);
        drone = new ARDrone(DEFAULT_INET_ADDRESS, null);
        registeredSubscriptions = new ArrayList<>();
        Log.v("Agrobo", "Initializing NavDataServiceModule");
    }

    private void createNavdataOptionsInterval() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            public void run() {
                drone.getCommandManager().setNavDataDemo(false);
                drone.getCommandManager().setNavDataOptions(MASK_ALL_OPTIONS);
            }
        }, 250, 250);
    }

    private void subscribeToEvent(String subscriptionName) throws InvalidEventSubscrtipion {
        NavDataManager ndManager = drone.getNavDataManager();
        ReactEventEmitter emitter = null;

        if(subscriptionName.equals("attitude")) {
            emitter = new AttitudeEmitter(getReactApplicationContext(), drone.getNavDataManager());
            ndManager.addAttitudeListener((AttitudeEmitter) emitter);
        } else if(subscriptionName.equals("altitude")){
            Log.v("subscribeToEvent:", "Subscribed to 'altitude' event");
            emitter = new AltitudeEmitter(getReactApplicationContext(), drone.getNavDataManager());
            ndManager.addAltitudeListener((AltitudeEmitter) emitter);
        } else if(subscriptionName.equals("battery")) {
            Log.v("subscribeToEvent:", "Subscribed to 'battery' event");
            emitter = new BatteryEmitter(getReactApplicationContext(), drone.getNavDataManager());
            ndManager.addBatteryListener((BatteryEmitter) emitter);
        } else if(subscriptionName.equals("flyState")) {
            Log.v("subscribeToEvent:", "Subscribed to 'flyState' event");
            emitter = new FlyStateEmitter(getReactApplicationContext(), drone.getNavDataManager());
            ndManager.addStateListener((FlyStateEmitter) emitter);
        } else if(subscriptionName.equals("gps")) {
            Log.v("subscribeToEvent:", "Subscribed to 'gps' event");
            emitter = new GPSEmitter(getReactApplicationContext(), drone.getNavDataManager());
            ndManager.addGPSListener((GPSEmitter) emitter);
        } else if(subscriptionName.equals("gyro")) {
            Log.v("subscribeToEvent:", "Subscribed to 'gyro' event");
            emitter = new GyroEmitter(getReactApplicationContext(), drone.getNavDataManager());
            ndManager.addGyroListener((GyroEmitter) emitter);
        } else if(subscriptionName.equals("accelero")) {
            Log.v("subscribeToEvent:", "Subscribed to 'accelero' event");
            emitter = new AcceleroEmitter(getReactApplicationContext(), drone.getNavDataManager());
            ndManager.addAcceleroListener((AcceleroEmitter) emitter);
        } else if(subscriptionName.equals("magneto")) {
            Log.v("subscribeToEvent:", "Subscribed to 'magneto' event");
            emitter = new MagnetoEmitter(getReactApplicationContext(), drone.getNavDataManager());
            ndManager.addMagnetoListener((MagnetoEmitter) emitter);
        } else {
            throw new InvalidEventSubscrtipion(subscriptionName);
        }

        if(emitter != null){ // Shouldn't happen but hey...
            registeredSubscriptions.add(emitter);
        }
    }

    @ReactMethod
    public void subscribe(String subscriptionName, Promise p) {
        try {
            subscribeToEvent(subscriptionName);
            p.resolve("Success");
        } catch(Exception e) {
            e.printStackTrace();
            p.reject(e);
        }

    }

    @ReactMethod
    public void subscribeAll(ReadableArray subscriptions, Promise p) {
        try {
            for(int i = 0; i < subscriptions.size(); i++){
                subscribeToEvent(subscriptions.getString(i));
            }

            drone.getCommandManager().setNavDataOptions(MASK_ALL_OPTIONS);
            p.resolve("Success");
        } catch (Exception e){
            e.printStackTrace();
            p.reject(e);
        }

    }


    @ReactMethod
    public void connect(Promise p){
        try
        {
            drone.start();
            //drone.getCommandManager().setNavDataDemo(false);
            //drone.getCommandManager().setNavDataOptions(MASK_ALL_OPTIONS);
            createNavdataOptionsInterval();
            p.resolve("Success");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            if (drone != null) drone.stop();
            p.reject(e);
        }
    }

    @ReactMethod
    public void disconnect(Promise p){
        try {
            for(ReactEventEmitter emitter: registeredSubscriptions){
                emitter.removeListener();
            }

            registeredSubscriptions.clear();
            drone.stop();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            p.reject(e);
        }
    }

    @Override
    public String getName() {
        return "NavDataServiceModule";
    }

    public NavDataManager getNavDataManager(){
        return drone.getNavDataManager();
    }

    private class InvalidEventSubscrtipion extends Exception {
        public InvalidEventSubscrtipion(String subscriptionName) {
            super("The subscription name provided does not exist: " + subscriptionName);
        }
    }
}
