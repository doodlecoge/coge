package com.szwx.yht.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.szwx.yht.bean.CallIn;
import com.szwx.yht.bean.CallOutLog;
import com.szwx.yht.bean.SoftPhone;
import com.szwx.yht.bean.User;
import com.szwx.yht.common.BaseDataQuery;
import com.szwx.yht.common.CommonAction;
import com.szwx.yht.common.CommonQuery;
import com.szwx.yht.common.DefaultBaseData;
import com.szwx.yht.dto.MethodCallBean;
import com.szwx.yht.exception.ActionException;
import com.szwx.yht.exception.ServiceException;
import com.szwx.yht.service.ICallInService;
import com.szwx.yht.service.ICallOutService;
import com.szwx.yht.service.ISoftPhoneService;
import com.szwx.yht.service.IUserService;
import com.szwx.yht.socket.Refursh;
import com.szwx.yht.util.IDTurn;
import com.szwx.yht.util.StringUtil;

/**
 * 电话处理Action
 * @author zhangyj
 * @date Jul 5, 2012
 */
@SuppressWarnings("serial")
@Controller("callAction")
@Scope("session")
public class CallAction extends CommonAction {
		
	@Autowired
	private ICallInService callInService;
	private String callInPhone;				//呼入号码
	private CallIn callIn;
	private String toPhone;					//呼出号码
	private CallOutLog callOut;				//呼出信息
	@Autowired
	private ICallOutService callOutService;
	@Autowired
	private IUserService userService;
	@Autowired
	private ISoftPhoneService softPhoneService;
	
	
	private List<User> users;
	
	private CommonQuery commonQuery;
	
	private Map<String, String> groupPhone;		//话机分组
	
	private int approve;						//满意度反馈
	
	private String cardId	;					//身份证
	
	private String workId;						//电话流水号
	
	@Autowired
	private Refursh refursh;
	
	
	public String test(){
		refursh.fursh(1);
		
		return null;
	}
	
	/**
	 * 示闲
	 * @author zhangyj
	 * @date Jul 6, 2012
	 * @return
	 */
	public String idle(){
		User user=(User)session.get("admin");
		
		SoftPhone softPhone=user.getSoftPhone();
		softPhone.setIsConnect(0);
		
		try {
			call=softPhoneService.updateSoftPhone(softPhone);
		} catch (ServiceException e) {
			e.printStackTrace();
			call=new MethodCallBean("系统异常");
		}
		
		return CALL_JSON;
	}
	/**
	 * 示忙
	 * @author zhangyj
	 * @date Jul 6, 2012
	 * @return
	 */
	public String busy(){
		User user=(User)session.get("admin");
		
		SoftPhone softPhone=user.getSoftPhone();
		softPhone.setIsConnect(3);
		
		try {
			softPhoneService.updateSoftPhone(softPhone);
		} catch (ServiceException e) {
			e.printStackTrace();
			call=new MethodCallBean("系统异常");
		}
		
		return CALL_JSON;
	}
	
