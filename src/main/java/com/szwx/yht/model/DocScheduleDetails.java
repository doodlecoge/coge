package com.szwx.yht.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-20
 * Time: 下午6:31
 * To change this template use File | Settings | File Templates.
 */
public class DocScheduleDetails {
    private int state;
    private BigDecimal apm;
    private BigDecimal regCode;
    private Date datetime;


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public BigDecimal getApm() {
        return apm;
    }

    public void setApm(BigDecimal apm) {
        this.apm = apm;
    }

    public BigDecimal getRegCode() {
        return regCode;
    }

    public void setRegCode(BigDecimal regCode) {
        this.regCode = regCode;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
