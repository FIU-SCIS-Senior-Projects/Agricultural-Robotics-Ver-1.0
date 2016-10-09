package com.fruitrec.drone.navdata;

import com.facebook.react.bridge.ReactApplicationContext;

import de.yadrone.base.navdata.ControlState;
import de.yadrone.base.navdata.DroneState;
import de.yadrone.base.navdata.NavDataManager;
import de.yadrone.base.navdata.StateListener;

public class FlyStateEmitter extends ReactEventEmitter implements StateListener {

    public FlyStateEmitter(ReactApplicationContext context, NavDataManager manager) {
        super(context, manager);
    }

    @Override
    public void removeListener() {

    }

    @Override
    public void stateChanged(DroneState droneState) {

    }

    @Override
    public void controlStateChanged(ControlState controlState) {

    }
}
