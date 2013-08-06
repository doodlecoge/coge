package com.szwx.yht.bean;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * 专家医生信息实体BEAN
 * @author zhangyj
 * @date Jun 18, 2012
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true,selectBeforeUpdate=false)
@Proxy(lazy=true)
@Table(name="YHT_DOCTOR_EXPERT")
public class DoctorExpert extends Doctor{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 2592136831305060579L;
	
	@Column(name="EXPERTS_FEE")
	private Float expertsFee;						//专家费用
	@Column(name="IS_TOP")
	private Boolean isTop;							//是否推荐
	@Column(name="REG_COUNT")
	private Integer regCount;							//预约次数
	@Column(name="REG_MINUTE")
	private Double regMinute;							//专家的平均工作时间
	@Column(name="FACTORAGE_FEE")
	private Float factorageFee;						//手续费
	@OneToMany(cascade= CascadeType.REMOVE,fetch = FetchType.LAZY, mappedBy = "doctorExpert")
	private Set<WSDoctor> wsDoctorSet=new TreeSet<WSDoctor>();
	/////////////////////////GET SET///////////////////////////////////
	
	
	

	public Set<WSDoctor> getWsDoctorSet() {
		return wsDoctorSet;
	}

	public void setWsDoctorSet(Set<WSDoctor> wsDoctorSet) {
		this.wsDoctorSet = wsDoctorSet;
	}

	public Float getExpertsFee() {
		return expertsFee;
	}
	
	public Double getRegMinute() {
		return regMinute;
	}

	public void setRegMinute(Double regMinute) {
		this.regMinute = regMinute;
	}

	public void setExpertsFee(Float expertsFee) {
		this.expertsFee = expertsFee;
	}

	public Boolean getIsTop() {
		return isTop;
	}

	public void setIsTop(Boolean isTop) {
		this.isTop = isTop;
	}

	public Integer getRegCount() {
		return regCount;
	}

	public void setRegCount(Integer regCount) {
		this.regCount = regCount;
	}

	public Float getFactorageFee() {
		return factorageFee;
	}

	public void setFactorageFee(Float factorageFee) {
		this.factorageFee = factorageFee;
	}

}
