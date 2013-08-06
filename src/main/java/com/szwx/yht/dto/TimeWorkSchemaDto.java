package com.szwx.yht.dto;

import com.szwx.yht.bean.RegPipelined;

public class TimeWorkSchemaDto {
	private int num;//已挂号源数量
	private RegPipelined regPipelined;//分时段排班信息
	private String startTime;//时间段开始时间
	private String endTime;//时间段结束时间
	private Long totalNum;//总号源数量
	public TimeWorkSchemaDto(){}
	public TimeWorkSchemaDto(int num, RegPipelined regPipelined,String startTime,String endTime,Long totalNum) {
		super();
		this.num = num;
		this.regPipelined = regPipelined;
		this.endTime=endTime;
		this.startTime=startTime;
		this.totalNum=totalNum;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public RegPipelined getRegPipelined() {
		return regPipelined;
	}
	public void setRegPipelined(RegPipelined regPipelined) {
		this.regPipelined = regPipelined;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Long getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}

}
