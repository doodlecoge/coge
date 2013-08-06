package com.szwx.yht.service;

import com.szwx.yht.bean.CallIn;
import com.szwx.yht.bean.User;
import com.szwx.yht.common.CommonQuery;
import com.szwx.yht.dto.MethodCallBean;
import com.szwx.yht.exception.ServiceException;

import java.util.List;

/**
 * 电话呼入处理接口
 * @author zhangyj
 * @date Jun 28, 2012
 */
public interface ICallInService {
	/**
	 * 添加一条电话流水记录
	 * @author zhangyj
	 * @date Jun 28, 2012
	 * @param callIn
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public MethodCallBean saveCallIn(CallIn callIn)throws ServiceException;
	/**
	 * 按ID获取电话呼入记录
	 * @author zhangyj
	 * @date Jun 28, 2012
	 * @param id
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public CallIn getCallInById(int id)throws ServiceException;
	/**
	 * 呼入电话接通
	 * @author zhangyj
	 * @date Jun 28, 2012
	 * @param id
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public MethodCallBean<CallIn> callInConnect(CallIn callIn)throws ServiceException;
	/**
	 * 获取系统呼入流水信息
	 * @author zhangyj
	 * @date Jun 28, 2012
	 * @param connect 0：全部 1：未接听 2：接听成功
	 * @param commonQuery 其它查询条件
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public List<CallIn> getCallIns(int connect, CommonQuery commonQuery)throws ServiceException;
	/**
	 * 获取对应客服接收的来电信息
	 * @author zhangyj
	 * @date Jun 28, 2012
	 * @param user 客服用户 null:全部
	 * @param connect 0：全部 1：未接听 2：接听成功
	 * @param commonQuery 其它查询条件
	 * @param
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public List<CallIn> getCallIns(int connect, User user, CommonQuery commonQuery)throws ServiceException;
	/**
	 * 获取来电号码的呼入历史记录
	 * @author zhangyj
	 * @date Jun 28, 2012
	 * @param callInPhone
	 * @param commonQuery 其它查询条件
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public List<CallIn> getCallIns(String callInPhone, CommonQuery commonQuery)throws ServiceException;
	/**
	 * 获取来电号码的最近的一次呼入信息
	 * @author zhangyj
	 * @date Jun 28, 2012
	 * @param callInPhone
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public CallIn getCallIn(String callInPhone)throws ServiceException;
	/**
	 * 更新CallIn的信息
	 * @author zhangyj
	 * @date Aug 9, 2012
	 * @param callIn
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public MethodCallBean updateCallIn(CallIn callIn)throws ServiceException;
	/**
	 * 按IPA的电话流水号查询CallIn的信息
	 * @author zhangyj
	 * @date Aug 9, 2012
	 * @param workId
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public CallIn getCallInByWrok(String workId)throws ServiceException;
	/**
	 * 按用户名和通话日期查询呼入信息
	 * @author zhangyj
	 * @date Aug 9, 2012
	 * @param userName	客服用户名
	 * @param sDate		开始日期
	 * @param eDate		结束日期
	 * @param isConnect 呼入是否接通	1：未接 2：接通
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public List<CallIn> getCallIn(String userName, String sDate, String eDate, int isConnect)throws ServiceException;
	/**
	 * 按用户名和通话日期查询呼入信息总数
	 * @author zhangyj
	 * @date Aug 9, 2012
	 * @param userName	客服用户名
	 * @param sDate		开始日期
	 * @param eDate		结束日期
	 * @param isConnect 呼入是否接通	1：未接 2：接通
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public int getCallInCount(String userName, String sDate, String eDate, int isConnect)throws ServiceException;
	/**
	 * 按用户名和通话日期查询呼入信息总数
	 * @author zhangyj
	 * @date Aug 9, 2012
	 * @param userName	客服用户名
	 * @param date		日期
	 * @param isConnect 呼入是否接通	1：未接 2：接通
	 * @param sHour	开始时间  24小时制
	 * @param eHour 结束时间 24小时制
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public int getCallInCount(String userName, String date, int isConnect, int sHour, int eHour)throws ServiceException;
	/**
	 * 按用户名和通话日期查询呼入信息总数
	 * @author zhangyj
	 * @date Aug 9, 2012
	 * @param userName	客服用户名
	 * @param sdate	开始日期
	 * @param edate	截止日期
	 * @param isConnect 呼入是否接通	1：未接 2：接通
	 * @param hour 时间  24小时制
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public int getCallInCount(String userName, String sdate, String edate, int isConnect, int hour)throws ServiceException;
	/**
	 * 统计时间段内的通话时长
	 * @author zhangyj
	 * @date Aug 9, 2012
	 * @param userName	客服的用户名
	 * @param sDate		开始时间
	 * @param eDate		结束时间
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public Integer getCallInTime(String userName, String sDate, String eDate)throws ServiceException;
	/**
	 * 获取时间内医院的挂号量
	 * @param hospitaName  医院名称
	 * @param date		日期
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public int countHospitalReg(String hospitaName, String date)throws ServiceException;
	/**
	 * 统计呼入数量
	 * @param userName 用户名   null:全部
	 * @param callType 呼入类型：1：挂号 2：咨询 3：投诉 4：人工服务
	 * @param date 时间字符
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public int countCallIn(String userName, int callType, String date)throws ServiceException;
	/**
	 * 分类查询评价数量
	 *@Title: getDegreeNums
	 *@author:gaowm
	 *@date:2012-12-26 下午04:47:36
	 *@param id
	 *@param sDate
	 *@param eDate
	 *@return
	 *@throws com.szwx.yht.exception.ServiceException
	 */
	public Integer[] getDegreeNums(int id, String sDate, String eDate)throws ServiceException;
}
