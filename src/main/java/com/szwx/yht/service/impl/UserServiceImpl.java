package com.szwx.yht.service.impl;

import com.szwx.yht.bean.Role;
import com.szwx.yht.bean.SYSLog;
import com.szwx.yht.bean.SoftPhone;
import com.szwx.yht.bean.User;
import com.szwx.yht.common.CommonQuery;
import com.szwx.yht.common.CommonService;
import com.szwx.yht.dto.MethodCallBean;
import com.szwx.yht.exception.ServiceException;
import com.szwx.yht.service.ISysLogService;
import com.szwx.yht.service.IUserService;
import com.szwx.yht.util.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("userService")
public class UserServiceImpl extends CommonService implements IUserService {
	@Autowired
	private ISysLogService logService;
	@Autowired
	private DateUtil dateUtil;
	
	public MethodCallBean addUser(User user) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMenuHTML(Role role) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public User getUserById(int id) throws ServiceException {
		User user=null;
		try {
			user=(User)commonDao.get(User.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("UserServiceImpl.getUserById("+id+")系统异常1："+e.getMessage(),e);
		}
		return user;
	}
	
	@Transactional
	public MethodCallBean loginSys(String userName, String password)
			throws ServiceException {

		//去除userName,password空格  可防sql注入等
		userName= BlankTool.stripBlank(userName);
		password= BlankTool.stripBlank(password);
		
		MethodCallBean call=null;
		if(StringUtil.isNull(userName)){
			call=new MethodCallBean(false,"用户名不能为空");
			call.setState(1);
			return call;
		}
		if(StringUtil.isNull(password)){
			call=new MethodCallBean(false,"密码不能为空");
			call.setState(2);
			return call;
		}

		//检验用户名 取值范围为6~10位a-z,A-Z,0-9,汉字，不能以"_"结尾
		if(!RegexCheck.newInstance().checkUsername(userName, 4, 15)){
			call=new MethodCallBean(false,"用户名不合法");
			call.setState(1);
			return call;
		}
		//检验密码 取值范围为6~15位a-z,A-Z,0-9,不能以"_"结尾
		if(!RegexCheck.newInstance().checkPassword(password,6,15)){
			call=new MethodCallBean(false,"密码不合法");
			call.setState(2);
			return call;
		}
		call=new MethodCallBean();
		User user=null;
		String hql="from User where userName ='"+userName+"' and isUserd=0";
		try {
			user=(User)commonDao.unique(hql);
		} catch (Exception e) {
			e.printStackTrace();
			call.setMsg("系统异常");
			call.setState(4);
			throw new ServiceException("UserServiceImpl.loginSys系统异常1："+e.getMessage(),e);
		}
		if(!StringUtil.isNull(call.getMsg())){
			return call;
		}
		if(null==user){
			call.setMsg("用户不存在");
			call.setState(1);
			return call;
		}
		if(user.getLogErrCount()>=5){
			call.setMsg("帐号验证错误6次，请半小时后再试");
			call.setState(4);
			return call;
		}
		if(!Md5.getMD5Str(password).equals(user.getPassword())){
			call.setMsg("密码错误");
			call.setState(2);
			// update user 密码错误次数+1
			user.setLogErrCount(user.getLogErrCount()+1);
			try {
				commonDao.update(user);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException("UserServiceImpl.loginSys系统异常2："+e.getMessage(),e);
			}
			if(user.getLogErrCount()>=5){
				//半小时后错误清零
				/*Timer timer=new Timer();
				TimerTask task=new ResetTask(user);
				ThreadPool.execute(task);
				timer.schedule(task, 30*60*1000);*/
				RestUtil.newInstance().clearZero(user,30);
				
				//TODO 记录锁定日志
				SYSLog log=new SYSLog();
				log.setOpeaterDesc(user.getUserName()+"登陆失败6次，帐户将在半小时后解除锁定");
				log.setModuleType(1);
				log.setCreateTimee(new Date());
				log.setOpeateClass(4);
				log.setOpeaterUser(user);
				
				logService.addLog(log);
				
			}
			return call;
		}
		call.setSuccessInfo("登陆成功", user);
		if(!(user.getLogErrCount()==0)){
			// update user 登陆错误次数清零
			user.setLogErrCount(0);
			try {
				commonDao.update(user);
			} catch (Exception e) {
				throw new ServiceException("UserServiceImpl.loginSys系统异常3："+e.getMessage(),e);
			}
		}
		return call;
		
	}

	public MethodCallBean updatePwd(String oldPwd, String pwd1, String pwd2)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public MethodCallBean updateUserInfo(User user) throws ServiceException {

		//去除userName,password空格  可防sql注入等
		user.setPassword(BlankTool.stripBlank(user.getPassword()));
		
		MethodCallBean<User> call=null;
		if(StringUtil.isNull(user.getPassword())){
			call=new MethodCallBean(false,"密码不能为空");
			call.setState(2);
			return call;
		}

		//检验密码 取值范围为6~15位a-z,A-Z,0-9,不能以"_"结尾
		if(!RegexCheck.newInstance().checkPassword(user.getPassword(),6,15)){
			call=new MethodCallBean(false,"密码不合法");
			call.setState(2);
			return call;
		}
		if(!StringUtil.isNull(user.getTrueName())){
			Pattern pattern=Pattern.compile("[\u4e00-\u9fa5]{2,8}");	
			Matcher matcher=pattern.matcher(user.getTrueName());
			if(!matcher.matches()){
				call=new MethodCallBean(false,"真实姓名为2~8位汉字");
				call.setState(3);
				return call;
			}
		}
		try {
			user.setPassword(Md5.getMD5Info(user.getPassword()));
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new ServiceException("SysServiceImpl.addUser()系统异常2："+e1.getMessage(),e1);
		}
		
		try {
			commonDao.update(user);
			call=new MethodCallBean(true,"更新成功");
		} catch (Exception e) {
			e.printStackTrace();
			call=new MethodCallBean(false,"系统异常");
			call.setState(4);
			throw new ServiceException("UserServiceImpl.updateUserInfo("+user.getUserName()+")系统异常3："+e.getMessage(),e);
		}
		
		return call;
	}

	public MethodCallBean updateUserRole(User user, Role role)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public User getUserByName(String userName,int reg) throws ServiceException {
		User user=null;
		String hql="from User where userName ='"+userName+"'";
		try {
			user=(User)commonDao.unique(hql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("UserServiceImpl.getUserByName("+userName+")系统异常1："+e.getMessage(),e);
		}
		return user;
	}
	
	public User getUserByName(String userName) throws ServiceException {
		User user=null;
		String hql="from User where userName ='"+userName+"' and isUserd=0";
		try {
			user=(User)commonDao.unique(hql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("UserServiceImpl.getUserByName("+userName+")系统异常1："+e.getMessage(),e);
		}
		return user;
	}

	public List<User> searchUser(int department, int roleId)
			throws ServiceException {
		List<User> list=null;

		DetachedCriteria criteria= DetachedCriteria.forClass(User.class).add(Restrictions.eq("isUserd", 0));
		if(department!=0){
			criteria.add(Restrictions.eq("department", department));
		}
		if(roleId!=0){
			criteria.createCriteria("role").add(Restrictions.eq("id", roleId));
		}
		
		try {
			list=commonDao.queryList(criteria, true);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("UserServiceImpl.searchUser("+department+","+roleId+")系统异常1："+e.getMessage(),e);
		}
		
		return list;
	}

	public MethodCallBean deleteUser(int userId, boolean realy)
			throws ServiceException {
		MethodCallBean call=new MethodCallBean();
		User user=this.getUserById(userId);
		if(realy&&null!=user){
			try {
				commonDao.delete(user);
			} catch (Exception e) {
				e.printStackTrace();
				call.setMsg(false, "系统异常");
				throw new ServiceException("UserServiceImpl.deleteUser("+userId+","+realy+")系统异常1："+e.getMessage(),e);
			}
		}
		if(!StringUtil.isNull(call.getMsg())){
			return call;
		}
		if((!realy)&&null!=user){
			user.setIsUserd(1);
			try {
				commonDao.update(user);
				call.setMsg(true, "删除成功");
			} catch (Exception e) {
				e.printStackTrace();
				call.setMsg(false, "系统异常");
				throw new ServiceException("UserServiceImpl.deleteUser("+userId+","+realy+")系统异常2："+e.getMessage(),e);
			}
		}
		
		return call;
	}

	public User getUserByPhone(String toPhone) throws ServiceException {

		User user=null;
		
		DetachedCriteria criteria= DetachedCriteria.forClass(User.class).createCriteria("softPhone")
					.add(Restrictions.eq("phoneNo", toPhone));
		
		criteria.add(Restrictions.eq("isLogin", 1));
		
		try {
			user=(User)commonDao.unique(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("UserServiceImpl.getUserByPhone("+toPhone+")系统异常2："+e.getMessage(),e);
		}
		
		return user;
	}

	public List<User> getUsersToRefer(CommonQuery commonQuery)
			throws ServiceException {
		
		List<User> list=null;
		
		DetachedCriteria criteria= DetachedCriteria.forClass(User.class).add(Restrictions.eq("isUserd", 0));
		
		criteria.add(Restrictions.isNotNull("softPhone"));//.createCriteria("softPhone").add(Restrictions.eq("isLogin", 1));
		
		criteria.createAlias("softPhone", "sp").add(Restrictions.eq("sp.isLogin", 1));
		criteria.createAlias("role", "re").add(Restrictions.not(Restrictions.eq("re.id", 1))).add(Restrictions.not(Restrictions.eq("re.id", 2)));
		//role.id<>1,2
		
		if(null==commonQuery){
			try {
				list=commonDao.queryList(criteria, 1, CommonQuery.pageSize);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException("UserServiceImpl.getUsersToRefer()系统异常2："+e.getMessage(),e);
			}
			return list;
		}
		
		try {
			list=commonDao.queryList(criteria, commonQuery.getPageIndex(), commonQuery.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("UserServiceImpl.getUsersToRefer()系统异常1："+e.getMessage(),e);
		}
		
		return list;

	}

	public int getCountToRefer() throws ServiceException {

		int total=0;
		
		DetachedCriteria criteria= DetachedCriteria.forClass(User.class).add(Restrictions.eq("isUserd", 0));
		
		criteria.add(Restrictions.isNotNull("softPhone"));//.createCriteria("softPhone").add(Restrictions.eq("isLogin", 1));
		
		criteria.createAlias("softPhone", "sp").add(Restrictions.eq("sp.isLogin", 1));
		criteria.createAlias("role", "re").add(Restrictions.not(Restrictions.eq("re.id", 1))).add(Restrictions.not(Restrictions.eq("re.id", 2)));
		
		try {
			total=commonDao.getCount(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("UserServiceImpl.getCountToRefer()系统异常1："+e.getMessage(),e);
		}
		
		return total;
	}

	public String getPhoneByGroup(String groupName) throws ServiceException {
		StringBuffer buf=new StringBuffer();
		
		DetachedCriteria criteria= DetachedCriteria.forClass(SoftPhone.class).add(Restrictions.eq("isLogin", 1));
		criteria.add(Restrictions.eq("group", groupName));
		
		try {
			for(SoftPhone s:(List<SoftPhone>)commonDao.queryList(criteria,false)){
				buf.append(s.getPhoneNo()+",");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("UserServiceImpl.getPhoneByGroup("+groupName+")系统异常1："+e.getMessage(),e);
		}
		
		if(StringUtil.isNull(buf.toString())){
			return null;
		}else {
			return buf.substring(0, buf.toString().length()-1);
		}
	}
/*	public static void main(String[] args) {
		StringBuffer buf=new StringBuffer();
		buf.append("1232"+",");
		buf.append("1232"+",");
		buf.append("1232"+",");
		buf.append("1232"+",");
		
		System.out.println(buf.substring(0, buf.toString().length()-1));
	}*/

	public List<User> searchUser(int department, int roleId,
			CommonQuery commonQuery) throws ServiceException {
		List<User> list=null;
		
		DetachedCriteria criteria= DetachedCriteria.forClass(User.class).add(Restrictions.eq("isUserd", 0));
		criteria.addOrder(Order.asc("userName"));
		if(department!=0){
			criteria.add(Restrictions.eq("department", department));
		}
		if(roleId!=0){
			criteria.createCriteria("role").add(Restrictions.eq("id", roleId));
		}
		
		try {
			list=commonDao.queryList(criteria, commonQuery.getPageIndex(),commonQuery.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("UserServiceImpl.searchUser("+department+","+roleId+")系统异常1："+e.getMessage(),e);
		}
		
		return list;
	}

	public int getCountUser(int department,int roleId ,CommonQuery commonQuery) throws ServiceException {
		int count=0;
		DetachedCriteria criteria= DetachedCriteria.forClass(User.class).add(Restrictions.eq("isUserd", 0));
		if(department!=0){
			criteria.add(Restrictions.eq("department", department));
		}
		if(roleId!=0){
			criteria.createCriteria("role").add(Restrictions.eq("id", roleId));
		}
		try {
			count=commonDao.getCount(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("UserServiceImpl.getCountUser()系统异常1："+e.getMessage(),e);

		}
		
		return count;
	}

	public List<User> getUser(int hasPhone) throws ServiceException {

		List<User> list=null;
		
		DetachedCriteria criteria= DetachedCriteria.forClass(User.class).add(Restrictions.eq("isUserd", 0));
		if(hasPhone==1){
			criteria.add(Restrictions.isNotNull("softPhone"));
			criteria.createCriteria("role").add(Restrictions.gt("id", 2));
		}
		if(hasPhone==2){
			criteria.add(Restrictions.isNull("softPhone"));
		}
		try {
			list=commonDao.queryList(criteria, false);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("UserServiceImpl.getUser("+hasPhone+")系统异常1："+e.getMessage(),e);
		}
		return list;
	}
//role id==1    2 为管理员
	public List<User> getTodayUser() throws ServiceException {

		String date=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		StringBuffer hql=new StringBuffer();
		hql.append(" select distinct  s.opeaterUser from SYSLog s where to_char(s.createTimee,'"+dateUtil.sdf(date)+"')='"+date+"'");
		hql.append(" and s.opeaterUser.role.id<>1 and s.opeaterUser.role.id<>2 order by s.opeaterUser.softPhone.phoneNo");
		List<User> list=null;
		
		try {
			list=commonDao.queryList(hql.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("UserServiceImpl.getTodayUser()系统异常1："+e.getMessage(),e);
		}
		
		return list;
	}

	public List<User> getUserLikeUserName(String userName)
			throws ServiceException {
		List<User> list=new ArrayList<User>();
		String hql="from User u where ( u.userName like '%"+userName+"%' or  u.trueName like '%"+userName+"%' or u.softPhone.phoneNo like '%"+userName+"%' ) and u.isUserd=0 and u.role.id not in(1,2)";
		try {
			list=(List<User>)commonDao.queryList(hql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("UserServiceImpl.getUserLikeUserName("+userName+")系统异常1："+e.getMessage(),e);
		}
		return list;
	}
}
