package com.szwx.yht.bean;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name="YHT_DEPARTS")
@Proxy(lazy=true)
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true,selectBeforeUpdate=true)
public class Depart implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -8636644837217875899L;
//	@Id
//	@SequenceGenerator(allocationSize=1,name="GEN_ID_GEN",sequenceName="SEQ_DEPART_YHT")
//	@GeneratedValue(generator="GEN_ID_GEN",strategy=GenerationType.SEQUENCE)
//	private int id;									//ID,唯一标示
	@ManyToOne
	@JoinColumn(name="HOSPITAL_ID")
	private Hospital hospital;						//所属医院
	@Column(name="DEPART_NAME",nullable=false,length=50)
	private String departName;						//科室名称
	@Column(name="SPELL_CODE",length=50)
	private String spellCode;						//拼音编码
	@Id
	@Column(name="DEPART_CODE_NO",length=16)
	private String departCodeNo;					//科室编号 eg：123456  123：大科室 456：小科室
	@Column(name="IS_SPECIALTIES",nullable=false)
	private Integer isSpecialties;					//是否是专科
	@Column(name="IS_KEY_DEPARTMENTS",nullable=false)
	private Integer isKeyDepartments;				//是否重点科室
	@Column(name="DEPART_DESC")
	private String DepartDesc;						//科室详细描述信息
	@Column(name="REGISTRY_FEE")
	private float registryFee;						//挂号费
	@Column(name="CLINIC_FEE")
	private float clinicFee;						//门诊费
	@Column(name="FACTORAGE_FEE")
	private float factorageFee;						//手续费
	@Column(name="IS_TOP")
	private boolean isTop;							//是否推荐
	@Column(name="SORT_ORDER")
	private int sortOrder;							//排序值
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TIME")
	private Date createTimee;						//创建时间
	@OrderBy(clause="code")
	@OneToMany(cascade= CascadeType.REMOVE,fetch = FetchType.LAZY, mappedBy = "depart")
	private Set<WSDepart> wsDepartSet=new TreeSet<WSDepart>();
//	@OrderBy(clause="forWorkNo")
//	@OneToMany(cascade=CascadeType.REMOVE,fetch = FetchType.LAZY, mappedBy = "depart")
//	private Set<Doctor> doctorSet=new TreeSet<Doctor>();
//	@OrderBy(clause="code")
//	@OneToMany(cascade=CascadeType.REMOVE,fetch = FetchType.LAZY, mappedBy = "depart")
//	private Set<WSDoctor> wsDoctorSet=new TreeSet<WSDoctor>();
	///////////////////////////GET SET/////////////////////////////
	
	
	
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	

	public Integer getIsSpecialties() {
		return isSpecialties;
	}

	public void setIsSpecialties(Integer isSpecialties) {
		this.isSpecialties = isSpecialties;
	}

	
	public Integer getIsKeyDepartments() {
		return isKeyDepartments;
	}

	public void setIsKeyDepartments(Integer isKeyDepartments) {
		this.isKeyDepartments = isKeyDepartments;
	}

	public String getDepartDesc() {
		return DepartDesc;
	}

	public void setDepartDesc(String departDesc) {
		DepartDesc = departDesc;
	}

	public float getRegistryFee() {
		return registryFee;
	}

	public String getSpellCode() {
		return spellCode;
	}

	public void setSpellCode(String spellCode) {
		this.spellCode = spellCode;
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

	public boolean isTop() {
		return isTop;
	}

	public void setTop(boolean isTop) {
		this.isTop = isTop;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Date getCreateTimee() {
		return createTimee;
	}

	public void setCreateTimee(Date createTimee) {
		this.createTimee = createTimee;
	}

	public String getDepartCodeNo() {
		return departCodeNo;
	}

	public void setDepartCodeNo(String departCodeNo) {
		this.departCodeNo = departCodeNo;
	}
	@JSON(serialize=false)
	public Set<WSDepart> getWsDepartSet() {
		return wsDepartSet;
	}

	public void setWsDepartSet(Set<WSDepart> wsDepartSet) {
		this.wsDepartSet = wsDepartSet;
	}

//	public Set<Doctor> getDoctorSet() {
//		return doctorSet;
//	}
//
//	public void setDoctorSet(Set<Doctor> doctorSet) {
//		this.doctorSet = doctorSet;
//	}

//	public Set<WSDoctor> getWsDoctorSet() {
//		return wsDoctorSet;
//	}
//
//	public void setWsDoctorSet(Set<WSDoctor> wsDoctorSet) {
//		this.wsDoctorSet = wsDoctorSet;
//	}

	
}
