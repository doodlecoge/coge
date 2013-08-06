package com.szwx.yht.action;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-10
 * Time: 下午2:24
 * To change this template use File | Settings | File Templates.
 */
public class Step02PostAction extends DataAccessAction {
    private String hospitalId;

    public String exec() {
        session.put("hospitalId", hospitalId);

        return SUCCESS;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }
}
