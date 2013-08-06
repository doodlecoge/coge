package com.szwx.yht.service.entity;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-16
 * Time: 上午10:50
 * To change this template use File | Settings | File Templates.
 */
public class Depart {
    private String code;
    private String name;

    public Depart(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
