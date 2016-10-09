package com.fruitrec.drone.navdata;


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
        initializeEventEmitter(3);
    }

    @Override
    public void removeListener() {
        getNavDataManager().removeAttitudeListener(this);
    }

    @Override
    public void attitudeUpdated(float pitch, float roll, float yaw) {
        if(!isReadyToEmit(UPDATE_ATTITUDE_PYR) && isReadyToEmit(UPDATE_ATTITUDE_TP)) return;
        turnOffEmit(UPDATE_ATTITUDE_PYR);

        WritableMap params = Arguments.createMap();
        params.putDouble("pitch", pitch);
        params.putDouble("roll", roll);
        params.putDouble("yaw", yaw);
        sendEvent("attitudeDidChange", params);
    }

    @Override
    public void attitudeUpdated(float thetaAngle, float phiAngle) {
        if(!isReadyToEmit(UPDATE_ATTITUDE_TP)) return;
        turnOffEmit(UPDATE_ATTITUDE_TP);

        //WritableMap params = Arguments.createMap();
        //params.putDouble("thetaAngle", thetaAngle);
        //params.putDouble("phiAngle", phiAngle);
        //sendEvent("attitudeDidChange", params);
    }

    @Override
    public void windCompensation(float windCompensationThetaAngle, float windCompensationPhiAngle) {
        if(!isReadyToEmit(UPDATE_WIND_COMPENSATION)) return;
        turnOffEmit(UPDATE_WIND_COMPENSATION);

        //WritableMap params = Arguments.createMap();
        //params.putDouble("windCompensationThetaAngle", windCompensationThetaAngle);
        //params.putDouble("windCompensationPhiAngle", windCompensationPhiAngle);
        //sendEvent("attitudeDidChange", params);
    }
}
