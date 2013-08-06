package com.szwx.yht.entity;

import com.szwx.yht.Global;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-7-6
 * Time: 下午4:13
 * To change this template use File | Settings | File Templates.
 */
public class IpBlockQueue {
    private static IpBlockQueue ins = new IpBlockQueue();
    private Map<String, Calendar> blockedIps = new HashMap<String, Calendar>();

    private IpBlockQueue(){}

    public static IpBlockQueue getInstance() {
        return ins;
    }

    public void enQueue(String ip) {
        blockedIps.put(ip, Calendar.getInstance());
    }

    public void enableAfterSomeTime() {
        Calendar now = Calendar.getInstance();

        for(String ip : blockedIps.keySet()) {
            Calendar cal= blockedIps.get(ip);
            if(cal.getTimeInMillis() + 1000L * Global.NextAllowSeconds < now.getTimeInMillis())
                blockedIps.remove(ip);
        }
    }

    public boolean contains(String ip) {
        return blockedIps.containsKey(ip);
    }
}
