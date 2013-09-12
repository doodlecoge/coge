package com.szwx.yht.action;

import com.hch.security.AccessControl;
import com.opensymphony.xwork2.ActionSupport;
import com.szwx.yht.QueuingUser;
import com.szwx.yht.RegistrationQueue;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-7-6
 * Time: 上午11:16
 * To change this template use File | Settings | File Templates.
 */
public class IndexAction extends ActionSupport {
    private static final Logger log = LoggerFactory.getLogger(IndexAction.class);
    public static final String cookieName = "ts";

    @Override
    public String execute() {
        RegistrationQueue regQueue = RegistrationQueue.getInstance();

        HttpServletRequest request = ServletActionContext.getRequest();

        String id = request.getSession().getId();
        String ip = request.getRemoteAddr();

        Cookie cookie = getCookie();

        if (cookie != null) {
            String oid = cookie.getValue();

            try {
                if (!id.equals(oid) && AccessControl.sessionSet.contains(oid)) {
                    log.debug("valid data, allow access");
                    ApplicationAction.accessControl.leave(ip, oid);
                    ApplicationAction.accessControl.requestAccess(ip, id);
                } else {
                    ApplicationAction.accessControl.requestAccess(ip, id);
                }
            } catch (Exception e) {
            }
        } else {
            Cookie newCookie = new Cookie(cookieName, id);
            newCookie.setMaxAge(600);
            ServletActionContext.getResponse().addCookie(newCookie);
        }

        return SUCCESS;
    }

    private Cookie getCookie() {
        HttpServletRequest request = ServletActionContext.getRequest();
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) return cookie;
        }
        return null;
    }
}
