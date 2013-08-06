package com.szwx.yht.bean;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 知识库模块实块信息BEAN
 * @author zhangyj
 * @date Jun 15, 2012
 */
@Entity
@Table(name="YHT_KNOWLEDGE_MOUDLE")
@Proxy(lazy=true)
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true,selectBeforeUpdate=true)
public class KnowLedgeMoudle implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -1329221339486685102L;
	@Id
	@SequenceGenerator(allocationSize=1,name="SEQ_ID_GEN",sequenceName="SEQ_KLGMOUDLE_YHT")
	@GeneratedValue(strategy= GenerationType.SEQUENCE,generator="SEQ_ID_GEN")
	private int id;								//ID
	@Column(name="ORDER_NO",nullable=false)
	private int  orderNo;						//同级排序
	@Column(name="MOUDLE_NAME",nullable=false,unique=false)
	private String moudleName;					//模块名称
	@ManyToOne
	@JoinColumn(name="PARENT")
	private KnowLedgeMoudle parent;				//上级模块
	@Column(name="MOUDLE_CODE",length=16,nullable=true)
	private String moudleCode;					//模块编号
	
	@Column(name="provincial_CODE",length=16,nullable=true)
	private String provincialCode;

	//////////////////////////GET SET////////////////////////////
	

	public String getMoudleName() {
		return moudleName;
	}

	public void setMoudleName(String moudleName) {
		this.moudleName = moudleName;
	}

	public KnowLedgeMoudle getParent() {
		return parent;
	}

	public void setParent(KnowLedgeMoudle parent) {
		this.parent = parent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getProvincialCode() {
		return provincialCode;
	}

	public void setProvincialCode(String provincialCode) {
		this.provincialCode = provincialCode;
	}

	public String getMoudleCode() {
		return moudleCode;
	}

	public void setMoudleCode(String moudleCode) {
		this.moudleCode = moudleCode;
	}
}
