package com.fruitrec.drone.navdata;


import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;

import de.yadrone.base.navdata.AttitudeListener;
import de.yadrone.base.navdata.NavDataManager;

public class AttitudeEmitter extends ReactEventEmitter implements AttitudeListener {

    public static int UPDATE_ATTITUDE_PYR = 0;
    public static int UPDATE_ATTITUDE_TP = 1;
    public static int UPDATE_WIND_COMPENSATION = 2;

    public AttitudeEmitter(ReactApplicationContext context, NavDataManager manager) {
        super(context, manager);
        initializeEventFlags(3);
    }

    @Override
    public void attitudeUpdated(float pitch, float roll, float yaw) {
        Log.v("attitudeUpdated", "pitch: " + pitch + ", roll:" + roll + ", yaw: " + yaw);
        if(!isReadyToEmit(UPDATE_ATTITUDE_PYR)) return;
        turnOffEmit(UPDATE_ATTITUDE_PYR);
        Log.v("attitudeUpdated", "Interval passed, sending this event.");

        WritableMap params = Arguments.createMap();
        params.putDouble("pitch", pitch);
        params.putDouble("roll", roll);
        params.putDouble("yaw", yaw);
        sendEvent("attitude", params);
    }

    @Override
    public void attitudeUpdated(float thetaAngle, float phiAngle) {
        Log.v("attitudeUpdated", "thetaAngle: " + thetaAngle + ", phiAngle:" + phiAngle);
        if(!isReadyToEmit(UPDATE_ATTITUDE_TP)) return;
        turnOffEmit(UPDATE_ATTITUDE_TP);
        Log.v("attitudeUpdated", "Interval passed, sending this event.");

        WritableMap params = Arguments.createMap();
        params.putDouble("thetaAngle", thetaAngle);
        params.putDouble("phiAngle", phiAngle);
        sendEvent("attitude", params);
    }

    @Override
    public void windCompensation(float windCompensationThetaAngle, float windCompensationPhiAngle) {
        Log.v("attitudeUpdated", "windCompensationThetaAngle: " + windCompensationThetaAngle + ", windCompensationPhiAngle:" + windCompensationPhiAngle);
        if(!isReadyToEmit(UPDATE_WIND_COMPENSATION)) return;
        turnOffEmit(UPDATE_WIND_COMPENSATION);
        Log.v("attitudeUpdated", "Interval passed, sending this event.");

        WritableMap params = Arguments.createMap();
        params.putDouble("windCompensationThetaAngle", windCompensationThetaAngle);
        params.putDouble("windCompensationPhiAngle", windCompensationPhiAngle);
        sendEvent("attitude", params);
    }

    @Override
    public void removeListener() {
        getNavDataManager().removeAttitudeListener(this);
    }

}
