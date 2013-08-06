package com.szwx.yht.bean;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 排班信息基类BEAN
 * @author zhangyj
 * @date Jun 19, 2012
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true,selectBeforeUpdate=true)
@Proxy(lazy=true)
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class WorkSchema implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -5596133620333584691L;
	@Id
	@SequenceGenerator(allocationSize=1,name="SEQ_ID_GEN",sequenceName="SEQ_WORK_SCHEMA_YHT")
	@GeneratedValue(generator="SEQ_ID_GEN",strategy= GenerationType.SEQUENCE)
	@Column(name="CODE")
	private long code;						//排班流水号
	@Temporal(TemporalType.DATE)
	@Column(name="WORK_DATE")
	private Date workDate;					//排班日期
	@OrderBy("code ASC")
	@OneToMany(cascade= CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "workSchema")
	private List<RegPipelined> regPipelinedList=new ArrayList<RegPipelined>();
	/////////////////////GET SET/////////////////////

	
	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public List<RegPipelined> getRegPipelinedList() {
		return regPipelinedList;
	}

	public void setRegPipelinedList(List<RegPipelined> regPipelinedList) {
		this.regPipelinedList = regPipelinedList;
	}

	
}
