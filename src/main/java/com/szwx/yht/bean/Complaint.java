package com.szwx.yht.bean;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 投诉实体BEAN
 * @author zhangyj
 * @date Jun 15, 2012
 */
@Entity
@Table(name="YHT_COMPLAINT")
@Proxy(lazy=true)
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true,selectBeforeUpdate=true)
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class Complaint implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 2877057925393863463L;
	@Id
	@SequenceGenerator(name="SEQ_ID_GEN",sequenceName="SEQ_COMPLAINT_YHT",allocationSize=1)
	@GeneratedValue(strategy= GenerationType.SEQUENCE,generator="SEQ_ID_GEN")
	private long id;
	@ManyToOne
	@JoinColumn(name="CALL_IN")
	private CallIn callIn;			//电话呼入流水
	@Column(name="ID_CARD_CODE",length=20)
	private String  identityCard;	//身份证编号	(根据生日判定是否有身份证件)
	@Column(name="TRUE_NAME",length=15)
	private String trueName;		//真实姓号
	@Column(name="SEX")
	private int sex;				//性别
	@Column(name="MOBILE",length=15)
	private String mobile;			//联系手机号
	@Column(name="E_MAIL",length=50)
	private String eMail;			//联系邮箱
	@Column(name="ADDRESS",length=100)
	private String address;			//联系地址
	@ManyToOne
	@JoinColumn(name="FOR_HOSPITAL",nullable=true)
	private Hospital forHospital;		//被投诉医院
	@Column(name="FOR_USER")
	private String forUser;		//被投诉人姓名
	@Column(name="FOR_WORK_NO",length=20)
	private String forWorkNo;		//被投诉人工号
	@Column(name="COMPLAINT_DESC",nullable=false,length=4000)
	private String complaintDesc;	//投诉内容
	@Column(name="REMARK")
	private String remark;			//备注
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TIMEEE")
	private Date createTimeee;		//创建时间
	@ManyToOne
	@JoinColumn(name="LAST_USER",nullable=false)
	private User lastUser;			//创建操作者
	@Column(name="STATE")
	private int state;				//投诉状态  1:待处理 2:处理中3:处理完成 4:作废
	@Column(name="RETURN_STATE")
	private int returnState;		//回访状态  1：未回访 2：已回访
	@Column(name="FOR_DEPARTMENT",nullable=false,length=50)
	private String forDepartment;	//投诉的部门					人工输入   add by zhangyj 2012-12-22
	@Column(name="COMPLAINT_TYPE",columnDefinition="int default 1")
	private int type;				//投诉类型  1：咨询 2：表扬3：建议 4 ：投诉 5举报 add by zhangyj 2012-12-22
	@Column(name="COMPLAINT_CODE")
	private String code;			//投诉编号  YHTYYMMDD0001
									//（YHT+年（两位）+月（两位）+日（两位）+0001（顺序号，每天从0001开始）
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATE_TIMEEE")
	private Date updateTime;	
	
	@ManyToOne
	@JoinColumn(name="UPDATE_USER")
	private User updateUser;			//最后操作者
	
	
	@OrderBy("rid ASC")
	@OneToMany(cascade= CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "complaint")
	private List<ComplaintReply> repList=new ArrayList<ComplaintReply>();
	////////////////////GET  SET////////////////////
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTrueName() {
		return trueName;
	}
	public User getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getMobile() {
		return mobile;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getForWorkNo() {
		return forWorkNo;
	}
	public void setForWorkNo(String forWorkNo) {
		this.forWorkNo = forWorkNo;
	}
	public String getComplaintDesc() {
		return complaintDesc;
	}
	public void setComplaintDesc(String complaintDesc) {
		this.complaintDesc = complaintDesc;
	}

	public CallIn getCallIn() {
		return callIn;
	}
	public void setCallIn(CallIn callIn) {
		this.callIn = callIn;
	}
	public Date getCreateTimeee() {
		return createTimeee;
	}
	public void setCreateTimeee(Date createTimeee) {
		this.createTimeee = createTimeee;
	}
	public User getLastUser() {
		return lastUser;
	}
	public void setLastUser(User lastUser) {
		this.lastUser = lastUser;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
//	public Depart getForDepart() {
//		return forDepart;
//	}
//	public void setForDepart(Depart forDepart) {
//		this.forDepart = forDepart;
//	}
	
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	
	public String getEMail() {
		return eMail;
	}
	public void setEMail(String mail) {
		eMail = mail;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getForUser() {
		return forUser;
	}
	public void setForUser(String forUser) {
		this.forUser = forUser;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getReturnState() {
		return returnState;
	}
	public void setReturnState(int returnState) {
		this.returnState = returnState;
	}
	public Hospital getForHospital() {
		return forHospital;
	}
	public void setForHospital(Hospital forHospital) {
		this.forHospital = forHospital;
	}
	public String getForDepartment() {
		return forDepartment;
	}
	public void setForDepartment(String forDepartment) {
		this.forDepartment = forDepartment;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<ComplaintReply> getRepList() {
		return repList;
	}
	public void setRepList(List<ComplaintReply> repList) {
		this.repList = repList;
	}
	
}
