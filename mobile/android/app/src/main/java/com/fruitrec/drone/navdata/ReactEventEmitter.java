package com.fruitrec.drone.navdata;


import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import com.fruitrec.navdata.NavDataManager;

public abstract class ReactEventEmitter {

    private static int EMIT_INTERVAL = 400;

    private ReactApplicationContext reactContext;
    private ArrayList<Boolean> emitFlags;
    private NavDataManager ndManager;

    public ReactEventEmitter(ReactApplicationContext context, NavDataManager manager){
        reactContext = context;
        ndManager = manager;
        initializeEventFlags(1);
        createEmitInterval(EMIT_INTERVAL);
    }

    public ReactEventEmitter(ReactApplicationContext context, NavDataManager manager, int emitIntervalMilliseconds){
        this(context, manager);
        createEmitInterval(emitIntervalMilliseconds);
    }

    private void createEmitInterval(int interval){
        new Timer().scheduleAtFixedRate(new TimerTask() {
            public void run() {
                for(int i = 0; i < emitFlags.size(); i++){
                    emitFlags.set(i, true);
                }
            }
        }, interval, interval);
    }

    protected boolean isReadyToEmit(int emitFlagIndex){
        return emitFlags.get(emitFlagIndex);
    }

    protected void turnOffEmit(int emitFlagIndex){
        emitFlags.set(emitFlagIndex, false);
    }

    protected void sendEvent(String eventName, WritableMap params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

    protected void initializeEventFlags(int numEvents){
        emitFlags = new ArrayList<Boolean>(Arrays.<Boolean>asList(new Boolean[numEvents]));
        for(int i = 0; i < emitFlags.size(); i++){
            emitFlags.set(i, false);
        }
    }

    public NavDataManager getNavDataManager(){
        return ndManager;
    }

    public abstract void removeListener();

}
