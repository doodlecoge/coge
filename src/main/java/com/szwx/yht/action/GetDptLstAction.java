package com.szwx.yht.action;

import com.szwx.yht.service.ZhylRespBind;
import com.szwx.yht.service.entity.Depart;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-16
 * Time: 下午3:52
 * To change this template use File | Settings | File Templates.
 */
public class GetDptLstAction extends DataAccessAction {
    private String dptType;

    private List<Depart> departs;

    public String exec() {
        String id = session.get("hospitalId").toString();
        if(dptType == null) dptType = "1";

        departs = ZhylRespBind.findDpList(id, dptType);

        return SUCCESS;
    }

    public String getDptType() {
        return dptType;
    }

    public void setDptType(String dptType) {
        this.dptType = dptType;
    }

    public List<Depart> getDeparts() {
        return departs;
    }
}
