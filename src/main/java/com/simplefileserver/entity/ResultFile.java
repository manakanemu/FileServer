package com.simplefileserver.entity;

import java.io.Serializable;

public class ResultFile implements Serializable {
    private String name;
    private String url;
    private int downloadTimes;

    @Override
    public String toString() {
        return "ResultFile{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", downLoadTimes=" + downloadTimes +
                '}';
    }

    public int getDownloadTimes() {
        return downloadTimes;
    }

    public void setDownloadTimes(int downloadTimes) {
        this.downloadTimes = downloadTimes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
