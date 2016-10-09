package com.fruitrec.drone.navdata;

import com.facebook.react.bridge.ReactApplicationContext;

import de.yadrone.base.navdata.BatteryListener;
import de.yadrone.base.navdata.NavDataManager;


public class BatteryEmitter extends ReactEventEmitter implements BatteryListener {

    public BatteryEmitter(ReactApplicationContext context, NavDataManager manager) {
        super(context, manager);
    }

    @Override
    public void batteryLevelChanged(int i) {

    }

    @Override
    public void voltageChanged(int i) {

    }

    @Override
    public void removeListener() {

    }
}
