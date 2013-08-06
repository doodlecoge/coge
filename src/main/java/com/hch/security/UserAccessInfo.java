package com.hch.security;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-7-9
 * Time: 下午7:59
 * To change this template use File | Settings | File Templates.
 */
public class UserAccessInfo {
    private String sessionId;
    private long time;
    private String ip;

    public boolean isTimeout(Long dueTime) {
        return time < dueTime;
    }

    public UserAccessInfo(String ip, String sid) {
        this.ip = ip;
        this.sessionId = sid;
        this.time = Calendar.getInstance().getTimeInMillis();
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
