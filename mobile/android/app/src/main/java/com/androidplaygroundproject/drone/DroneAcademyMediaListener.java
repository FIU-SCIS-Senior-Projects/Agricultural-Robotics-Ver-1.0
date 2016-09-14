package com.androidplaygroundproject.drone;

public interface DroneAcademyMediaListener
{
    void onNewMediaIsAvailable(String path);
    void onNewMediaToQueue(String path);
    void onQueueComplete();
}
