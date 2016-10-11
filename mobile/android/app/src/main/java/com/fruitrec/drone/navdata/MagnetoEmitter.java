package com.fruitrec.drone.navdata;

import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;

import de.yadrone.base.navdata.MagnetoData;
import de.yadrone.base.navdata.MagnetoListener;
import de.yadrone.base.navdata.NavDataManager;


public class MagnetoEmitter extends ReactEventEmitter implements MagnetoListener {

    public static int UPDATE_MAGNETO = 0;

    public MagnetoEmitter(ReactApplicationContext context, NavDataManager manager) {
        super(context, manager);
        initializeEventFlags(1);
    }

    @Override
    public void removeListener() {
        getNavDataManager().removeMagnetoListener(this);
    }


    @Override
    public void received(MagnetoData paramMagnetoData) {
        short[] mag = paramMagnetoData.getM();
        Log.v("received",
                    "magX: " + mag[0] +
                    ", magY: " + mag[1] +
                    ", magZ: " + mag[2]);
        if(!isReadyToEmit(UPDATE_MAGNETO)) return;
        turnOffEmit(UPDATE_MAGNETO);
        Log.v("received", "Interval passed, sending this event.");

        WritableMap params = Arguments.createMap();
        params.putInt("magX", mag[0]);
        params.putInt("magY", mag[1]);
        params.putInt("magZ", mag[2]);
        sendEvent("magneto", params);
    }
}
