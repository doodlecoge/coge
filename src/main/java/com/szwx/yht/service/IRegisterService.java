package com.szwx.yht.service;

import com.szwx.yht.bean.*;
import com.szwx.yht.dto.MethodCallBean;
import com.szwx.yht.dto.TimeWorkSchemaDto;
import com.szwx.yht.dto.WSDepartListDto;
import com.szwx.yht.dto.WSDoctorListDto;
import com.szwx.yht.exception.ServiceException;
import com.szwx.yht.util.Page;

import java.util.Date;
import java.util.List;

public interface IRegisterService {
/**
 * 根据医生姓名，科室名等进行条件查询
 *@Title: getWsDoctorListDtos
 *@author:gaowm
 *@date:Jul 14, 2012 9:51:56 AM
 *@param docName
 *@param deptName
 *@param page
 *@return
 *@throws ServiceException
 */
	List<WSDoctorListDto> getWsDoctorListDtos(String docName, String deptName,
                                              Page page, String hospitalId)throws ServiceException;
/**
 * 根据医生id查询，对应日期内的所有排班
 *@Title: getWsDoctorListDtosByDoctor
 *@author:gaowm
 *@date:Jul 14, 2012 9:52:49 AM
 *@param doctor
 *@param page
 *@return
 */
List<WSDoctorListDto> getWsDoctorListDtosByDoctor(Doctor doctor, Page page)throws ServiceException;

/**
 * 查询出某天上午或下午的所有某医生时间段排版
 *@Title: getRegPipelineds
 *@author:gaowm
 *@date:Jul 14, 2012 6:48:55 PM
 *@param workType
 *@param workDate
 *@param doctorNo
 *@param departNo
 * @param workScheamId 
 * @param hospitalCode 
 *@return
 *@throws ServiceException
 */
List<TimeWorkSchemaDto> getRegPipelineds(int workType, Date workDate, String doctorNo, String departNo, Long workScheamId, String hospitalCode)throws ServiceException;

/**
 * 查询就诊人信息
 *@Title: getRegPeople
 *@author:gaowm
 *@date:Jul 15, 2012 10:33:28 AM
 *@param
 *@return
 *@throws ServiceException
 */
RegPeople getRegPeople(String cradCord)throws ServiceException;
/**
 * 根据挂号人，查询就诊人集合
 *@Title: getRegPeoples
 *@author:gaowm
 *@date:Jul 15, 2012 10:48:02 AM
 *@param logPeople
 *@return
 *@throws ServiceException
 */
List<RegPeople> getRegPeoples(RegPeople logPeople)throws ServiceException;
/**
 * 根据医院医生等查询条件分页查询科室排班信息
 *@Title: getWsDepartListDtos
 *@author:gaowm
 *@date:Jul 15, 2012 4:34:47 PM
 *@param hospital
 *@param deptName
 *@param page
 *@return
 *@throws ServiceException
 */
List<WSDepartListDto> getWsDepartListDtos(Hospital hospital, String deptName, Page page)throws ServiceException;
/**
 * 根据科室信息查询条件分页查询科室排班信息
 *@Title: getWsDepartListDtosByDoctor
 *@author:gaowm
 *@date:Jul 15, 2012 4:35:16 PM
 *@param depart
 *@param page
 *@return
 *@throws ServiceException
 */
List<WSDepartListDto> getWsDepartListDtosByDoctor(Depart depart, Page page)throws ServiceException;
/**
 * 查询出所有的有效医院
 *@Title: gethospitals
 *@author:gaowm
 *@date:Jul 16, 2012 10:28:08 AM
 *@return
 *@throws ServiceException
 */
List<Hospital> gethospitals()throws ServiceException;
/**
 * 查询某科室对应的实时排班
 *@Title: getRegPipelinedsByDepart
 *@author:gaowm
 *@date:Jul 16, 2012 11:20:20 AM
 *@param
 *@return
 *@throws ServiceException
 */
//List<TimeWorkSchemaDto> getRegPipelinedsByDepart(String departCardNo,Long workid)throws ServiceException;
List<TimeWorkSchemaDto> getRegPipelinedsByDepart(Depart depart, Long workid)throws ServiceException;
/**
 * 根据时间查询有专家排版的医院
 *@Title: getHospitalByworkDate
 *@author:gaowm
 *@date:Jul 16, 2012 2:59:11 PM
 *@param workDate
 *@return
 *@throws ServiceException
 */
MethodCallBean getHospitalByworkDate(Date workDate)throws ServiceException;
/**
 * 根据时间医院查询出有排版的科室
 *@Title: getDepartByworkDateAndHopital
 *@author:gaowm
 *@date:Jul 16, 2012 3:59:00 PM
 *@param workDate
 *@param code
 *@return
 *@throws ServiceException
 */
MethodCallBean getDepartByworkDateAndHopital(Date workDate, String code)throws ServiceException;

/**
 * 根据时间和科室查询有排班的医生
 *@Title: getDoctorByworkDateAndDepart
 *@author:gaowm
 *@date:Jul 16, 2012 3:59:05 PM
 *@param workDate
 *@param code
 *@return
 *@throws ServiceException
 */
MethodCallBean getDoctorByworkDateAndDepart(Date workDate, String code)throws ServiceException;

/**
 * 挂号
 *@Title: saveRegister
 *@author:gaowm
 *@date:Jul 16, 2012 9:42:04 PM
 *@param regOrder
 *@return
 *@throws ServiceException
 */
MethodCallBean saveRegister(RegOrder regOrder)throws ServiceException;
/**
 * 根据时间医院查询出实时挂号有排版的科室
 *@Title: getDepartByworkDateAndHopitalFromTime
 *@author:gaowm
 *@date:Jul 17, 2012 2:05:20 PM
 *@param code
 *@return
 *@throws ServiceException
 */
MethodCallBean getDepartByworkDateAndHopitalFromTime(String code)throws ServiceException;
/**
 * 保存用户
 *@Title: savePeople
 *@author:gaowm
 *@date:Jul 17, 2012 4:25:49 PM
 *@param regPeople
 *@return
 *@throws ServiceException
 */
MethodCallBean savePeople(RegPeople regPeople)throws ServiceException;
MethodCallBean saveOrUpdatePeople(RegPeople regPeople)throws ServiceException;
/**
 * 更新用户
 *@Title: updatePeople
 *@author:gaowm
 *@date:Jul 17, 2012 4:26:04 PM
 *@param regPeople
 *@return
 *@throws ServiceException
 */
MethodCallBean updatePeople(RegPeople regPeople)throws ServiceException;
/**
 * 出错退号
 *@Title: updateRegisterByError
 *@author:gaowm
 *@date:Sep 6, 2012 1:49:05 PM
 *@param ylid
 */
void updateRegisterByError(String ylid);
/**
 * 查某个排班
 *@Title: getRegPiplinedById
 *@author:gaowm
 *@date:Jul 17, 2012 6:20:51 PM
 *@param code
 *@return
 *@throws ServiceException
 */
RegPipelined getRegPiplinedById(long code)throws ServiceException;
/**
 * 条件查询挂号信息
 *@Title: getRegOrders
 *@author:gaowm
 *@date:Jul 19, 2012 9:54:26 AM
 *@param hospital
 *@param departName
 *@param doctorName
 *@param userName
 *@param registerName
 *@param beginTime
 *@param endTime
 *@param page
 *@return
 *@throws ServiceException
 */
List<RegOrder> getRegOrders(Hospital hospital, String departName,
                            String doctorName, String userName, String registerName,
                            Date beginTime, Date endTime, Integer status, Page page, String telPhone, Date regStratTime, Date regEndTime)throws ServiceException;


