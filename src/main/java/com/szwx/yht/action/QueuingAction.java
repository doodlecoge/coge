package com.szwx.yht.action;

import com.opensymphony.xwork2.ActionSupport;
import com.szwx.yht.QueuingUser;
import com.szwx.yht.RegistrationQueue;
import org.apache.struts2.ServletActionContext;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-7-6
 * Time: 上午11:16
 * To change this template use File | Settings | File Templates.
 */
public class QueuingAction extends ActionSupport {
    @Override
    public String execute() {
        RegistrationQueue regQueue = RegistrationQueue.getInstance();

        String id = ServletActionContext.getRequest().getSession().getId();

        regQueue.moveToLast(id);

        return SUCCESS;
    }
}
