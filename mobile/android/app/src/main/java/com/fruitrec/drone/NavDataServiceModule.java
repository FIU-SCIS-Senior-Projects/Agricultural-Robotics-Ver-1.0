package com.fruitrec.drone;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.facebook.react.bridge.ReactMethod;
import com.fruitrec.MainApplication;
import com.facebook.react.*;
import com.facebook.react.BuildConfig;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.Timer;
import java.util.TimerTask;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.navdata.AttitudeListener;
import de.yadrone.base.navdata.NavDataManager;

public class NavDataServiceModule extends ReactContextBaseJavaModule implements AttitudeListener {

    private static int emitDelay = 1000;
    private boolean shouldEmit;
    private NavDataManager navDataManager;
    private IARDrone drone;

    // Refer to mReactApplicationContext



    public NavDataServiceModule(ReactApplicationContext reactContext) {
        super(reactContext);
        drone = new ARDrone("192.168.1.1", null);
        shouldEmit = false;

        Log.v("Agrobo", "Initializing NavDataServiceModule");


        new Timer().scheduleAtFixedRate(new TimerTask()
        {
            public void run()
            {
                shouldEmit = true;
            }
        }, emitDelay, emitDelay);
    }

    @ReactMethod
    public void connect(){
        try
        {
            drone.start();
            drone.getNavDataManager().addAttitudeListener(this);
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
        drone.getNavDataManager().removeAttitudeListener(this);
    }

    @Override
    public String getName() {
        return "NavDataServiceModule";
    }

    public NavDataManager getNavDataManager(){
        return navDataManager;
    }

    public void attitudeUpdated(final float pitch, final float roll, final float yaw)
    {
        if(!shouldEmit) return;
        shouldEmit = false;

        WritableMap params = Arguments.createMap();

        params.putDouble("pitch", pitch);
        params.putDouble("roll", roll);
        params.putDouble("yaw", yaw);

        Log.v("Agrobo", String.format("attitudeUpdated: pitch:%d, roll:%d, yaw:%d", (long)pitch, (long)roll, (long)yaw));

        sendEvent(getReactApplicationContext(), "attitudeUpdated", params);
    }


    private void sendEvent(ReactContext reactContext,
                           String eventName,
                           WritableMap params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }



    @Override
    public void attitudeUpdated(float v, float v1) {

    }

    @Override
    public void windCompensation(float v, float v1) {

    }
}
