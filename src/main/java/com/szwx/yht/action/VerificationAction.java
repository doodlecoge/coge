package com.szwx.yht.action;

import com.hch.security.IDCard;
import com.opensymphony.xwork2.ActionSupport;
import com.szwx.yht.bean.RegPeople;
import com.szwx.yht.exception.ServiceException;
import com.szwx.yht.service.IRegisterService;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-31
 * Time: 上午11:00
 * To change this template use File | Settings | File Templates.
 */
@Controller("verify")
@Scope("session")
public class VerificationAction extends ActionSupport {
    private static final Logger log = LoggerFactory.getLogger(VerificationAction.class);

    private String name;
    private String id;
    private String phone;
    private int medicalType;
    private int chgPhone;

    private String smsCode;
    private String phone2;


    private Map<String, List<String>> fieldErrors;


    @Autowired
    private IRegisterService registerService;


    @Override
    public String execute() throws Exception {
        Map<String, Object> session = ServletActionContext.getContext().getSession();
        session.put("id", id);


        boolean bChangePhone = chgPhone == 1;
        chgPhone = 0;


        fieldErrors = new HashMap<String, List<String>>();

        if (!verifyId()) {
            return "err";
        }


        if (medicalType > 1 || medicalType < 0) medicalType = 0;

        RegPeople regPeople = registerService.getRegPeople(id);

        if (regPeople == null) {
            verifyId();
            verifyName();
            verifyPhone();

            if (fieldErrors.size() > 0) return "err";
            else return "verify_phone";
        } else {
            if (!regPeople.getTrueName().equals(name)) {
                addError("id", "身份证、姓名不匹配");
            }
        }

        if (fieldErrors.size() > 0) {
            return "err";
        }

        if (bChangePhone) {
            return "change_phone";
        }


        return SUCCESS;
    }


    public String verifyNewCode() {
        fieldErrors = new HashMap<String, List<String>>();

        Map<String, Object> session = ServletActionContext.getContext().getSession();
        Object validation_code = session.get("validation_code");
        if (validation_code == null || !validation_code.toString().equals(smsCode)) {
            addError("smsCode", "手机验证码不正确");
            return "err";
        }


        try {
            RegPeople regPeople = registerService.getRegPeople(id);

            if (regPeople != null) {
                log.warn("reg people exists === " + id + ", " + name);
            } else {
                regPeople = new RegPeople();
                regPeople.setIdentityCard(id);
                regPeople.setTrueName(name);
                regPeople.setSex(getSex(id));
                regPeople.setMobile(phone);
                regPeople.setMedicalType(medicalType);

                registerService.saveOrUpdatePeople(regPeople);

                session.remove("validation_code");
            }


        } catch (Exception e) {
            addError("sys", "系统出错" + e.getMessage());
            return "err";
        }

        return SUCCESS;
    }

    public String verifyChangeCode() {
        fieldErrors = new HashMap<String, List<String>>();

        if (phone.equals(phone2)) return SUCCESS;

        Map<String, Object> session = ServletActionContext.getContext().getSession();
        Object validation_code = session.get("validation_code");
        if (validation_code == null || !validation_code.toString().equals(smsCode)) {
            addError("smsCode", "手机验证码不正确");
            return "err";
        }

        try {
            RegPeople regPeople = registerService.getRegPeople(id);

            if (regPeople == null) {
                addError("sys", "用户不存在");
                return "err";
            }

            regPeople.setMedicalType(medicalType);
            regPeople.setMobile(phone2);

            registerService.saveOrUpdatePeople(regPeople);

            session.remove("validation_code");
        } catch (Exception e) {
            addError("sys", "系统出错" + e.getMessage());
            return "err";
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

    private void addError(String key, String val) {
        if (!fieldErrors.containsKey(key)) {
            fieldErrors.put(key, new ArrayList<String>());
        }

        List<String> errLst = fieldErrors.get(key);

        errLst.add(val);
    }

    private boolean verifyId() {
        if (id == null || "".equals(id.trim())) {
            addError("id", "身份证不能为空");
            return false;
        } else {
            if (!new IDCard(id).validate()) {
                addError("id", "身份证信息不正确");
            }
        }

        return true;
    }


    private void verifyName() {
        if (name == null || "".equals(name.trim())) {
            addError("name", "姓名不能为空");
        } else {
            Pattern ptn = Pattern.compile("[^\\u4e00-\\u9fbb]");
            Matcher matcher = ptn.matcher(name);
            if (matcher.find()) {
                addFieldError("name", "姓名必须为中文");
            }
        }
    }

    private void verifyPhone() {
        if (phone == null || "".equals(phone.trim())) {
            addError("phone", "手机不能为空");
        } else {
            Pattern ptn = Pattern.compile("^1((3[4-9]|5[012789]|8[78])|18[09]|(3[0-2]|5[56]|8[56])|[35]3)\\d{8}$");
            Matcher matcher = ptn.matcher(phone);
            if (!matcher.find()) {
                addFieldError("phone", "手机号码格式不正确");
            }
        }
    }


    //******************************************************************
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

    public int getChgPhone() {
        return chgPhone;
    }

    public void setChgPhone(int chgPhone) {
        this.chgPhone = chgPhone;
    }

    public Map<String, List<String>> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, List<String>> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }
}
