package com.szwx.yht.entity;

import com.szwx.yht.RegistrationQueue;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-7-6
 * Time: 下午4:48
 * To change this template use File | Settings | File Templates.
 */
public class IpLimit {
    private Map<String, Map<String, Calendar>> lst = new HashMap<String, Map<String, Calendar>>();

    private static IpLimit ins = new IpLimit();

    private IpLimit() {
    }

    public static IpLimit getInstance() {
        return ins;
    }

    public void clear() {
        RegistrationQueue regQueue = RegistrationQueue.getInstance();
        for (String ip : lst.keySet()) {
            Map<String, Calendar> sessionLst = lst.get(ip);
            for (String sid : sessionLst.keySet()) {
                if (!regQueue.contains(sid)) sessionLst.remove(sid);
            }
        }
    }

    public boolean canAccess(String ip, String sessionId) {
        if(lst.containsKey(ip)) {
            if(lst.get(ip).containsKey(sessionId)) return true;
            else {

            }
        } else {

            return true;
        }
        return false;
    }
}
