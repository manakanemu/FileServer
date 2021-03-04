package com.simplefileserver.entity;

import java.io.Serializable;

public class ResultInfo implements Serializable {
    private String msg;
    private int status;

    @Override
    public String toString() {
        return "ResultInfo{" +
                "msg='" + msg + '\'' +
                ", status=" + status +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
