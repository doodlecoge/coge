package com.szwx.yht;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-7-2
 * Time: 下午9:44
 * To change this template use File | Settings | File Templates.
 */
public class RegistrationQueue {
    private static final RegistrationQueue ins = new RegistrationQueue();

    private List<QueuingUser> Queue = new ArrayList<QueuingUser>();
    private Set<String> Ids = new HashSet<String>();

    private RegistrationQueue() {
    }

    public static RegistrationQueue getInstance() {
        return ins;
    }

    public boolean contains(String sessionId) {
        return Ids.contains(sessionId);
    }


    public synchronized void moveToLast(String sesstionId) {
        if(!Ids.contains(sesstionId)) {
            enQueue(sesstionId);

        } else {
            int idx = -1;

            for (int i = 0; i < Queue.size(); i++) {
                if(Queue.get(i).getSessionId().equals(sesstionId)) {
                    idx = i;
                    break;
                }
            }

            if(idx != -1) {
                QueuingUser qu = Queue.get(idx);
                Queue.add(qu);
            }
        }
    }



    public synchronized QueuingUser enQueue(String sessionId) {
        if(Queue.size() > Global.QueueSize) return null;

        boolean contains = Ids.contains(sessionId);

        if (!contains) {
            QueuingUser queuingUser = new QueuingUser();
            queuingUser.setSessionId(sessionId);
            queuingUser.setTime(Calendar.getInstance());
            Queue.add(queuingUser);
            Ids.add(sessionId);

            return queuingUser;
        } else {
            for (QueuingUser queuingUser : Queue) {
                if(queuingUser.getSessionId().equals(sessionId))
                    return queuingUser;
            }
        }

        return null;
    }

    public synchronized QueuingUser deQueue(String sessionId) {
        for (QueuingUser queuingUser : Queue) {
            if (sessionId.equals(queuingUser.getSessionId())) {
                Queue.remove(queuingUser);
                Ids.remove(sessionId);
                return queuingUser;
            }
        }
        return null;
    }

    public synchronized int getOrder(String sessionId) {
        if (!Ids.contains(sessionId)) enQueue(sessionId);

        int size = Queue.size();

        for (int i = 0; i < size; i++) {
            if (sessionId.equals(Queue.get(i).getSessionId())) {
                return i;
            }
        }

        return -1;
    }

    public synchronized void clearStaleQueuingUsers() {
        Calendar ago = Calendar.getInstance();
        ago.add(Calendar.MINUTE, -Global.TimeoutMin);

        List<QueuingUser> lst = new ArrayList<QueuingUser>();

        for (QueuingUser queuingUser : Queue) {
            if (ago.getTimeInMillis() > queuingUser.getTime().getTimeInMillis()) {
                lst.add(queuingUser);
            }
        }

        Queue.removeAll(lst);

        for (QueuingUser queuingUser : lst) {
            Ids.remove(queuingUser.getSessionId());
        }
    }

    public int size() {
        return Queue.size();
    }

    public String toString() {
        String str = "";

        for (QueuingUser queuingUser : Queue) {
            str += queuingUser.getSessionId() + ", ";
        }

        return str;
    }
}
