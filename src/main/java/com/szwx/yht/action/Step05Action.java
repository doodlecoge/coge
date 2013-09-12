package com.szwx.yht.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sms.*;
import com.szwx.yht.bean.RegOrder;
import com.szwx.yht.exception.ServiceException;
import com.szwx.yht.service.IRegisterService;
import com.szwx.yht.util.SendMsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-10
 * Time: 下午2:27
 * To change this template use File | Settings | File Templates.
 */
public class Step05Action extends ActionSupport {
    private long orderId;
    private RegOrder regOrder;
    private Date qhStartTime;

    private String msg;
    @Autowired
    private IRegisterService registerService;

    private static final Logger log = LoggerFactory.getLogger(Step05Action.class);

    public String execute() {

        try {
            regOrder = registerService.getRegOrder(orderId);

            qhStartTime = regOrder.getStateTime();
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(qhStartTime.getTime());

            cal.add(Calendar.MINUTE, -30);

            qhStartTime = cal.getTime();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    String mobile = regOrder.getRegPeople().getMobile();
                    if (mobile == null || mobile.trim().equals("")) return;

                    String shortMessage = SendMsgUtil.getShortMessage2(regOrder);
                    msg = shortMessage;

                    SmsRequest req = new SmsRequest();
                    req.header = new SmsRequestHeader();
                    req.body = new SmsRequestBody(
                            regOrder.getRegPeople().getMobile(), shortMessage);
                    try {
                        log.warn("[" + regOrder.getRegPeople().getMobile() + "],[" + shortMessage + "]");
                        SmsResponse smsResponse = SmsUtils.sendShortMessage(req);
                        log.warn("SMS send result: " + smsResponse.body.result);
                    } catch (Exception e) {

                        log.error("SMS ERROR", e);
                    }
                }
            }).start();


        } catch (Exception e) {
            log.error("-->", e);
        }

        return SUCCESS;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public RegOrder getRegOrder() {
        return regOrder;
    }

    public void setRegOrder(RegOrder regOrder) {
        this.regOrder = regOrder;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getQhStartTime() {
        return qhStartTime;
    }

    public void setQhStartTime(Date qhStartTime) {
        this.qhStartTime = qhStartTime;
    }
}
