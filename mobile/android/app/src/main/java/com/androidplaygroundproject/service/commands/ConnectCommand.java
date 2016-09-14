/*
 * ConnectCommand
 *
 *  Created on: May 5, 2011
 *      Author: Dmytro Baryskyy
 */

package com.androidplaygroundproject.service.commands;

import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.androidplaygroundproject.drone.DroneProxy;
import com.androidplaygroundproject.drone.DroneProxy.EVideoRecorderCapability;
import com.androidplaygroundproject.drone.DroneProxyConnectedReceiver;
import com.androidplaygroundproject.drone.DroneProxyConnectedReceiverDelegate;
import com.androidplaygroundproject.drone.DroneProxyConnectionFailedReceiver;
import com.androidplaygroundproject.drone.DroneProxyConnectionFailedReceiverDelegate;
import com.androidplaygroundproject.drone.DroneProxyDisconnectedReceiver;
import com.androidplaygroundproject.drone.DroneProxyDisconnectedReceiverDelegate;
import com.androidplaygroundproject.service.DroneControlService;
import com.androidplaygroundproject.utils.DeviceCapabilitiesUtils;

public class ConnectCommand extends DroneServiceCommand 
implements 
DroneProxyConnectedReceiverDelegate,
DroneProxyDisconnectedReceiverDelegate,
DroneProxyConnectionFailedReceiverDelegate
{
	public static final int CONNECTED = 0;
	public static final int DISCONNECTED = 1;
	public static final int CONNECTION_FAILED = 2;

	private int result;
	
	private LocalBroadcastManager bm;
	private DroneProxy droneProxy;
	
	private DroneProxyConnectedReceiver connectedReceiver;
	private DroneProxyDisconnectedReceiver disconnectedReceiver;
    private DroneProxyConnectionFailedReceiver connFailedReceiver;

	
	public ConnectCommand(DroneControlService context)
	{
		super(context);
	      
		droneProxy = DroneProxy.getInstance(context.getApplicationContext());
		
		connectedReceiver = new DroneProxyConnectedReceiver(this);
		disconnectedReceiver = new DroneProxyDisconnectedReceiver(this);
		connFailedReceiver = new DroneProxyConnectionFailedReceiver(this);
		
		bm = LocalBroadcastManager.getInstance(context.getApplicationContext());
		
		result = DISCONNECTED;
	}
	
	public void execute() 
	{
	    bm.registerReceiver(connectedReceiver, new IntentFilter(DroneProxy.DRONE_PROXY_CONNECTED_ACTION));
	    bm.registerReceiver(disconnectedReceiver, new IntentFilter(DroneProxy.DRONE_PROXY_DISCONNECTED_ACTION));
	    bm.registerReceiver(connFailedReceiver, new IntentFilter(DroneProxy.DRONE_PROXY_CONNECTION_FAILED_ACTION));
	  
	    EVideoRecorderCapability recorderCapability = DeviceCapabilitiesUtils.getMaxSupportedVideoRes();
	    
	    if (recorderCapability == EVideoRecorderCapability.NOT_SUPPORTED) {
	    	// Giving user a chance to record the video
	    	recorderCapability = EVideoRecorderCapability.VIDEO_360P;
	    }
	    
	    droneProxy.doConnect(context, recorderCapability);
	}

	public void onToolConnected() 
	{
	    onCommandFinished(CONNECTED);
	}

	public void onToolConnectionFailed(int reason) 
	{
	    onCommandFinished(CONNECTION_FAILED);
	}

	public void onToolDisconnected() 
	{
	    onCommandFinished(DISCONNECTED);	
	}

	protected void onCommandFinished(int result)
	{
	    bm.unregisterReceiver(connectedReceiver);
	    bm.unregisterReceiver(disconnectedReceiver);
	    bm.unregisterReceiver(connFailedReceiver);
	    
        this.result = result;
        context.onCommandFinished(this);
	}
	
	public int getResult()
	{
		return result;
	}
}
