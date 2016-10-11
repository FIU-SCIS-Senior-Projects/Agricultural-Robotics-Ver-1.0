package de.yadrone.base.navdata;

import java.util.EventListener;

public abstract interface GPSListener extends EventListener
{
  public abstract void received(
    double latitude,
    double longitude,
    double elevation,
    double hdop,
    long dataAvailable,
    boolean zeroValidated,
    boolean wptValidated,
    double latitudeZero,
    double longitudeZero,
    double latitudeFused,
    double longitudeFuzed,
    long gpsState,
    float xTrajectory,
    float yTrajectory,
    float xReference,
    float yReference
  );

}
