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
@Scope("session")
public class Step01PostAction extends DataAccessAction {
    private static final Logger log = LoggerFactory.getLogger(Step01PostAction.class);
    @Autowired
    private IRegisterService registerService;

    private String code;
    private String name;
    private String id;
    private String phone;
    private int medicalType;

    public String execute() {
        log.error(System.currentTimeMillis() + "-a");

        session.put("id", id);
        session.put("name", name);
        session.put("phone", phone);

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
        clearActionErrors();
        clearErrors();
        RegPeople regPeople = null;
        long num = 0;
        try {
            regPeople = registerService.getRegPeople(id.toString());
            if(regPeople != null) num = registerService.queryQuitNum(regPeople.getIdentityCard()).getState();
        } catch (ServiceException e) {
            log.error("", e);
        }

        if(num > 1) {
            addFieldError("id", "您本周爽约" + num + "次，被计入黑名单，不能预约挂号！");
        }

        if (id == null || "".equals(id)) {
            addFieldError("id", "身份证号码必填");
        }

        if (name == null || "".equals(name)) {
            addFieldError("name", "姓名必填");
        }

//        if (phone == null || "".equals(phone)) {
//            addFieldError("phone", "手机必填");
//        }

        Pattern ptn = Pattern.compile("^(\\d{15}|\\d{18}|\\d{17}[xX])$");
        Matcher matcher = ptn.matcher(id);

        if (!matcher.find()) {
            addFieldError("id", "身份证号码格式不正确");
        }

        ptn = Pattern.compile("[^\\u4e00-\\u9fbb]");
        matcher = ptn.matcher(name);
        if (matcher.find()) {
            addFieldError("name", "姓名必须为中文");
        }

        try {
            regPeople = registerService.getRegPeople(id);
        } catch (ServiceException e) {
            log.error("", e);
        }


        if (phone == null || "".equals(phone)) {
            if (regPeople == null) addFieldError("phone", "手机必填");
        } else {
            ptn = Pattern.compile("^1((3[4-9]|5[012789]|8[78])|18[09]|(3[0-2]|5[56]|8[56])|[35]3)\\d{8}$");
            matcher = ptn.matcher(phone);
            if (!matcher.find()) {
                addFieldError("phone", "手机号码格式不正确");
            }
        }

        if (regPeople == null) return;

        if (!regPeople.getTrueName().equals(name)) {
            addFieldError("id-name", "身份证、姓名不匹配");
        }

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public int getMedicalType() {
        return medicalType;
    }

    public void setMedicalType(int medicalType) {
        this.medicalType = medicalType;
    }
}
