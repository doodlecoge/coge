package com.szwx.yht.action;

import com.szwx.yht.bean.Hospital;
import com.szwx.yht.bean.RegOrder;
import com.szwx.yht.bean.RegPeople;
import com.szwx.yht.bean.User;
import com.szwx.yht.common.CommonAction;
import com.szwx.yht.dto.MethodCallBean;
import com.szwx.yht.exception.ActionException;
import com.szwx.yht.exception.ServiceException;
import com.szwx.yht.service.IRegisterService;
import com.szwx.yht.util.Page;
import com.szwx.yht.util.SendMsgUtil;
import com.szwx.yht.util.TimeUtil;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 统计，查询挂号信息
 *
 * @author Administrator
 * @date Jul 19, 2012 9:30:57 AM
 */

@SuppressWarnings("serial")
@Controller("queryRegisterAction")
@Scope("session")
public class QueryRegisterAction extends CommonAction {

    @Autowired
    private IRegisterService registerService;
    private Hospital hospital;
    private String departName;
    private String doctorName;
    private String userName;//客服操作员名
    private String registerName;
    private Date beginTime;
    private Date endTime;
    private Integer status;
    private Date regStratTime;
    private Date regEndTime;
    private String telPhone;
    private List<Hospital> hospitals;
    private Integer way;//0:post,1:get
    private Page page = new Page();
    private List<RegOrder> regOrders;
    private RegOrder regOrder;
    private List<RegPeople> peoples;
    private Date nowTime;
    private RegPeople regPeople;
    private Integer isBlack;
    private Integer type;//0:呼入；1:呼出
    private List calls;
    private String getToMobile; //取得页面传入手机号码

    private String removeReason;//解除黑名单的原因

