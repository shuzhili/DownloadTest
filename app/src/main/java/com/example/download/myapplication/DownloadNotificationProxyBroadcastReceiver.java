package com.example.download.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DownloadNotificationProxyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        VideoDownloadManager manager = VideoDownloadManager.getManager();
        if (manager != null) {
            DownloadNotificationClickHandler handler = manager.getNotificationClickHandler();
            if (handler != null) {
                handler.handleMessage(context, intent);
            }
        }
    }

}
