package com.example.download.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class VideoDownloadManager {
    private Context mContext;
    public static volatile VideoDownloadManager videoDownloadManager;
    private Queue queue = new LinkedList();
    private ConcurrentHashMap map = new ConcurrentHashMap();
    private DownloadableViewPlug downloadableViewPlug;
    private DownloadNotifyCationHandler downloadNotifyCationHandler;
    private DownloadNotificationClickHandler downloadNotificationClickHandler;
    private DownVideoObserver observer;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            VideoDownload videoItem = (VideoDownload) msg.obj;
            if (videoItem.getStatus() == VideoDownloadObserver.DownloadStatus.COMPLETED || videoItem.getStatus() == VideoDownloadObserver.DownloadStatus.ERROR) {
                String url = videoItem.url;
                queue.remove(url);
                map.remove(url);
                nextQueue();
            }
        }
    };

    private VideoDownloadManager(Context context, DownloadableViewPlug downloadableViewPlug) {
        mContext = context;
        this.downloadableViewPlug = downloadableViewPlug;
        this.downloadNotifyCationHandler = new DownloadNotifyCationHandler();
        this.downloadNotificationClickHandler = new DownloadNotificationClickHandler();
        this.observer = new DownVideoObserver(context, mHandler, downloadNotifyCationHandler);
        init();
    }

    public static synchronized VideoDownloadManager getInstance(Context context, DownloadableViewPlug downloadableViewPlug) {
        if (videoDownloadManager == null) {
            videoDownloadManager = new VideoDownloadManager(context, downloadableViewPlug);
        }
        return videoDownloadManager;
    }

    public static VideoDownloadManager getManager() {
        return videoDownloadManager;
    }

    public void init() {
        this.map.clear();
        this.queue.clear();
        List<HLSOffline> downloadingList = downloadableViewPlug.getDownloadingList();
        if (downloadingList != null) {
            for (int i = 0; i < downloadingList.size(); i++) {
                HLSOffline offline = downloadingList.get(i);
                String url = offline.getUrl();
                VideoDownload videoDownload = getVideoDownload(offline);
                map.put(url, videoDownload);
            }
        }

        List<HLSOffline> pendingList = downloadableViewPlug.getPendingList();
        if (pendingList != null) {
            for (int i = 0; i < pendingList.size(); i++) {
                HLSOffline offline = pendingList.get(i);
                String url = pendingList.get(i).getUrl();
                VideoDownloadObserver item = getVideoDownload(offline);
                map.put(url, item);
                queue.offer(url);
            }
        }
        //----3
        registerNetworkReceiver();
    }

    private VideoDownload getVideoDownload(HLSOffline offline) {
        VideoDownloadObserver videodown = new VideoDownloadObserver();
        videodown.setNotifyCationTag(offline.getVodId());
        videodown.setPicUrl(offline.getPic());
        videodown.setTitle(offline.getTitle());
        videodown.setUrl(offline.getUrl());

        String urlMd5 = offline.getUrl_md5();
        String key = DownloadUtils.getMD5(offline.getUrl());
        String fold = this.getDownloadFolder() + key + "/";
        String file = key + ".m3u8";

        videodown.setUrlMd5(key);
        videodown.setFold(fold);
        videodown.setFile(file);
        videodown.setDuration(0);
        videodown.setTsNum(0);
        videodown.setTsD1(0);
        videodown.setStatus(VideoDownloadObserver.DownloadStatus.NONE);

        VideoDownload videoDownload = VideoDownload.getInstance(mContext, videodown, downloadableViewPlug);
        videoDownload.addObserver(observer);
        return videoDownload;
    }


    public String getDownloadFolder() {
        File file = mContext.getExternalFilesDir(null);
        if (file == null) {
            file = mContext.getFilesDir();
        }
        return file.getAbsolutePath() + "/video/";
    }

    private BroadcastReceiver mNetworkReceiver = null;

    public void registerNetworkReceiver() {
        if (mNetworkReceiver != null)
            return;

        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        mNetworkReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                    ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo info = manager.getActiveNetworkInfo();
                    if (info != null && info.isConnected()
                            && info.getType() == ConnectivityManager.TYPE_WIFI) {

                        boolean start = true;
                        for (Object key : map.keySet()) {
                            if (!queue.contains(key)) {
                                VideoDownload item = (VideoDownload) map.get(key);
                                item.start();
                                start = false;
                            }
                        }
                        if (start) {
                            nextQueue();
                        }
                    } else {

                        for (Object key : map.keySet()) {
                            if (!queue.contains(key)) {
                                VideoDownload item = (VideoDownload) map.get(key);
                                item.pending();
                            }
                        }
                    }
                }
            }
        };
        this.mContext.registerReceiver(mNetworkReceiver, intentFilter);
    }

    private void nextQueue() {

        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info == null || !info.isConnected()) {
            return;
        }

        synchronized (this.queue) {
            if (map.size() - queue.size() < 1) {
                String url = (String) this.queue.poll();
                if (url != null) {
                    VideoDownload item = (VideoDownload) this.map.get(url);
                    if (item != null) {
                        item.start();
                    }
                }
            }
        }
    }

    public DownloadNotificationClickHandler getNotificationClickHandler() {
        return downloadNotificationClickHandler;
    }
}
