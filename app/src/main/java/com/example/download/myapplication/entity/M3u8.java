package com.example.download.myapplication.entity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class M3u8 implements Serializable {
    private static final long serialVersionUID = 89827041756200370L;

    private float totalDuration;
    private int totalTsNum;
    private List<Ts> tsList;
    private boolean complete;
    private String local;

    public M3u8(List<Ts> tsList) {
        this.tsList = tsList;
    }

    public float getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(float totalDuration) {
        this.totalDuration = totalDuration;
    }

    public int getTotalTsNum() {
        return totalTsNum;
    }

    public void setTotalTsNum(int totalTsNum) {
        this.totalTsNum = totalTsNum;
    }

    public List<Ts> getTsList() {
        return tsList;
    }

    public void setTsList(List<Ts> tsList) {
        this.tsList = tsList;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public boolean saveTs(Ts ts) {
        synchronized (this) {
            if (tsList == null) {
                return false;
            }
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(local);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(M3u8.this);
            } catch (Exception e) {
                tsList.add(ts);
                e.printStackTrace();
            } finally {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            tsList.add(ts);
            return false;
        }
    }

}
