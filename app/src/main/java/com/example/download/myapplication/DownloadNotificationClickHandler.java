package com.example.download.myapplication;

import android.content.Context;
import android.content.Intent;

public class DownloadNotificationClickHandler {

    protected void handleMessage(Context context, Intent intent) {
        String action = intent.getStringExtra("D_ACTION");
        String notificationTag = intent.getStringExtra("NOTIFICATION_VOD_ID");  //实际是 vodId
        if ("7".equals(action)) {
            dealWithCustomAction(context);
        } else if ("10".equals(action)) {
            dealWithCustomActionCompleted(context, notificationTag);
        } else {
            dismissNotification(context, notificationTag);
        }
    }


    public void dealWithCustomAction(Context context) {
    }

    public void dealWithCustomActionCompleted(Context context, String vodId) {
    }

    public void dismissNotification(Context context, String vodId) {
    }

}
