package com.fruitrec.drone.navdata;

import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;

import com.fruitrec.navdata.ControlState;
import com.fruitrec.navdata.DroneState;
import com.fruitrec.navdata.NavDataManager;
import com.fruitrec.navdata.StateListener;

public class FlyStateEmitter extends ReactEventEmitter implements StateListener {

    public static int UPDATE_DRONE_STATE = 0;

    public FlyStateEmitter(ReactApplicationContext context, NavDataManager manager) {
        super(context, manager);
    }

    @Override
    public void removeListener() {
        getNavDataManager().removeStateListener(this);
    }


    @Override
    public void stateChanged(DroneState droneState) {
        Log.v("stateChanged", "droneState: " + droneState.getStateBits());
        if(!isReadyToEmit(UPDATE_DRONE_STATE)) return;
        turnOffEmit(UPDATE_DRONE_STATE);
        Log.v("droneState", "Interval passed, sending this event.");

        WritableMap params = Arguments.createMap();
        params.putInt("droneState", droneState.getStateBits());
        sendEvent("droneState", params);
    }
    @Override
    public void controlStateChanged(ControlState controlState) {
        // Data here is a subset of stateChanged. Safe to say we'll never need this implementation.
    }
}
