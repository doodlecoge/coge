package com.szwx.yht.action;

import com.szwx.yht.bean.RegPeople;
import com.szwx.yht.common.CommonAction;
import com.szwx.yht.exception.ActionException;
import com.szwx.yht.exception.ServiceException;
import com.szwx.yht.service.IRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-10
 * Time: 下午1:43
 * To change this template use File | Settings | File Templates.
 */
//public class Step01PostAction extends DataAccessAction {
@SuppressWarnings("serial")
@Controller("step01_post_action")
public class Step01PostAction extends DataAccessAction {
    private static final Logger log = LoggerFactory.getLogger(Step01PostAction.class);
    @Autowired
    private IRegisterService registerService;

    private static final Pattern phonePtn = Pattern.compile(
            "^1((3[4-9]|5[012789]|8[78])|18[09]|(3[0-2]|5[56]|8[56])|[35]3)\\d{8}$"
    );

    private String id;
    private String name;
    private String phone;
    private String newPhone;
    private String checkCode;
    private int medicalType;
    private boolean chgPhone;


    private int ttt; // 0: exists, 1: exists & change phone, 2: not exists

    public String execute() {
        log.error(System.currentTimeMillis() + "-a");

        session.put("id", id);
        session.put("name", name);
        session.put("phone", phone);

        if(chgPhone) session.put("phone", newPhone);


        try {
            log.error(System.currentTimeMillis() + "-b");
            RegPeople regPeople = registerService.getRegPeople(id);
            log.error(System.currentTimeMillis() + "-c");

            if (regPeople == null) {
                regPeople = new RegPeople();
                regPeople.setIdentityCard(id);
                regPeople.setTrueName(name);
                regPeople.setSex(getSex(id));
            }

            if (phone != null && !"".equals(phone.trim())) regPeople.setMobile(phone);
            regPeople.setMedicalType(medicalType);
            log.error(System.currentTimeMillis() + "-d");
            registerService.saveOrUpdatePeople(regPeople);
            log.error(System.currentTimeMillis() + "-e");

        } catch (ServiceException e) {
            session.put("ERR", e);
            return ERROR;
        }

        return SUCCESS;
    }

    private int getSex(String id) {
        if (id == null) return 0;

        char c = ' ';

        if (id.length() == 15) c = id.charAt(14);
        else if (id.length() == 18) c = id.charAt(16);

        if (c == ' ') return 0;
        else return (c - '0') % 2 == 0 ? 2 : 1;
    }


    public void validate() {
        if(id == null) id = "";
        if(name == null) name = "";
        if(phone == null) phone = "";
        if(newPhone == null) newPhone = "";
        if(checkCode == null) checkCode = "";


        clearActionErrors();
        clearErrors();
        RegPeople regPeople = null;
        long num = 0;

        try {
            regPeople = registerService.getRegPeople(id.toString());
            if (regPeople != null) num = registerService.queryQuitNum(regPeople.getIdentityCard()).getState();
        } catch (ServiceException e) {
            log.error("", e);
        }

        Object validation_code = session.get("validation_code");

        if(regPeople == null) {
            ttt = 2;
        } else {
            if(chgPhone) {
                ttt = 1;
            } else ttt = 0;
        }

        if ((regPeople == null || chgPhone) && !checkCode.equals(validation_code)) {
            addFieldError("vc", "手机短信验证码不正确");
        }

        if (num > 1) {
            addFieldError("id", "您本周爽约" + num + "次，被计入黑名单，不能预约挂号！");
        }

        // 身份证
        Pattern ptn = Pattern.compile("^(\\d{15}|\\d{18}|\\d{17}[xX])$");
        Matcher matcher = ptn.matcher(id);

        if (!matcher.find()) {
            addFieldError("id", "身份证号码格式不正确");
        }

        // 姓名
        if("".equals(name)) {
            addFieldError("name", "姓名不能为空");
        }
        ptn = Pattern.compile("[^\\u4e00-\\u9fbb]");
        matcher = ptn.matcher(name);
        if (matcher.find()) {
            addFieldError("name", "姓名必须为中文");
        }

//        try {
//            regPeople = registerService.getRegPeople(id);
//        } catch (ServiceException e) {
//            log.error("", e);
//        }


        // 手机
        matcher = phonePtn.matcher(phone);
        if (!matcher.find()) {
            addFieldError("phone", "手机号码格式不正确");
        }

        matcher = phonePtn.matcher(newPhone);
        if (!matcher.find()) {
            addFieldError("newPhone", "新手机号码格式不正确");
        }

        if (regPeople == null) return;

        if (!regPeople.getTrueName().equals(name)) {
            addFieldError("id-name", "身份证、姓名不匹配");
        }

    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNewPhone() {
        return newPhone;
    }

    public void setNewPhone(String newPhone) {
        this.newPhone = newPhone;
    }

    public int getMedicalType() {
        return medicalType;
    }

    public void setMedicalType(int medicalType) {
        this.medicalType = medicalType;
    }

    public boolean isChgPhone() {
        return chgPhone;
    }

    public void setChgPhone(boolean chgPhone) {
        this.chgPhone = chgPhone;
    }

    public int getTtt() {
        return ttt;
    }
}
