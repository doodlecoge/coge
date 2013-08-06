package com.szwx.yht.dao;

import com.szwx.yht.bean.*;
import com.szwx.yht.dto.WSDepartListDto;
import com.szwx.yht.dto.WSDoctorListDto;
import com.szwx.yht.exception.DaoException;
import com.szwx.yht.util.Page;

import java.util.Date;
import java.util.List;

public interface IRegisterDAO {
	/**
	 * 根据医生姓名，科室名称分页查询有排版的专家排班信息
	 *@Title: getWsDoctorLists
	 *@author:gaowm
	 *@date:Jul 14, 2012 10:59:29 AM
	 *@param docName
	 *@param deptName
	 *@param page
	 *@return
	 *@throws DaoException
	 */
	List<WSDoctorListDto> getWsDoctorLists(String docName, String deptName,
                                           Page page, String hospitalId)throws DaoException;
	/**
	 * 根据医生，科室，排班日期，上下午查出对应的排班信息
	 *@Title: getwsDoctorListsByWdAndTimeAndWorkType
	 *@author:gaowm
	 *@date:Jul 14, 2012 2:11:12 PM
	 *@param wd
	 *@param time
	 *@param j
	 *@return
	 *@throws DaoException
	 */
	List<RegPipelined> getwsDoctorListsByWdAndTimeAndWorkType(
            WSDoctorListDto wd, String time, int j)throws DaoException;
	/**
	 * 查询某日上下午的某专家的排班集合
	 *@Title: getRegPipelined
	 *@author:gaowm
	 *@date:Jul 14, 2012 7:07:24 PM
	 *@param workType
	 *@param workDate
	 *@param doctorid
	 *@param departid
	 *@return
	 *@throws DaoException
	 */
	List<RegPipelined> getRegPipelined(int workType, Date workDate,
                                       String doctorid, String departid)throws DaoException;
	/**
	 * 根据医生分页查询有排版的专家排班信息
	 *@Title: getWsDoctorLists
	 *@author:gaowm
	 *@date:Jul 14, 2012 8:24:24 PM
	 *@param doctor
	 *@param page
	 *@return
	 *@throws DaoException
	 */
	List<WSDoctorListDto> getWsDoctorLists(Doctor doctor, Page page)throws DaoException;
	/**
	 * 根据医院科室信息分页查询科室排班信息
	 *@Title: getWsDepartLists
	 *@author:gaowm
	 *@date:Jul 15, 2012 4:43:04 PM
	 *@param hospital
	 *@param deptName
	 *@param page
	 *@return
	 *@throws DaoException
	 */
	List<WSDepartListDto> getWsDepartLists(Hospital hospital, String deptName,
                                           Page page)throws DaoException;
	/**
	 * 根据科室查询此时间段内的所有排班
	 *@Title: getDepartListRegPipelinedsByDepart
	 *@author:gaowm
	 *@date:Jul 15, 2012 5:22:18 PM
	 *@param depart
	 *@return
	 *@throws DaoException
	 */
	List<RegPipelined> getDepartListRegPipelinedsByDepart(Depart depart)throws DaoException;
	/**
	 * 查询某科室的实时排班
	 *@Title: getRegPipelinedByDepart
	 *@author:gaowm
	 *@date:Jul 16, 2012 11:23:45 AM
	 *@param id
	 *@return
	 *@throws DaoException
	 */
	List<RegPipelined> getRegPipelinedByDepart(String departCardNo)throws DaoException;
	/**
	 * 根据日期查询有排班医院
	 *@Title: getHospitalByworkDate
	 *@author:gaowm
	 *@date:Jul 16, 2012 3:05:43 PM
	 *@param workDate
	 *@return
	 *@throws DaoException
	 */
	List<Hospital> getHospitalByworkDate(Date workDate)throws DaoException;
	/**
	 * 根据时间和科室查询有排班的医生
	 *@Title: getDoctorByworkDateAndDepart
	 *@author:gaowm
	 *@date:Jul 16, 2012 4:03:10 PM
	 *@param workDate
	 *@param code
	 *@return
	 *@throws DaoException
	 */
	List<DoctorExpert> getDoctorByworkDateAndDepart(Date workDate, String code)throws DaoException;
	/**
	 * 根据时间医院查询出有排版的科室
	 *@Title: getDepartByworkDateAndHopital
	 *@author:gaowm
	 *@date:Jul 16, 2012 4:03:17 PM
	 *@param workDate
	 *@param code
	 *@return
	 *@throws DaoException
	 */
	List<Depart> getDepartByworkDateAndHopital(Date workDate, String code)throws DaoException;
	/**
	 * 根据科室id查询当前时间排版
	 *@Title: getWsDepartListDtosByDoctor
	 *@author:gaowm
	 *@date:Jul 16, 2012 4:08:43 PM
	 *@param depart
	 *@param page
	 *@return
	 *@throws DaoException
	 */
	List<WSDepartListDto> getWsDepartListDtosByDoctor(Depart depart, Page page)throws DaoException;
	/**
	 * 根据时间医院查询出实时挂号有排版的科室
	 *@Title: getDepartByworkDateAndHopitalFromTime
	 *@author:gaowm
	 *@date:Jul 17, 2012 2:06:45 PM
	 *@param code
	 *@return
	 *@throws DaoException
	 */
	List<Depart> getDepartByworkDateAndHopitalFromTime(String code)throws DaoException;
	/**
	 * 判断用户是否已挂过此排班
	 *@Title: isHaveRegister
	 *@author:gaowm
	 *@date:Jul 18, 2012 10:28:00 AM
	 *@param regOrder
	 *@return
	 *@throws DaoException
	 */
	boolean isHaveRegister(RegOrder regOrder)throws DaoException;
	/**
	 * 查询此时间段已退号的最小就诊序号
	 *@Title: getMinNumOrder
	 *@author:gaowm
	 *@date:Jul 18, 2012 11:37:41 AM
	 *@param regOrder
	 *@return
	 *@throws DaoException
	 */
	int getMinNumOrder(RegOrder regOrder)throws DaoException;
	List<WSDoctorListDto> getWsDoctorLists2()throws DaoException;

}
