package com.szwx.yht.action;

import com.hch.security.Config;
import com.sms.SmsRequest;
import com.sms.SmsRequestBody;
import com.sms.SmsRequestHeader;
import com.sms.SmsUtils;
import com.szwx.yht.Global;
import com.szwx.yht.bean.*;
import com.szwx.yht.dto.MethodCallBean;
import com.szwx.yht.exception.ServiceException;
import com.szwx.yht.service.IRegisterService;
import com.szwx.yht.service.IUserService;
import com.szwx.yht.util.RealTimeReserve;
import com.szwx.yht.util.SendMsgService;
import com.szwx.yht.util.SendMsgUtil;
import com.szwx.yht.util.TimeUtil;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-10
 * Time: 下午2:24
 * To change this template use File | Settings | File Templates.
 */


//public class Step04PostAction extends CommonAction {
public class Step04PostAction extends DataAccessAction {


    @Autowired
    private IRegisterService registerService;

    @Autowired
    private IUserService userService;

    private MethodCallBean call;
    private RegOrder regOrder = new RegOrder();
    private boolean showCode;


    private static Calendar lastRegTime = Calendar.getInstance();

    private List<Long> regHis = new ArrayList<Long>();

    public String exec() {
//        Calendar now = Calendar.getInstance();
//        if (now.getTimeInMillis() - lastRegTime.getTimeInMillis() < Config.getInt("RegGapSec") * 1000) {
//            return "wait";
//        } else lastRegTime = now;



        long now = Calendar.getInstance().getTimeInMillis();
        long boundary = now - 60000;

        int cnt = 0;

        while (regHis.size() > 0) {
            if (regHis.get(0) < boundary) regHis.remove(0);
            else break;
        }


        int len = regHis.size();


        if (len > Config.getInt("reg_limit_per_min")) {
            return "wait";
        }


        Object rpCode = session.get("rpCode");
        Object id = session.get("id");

        if (call == null) call = new MethodCallBean();

        if (id == null || rpCode == null) {
            call.setMsg(false, "非法访问！");
            return "saveRegisterSuccess";
        }


        RegPeople regPeople = null;
        try {
            regPeople = registerService.getRegPeople(id.toString());
            long num = registerService.queryQuitNum(regPeople.getIdentityCard()).getState();

            if (num > 1) {
                call.setMsg(false, "您已存在" + num + "次爽约记录，不能进行预约挂号。");
                return "saveRegisterSuccess";
            }
        } catch (ServiceException e) {
            call.setMsg(false, "程序出错");
            return "saveRegisterSuccess";
        }


        try {

            regOrder = new RegOrder();

            RegPipelined regPipelined = registerService.getRegPiplinedById(Long.parseLong(rpCode.toString()));

            regOrder.setRegPeople(regPeople);
            regOrder.setTreatPeople(regPeople);
            regOrder.setRegPipelined(regPipelined);
            regOrder.setStateTime(TimeUtil.formatDate3(session.get("startTime").toString(), "HH:mm"));
            regOrder.setEndTime(TimeUtil.formatDate3(session.get("endTime").toString(), "HH:mm"));
            regOrder.setState(1);


            User user = (User) commonDao.get(User.class, Global.UserId);
            regOrder.setOpeateESQ(user);

            int age = getAge(regOrder.getRegPeople().getIdentityCard());
            if (age < 16 && regOrder.getMedicalType() == 0)
                showCode = true;


            //过滤就诊儿科满16周岁的病友

            Integer havaPj = RealTimeReserve.getHospitalCode(regOrder);


            if (havaPj.intValue() == 1) {
                call.setMsg(false, "为了避免影响医生就诊，16周岁以上的不可以预约儿科");
                return "saveRegisterSuccess";
            } else if (havaPj.intValue() == 2) {
                call.setMsg(false, "系统出错，请联系管理员");
                return "saveRegisterSuccess";
            }
            //3点之后，过滤中医院和附二院明天的挂号

            Integer overTime = RealTimeReserve.isOverTime(regOrder);

            if (overTime.intValue() == 1) {
                call.setMsg(false, "时间已过3点");
                return "saveRegisterSuccess";
            } else if (overTime.intValue() == 2) {
                call.setMsg(false, "系统出错，请联系管理员");
                return "saveRegisterSuccess";
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        try {

            call = registerService.saveRegister(regOrder);


        } catch (ServiceException e) {
            e.printStackTrace();
            registerService.updateRegisterByError(regOrder.getYLId() + "");
            call.setMsg(false, "程序出错");
            return "saveRegisterSuccess";
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            registerService.updateRegisterByError(regOrder.getYLId() + "");
            call.setMsg(false, "运行出错");
            return "saveRegisterSuccess";
        } catch (Exception exx) {
            exx.printStackTrace();
            registerService.updateRegisterByError(regOrder.getYLId() + "");
            call.setMsg(false, "系统出错");
            return "saveRegisterSuccess";
        }

        if (call.isSuccess()) {
            regHis.add(now);
            call.setMsg(regOrder.getCode() + "");
            accessControl.leave(getIp(), getSessionId());


            Cookie cookie = new Cookie(IndexAction.cookieName, "");
            cookie.setMaxAge(0);
            ServletActionContext.getResponse().addCookie(cookie);
        }


        return "saveRegisterSuccess";
    }


    public int getAge(String id) {
        String birth = id.length() == 15 ? "19" + id.substring(6, 12) : id.substring(6, 14);
        Date date = null;
        try {
            date = DateUtils.parseDate(birth, new String[]{"yyyyMMdd"});
        } catch (DateParseException e) {
            return 0;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        Calendar now = Calendar.getInstance();

        int dy = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
        int dm = now.get(Calendar.MONTH) - cal.get(Calendar.MONTH);
        int dd = now.get(Calendar.DAY_OF_YEAR) - cal.get(Calendar.DAY_OF_YEAR);

        if (dm > 0 || dm == 0 && dd >= 0) {
            return dy;
        } else return dy - 1;
    }

    public RegOrder getRegOrder() {
        return regOrder;
    }

    public MethodCallBean getCall() {
        return call;
    }

    public boolean isShowCode() {
        return showCode;
    }

    public void setShowCode(boolean showCode) {
        this.showCode = showCode;
    }
}
