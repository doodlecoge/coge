package com.szwx.yht.action;

import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-7-6
 * Time: 下午3:23
 * To change this template use File | Settings | File Templates.
 */
public class AccessDeniedAction extends DataAccessAction {
    public static Map<String, Calendar> ipBlackList = new HashMap<String, Calendar>();

    @Override
    public String exec() throws IOException {
        String ip = ServletActionContext.getRequest().getRemoteAddr();

        if (!ipBlackList.containsKey(ip))
            ServletActionContext.getResponse().sendRedirect("index");

        return SUCCESS;
    }
}
