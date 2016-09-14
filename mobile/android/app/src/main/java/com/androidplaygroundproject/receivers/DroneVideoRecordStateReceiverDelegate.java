package com.androidplaygroundproject.receivers;

public interface DroneVideoRecordStateReceiverDelegate 
{
	public void onDroneRecordVideoStateChanged(boolean recording, boolean usbActive, int remainingTime);
}
