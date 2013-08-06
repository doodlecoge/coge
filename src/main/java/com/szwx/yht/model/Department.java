package com.szwx.yht.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-10
 * Time: 下午2:50
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "YHT_DEPARTS")
public class Department {
    @Column(name = "HOSPITAL_ID")
    private String hospitalId;

    @Id
    @Column(name = "DEPART_CODE_NO")
    private String departCodeNo;

    @Column(name = "DEPART_NAME")
    private String departName;

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getDepartCodeNo() {
        return departCodeNo;
    }

    public void setDepartCodeNo(String departCodeNo) {
        this.departCodeNo = departCodeNo;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }
}
