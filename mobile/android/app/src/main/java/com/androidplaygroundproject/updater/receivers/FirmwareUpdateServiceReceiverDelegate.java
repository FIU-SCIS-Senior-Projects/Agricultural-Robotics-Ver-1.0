package com.androidplaygroundproject.updater.receivers;

import com.androidplaygroundproject.updater.FirmwareUpdateService.ECommand;
import com.androidplaygroundproject.updater.FirmwareUpdateService.ECommandResult;

public interface FirmwareUpdateServiceReceiverDelegate
{
    public void onCommandStateChanged(ECommand command, ECommandResult result, int progress, String message);
}
