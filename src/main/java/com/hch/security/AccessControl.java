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

    public static final Set<String> sessionSet = new HashSet<String>();
    public static final List<UserAccessInfo> sessionQueue = new ArrayList<UserAccessInfo>();
    public static final Map<String, IpAccessInfo> ipAccessList = new HashMap<String, IpAccessInfo>();


    public synchronized void requestAccess(String ip, String sid) {
        removeStaleSession();

        IpAccessInfo ipAccessInfo = ipAccessList.get(ip);

        if (ipAccessInfo == null) {
            IpAccessInfo info = new IpAccessInfo();
            info.setIp(ip);
            info.addSession(sid);
            ipAccessList.put(ip, info);
        } else {
            if (!sessionSet.contains(sid)) {
                int num = ipAccessInfo.getSessionNumber();

                if (num > Config.getInt("MaxSessionPerIp"))
                    throw new HrsExpression(SecurityErrorMessage.EXCEED_SESSION_PER_IP_LIMIT.name());

                ipAccessInfo.addSession(sid);
            }
        }

        boolean contains = sessionSet.contains(sid);

        if (!contains) {
            if (sessionSet.size() > Config.getInt("QueueSize"))
                throw new HrsExpression(
                        SecurityErrorMessage.QUEUING.name(),
                        null,
                        Config.getInt("QueueSize"));

            sessionQueue.add(new UserAccessInfo(ip, sid));
            sessionSet.add(sid);
        }

        int order = getOrder(sid);
        if (order >= Config.getInt("RegLimit"))
            throw new HrsExpression(
                    SecurityErrorMessage.QUEUING.name(),
                    null,
                    order - Config.getInt("RegLimit") + 1);
    }


    public synchronized void removeStaleSession() {
        int len = sessionQueue.size();
        Long now = Calendar.getInstance().getTimeInMillis();
        long ten_m_ago = now - 1000 * 60 *
                Config.getInt("TimeoutMin");

        while (sessionQueue.size() > 0) {
            UserAccessInfo uai = sessionQueue.get(0);
            if (!uai.isTimeout(ten_m_ago)) break;
            sessionSet.remove(uai.getSessionId());
            ipAccessList.get(uai.getIp()).removeSession(uai.getSessionId());
            sessionQueue.remove(0);
        }
    }

    public int getOrder(String sid) {
        int len = sessionQueue.size();

        for (int i = 0; i < len; i++) {
            UserAccessInfo info = sessionQueue.get(i);
            if (info.getSessionId().equalsIgnoreCase(sid)) return i;
        }

        return Config.getInt("QueueSize");
    }

    public void leave(String ip, String sid) {
        sessionSet.remove(sid);

        UserAccessInfo uai = null;

        for (UserAccessInfo info : sessionQueue) {
            if (!info.getSessionId().equals(sid)) continue;

            uai = info;
            break;
        }

        if (uai == null) return;

        sessionQueue.remove(uai);

        IpAccessInfo ipAccessInfo = ipAccessList.get(uai.getIp());

        if(ipAccessInfo == null) return;

        ipAccessInfo.removeSession(uai.getSessionId());
        if (ipAccessInfo.getSessionNumber() == 0) {
            ipAccessList.remove(uai.getIp());
        }
    }
}
