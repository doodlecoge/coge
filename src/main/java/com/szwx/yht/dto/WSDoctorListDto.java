package com.szwx.yht.dto;

import com.szwx.yht.bean.Depart;
import com.szwx.yht.bean.DoctorExpert;

import java.util.List;

public class WSDoctorListDto {
	
	private DoctorExpert doctor; 
	private Depart depart;
	private List<WSDoctorDto> wsDoctors;
	
	public WSDoctorListDto(){
		
	}
	
	public WSDoctorListDto(DoctorExpert doctor, Depart depart,
			List<WSDoctorDto> wsDoctors) {
		super();
		this.doctor = doctor;
		this.depart = depart;
		this.wsDoctors = wsDoctors;
	}

	public DoctorExpert getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorExpert doctor) {
		this.doctor = doctor;
	}

	public Depart getDepart() {
		return depart;
	}
	public void setDepart(Depart depart) {
		this.depart = depart;
	}

	public List<WSDoctorDto> getWsDoctors() {
		return wsDoctors;
	}

	public void setWsDoctors(List<WSDoctorDto> wsDoctors) {
		this.wsDoctors = wsDoctors;
	}
	
	
	
	

}
