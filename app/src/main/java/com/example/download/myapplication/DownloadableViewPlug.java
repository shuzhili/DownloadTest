package com.example.download.myapplication;

import java.util.List;

public interface DownloadableViewPlug {
    void deleteDownloadableVideos(String vodID);

    void updataDownloadInfo(VideoDownloadObserver info);

    List<HLSOffline> getDownloadingList();

    List<HLSOffline> getPendingList();
}
