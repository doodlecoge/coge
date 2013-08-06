package com.szwx.yht.bean;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.Date;

/**
 * 医生信息实体BEAN
 * @author zhangyj
 * @date Jun 18, 2012
 */
@Entity
@Table(name="YHT_DOCTOR")
@Proxy(lazy=true)
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true,selectBeforeUpdate=true)
@Inheritance(strategy= InheritanceType.JOINED)
public class Doctor implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 7659739051106286886L;
//	@Id
//	@SequenceGenerator(allocationSize=1,name="SEQ_ID_GEN",sequenceName="SEQ_DOCTOR_YHT")
//	@GeneratedValue(generator="SEQ_ID_GEN",strategy=GenerationType.SEQUENCE)
//	private int id;								//ID,唯一标示
	@JoinColumn(name="DEPART_CODE")
	@ManyToOne
	private Depart depart;						//所属科室编码

	@Column(name="IS_EXPERT")
	private boolean isExpert;					//是否专家
	@Column(name="NAME",nullable=false,length=20)
	private String name;						//医生名字
	@Column(name="SPELL_CODE",length=50)
	private String spellCode;					//拼音编码
	@Id
	@Column(name="FOR_WORK_NO",length=20)
	private String forWorkNo;					//医生编码
	@Column(name="DOCTOR_INFO",length=1000)
	private String doctorInfo;					//医生简介
	@Column(name="DOCTOR_RANK",length=100)
	private String doctorRank;					//医生职称
	@Column(name="DOCTOR_SEX")
	private int sex;							//医生性别
	@Column(name="PHOTO")
	private File photo;							//医生头像
	@Column(name="SPECIALTY",length=500)
	private String specialty;					//医生特长
	@Column(name="REMARK",length=300)
	private String remark;						//备注
	@Column(name="SORT_ORDER")
	private Integer sortOrder;						//排序值
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TIME")
	private Date createTimee;					//创建时间
	@Column(name="TITLE_ID",length=20)
	private String titleId;						//职称代码
	@Column(name="DESCRIPTION",length=2000)
	private String description;					//医生简介
	@Column(name="INTRODUCE_URL",length=200)
	private String introduceURL;				//医生介绍URL
	@Column(name="DOCTORFORDEPARTNAME",length=200)
	private String departNameForDoctor;				//坐诊科室
	////////////////////////////////GET SET////////////////////////////
	
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}

	public Depart getDepart() {
		return depart;
	}

	public void setDepart(Depart depart) {
		this.depart = depart;
	}

	public boolean isExpert() {
		return isExpert;
	}

	public void setExpert(boolean isExpert) {
		this.isExpert = isExpert;
	}

	public String getDepartNameForDoctor() {
		return departNameForDoctor;
	}

	public void setDepartNameForDoctor(String departNameForDoctor) {
		this.departNameForDoctor = departNameForDoctor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDoctorInfo() {
		return doctorInfo;
	}

	public void setDoctorInfo(String doctorInfo) {
		this.doctorInfo = doctorInfo;
	}

	public String getDoctorRank() {
		return doctorRank;
	}

	public void setDoctorRank(String doctorRank) {
		this.doctorRank = doctorRank;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Date getCreateTimee() {
		return createTimee;
	}

	public void setCreateTimee(Date createTimee) {
		this.createTimee = createTimee;
	}

	public String getForWorkNo() {
		return forWorkNo;
	}

	public void setForWorkNo(String forWorkNo) {
		this.forWorkNo = forWorkNo;
	}

	public String getSpellCode() {
		return spellCode;
	}

	public void setSpellCode(String spellCode) {
		this.spellCode = spellCode;
	}

	public String getTitleId() {
		return titleId;
	}

	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIntroduceURL() {
		return introduceURL;
	}

	public void setIntroduceURL(String introduceURL) {
		this.introduceURL = introduceURL;
	}

	
}
