package com.szwx.yht.action;

import com.szwx.yht.bean.*;
import com.szwx.yht.bean.Hospital;
import com.szwx.yht.common.CommonAction;
import com.szwx.yht.common.DefaultBaseData;
import com.szwx.yht.dto.MethodCallBean;
import com.szwx.yht.dto.TimeWorkSchemaDto;
import com.szwx.yht.dto.WSDepartListDto;
import com.szwx.yht.dto.WSDoctorListDto;
import com.szwx.yht.exception.ActionException;
import com.szwx.yht.exception.ServiceException;
import com.szwx.yht.service.IRegisterService;
import com.szwx.yht.userRz.GarzServiceClient;
import com.szwx.yht.userRz.GarzServicePortType;
import com.szwx.yht.util.*;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.*;

@SuppressWarnings("serial")
@Controller("registerAction")
@Scope("session")
public class RegisterAction extends CommonAction {

    @Autowired
    private IRegisterService registerService;


    private static final String[] ptn = new String[]{"MM/dd/yyyy"};


    private RegPeople logPeople;//预约人信息
    private RegPeople regPeople;//就诊人信息

    private String docName;//专家姓名
    private String deptName;//科室名称
    private Depart depart;//部门对象
    private DoctorExpert doctor;//专家对象
    private WSDoctor docWS;
    private WSDepart deptWS;
    private Page page = new Page();
    private int t;//标示请求类型 1：分页请求(需编码转换)
    private int methodType;//方法来源 0：按专家或科室，1：按时间或医院
    private List<WSDoctorListDto> wsDoctors;//分页查询出的排班
    private List<Date> listDate;
    private int workType; //排班上下午
    //    private Date workDate; //排班日期
    private String workDate;
    private Hospital hospital;//医院（对象）
    private List<TimeWorkSchemaDto> timeWorkSchemaDtos;//排班的分时段对象
    private List<RegPeople> regPeoples;//挂号人登陆亲友信息
    private Date today;
    private List<WSDepartListDto> wsDeparts;//分页查询出来的科室排班
    private List<Hospital> hospitals;//医院集合
    private CallIn callIn; //呼入记录对象
    private RegPipelined regPipelined;//
    private RegOrder regOrder;//挂号记录实体类
    private String startTime;
    private String endTime;
    private String hospitalId;//医院编号

    private Long workScheamId;//排班id

    private String identetycard;//身份证号码

    private Integer medicalType;

    private int regType;

    public int getRegType() {
        return regType;
    }

    public void setRegType(int regType) {
        this.regType = regType;
    }

