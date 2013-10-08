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
        int type = Integer.valueOf(session.get("regType").toString());


        if (type == 2) {
            try {
                regPipelined = registerService.getRegPiplinedById(rpid);

                if (!canReg()) {
                    errMsg = "每天" + Config.getString("doc_next_day_reg_end_time").trim();
                    errMsg += "过后不可预约第二天专家号！";
                    return "time_error";
                }

                if (!canReg7th()) {
                    errMsg = "每天" + Config.getString("doc_7th_day_reg_start_time").trim();
                    errMsg += "前不可预约第七天专家号！";
                    return "time_error";
                }

                if(!inDocWorkTime()) {
                    errMsg = "每天只能在" + Config.getString("doc_reg_time_window").trim();
                    errMsg += "间预约专家号！";
                    return "time_error";
                }

                regPeople = registerService.getRegPeople(oid.toString());
            } catch (ServiceException e) {
            }
        } else if (type == 1) {
            if (!canRegRt()) {
                errMsg = "不在预约时间段，无法预约。可预约时间段为：" + Config.getString("real_time_reg_time_window").trim() + "。";

                return "time_error";
            } else {
                try {
                    regPipelined = registerService.getRegPiplinedById(rpid);
                    regPeople = registerService.getRegPeople(oid.toString());
                } catch (ServiceException e) {
                }
            }
        }


        return SUCCESS;
    }


    private boolean inDocWorkTime() {
        String str = Config.getString("doc_reg_time_window");
        String[] strs = str.split("-");
        String start = strs[0].trim();
        String end = strs[1].trim();

        if(start.length() == 4) {
            start = "0" + start;
        }

        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String nowString = sdf.format(now.getTime());


        return nowString.compareTo(start) >= 0 && nowString.compareTo(end) <= 0;
    }

    private boolean canRegRt() {
        String real_time_reg_time_window = Config.getString("real_time_reg_time_window").trim();
        String[] wins = real_time_reg_time_window.split(",");

        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String nowStr = sdf.format(now.getTime());

        for (String win : wins) {
            String[] ends = win.split("-");

            if ((nowStr.compareTo(ends[0]) > 0 && nowStr.compareTo(ends[1]) < 0)) {
                return true;
            }
        }

        return false;
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

        if (days < 7) return true;

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
