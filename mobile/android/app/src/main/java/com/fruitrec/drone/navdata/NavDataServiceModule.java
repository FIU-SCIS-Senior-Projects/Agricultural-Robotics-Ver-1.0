package com.fruitrec.drone.navdata;

import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;

import java.util.ArrayList;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.navdata.NavDataManager;

public class NavDataServiceModule extends ReactContextBaseJavaModule {

    private NavDataManager navDataManager;
    private IARDrone drone;
    private ArrayList<ReactEventEmitter> registeredSubscriptions;

    public NavDataServiceModule(ReactApplicationContext reactContext) {
        super(reactContext);
        drone = new ARDrone("192.168.1.1", null);
        Log.v("Agrobo", "Initializing NavDataServiceModule");
    }

    @ReactMethod
    public void subscribeToEvent(String subscriptionName){
        NavDataManager ndManager = drone.getNavDataManager();
        ReactEventEmitter emitter = null;

        switch (subscriptionName){
            case "attitude":
                emitter = new AttitudeEmitter(getReactApplicationContext(), drone.getNavDataManager());
                ndManager.addAttitudeListener((AttitudeEmitter) emitter);
                break;
            //case "altitude":
                //emitter = new AltitudeEmitter(getReactApplicationContext(), drone.getNavDataManager());
                //ndManager.addAltitudeListener((AltitudeListener) emitter);
                //break;
        }

        if(emitter != null){
            registeredSubscriptions.add(emitter);
        }
    }

    @ReactMethod
    public void subscribeToEvents(ReadableArray subscriptions){
        for(int i = 0; i < subscriptions.size(); i++){
            subscribeToEvent(subscriptions.getString(i));
        }
    }


    @ReactMethod
    public void connect(){
        try
        {
            drone.start();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();

            if (drone != null)
                drone.stop();
        }
    }

    @ReactMethod
    public void disconnect(){
        for(ReactEventEmitter emitter: registeredSubscriptions){
            emitter.removeListener();
        }

        registeredSubscriptions.clear();
    }

    @Override
    public String getName() {
        return "NavDataServiceModule";
    }

    public NavDataManager getNavDataManager(){
        return navDataManager;
    }
}
