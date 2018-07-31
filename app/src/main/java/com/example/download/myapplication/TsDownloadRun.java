package com.example.download.myapplication;

import android.net.TrafficStats;
import android.os.Build;
import android.os.Environment;
import android.os.Process;

import com.example.download.myapplication.entity.Ts;

import java.io.BufferedInputStream;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TsDownloadRun implements Runnable {
    private Ts ts;
    private VideoDownload videoDownload;
    private CallBack callBack;

    private final TsDownloadInfo mInfoDelta;

    private class TsDownloadInfo {
        public String mUri;
        public String mFileName;
        public int sttatus;
        public int mNumFailed;
        public long mTotalBytes;
        public long mCurrentBytes;

        public TsDownloadInfo(String url, String fileName) {
            this.mUri = url;
            this.mFileName = fileName;
        }
    }

    public TsDownloadRun(VideoDownload videoDownload, Ts ts, CallBack callBack) {
        this.ts = ts;
        this.videoDownload = videoDownload;
        this.callBack = callBack;
        mInfoDelta = new TsDownloadInfo(ts.getUrl(), ts.getPath());
    }

    @Override
    public void run() {
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
        mInfoDelta.mNumFailed = -1;
        while (mInfoDelta.mNumFailed++ < 2) {
            try {
                if (Build.VERSION.SDK_INT >= 14) {
                    TrafficStats.setThreadStatsTag(0xFFFFFF07);
                }
                execute(ts);
                if (Build.VERSION.SDK_INT >= 14) {
                    TrafficStats.incrementOperationCount(1);
                }
                mInfoDelta.sttatus = 200;
                if (mInfoDelta.mTotalBytes == -1) {
                    mInfoDelta.mTotalBytes = mInfoDelta.mCurrentBytes;
                }
            } catch (Exception e) {

            } finally {
                if (Build.VERSION.SDK_INT >= 14) {
                    TrafficStats.clearThreadStatsTag();
                }
            }
        }
        callBack.isSuccess(mInfoDelta.sttatus == 200, ts);
    }

    public void execute(Ts ts) {
        try {
            URL url = new URL(ts.getUrl());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(1000);
            urlConnection.setReadTimeout(1000);
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = urlConnection.getInputStream();
                saveInputStream(inputStream);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveInputStream(InputStream inputStream) throws IOException {
        FileOutputStream fileOutputStream = null;
        FileDescriptor fileDescriptor;
        try {
            fileOutputStream = new FileOutputStream(Environment.getDownloadCacheDirectory());
            fileDescriptor = fileOutputStream.getFD();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        fileOutputStream.write(bufferedInputStream.read());
    }

    private void checkPauseOrStop(){

    }
    public abstract static class CallBack {
        public abstract void isSuccess(boolean is, Ts ts);
    }
}
