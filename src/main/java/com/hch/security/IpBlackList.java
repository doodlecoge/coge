package com.hch.security;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-7-9
 * Time: 下午8:16
 * To change this template use File | Settings | File Templates.
 */
public class IpBlackList {
    private Map<String, Long> lst = new HashMap<String, Long>();

    public void addBlockedIp(String ip) {
        lst.put(ip, Calendar.getInstance().getTimeInMillis());
    }

    public boolean isIpBlocked(String ip) {
        return lst.containsKey(ip);
    }

    public void refresh() {
        Long now = Calendar.getInstance().getTimeInMillis();
        now -= 1000 * 60 * 10;

        for(String ip : lst.keySet()) {
            Long ts = lst.get(ip);
            if(ts < now) lst.remove(ip);
        }
    }
}
