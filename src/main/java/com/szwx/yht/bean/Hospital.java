package com.szwx.yht.bean;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * 医院信息实体BEAN
 * @author zhangyj
 * @date Jun 18, 2012
 */
@Entity
@Table(name="YHT_HOSPITAL_INFO")
@Proxy(lazy=true)
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true,selectBeforeUpdate=true)
public class Hospital implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -5130794302065834870L;
//	@Id
//	@SequenceGenerator(allocationSize=1,initialValue=1,name="SEQ_ID_GEN",sequenceName="SEQ_HOSPITAL_YHT")
//	@GeneratedValue(generator="SEQ_ID_GEN",strategy=GenerationType.SEQUENCE)
//	private int id;									//ID,唯一标示
	@Column(name="HOSPITAL_NAME",nullable=false,unique=true,length=50)
	private String name;							//医院名称
	@Column(name="SPELL_CODE",nullable=false,length=10)
	private String spellCode;						//拼音编码
	@Id
	@Column(name="HOSPITAL_CODE",length=16)
	private String hospitalCode;					//医院编码
	@Column(name="HOSPITAL_TYPE",nullable=false,length=100)
	private String hospitalType;					//医院类型
	@Column(name="hospital_LEVEL",nullable=false,length=100)
	private String hospitalLevel;					//医院等级
	@ManyToOne
	@JoinColumn(name="AREA_REGION")
	private AreaRegion areaRegion;					//所属区域
	@Column(name="ADDRESS",length=100,nullable=false)
	private String address;							//地址
	@Column(name="LINK_TEL",length=15)
	private String linkTel;							//联系电话
	@Column(name="WEB_HOST",length=100)
	private String webHost;							//医院门户网站
	@Column(name="HOSPITAL_DESC")
	private String hospitalDesc;					//详细描述
	@Column(name="BUS_LINES")
	private String busLines;						//乘车路线
	@Column(name="IS_TOP",nullable=false)
	private boolean isTop;							//是否推荐
	@Column(name="HOSPITAL_IMAGE")
	private File hospitalImage;						//展示图片
	@Column(name="SORT_ORDER")
	private int sortOrder;							//排序值
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TIME")
	private Date createTimee;						//创建时间
	@OrderBy("sortOrder")
	@OneToMany(cascade= CascadeType.REMOVE,fetch = FetchType.LAZY, mappedBy = "hospital")

	private Set<Depart> departSet=new TreeSet<Depart>();
	@Column(name="USE_MEDICAL",length=200)
	private String useMedical;						//医院支持的医保 eg: 园区医保，市区医保  用','隔开
	///////////////////////////////GET  SET///////////////////////
	
	
	
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHospitalType() {
		return hospitalType;
	}

	public void setHospitalType(String hospitalType) {
		this.hospitalType = hospitalType;
	}

	public String getHospitalLevel() {
		return hospitalLevel;
	}

	public void setHospitalLevel(String hospitalLevel) {
		this.hospitalLevel = hospitalLevel;
	}

	public AreaRegion getAreaRegion() {
		return areaRegion;
	}

	public void setAreaRegion(AreaRegion areaRegion) {
		this.areaRegion = areaRegion;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLinkTel() {
		return linkTel;
	}

	public void setLinkTel(String linkTel) {
		this.linkTel = linkTel;
	}

	public String getWebHost() {
		return webHost;
	}

	public void setWebHost(String webHost) {
		this.webHost = webHost;
	}

	public String getHospitalDesc() {
		return hospitalDesc;
	}

	public void setHospitalDesc(String hospitalDesc) {
		this.hospitalDesc = hospitalDesc;
	}

	public String getBusLines() {
		return busLines;
	}

	public void setBusLines(String busLines) {
		this.busLines = busLines;
	}

	public boolean isTop() {
		return isTop;
	}

	public void setTop(boolean isTop) {
		this.isTop = isTop;
	}

	public File getHospitalImage() {
		return hospitalImage;
	}

	public void setHospitalImage(File hospitalImage) {
		this.hospitalImage = hospitalImage;
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

	public String getSpellCode() {
		return spellCode;
	}

	public void setSpellCode(String spellCode) {
		this.spellCode = spellCode;
	}
	@JSON(serialize=false)
	public Set<Depart> getDepartSet() {
		return departSet;
	}

	public void setDepartSet(Set<Depart> departSet) {
		this.departSet = departSet;
	}

	public String getUseMedical() {
		return useMedical;
	}

	public void setUseMedical(String useMedical) {
		this.useMedical = useMedical;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
}
