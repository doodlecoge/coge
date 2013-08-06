package com.szwx.yht.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-12
 * Time: 下午4:54
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "YHT_DOCTOR")
public class Doctor {
    @Id
    @Column(name = "FOR_WORK_NO")
    private String forWorkNo;

    @Column(name = "NAME")
    private String doctorName;


    public String getForWorkNo() {
        return forWorkNo;
    }

    public void setForWorkNo(String forWorkNo) {
        this.forWorkNo = forWorkNo;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
