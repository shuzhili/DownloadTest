package com.example.download.myapplication;

import android.content.Context;
import android.text.TextUtils;

import com.example.download.myapplication.DBManage.DBManager;
import com.greenrobot.greendao.HLSOfflineDao;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class DownloadVideoPlug implements DownloadableViewPlug {
    public HLSOfflineDao hlsOfflineDao;

    public DownloadVideoPlug(Context context) {
        hlsOfflineDao = DBManager.getHlsOfflineDao(context);
    }

    @Override
    public void deleteDownloadableVideos(String vodID) {
        HLSOffline hlsOffline =
                hlsOfflineDao.queryBuilder().where(HLSOfflineDao.Properties.VodId.eq(vodID)).unique();
        if (hlsOffline != null) {
            if (!TextUtils.isEmpty(hlsOfflineDao.getKey(hlsOffline))) {
                hlsOfflineDao.delete(hlsOffline);
            }
        }
        if (TextUtils.isEmpty(hlsOffline.getFold())) {
            return;
        }
        File fold = new File(hlsOffline.getFold());
        if (fold.exists() && fold.isDirectory()) {
            File[] files = fold.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    file.delete();
                }
            }
            fold.delete();
        }
    }

    @Override
    public void updataDownloadInfo(VideoDownloadObserver info) {
        String url = info.getUrl();
        if (TextUtils.isEmpty(url)) {
            return;
        }
        HLSOffline hlsOffline = hlsOfflineDao.queryBuilder().where(HLSOfflineDao.Properties.Url.eq(url)).unique();

        hlsOffline.setUrl(info.getUrl());
        hlsOffline.setUrl_md5(info.getUrlMd5());
        hlsOffline.setFile(info.getFile());
        hlsOffline.setFold(info.getFold());
        hlsOffline.setTotalDuration((int) info.getDuration());
        hlsOffline.setTsNum(info.getTsNum());
        hlsOffline.setTsdl(info.getTsD1());
        hlsOffline.setStatus(info.getStatus().getStatusCode());

        float bandW = hlsOffline.getBandW();
        int tsNum = info.getTsNum();
        int tsDl = info.getTsD1();
        int totalSize = (int) (bandW * (info.getDuration() / 60f));

        int downloadSize = 0;
        if (tsNum != 0) {
            downloadSize = (int) (totalSize * (tsDl * 1.0f / tsNum * 1.0f));
        }
        hlsOffline.setTotalSize(totalSize);
        hlsOffline.setDownloadSize(downloadSize);

        hlsOfflineDao.update(hlsOffline);
    }

    @Override
    public List<HLSOffline> getDownloadingList() {
        List<HLSOffline> hlsOfflines = hlsOfflineDao.queryBuilder().where(HLSOfflineDao.Properties.Status.eq(1)).list();
        return hlsOfflines;
    }

    @Override
    public List<HLSOffline> getPendingList() {
        List<HLSOffline> hlsOfflines = hlsOfflineDao.queryBuilder().where(HLSOfflineDao.Properties.Status.eq(6)).list();
        return hlsOfflines;
    }
}
