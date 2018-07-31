package com.example.download.myapplication;

import android.content.Context;

import com.example.download.myapplication.entity.M3u8;
import com.example.download.myapplication.entity.Ts;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VideoDownload extends VideoDownloadObserver {
    protected M3u8 m3u8;
    private ExecutorService executorService = Executors.newScheduledThreadPool(3);
    public int count;
    public volatile DownloadStatus status;
    public Context mContext;
    public VideoDownloadObserver mVideoDownloadObserver;
    public DownloadableViewPlug mPlug;
    public DownloadCallBack mDownloadCallBack;

    public VideoDownload(Context context, VideoDownloadObserver videoDownloadObserver, DownloadableViewPlug plug) {
        mContext = context;
        mVideoDownloadObserver = videoDownloadObserver;
        mPlug = plug;
        mDownloadCallBack = new DownloadCallBack(this);
    }

    public void execute() {
        List<Ts> tsList = m3u8.getTsList();
        for (Ts ts : tsList) {
            TsDownloadRun tsDownloadRun = new TsDownloadRun(VideoDownload.this, ts, mDownloadCallBack);
            executorService.execute(tsDownloadRun);
        }
    }

    public void updateStatus(DownloadStatus completed) {
        status = completed;
        update();
    }

    public void update() {
        setChanged();
        notifyObservers();
    }

    public void start() {
        status = DownloadStatus.DOWNLOADING;
        updateStatus(DownloadStatus.DOWNLOADING);
        execute();
    }

    public void pause() {
        status = DownloadStatus.PAUSED;
        updateStatus(DownloadStatus.PAUSED);
        executorService.shutdown();
    }

    public void delete() {
        status = DownloadStatus.DELETED;
        updateStatus(DownloadStatus.DELETED);
        executorService.shutdown();
    }

    public static VideoDownload getInstance(Context context, VideoDownloadObserver VideoDownloadObserver, DownloadableViewPlug plug) {
        return new VideoDownload(context, VideoDownloadObserver, plug);
    }
}
