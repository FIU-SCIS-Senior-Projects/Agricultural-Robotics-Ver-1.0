package com.fruitrec.drone.navdata;

import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;

import de.yadrone.base.navdata.AcceleroListener;
import de.yadrone.base.navdata.AcceleroPhysData;
import de.yadrone.base.navdata.AcceleroRawData;
import de.yadrone.base.navdata.NavDataManager;


public class AcceleroEmitter extends ReactEventEmitter implements AcceleroListener {

    public static int UPDATE_ACCELERO_PHYS = 0;

    public AcceleroEmitter(ReactApplicationContext context, NavDataManager manager) {
        super(context, manager);
        initializeEventFlags(1);
    }

    @Override
    public void removeListener() {
        getNavDataManager().removeAcceleroListener(this);
    }

    @Override
    public void receivedRawData(AcceleroRawData paramAcceleroRawData) {
        // We don't need this yet.
    }

    @Override
    public void receivedPhysData(AcceleroPhysData paramAcceleroPhysData) {
        float[] acc = paramAcceleroPhysData.getPhysAccs();
        Log.v("receivedPhysData",
                "accX: " + acc[0] +
                ", accY: " + acc[1] +
                ", accZ: " + acc[2]);
        if(!isReadyToEmit(UPDATE_ACCELERO_PHYS)) return;
        turnOffEmit(UPDATE_ACCELERO_PHYS);
        Log.v("receivedPhysData", "Interval passed, sending this event.");

        WritableMap params = Arguments.createMap();
        params.putDouble("accX", acc[0]);
        params.putDouble("accY", acc[1]);
        params.putDouble("accZ", acc[2]);
        sendEvent("accelero", params);
    }
}
