package com.szwx.yht.action;

import com.opensymphony.xwork2.ActionSupport;
import com.szwx.yht.Global;
import com.szwx.yht.RegistrationQueue;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

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
    private static Calendar lastUpdateTime = Calendar.getInstance();

    public void queryQueuingPosition() {
        Calendar now = Calendar.getInstance();
        if (now.getTimeInMillis() - lastUpdateTime.getTimeInMillis() > 1000 * 60) {
            ApplicationAction.accessControl.removeStaleSession();
            lastUpdateTime = Calendar.getInstance();
        }

        int order = ApplicationAction.accessControl.getOrder(
                ServletActionContext.getRequest().getSession().getId()
        );

        try {
            responseJson(true, (order + 1 - Global.RegLimit) + "");
        } catch (IOException e) {

        }

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
