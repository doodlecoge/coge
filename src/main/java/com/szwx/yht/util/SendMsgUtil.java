package com.szwx.yht.util;

import com.szwx.yht.bean.RegOrder;
import com.szwx.yht.bean.RegPeople;
import com.szwx.yht.bean.WSDepart;
import com.szwx.yht.bean.WSDoctor;
import com.szwx.yht.smsWebService.noteClient;
import com.szwx.yht.smsWebService.notePortType;
import org.apache.commons.lang.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

public class SendMsgUtil {

    public static boolean sendMsg(String phone, String content) {
        try {
            noteClient client = new noteClient();
            notePortType service = client.getnoteHttpPort();
            String reqTime = TimeUtil.formatDate1(new Date(), "yyyyMMddhhmmss");
            String sign = Md5.getMD5Str("zhyl" + reqTime + Md5.getMD5Str("111111"));
            try {
                return service.getSendMessageUSER2(reqTime, sign, content, phone, 2,
                        "noteSms", "zhyl");
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 预约短信 *
     */
    public static void sendPhoneMsg(RegOrder orderMsg) {
        WSDoctor ws = new WSDoctor();
        WSDepart dp = new WSDepart();
        String context = "";
        // Integer isSendMsg=new Integer(1);//0:无需发短信，1：需要发短信
        Integer over16years = new Integer(0);// 0:16岁以下，1:16岁以上,2:出错
        Integer isyb = new Integer(0);// 0:是医保，1：不是医保,2:出错
        Integer isfsd = new Integer(1);// 0:分时段，1：不是分时段；2：出错
        try {
            ws = (WSDoctor) orderMsg.getRegPipelined().getWorkSchema();
        } catch (Exception e) {
            ws = null;
        }
        try {
            dp = (WSDepart) orderMsg.getRegPipelined().getWorkSchema();
        } catch (Exception e) {
            dp = null;
        }
        over16years = SendMsgUtil.isOver16years(orderMsg);
        isyb = SendMsgUtil.isyb(orderMsg);
        isfsd = SendMsgUtil.isfsd(orderMsg);

        if (ws != null) {
            context = orderMsg.getTreatPeople().getTrueName() + "预约了" + TimeUtil.formatDate1(ws.getWorkDate(), "yyyy年MM月dd日 EEE")
                    + ws.getDoctorExpert().getName() + "医生的"
                    + orderMsg.getTreatOrder() + "号,";
            if (over16years.intValue() == 1 && isyb.intValue() == 0) {
                context += "凭就诊者的市民卡(园区患者凭园区医保卡),于";
            } else if (over16years.intValue() == 1 && isyb.intValue() == 1) {
                context += "凭就诊者二代身份证,于";
            } else {
                context += "凭密码" + orderMsg.getCheckCode() + "于";
            }

            if (isfsd.intValue() == 0) {
                context += TimeUtil.formatDate1(orderMsg.getStateTime(), "HH:mm") + "前";
            } else {
                if (orderMsg.getRegPipelined().getWorkType() == 1) {
                    context += "7:30至9:30";
                } else if (orderMsg.getRegPipelined().getWorkType() == 2) {
                    context += "12:30至14:00";
                }
            }
            context += "到" + ws.getDepart().getHospital().getName() + "取号。";
            if (orderMsg.getIdealRegisterTime() != null && isfsd.intValue() != 0) {
                context += "您的预期就诊时间为"
                        + TimeUtil.formatDate1(orderMsg.getIdealRegisterTime(), "HH:mm")
                        + ",此时间仅供参考。";
            }
            context += "如需退号，请根据身份证、姓名和密码"
                    + orderMsg.getCheckCode() + "至网站退号,延期无效。 ";
            System.out.println("预约短信，内容：" + context);
            SendMsgService.sendMsg(orderMsg.getTreatPeople().getMobile(), context);
        } else if (dp != null) {
            context = orderMsg.getTreatPeople().getTrueName() + "预约了"
                    + TimeUtil.formatDate1(dp.getWorkDate(), "yyyy年MM月dd日 EEE")
                    + dp.getDepart().getDepartName() + "的";
            if (orderMsg.getTreatOrder() > 0) {
                context += orderMsg.getTreatOrder();
            }
            context += "号,";
            if (over16years.intValue() == 1 && isyb.intValue() == 0) {
                context += "凭就诊者的市民卡(园区患者凭园区医保卡),于";
            } else if (over16years.intValue() == 1 && isyb.intValue() == 1) {
                context += "凭就诊者二代身份证,于";
            } else {
                context += "凭密码" + orderMsg.getCheckCode() + "于";
            }
            if (isfsd.intValue() == 0) {
                context += TimeUtil.formatDate1(orderMsg.getStateTime(), "HH:mm") + "前";
            } else {
                if (orderMsg.getRegPipelined().getWorkType() == 1) {
                    context += "7:30至11:30";
                } else if (orderMsg.getRegPipelined().getWorkType() == 2) {
                    context += "12:30至16:00";
                }
            }
            context += "到" + dp.getDepart().getHospital().getName() + "取号。";
            System.out.println("预约短信，内容：" + context);
            SendMsgService.sendMsg(orderMsg.getTreatPeople().getMobile(),
                    context);
        }
    }


    public static String getShortMessage2(RegOrder orderMsg) {
        WSDoctor ws = new WSDoctor();
        WSDepart dp = new WSDepart();
        String context = "";
        // Integer isSendMsg=new Integer(1);//0:无需发短信，1：需要发短信
        Integer over16years = new Integer(0);// 0:16岁以下，1:16岁以上,2:出错
        Integer isyb = new Integer(0);// 0:是医保，1：不是医保,2:出错
        Integer isfsd = new Integer(1);// 0:分时段，1：不是分时段；2：出错
        try {
            ws = (WSDoctor) orderMsg.getRegPipelined().getWorkSchema();
        } catch (Exception e) {
            ws = null;
        }
        try {
            dp = (WSDepart) orderMsg.getRegPipelined().getWorkSchema();
        } catch (Exception e) {
            dp = null;
        }
        over16years = SendMsgUtil.isOver16years(orderMsg);
        isyb = SendMsgUtil.isyb(orderMsg);
        isfsd = SendMsgUtil.isfsd(orderMsg);

        if (ws != null) {
            context = orderMsg.getTreatPeople().getTrueName()+"预约了"	+
                    TimeUtil.formatDate1(ws.getWorkDate(), "yyyy年MM月dd日 EEE")
                    + ws.getDoctorExpert().getName() + "医生的"
                    + orderMsg.getTreatOrder() + "号,";
            if (over16years.intValue() == 1 && isyb.intValue() == 0) {
                context += "凭就诊者的市民卡(园区患者凭园区医保卡),于";
            } else if (over16years.intValue() == 1 && isyb.intValue() == 1) {
                context += "凭就诊者二代身份证,于";
            } else {
                context += "凭密码" + orderMsg.getCheckCode() + "于";
            }

            if (isfsd.intValue() == 0) {
                Date date = orderMsg.getStateTime();
                Date date2 = DateUtils.addMinutes(date, -30);

                context += TimeUtil.formatDate1(date2, "HH:mm") + "至" +
                        TimeUtil.formatDate1(date, "HH:mm");

            } else {
                if (orderMsg.getRegPipelined().getWorkType() == 1) {
                    context += "7:30至9:30";
                } else if (orderMsg.getRegPipelined().getWorkType() == 2) {
                    context += "12:30至14:00";
                }
            }
            context += "到" + ws.getDepart().getHospital().getName() + "取号。";
            if (orderMsg.getIdealRegisterTime() != null&& isfsd.intValue() != 0) {
                context += "您的预期就诊时间为"+ TimeUtil.formatDate1(orderMsg.getIdealRegisterTime(),"HH:mm") + ",此时间仅供参考。";
            }
            context += "如需退号，请至少就诊前一天下午三点前拨打12320,延期无效。 ";
            System.out.println("预约短信，内容："+context);
        } else if (dp != null) {
            context = orderMsg.getTreatPeople().getTrueName()+"预约了"
                    + TimeUtil.formatDate1(dp.getWorkDate(), "yyyy年MM月dd日 EEE")
                    + dp.getDepart().getDepartName() + "的";
            if(orderMsg.getTreatOrder()>0){
                context +=orderMsg.getTreatOrder();
            }
            context +="号,";
            if (over16years.intValue() == 1 && isyb.intValue() == 0) {
                context += "凭就诊者的市民卡(园区患者凭园区医保卡),于";
            } else if (over16years.intValue() == 1 && isyb.intValue() == 1) {
                context += "凭就诊者二代身份证,于";
            } else {
                context += "凭密码" + orderMsg.getCheckCode() + "于";
            }
            if (isfsd.intValue() == 0) {
                Date date = orderMsg.getStateTime();
                Date date2 = DateUtils.addMinutes(date, -30);

                context += TimeUtil.formatDate1(date2, "HH:mm") + "至" +
                        TimeUtil.formatDate1(date, "HH:mm");
            } else {
                if (orderMsg.getRegPipelined().getWorkType() == 1) {
                    context += "7:30至11:30";
                } else if (orderMsg.getRegPipelined().getWorkType() == 2) {
                    context += "12:30至16:00";
                }
            }
            context += "到" + dp.getDepart().getHospital().getName()+ "取号。如需退号，请至少就诊前一天下午三点前拨打12320,延期无效。";
            System.out.println("预约短信，内容："+context);
        }

        return context;
    }

    public static String getShortMessage(RegOrder orderMsg) {
        WSDoctor ws = new WSDoctor();
        WSDepart dp = new WSDepart();
        String context = "";
        // Integer isSendMsg=new Integer(1);//0:无需发短信，1：需要发短信
        Integer over16years = new Integer(0);// 0:16岁以下，1:16岁以上,2:出错
        Integer isyb = new Integer(0);// 0:是医保，1：不是医保,2:出错
        Integer isfsd = new Integer(1);// 0:分时段，1：不是分时段；2：出错
        try {
            ws = (WSDoctor) orderMsg.getRegPipelined().getWorkSchema();
        } catch (Exception e) {
            ws = null;
        }
        try {
            dp = (WSDepart) orderMsg.getRegPipelined().getWorkSchema();
        } catch (Exception e) {
            dp = null;
        }
        over16years = SendMsgUtil.isOver16years(orderMsg);
        isyb = SendMsgUtil.isyb(orderMsg);
        isfsd = SendMsgUtil.isfsd(orderMsg);

        if (ws != null) {
            context = orderMsg.getTreatPeople().getTrueName() + "预约了" + TimeUtil.formatDate1(ws.getWorkDate(), "yyyy年MM月dd日 EEE")
                    + ws.getDoctorExpert().getName() + "医生的"
                    + orderMsg.getTreatOrder() + "号,";
            if (over16years.intValue() == 1 && isyb.intValue() == 0) {
                context += "凭就诊者的市民卡(园区患者凭园区医保卡),于";
            } else if (over16years.intValue() == 1 && isyb.intValue() == 1) {
                context += "凭就诊者二代身份证,于";
            } else {
                context += "凭密码" + orderMsg.getCheckCode() + "于";
            }

            if (isfsd.intValue() == 0) {
                Date date = orderMsg.getStateTime();
                Date date2 = DateUtils.addMinutes(date, -30);

                context += TimeUtil.formatDate1(date2, "HH:mm") + "至" +
                        TimeUtil.formatDate1(date, "HH:mm");
            } else {
                if (orderMsg.getRegPipelined().getWorkType() == 1) {
                    context += "7:30至9:30";
                } else if (orderMsg.getRegPipelined().getWorkType() == 2) {
                    context += "12:30至14:00";
                }
            }
            context += "到" + ws.getDepart().getHospital().getName() + "取号。";
            if (orderMsg.getIdealRegisterTime() != null && isfsd.intValue() != 0) {
                context += "您的预期就诊时间为"
                        + TimeUtil.formatDate1(orderMsg.getIdealRegisterTime(), "HH:mm")
                        + ",此时间仅供参考。";
            }
            context += "如需退号，请根据身份证、姓名和密码"
                    + orderMsg.getCheckCode() + "至网站退号,延期无效。 ";
            System.out.println("预约短信，内容：" + context);
        } else if (dp != null) {
            context = orderMsg.getTreatPeople().getTrueName() + "预约了"
                    + TimeUtil.formatDate1(dp.getWorkDate(), "yyyy年MM月dd日 EEE")
                    + dp.getDepart().getDepartName() + "的";
            if (orderMsg.getTreatOrder() > 0) {
                context += orderMsg.getTreatOrder();
            }
            context += "号,";
            if (over16years.intValue() == 1 && isyb.intValue() == 0) {
                context += "凭就诊者的市民卡(园区患者凭园区医保卡),于";
            } else if (over16years.intValue() == 1 && isyb.intValue() == 1) {
                context += "凭就诊者二代身份证,于";
            } else {
                context += "凭密码" + orderMsg.getCheckCode() + "于";
            }
            if (isfsd.intValue() == 0) {
                Date date = orderMsg.getStateTime();
                Date date2 = DateUtils.addMinutes(date, -30);

                context += TimeUtil.formatDate1(date2, "HH:mm") + "至" +
                        TimeUtil.formatDate1(date, "HH:mm");
            } else {
                if (orderMsg.getRegPipelined().getWorkType() == 1) {
                    context += "7:30至11:30";
                } else if (orderMsg.getRegPipelined().getWorkType() == 2) {
                    context += "12:30至16:00";
                }
            }
            context += "到" + dp.getDepart().getHospital().getName() + "取号。";
            System.out.println("预约短信，内容：" + context);

        }
        return context;
    }

    /* 重发短信 */
    //public static void sendPhoneMsgAgain(RegOrder orderMsg) {
    public static void sendPhoneMsgAgain(String getpeopleMobile, RegOrder orderMsg) {
        WSDoctor ws = new WSDoctor();
        WSDepart dp = new WSDepart();
        String context = "";
        Integer over16years = new Integer(0);// 0:16岁以下，1:16岁以上,2:出错
        Integer isyb = new Integer(0);// 0:是医保，1：不是医保,2:出错
        Integer isfsd = new Integer(1);// 0:分时段，1：不是分时段；2：出错
        try {
            ws = (WSDoctor) orderMsg.getRegPipelined().getWorkSchema();
        } catch (Exception e) {
            ws = null;
        }
        try {
            dp = (WSDepart) orderMsg.getRegPipelined().getWorkSchema();
        } catch (Exception e) {
            dp = null;
        }
        over16years = SendMsgUtil.isOver16years(orderMsg);
        isyb = SendMsgUtil.isyb(orderMsg);
        isfsd = SendMsgUtil.isfsd(orderMsg);
        if (ws != null) {
            context = orderMsg.getTreatPeople().getTrueName() + "预约了"
                    + TimeUtil.formatDate1(ws.getWorkDate(), "yyyy年MM月dd日 EEE")
                    + ws.getDoctorExpert().getName() + "医生的"
                    + orderMsg.getTreatOrder() + "号,";
            if (over16years.intValue() == 1 && isyb.intValue() == 0) {
                context += "凭就诊者的市民卡(园区患者凭园区医保卡),于";
            } else if (over16years.intValue() == 1 && isyb.intValue() == 1) {
                context += "凭就诊者二代身份证,于";
            } else {
                context += "凭密码" + orderMsg.getCheckCode() + "于";
            }

            if (isfsd.intValue() == 0) {
                context += TimeUtil.formatDate1(orderMsg.getStateTime(),
                        "HH:mm")
                        + "前";
            } else {
                if (orderMsg.getRegPipelined().getWorkType() == 1) {
                    context += "7:30至9:30";
                } else if (orderMsg.getRegPipelined().getWorkType() == 2) {
                    context += "12:30至14:00";
                }
            }

            context += "到" + ws.getDepart().getHospital().getName() + "取号。";

            if (orderMsg.getIdealRegisterTime() != null
                    && isfsd.intValue() != 0) {
                context += "您的预期就诊时间为"
                        + TimeUtil.formatDate1(orderMsg.getIdealRegisterTime(),
                        "HH:mm") + ",此时间仅供参考。";
            }
            context += "如需退号，请至少就诊前一天下午三点前拨打12320,延期无效。";
            //System.out.println("重发，内容："+context);
            //SendMsgService.sendMsg(orderMsg.getTreatPeople().getMobile(),
            //		context);
            System.out.println("重发，内容：" + context + "短信重发至Mobile：" + getpeopleMobile);
            SendMsgService.sendMsg(getpeopleMobile, context);
        } else {
            context = orderMsg.getTreatPeople().getTrueName() + "预约了"
                    + TimeUtil.formatDate1(dp.getWorkDate(), "yyyy年MM月dd日 EEE")
                    + dp.getDepart().getDepartName() + "的";
            if (orderMsg.getTreatOrder() > 0) {
                context += orderMsg.getTreatOrder();
            }
            context += "号,";
            if (over16years.intValue() == 1 && isyb.intValue() == 0) {
                context += "凭就诊者的市民卡(园区患者凭园区医保卡),于";
            } else if (over16years.intValue() == 1 && isyb.intValue() == 1) {
                context += "凭就诊者二代身份证,于";
            } else {
                context += "凭密码" + orderMsg.getCheckCode() + "于";
            }
            if (isfsd.intValue() == 0) {
                context += TimeUtil.formatDate1(orderMsg.getStateTime(),
                        "HH:mm")
                        + "前";
            } else {
                if (orderMsg.getRegPipelined().getWorkType() == 1) {
                    context += "7:30至11:30";
                } else if (orderMsg.getRegPipelined().getWorkType() == 2) {
                    context += "12:30至16:00";
                }
            }
            context += "到" + dp.getDepart().getHospital().getName()
                    + "取号。如需退号，请至少就诊前一天下午三点前拨打12320,延期无效。";
            //System.out.println("重发，内容："+context);
            //SendMsgService.sendMsg(orderMsg.getTreatPeople().getMobile(),
            //		context);
            System.out.println("重发，内容：" + context + "短信重发至Mobile：" + getpeopleMobile);
            SendMsgService.sendMsg(getpeopleMobile, context);
        }
        // sendMsg(orderMsg.getTreatPeople().getMobile(),context);
    }

    /**
     * 退号 *
     */
    public static void sendPhoneMsgToquit(RegOrder orderMsg) {
        WSDoctor ws = new WSDoctor();
        WSDepart dp = new WSDepart();
        String context = "";
        try {
            ws = (WSDoctor) orderMsg.getRegPipelined().getWorkSchema();
        } catch (Exception e) {
            ws = null;
        }
        try {
            dp = (WSDepart) orderMsg.getRegPipelined().getWorkSchema();
        } catch (Exception e) {
            dp = null;
        }
        if (ws != null) {
            context = "您成功取消了"
                    + TimeUtil.formatDate1(ws.getWorkDate(), "yyyy年MM月dd日 EEE")
                    + ws.getDoctorExpert().getName() + "医生的"
                    + orderMsg.getTreatOrder() + "号,"
                    + TimeUtil.formatDate1(orderMsg.getStateTime(), "HH:mm")
                    + "至"
                    + TimeUtil.formatDate1(orderMsg.getEndTime(), "HH:mm")
                    + "的" + ws.getDepart().getHospital().getName() + "专家门诊号。";
            System.out.println("退号，内容：" + context);
            SendMsgService.sendMsg(orderMsg.getTreatPeople().getMobile(),
                    context);
        } else if (dp != null) {
            context = "您成功取消了"
                    + TimeUtil.formatDate1(dp.getWorkDate(), "yyyy年MM月dd日 EEE")
                    + dp.getDepart().getDepartName() + "的";
            if (orderMsg.getTreatOrder() > 0) {
                context += orderMsg.getTreatOrder();
            }
            context += "号,";
            context += TimeUtil.formatDate1(orderMsg.getStateTime(), "HH:mm")
                    + "至"
                    + TimeUtil.formatDate1(orderMsg.getEndTime(), "HH:mm")
                    + "的" + dp.getDepart().getHospital().getName() + "今天的门诊号。";
            System.out.println("退号，内容：" + context);
            SendMsgService.sendMsg(orderMsg.getTreatPeople().getMobile(),
                    context);
        }

    }

    /**
     * 停诊退号 *
     */
    public static void sendTJMsg(RegOrder orderMsg) {
        WSDoctor ws = new WSDoctor();
        WSDepart dp = new WSDepart();
        String context = "";
        try {
            ws = (WSDoctor) orderMsg.getRegPipelined().getWorkSchema();
        } catch (Exception e) {
            ws = null;
        }
        try {
            dp = (WSDepart) orderMsg.getRegPipelined().getWorkSchema();
        } catch (Exception e) {
            dp = null;
        }
        if (ws != null) {
            context = "由于医院专家排班停诊，系统已成功取消了您"
                    + TimeUtil.formatDate1(ws.getWorkDate(), "yyyy年MM月dd日 EEE")
                    + ws.getDoctorExpert().getName() + "医生的"
                    + orderMsg.getTreatOrder() + "号,"
                    + TimeUtil.formatDate1(orderMsg.getStateTime(), "HH:mm")
                    + "至"
                    + TimeUtil.formatDate1(orderMsg.getEndTime(), "HH:mm")
                    + "的" + ws.getDepart().getHospital().getName() + "专家门诊号。";
            System.out.println("停诊退号，内容：" + context);
            SendMsgService.sendMsg(orderMsg.getTreatPeople().getMobile(),
                    context);
        } else if (dp != null) {
            context = "由于医院专家排班停诊，系统已成功取消了您"
                    + TimeUtil.formatDate1(dp.getWorkDate(), "yyyy年MM月dd日 EEE")
                    + dp.getDepart().getDepartName() + "的";
            if (orderMsg.getTreatOrder() > 0) {
                context += orderMsg.getTreatOrder();
            }
            context += "号,";
            context += TimeUtil.formatDate1(orderMsg.getStateTime(), "HH:mm")
                    + "至"
                    + TimeUtil.formatDate1(orderMsg.getEndTime(), "HH:mm")
                    + "的" + dp.getDepart().getHospital().getName() + "今天的门诊号。";
            System.out.println("停诊退号，内容：" + context);
            SendMsgService.sendMsg(orderMsg.getTreatPeople().getMobile(),
                    context);
        }

    }

    public static Integer isOver16years(RegOrder regOrder) {
        try {
            System.out.println(regOrder.getRegPeople().getIdentityCard());
            String birth = regOrder.getRegPeople().getIdentityCard().length() == 15 ? "19"
                    + regOrder.getRegPeople().getIdentityCard()
                    .substring(6, 12)
                    : regOrder.getRegPeople().getIdentityCard()
                    .substring(6, 14);
            Integer year = Integer.parseInt(birth.substring(0, 4));
            Integer month = Integer.parseInt(birth.substring(4, 6));
            Integer day = Integer.parseInt(birth.substring(6, 8));
            Calendar cal = Calendar.getInstance();
            int dayNow = cal.get(Calendar.DATE);
            int monthNow = cal.get(Calendar.MONTH) + 1;
            int yearNow = cal.get(Calendar.YEAR);
            if (yearNow - year.intValue() < 16) {
                return 0;
            } else if (yearNow - year.intValue() > 16) {
                return 1;
            } else if (yearNow - year.intValue() == 16) {
                if (monthNow - month.intValue() > 0) {
                    return 1;
                } else if (monthNow - month.intValue() < 0) {
                    return 0;
                } else if (monthNow - month.intValue() == 0) {
                    if (dayNow - day.intValue() >= 0) {
                        return 1;
                    } else {
                        return 0;
                    }
                } else {
                    return 2;
                }
            } else {
                return 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
    }

    public static Integer isyb(RegOrder regOrder) {
        try {
            Integer type = regOrder.getRegPeople().getMedicalType();
            if (type == null) {
                return 2;
            } else if (type.intValue() == 0) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
    }

    private static Integer isfsd(RegOrder orderMsg) {
        // SDFE,SDFY 分时段
        WSDoctor ws = new WSDoctor();
        WSDepart dp = new WSDepart();
        try {
            ws = (WSDoctor) orderMsg.getRegPipelined().getWorkSchema();
        } catch (Exception e) {
            ws = null;
        }
        try {
            dp = (WSDepart) orderMsg.getRegPipelined().getWorkSchema();
        } catch (Exception e) {
            dp = null;
        }
        if (ws != null) {
            if ("SDFY".equals(ws.getDepart().getHospital().getHospitalCode())) {
                return 0;
            } else if ("SDFE".equals(ws.getDepart().getHospital()
                    .getHospitalCode())) {
                return 0;
            } else if ("SLDQ".equals(ws.getDepart().getHospital()
                    .getHospitalCode())) {
                return 0;
            } else {
                return 1;
            }
        }
        if (dp != null) {
            if ("SDFY".equals(dp.getDepart().getHospital().getHospitalCode())) {
                return 0;
            } else if ("SDFE".equals(dp.getDepart().getHospital()
                    .getHospitalCode())) {
                return 0;
            } else if ("SLDQ".equals(dp.getDepart().getHospital()
                    .getHospitalCode())) {
                if (orderMsg.getRegPipelined().getWorkType() == 1 && TimeUtil.formatDate1(orderMsg.getStateTime(), "HH:mm").toString().trim().equals("7:30") && TimeUtil.formatDate1(orderMsg.getEndTime(), "HH:mm").toString().trim().equals("11:30")) {
                    return 1;
                } else if (orderMsg.getRegPipelined().getWorkType() == 2 && TimeUtil.formatDate1(orderMsg.getStateTime(), "HH:mm").toString().trim().equals("12:30") && TimeUtil.formatDate1(orderMsg.getEndTime(), "HH:mm").toString().trim().equals("16:30")) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return 1;
            }
        }
        return 2;
    }

}
