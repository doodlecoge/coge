package com.szwx.yht;

import com.szwx.yht.action.ApplicationAction;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-7-2
 * Time: 下午9:38
 * To change this template use File | Settings | File Templates.
 */
public class QueuingListener implements HttpSessionListener {
    public static final RegistrationQueue RegQueue = RegistrationQueue.getInstance();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
//        System.out.println("enqueue: " + httpSessionEvent.getSession().getId());
//        RegQueue.enQueue(httpSessionEvent.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
//        System.out.println("dequeue: " + httpSessionEvent.getSession().getId());
//        RegQueue.deQueue(httpSessionEvent.getSession().getId());

        String sid = httpSessionEvent.getSession().getId();
        String ip = ServletActionContext.getRequest().getRemoteAddr();
        ApplicationAction.accessControl.leave(ip, sid);
    }
}
