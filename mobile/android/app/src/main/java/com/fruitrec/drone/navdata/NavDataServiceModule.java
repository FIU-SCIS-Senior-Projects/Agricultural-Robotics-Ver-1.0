package com.fruitrec.drone.navdata;

import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;

import java.util.ArrayList;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.navdata.NavDataManager;

public class NavDataServiceModule extends ReactContextBaseJavaModule {

    private IARDrone drone;
    private ArrayList<ReactEventEmitter> registeredSubscriptions;

    public NavDataServiceModule(ReactApplicationContext reactContext) {
        super(reactContext);
        drone = new ARDrone("192.168.1.1", null);
        registeredSubscriptions = new ArrayList<>();
        Log.v("Agrobo", "Initializing NavDataServiceModule");
    }

    private void subscribeToEvent(String subscriptionName) throws InvalidEventSubscrtipion {
        NavDataManager ndManager = drone.getNavDataManager();
        ReactEventEmitter emitter = null;

        if(subscriptionName.equals("attitude")){
            emitter = new AttitudeEmitter(getReactApplicationContext(), drone.getNavDataManager());
            ndManager.addAttitudeListener((AttitudeEmitter) emitter);
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
