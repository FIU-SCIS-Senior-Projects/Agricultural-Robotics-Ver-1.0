package com.fruitrec.drone.navdata;

import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;

import de.yadrone.base.navdata.GyroListener;
import de.yadrone.base.navdata.GyroPhysData;
import de.yadrone.base.navdata.GyroRawData;
import de.yadrone.base.navdata.NavDataManager;


public class GyroEmitter extends ReactEventEmitter implements GyroListener {

    public static int UPDATE_GYRO_PHYS = 0;

    public GyroEmitter(ReactApplicationContext context, NavDataManager manager) {
        super(context, manager);
        initializeEventFlags(1);
    }

    @Override
    public void removeListener() {
        getNavDataManager().removeGyroListener(this);
    }

    @Override
    public void receivedRawData(GyroRawData paramGyroRawData) {
        // We don't need this yet.
    }

    @Override
    public void receivedOffsets(float[] paramArrayOfFloat) {
        // We don't need this yet.
    }

    @Override
    public void receivedPhysData(GyroPhysData paramGyroPhysData) {
        float[] vel = paramGyroPhysData.getPhysGyros();
        Log.v("receivedPhysData",
                "velX: " + vel[0] +
                ", velY: " + vel[1] +
                ", velZ: " + vel[2]);
        if(!isReadyToEmit(UPDATE_GYRO_PHYS)) return;
        turnOffEmit(UPDATE_GYRO_PHYS);
        Log.v("receivedPhysData", "Interval passed, sending this event.");

        WritableMap params = Arguments.createMap();
        params.putDouble("velX", vel[0]);
        params.putDouble("velY", vel[1]);
        params.putDouble("velZ", vel[2]);
        sendEvent("gyro", params);
    }


}
