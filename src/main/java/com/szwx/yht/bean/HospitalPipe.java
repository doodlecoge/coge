package com.szwx.yht.bean;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 医院分时段预约设置实体BEAN
 * @author zhangyj
 * @date Jun 19, 2012
 */
@Entity
@Table(name="YHT_HOSPITAL_PIPE")
@Proxy(lazy=true)
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true,selectBeforeUpdate=true)
public class HospitalPipe implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -5387258956995732113L;
	@Id
	@SequenceGenerator(allocationSize=1,name="SEQ_ID_GEN",sequenceName="SEQ_HOSPITAL_PIPE_YHT")
	@GeneratedValue(generator="SEQ_ID_GEN",strategy= GenerationType.SEQUENCE)
	private int id;
	@ManyToOne
	@JoinColumn(name="HOSPITAL")
	private Hospital hospital;					//医院信息
	@Column(name="WORK_TYPE")
	private int workType;   					//1：上午 2：下午
	@Column(name="START_TIME",nullable=false,length=20)
	private String startTime;					//开始时间
	@Column(name="END_TIME",nullable=false,length=20)
	private String endTime;						//结束时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_UP_DATE")
	private Date lastUpdate;					//日期
	@Column(name="STATE")
	private int state;							//状态 1：有效 2：停诊
	
	//////////////////////////GET SET///////////////////////////////////////
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getWorkType() {
		return workType;
	}

	public void setWorkType(int workType) {
		this.workType = workType;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
