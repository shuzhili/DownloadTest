package com.example.download.myapplication;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.RemoteViews;

class DownloadNotifyCationHandler {
    private NotificationCompat.Builder builder;

    public synchronized void handleMessage(Context context, VideoDownloadObserver video) {

        if (builder == null) {
            builder = new NotificationCompat.Builder(context, "download");
            builder.setSmallIcon(getSmallIconId(context));
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.download_notification_layout);
            builder.setCustomContentView(remoteViews);
        }

        VideoDownloadObserver.DownloadStatus status = video.getStatus();
        String notificationTag = video.getNotifyCationTag();

        switch (status) {
            case DOWNLOADING:
                this.notify(context, this.getNotification(context, video), notificationTag);
                break;
            case COMPLETED:
                this.cancel(context, notificationTag);
                this.notify(context, this.getNotificationCompleted(context, video), "download_notification_completed");
                break;
            case ERROR:
            case PENDING:
            case PAUSED:
            case DELETED:
                this.cancel(context, notificationTag);
                break;
        }
    }

    private void cancel(Context context, String notificationTag) {
        try {
            NotificationManager manager = (NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

            manager.cancel(notificationTag, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void notify(Context context, Notification notification, String notificationTag) {
        if (notification == null) {
            return;
        }
        try {
            NotificationManager manager = (NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

            manager.notify(notificationTag, 0, notification);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PendingIntent getClickPendingIntent(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, DownloadNotificationProxyBroadcastReceiver.class);
        intent.putExtra("D_ACTION", "7");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_CANCEL_CURRENT);
        return pendingIntent;
    }

    private PendingIntent getClickPendingIntentCompleted(Context context, String vodId) {
        Intent intent = new Intent();
        intent.setClass(context, DownloadNotificationProxyBroadcastReceiver.class);
        intent.putExtra("D_ACTION", "10");
        intent.putExtra("NOTIFICATION_VOD_ID", vodId);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_CANCEL_CURRENT);
        return pendingIntent;
    }

    private Notification getNotificationCompleted(Context context, VideoDownloadObserver video) {
        @SuppressLint("RestrictedApi") RemoteViews remoteViews = builder.getContentView();

        String title = video.title;
        Bitmap bitmap = video.bitmap;
        String vodId = video.getNotifyCationTag();

        if (bitmap != null && !bitmap.isRecycled()) {
            remoteViews.setImageViewBitmap(R.id.dn_image, bitmap);
        } else {
            remoteViews.setImageViewResource(R.id.dn_image, R.drawable.notification_default_16_9);
        }
        remoteViews.setTextViewText(R.id.dn_title, "缓存完成！");
        remoteViews.setTextViewText(R.id.dn_content, "点击观看 " + title);

        remoteViews.setViewVisibility(R.id.dn_progress_text, View.GONE);
        remoteViews.setViewVisibility(R.id.dn_progress_bar, View.GONE);
        remoteViews.setViewVisibility(R.id.dn_content, View.VISIBLE);

        builder.setAutoCancel(true);
        builder.setOngoing(false);

        Notification notification = builder.build();
        notification.contentIntent = getClickPendingIntentCompleted(context, vodId);

        return notification;
    }

    private Notification getNotification(Context context, VideoDownloadObserver video) {

        String title = video.title;
        int percent = video.getTsNum() == 0 ? 0 : video.tsD1 * 100 / video.getTsNum();
        Bitmap bitmap = video.bitmap;

        @SuppressLint("RestrictedApi") RemoteViews remoteViews = builder.getContentView();

        if (bitmap != null && !bitmap.isRecycled()) {
            remoteViews.setImageViewBitmap(R.id.dn_image, bitmap);
        } else {
            remoteViews.setImageViewResource(R.id.dn_image, R.drawable.notification_default_16_9);
        }
        remoteViews.setTextViewText(R.id.dn_title, title);
        remoteViews.setTextViewText(R.id.dn_progress_text, percent + "%");
        remoteViews.setProgressBar(R.id.dn_progress_bar, 100, percent, false);

        remoteViews.setViewVisibility(R.id.dn_progress_text, View.VISIBLE);
        remoteViews.setViewVisibility(R.id.dn_progress_bar, View.VISIBLE);
        remoteViews.setViewVisibility(R.id.dn_content, View.GONE);

        builder.setAutoCancel(false);
        builder.setOngoing(true);

        Notification notification = builder.build();
        notification.contentIntent = getClickPendingIntent(context);

        return notification;
    }

    private int getSmallIconId(Context context) {
        Resources res = context.getResources();
        int picid = res.getIdentifier("umeng_push_notification_default_small_icon", "drawable", context.getPackageName());
        if (picid <= 0) {
            picid = R.drawable.download_notification_default_small_icon;
        }
        return picid;
    }
}
