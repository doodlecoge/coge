package com.szwx.yht.bean;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 科室排班实体BEAN
 * @author zhangyj
 * @date Jun 19, 2012
 */
@Entity
@Table(name="YHT_DEPART_WS")
public class WSDepart extends WorkSchema{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -3863665876178264576L;
	@ManyToOne
	@JoinColumn(name="DEPART")
	private Depart depart;	//科室信息
	
	////////////////////////////GET SET/////////////////////////////
	
	public Depart getDepart() {
		return depart;
	}
	public void setDepart(Depart depart) {
		this.depart = depart;
	}
	
}
