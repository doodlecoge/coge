package com.hch.security;


import com.szwx.yht.exception.HrsExpression;
import com.szwx.yht.exception.SecurityErrorMessage;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-7-7
 * Time: 下午9:33
 * To change this template use File | Settings | File Templates.
 */
public class AccessControl {

    private static final Set<String> sessionSet = new HashSet<String>();
    private static final List<UserAccessInfo> sessionQueue = new ArrayList<UserAccessInfo>();
    private static final Map<String, IpAccessInfo> ipAccessList = new HashMap<String, IpAccessInfo>();


    public void requestAccess(String ip, String sid) {
        removeStaleSession();

        IpAccessInfo ipAccessInfo = ipAccessList.get(ip);

        if (ipAccessInfo == null) {
            IpAccessInfo info = new IpAccessInfo();
            info.setIp(ip);
            info.addSession(sid);
            ipAccessList.put(ip, info);
        } else {
            int num = ipAccessInfo.getSessionNumber();
//            if (num > Config.MaxSessionPerIp)
            if (num > Config.getInt("MaxSessionPerIp"))
                throw new HrsExpression(SecurityErrorMessage.EXCEED_SESSION_PER_IP_LIMIT.name());

            ipAccessInfo.addSession(sid);
        }

        boolean contains = sessionSet.contains(sid);

        if (!contains) {
//            if (sessionSet.size() > Config.QueueSize)
            if (sessionSet.size() > Config.getInt("QueueSize"))
                throw new HrsExpression(
                        SecurityErrorMessage.QUEUING.name(),
                        null,
                        Config.getInt("QueueSize"));
//                        Config.QueueSize);

            sessionQueue.add(new UserAccessInfo(ip, sid));
            sessionSet.add(sid);
        }

        int order = getOrder(sid);
//        if (order >= Config.RegLimit)
        if (order >= Config.getInt("RegLimit"))
            throw new HrsExpression(
                    SecurityErrorMessage.QUEUING.name(),
                    null,
                    order - Config.getInt("RegLimit") + 1);
//                    order - Config.RegLimit + 1);
    }


    public synchronized void removeStaleSession() {
        System.out.println("##########");
        for (int i = 0; i < sessionQueue.size(); i++) {
            UserAccessInfo info = sessionQueue.get(i);
            System.out.println("" + i + ": " + info.getSessionId() + ", " + info.getIp());
        }
        System.out.println("##########");

        int len = sessionQueue.size();
        Long now = Calendar.getInstance().getTimeInMillis();
        long ten_m_ago = now - 1000 * 60 *
                Config.getInt("TimeoutMin");
//                Config.TimeoutMin;

        while (sessionQueue.size() > 0) {
            UserAccessInfo uai = sessionQueue.get(0);
            if (!uai.isTimeout(ten_m_ago)) break;
            sessionSet.remove(uai.getSessionId());
            ipAccessList.get(uai.getIp()).removeSession(uai.getSessionId());
            sessionQueue.remove(0);
        }


        System.out.println("==========");
        for (int i = 0; i < sessionQueue.size(); i++) {
            UserAccessInfo info = sessionQueue.get(i);
            System.out.println("" + i + ": " + info.getSessionId() + ", " + info.getIp());
        }
        System.out.println("==========");
    }

    public int getOrder(String sid) {


        int len = sessionQueue.size();




        for (int i = 0; i < len; i++) {
            UserAccessInfo info = sessionQueue.get(i);
            if (info.getSessionId().equalsIgnoreCase(sid)) return i;
        }

        return -1;
    }

    public void leave(String ip, String sid) {
        sessionSet.remove(sid);
        UserAccessInfo uai = null;
        for (UserAccessInfo info : sessionQueue) {
            if(info.getSessionId().equals(sid)) {
                uai = info;
                break;
            }
        }

        if(uai == null) return;

        sessionQueue.remove(uai);
        ipAccessList.get(uai.getIp()).removeSession(uai.getSessionId());
    }
}
