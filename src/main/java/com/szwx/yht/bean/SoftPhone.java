package com.szwx.yht.bean;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 软电话信息实体BEAN
 * @author zhangyj
 * @date Jul 5, 2012
 */
@Entity
@Table(name="YHT_SOFT_PHONE")
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true,selectBeforeUpdate=true)
public class SoftPhone implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 4759370378081739960L;
	@Id
	@Column(name="PHONE_NO",length=4)
	private String phoneNo;				//软电话号码
	@Column(name="PASSWORD",length=15)
	private String password;			//软电话密码
	@Column(name="IS_CONNECT")
	private int isConnect;				//是否接通 0:无通话 1：来电状态（未接通） 2：接通 3：示忙 4:呼出中
	@Column(name="IS_LOGIN")
	private int isLogin;				//是否登陆 0：未登陆  1：登陆
/*	@Column(name="GROUP_NAME",length=15)
	private String group;					//组别		1
*/	@OneToMany(mappedBy="softPhone")
	private List<User> users=new ArrayList<User>();
	////////////////////GET SET//////////////////////////////////
	
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getIsConnect() {
		return isConnect;
	}
	public void setIsConnect(int isConnect) {
		this.isConnect = isConnect;
	}
	public int getIsLogin() {
		return isLogin;
	}
	public void setIsLogin(int isLogin) {
		this.isLogin = isLogin;
	}
	
	@JSON(serialize=false)
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
/*	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}*/
}
