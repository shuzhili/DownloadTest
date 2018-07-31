package com.example.download.myapplication;

import com.example.download.myapplication.entity.Ts;

public class DownloadCallBack extends TsDownloadRun.CallBack {
    private VideoDownload mVideoDownload;

    public DownloadCallBack(VideoDownload videoDownload) {
        mVideoDownload = videoDownload;
    }

    @Override
    public synchronized void isSuccess(boolean is, Ts ts) {
        if (mVideoDownload.status == VideoDownload.DownloadStatus.DOWNLOADING) {
            return;
        }
        --mVideoDownload.tsNum;
        if (is) {
            boolean b = mVideoDownload.m3u8.saveTs(ts);
            if (b) {
                this.mVideoDownload.tsD1 = mVideoDownload.m3u8.getTotalTsNum() - mVideoDownload.m3u8.getTsList().size();
                successStatus();
            } else {
                failureStatus();
            }
        } else {
            failureStatus();
        }
    }

    private void successStatus() {
        if (this.mVideoDownload.m3u8.isComplete()) {
            this.mVideoDownload.updateStatus(VideoDownloadObserver.DownloadStatus.COMPLETED);
        } else if (this.mVideoDownload.count == 0) {
            this.mVideoDownload.updateStatus(VideoDownloadObserver.DownloadStatus.ERROR);
        } else {
            this.mVideoDownload.update();
        }
    }

    private void failureStatus() {
        if (this.mVideoDownload.count == 0) {
            this.mVideoDownload.updateStatus(VideoDownloadObserver.DownloadStatus.ERROR);
        }
    }
}
