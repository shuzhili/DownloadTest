package com.example.download.myapplication.entity;

import java.io.Serializable;

public class Ts implements Serializable {

    private static final long serialVersionUID = 8401924340633030676L;

    private String url;
    private String path;
    private long duration;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Ts{" +
                "url='" + url + '\'' +
                ", path='" + path + '\'' +
                ", duration=" + duration +
                '}';
    }
}
