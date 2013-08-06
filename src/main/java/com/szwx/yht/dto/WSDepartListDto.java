package com.szwx.yht.dto;

import com.szwx.yht.bean.Depart;

public class WSDepartListDto {
	private Depart depart;
	private int totalNum;
	private int regNum;
	private int isFull;//是否已挂满0：已满 1：未满
	private Long code;//排班id
	public WSDepartListDto(){}
	public WSDepartListDto(Depart depart, int totalNum, int regNum, int isFull,Long code) {
		super();
		this.depart = depart;
		this.totalNum = totalNum;
		this.regNum = regNum;
		this.isFull = isFull;
		this.code=code;
	}
	public Depart getDepart() {
		return depart;
	}
	public void setDepart(Depart depart) {
		this.depart = depart;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public int getRegNum() {
		return regNum;
	}
	public void setRegNum(int regNum) {
		this.regNum = regNum;
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
