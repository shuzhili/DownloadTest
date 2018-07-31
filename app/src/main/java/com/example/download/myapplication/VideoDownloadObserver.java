package com.example.download.myapplication;

import android.app.Notification;
import android.graphics.Bitmap;

import java.util.Observable;

public class VideoDownloadObserver extends Observable {

    protected String title;
    protected String url;
    protected String urlMd5;
    protected String fold;
    protected String file;
    protected int tsNum;
    protected volatile int tsD1;

    protected volatile DownloadStatus status;

    protected Notification notification;
    protected String notifyCationTag;
    protected float duration;
    protected volatile int progress;
    protected Bitmap bitmap;
    protected String picUrl;

    public enum DownloadStatus {
        NONE(0, "NONE"),
        DOWNLOADING(1, "DOWNLOADING"),
        PAUSED(2, "PAUSED"),
        COMPLETED(3, "COMPLETED"),
        ERROR(4, "ERROR"),
        DELETED(5, "DELETED"),
        PENDING(6, "PENDING");

        private int statusCode;
        private String statusString;

        DownloadStatus(int i, String none) {
            this.statusCode = i;
            this.statusString = none;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public String getStatusString() {
            return statusString;
        }

        public void setStatusString(String statusString) {
            this.statusString = statusString;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlMd5() {
        return urlMd5;
    }

    public void setUrlMd5(String urlMd5) {
        this.urlMd5 = urlMd5;
    }

    public String getFold() {
        return fold;
    }

    public void setFold(String fold) {
        this.fold = fold;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getTsNum() {
        return tsNum;
    }

    public void setTsNum(int tsNum) {
        this.tsNum = tsNum;
    }

    public int getTsD1() {
        return tsD1;
    }

    public void setTsD1(int tsD1) {
        this.tsD1 = tsD1;
    }

    public DownloadStatus getStatus() {
        return status;
    }

    public void setStatus(DownloadStatus status) {
        this.status = status;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public String getNotifyCationTag() {
        return notifyCationTag;
    }

    public void setNotifyCationTag(String notifyCationTag) {
        this.notifyCationTag = notifyCationTag;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
