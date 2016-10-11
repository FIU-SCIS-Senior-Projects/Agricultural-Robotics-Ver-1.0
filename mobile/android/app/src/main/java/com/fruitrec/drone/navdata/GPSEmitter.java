package com.fruitrec.drone.navdata;

import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;

import de.yadrone.base.navdata.GPSListener;
import de.yadrone.base.navdata.NavDataManager;

public class GPSEmitter extends ReactEventEmitter implements GPSListener {

    public static int UPDATE_GPS = 0;

    public GPSEmitter(ReactApplicationContext context, NavDataManager manager) {
        super(context, manager);
        initializeEventFlags(1);
    }

    @Override
    public void removeListener() {
        getNavDataManager().removeGPSListener(this);
    }

    @Override
    public void received(double latitude,
                         double longitude,
                         double elevation,
                         double hdop,
                         long dataAvailable,
                         boolean zeroValidated,
                         boolean wptValidated,
                         double latitudeZero,
                         double longitudeZero,
                         double latitudeFused,
                         double longitudeFuzed,
                         long gpsState,
                         float xTrajectory,
                         float yTrajectory,
                         float xReference,
                         float yReference) {

        Log.v("gpsUpdated", "latitude: " + latitude + ", longitude:" + longitude);
        if(!isReadyToEmit(UPDATE_GPS)) return;
        turnOffEmit(UPDATE_GPS);
        Log.v("gpsUpdated", "Interval passed, sending this event.");
        WritableMap params = Arguments.createMap();
        params.putDouble("latitude", latitude);
        params.putDouble("longitude", longitude);
        sendEvent("gps", params);
    }

}
