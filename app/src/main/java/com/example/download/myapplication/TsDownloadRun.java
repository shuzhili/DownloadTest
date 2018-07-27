package com.example.download.myapplication;

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

    public TsDownloadRun(Ts ts) {
        this.ts = ts;
    }

    @Override
    public void run() {
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
        execute(ts);
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
}
