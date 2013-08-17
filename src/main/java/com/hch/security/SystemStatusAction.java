package com.hch.security;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.velocity.tools.generic.DateTool;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-17
 * Time: 上午10:35
 * To change this template use File | Settings | File Templates.
 */

public class SystemStatusAction extends ActionSupport {
    private List<UserAccessInfo> sessionQueue;
    private DateTool date;
    @Override
    public String execute() throws Exception {
        sessionQueue = AccessControl.sessionQueue;
        date = new DateTool();
        return SUCCESS;
    }

    public List<UserAccessInfo> getSessionQueue() {
        return sessionQueue;
    }

    public void setSessionQueue(List<UserAccessInfo> sessionQueue) {
        this.sessionQueue = sessionQueue;
    }

    public DateTool getDate() {
        return date;
    }

    public void setDate(DateTool date) {
        this.date = date;
    }
}
