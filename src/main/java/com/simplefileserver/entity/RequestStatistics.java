package com.simplefileserver.entity;

import java.util.Date;

public class RequestStatistics {
    private int requestTimes;
    private  Date time;

    @Override
    public String toString() {
        return "RequestStatistics{" +
                "requestTimes=" + requestTimes +
                ", time=" + time +
                '}';
    }

    public int getRequestTimes() {
        return requestTimes;
    }

    public void setRequestTimes(int requestTimes) {
        this.requestTimes = requestTimes;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
