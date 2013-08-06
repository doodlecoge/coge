package com.szwx.yht.bind;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-15
 * Time: 下午2:20
 * To change this template use File | Settings | File Templates.
 */
//@XmlRootElement(name = "DOCTORCALENDAR")
public class DoctorCalendar {
    @XmlElement
    public String Hospitalcode;
    @XmlElement
    public String WorkStatus;
    @XmlElement
    public String DoctorId;
    @XmlElement
    public String DepartId;
    @XmlElement
    public String WorkDate;
    @XmlElement
    public String WorkType;
    @XmlElement
    public String BeginNo;
    @XmlElement
    public String SpaceNo;
    @XmlElement
    public String Limited;
    @XmlElement
    public String Registryfee;
    @XmlElement
    public String Chinicfee;
    @XmlElement
    public String Expertsfee;


    public String getHospitalcode() {
        return Hospitalcode;
    }

    public void setHospitalcode(String hospitalcode) {
        Hospitalcode = hospitalcode;
    }

    public String getWorkStatus() {
        return WorkStatus;
    }

    public void setWorkStatus(String workStatus) {
        WorkStatus = workStatus;
    }

    public String getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(String doctorId) {
        DoctorId = doctorId;
    }

    public String getDepartId() {
        return DepartId;
    }

    public void setDepartId(String departId) {
        DepartId = departId;
    }

    public String getWorkDate() {
        return WorkDate;
    }

    public void setWorkDate(String workDate) {
        WorkDate = workDate;
    }

    public String getWorkType() {
        return WorkType;
    }

    public void setWorkType(String workType) {
        WorkType = workType;
    }

    public String getBeginNo() {
        return BeginNo;
    }

    public void setBeginNo(String beginNo) {
        BeginNo = beginNo;
    }

    public String getSpaceNo() {
        return SpaceNo;
    }

    public void setSpaceNo(String spaceNo) {
        SpaceNo = spaceNo;
    }

    public String getLimited() {
        return Limited;
    }

    public void setLimited(String limited) {
        Limited = limited;
    }

    public String getRegistryfee() {
        return Registryfee;
    }

    public void setRegistryfee(String registryfee) {
        Registryfee = registryfee;
    }

    public String getChinicfee() {
        return Chinicfee;
    }

    public void setChinicfee(String chinicfee) {
        Chinicfee = chinicfee;
    }

    public String getExpertsfee() {
        return Expertsfee;
    }

    public void setExpertsfee(String expertsfee) {
        Expertsfee = expertsfee;
    }
}
