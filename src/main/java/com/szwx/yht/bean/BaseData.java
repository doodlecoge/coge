package com.szwx.yht.bean;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/**
 * 基础数据字典
 * @author zhangyj
 * @date Jun 30, 2012
 */
@Entity
@Table(name="YHT_BASE_DATA")
@Proxy(lazy=true)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Cache(usage= CacheConcurrencyStrategy.READ_ONLY)
public class BaseData {
	@Id
	@Column(name="ID")
	private int id;				//ID
	@Column(name="DATA",length=15,unique=true,nullable=false)
	private String data;		//数据
	@ManyToOne
	@JoinColumn(name="PARENT",nullable=true)
	private BaseData parent;	//上级

	/////////////////////////////GET SET//////////////////////////////
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public BaseData getParent() {
		return parent;
	}
	public void setParent(BaseData parent) {
		this.parent = parent;
	}
	
}
