package com.szwx.yht.action;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-10
 * Time: 下午2:24
 * To change this template use File | Settings | File Templates.
 */
public class Step03PostAction extends DataAccessAction {
    private String rpCode;
    private String startTime;
    private String endTime;

    public String exec() {
        session.put("rpCode", rpCode);
        session.put("startTime", startTime);
        session.put("endTime", endTime);
        return SUCCESS;
    }

    public void setRpCode(String rpCode) {
        this.rpCode = rpCode;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
