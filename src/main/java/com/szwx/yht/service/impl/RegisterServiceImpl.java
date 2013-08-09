package com.szwx.yht.service.impl;

import com.hch.security.Config;
import com.szwx.yht.bean.*;
import com.szwx.yht.common.CommonService;
import com.szwx.yht.dao.IRegOrderDAO;
import com.szwx.yht.dao.IRegisterDAO;
import com.szwx.yht.dto.*;
import com.szwx.yht.exception.DaoException;
import com.szwx.yht.exception.ServiceException;
import com.szwx.yht.service.IRegisterService;
import com.szwx.yht.util.Flag;
import com.szwx.yht.util.Page;
import com.szwx.yht.util.RealTimeReserve;
import com.szwx.yht.util.TimeUtil;
import com.szwx.yht.zhylWebService.yhtRegClient;
import com.szwx.yht.zhylWebService.yhtRegPortType;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;


@Service("registerService")
public class RegisterServiceImpl extends CommonService implements IRegisterService {


    private Logger log = Logger.getLogger(RegisterServiceImpl.class);

    @Autowired
    private IRegisterDAO registerDao;

    @Autowired
    private IRegOrderDAO regOrderDao;


    public List<WSDoctorListDto> getWsDoctorListDtos(
            String docName, String deptName, Page page, String hospitalId
    ) throws ServiceException {


//		this.sendToProvince(null);
        List<WSDoctorListDto> wsDoctors = new ArrayList<WSDoctorListDto>();
        try {
            wsDoctors = registerDao.getWsDoctorLists(docName, deptName, page, hospitalId);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("RegisterServiceImpl.getWsDoctorListDtos:系统异常1" + e.getMessage(), e);
        }

        try {
            wsDoctors = this.getWSDoctorDtos(wsDoctors);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("RegisterServiceImpl.getWsDoctorListDtos:系统异常2" + e.getMessage(), e);
        }

        return wsDoctors;
    }


