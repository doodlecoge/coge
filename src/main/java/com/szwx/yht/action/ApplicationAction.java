package com.szwx.yht.action;

import com.hch.security.AccessControl;
import com.opensymphony.xwork2.ActionSupport;
import com.szwx.yht.exception.HrsExpression;
import com.szwx.yht.exception.SecurityErrorMessage;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: huaiwang
 * Date: 13-1-23
 * Time: 下午4:28
 * To change this template use File | Settings | File Templates.
 */
public abstract class ApplicationAction extends ActionSupport implements SessionAware {
    private static final Logger log = LoggerFactory.getLogger(ApplicationAction.class);

    protected Map<String, Object> session;

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public static final AccessControl accessControl = new AccessControl();

    @Override
    public String execute() throws Exception {
        String ip = getIp();
        String sessionId = getSessionId();
        String actionName = ServletActionContext.getActionMapping().getName();

        log.debug(actionName + ", " + ip + ", " + sessionId);


        try {
            accessControl.requestAccess(ip, sessionId);
        } catch (HrsExpression e) {
            if (e.getMessage().equals(SecurityErrorMessage.EXCEED_SESSION_PER_IP_LIMIT.name())) {
                if (!"acc_dny".equals(actionName))
                    redirect("acc_dny");
            } else if (e.getMessage().equals(SecurityErrorMessage.QUEUING.name())) {
                if (!"index".equals(actionName))
                    redirect("index");
            }
        }

        String ret = null;

        try {
            ret = this.exec();
        } catch (Exception e) {
            return "ERR";
        }

        return ret;
    }


    protected void responseJson(String json) {
        PrintWriter out = null;
        try {
            out = ServletActionContext.getResponse().getWriter();
        } catch (IOException e) {
        }
        out.write(json.toCharArray());
        out.flush();
        out.close();
    }

    protected void redirect(String str) throws IOException {
        ServletActionContext.getResponse().sendRedirect(str);
    }

    public abstract String exec() throws Exception;

    protected String getSessionId() {
        return ServletActionContext.getRequest().getSession().getId();
    }

    protected String getIp() {
        return ServletActionContext.getRequest().getRemoteAddr();
    }
}
