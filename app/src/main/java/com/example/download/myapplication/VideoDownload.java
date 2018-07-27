package com.example.download.myapplication;

import com.example.download.myapplication.entity.M3u8;
import com.example.download.myapplication.entity.Ts;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VideoDownload {
    private M3u8 m3u8;
    private ExecutorService executorService = Executors.newScheduledThreadPool(3);

    public VideoDownload(M3u8 m3u8) {
        this.m3u8 = m3u8;
    }

    public void execut() {
        List<Ts> tsList = m3u8.getTsList();
        for (Ts ts : tsList) {
            TsDownloadRun tsDownloadRun = new TsDownloadRun(ts);
            executorService.execute(tsDownloadRun);
        }
    }
}
