package com.szwx.yht.action;

import com.szwx.yht.bean.RegPeople;
import com.szwx.yht.bean.RegPipelined;
import com.szwx.yht.common.CommonAction;
import com.szwx.yht.exception.ServiceException;
import com.szwx.yht.service.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-10
 * Time: 下午2:27
 * To change this template use File | Settings | File Templates.
 */

@SuppressWarnings("serial")
@Controller("step04_action")
@Scope("session")
//public class Step04Action extends CommonAction {
public class Step04Action extends DataAccessAction {

    @Autowired
    private IRegisterService registerService;

    private RegPipelined regPipelined;
    private RegPeople regPeople;

    public String exec() {
        Object rpcode = session.get("rpCode");
        Object oid = session.get("id");
        long rpid = Long.parseLong(rpcode.toString());
        try {
            regPipelined = registerService.getRegPiplinedById(rpid);
            regPeople = registerService.getRegPeople(oid.toString());
        } catch (ServiceException e) {
        }
        return SUCCESS;
    }

    public RegPipelined getRegPipelined() {
        return regPipelined;
    }

    public RegPeople getRegPeople() {
        return regPeople;
    }
}
