package com.hch.security;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-24
 * Time: 上午10:13
 * To change this template use File | Settings | File Templates.
 */
public class SmsVerificationHistory {
    private Map<String, Long> his;


    public Map<String, Long> getHis() {
        return his;
    }

    public void setHis(Map<String, Long> his) {
        this.his = his;
    }
}
