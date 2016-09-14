/*
 * DisconnectedServiceState
 *
 *  Created on: May 5, 2011
 *      Author: Dmytro Baryskyy
 */

package com.androidplaygroundproject.service.states;

import android.util.Log;

import com.androidplaygroundproject.service.DroneControlService;
import com.androidplaygroundproject.service.ServiceStateBase;
import com.androidplaygroundproject.service.commands.ConnectCommand;
import com.androidplaygroundproject.service.commands.DroneServiceCommand;

public class DisconnectedServiceState 
	extends ServiceStateBase
{

	public DisconnectedServiceState(DroneControlService context) 
	{
		super(context);
	}

	@Override
	public void connect() 
	{
		startCommand(new ConnectCommand(context));
	}

	@Override
	public void disconnect() 
	{
		Log.w(getStateName(), "Disconnect. Already disconnected. Skipped...");
	}
	
	@Override
	public void onCommandFinished(DroneServiceCommand command) 
	{
		if (command instanceof ConnectCommand) {
			if (((ConnectCommand)command).getResult() == ConnectCommand.CONNECTED) {
				setState(new ConnectedServiceState(context));
				onConnected();
			} else {
				onDisconnected();
			}
		}
	}

	@Override
	public void resume() 
	{
		Log.w(getStateName(), "Can't resume while in disconnected state. Skipped.");
	}

	@Override
	public void pause() 
	{
		Log.w(getStateName(), "Can't pause while in disconnected state. Skipped.");
	}

}
