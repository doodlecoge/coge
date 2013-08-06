package com.szwx.yht.action;

import com.szwx.yht.QueuingUser;
import com.szwx.yht.RegistrationQueue;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-7-6
 * Time: 上午11:16
 * To change this template use File | Settings | File Templates.
 */
public class QueuingAction extends DataAccessAction {
    @Override
    public String exec() {
        RegistrationQueue regQueue = RegistrationQueue.getInstance();

//        QueuingUser queuingUser = regQueue.deQueue(getSessionId());
//        regQueue.enQueue(getSessionId());

        regQueue.moveToLast(getSessionId());

        return SUCCESS;
    }
}