    public String toRegister() throws ActionException {
        try {
//			logPeople=new RegPeople();
            regPeoples = new ArrayList<RegPeople>();
            regOrder = new RegOrder();
            callIn = (CallIn) session.get(DefaultBaseData.CALL_INFO);
            identetycard = session.get(DefaultBaseData.CARD_ID) == null ? "" : getSession().get(DefaultBaseData.CARD_ID).toString();
            if (identetycard != null && !"".equals(identetycard)) {
                logPeople = registerService.getRegPeople(identetycard);
                if (logPeople == null) {
                    logPeople = new RegPeople();
                    logPeople.setIdentityCard(identetycard);
                    //	logPeople.setTrueName(getNameByIdentityCard(identetycard));
                }

                if (null != medicalType) {
                    logPeople.setMedicalType(medicalType);        //设置自费还是医保    0：自费  1：市区医保
                }

                //根据身份证判别性别
                if (logPeople.getIdentityCard().length() == 15) {
                    if ((Integer.parseInt(logPeople.getIdentityCard().substring(14, 15)) % 2) == 0) {
                        logPeople.setSex(2);
                    } else {
                        logPeople.setSex(1);
                    }

                }
                if (logPeople.getIdentityCard().length() == 18) {
                    if ((Integer.parseInt(logPeople.getIdentityCard().substring(16, 17)) % 2) == 0) {
                        logPeople.setSex(2);
                    } else {
                        logPeople.setSex(1);
                    }
                }

            }
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ActionException("RegisterAction.toRegister():系统出错1" + e.getMessage(), e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ActionException("RegisterAction.toRegister():系统出错2" + e.getMessage(), e);
        }
        return "toRegister";
    }


    public String toRegisterImpl() throws ActionException {
        logPeople = new RegPeople();
        hospitalId = "";
        deptName = "";
        docName = "";
        return this.toRegister();
    }


    private String getNameByIdentityCard(String identetycard) throws Exception {
        GarzServicePortType service = new GarzServiceClient().getGarzServiceHttpPort();
        String str = "szwx" + TimeUtil.formatDate1(new Date(), "yyyyMMddHHmmss") + "p%0!w@d_#" + identetycard;
        String result = service.getNameByIdno("szwx," + TimeUtil.formatDate1(new Date(), "yyyyMMddHHmmss") + "," + Md5.getMD5Info(str) + "," + identetycard);
        String[] strs = result.split(",");
        if (strs[0].trim().equals("0000")) {
            return strs[2];
        } else {
            return "身份证出错";
        }
    }

    public String getTrueNameBynoid() {
        call = new MethodCallBean();
        call.setMsg(true, "请求超时");        /*新线程进行接口调用*/
        new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("IdentityCard:" + regPeople.getIdentityCard());
//						RegPeople peop=registerService.getRegPeople(regPeople.getIdentityCard());
//						if(peop!=null&&peop.getTrueName()!=null&&!"".equals(peop.getTrueName())){
//							call.setMsg(true,peop.getTrueName());
//						}else{
                    String result = "";
                    final GarzServicePortType service = new GarzServiceClient().getGarzServiceHttpPort();
                    String str = "szwx" + TimeUtil.formatDate1(new Date(), "yyyyMMddHHmmss") + "p%0!w@d_#" + regPeople.getIdentityCard();
                    result = service.getNameByIdno("szwx," + TimeUtil.formatDate1(new Date(), "yyyyMMddHHmmss") + "," + Md5.getMD5Info(str) + "," + regPeople.getIdentityCard());
                    String[] strs = result.split(",");
                    if (strs[0].trim().equals("0000")) {
                        if (Integer.valueOf(strs[3].trim()) == 0) {
                            call.setMsg(false, "此身份证已注销");
                        } else {
                            call.setMsg(true, strs[2]);
                        }
                    } else {
                        call.setMsg(false, strs[1]);
                    }
//						}
                    Thread.interrupted();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    call.setMsg(false, "系统出错");
                } catch (Exception e) {
                    call.setMsg(false, "系统出错");
                }
            }
        }).start();
        try {
            Thread.sleep(Long.parseLong(Flag.read("ReceiveTimeout").trim()));
        } catch (InterruptedException e1) {
        }
        return "callJson";
    }


    /**
     * 加载有排版专家信息
     *
     * @return
     * @throws com.szwx.yht.exception.ActionException
     *
     * @Title: loadWorkScheamDoctor
     * @author:gaowm
     * @date:Jul 14, 2012 8:36:16 PM
     */
    public String loadWorkScheamDoctor() throws ActionException {
        if (getSession().containsKey("hospitalId"))
            hospitalId = getSession().get("hospitalId").toString();
        else hospitalId = "SDFY";

        GregorianCalendar calendar = new GregorianCalendar();
        listDate = new ArrayList<Date>();
        for (int i = 0; i < 7; i++) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            listDate.add(calendar.getTime());
        }
        try {
            if (methodType == 0 && t == 1) {
                if (docName != null && !"".equals(docName.trim())) {
                    docName = new String(docName.getBytes("iso8859-1"), "utf-8");
                }
                if (deptName != null && !"".equals(deptName.trim())) {
                    deptName = new String(deptName.getBytes("iso8859-1"), "utf-8");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ActionException("RegisterAction.loadWorkScheamDoctor():系统出错1" + e.getMessage(), e);
        }
        try {
            if (methodType == 0) {
                wsDoctors = registerService
                        .getWsDoctorListDtosimpl(docName, deptName, page, hospitalId);
            } else {
                wsDoctors = registerService.getWsDoctorListDtosByDoctor(doctor, page);
                throw new ActionException("--1--");
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ActionException("RegisterAction.loadWorkScheamDoctor():系统出错2" + e.getMessage(), e);
        }
        return "loadWorkScheamDoctor";
    }

    /**
     * 加载科室排班信息
     *
     * @return
     * @throws com.szwx.yht.exception.ActionException
     *
     * @Title: loadWorkScheamDepart
     * @author:gaowm
     * @date:Jul 15, 2012 4:23:00 PM
     */
    public String loadWorkScheamDepart() throws ActionException {
        session.put("regType", regType);

        if (getSession().containsKey("hospitalId"))
            hospitalId = getSession().get("hospitalId").toString();
        else hospitalId = "SDFY";


        try {
            Hospital hos = new Hospital();
            hos.setHospitalCode(hospitalId);
            wsDeparts = registerService.getWsDepartListDtos(hos, deptName, page);

        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ActionException(
                    "RegisterAction.loadWorkScheamDepart():系统出错2"
                            + e.getMessage(), e
            );
        }

        return "loadWorkScheamDepart";

    }

    /**
     * 加载选中时间上下午对应的时间段排班信息
     *
     * @return
     * @throws Exception
     * @Title: loadTimeList
     * @author:gaowm
     * @date:Jul 14, 2012 8:35:42 PM
     */
    public String loadTimeList() throws Exception {
        try {
            Date date = DateUtils.parseDate(workDate, ptn);
            timeWorkSchemaDtos = registerService.getRegPipelineds(
                    workType, date, doctor.getForWorkNo(),
                    depart.getDepartCodeNo(), workScheamId,
                    hospital.getHospitalCode());
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ActionException("RegisterAction.loadTimeList():系统出错1" + e.getMessage(), e);
        }
        if (timeWorkSchemaDtos.size() > Integer.valueOf(Flag.read("fsdNum").toString().trim())) {
            return "toTimeListTwo";
        } else {
            return "toTimeList";
        }

    }


    /**
     * 实时科室排班查询排班信息
     *
     * @return
     * @throws com.szwx.yht.exception.ActionException
     *
     * @Title: loadTimeListDeaprt
     * @author:gaowm
     * @date:Jul 16, 2012 11:17:11 AM
     */
    public String loadTimeListDeaprt() throws ActionException {
        try {
//			timeWorkSchemaDtos=registerService.getRegPipelinedsByDepart(depart.getDepartCodeNo(),workScheamId);
            timeWorkSchemaDtos = registerService.getRegPipelinedsByDepart(depart, workScheamId);
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ActionException("RegisterAction.loadTimeList():系统出错1" + e.getMessage(), e);
        }
        if (timeWorkSchemaDtos.size() > Integer.valueOf(Flag.read("fsdNum").toString().trim())) {
            return "toTimeListTwo";
        } else {
            return "toTimeList";
        }
    }

    public String toAddFriend() throws ActionException {
        try {
            if (logPeople != null) {
                regPeoples = registerService.getRegPeoples(logPeople);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ActionException("RegisterAction.toAddFriend():系统出错1" + e.getMessage(), e);
        }
        return "toAddFriend";
    }


    public String loadQueryDoctor() throws ActionException {
        try {
            hospitals = registerService.gethospitals();
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ActionException("RegisterAction.loadQueryDoctor():系统出错1" + e.getMessage(), e);
        }
        return "loadQueryDoctor";
    }

    public String loadQueryDoctorBYTime() throws ActionException {
        GregorianCalendar calendar = new GregorianCalendar();
        listDate = new ArrayList<Date>();
        for (int i = 0; i < 7; i++) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            listDate.add(calendar.getTime());
        }
        return "loadQueryDoctorBYTime";
    }

    /**
     * 按科室挂号，load中间页
     *
     * @return
     * @throws com.szwx.yht.exception.ActionException
     *
     * @Title: loadQueryDepart
     * @author:gaowm
     * @date:Jul 16, 2012 2:54:46 PM
     */
    public String loadQueryDepart() throws ActionException {
        GregorianCalendar calendar = new GregorianCalendar();
        today = calendar.getTime();
        try {
            hospitals = registerService.gethospitals();
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ActionException("RegisterAction.loadQueryDepart():系统出错1" + e.getMessage(), e);
        }
        return "loadQueryDepart";
    }


    public String loadQueryDepartByTime() throws ActionException {
        GregorianCalendar calendar = new GregorianCalendar();
        today = calendar.getTime();
        try {
            hospitals = registerService.gethospitals();
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ActionException("RegisterAction.loadQueryDepartByTime():系统出错1" + e.getMessage(), e);
        }
        return "loadQueryDepartByTime";
    }

    /**
     * 根据日期查询有专家排版信息的医院
     *
     * @return
     * @throws com.szwx.yht.exception.ActionException
     *
     * @Title: getHospitalByworkDate
     * @author:gaowm
     * @date:Jul 16, 2012 2:58:03 PM
     */
    public String getHospitalByworkDate() throws ActionException {
        try {
            Date date = DateUtils.parseDate(workDate, ptn);
            call = registerService.getHospitalByworkDate(date);
        } catch (Exception e) {
            e.printStackTrace();
            call.setMsg(false, "系统出错");
        }
        return "callJson";
    }

    /**
     * 根据时间和科室查询有排班的医生
     *
     * @return
     * @throws Exception
     * @Title: getDoctorByworkDateAndDepart
     * @author:gaowm
     * @date:Jul 16, 2012 3:56:19 PM
     */
    public String getDoctorByworkDateAndDepart() throws Exception {
        try {
            Date date = DateUtils.parseDate(workDate, ptn);
            call = registerService.getDoctorByworkDateAndDepart(
                    date, depart.getDepartCodeNo()
            );
        } catch (ServiceException e) {
            e.printStackTrace();
            call.setMsg(false, "系统出错");
        }
        return "callJson";
    }

    /**
     * 根据时间医院查询出有排版的科室
     *
     * @return
     * @throws Exception
     * @Title: getDepartByworkDateAndHopital
     * @author:gaowm
     * @date:Jul 16, 2012 3:59:24 PM
     */

    public String getDepartByworkDateAndHopital() throws Exception {
        try {
            Date date = DateUtils.parseDate(workDate, ptn);
            call = registerService.getDepartByworkDateAndHopital(
                    date, hospital.getHospitalCode());
        } catch (ServiceException e) {
            e.printStackTrace();
            call.setMsg(false, "系统出错");
        }
        return "callJson";
    }


    /**
     * 根据时间医院查询出实时挂号有排版的科室
     *
     * @return
     * @throws Exception
     * @Title: getDepartByworkDateAndHopitalFromTime
     * @author:gaowm
     * @date:Jul 17, 2012 2:03:53 PM
     */
    public String getDepartByworkDateAndHopitalFromTime() throws Exception {
        try {
            call = registerService.getDepartByworkDateAndHopitalFromTime(hospital.getHospitalCode());
        } catch (ServiceException e) {
            e.printStackTrace();
            call.setMsg(false, "系统出错");
        }
        return "callJson";
    }

    /**
     * 确认挂号信息
     *
     * @return
     * @throws com.szwx.yht.exception.ActionException
     *
     * @Title: toSaveRegister
     * @author:gaowm
     * @date:Jul 15, 2012 8:55:18 PM
     */
    public String toSaveRegister() throws ActionException {
        return "toSaveRegister";
    }

    /**
     * 确认去挂号
     *
     * @return
     * @throws com.szwx.yht.exception.ActionException
     *
     * @Title: saveRegister
     * @author:gaowm
     * @date:Jul 15, 2012 8:56:56 PM
     */
    public String saveRegister() throws ActionException {
        RegPeople people = null;
        try {
            people = registerService.getRegPeople(regPeople.getIdentityCard());
        } catch (ServiceException e) {
            call.setMsg(false, "获取就诊人信息出错！");
            return "saveRegisterSuccess";
        }

        regOrder.setCallIn(callIn);
        regOrder.setOpeateESQ((User) getSession().get("admin"));
//		if(session.get(DefaultBaseData.CARD_ID)==null){
        regOrder.setRegPeople(people);
//        regOrder.setRegPeople(regPeople);
//		}else{
//			regOrder.setRegPeople(logPeople);
//		}
        regOrder.setTreatPeople(regPeople);
        regOrder.setState(1);
        regOrder.setRegPipelined(regPipelined);        /*---------黑名单过滤-----------*/
        if (regPeople.getBreakPromiseCount() > 2) {
            call.setMsg(false, "您已存在" + regPeople.getBreakPromiseCount()
                    + "次爽约记录，不能进行预约挂号。");
            return "saveRegisterSuccess";
        }
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
        try {
            if (call.isSuccess()) {
                logPeople = new RegPeople();
                //SendMsgUtil.sendPhoneMsg(regOrder);
            }
        } catch (Exception e) {
        }
        return "saveRegisterSuccess";
    }


    private void quitRegister() {
        MethodCallBean callInfo = new MethodCallBean();
        try {
            callInfo = registerService.updateRegister(regOrder.getCode());
        } catch (ServiceException e) {
            e.printStackTrace();
            callInfo.setMsg(false, "程序出错");

        } catch (RuntimeException ex) {
            ex.printStackTrace();
            callInfo.setMsg(false, "运行出错");
        } catch (Exception exx) {
            exx.printStackTrace();
            callInfo.setMsg(false, "系统出错");
        }
    }

    /**
     * 修改就诊人信息
     *
     * @return
     * @throws com.szwx.yht.exception.ActionException
     *
     * @Title: updateRegister
     * @author:gaowm
     * @date:Jul 17, 2012 4:12:38 PM
     */
    public String updateRegister() throws ActionException {
        regPeople.setIdentityCard(getSession().get("id").toString());
        regPeople.setTrueName(getSession().get("name").toString());

        try {
            RegPeople r = registerService.getRegPeople(regPeople.getIdentityCard());
            if (r == null) {//&&(regPeople.getId()==-1||regPeople.getId()==0)
                if (regPeople.getMobile().trim().substring(0, 1).equals("0")) {
                    regPeople.setMobile(regPeople.getMobile().trim().substring(1).trim());
                }
                call = registerService.savePeople(regPeople);
                regPeople = (RegPeople) call.getObject();
//				if(logPeople.getId()==0){
//					logPeople=regPeople;
//				}

            } else {
                regPeople.setLastUser((User) getSession().get("admin"));
                if (regPeople.getMobile().trim().substring(0, 1).equals("0")) {
                    regPeople.setMobile(regPeople.getMobile().trim().substring(1));
                }
                call = registerService.updatePeople(regPeople);
                regPeople = (RegPeople) call.getObject();
//				if(regPeople.getId()==logPeople.getId()){
//					logPeople=regPeople;
//				}
            }
            logPeople = regPeople;
            regPipelined = registerService.getRegPiplinedById(regPipelined.getCode());
            if (startTime != null && !"".equals(startTime.trim())) {
                regOrder.setStateTime(TimeUtil.formatDate3(startTime, "HH:mm"));
            }
            if (endTime != null && !"".equals(endTime.trim())) {
                regOrder.setEndTime(TimeUtil.formatDate3(endTime, "HH:mm"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            call.setMsg(false, "系统出错");
        }
        return "callJson";

    }


    public String getPeopleByCard() throws ActionException {
        try {
            call = registerService.getPeopleByCard(regPeople);
        } catch (ServiceException e) {
            e.printStackTrace();
            call.setMsg(false, "系统出错");
        }
        return "callJson";
    }


    public String toAddResult() {
        return "toAddResult";
    }

    /*-------------GET/SET---------------------*/
    public String getDocName() {
        return docName;
    }


    public void setDocName(String docName) {
        this.docName = docName;
    }


    public String getDeptName() {
        return deptName;
    }


    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }


    public RegPeople getLogPeople() {
        return logPeople;
    }


    public void setLogPeople(RegPeople logPeople) {
        this.logPeople = logPeople;
    }


    public RegPeople getRegPeople() {
        return regPeople;
    }


    public void setRegPeople(RegPeople regPeople) {
        this.regPeople = regPeople;
    }


    public WSDoctor getDocWS() {
        return docWS;
    }


    public void setDocWS(WSDoctor docWS) {
        this.docWS = docWS;
    }


    public WSDepart getDeptWS() {
        return deptWS;
    }


    public void setDeptWS(WSDepart deptWS) {
        this.deptWS = deptWS;
    }


    public Page getPage() {
        return page;
    }


    public void setPage(Page page) {
        this.page = page;
    }


    public int getT() {
        return t;
    }


    public void setT(int t) {
        this.t = t;
    }


    public int getMethodType() {
        return methodType;
    }


    public void setMethodType(int methodType) {
        this.methodType = methodType;
    }

    public Depart getDepart() {
        return depart;
    }


    public void setDepart(Depart depart) {
        this.depart = depart;
    }


    public DoctorExpert getDoctor() {
        return doctor;
    }


    public void setDoctor(DoctorExpert doctor) {
        this.doctor = doctor;
    }


    public List<WSDoctorListDto> getWsDoctors() {
        return wsDoctors;
    }

    public void setWsDoctors(List<WSDoctorListDto> wsDoctors) {
        this.wsDoctors = wsDoctors;
    }

    public List<Date> getListDate() {
        return listDate;
    }

    public void setListDate(List<Date> listDate) {
        this.listDate = listDate;
    }

    public int getWorkType() {
        return workType;
    }


    public void setWorkType(int workType) {
        this.workType = workType;
    }


//    public Date getWorkDate() {
//        return workDate;
//    }
//
//
//    public void setWorkDate(Date workDate) {
//        this.workDate = workDate;
//    }


//    public Date getWorkDate() {
//        return workDate;
//    }
//
//    public void setWorkDate(Date workDate) {
//        log.error("--2--> " + workDate);
//        this.workDate = workDate;
//    }

    public void setWorkDate(String workDate) throws DateParseException {
        this.workDate = workDate;
    }

    public String getWorkDate() {
        return workDate;
    }

    public List<TimeWorkSchemaDto> getTimeWorkSchemaDtos() {
        return timeWorkSchemaDtos;
    }


    public void setTimeWorkSchemaDtos(List<TimeWorkSchemaDto> timeWorkSchemaDtos) {
        this.timeWorkSchemaDtos = timeWorkSchemaDtos;
    }

    public List<RegPeople> getRegPeoples() {
        return regPeoples;
    }

    public void setRegPeoples(List<RegPeople> regPeoples) {
        this.regPeoples = regPeoples;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public List<WSDepartListDto> getWsDeparts() {
        return wsDeparts;
    }

    public void setWsDeparts(List<WSDepartListDto> wsDeparts) {
        this.wsDeparts = wsDeparts;
    }

    public List<Hospital> getHospitals() {
        return hospitals;
    }

    public void setHospitals(List<Hospital> hospitals) {
        this.hospitals = hospitals;
    }

    public CallIn getCallIn() {
        return callIn;
    }

    public void setCallIn(CallIn callIn) {
        this.callIn = callIn;
    }

    public RegPipelined getRegPipelined() {
        return regPipelined;
    }

    public void setRegPipelined(RegPipelined regPipelined) {
        this.regPipelined = regPipelined;
    }

    public RegOrder getRegOrder() {
        return regOrder;
    }

    public void setRegOrder(RegOrder regOrder) {
        this.regOrder = regOrder;
    }

    public Long getWorkScheamId() {
        return workScheamId;
    }

    public void setWorkScheamId(Long workScheamId) {
        this.workScheamId = workScheamId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getIdentetycard() {
        return identetycard;
    }

    public void setIdentetycard(String identetycard) {
        this.identetycard = identetycard;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Integer getMedicalType() {
        return medicalType;
    }

    public void setMedicalType(Integer medicalType) {
        this.medicalType = medicalType;
    }


}
