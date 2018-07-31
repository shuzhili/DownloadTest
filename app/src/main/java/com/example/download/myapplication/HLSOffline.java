package com.example.download.myapplication;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "offline_tbl")
public class HLSOffline {
    @Id
    @Property(nameInDb = "vodId")
    private String vodId;
    @Property(nameInDb = "setId")
    private String setId;
    @Property(nameInDb = "setTitle")
    private String setTitle;
    @Property(nameInDb = "setId")
    private String setCover;
    @Property(nameInDb = "title")
    private String title;
    @Property(nameInDb = "pic")
    private String pic;
    @Property(nameInDb = "url")
    private String url;
    @Property(nameInDb = "url_md5")
    private String url_md5;
    @Property(nameInDb = "fold")
    private String fold;
    @Property(nameInDb = "file")
    private String file;
    @Property(nameInDb = "bandWidth")
    private int bandWidth;
    @Property(nameInDb = "bandW")
    private float bandW;
    @Property(nameInDb = "totalDuration")
    private int totalDuration;
    @Property(nameInDb = "playedDuration")
    private int playedDuration;
    @Property(nameInDb = "downloadDuration")
    private int downloadDuration;
    @Property(nameInDb = "totalSize")
    private int totalSize;
    @Property(nameInDb = "downloadSize")
    private int downloadSize;
    @Property(nameInDb = "status")
    private int status;
    @Property(nameInDb = "progress")
    private int progress;
    @Property(nameInDb = "tsNum")
    private int tsNum;
    @Property(nameInDb = "tsdl")
    private int tsdl;
    @Property(nameInDb = "isChecked")
    private boolean isChecked;
    @Generated(hash = 1688824176)
    public HLSOffline(String vodId, String setId, String setTitle, String setCover,
            String title, String pic, String url, String url_md5, String fold,
            String file, int bandWidth, float bandW, int totalDuration,
            int playedDuration, int downloadDuration, int totalSize,
            int downloadSize, int status, int progress, int tsNum, int tsdl,
            boolean isChecked) {
        this.vodId = vodId;
        this.setId = setId;
        this.setTitle = setTitle;
        this.setCover = setCover;
        this.title = title;
        this.pic = pic;
        this.url = url;
        this.url_md5 = url_md5;
        this.fold = fold;
        this.file = file;
        this.bandWidth = bandWidth;
        this.bandW = bandW;
        this.totalDuration = totalDuration;
        this.playedDuration = playedDuration;
        this.downloadDuration = downloadDuration;
        this.totalSize = totalSize;
        this.downloadSize = downloadSize;
        this.status = status;
        this.progress = progress;
        this.tsNum = tsNum;
        this.tsdl = tsdl;
        this.isChecked = isChecked;
    }
    @Generated(hash = 53773347)
    public HLSOffline() {
    }
    public String getVodId() {
        return this.vodId;
    }
    public void setVodId(String vodId) {
        this.vodId = vodId;
    }
    public String getSetId() {
        return this.setId;
    }
    public void setSetId(String setId) {
        this.setId = setId;
    }
    public String getSetTitle() {
        return this.setTitle;
    }
    public void setSetTitle(String setTitle) {
        this.setTitle = setTitle;
    }
    public String getSetCover() {
        return this.setCover;
    }
    public void setSetCover(String setCover) {
        this.setCover = setCover;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPic() {
        return this.pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl_md5() {
        return this.url_md5;
    }
    public void setUrl_md5(String url_md5) {
        this.url_md5 = url_md5;
    }
    public String getFold() {
        return this.fold;
    }
    public void setFold(String fold) {
        this.fold = fold;
    }
    public String getFile() {
        return this.file;
    }
    public void setFile(String file) {
        this.file = file;
    }
    public int getBandWidth() {
        return this.bandWidth;
    }
    public void setBandWidth(int bandWidth) {
        this.bandWidth = bandWidth;
    }
    public float getBandW() {
        return this.bandW;
    }
    public void setBandW(float bandW) {
        this.bandW = bandW;
    }
    public int getTotalDuration() {
        return this.totalDuration;
    }
    public void setTotalDuration(int totalDuration) {
        this.totalDuration = totalDuration;
    }
    public int getPlayedDuration() {
        return this.playedDuration;
    }
    public void setPlayedDuration(int playedDuration) {
        this.playedDuration = playedDuration;
    }
    public int getDownloadDuration() {
        return this.downloadDuration;
    }
    public void setDownloadDuration(int downloadDuration) {
        this.downloadDuration = downloadDuration;
    }
    public int getTotalSize() {
        return this.totalSize;
    }
    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
    public int getDownloadSize() {
        return this.downloadSize;
    }
    public void setDownloadSize(int downloadSize) {
        this.downloadSize = downloadSize;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public int getProgress() {
        return this.progress;
    }
    public void setProgress(int progress) {
        this.progress = progress;
    }
    public int getTsNum() {
        return this.tsNum;
    }
    public void setTsNum(int tsNum) {
        this.tsNum = tsNum;
    }
    public int getTsdl() {
        return this.tsdl;
    }
    public void setTsdl(int tsdl) {
        this.tsdl = tsdl;
    }
    public boolean getIsChecked() {
        return this.isChecked;
    }
    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
