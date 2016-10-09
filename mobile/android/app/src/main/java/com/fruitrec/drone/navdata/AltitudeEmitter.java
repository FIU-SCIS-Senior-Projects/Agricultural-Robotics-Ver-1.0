package com.fruitrec.drone.navdata;

import com.facebook.react.bridge.ReactApplicationContext;

import de.yadrone.base.navdata.Altitude;
import de.yadrone.base.navdata.AltitudeListener;
import de.yadrone.base.navdata.NavDataManager;

/**
 * Created by miguel on 10/8/16.
 */
public class AltitudeEmitter extends ReactEventEmitter implements AltitudeListener {
    public AltitudeEmitter(ReactApplicationContext context, NavDataManager manager) {
        super(context, manager);
    }

    @Override
    public void receivedAltitude(int i) {

    }

    @Override
    public void receivedExtendedAltitude(Altitude altitude) {

    }

    @Override
    public void removeListener() {

    }
}
