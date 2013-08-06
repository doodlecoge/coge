package com.szwx.yht.bean;

import javax.persistence.*;

/**
 * 专家排班实体BEAN
 * @author zhangyj
 * @date Jun 19, 2012
 */
@Entity
@Table(name="YHT_DOCTOR_WS")
public class WSDoctor extends WorkSchema{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -1298515777365467621L;
	@ManyToOne
	@JoinColumn(name="DOCTOR_EXPERT")
	private DoctorExpert doctorExpert;		//专家信息
	@ManyToOne
	@JoinColumn(name="DEPART")
	private Depart depart;					//排班属于哪个科室(卖狗肉的)
	@Column(name="REGISTRY_FEE")
	private float registryFee;						//挂号费
	@Column(name="CLINIC_FEE")
	private float clinicFee;						//门诊费
	@Column(name="FACTORAGE_FEE")
	private float factorageFee;						//手续费
	@Column(name="EXPERTS_FEE")
	private float expertsFee;						//专家费用
	////////////////////////////GET  SET/////////////////////////////////

	public DoctorExpert getDoctorExpert() {
		return doctorExpert;
	}
	public void setDoctorExpert(DoctorExpert doctorExpert) {
		this.doctorExpert = doctorExpert;
	}
	public Depart getDepart() {
		return depart;
	}
	public void setDepart(Depart depart) {
		this.depart = depart;
	}
	public float getRegistryFee() {
		return registryFee;
	}
	public void setRegistryFee(float registryFee) {
		this.registryFee = registryFee;
	}
	public float getClinicFee() {
		return clinicFee;
	}
	public void setClinicFee(float clinicFee) {
		this.clinicFee = clinicFee;
	}
	public float getFactorageFee() {
		return factorageFee;
	}
	public void setFactorageFee(float factorageFee) {
		this.factorageFee = factorageFee;
	}
	public float getExpertsFee() {
		return expertsFee;
	}
	public void setExpertsFee(float expertsFee) {
		this.expertsFee = expertsFee;
	}
	
}
