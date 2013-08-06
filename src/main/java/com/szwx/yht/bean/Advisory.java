package com.szwx.yht.bean;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 咨询信息实体BEAN
 * @author zhangyj
 * @date Jun 19, 2012
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true,selectBeforeUpdate=true)
@Proxy(lazy=true)
@Table(name="YHT_ADVISORY")
public class Advisory implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -5792548454054711023L;
	@Id
	@SequenceGenerator(allocationSize=1,name="SEQ_ID_GEN",sequenceName="SEQ_ADVISORY_YHT")
	@GeneratedValue(generator="SEQ_ID_GEN",strategy= GenerationType.SEQUENCE)
	private long id;						//ID
	@ManyToOne
	@JoinColumn(name="CALL_ID")
	private CallIn callIn;					//呼入信息
	@Column(name="ID_CARD_CODE",length=20)
	private String IDCardCode;				//咨询人身份证编号
	@Column(name="NAME",length=10)
	private String name;					//咨询人姓名
	@Column(name="SEX")
	private int sex;			//性别		1：男 0：女
	@Column(name="LINK_TEL",length=15,nullable=false)
	private String linkTel;					//联系电话
	@Column(name="ADRESS",length=50)
	private String adress;					//联系地址
	@Column(name="E_MAIL",length=50)
	private String eMail;					//邮箱
	@Column(name="ADVISORY_DESC",nullable=false,columnDefinition="clob")
	private String advisoryDesc;			//咨询内容
	@Column(name="REMARK")
	private String reMark;					//备注
	@ManyToOne
	@JoinColumn(name="LAST_OPEAT_USER")
	private User lastOpeatUser;				//最后操作者
	@Column(name="STATE")
	private int state;						//状态  1：已解决 2：未解决
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TIME",nullable=false)
	private Date createeTime;				//咨询时间
	@ManyToOne
	@JoinColumn(name="KNOW_LEDGE_MOUDLE")
	private KnowLedgeMoudle moudle;			//所属知识点
	
	@Column(name="SCWX_ID",length=20)
	private String scwxId;	//上传电话记录
	@Column(name="ISSC")
	private Integer isSC;  //是否上传 0:未上传；1：已上传;2:数据出错
	///////////////////////////GET SET///////////////////////////////
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public CallIn getCallIn() {
		return callIn;
	}
	public void setCallIn(CallIn callIn) {
		this.callIn = callIn;
	}
	public String getIDCardCode() {
		return IDCardCode;
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
	public void setIDCardCode(String cardCode) {
		IDCardCode = cardCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLinkTel() {
		return linkTel;
	}
	public void setLinkTel(String linkTel) {
		this.linkTel = linkTel;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getEMail() {
		return eMail;
	}
	public void setEMail(String mail) {
		eMail = mail;
	}
	public String getAdvisoryDesc() {
		return advisoryDesc;
	}
	public void setAdvisoryDesc(String advisoryDesc) {
		this.advisoryDesc = advisoryDesc;
	}
	public String getReMark() {
		return reMark;
	}
	public void setReMark(String reMark) {
		this.reMark = reMark;
	}
	public User getLastOpeatUser() {
		return lastOpeatUser;
	}
	public void setLastOpeatUser(User lastOpeatUser) {
		this.lastOpeatUser = lastOpeatUser;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getCreateeTime() {
		return createeTime;
	}
	public void setCreateeTime(Date createeTime) {
		this.createeTime = createeTime;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public KnowLedgeMoudle getMoudle() {
		return moudle;
	}
	public void setMoudle(KnowLedgeMoudle moudle) {
		this.moudle = moudle;
	}
}
