package com.szwx.yht.action;

import com.hch.security.Config;
import com.opensymphony.xwork2.ActionSupport;
import com.szwx.yht.Global;
import com.szwx.yht.RegistrationQueue;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
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
//public class AsyncAction extends DataAccessAction {
public class AsyncAction extends ActionSupport {
    private static final Logger log = LoggerFactory.getLogger(AsyncAction.class);
    private static Calendar lastUpdateTime = Calendar.getInstance();

    public void queryQueuingPosition() {
        long st = System.currentTimeMillis();
        Calendar now = Calendar.getInstance();
        if (now.getTimeInMillis() - lastUpdateTime.getTimeInMillis() > 1000 * 60) {
            ApplicationAction.accessControl.removeStaleSession();
            lastUpdateTime = Calendar.getInstance();
        }

        ApplicationAction.accessControl.requestAccess(
                ServletActionContext.getRequest().getRemoteAddr(),
                ServletActionContext.getRequest().getSession().getId()
        );

        int order = ApplicationAction.accessControl.getOrder(
                ServletActionContext.getRequest().getSession().getId()
        );

        try {
            responseJson(true, (order + 1 - Config.getInt("RegLimit")) + "");
        } catch (IOException e) {
            log.error("-", e);
        }
        long et = System.currentTimeMillis();

        log.debug("use time: " + (et - st));

    }

    public void getPhone() {
        String name = getReqValue("name");
        String id = getReqValue("id");

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

    private String getReqValue(String key) {
        HttpServletRequest request = ServletActionContext.getRequest();
        String val = request.getParameter(key);
        return val;
    }

}
