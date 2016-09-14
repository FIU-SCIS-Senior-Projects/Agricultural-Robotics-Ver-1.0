package com.androidplaygroundproject.receivers;

import java.io.File;

public interface MediaReadyDelegate
{
    public void onMediaReady(File mediaFile);
}
