package com.szwx.yht.dto;

import java.util.Date;

public class WSDoctorDto {
	private int order ;//列表里面的排序
	private Date workDate;//排版时间
	private int workType;//上下午0：上午，1:下午
	private int isFull;//是否已挂满0：已满 1：未满 9:停诊8:时间未到
	private Long code;//排班id 
	
	public WSDoctorDto(){
		
	}
	
	
	public WSDoctorDto(int order, Date workDate, int workType, int isFull, Long code) {
		super();
		this.order = order;
		this.workDate = workDate;
		this.workType = workType;
		this.isFull = isFull;
		this.code=code;
	}


	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public Date getWorkDate() {
		return workDate;
	}
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	public int getWorkType() {
		return workType;
	}
	public void setWorkType(int workType) {
		this.workType = workType;
	}
	public int getIsFull() {
		return isFull;
	}
	public void setIsFull(int isFull) {
		this.isFull = isFull;
	}
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}

}
