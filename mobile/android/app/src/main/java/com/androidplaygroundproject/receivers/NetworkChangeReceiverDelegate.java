package com.androidplaygroundproject.receivers;

import android.net.NetworkInfo;


public interface NetworkChangeReceiverDelegate 
{
	public void onNetworkChanged(NetworkInfo info);
}
