package com.szwx.yht.action;

import com.szwx.yht.Global;
import com.szwx.yht.RegistrationQueue;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-7-6
 * Time: 上午11:21
 * To change this template use File | Settings | File Templates.
 */
public class AsyncAction extends DataAccessAction {
//    private static int visits = 0;
    private static Calendar lastUpdateTime = Calendar.getInstance();

    private String t;

    public void queryQueuingPosition() {
        Calendar now = Calendar.getInstance();
        if(now.getTimeInMillis() - lastUpdateTime.getTimeInMillis() > 1000 * 60) {
            accessControl.removeStaleSession();
            lastUpdateTime = Calendar.getInstance();
        }

        int order = accessControl.getOrder(getSessionId());

        try {
            responseJson(true, (order + 1 - Global.RegLimit) + "");
        } catch (IOException e) {

        }

//        synchronized (this) {
//            visits++;
//        }
//
//        RegistrationQueue regQueue = RegistrationQueue.getInstance();
//
//        if(visits > regQueue.size() * 20) {
//            regQueue.clearStaleQueuingUsers();
//            visits = 0;
//        }
//
//        int order = regQueue.getOrder(getSessionId());
//
//        order = order == -1 ? Global.QueueSize : order;
//
//        try {
//            responseJson(true, (order + 1 - Global.RegLimit) + "");
//        } catch (IOException e) {
//        }

        System.out.println(">>> " + order);
    }

    private void responseJson(boolean success, String data) throws IOException {
        JSONObject jobj = new JSONObject();

        jobj.put("success", success);
        jobj.put("data", data);

        PrintWriter out = ServletActionContext.getResponse().getWriter();
        out.write(jobj.toString().toCharArray());
        out.flush();
        out.close();
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }
}