    public List<WSDoctorListDto> getWsDoctorListDtosimpl(String docName,
                                                         String deptName, Page page, String hospitalId) throws ServiceException {
        try {
            Integer isUserSession = Integer.valueOf(Flag.read("isUserSession").toString().trim());
            List<WSDoctorListDto> wsList = new ArrayList<WSDoctorListDto>();
            if (isUserSession.intValue() != 0) {
                return this.getWsDoctorListDtos(docName, deptName, page, hospitalId);
            } else {
                wsList = (List<WSDoctorListDto>) sessionSerive.get("workTimeInfo");
                if (wsList != null && wsList.size() > 0) {
                    wsList = filterDoctorList(wsList, docName, deptName, page, hospitalId);
                    wsList = this.getWSDoctorDtos(wsList);
                    System.out.println("使用session查询成功,size:" + wsList.size());
                    return wsList;
                } else {
                    return this.getWsDoctorListDtos(docName, deptName, page, hospitalId);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getWsDoctorListDtosimpl使用session查询排班出错" + e.getMessage());
            return this.getWsDoctorListDtos(docName, deptName, page, hospitalId);
        }

    }


    @SuppressWarnings("unused")
    private List<WSDoctorListDto> filterDoctorList(List<WSDoctorListDto> wsList, String docName, String deptName, Page page, String hospitalId) throws Exception {
        Integer num = wsList.size();
        List<WSDoctorListDto> wsListReturn = new ArrayList<WSDoctorListDto>();
        List<WSDoctorListDto> wsLists = new ArrayList<WSDoctorListDto>();
        for (int i = 0; i < num; i++) {
            Boolean isf = true;
            WSDoctorListDto ws = wsList.get(i);
            if (hospitalId != null && !"".equals(hospitalId.trim())) {
                if (!ws.getDepart().getHospital().getHospitalCode().toString().equals(hospitalId))
                    isf = false;
            }
            if (docName != null && !"".equals(docName.trim())) {
                if (!ws.getDoctor().getName().contains(docName) && !ws.getDoctor().getSpellCode().contains(docName))
                    isf = false;
            }
            if (deptName != null && !"".equals(deptName.trim())) {
                if (!ws.getDepart().getDepartName().contains(deptName) && (ws.getDoctor().getDepartNameForDoctor() == null || !ws.getDoctor().getDepartNameForDoctor().contains(deptName)) && (ws.getDepart().getSpellCode() == null || !ws.getDepart().getSpellCode().toString().contains(deptName)))
                    isf = false;
            }

            if (isf) {
                wsLists.add(ws);
            }
        }
        page.setTotalCount(wsLists.size());
        page.setPageCount(wsLists.size() % page.getPageSize() == 0 ? wsLists.size() / page.getPageSize() : wsLists.size() / page.getPageSize() + 1);
        if (page.getPageNum() >= page.getPageCount()) {
            for (int i = (page.getPageNum() - 1) * page.getPageSize(); i < wsLists.size(); i++) {
                wsListReturn.add(wsLists.get(i));
            }
        } else if (page.getPageNum() < page.getPageCount() && page.getPageNum() > 0) {
            for (int i = (page.getPageNum() - 1) * page.getPageSize(); i < page.getPageNum() * page.getPageSize(); i++) {
                wsListReturn.add(wsLists.get(i));
            }
        }
        return wsListReturn;

    }

    public List<WSDoctorListDto> getWsDoctorListDtosByDoctor(Doctor doctor,
                                                             Page page) throws ServiceException {
        List<WSDoctorListDto> wsDoctors = new ArrayList<WSDoctorListDto>();
        try {
            wsDoctors = registerDao.getWsDoctorLists(doctor, page);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("RegisterServiceImpl.getWsDoctorListDtosByDoctor:系统异常1" + e.getMessage(), e);
        }

        try {
            wsDoctors = this.getWSDoctorDtos(wsDoctors);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("RegisterServiceImpl.getWsDoctorListDtosByDoctor:系统异常2" + e.getMessage(), e);
        }

        return wsDoctors;
    }

    private List<WSDoctorListDto> getWSDoctorDtos(
            List<WSDoctorListDto> wsDoctors) throws Exception {
        for (int i = 0; i < wsDoctors.size(); i++) {
            WSDoctorListDto wd = wsDoctors.get(i);
            wd.setWsDoctors(this.getWSDoctorDtosList(wd));
        }
        return wsDoctors;
    }

    private List<WSDoctorDto> getWSDoctorDtosList(WSDoctorListDto wd) throws Exception {
        List<WSDoctorDto> list = new ArrayList<WSDoctorDto>();
        Object[] stats = new Object[]{1, 4};
        for (int i = 1; i < 8; i++) {
            String time = this.getFormatDate(i);
            for (int j = 1; j < 3; j++) {
                List<RegPipelined> rps = registerDao.getwsDoctorListsByWdAndTimeAndWorkType(wd, time, j);
                WSDoctorDto ws = new WSDoctorDto();
                ws.setOrder((i - 1) * 2 + j);
                ws.setWorkDate(TimeUtil.formatDate3(time, "yyyy-MM-dd"));
                ws.setWorkType(j);

//				for(RegPipelined rp:rps){
//					Integer num=commonDao.getCount(DetachedCriteria.forClass(RegOrder.class).add(Restrictions.in("state", stats)).createCriteria("regPipelined").add(Restrictions.eq("code", rp.getCode())));
//					if((num==null&&rp.getMaxReg()>0)||(num!=null&&num.intValue()<rp.getMaxReg()&&rp.getMaxReg()>0)){
//						ws.setIsFull(1);
//						break;
//					}
//				}
                if (rps.size() > 0) {
                    ws.setCode(rps.get(0).getCode());
                    if (this.pangDuanShiJian(i)) {
                        ws.setIsFull(8);//第7天号时间未到
                    } else {
                        if (rps.get(0).getState() == 1) {
                            ws.setIsFull(9);//已停诊
                        } else {
                            ws.setIsFull(1);
                        }
                    }
                    list.add(ws);
                } else {
                    list.add(null);
                }

            }
        }

        return list;
    }

    /**
     * 进行第7天下午3点时间判断
     *
     * @param i
     * @return
     * @Title: pangDuanShiJian
     * @author:gaowm
     * @date:2013-1-4 上午10:27:12
     */
    private boolean pangDuanShiJian(int i) {
        if (i == 7) {
            GregorianCalendar calendar = new GregorianCalendar();
            if (calendar.get(Calendar.HOUR_OF_DAY) < Integer.parseInt(Flag.read("obsoleteHour").trim())) {
                return true;
            } else if (calendar.get(Calendar.HOUR_OF_DAY) == Integer.parseInt(Flag.read("obsoleteHour").trim())) {
                if (calendar.get(Calendar.MINUTE) < Integer.parseInt(Flag.read("obsoleteMinute").trim())) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private String getFormatDate(int num) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_YEAR, num);
        return TimeUtil.formatDate1(calendar.getTime(), "yyyy-MM-dd");
    }

	/*public List<TimeWorkSchemaDto> getRegPipelineds(int workType, Date workDate,
            String doctorid, String departid) throws  ServiceException {
		Object[] stats=new Object[]{1,4};
		List<TimeWorkSchemaDto> timeList=new ArrayList<TimeWorkSchemaDto>();
		List<RegPipelined> list=new ArrayList<RegPipelined>();
		try {
			list=registerDao.getRegPipelined(workType,workDate,doctorid,departid);
			for (int i = 0; i < list.size(); i++) {
				RegPipelined rp=(RegPipelined)list.get(i);
				Integer num=commonDao.getCount(DetachedCriteria.forClass(RegOrder.class).add(Restrictions.in("state", stats)).createCriteria("regPipelined").add(Restrictions.eq("code", rp.getCode())));
				if((num==null&&rp.getMaxReg()>0)||(num!=null&&num.intValue()<rp.getMaxReg()&&rp.getMaxReg()>0)){
					timeList.add(new TimeWorkSchemaDto(num.intValue(),rp));
				}
			}
		}  catch (DaoException e) {
			e.printStackTrace();
			throw  new ServiceException("RegisterServiceImpl.getRegPipelineds:系统异常1"+e.getMessage(),e);
		} catch (Exception e) {
			e.printStackTrace();
			throw  new ServiceException("RegisterServiceImpl.getRegPipelineds:系统异常2"+e.getMessage(),e);
		}
		return timeList;
	}*/

    public List<TimeWorkSchemaDto> getRegPipelineds(int workType, Date workDate,
                                                    String doctorid, String departid, Long workScheamId, String hospitalCode) throws ServiceException {
        List<TimeWorkSchemaDto> timeList = new ArrayList<TimeWorkSchemaDto>();
        RegPipelined reg = new RegPipelined();
        try {
            reg = (RegPipelined) commonDao.get(RegPipelined.class, workScheamId);
            if (reg == null) {
                return timeList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("RegisterServiceImpl.getRegPipelineds:系统异常1" + e.getMessage(), e);
        }
        //调取智慧医疗接口查询排班分时段等信息
        yhtRegPortType service = new yhtRegClient().getyhtRegHttpPort();
        String str = RealTimeReserve.reqWorkTime(hospitalCode, departid, doctorid, TimeUtil.formatDate1(workDate, "yyyy-MM-dd"), (long) workType);
        String result = service.getWorkTime(str);
        System.out.println(result);
        List list = RealTimeReserve.rspWorkTime(result);
        for (int i = 0; i < list.size(); i++) {
            Object[] objs = (Object[]) list.get(i);
            TimeWorkSchemaDto timewsd = new TimeWorkSchemaDto();
            timewsd.setRegPipelined(reg);
            if (objs[0] == null || "".equals(objs[0].toString().trim())) {
                if (workType == 1) {
                    timewsd.setStartTime("7:30");
                    timewsd.setEndTime("11:30");
                } else {
                    timewsd.setStartTime("12:30");
                    timewsd.setEndTime("16:00");
                }
            } else {
//				String kssj = "";
//				if (objs[1].toString().length() < 4) {
//					kssj = objs[1].toString().substring(0, 1)+":"+objs[1].toString().substring(1,3);
//				} else {
//					kssj = objs[1].toString().substring(0, 2)+":"+objs[1].toString().substring(2,4);
//				}
//				String jssj = "";
//				if (objs[2].toString().length() < 4) {
//					jssj = objs[2].toString().substring(0, 1)+":"+objs[2].toString().substring(1,3);
//				} else {
//					jssj = objs[2].toString().substring(0, 2)+":"+objs[2].toString().substring(2,4);
//				}
//				timewsd.setStartTime(kssj);
//				timewsd.setEndTime(jssj);
                timewsd.setStartTime(objs[1].toString().trim());
                timewsd.setEndTime(objs[2].toString().trim());
            }
            timewsd.setTotalNum(Long.parseLong(objs[3].toString()));
            timewsd.setNum(Integer.parseInt(Long.parseLong(objs[3].toString()) - Long.parseLong(objs[4].toString()) + ""));
            timeList.add(timewsd);
        }
        return timeList;
    }


    public RegPeople getRegPeople(String cardCode) throws ServiceException {
        RegPeople r = null;
        try {
            r = (RegPeople) commonDao.unique(
                    DetachedCriteria.forClass(RegPeople.class)
                            .add(Restrictions.eq("identityCard", cardCode)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("RegisterServiceImpl.getRegPeople:系统异常" + e.getMessage(), e);
        }
        return r;
    }

    public List<RegPeople> getRegPeoples(RegPeople logPeople)
            throws ServiceException {
        List<RegPeople> r = null;
        try {
            r = commonDao.queryList("select distinct r.treatPeople from RegOrder r where r.regPeople.id=" + logPeople.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("RegisterServiceImpl.getRegPeoples:系统异常" + e.getMessage(), e);
        }
        return r;
    }

    public List<WSDepartListDto> getWsDepartListDtos(Hospital hospital,
                                                     String deptName, Page page) throws ServiceException {
        List<WSDepartListDto> wsDeparts = new ArrayList<WSDepartListDto>();
        try {
            wsDeparts = registerDao.getWsDepartLists(hospital, deptName, page);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("RegisterServiceImpl.getWsDepartListDtos:系统异常1" + e.getMessage(), e);
        }

        try {
            wsDeparts = this.getWSDepartDtos(wsDeparts);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("RegisterServiceImpl.getWsDepartListDtos:系统异常2" + e.getMessage(), e);
        }

        return wsDeparts;
    }

    private List<WSDepartListDto> getWSDepartDtos(
            List<WSDepartListDto> wsDeparts) throws Exception {
        Object[] stats = new Object[]{1, 4};
        for (int i = 0; i < wsDeparts.size(); i++) {
            WSDepartListDto ws = wsDeparts.get(i);
            List<RegPipelined> rps = registerDao.getDepartListRegPipelinedsByDepart(ws.getDepart());
            if (rps.size() > 0) {
                RegPipelined rp = rps.get(0);
                Integer num = commonDao.getCount(DetachedCriteria.forClass(RegOrder.class).add(Restrictions.in("state", stats)).createCriteria("regPipelined").add(Restrictions.eq("code", rp.getCode())));
                ws.setRegNum(ws.getRegNum() + num.intValue());
                ws.setCode(rp.getCode());
                if (rp.getState().intValue() == 2) {
                    ws.setIsFull(0);
                } else {
                    ws.setIsFull(1);
                }
            } else {
                wsDeparts.remove(i);
            }
        }

        return wsDeparts;
    }

    public List<WSDepartListDto> getWsDepartListDtosByDoctor(Depart depart,
                                                             Page page) throws ServiceException {
        List<WSDepartListDto> wsDeparts = new ArrayList<WSDepartListDto>();
        try {
            wsDeparts = registerDao.getWsDepartListDtosByDoctor(depart, page);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("RegisterServiceImpl.getWsDepartListDtosByDoctor:系统异常1" + e.getMessage(), e);
        }

        try {
            wsDeparts = this.getWSDepartDtos(wsDeparts);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("RegisterServiceImpl.getWsDepartListDtosByDoctor:系统异常2" + e.getMessage(), e);
        }

        return wsDeparts;
    }

    public List<Hospital> gethospitals() throws ServiceException {
        List<Hospital> list = new ArrayList<Hospital>();
        try {
            list = commonDao.queryList(" from Hospital ");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("RegisterServiceImpl.gethospitals:系统异常1" + e.getMessage(), e);
        }
        return list;
    }

    public List<TimeWorkSchemaDto> getRegPipelinedsByDepart(Depart depart, Long workid)
            throws ServiceException {
//		Object[] stats=new Object[]{1,4};
//		long worktype = TimeUtil.getworkType();
//		List<TimeWorkSchemaDto> timeList=new ArrayList<TimeWorkSchemaDto>();
//		List<RegPipelined> list=new ArrayList<RegPipelined>();
//		try {
//			list=registerDao.getRegPipelinedByDepart(departCardNo);
//			for (int i = 0; i < list.size(); i++) {
//				RegPipelined rp=(RegPipelined)list.get(i);
//				Integer num=commonDao.getCount(DetachedCriteria.forClass(RegOrder.class).add(Restrictions.in("state", stats)).createCriteria("regPipelined").add(Restrictions.eq("code", rp.getCode())));
//				TimeWorkSchemaDto twsd=new TimeWorkSchemaDto();
//				twsd.setRegPipelined(rp);
//				twsd.setNum(num==null?0:num);
//				if(worktype==1){
//					twsd.setStartTime("7:30");
//					twsd.setEndTime("11:30");
//				}else{
//					twsd.setStartTime("12:30");
//					twsd.setEndTime("16:00");
//				}
//				timeList.add(twsd);
//			}
//		}  catch (DaoException e) {
//			e.printStackTrace();
//			throw  new ServiceException("RegisterServiceImpl.getRegPipelinedsByDepart:系统异常1"+e.getMessage(),e);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw  new ServiceException("RegisterServiceImpl.getRegPipelinedsByDepart:系统异常2"+e.getMessage(),e);
//		}
//		return timeList;


        List<TimeWorkSchemaDto> timeList = new ArrayList<TimeWorkSchemaDto>();
        RegPipelined reg = new RegPipelined();
        try {
            reg = (RegPipelined) commonDao.get(RegPipelined.class, workid);
            depart = (Depart) commonDao.get(Depart.class, depart.getDepartCodeNo());
            if (reg == null) {
                return timeList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("RegisterServiceImpl.getRegPipelinedsByDepart:系统异常1" + e.getMessage(), e);
        }
        //调取智慧医疗接口查询排班分时段等信息
        yhtRegPortType service = new yhtRegClient().getyhtRegHttpPort();
        String str = RealTimeReserve.reqDepartWorkTime(depart.getHospital().getHospitalCode(), depart.getDepartCodeNo(), TimeUtil.formatDate1(reg.getWorkSchema().getWorkDate(), "yyyy-MM-dd"), (long) reg.getWorkType());
        String result = service.getDpWorkTime(str);
        System.out.println(result);
        List list = RealTimeReserve.rspDepartWorkTime(result);
        for (int i = 0; i < list.size(); i++) {
            Object[] objs = (Object[]) list.get(i);
            TimeWorkSchemaDto timewsd = new TimeWorkSchemaDto();
            timewsd.setRegPipelined(reg);
            if (objs[0] == null || "".equals(objs[0].toString().trim())) {
                if (reg.getWorkType() == 1) {
                    timewsd.setStartTime("7:30");
                    timewsd.setEndTime("11:30");
                } else {
                    timewsd.setStartTime("12:30");
                    timewsd.setEndTime("16:00");
                }
            } else {
//				String kssj = "";
//				if (objs[1].toString().length() < 4) {
//					kssj = objs[1].toString().substring(0, 1)+":"+objs[1].toString().substring(1,3);
//				} else {
//					kssj = objs[1].toString().substring(0, 2)+":"+objs[1].toString().substring(2,4);
//				}
//				String jssj = "";
//				if (objs[2].toString().length() < 4) {
//					jssj = objs[2].toString().substring(0, 1)+":"+objs[2].toString().substring(1,3);
//				} else {
//					jssj = objs[2].toString().substring(0, 2)+":"+objs[2].toString().substring(2,4);
//				}
//				timewsd.setStartTime(kssj);
//				timewsd.setEndTime(jssj);
                timewsd.setStartTime(objs[1].toString().trim());
                timewsd.setEndTime(objs[2].toString().trim());
            }
            timewsd.setTotalNum(Long.parseLong(objs[3].toString()));
            timewsd.setNum(Integer.parseInt(Long.parseLong(objs[3].toString()) - Long.parseLong(objs[4].toString()) + ""));
            timeList.add(timewsd);
        }
        return timeList;
    }


    /**
     * 科室分时段之前的方法
     * <p/>
     * public List<TimeWorkSchemaDto> getRegPipelinedsByDepart(String departCardNo,Long workid)
     * throws ServiceException {
     * Object[] stats=new Object[]{1,4};
     * long worktype = TimeUtil.getworkType();
     * List<TimeWorkSchemaDto> timeList=new ArrayList<TimeWorkSchemaDto>();
     * List<RegPipelined> list=new ArrayList<RegPipelined>();
     * try {
     * list=registerDao.getRegPipelinedByDepart(departCardNo);
     * for (int i = 0; i < list.size(); i++) {
     * RegPipelined rp=(RegPipelined)list.get(i);
     * Integer num=commonDao.getCount(DetachedCriteria.forClass(RegOrder.class).add(Restrictions.in("state", stats)).createCriteria("regPipelined").add(Restrictions.eq("code", rp.getCode())));
     * TimeWorkSchemaDto twsd=new TimeWorkSchemaDto();
     * twsd.setRegPipelined(rp);
     * twsd.setNum(num==null?0:num);
     * if(worktype==1){
     * twsd.setStartTime("7:30");
     * twsd.setEndTime("11:30");
     * }else{
     * twsd.setStartTime("12:30");
     * twsd.setEndTime("16:00");
     * }
     * timeList.add(twsd);
     * }
     * }  catch (DaoException e) {
     * e.printStackTrace();
     * throw  new ServiceException("RegisterServiceImpl.getRegPipelinedsByDepart:系统异常1"+e.getMessage(),e);
     * } catch (Exception e) {
     * e.printStackTrace();
     * throw  new ServiceException("RegisterServiceImpl.getRegPipelinedsByDepart:系统异常2"+e.getMessage(),e);
     * }
     * return timeList;
     * }
     */
    public MethodCallBean<Hospital> getHospitalByworkDate(Date workDate)
            throws ServiceException {
        MethodCallBean<Hospital> call = new MethodCallBean<Hospital>();
        List<Hospital> list = new ArrayList<Hospital>();
        try {
            //list=registerDao.getHospitalByworkDate(workDate);
            list = commonDao.queryList(" from Hospital h");
            if (list.size() > 0) {
                call.setMsg(true, "查询成功");
                call.setObjects(list);
            } else {
                call.setMsg(true, "暂无数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("RegisterServiceImpl.getHospitalByworkDate:系统异常1" + e.getMessage(), e);
        }
        return call;
    }

    public MethodCallBean<Depart> getDepartByworkDateAndHopital(Date workDate, String code)
            throws ServiceException {
        MethodCallBean<Depart> call = new MethodCallBean<Depart>();
        List<Depart> list = new ArrayList<Depart>();
        try {
            list = registerDao.getDepartByworkDateAndHopital(workDate, code);
            if (list.size() > 0) {
                call.setMsg(true, "查询成功");
                call.setObjects(list);
            } else {
                call.setMsg(true, "暂无数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("RegisterServiceImpl.getDepartByworkDateAndHopital:系统异常1" + e.getMessage(), e);
        }
        return call;
    }

    public MethodCallBean<DoctorExpert> getDoctorByworkDateAndDepart(Date workDate, String code)
            throws ServiceException {
        MethodCallBean<DoctorExpert> call = new MethodCallBean<DoctorExpert>();
        List<DoctorExpert> list = new ArrayList<DoctorExpert>();
        try {
            list = registerDao.getDoctorByworkDateAndDepart(workDate, code);
            if (list.size() > 0) {
                call.setMsg(true, "查询成功");
                call.setObjects(list);
            } else {
                call.setMsg(true, "暂无数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("RegisterServiceImpl.getDoctorByworkDateAndDepart:系统异常1" + e.getMessage(), e);
        }
        return call;
    }


    @Transactional
    public MethodCallBean<RegOrder> saveRegister(RegOrder regOrder)
            throws ServiceException {
        MethodCallBean<RegOrder> call = new MethodCallBean<RegOrder>();
        yhtRegPortType service = new yhtRegClient().getyhtRegHttpPort();
        try {
            long ts = Calendar.getInstance().getTimeInMillis();
            String xml = service.uploadRegInfo(
                    RealTimeReserve.uploadRegInfoRequest(regOrder)
            );
            long te = Calendar.getInstance().getTimeInMillis();

            System.out.println(((te - ts) / 1000) + " sec");

            Properties str = RealTimeReserve.uploadRegInfoRsp(xml);


            System.out.println();

            if ("0000".equals(str.get("code").toString().trim())) {
                regOrder.setCheckCode(str.get("checkNo").toString().trim());
                regOrder.setYLId(Long.parseLong(str.get("regId").toString().trim()));
                regOrder.setTreatOrder(Integer.parseInt(str.get("returnValue").toString().trim()));
                regOrder.setIdealRegisterTime(RealTimeReserve.havaReisterTimeForPeople(regOrder));
                regOrder.setCreatTimeee(new Date());
                regOrder = RealTimeReserve.perfectRegInfo(regOrder);
                commonDao.save(regOrder);
                call.setMsg(true, "挂号成功");
            } else {
                call.setMsg(false, str.get("returnValue").toString().trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("RegisterServiceImpl.saveRegister:系统异常1" + e.getMessage(), e);
        }
        return call;
    }

    public MethodCallBean<Depart> getDepartByworkDateAndHopitalFromTime(String code)
            throws ServiceException {
        MethodCallBean<Depart> call = new MethodCallBean<Depart>();
        List<Depart> list = new ArrayList<Depart>();
        try {
            list = registerDao.getDepartByworkDateAndHopitalFromTime(code);
            if (list.size() > 0) {
                call.setMsg(true, "查询成功");
                call.setObjects(list);
            } else {
                call.setMsg(true, "暂无数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("RegisterServiceImpl.getDepartByworkDateAndHopitalFromTime:系统异常1" + e.getMessage(), e);
        }
        return call;
    }

    @Transactional
    public MethodCallBean<RegPeople> savePeople(RegPeople regPeople)
            throws ServiceException {
        MethodCallBean<RegPeople> call = new MethodCallBean<RegPeople>();
        try {
            commonDao.save(regPeople);
            call.setMsg(true, "保存成功");
            call.setObject(regPeople);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("RegisterServiceImpl.savePeople:系统异常1" + e.getMessage(), e);
        }
        return call;
    }

    @Transactional
    public MethodCallBean<RegPeople> saveOrUpdatePeople(RegPeople regPeople)
            throws ServiceException {
        MethodCallBean<RegPeople> call = new MethodCallBean<RegPeople>();
        try {
            commonDao.saveOrUpdate(regPeople);
            call.setMsg(true, "保存成功");
            call.setObject(regPeople);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("RegisterServiceImpl.savePeople:系统异常1" + e.getMessage(), e);
        }
        return call;
    }

    @Transactional
    public MethodCallBean<RegPeople> updatePeople(RegPeople regPeople)
            throws ServiceException {
        MethodCallBean<RegPeople> call = new MethodCallBean<RegPeople>();
        try {

//			RegPeople r=(RegPeople) commonDao.get(RegPeople.class, regPeople.getId());
            RegPeople r = this.getRegPeople(regPeople.getIdentityCard());
            r.setIdentityCard(regPeople.getIdentityCard());
            r.setLastUser(regPeople.getLastUser());
            r.setMedicalNo(regPeople.getMedicalNo());
            r.setMedicalType(regPeople.getMedicalType());
            r.setMobile(regPeople.getMobile());
            r.setSex(regPeople.getSex());
            r.setTrueName(regPeople.getTrueName());
            commonDao.save(r);
            call.setMsg(true, "更新成功");
            call.setObject(r);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("RegisterServiceImpl.updatePeople:系统异常1" + e.getMessage(), e);
        }
        return call;
    }

    public RegPipelined getRegPiplinedById(long code) throws ServiceException {
        RegPipelined r = new RegPipelined();
        try {
            r = (RegPipelined) commonDao.get(RegPipelined.class, code);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("RegisterServiceImpl.getRegPiplinedById:系统异常1" + e.getMessage(), e);
        }
        return r;
    }

    //hch
    public List<RegOrder> getRegOrders(String id, String name) throws ServiceException {
        try {
            return regOrderDao.queryRegOrders(id, name);
        } catch (Exception e) {
            throw new ServiceException("RegisterServiceImpl.getRegOrders:系统异常1" + e.getMessage(), e);
        }
    }

    public List<RegOrder> getRegOrders(Hospital hospital, String departName,
                                       String doctorName, String userName, String registerName,
                                       Date beginTime, Date endTime, Integer status, Page page, String telPhone, Date regStratTime, Date regEndTime) throws ServiceException {
        try {
            return regOrderDao.queryRegOrders(hospital, departName, doctorName, userName, registerName, beginTime, endTime, status, page, telPhone, regStratTime, regEndTime);
        } catch (Exception e) {
            throw new ServiceException("RegisterServiceImpl.getRegOrders:系统异常1" + e.getMessage(), e);
        }
    }

    public RegOrder getRegOrder(long code) throws ServiceException {
        RegOrder regOrder = new RegOrder();
        try {
            regOrder = (RegOrder) commonDao.unique(DetachedCriteria.forClass(RegOrder.class).add(Restrictions.eq("code", code)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("RegisterServiceImpl.getRegOrder:系统异常1" + e.getMessage(), e);
        }
        return regOrder;
    }

    private boolean canQuit(RegOrder regOrder) {
        Calendar now = Calendar.getInstance();
        Calendar reg = Calendar.getInstance();
        reg.setTime(regOrder.getWorkDate());

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");

        String endTimeString = Config.getString("doc_next_day_reg_end_time").trim();
        String nowTimeString = sdf.format(now.getTime());

        int days = reg.get(Calendar.DAY_OF_YEAR) - now.get(Calendar.DAY_OF_YEAR);
        if (days < 2 && nowTimeString.compareTo(endTimeString) > 0)
            return false;

        return true;
    }

    public MethodCallBean updateRegister(long code) throws ServiceException {
        MethodCallBean<RegOrder> call = new MethodCallBean<RegOrder>();
        RegOrder regOrder = new RegOrder();
        try {
            regOrder = (RegOrder) commonDao.unique(DetachedCriteria.forClass(RegOrder.class).add(Restrictions.eq("code", code)));
            if (regOrder.getState() != 1) {
                call.setMsg(false, "不能退号");
                return call;
            }

            if(!canQuit(regOrder)) {
                call.setMsg(false, Config.getString("doc_next_day_reg_end_time") + "后，不能退第二天的专家号");
                return call;
            }

            yhtRegPortType service = new yhtRegClient().getyhtRegHttpPort();
            String str = RealTimeReserve.backRegNo(regOrder.getYLId() + "");
            Properties propers = RealTimeReserve.uploadRegInfoBack(service.backRegNo(str));
            if ("0000".equals(propers.get("code").toString().trim())) {
                regOrder.setState(2);
                regOrder.setQuitType(0);
                regOrder.setQuitTimeee(new Date());

                //regOrder.setCanclePeople((User) ServletActionContext.getContext().getSession().get("admin"));
                //regOrder.setCancleName(regOrder.getCanclePeople().getTrueName());

                //hch 2013/06/27
                commonDao.saveOrUpdate(regOrder);
                call.setMsg(true, "退号成功");
            } else {
                call.setMsg(false, propers.get("msg").toString().trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
            call.setMsg(false, "退号失败");
        }
        return call;
    }

    public MethodCallBean queryQuitNum(Long code) throws ServiceException {
        MethodCallBean<RegOrder> call = new MethodCallBean<RegOrder>();
        RegOrder regOrder = new RegOrder();
        try {
            int num = regOrderDao.queryDaoQuitNum(code);
            if (num >= 0) {
                call.setMsg(true, "查询成功");
                call.setState(num);
            } else {
                call.setMsg(false, "系统出错");
                call.setState(-1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            call.setMsg(false, "系统出错");
            call.setState(-1);
        }
        return call;
    }

    public MethodCallBean queryQuitNum(String pid) throws ServiceException {
        MethodCallBean<RegOrder> call = new MethodCallBean<RegOrder>();
        RegOrder regOrder = new RegOrder();
        try {
            int num = regOrderDao.queryDaoQuitNum(pid);
            if (num >= 0) {
                call.setMsg(true, "查询成功");
                call.setState(num);
            } else {
                call.setMsg(false, "系统出错");
                call.setState(-1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            call.setMsg(false, "系统出错");
            call.setState(-1);
        }
        return call;
    }

    public void updateRegisterByError(String ylid) {
        try {
            if (ylid != null && !"".equals(ylid.trim())) {
                yhtRegPortType service = new yhtRegClient().getyhtRegHttpPort();
                String str = RealTimeReserve.backRegNo(ylid);
                String msg = service.backRegNo(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public List<RegPeople> queryBlackPeople(String telPhone,
                                            String registerName, Page page, Integer isBlack) throws ServiceException {
        List<RegPeople> list = new ArrayList<RegPeople>();
        try {
            //更新黑名单数据的方法
            //regOrderDao.updateBlackPeople();
            list = regOrderDao.queryBlackPeople(telPhone, registerName, page, isBlack);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("RegisterServiceImpl.queryBlackPeople:系统异常1" + e.getMessage(), e);
        }
        return list;
    }

    public MethodCallBean getPeopleByCard(RegPeople regPeople)
            throws ServiceException {
        MethodCallBean<RegPeople> call = new MethodCallBean<RegPeople>();
        List<RegPeople> list = new ArrayList<RegPeople>();
        try {
            list = commonDao.queryList(" from RegPeople r where r.identityCard='" + regPeople.getIdentityCard().trim() + "' ");
            if (list.size() > 0) {
                call.setMsg(true, "查询成功");
                call.setObject(list.get(0));
            } else {
                call.setMsg(false, "暂无数据");
                call.setState(2l);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("RegisterServiceImpl.getHospitalByworkDate:系统异常1" + e.getMessage(), e);
        }
        return call;
    }

    public MethodCallBean updateBlack(long id, String removeReason, User user) throws ServiceException {
        MethodCallBean<RegPeople> call = new MethodCallBean<RegPeople>();
        RegPeople regPeople = new RegPeople();
        try {
            regPeople = (RegPeople) commonDao.unique(" from RegPeople r where r.id=" + id);
            regPeople.setCancelDate(new Date());
            regPeople.setRemoveReason(removeReason);
            regPeople.setLastUser(user);
            regPeople.setBreakPromiseCount(0);
            commonDao.update(regPeople);
            call.setMsg(true, "解除成功");
        } catch (Exception e) {
            e.printStackTrace();
            call.setMsg(false, "系统出错");
        }
        return call;
    }

    public List queryCallList(Page page, Date beginTime, Date endTime,
                              Integer type, User user, String userName, String telPhone) throws ServiceException {
        StringBuffer sql = new StringBuffer();
        List list = new ArrayList();
        if (type != null && type.intValue() == 1) {
            if (user.getRole().getId() != 1 && user.getRole().getId() != 2) {
                sql.append(" from CallOutLog c where c.outUser.id=").append(user.getId());
            } else {
                sql.append(" from CallOutLog c where 1=1 ");
            }
            if (beginTime != null) {
                sql.append(" and to_char(c.callTime,'yyyy-MM-dd') >='").append(TimeUtil.formatDate1(beginTime, "yyyy-MM-dd")).append("'");
            }
            if (endTime != null) {
                sql.append(" and  to_char(c.callTime,'yyyy-MM-dd') <= '").append(TimeUtil.formatDate1(endTime, "yyyy-MM-dd")).append("'");
            }
            if (userName != null && !userName.trim().equals("")) {
                sql.append(" and (c.outUser.softPhone.phoneNo like '%").append(userName).append("%'  or c.outUser.trueName like '%").append(userName).append("%' )");
            }
            if (telPhone != null && !telPhone.trim().equals("")) {
                sql.append(" and  c.toPhone like '%").append(telPhone).append("%' ");
            }
            sql.append(" order by c.callTime desc ");
        } else {
            if (user.getRole().getId() != 1 && user.getRole().getId() != 2) {
                sql.append(" from CallIn c where c.opeateESQ.id=").append(user.getId());
            } else {
                sql.append(" from CallIn c where 1=1 ");
            }
            if (beginTime != null) {
                sql.append(" and to_char(c.callInTime,'yyyy-MM-dd') >= '").append(TimeUtil.formatDate1(beginTime, "yyyy-MM-dd")).append("'");
            }
            if (endTime != null) {
                sql.append(" and  to_char(c.callInTime,'yyyy-MM-dd') <= '").append(TimeUtil.formatDate1(endTime, "yyyy-MM-dd")).append("'");
            }

            if (userName != null && !userName.trim().equals("")) {
                sql.append(" and (c.opeateESQ.softPhone.phoneNo like '%").append(userName).append("%'  or c.opeateESQ.trueName like '%").append(userName).append("%' )");
            }
            if (telPhone != null && !telPhone.trim().equals("")) {
                sql.append(" and  c.callInPhone like '%").append(telPhone).append("%' ");
            }
            sql.append(" order by c.callInTime desc ");
        }
        try {
            page.setTotalCount(commonDao.queryList(sql.toString()).size());
            list = commonDao.queryList(sql.toString(), page.getPageNum(), page.getPageSize());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("RegisterServiceImpl.queryCallList:系统异常1" + e.getMessage(), e);
        }
        if (type != null && type.intValue() == 1) {
            for (int i = 0; i < list.size(); i++) {
                CallOutLog col = (CallOutLog) list.get(i);
                if (col != null && col.getConnectTime() != null && col.getOffTime() != null) {
                    col.setSpareOne(this.havaminuter(col.getOffTime().getTime(), col.getConnectTime().getTime()));
                }
            }
        } else {
            for (int i = 0; i < list.size(); i++) {
                CallIn ci = (CallIn) list.get(i);
                if (ci != null && ci.getConnectTime() != null && ci.getOffTime() != null) {
                    ci.setSpareOne(this.havaminuter(ci.getOffTime().getTime(), ci.getConnectTime().getTime()));
                }
            }
        }
        return list;
    }

    private String havaminuter(Long time, Long time2) {
        Long num = time.longValue() - time2.longValue();
        if (num % (1000 * 60) == 0) {
            return num / (1000 * 60) + "分";
        } else {
            Long num2 = num % (1000 * 60);
            return (num.longValue() - num2.longValue()) / (1000 * 60) + "分" + num2.longValue() / 1000 + "秒";
        }
    }

    public List<RegOrder> getRegOrdersForBlack(long id) throws ServiceException {
        List<RegOrder> list = new ArrayList<RegOrder>();
        try {
            RegPeople regPeople = (RegPeople) commonDao.get(RegPeople.class, id);
            list = regOrderDao.getRegOrdersForBlack(regPeople);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("RegisterServiceImpl.getRegOrdersForBlack:系统异常1" + e.getMessage(), e);
        }
        return list;
    }

    public RegPeople getPeopleDetail(long id) throws ServiceException {
        RegPeople r = new RegPeople();
        try {
            r = (RegPeople) commonDao.get(RegPeople.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("RegisterServiceImpl.getPeopleDetail:系统异常1" + e.getMessage(), e);
        }
        return r;
    }

    public static void main(String[] args) {
//		try {
//			List list=common2Dao.queryListBySql();
//			for (int i = 0; i < list.size(); i++) {
//				System.out.println(list.get(i));
//			}
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
    }

    public void updateWorkTimeInfo() {
        Page page = new Page();
        page.setPageSize(10000);
        List<WSDoctorListDto> wsDoctors = new ArrayList<WSDoctorListDto>();
        try {
            wsDoctors = registerDao.getWsDoctorLists2();
        } catch (DaoException e) {
            e.printStackTrace();
            log.error("更新session排班信息出错" + TimeUtil.formatDate1(new Date(), "yyyy-MM-dd hh:mm:ss"));
        }
        if (sessionSerive.get("workTimeInfo") != null) {
            log.fatal("更新session排班信息成功" + TimeUtil.formatDate1(new Date(), "yyyy-MM-dd hh:mm:ss"));
            sessionSerive.clear();
        }
        sessionSerive.put("workTimeInfo", wsDoctors);
    }


}
