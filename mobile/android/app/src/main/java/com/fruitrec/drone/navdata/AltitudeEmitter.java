package com.fruitrec.drone.navdata;

import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;

import de.yadrone.base.navdata.Altitude;
import de.yadrone.base.navdata.AltitudeListener;
import de.yadrone.base.navdata.NavDataManager;

public class AltitudeEmitter extends ReactEventEmitter implements AltitudeListener {

    public static int UPDATE_ALTITUDE = 0;

    public AltitudeEmitter(ReactApplicationContext context, NavDataManager manager) {
        super(context, manager);
    }

    @Override
    public void removeListener() {
        getNavDataManager().removeAltitudeListener(this);
    }

    @Override
    public void receivedAltitude(int i) {
        Log.v("receivedAltitude", "altitude: " + i);
        if(!isReadyToEmit(UPDATE_ALTITUDE)) return;
        turnOffEmit(UPDATE_ALTITUDE);
        Log.v("receivedAltitude", "Interval passed, sending this event.");

        WritableMap params = Arguments.createMap();
        params.putInt("altitude", i);
        sendEvent("attitude", params);
    }

    @Override
    public void receivedExtendedAltitude(Altitude altitude) {
        // We don't need this yet.
    }



}