    List<RegOrder> getRegOrders(String id, String name)throws ServiceException;

    /**
 * code查询某条挂号记录
 *@Title: getRegOrder
 *@author:gaowm
 *@date:Jul 19, 2012 1:43:23 PM
 *@param code
 *@return
 *@throws ServiceException
 */
RegOrder getRegOrder(long code)throws ServiceException;
/**
 * 退号
 *@Title: updateRegister
 *@author:gaowm
 *@date:Jul 20, 2012 4:17:49 PM
 *@param code
 *@return
 *@throws ServiceException
 */
MethodCallBean updateRegister(long code)throws ServiceException;
/**
 * 查询黑名单
 *@Title: queryBlackPeople
 *@author:gaowm
 *@date:Jul 20, 2012 5:41:40 PM
 *@param telPhone
 *@param registerName
 *@param page
 * @param isBlack 
 *@return
 *@throws ServiceException
 */
List<RegPeople> queryBlackPeople(String telPhone, String registerName, Page page, Integer isBlack)throws ServiceException;

/**
 *通过身份证号查询用户
 *@Title: getPeopleByCard
 *@author:gaowm
 *@date:Aug 1, 2012 8:13:55 PM
 *@param regPeople
 *@return
 *@throws ServiceException
 */
MethodCallBean getPeopleByCard(RegPeople regPeople)throws ServiceException;
/**
 * 解除黑名单
 *@Title: updateBlack
 *@author:gaowm
 *@date:Aug 16, 2012 3:52:30 PM
 *@param id
 * @param user 
 * @param removeReason 
 *@return
 *@throws ServiceException
 */
MethodCallBean updateBlack(long id, String removeReason, User user)throws ServiceException;
List queryCallList(Page page, Date beginTime, Date endTime, Integer type,
                   User user, String userName, String telPhone)throws ServiceException;
/**
 * 查询黑名单中人员的挂号违规具体信息
 *@Title: getRegOrdersForBlack
 *@author:gaowm
 *@date:2012-9-28 下午02:52:43
 *@param id
 *@return
 *@throws ServiceException
 */
List<RegOrder> getRegOrdersForBlack(long id)throws ServiceException;
RegPeople getPeopleDetail(long id)throws ServiceException;
MethodCallBean queryQuitNum(Long code)throws ServiceException;
MethodCallBean queryQuitNum(String pid)throws ServiceException;

void updateWorkTimeInfo();
List<WSDoctorListDto> getWsDoctorListDtosimpl(String docName, String deptName,
                                              Page page, String hospitalId)throws ServiceException;


	
	

}
