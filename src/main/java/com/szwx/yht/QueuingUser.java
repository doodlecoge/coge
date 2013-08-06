package com.szwx.yht;

import com.szwx.yht.action.AccessDeniedAction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-7-2
 * Time: 下午9:43
 * To change this template use File | Settings | File Templates.
 */
public class QueuingUser {
    private String sessionId;
    private Calendar time;
    private List<Calendar> accHis = new ArrayList<Calendar>();

    public void access() {
        clearOld();

        accHis.add(Calendar.getInstance());
    }

    public boolean tooManyRequests() {
        return accHis.size() > Global.MaxAccessPerMin;
    }

    private void clearOld() {
        long oneMin = 60000l;
        int len = accHis.size();
        Calendar now = Calendar.getInstance();

        for (int i = 0; i < len; i++) {
            if (accHis.get(i).getTimeInMillis() + oneMin < now.getTimeInMillis()) {
                accHis.remove(i);
            }
        }
    }


    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public List<Calendar> getAccHis() {
        return accHis;
    }

    public void setAccHis(List<Calendar> accHis) {
        this.accHis = accHis;
    }


}