	/**
	 * 跳转到转接页面
	 * @author zhangyj
	 * @date Jul 22, 2012
	 * @return
	 */
	public String toRefer()throws ActionException {
		
		if(null==commonQuery){
			commonQuery=new CommonQuery();
		}
		
		try {
			users=userService.getUsersToRefer(commonQuery);
			int totalCount=0;
			totalCount=userService.getCountToRefer();
			commonQuery.setTotalCount(totalCount);
			int total=0;
			if(0!=totalCount){
				if (totalCount % CommonQuery.pageSize == 0) {
					total = totalCount / CommonQuery.pageSize;
				} else {
					total = totalCount / CommonQuery.pageSize + 1;
				}
			}
			commonQuery.setTotalPage(total);
			
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException("CallAction.toRefer()系统异常1："+e.getMessage(),e);
		}
		
		try {
			groupPhone=new HashMap<String, String>();
			for(String s:BaseDataQuery.newInstance().getBaseData(DefaultBaseData.GROUP_PHONE)){
				
//				groupPhone.put(s, userService.getPhoneByGroup(s));
				groupPhone.put(s, BaseDataQuery.newInstance().getBaseData(s).get(0));//放置组号
				
			};
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException("CallAction.toRefer()系统异常2："+e.getMessage(),e);
		}
		
		
		return "toRefer";
	}
	/**
	 * 呼出挂机操作
	 * @author zhangyj
	 * @date Jul 6, 2012
	 * @return
	 */
	public String outOff(){
		
		callOut.setOffTime(new Date());
		
		try {
			call=callOutService.updateCallOut(callOut);
		} catch (ServiceException e) {
			e.printStackTrace();
			call=new MethodCallBean("系统异常");
		}
		
		//更新软电话状态
		User user=(User)session.get("admin");
		
		SoftPhone softPhone=user.getSoftPhone();
		
		softPhone.setIsConnect(0);
		
		try {
			softPhoneService.updateSoftPhone(softPhone);
		} catch (ServiceException e) {
			e.printStackTrace();
			call=new MethodCallBean("系统异常");
		}
		
		return CALL_JSON;
	}
	/**
	 * 呼出成功操作
	 * @author zhangyj
	 * @date Jul 6, 2012
	 * @return
	 */
	public String outSuc(){
		
		try {
			call=callOutService.CallOutConnect(callOut);
			callOut=(CallOutLog)call.getObject();
		} catch (ServiceException e) {
			e.printStackTrace();
			call=new MethodCallBean("系统异常");
		}
		
		return  CALL_JSON;
	}
	/**
	 * 呼出操作
	 * @author zhangyj
	 * @date Jul 6, 2012
	 * @return
	 */
	public String out(){
		callOut=new CallOutLog();
		callOut.setCallTime(new Date());
		callOut.setIsConnect(1);
		callOut.setOutUser((User)session.get("admin"));
		callOut.setToPhone(toPhone);
		callOut.setOutType(2);
		
		try {
			call=callOutService.saveCallOut(callOut);
			callOut.setId(call.getState());
			callOut=(CallOutLog)call.getObject();
		} catch (ServiceException e) {
			e.printStackTrace();
			call=new MethodCallBean("系统异常");
		}
		
		return CALL_JSON;
	}
	/**
	 * 挂机电话操作
	 * @author zhangyj
	 * @date Jul 6, 2012
	 * @return
	 */
	public String disConnect(){
		
		callIn.setOffTime(new Date());
		
		try {
			call=callInService.updateCallIn(callIn);
		} catch (ServiceException e) {
			e.printStackTrace();
			call=new MethodCallBean("系统异常");
		}
		
		//更新软电话状态
		User user=(User)session.get("admin");
		
		SoftPhone softPhone=user.getSoftPhone();
		
		softPhone.setIsConnect(0);
		
		try {
			softPhoneService.updateSoftPhone(softPhone);
		} catch (ServiceException e) {
			e.printStackTrace();
			call=new MethodCallBean("系统异常");
		}
		
		session.remove(DefaultBaseData.CALL_INFO);	//清呼入信息进session
		
		if(null!=session.get(DefaultBaseData.CARD_ID)){
			session.remove(DefaultBaseData.CARD_ID);	//清入身份证进seeion
		}
		
		callIn=null;
		
		return CALL_JSON;
	}
	/**
	 * 电话接通的操作
	 * @author zhangyj
	 * @date Jul 5, 2012
	 * @return
	 */
	public String connect(){
		refursh.fursh(1);
		try {
			try {
				call=callInService.callInConnect(callIn);
				callIn=(CallIn)call.getObject();
			} catch (ServiceException e) {
				e.printStackTrace();
				call=new MethodCallBean("系统异常");
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		session.put(DefaultBaseData.CALL_INFO, callIn);	//放呼入信息进session
		
		/*---------------为挂号不成功，电话挂断快捷录入身份电话信息-------------------*/
		session.remove(DefaultBaseData.CALL_INFO_for_result);//出去之前的保存值
		session.remove(DefaultBaseData.CARD_ID_for_result);
		session.put(DefaultBaseData.CALL_INFO_for_result, callIn);	//放呼入信息进session
		
		
		
		if(!StringUtil.isNull(cardId)){
			System.out.println(callIn.getCallInPhone()+"身份证："+cardId);
			cardId=cardId.replace("*", "X");
			cardId= IDTurn.fixPersonIDCode(cardId);			//15位的身份证转换成18位  add  by zhangyj  at 2012-12-22
			session.put(DefaultBaseData.CARD_ID, cardId);	//放入身份证进seeion
			/*---------------为挂号不成功，电话挂断快捷录入身份电话信息-------------------*/
			session.put(DefaultBaseData.CARD_ID_for_result, cardId);
		}
		
		
		return CALL_JSON;
	}
	/**
	 * 电话接入
	 * @author zhangyj
	 * @date Jul 6, 2012
	 * @return
	 */
	public String in(){
		//callIn.setCallInPhone(callInPhone);
		callIn.setCallInTime(new Date());
		callIn.setIsConnect(1);
		callIn.setOpeateESQ((User)session.get("admin"));
		callIn.setApproving(0);
		try {
			call=callInService.saveCallIn(callIn);
			callIn=(CallIn)call.getObject();
			//callIn.setId(call.getState());//session内 设置callIn的id标示，方便更新
		} catch (Exception e) {
			e.printStackTrace();
			call=new MethodCallBean("系统异常");
		}

		return CALL_JSON;
	}
	/**
	 * 满意度反馈
	 * @author zhangyj
	 * @date Jul 24, 2012
	 * @return
	 */
	public String approve(){
		 CallIn callApp=null;
		try {
		 callApp=callInService.getCallInByWrok(workId);
		} catch (ServiceException e1) {
			e1.printStackTrace();
			call=new MethodCallBean("系统异常");
		}
		
		callApp.setApproving(approve);

		try {
			call=callInService.updateCallIn(callApp);
			//callIn.setId(call.getState());//session内 设置callIn的id标示，方便更新
		} catch (ServiceException e) {
			e.printStackTrace();
			call=new MethodCallBean("系统异常");
		}
		
		return CALL_JSON;
	}
	/**
	 * 软电话登陆事件
	 * @author zhangyj
	 * @date Jul 22, 2012
	 * @return
	 */
	public String login(){
		
		User user=(User)session.get("admin");
		
		SoftPhone softPhone=user.getSoftPhone();
		
		softPhone.setIsLogin(1);
		
		try {
			call=softPhoneService.updateSoftPhone(softPhone);
		} catch (ServiceException e) {
			call=new MethodCallBean(false,"系统异常");
			log.error("CallAction.login()系统异常1"+e.getMessage(),e);
			e.printStackTrace();
		}
		
		return CALL_JSON;
	}
	

	public CallIn getCallIn() {
		return callIn;
	}
	public void setCallIn(CallIn callIn) {
		this.callIn = callIn;
	}
	public String getCallInPhone() {
		return callInPhone;
	}
	public void setCallInPhone(String callInPhone) {
		this.callInPhone = callInPhone;
	}
	public String getToPhone() {
		return toPhone;
	}
	public void setToPhone(String toPhone) {
		this.toPhone = toPhone;
	}
	public CallOutLog getCallOut() {
		return callOut;
	}
	public void setCallOut(CallOutLog callOut) {
		this.callOut = callOut;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public CommonQuery getCommonQuery() {
		return commonQuery;
	}
	public void setCommonQuery(CommonQuery commonQuery) {
		this.commonQuery = commonQuery;
	}
	public Map<String, String> getGroupPhone() {
		return groupPhone;
	}
	public void setGroupPhone(Map<String, String> groupPhone) {
		this.groupPhone = groupPhone;
	}
	public int getApprove() {
		return approve;
	}
	public void setApprove(int approve) {
		this.approve = approve;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
}
