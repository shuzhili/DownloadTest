package com.example.download.myapplication;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import java.util.Observable;
import java.util.Observer;

public class DownVideoObserver implements Observer {
    private Context context;
    private Handler handler;
    private DownloadNotifyCationHandler notifycation;

    public DownVideoObserver(Context context, Handler handler, DownloadNotifyCationHandler notifycation) {
        this.context = context;
        this.handler = handler;
        this.notifycation = notifycation;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof VideoDownloadObserver) {
            if (handler != null) {
                Message message = handler.obtainMessage();
                message.obj = o;
                message.sendToTarget();
            }
            if (notifycation != null) {
                notifycation.handleMessage(context, (VideoDownloadObserver) o);
            }
        }
    }


}
