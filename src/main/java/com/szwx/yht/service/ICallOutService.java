package com.szwx.yht.service;

import com.szwx.yht.bean.CallOutLog;
import com.szwx.yht.bean.User;
import com.szwx.yht.common.CommonQuery;
import com.szwx.yht.dto.MethodCallBean;
import com.szwx.yht.exception.ServiceException;

import java.util.List;

/**
 * 电话呼出处理接口
 * @author zhangyj
 * @date Jun 28, 2012
 */
public interface ICallOutService {
	
	/**
	 * 更新呼出信息
	 * @author zhangyj
	 * @date Jul 6, 2012
	 * @param callOut
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public MethodCallBean updateCallOut(CallOutLog callOut)throws ServiceException;
	/**
	 * 呼出电话接通
	 * @author zhangyj
	 * @date Jul 6, 2012
	 * @param callOut
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public MethodCallBean CallOutConnect(CallOutLog callOut)throws ServiceException;
	/**
	 * 记录电话呼出的日志
	 * @author zhangyj
	 * @date Jun 28, 2012
	 * @param callOut
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public MethodCallBean saveCallOut(CallOutLog callOut)throws ServiceException;
	/**
	 * 呼出电话接通成功
	 * @author zhangyj
	 * @date Jun 28, 2012
	 * @param id 呼出流水ID
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public MethodCallBean<CallOutLog> CallOutconnect(int id)throws ServiceException;
	/**
	 * 按ID获取呼出的日志记录
	 * @author zhangyj
	 * @date Jun 28, 2012
	 * @param id
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public CallOutLog getCallOutById(int id)throws ServiceException;
	/**
	 *
	 * @author zhangyj
	 * @date Jun 28, 2012
	 * @param isConnect 0:全部 1：呼出未接通 2：接通
	 * @param outType呼出类型	0：全部
	 * @param user 呼出操作客服 null:全部
	 * @param commonQuery 其它查询条件
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public List<CallOutLog> getCallOuts(int isConnect, int outType, User user, CommonQuery commonQuery)throws ServiceException;

	/**
	 * 按用户名和通话日期查询呼出信息
	 * @author zhangyj
	 * @date Aug 9, 2012
	 * @param userName	用户名
	 * @param sDate	 开始日期 字符格式
	 * @param eDate	 结束日期 字符格式
	 * @param isConnect 呼入是否接通	1：未接 2：接通
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public List<CallOutLog> getCallOuts(String userName, String sDate, String eDate, int isConnect)throws ServiceException;

	/**
	 * 按用户名和通话日期查询呼出信息
	 * @author zhangyj
	 * @date Aug 9, 2012
	 * @param userName	用户名
	 * @param sDate	 开始日期 字符格式
	 * @param eDate	 结束日期 字符格式
	 * @param isConnect 呼入是否接通	1：未接 2：接通
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public int getCallOutCount(String userName, String sDate, String eDate, int isConnect)throws ServiceException;
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
	public Integer getCallOutTime(String userName, String sDate, String eDate)throws ServiceException;
	
}	
