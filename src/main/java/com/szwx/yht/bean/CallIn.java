package com.szwx.yht.bean;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 电话呼入流水BEAN
 * @author zhangyj
 * @date Jun 15, 2012
 */
@Entity
@Table(name="YHT_CALL_IN")
@Proxy(lazy=true)
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true,selectBeforeUpdate=true)
public class CallIn implements Serializable{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -7075444001282875961L;
	@Id
	@SequenceGenerator(name = "SEQ_ID_GEN", sequenceName = "SEQ_CALLIN_YHT", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_GEN")
    @Column(name="CALL_IN_ORDER")
	private long id;					//呼入流水号
	@Column(name="CALL_IN_PHONE",length=15,nullable=false)
	private String callInPhone;			//呼入电话
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CALL_IN_TIME")
	private Date  callInTime;			//呼入时间
	@Temporal(TemporalType.TIME)
	@Column(name="CONNECT_TIME")
	private Date  connectTime;			//接通时间
	@Temporal(TemporalType.TIME)
	@Column(name="OFF_TIME")
	private Date  offTime;				//挂机时间
	@ManyToOne
	@JoinColumn(name="OPEATE_ESQ",updatable=false)
	private User opeateESQ;				//接电话客服
	@Column(name="IS_CONNECT")
	private int isConnect;			//是否接通	1：呼入成功 2：接通
	@Column(name="CALL_CODE",unique=false,length=100)	//转接时未生成新的话单
	private String callCode;		//话机服务生成的流水号
	@Column(name="RECORDING",unique=true,length=100)
	private String recordIng;		//录音流水号
	@Column(name="APPROVING")
	private int approving;			//满意度	1：非常满意 2：一般 3：不满意 0:未评价
	@Column(name="CALL_TYPE",columnDefinition="int default 0")
	private int callType;   		//呼入类型  1：挂号 2：咨询 3：投诉 4：人工服务
	@Transient
	private String spareOne;
	@Column(name="IS_SEND")
	private Integer isSend;   //1:已上传
	

	///////////////////////GET  SET////////////////////
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCallInPhone() {
		return callInPhone;
	}
	public void setCallInPhone(String callInPhone) {
		this.callInPhone = callInPhone;
	}
	public Date getCallInTime() {
		return callInTime;
	}
	public Integer getIsSend() {
		return isSend;
	}
	public void setIsSend(Integer isSend) {
		this.isSend = isSend;
	}
	public void setCallInTime(Date callInTime) {
		this.callInTime = callInTime;
	}
	public User getOpeateESQ() {
		return opeateESQ;
	}
	public void setOpeateESQ(User opeateESQ) {
		this.opeateESQ = opeateESQ;
	}
	public int getIsConnect() {
		return isConnect;
	}
	public void setIsConnect(int isConnect) {
		this.isConnect = isConnect;
	}
	public Date getConnectTime() {
		return connectTime;
	}
	public void setConnectTime(Date connectTime) {
		this.connectTime = connectTime;
	}
	public Date getOffTime() {
		return offTime;
	}
	public void setOffTime(Date offTime) {
		this.offTime = offTime;
	}
	public String getCallCode() {
		return callCode;
	}
	public void setCallCode(String callCode) {
		this.callCode = callCode;
	}
	public String getRecordIng() {
		return recordIng;
	}
	public void setRecordIng(String recordIng) {
		this.recordIng = recordIng;
	}
	public int getApproving() {
		return approving;
	}
	public void setApproving(int approving) {
		this.approving = approving;
	}
	public String getSpareOne() {
		return spareOne;
	}
	public void setSpareOne(String spareOne) {
		this.spareOne = spareOne;
	}
	public int getCallType() {
		return callType;
	}
	public void setCallType(int callType) {
		this.callType = callType;
	}
}
