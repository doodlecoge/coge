package com.szwx.yht.bean;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 挂号信息流水BEAN		（流水号生成触发智慧医疗生成一条新的排班记录）
 * @author zhangyj
 * @date Jun 14, 2012
 */
@Entity
@Table(name="YHT_REG_ORDER")
@Proxy(lazy=true)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class RegOrder implements Serializable{
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -6456312253292126469L;
	@Id
	@SequenceGenerator(name = "SEQ_ID_GEN", sequenceName = "SEQ_REG_ORDER_YHT", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_GEN")
	private long code;					//挂号流水号
	@ManyToOne
	@JoinColumn(name="CALL_IN_ORDER")
	private CallIn callIn;				//电话呼入流水
	
	@ManyToOne
	@JoinColumn(name="OPEATE_ESQ")
	private User opeateESQ;				//预约操作客服
	
	@ManyToOne
	@JoinColumn(name="CANCLE_ESQ")
	private User canclePeople;			//退号人
	
	/////////挂号信息//////////////////
	@ManyToOne
	@JoinColumn(name="REG_PEOPLE")
	private RegPeople regPeople;		//预约挂号人
	@ManyToOne
	@JoinColumn(name="TREAT_PEOPLE")
	private RegPeople treatPeople;		//就诊人
	@Column(name="TREAT_PEOPLE_TYPE")
	private int treatPeopleType;		//1:本人 2：子女  3：其他人
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TIMEEE")
	private Date creatTimeee;			//挂号信息生存时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="QUIT_TIME")
	private Date quitTimeee;			//退号时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATE_TIMEEE")
	private Date updateTimeee;			//最近更新时间
	@Column(name="STATE")
	private int state;					//挂号状态 1：挂号中 2：退号 3：爽约 4：正常就诊完毕 （智慧医疗系统改变3、4状态）
	@Column(name="ORDER_DESC",length=255)
	private String orderDesc;			//挂号单备注		eg:帮无身份证的亲人挂号(就诊人无身份证则拿预约人身份证)
	@Column(name="CHECK_CODE",nullable=false,length=6)
	private String checkCode;			//取号验证码		随机生成
	@Column(name="TREAT_ORDER",nullable=false)
	private int treatOrder;				//就诊序号	
	@Column(name="ZHYL_ID")
	private long yLId;					//智慧医疗ID
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="STATE_TIME")
	private Date stateTime;				//开始时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="END_TIME")
	private Date endTime;				//结束时间
	@Column(name="QUIT_TYPE")
	private Integer quitType;			//0:主动退号，1：医院停诊
	/////////////预约信息////////////////
	
	@ManyToOne
	@JoinColumn(name="REG_PIPELINED")
	private RegPipelined regPipelined;	//分时段信息
	
	
	@Column(name="SCWX_ID",length=20)
	private String scwxId;	//上传电话记录
	@Column(name="ISSC")
	private Integer isSC;  //是否上传 0:未上传；1：已上传;2:数据出错
	
	@Column(name="IDEALREGISTER_TIME")
	private Date idealRegisterTime;//理想就诊时间
	
	/*---------------------------------2013-3-13 add by gaowm------------------------------*/
	@Column(name="ID_CARD_CODE",length=20)
	private String  identityCard;	//身份证编号	
	@Column(name="MEDICAL_NO",length=30)
	private String 	medicalNo;		//医保编号
	@Column(name="MEDICAL_TYPE")
	private Integer medicalType;	//医保类型    	eg:  0:自费
	@Column(name="TRUE_NAME",length=15)
	private String trueName;    //就诊人姓名
	@Column(name="SEX")
	private Integer sex;   //性别
	@Column(name="MOBILE",length=15)
	private String mobile;      //电话
	
	@Column(name="CANCLENAME",length=15)
	private String cancleName;		 //退号操作客服姓名
	@Column(name="opeatename",length=15)
	private String opeateName;     //挂号操作客服姓名
	
	@Temporal(TemporalType.DATE)
	@Column(name="WORK_DATE")
	private Date workDate;					//排班日期
	
	@Column(name="REGISTRY_FEE")
	private Float registryFee;						//挂号费
	@Column(name="CLINIC_FEE")
	private Float clinicFee;						//门诊费
	@Column(name="FACTORAGE_FEE")
	private Float factorageFee;						//手续费
	@Column(name="EXPERTS_FEE")
	private Float expertsFee;						//专家费用
	@Column(name="HOSPITAL_CODE",length=16)
	private String hospitalCode;					//医院编码
	@Column(name="HOSPITAL_NAME",length=50)
	private String hospitalName;					//医院名称
	@Column(name="DEPART_NAME",length=50)
	private String departName;						//科室名称
	@Column(name="DEPART_CODE_NO",length=16)
	private String departCodeNo;					//科室编号 
	@Column(name="FOR_WORK_NO",length=20)
	private String forWorkNo;					//医生编码
	
	@Column(name="REGTYPE")
	private Integer regType;						//号源类型 0：专家 1：实时
	
	@Column(name="Doctor_NAME",length=20)
	private String docName;						//医生姓名
	
	
	
	
	
	//////////////////////////GET  SET////////////////////////////////
	
	public long getCode() {
		return code;
	}
	public Integer getQuitType() {
		return quitType;
	}
	public void setQuitType(Integer quitType) {
		this.quitType = quitType;
	}
	public String getScwxId() {
		return scwxId;
	}
	public void setScwxId(String scwxId) {
		this.scwxId = scwxId;
	}
	public Integer getIsSC() {
		return isSC;
	}
	public void setIsSC(Integer isSC) {
		this.isSC = isSC;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public RegPeople getRegPeople() {
		return regPeople;
	}
	public void setRegPeople(RegPeople regPeople) {
		this.regPeople = regPeople;
	}
	public RegPeople getTreatPeople() {
		return treatPeople;
	}
	public void setTreatPeople(RegPeople treatPeople) {
		this.treatPeople = treatPeople;
	}
	public int getTreatPeopleType() {
		return treatPeopleType;
	}
	public void setTreatPeopleType(int treatPeopleType) {
		this.treatPeopleType = treatPeopleType;
	}
	public Date getCreatTimeee() {
		return creatTimeee;
	}
	public void setCreatTimeee(Date creatTimeee) {
		this.creatTimeee = creatTimeee;
	}
	public Date getUpdateTimeee() {
		return updateTimeee;
	}
	public void setUpdateTimeee(Date updateTimeee) {
		this.updateTimeee = updateTimeee;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getOrderDesc() {
		return orderDesc;
	}
	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}
	public User getOpeateESQ() {
		return opeateESQ;
	}
	public void setOpeateESQ(User opeateESQ) {
		this.opeateESQ = opeateESQ;
	}
	public CallIn getCallIn() {
		return callIn;
	}
	public void setCallIn(CallIn callIn) {
		this.callIn = callIn;
	}
	public RegPipelined getRegPipelined() {
		return regPipelined;
	}
	public void setRegPipelined(RegPipelined regPipelined) {
		this.regPipelined = regPipelined;
	}
	public String getCheckCode() {
		return checkCode;
	}
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	public Date getQuitTimeee() {
		return quitTimeee;
	}
	public void setQuitTimeee(Date quitTimeee) {
		this.quitTimeee = quitTimeee;
	}
	public int getTreatOrder() {
		return treatOrder;
	}
	public void setTreatOrder(int treatOrder) {
		this.treatOrder = treatOrder;
	}
	public long getYLId() {
		return yLId;
	}
	public void setYLId(long id) {
		yLId = id;
	}
	public Date getStateTime() {
		return stateTime;
	}
	public void setStateTime(Date stateTime) {
		this.stateTime = stateTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public User getCanclePeople() {
		return canclePeople;
	}
	public void setCanclePeople(User canclePeople) {
		this.canclePeople = canclePeople;
	}
	public Date getIdealRegisterTime() {
		return idealRegisterTime;
	}
	public void setIdealRegisterTime(Date idealRegisterTime) {
		this.idealRegisterTime = idealRegisterTime;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public String getMedicalNo() {
		return medicalNo;
	}
	public void setMedicalNo(String medicalNo) {
		this.medicalNo = medicalNo;
	}
	public Integer getMedicalType() {
		return medicalType;
	}
	public void setMedicalType(Integer medicalType) {
		this.medicalType = medicalType;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCancleName() {
		return cancleName;
	}
	public void setCancleName(String cancleName) {
		this.cancleName = cancleName;
	}
	public String getOpeateName() {
		return opeateName;
	}
	public void setOpeateName(String opeateName) {
		this.opeateName = opeateName;
	}
	public Date getWorkDate() {
		return workDate;
	}
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	
	public Float getRegistryFee() {
		return registryFee;
	}
	public void setRegistryFee(Float registryFee) {
		this.registryFee = registryFee;
	}
	public Float getClinicFee() {
		return clinicFee;
	}
	public void setClinicFee(Float clinicFee) {
		this.clinicFee = clinicFee;
	}
	public Float getFactorageFee() {
		return factorageFee;
	}
	public void setFactorageFee(Float factorageFee) {
		this.factorageFee = factorageFee;
	}
	public Float getExpertsFee() {
		return expertsFee;
	}
	public void setExpertsFee(Float expertsFee) {
		this.expertsFee = expertsFee;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public String getDepartCodeNo() {
		return departCodeNo;
	}
	public void setDepartCodeNo(String departCodeNo) {
		this.departCodeNo = departCodeNo;
	}
	public String getForWorkNo() {
		return forWorkNo;
	}
	public void setForWorkNo(String forWorkNo) {
		this.forWorkNo = forWorkNo;
	}
	public Integer getRegType() {
		return regType;
	}
	public void setRegType(Integer regType) {
		this.regType = regType;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
}
