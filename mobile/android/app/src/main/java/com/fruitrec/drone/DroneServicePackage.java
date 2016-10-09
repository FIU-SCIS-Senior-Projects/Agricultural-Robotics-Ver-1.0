package com.fruitrec.drone;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.fruitrec.drone.navdata.NavDataServiceModule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DroneServicePackage implements ReactPackage {

    //private IARDrone drone;

    public DroneServicePackage()
    {
        //drone = new ARDrone("192.168.1.1", null); // null because of missing video support on Android
    }

    @Override
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }

    @Override
    public List<NativeModule> createNativeModules(
            ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();

        //modules.add(new NavDataServiceModule(reactContext, drone));
        modules.add(new NavDataServiceModule(reactContext));

        return modules;
    }

}