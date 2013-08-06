package com.szwx.yht.bean;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体BEAN(主要是客服)
 * @author zhangyj
 * @date May 2, 2012
 */
@Entity
@Table(name="YHT_USER")
@Proxy(lazy=true)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1365228824409153885L;
	@Id
	@SequenceGenerator(name = "SEQ_ID_GEN", sequenceName = "SEQ_USER_YHT", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_GEN")
    @Column(name="ID")
	private int id;				//ID，唯一标示
	@Column(name="USER_NAME",unique=true,nullable=false,length=18)
	private String userName;	//用户名，帐号
	@Column(name="PASSWORD",nullable=false,length=35)
	private String password;	//密码
//	@Column(name="SOFT_PHONE",unique=true,length=10)
//	private String softPhone;	//客服电话机号码  ！！！！！！！！！！！！！！！！！！！！！
//	@Column(name="PHONE_PWD",length=35)
//	private String phonePwd;	//电话机密码	
	@ManyToOne(cascade= CascadeType.ALL)			//多个人对应一个话机
	@JoinColumn(name="SOFT_PHONE")
	private SoftPhone  softPhone;//软电话信息
	@Column(name="TRUE_NAME",nullable=false,length=10)
	private String trueName;	//真实姓名
	@Column(name="SEX")
	private int sex;			//性别		1：男 0：女
	@Column(name="TEL",length=15)
	private String tel;			//手机号码
	@Column(name="E_MAIL",length=30)
	private String e_Mail;		//邮箱
	@Column(name="DEPARTMENT")
	private int department;		//部门  	1:客服部 2：技术部
	@ManyToOne
	@JoinColumn(name="ROLE")
	private Role role;			//角色
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="REG_DATE")
	private Date regDate;		//注册时间
	@Column(name="LAST_UPDATE_TIME")
	private Date lastUpdateTime;//最后修改时间
	@Column(name="LOG_ERR_COUNT")
	private int logErrCount;	//登陆错误次数
	@Column(name="IS_USERD")
	private int isUserd; 		//是否有效	0：有效 1：无效
	//////////////////GET SET//////////////////////
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getE_Mail() {
		return e_Mail;
	}
	public void setE_Mail(String mail) {
		e_Mail = mail;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public int getLogErrCount() {
		return logErrCount;
	}
	public void setLogErrCount(int logErrCount) {
		this.logErrCount = logErrCount;
	}
/*	public String getSoftPhone() {
		return softPhone;
	}
	public void setSoftPhone(String softPhone) {
		this.softPhone = softPhone;
	}
	public String getPhonePwd() {
		return phonePwd;
	}
	public void setPhonePwd(String phonePwd) {
		this.phonePwd = phonePwd;
	}*/
	public int getDepartment() {
		return department;
	}
	public void setDepartment(int department) {
		this.department = department;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getIsUserd() {
		return isUserd;
	}
	public void setIsUserd(int isUserd) {
		this.isUserd = isUserd;
	}
	public SoftPhone getSoftPhone() {
		return softPhone;
	}
	public void setSoftPhone(SoftPhone softPhone) {
		this.softPhone = softPhone;
	}
}
