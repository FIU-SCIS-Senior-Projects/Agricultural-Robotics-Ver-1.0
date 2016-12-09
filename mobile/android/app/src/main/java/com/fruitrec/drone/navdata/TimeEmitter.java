package com.fruitrec.drone.navdata;

import com.facebook.react.bridge.ReactApplicationContext;

import com.fruitrec.navdata.NavDataManager;
import com.fruitrec.navdata.TimeListener;

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
