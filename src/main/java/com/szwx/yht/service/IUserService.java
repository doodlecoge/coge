package com.szwx.yht.service;

import com.szwx.yht.bean.Role;
import com.szwx.yht.bean.User;
import com.szwx.yht.common.CommonQuery;
import com.szwx.yht.dto.MethodCallBean;
import com.szwx.yht.exception.ServiceException;

import java.util.List;


/**
 * 用户维护接口
 * @author zhangyj
 * @date Mar 19, 2012
 */
public interface IUserService {
	/**
	 * 按组获取可转接号码以，号隔开
	 * @author zhangyj
	 * @date Jul 22, 2012
	 * @param groupName
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public String getPhoneByGroup(String groupName)throws ServiceException;
	/**
	 * 获取登陆的客户总数
	 * @author zhangyj
	 * @date Jul 22, 2012
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public int getCountToRefer()throws ServiceException;
	/**
	 * 查询转接用户
	 * @author zhangyj
	 * @date Jul 22, 2012
	 * @param commonQuery
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public List<User> getUsersToRefer(CommonQuery commonQuery)throws ServiceException;
	/**
	 * confirmation user to login system
	 * @author zhangyj
	 * @date Mar 20, 2012
	 */
	public MethodCallBean loginSys(String userName, String password)throws ServiceException;
	/**
	 * 获取角色菜单
	 * @author zhangyj
	 * @date Mar 27, 2012
	 */
	public String getMenuHTML(Role role)throws ServiceException;
	/**
	 * 更新用户帐号信息
	 * @author zhangyj
	 * @date Mar 27, 2012
	 */
	public MethodCallBean updateUserInfo(User user)throws ServiceException;
	/**
	 * 修改密码
	 * @author zhangyj
	 * @date Mar 27, 2012
	 */
	public MethodCallBean updatePwd(String oldPwd, String pwd1, String pwd2)throws ServiceException;
	/**
	 * 给用户分配角色
	 * @author zhangyj
	 * @date Mar 27, 2012
	 */
	public MethodCallBean updateUserRole(User user, Role role)throws ServiceException;
	/**
	 * 添加一个用户帐号
	 * @author zhangyj
	 * @date Mar 27, 2012
	 */
	public MethodCallBean addUser(User user)throws ServiceException;
	/**
	 * 根据用户ID获取用户帐号信息
	 * @author zhangyj
	 * @date Mar 28, 2012
	 * @param id
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public User getUserById(int id)throws ServiceException;
	/**
	 * 按用户名获取用户对象
	 * @author zhangyj
	 * @date Jun 25, 2012
	 * @param userName
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public User getUserByName(String userName)throws ServiceException;
	public User getUserByName(String userName, int reg) throws ServiceException;
	/**
	 * 按部门和角色查询用户
	 * @author zhangyj
	 * @date Jun 25, 2012
	 * @param department 部门代号
	 * @param roleId	角色ID
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public List<User> searchUser(int department, int roleId)throws ServiceException;
	public List<User> searchUser(int department, int roleId, CommonQuery commonQuery)throws ServiceException;
	/**
	 * 删除一个用户
	 * @author zhangyj
	 * @date Jun 25, 2012
	 * @param userId	用户ID
	 * @param realy		真假删除
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public MethodCallBean deleteUser(int userId, boolean realy)throws ServiceException;
	/**
	 * 按绑定的软电话号码查找用户
	 * @author zhangyj
	 * @date Jul 6, 2012
	 * @param toPhone
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public User getUserByPhone(String toPhone)throws ServiceException;
	/**
	 * 获取有效用户总数
	 * @author zhangyj
	 * @date Jul 23, 2012
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public int getCountUser(int department, int roleId, CommonQuery commonQuery)throws ServiceException;
	/**
	 * 按有无软电话坐席获取用户集
	 * @author zhangyj
	 * @date Aug 9, 2012
	 * @param hasPhone 0：全部 1：有电话 2：无电话
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public List<User>  getUser(int hasPhone)throws ServiceException;
	/**
	 * 今天登陆过的客服
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public List<User>  getTodayUser()throws ServiceException;
	/**
	 * 姓名模糊查询
	 *@Title: getUserLikeUserName
	 *@author:gaowm
	 *@date:2012-12-26 下午03:38:37
	 *@param userName
	 *@return
	 *@throws com.szwx.yht.exception.ServiceException
	 */
	public List<User> getUserLikeUserName(String userName)throws ServiceException;
}
