package com.hch.security;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-7-9
 * Time: 下午8:01
 * To change this template use File | Settings | File Templates.
 */
public class IpAccessInfo {
    private String ip;
    private Set<String> sessions = new HashSet<String>();

    public void addSession(String sid) {
        sessions.add(sid);
    }

    public void removeSession(String sid) {
        sessions.remove(sid);
    }

    public int getSessionNumber() {
        return sessions.size();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
