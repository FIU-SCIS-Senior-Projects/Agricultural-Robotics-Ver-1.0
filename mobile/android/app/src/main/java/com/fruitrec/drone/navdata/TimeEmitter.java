package com.fruitrec.drone.navdata;

import com.facebook.react.bridge.ReactApplicationContext;

import de.yadrone.base.navdata.NavDataManager;
import de.yadrone.base.navdata.TimeListener;


public class TimeEmitter extends ReactEventEmitter implements TimeListener {
    public TimeEmitter(ReactApplicationContext context, NavDataManager manager) {
        super(context, manager);
    }

    @Override
    public void timeReceived(int i, int i1) {

    }

    @Override
    public void removeListener() {

    }
}
