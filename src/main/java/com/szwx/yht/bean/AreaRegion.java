package com.szwx.yht.bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 行政区域实体BEAN
 * @author zhangyj
 * @date Mar 16, 2012
 */
@Entity
@Table(name="YHT_AREA_REGION")
public class AreaRegion implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3084173972456592836L;
	@Id
	@Column(name="REGION_NO",length=15)
	private String regionNo;		//区域码    
	@Column(name="REGION_NAME",nullable=false,length=100)
	private String regionName;		//区域名字
	@ManyToOne
	@JoinColumn(name="PARENT_ID")
	private AreaRegion parentRegion;//上级区域
	
	//***************＊＊＊**GET　ＳＥＴ＊＊＊＊＊＊＊＊＊＊
	
	public String getRegionNo() {
		return regionNo;
	}
	public void setRegionNo(String regionNo) {
		this.regionNo = regionNo;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public AreaRegion getParentRegion() {
		return parentRegion;
	}
	public void setParentRegion(AreaRegion parentRegion) {
		this.parentRegion = parentRegion;
	}
		
}	
		
		