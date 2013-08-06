package com.szwx.yht.bean;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 挂人信息实体BEAN
 * @author zhangyj
 * @date Jun 14, 2012
 */
@Entity
@Table(name="YHT_REG_PEOPLE")
@Proxy(lazy=true)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
public class RegPeople implements Serializable{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 2265037246337896655L;
	@Id
	@SequenceGenerator(name = "SEQ_ID_GEN", sequenceName = "SEQ_REGPEOPLE_YHT", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_GEN")
	private long id;				//ID
	@Column(name="ID_CARD_CODE",length=20)
	private String  identityCard;	//身份证编号	(根据生日判定是否有身份证件)
	@Column(name="MEDICAL_NO",length=30)
	private String 	medicalNo;		//医保编号
	@Column(name="MEDICAL_TYPE")
	private Integer medicalType;	//医保类型    	eg:  0:自费
	@Column(name="TRUE_NAME",nullable=false,length=15)
	private String trueName;		//真实姓名
/*	@Temporal(TemporalType.DATE)
	@Column(name="BRITH_DATE")
	private Date brithDate;			//生日
*/
	@Column(name="AGE")
	private int age;				//年龄
	@Column(name="SEX")
	private int sex;				//性别
	@Column(name="MOBILE",length=15)
	private String mobile;			//手机号
	@Column(name="BREAK_PROMISE_COUNT")
	private int breakPromiseCount;	//爽约次数 （超过三次拉入黑名单）
	@Column(name="cancel_DATE")
	private Date cancelDate;		//解约时间
/*	@ManyToMany
	@JoinTable(
			name="YHT_HELP_TO_PEOPLE",
			joinColumns=@JoinColumn(name="REG_PEOPLE"),
			inverseJoinColumns=@JoinColumn(name="HELP_PEOPLE")
	)
	private List<RegPeople>  helpPeople;//曾经帮别人挂号的人
*/	
	@ManyToOne
	@JoinColumn(name="LAST_USER",nullable=true)
	private User lastUser;			//最后操作客服(接触黑名单的工作人员)
	
	@Column(name="REMOVE_REASON")
	private String removeReason;
	//////////////////////////GET SET//////////////////////////
	
	public long getId() {
		return id;
	}
	public String getRemoveReason() {
		return removeReason;
	}
	public void setRemoveReason(String removeReason) {
		this.removeReason = removeReason;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public Integer getMedicalType() {
		return medicalType;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
/*	public Date getBrithDate() {
		return brithDate;
	}
	public void setBrithDate(Date brithDate) {
		this.brithDate = brithDate;
	}*/
	public int getBreakPromiseCount() {
		return breakPromiseCount;
	}
	public void setBreakPromiseCount(int breakPromiseCount) {
		this.breakPromiseCount = breakPromiseCount;
	}
	/*public List<RegPeople> getHelpPeople() {
		return helpPeople;
	}
	public void setHelpPeople(List<RegPeople> helpPeople) {
		this.helpPeople = helpPeople;
	}*/
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMedicalNo() {
		return medicalNo;
	}
	public void setMedicalNo(String medicalNo) {
		this.medicalNo = medicalNo;
	}
	public User getLastUser() {
		return lastUser;
	}
	public void setLastUser(User lastUser) {
		this.lastUser = lastUser;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public void setMedicalType(Integer medicalType) {
		this.medicalType = medicalType;
	}
	public Date getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}
}
