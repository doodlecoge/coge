package com.szwx.yht.action;

import com.szwx.yht.service.ZhylRespBind;
import com.szwx.yht.service.entity.Depart;
import com.szwx.yht.util.Hospital;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-10
 * Time: 下午2:27
 * To change this template use File | Settings | File Templates.
 */
public class Step03Action extends DataAccessAction {
    private String dptType;
    private String hospitalName;


    public String exec() {
        String id = session.get("hospitalId").toString();

        hospitalName = Hospital.getNameByCode(id);

        return SUCCESS;
    }

    public void setDptType(String dptType) {
        this.dptType = dptType;
    }

    public String getHospitalName() {
        return hospitalName;
    }

}
