package com.androidplaygroundproject.receivers;

public interface DroneFirmwareCheckReceiverDelegate 
{
	public void onFirmwareChecked(boolean updateRequired);
}
