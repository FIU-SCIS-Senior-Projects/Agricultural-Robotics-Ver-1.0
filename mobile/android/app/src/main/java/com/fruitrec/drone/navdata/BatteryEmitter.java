package com.fruitrec.drone.navdata;

import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;

import com.fruitrec.navdata.BatteryListener;
import com.fruitrec.navdata.NavDataManager;

public class BatteryEmitter extends ReactEventEmitter implements BatteryListener {

    public static int UPDATE_BATTERY_LEVEL = 0;
    public static int UPDATE_BATTERY_VOLTAGE = 1;

    public BatteryEmitter(ReactApplicationContext context, NavDataManager manager) {
        super(context, manager);
        initializeEventFlags(2);
    }

    @Override
    public void batteryLevelChanged(int i) {
        Log.v("batteryLevelChanged", "batteryLevel: " + i);
        if(!isReadyToEmit(UPDATE_BATTERY_LEVEL)) return;
        turnOffEmit(UPDATE_BATTERY_LEVEL);
        Log.v("batteryLevelChanged", "Interval passed, sending this event.");

        WritableMap params = Arguments.createMap();
        params.putInt("batteryLevel", i);
        sendEvent("battery", params);
    }

    @Override
    public void voltageChanged(int i) {
        Log.v("batteryVoltageChanged", "batteryVoltage: " + i);
        if(!isReadyToEmit(UPDATE_BATTERY_VOLTAGE)) return;
        turnOffEmit(UPDATE_BATTERY_VOLTAGE);
        Log.v("batteryVoltageChanged", "Interval passed, sending this event.");

        WritableMap params = Arguments.createMap();
        params.putInt("batteryVoltage", i);
        sendEvent("battery", params);
    }

    @Override
    public void removeListener() {
        getNavDataManager().removeBatteryListener(this);
    }

}
