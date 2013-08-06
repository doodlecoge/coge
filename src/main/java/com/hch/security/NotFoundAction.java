package com.hch.security;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-20
 * Time: 下午1:56
 * To change this template use File | Settings | File Templates.
 */
public class NotFoundAction extends ActionSupport {
    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
}
