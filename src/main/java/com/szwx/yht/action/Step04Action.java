package com.szwx.yht.action;

import com.hch.security.Config;
import com.szwx.yht.bean.RegPeople;
import com.szwx.yht.bean.RegPipelined;
import com.szwx.yht.common.CommonAction;
import com.szwx.yht.exception.ServiceException;
import com.szwx.yht.service.IRegisterService;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    private String errMsg;

    public String exec() {
        Object rpcode = session.get("rpCode");
        Object oid = session.get("id");
        long rpid = Long.parseLong(rpcode.toString());
        try {
            regPipelined = registerService.getRegPiplinedById(rpid);

            if (!canReg()) {
                errMsg = "每天" + Config.getString("doc_next_day_reg_end_time").trim();
                errMsg += "过后不可预约第二天专家号！";
                return "time_error";
            }

            if(!canReg7th()) {
                errMsg = "每天" + Config.getString("doc_7th_day_reg_start_time").trim();
                errMsg += "前不可预约第七天专家号！";
                return "time_error";
            }

            regPeople = registerService.getRegPeople(oid.toString());
        } catch (ServiceException e) {
        }

        Date date = regPipelined.getWorkSchema().getWorkDate();

        return SUCCESS;
    }

    private boolean canReg() {
        Calendar now = Calendar.getInstance();
        Calendar reg = Calendar.getInstance();
        reg.setTime(regPipelined.getWorkSchema().getWorkDate());

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String endTimeString = Config.getString("doc_next_day_reg_end_time").trim();
        String nowTimeString = sdf.format(now.getTime());
        if (nowTimeString.compareTo(endTimeString) < 0)
            return true;

        int days = reg.get(Calendar.DAY_OF_YEAR) - now.get(Calendar.DAY_OF_YEAR);
        if (days > 1) return true;

        return false;
    }

    private boolean canReg7th() {
        Calendar now = Calendar.getInstance();
        Calendar reg = Calendar.getInstance();
        reg.setTime(regPipelined.getWorkSchema().getWorkDate());

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        String endTimeString = Config.getString("doc_7th_day_reg_start_time").trim();
        String nowTimeString = sdf.format(now.getTime());

        int days = reg.get(Calendar.DAY_OF_YEAR) - now.get(Calendar.DAY_OF_YEAR);

        if(days < 7) return true;

        if (days == 7 && nowTimeString.compareTo(endTimeString) < 0)
            return false;

        return true;
    }

    public RegPipelined getRegPipelined() {
        return regPipelined;
    }

    public RegPeople getRegPeople() {
        return regPeople;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