    public String toQueryRegisterList() throws ActionException {
        try {
            nowTime = TimeUtil.formatDate2(new Date(), "yyyy-MM-dd");
            hospitals = registerService.gethospitals();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, 7);
            endTime = cal.getTime();
            cal.add(Calendar.MONTH, -1);
            beginTime = cal.getTime();
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ActionException("QueryRegisterAction.toQueryRegisterList():系统出错1" + e.getMessage(), e);
        }
        return "regOrderList";
    }

    /**
     * 条件查询挂号信息
     *
     * @return
     * @throws com.szwx.yht.exception.ActionException
     *
     * @Title: queryRegisterList
     * @author:gaowm
     * @date:Jul 19, 2012 9:52:00 AM
     */
    public String queryRegisterList() throws ActionException {
//		try {
//			if(way.intValue()!=0){
//				if(departName!=null&&!"".equals(departName.trim())){
//					departName=new String(departName.getBytes("iso8859-1"),"UTF-8");
//				}
//				if(doctorName!=null&&!"".equals(doctorName.trim())){
//					doctorName=new String(doctorName.getBytes("iso8859-1"),"UTF-8");
//				}
//				if(userName!=null&&!"".equals(userName.trim())){
//					userName=new String(userName.getBytes("iso8859-1"),"UTF-8");
//				}
//				if(registerName!=null&&!"".equals(registerName.trim())){
//					registerName=new String(registerName.getBytes("iso8859-1"),"UTF-8");
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new ActionException("QueryRegisterAction.queryRegisterList:编码转换出错1",e);
//		}


        HttpServletRequest request = ServletActionContext.getRequest();

        String id = request.getParameter("id") == null ? "" : request.getParameter("id").trim().toString();
        String name = request.getParameter("name") == null ? "" : request.getParameter("name").trim().toString();
//        String code = request.getParameter("code") == null ? "" : request.getParameter("code").trim().toString();

        nowTime = TimeUtil.formatDate2(new Date(), "yyyy-MM-dd");

        try {
            //hch
            regOrders = registerService.getRegOrders(id, name);
//			regOrders=registerService.getRegOrders(hospital,departName,doctorName,userName,registerName,beginTime,endTime,status,page,telPhone,regStratTime,regEndTime);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ActionException("QueryRegisterAction.queryRegisterList:系统查询出错1" + e.getMessage(), e);
        }
        return "toRegisterList";
    }

    public String getRemoveReason() {
        return removeReason;
    }

    public void setRemoveReason(String removeReason) {
        this.removeReason = removeReason;
    }

    public String queryOrderListForBlack() throws ActionException {
        try {
            regOrders = registerService.getRegOrdersForBlack(regPeople.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ActionException("QueryRegisterAction.queryOrderListForBlack:系统查询出错1" + e.getMessage(), e);
        }
        return "toRegisterListForBlack";

    }


    /**
     * 查询具体某个用户的信息
     *
     * @return
     * @throws com.szwx.yht.exception.ActionException
     *
     * @Title: queryPeopleDetail
     * @author:gaowm
     * @date:2012-9-28 下午03:32:18
     */

    public String queryPeopleDetail() throws ActionException {
        try {
            regPeople = registerService.getPeopleDetail(regPeople.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ActionException("QueryRegisterAction.queryPeopleDetail:系统查询出错1" + e.getMessage(), e);
        }
        return "queryPeopleDetail";
    }

    /**
     * 查询某个挂号记录
     *
     * @return
     * @throws com.szwx.yht.exception.ActionException
     *
     * @Title: getRegOrder
     * @author:gaowm
     * @date:Jul 19, 2012 1:27:29 PM
     */
    public String getRegOrderDetail() throws ActionException {
        try {
            regOrder = registerService.getRegOrder(regOrder.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ActionException("QueryRegisterAction.getRegOrder:系统查询出错1" + e.getMessage(), e);
        }
        return "lookRegOrderDetail";

    }


    public String toQueryBlackPeople() throws ActionException {
        return "toQueryBlackPeople";
    }

    public String toQueryPeoples() throws ActionException {
        return "toQueryPeoples";
    }

    /**
     * 查询黑名单
     *
     * @return
     * @throws com.szwx.yht.exception.ActionException
     *
     * @Title: queryBlackPeople
     * @author:gaowm
     * @date:Jul 20, 2012 5:38:07 PM
     */
    public String queryBlackPeople() throws ActionException {
        try {
            peoples = registerService.queryBlackPeople(telPhone, registerName, page, isBlack);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ActionException("QueryRegisterAction.queryBlackPeople:系统查询出错1" + e.getMessage(), e);
        }
        return "queryBlackPeople";
    }

    public String toGetReason() throws ActionException {
        try {
            regPeople = registerService.getPeopleDetail(regPeople.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ActionException("QueryRegisterAction.toGetReason:系统查询出错1" + e.getMessage(), e);
        }
        return "toGetReason";
    }

//	public String queryPeoples()throws ActionException{
//		try {
//			peoples=registerService.queryPeoples(telPhone,registerName,page);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new ActionException("QueryRegisterAction.queryPeoples:系统查询出错1"+e.getMessage(),e);
//		}
//		return "queryPeoples";
//	}

    /**
     * 退号
     *
     * @return
     * @throws com.szwx.yht.exception.ActionException
     *
     * @Title: quitRegister
     * @author:gaowm
     * @date:Jul 20, 2012 5:58:27 PM
     */
    public String quitRegister() throws ActionException {
        try {
            call = registerService.updateRegister(regOrder.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            call.setMsg(false, "系统出错");
        }
        try {
            if (call.isSuccess()) {
                RegOrder orderMsg = registerService.getRegOrder(regOrder.getCode());
//                SendMsgUtil.sendPhoneMsgToquit(orderMsg);
            }
        } catch (Exception ex) {
        }
        return "callJson";
    }


    public String queryQuitNum() {
        try {
            call = registerService.queryQuitNum(regOrder.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            call.setMsg(false, "系统出错");
            call.setState(-1);
        }
        return "callJson";
    }


    public String sendMsgAgain() throws ActionException {
        call = new MethodCallBean();
        try {
            RegOrder orderMsg = registerService.getRegOrder(regOrder.getCode());
            //SendMsgUtil.sendPhoneMsgAgain(orderMsg);
            //call.setMsg(true,"重发成功");
            //获取页面传入的手机号码
            getToMobile = (ServletActionContext.getRequest().getParameter("setToMobile")).trim();
            //判断手机号码不为null或""时
            if (getToMobile != null && !"".equals(getToMobile)) {
                //验证手机号码11位并且手机号码正确性时
                if (checkCellPhone(getToMobile) && checkMobilePhone(getToMobile)) {
                    System.out.println("短信将重发至Mobile ===> " + getToMobile);
                    //调用重发短信方法
                    SendMsgUtil.sendPhoneMsgAgain(getToMobile, orderMsg);
                    call.setMsg(true, "重发成功");
                } else {
                    //验证手机号码非11位并且手机号码验证失败时，提示"重发失败，请检查您输入的手机号码是否正确！"
                    call.setMsg(true, "重发失败，请检查您输入的手机号码是否正确！");
                }
            } else {
                //手机号码为null或""时，提示"重发失败，请输入手机号码！"
                call.setMsg(true, "重发失败，请输入手机号码！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            call.setMsg(false, "重发失败");
        }
        return "callJson";
    }

    public String queryCallList() throws ActionException {
        User user = (User) session.get("admin");
        if (type == null || (type.intValue() != 0 && type.intValue() != 1)) {
            type = 0;
        }
        try {
            calls = registerService.queryCallList(page, beginTime, endTime, type, user, userName, telPhone);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ActionException("QueryRegisterAction.toQueryCallList:系统查询出错1" + e.getMessage(), e);
        }
        return "toQueryCallList";

    }

    public String toQueryCallList() throws ActionException {
        type = 0;
        beginTime = TimeUtil.formatDate2(new Date(), "yyyy-MM-dd");
        endTime = TimeUtil.formatDate2(new Date(), "yyyy-MM-dd");
        ;
        page = new Page();
        return "queryCallList";

    }


    public String quitBlack() throws ActionException {
        User user = (User) session.get("admin");
        if (null == user) {
            return "logout";
        }
        try {
            call = registerService.updateBlack(regPeople.getId(), removeReason, user);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            call.setMsg(false, "运行出错");
        } catch (Exception e) {
            e.printStackTrace();
            call.setMsg(false, "系统出错");
        }
        return "callJson";
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<Hospital> getHospitals() {
        return hospitals;
    }

    public void setHospitals(List<Hospital> hospitals) {
        this.hospitals = hospitals;
    }

    public Integer getWay() {
        return way;
    }

    public void setWay(Integer way) {
        this.way = way;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<RegOrder> getRegOrders() {
        return regOrders;
    }

    public void setRegOrders(List<RegOrder> regOrders) {
        this.regOrders = regOrders;
    }

    public void setRegOrder(RegOrder regOrder) {
        this.regOrder = regOrder;
    }

    public RegOrder getRegOrder() {
        return this.regOrder;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public List<RegPeople> getPeoples() {
        return peoples;
    }

    public void setPeoples(List<RegPeople> peoples) {
        this.peoples = peoples;
    }

    public Date getNowTime() {
        return nowTime;
    }

    public void setNowTime(Date nowTime) {
        this.nowTime = nowTime;
    }

    public RegPeople getRegPeople() {
        return regPeople;
    }

    public void setRegPeople(RegPeople regPeople) {
        this.regPeople = regPeople;
    }

    public Integer getIsBlack() {
        return isBlack;
    }

    public void setIsBlack(Integer isBlack) {
        this.isBlack = isBlack;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List getCalls() {
        return calls;
    }

    public void setCalls(List calls) {
        this.calls = calls;
    }

    public Date getRegStratTime() {
        return regStratTime;
    }

    public void setRegStratTime(Date regStratTime) {
        this.regStratTime = regStratTime;
    }

    public Date getRegEndTime() {
        return regEndTime;
    }

    public void setRegEndTime(Date regEndTime) {
        this.regEndTime = regEndTime;
    }

    /**
     * 检查手机号码的正确性
     */
    public boolean checkMobilePhone(String MobilePhone) {
        //当前运营商号段分配
        //中国移动号段 1340-1348 135 136 137 138 139 150 151 152 157 158 159 187 188 147
        //中国联通号段 130 131 132 155 156 185 186 145
        //中国电信号段 133 1349 153 180 189
        String regular = "1[3,4,5,8]{1}\\d{9}";
        Pattern pattern = Pattern.compile(regular);
        boolean flag = false;
        if (MobilePhone != null) {
            Matcher matcher = pattern.matcher(MobilePhone);
            flag = matcher.matches();
        }
        return flag;
    }

    /**
     * 验证手机号码是否为11位，不知道详细的手机号码段，只是验证开头必须是1和位数
     */
    public boolean checkCellPhone(String cellPhoneNr) {
        boolean tem = false;
        String reg = "^[1][\\d]{10}";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(cellPhoneNr);
        tem = matcher.matches();
        return tem;
    }
}
