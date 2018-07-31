package com.example.download.myapplication;

import android.app.DownloadManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

final class DownloadUtils {
    public static long downloadTs(String url, String path, DownloadManager dManager) {
        Log.d("DownloadUtils_New", String.format("save url[%s] to file[%s]", new Object[]{url, path}));
        try {
            Uri uri = Uri.parse(url);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setDestinationUri(Uri.fromFile(new File(path)));
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
            long downId = dManager.enqueue(request);

            return downId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static boolean downloadTs(String url, String path, boolean isRedirect) {
        Log.d("DownloadUtils", String.format("save url[%s] to file[%s]", new Object[]{url, path}));
        String tmpPath = path + ".tmp";
        File tmpFile = null;
        BufferedInputStream inputStream = null;
        BufferedOutputStream outputStream = null;
        boolean isRenameSuccess = false;
        HttpURLConnection urlConnection = null;

        boolean isSuccess;
        try {
            URL lUrl = new URL(url);
            urlConnection = (HttpURLConnection) lUrl.openConnection();
            urlConnection.setConnectTimeout(30000);
            urlConnection.setReadTimeout(30000);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = new BufferedInputStream(urlConnection.getInputStream());
                tmpFile = new File(tmpPath);
                File file = new File(path);
                if (!tmpFile.getParentFile().exists()) {
                    tmpFile.getParentFile().mkdirs();
                }

                if (tmpFile.exists()) {
                    tmpFile.delete();
                }

                if (file.exists()) {
                    file.delete();
                }

                outputStream = new BufferedOutputStream(new FileOutputStream(tmpFile));
                byte[] bytes = new byte[5120];

                int len;
                while ((len = inputStream.read(bytes)) > 0) {
                    outputStream.write(bytes, 0, len);
                }

                if (tmpFile != null && tmpFile.exists()) {
                    if (tmpFile.renameTo(file)) {
                        Log.d("DownloadUtils", "rename success");
                        isRenameSuccess = true;
                    } else {
                        Log.d("DownloadUtils", "rename failed");
                        isRenameSuccess = false;
                    }
                } else {
                    Log.d("DownloadUtils", "tmFile not exists");
                    isRenameSuccess = false;
                }

                return isRenameSuccess;
            }

            isSuccess = false;
        } catch (Exception e) {
            Log.d("DownloadUtils", "Exception:" + e.getMessage());

            try {
                if (tmpFile != null) {
                    tmpFile.delete();
                }
            } catch (Exception ee) {

            }

            if (isRedirect) {
                Log.d("DownloadUtils", "Exception 重定向");
                return downloadTs(url, path, false);
            }

            isSuccess = false;
            return isSuccess;
        } finally {
            Log.d("DownloadUtils", "finally");
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {

                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e) {

                }
            }

            if (urlConnection != null) {
                try {
                    urlConnection.disconnect();
                } catch (Exception e) {

                }
            }
        }

        return isSuccess;
    }

    public static String getM3U8String(String m3u8Url) {
        Log.e("lkr", "getM3U8String：m3u8Url=" + m3u8Url);
        BufferedReader lBufferedReader = null;
        StringBuilder lStringBuilder = new StringBuilder();
        HttpURLConnection lHttpURLConnection = null;

        String lineStr;
        try {
            URL url = new URL(m3u8Url);
            lHttpURLConnection = (HttpURLConnection) url.openConnection();
            lHttpURLConnection.setConnectTimeout(30000);
            lHttpURLConnection.setReadTimeout(30000);
            lHttpURLConnection.connect();
            if (lHttpURLConnection.getResponseCode() != 200 || lHttpURLConnection.getContentLength() > 5242880) {
                lineStr = null;
                return lineStr;
            }

            lBufferedReader = new BufferedReader(new InputStreamReader(lHttpURLConnection.getInputStream()));

            while ((lineStr = lBufferedReader.readLine()) != null) {
                lStringBuilder.append(lineStr + "\n");
            }

            String m3u8Str = lStringBuilder.toString();
            Log.e("lkr", "m3u8Str=" + m3u8Str);
            return m3u8Str;
        } catch (Exception e) {
            Log.d("DownloadUtils", "", e);
            lineStr = null;
        } finally {
            if (lBufferedReader != null) {
                try {
                    lBufferedReader.close();
                } catch (Exception e) {

                }
            }
            if (lHttpURLConnection != null) {
                try {
                    lHttpURLConnection.disconnect();
                } catch (Exception e) {

                }
            }
        }

        return lineStr;
    }

    public static boolean createLocalM3u8File(byte[] var0, String path) {
        FileOutputStream outputStream = null;
        File file = null;

        boolean isSuccess;
        try {
            file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            if (file.exists()) {
                file.delete();
            }

            file.createNewFile();
            outputStream = new FileOutputStream(file);
            outputStream.write(var0);
            outputStream.flush();
            outputStream.close();

            return true;
        } catch (Exception e) {
            Log.d("DownloadUtils", "" + e.getMessage());
            if (file != null && file.exists()) {
                file.delete();
            }

            isSuccess = false;
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                Log.d("DownloadUtils", "" + e.getMessage());
            }

        }

        return isSuccess;
    }

    public static String getMD5(String paramString) {
        if (paramString == null) {
            return "";
        }
        byte[] arrayOfByte1 = paramString.getBytes();
        String str = "";
        char[] arrayOfChar1 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(arrayOfByte1);
            byte[] arrayOfByte2 = localMessageDigest.digest();
            char[] arrayOfChar2 = new char[32];
            int i = 0;
            for (int j = 0; j < 16; j++) {
                int k = arrayOfByte2[j];
                arrayOfChar2[(i++)] = arrayOfChar1[(k >>> 4 & 0xF)];
                arrayOfChar2[(i++)] = arrayOfChar1[(k & 0xF)];
            }
            str = new String(arrayOfChar2);
        } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
            Log.e("DownloadUtils", "", localNoSuchAlgorithmException);
        }
        return str;
    }

    public static boolean isWifiNetworkType(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null) {
                return networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
            }
        }
        return false;
    }
}
