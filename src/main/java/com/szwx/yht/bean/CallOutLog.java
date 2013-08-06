package com.szwx.yht.bean;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 呼出电话日志
 * @author zhangyj
 * @date Jun 18, 2012
 */
@Entity
@Table(name="YHT_CALL_OUT")
@Proxy(lazy=true)
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true,selectBeforeUpdate=true)
public class CallOutLog implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -2862379229383242020L;
	@Id
	@SequenceGenerator(allocationSize=1,name="SEQ_ID_GEN",sequenceName="SEQ_CALL_OUT_YHT")
	@GeneratedValue(generator="SEQ_ID_GEN",strategy= GenerationType.SEQUENCE)
	private long id;		//呼出流水号
	@ManyToOne
	@JoinColumn(name="OUT_USER")
	private User outUser;	//呼出操作者
	@Column(name="TO_PHONE",nullable=false,length=15)
	private String toPhone;	//呼出号码
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CALL_TIME")
	private Date callTime;	//呼出时间
	@Temporal(TemporalType.TIME)
	@Column(name="CONNECT_TIME")
	private Date  connectTime;			//接通时间
	@Temporal(TemporalType.TIME)
	@Column(name="OFF_TIME")
	private Date  offTime;				//挂机时间
	@Column(name="OUT_TYPE")
	private int outType;	//呼出类型 eg:   1:回访 2：其它
	@Column(name="IS_CONNECT")
	private int isConnect;	//1:呼出成功 2：接通
	@Column(name="CALL_CODE",unique=true,length=100)
	private String callCode;		//话机服务生成的流水号
	@Column(name="RECORDING",unique=true,length=100)
	private String recordIng;		//录音流水号
	@Transient
	private String spareOne;
	//////////////////////GET SET////////////////////////////
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getOutUser() {
		return outUser;
	}

	public void setOutUser(User outUser) {
		this.outUser = outUser;
	}

	public String getToPhone() {
		return toPhone;
	}

	public void setToPhone(String toPhone) {
		this.toPhone = toPhone;
	}

	public Date getCallTime() {
		return callTime;
	}

	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}

	public int getOutType() {
		return outType;
	}

	public void setOutType(int outType) {
		this.outType = outType;
	}

	public Date getOffTime() {
		return offTime;
	}

	public void setOffTime(Date offTime) {
		this.offTime = offTime;
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

	public String getSpareOne() {
		return spareOne;
	}

	public void setSpareOne(String spareOne) {
		this.spareOne = spareOne;
	}
}
