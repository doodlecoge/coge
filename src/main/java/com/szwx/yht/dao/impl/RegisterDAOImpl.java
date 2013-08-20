package com.szwx.yht.dao.impl;

import com.szwx.yht.bean.*;
import com.szwx.yht.common.CommonDao;
import com.szwx.yht.dao.IRegisterDAO;
import com.szwx.yht.dto.WSDepartListDto;
import com.szwx.yht.dto.WSDoctorListDto;
import com.szwx.yht.exception.DaoException;
import com.szwx.yht.util.Page;
import com.szwx.yht.util.TimeUtil;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("registerDAO")
public class RegisterDAOImpl extends CommonDao implements IRegisterDAO {

    public List<WSDoctorListDto> getWsDoctorLists(String docName,
                                                  String deptName, Page page, String hospitalId) throws DaoException {
        List<WSDoctorListDto> wsDoctors = new ArrayList<WSDoctorListDto>();
        String nowDate = getFormatDate(1);
        String maxDate = getFormatDate(7);
        StringBuffer hql = new StringBuffer();
        hql.append("select distinct rp.workSchema.doctorExpert.forWorkNo,rp.workSchema.depart.departCodeNo,rp.workSchema.doctorExpert.name,rp.workSchema.depart.departName " +
                "from RegPipelined rp, DoctorExpert d " +
                "where d.forWorkNo=rp.workSchema.doctorExpert.forWorkNo ");
//		hql.append(" and rp.state!=1 ");
        hql.append(" and rp.workSchema.workDate>=to_date('").append(nowDate).append("','yyyy-MM-dd')");
        hql.append(" and rp.workSchema.workDate<=to_date('").append(maxDate).append("','yyyy-MM-dd')");
        if (hospitalId != null && !"".equals(hospitalId.trim())) {
            hql.append(" and rp.workSchema.depart.hospital.hospitalCode ='").append(hospitalId.trim()).append("' ");
        }
        if (docName != null && !"".equals(docName.trim())) {
            hql.append(" and ( rp.workSchema.doctorExpert.name like '%").append(docName).append("%'");
            hql.append(" or rp.workSchema.doctorExpert.spellCode like '%").append(docName).append("%' )");
        }
        if (deptName != null && !"".equals(deptName.trim())) {
            hql.append(" and ( rp.workSchema.depart.departName like '%").append(deptName).append("%'");
            hql.append(" or rp.workSchema.doctorExpert.departNameForDoctor like '%").append(deptName).append("%' ");
            hql.append(" or rp.workSchema.depart.spellCode like '%").append(deptName).append("%' )");
        }
        hql.append(" order by rp.workSchema.doctorExpert.name,rp.workSchema.depart.departName ");
        try {
            page.setTotalCount(queryList(hql.toString()).size());
            List<Object[]> list = queryList(hql.toString(), page.getPageNum(), page.getPageSize());
            wsDoctors = getWsDoctors(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("RegisterDAOImpl.getWsDoctorLists() 查询异常1：" + e.getMessage(), e);
        }
        return wsDoctors;
    }


    private List<WSDoctorListDto> getWsDoctors(List<Object[]> list) throws Exception {
        List<WSDoctorListDto> wsDoctors = new ArrayList<WSDoctorListDto>();
        for (int i = 0; i < list.size(); i++) {
            WSDoctorListDto w = new WSDoctorListDto();
            w.setDoctor((DoctorExpert) this.unique(" from DoctorExpert d where d.forWorkNo='" + list.get(i)[0].toString() + "'"));
            w.setDepart((Depart) this.unique(" from Depart d where d.departCodeNo='" + list.get(i)[1].toString() + "'"));
            wsDoctors.add(w);
        }
        return wsDoctors;
    }


    private String getFormatDate(int num) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_YEAR, num);
        String s = TimeUtil.formatDate1(calendar.getTime(), "yyyy-MM-dd");
        return s;
    }


    public List<RegPipelined> getwsDoctorListsByWdAndTimeAndWorkType(
            WSDoctorListDto wd, String time, int j) throws DaoException {
        List<RegPipelined> list = new ArrayList<RegPipelined>();
        StringBuffer hql = new StringBuffer();
        hql.append("from RegPipelined rp where rp.workSchema.doctorExpert.forWorkNo='");
        hql.append(wd.getDoctor().getForWorkNo()).append("' ");
        hql.append(" and rp.workSchema.depart.departCodeNo='");
        hql.append(wd.getDepart().getDepartCodeNo()).append("' ");
        hql.append(" and rp.workSchema.workDate=to_date('").append(time).append("','yyyy-MM-dd')");
        hql.append(" and rp.workType=").append(j);
//		hql.append(" and rp.state!=1 ");
        hql.append(" order by rp.code ");
        try {
            list = queryList(hql.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("RegisterDAOImpl.getwsDoctorListsByWdAndTimeAndWorkType() 查询异常1：" + e.getMessage(), e);
        }
        return list;
    }


    public List<RegPipelined> getRegPipelined(int workType, Date workDate,
                                              String doctorid, String departid) throws DaoException {
        List<RegPipelined> list = new ArrayList<RegPipelined>();
        StringBuffer hql = new StringBuffer();
        hql.append("from RegPipelined rp where rp.workSchema.doctorExpert.forWorkNo='");
        hql.append(doctorid).append("' ");
        hql.append(" and rp.workSchema.depart.departCodeNo='");
        hql.append(departid).append("' ");
        hql.append(" and rp.workSchema.workDate=to_date('").append(TimeUtil.formatDate1(workDate, "yyyy-MM-dd")).append("','yyyy-MM-dd')");
        hql.append(" and rp.workType=").append(workType);
        hql.append(" order by rp.code ");
        try {
            list = queryList(hql.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("RegisterDAOImpl.getRegPipelined() 查询异常1：" + e.getMessage(), e);
        }
        return list;
    }


    public List<WSDoctorListDto> getWsDoctorLists(Doctor doctor, Page page)
            throws DaoException {
        List<WSDoctorListDto> wsDoctors = new ArrayList<WSDoctorListDto>();
        String nowDate = getFormatDate(1);
        String maxDate = getFormatDate(7);
        StringBuffer hql = new StringBuffer();
        hql.append("select distinct rp.workSchema.doctorExpert.forWorkNo,rp.workSchema.depart.departCodeNo from RegPipelined rp where rp.workSchema.doctorExpert!=null");
//		hql.append(" and rp.state!=1 ");
        if (doctor != null && !"".equals(doctor.getForWorkNo().trim())) {
            hql.append(" and rp.workSchema.doctorExpert.forWorkNo='").append(doctor.getForWorkNo()).append("' ");
        }
        hql.append(" and rp.workSchema.workDate>=to_date('").append(nowDate).append("','yyyy-MM-dd')");
        hql.append(" and rp.workSchema.workDate<=to_date('").append(maxDate).append("','yyyy-MM-dd')");
        try {
            page.setTotalCount(queryList(hql.toString()).size());
            List<Object[]> list = queryList(hql.toString(), page.getPageNum(), page.getPageSize());
            wsDoctors = getWsDoctors(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("RegisterDAOImpl.getWsDoctorLists() 查询异常1：" + e.getMessage(), e);
        }
        return wsDoctors;
    }


    public List<WSDepartListDto> getWsDepartLists(Hospital hospital,
                                                  String deptName, Page page) throws DaoException {
        List<WSDepartListDto> departDtos = new ArrayList<WSDepartListDto>();
        long worktype = TimeUtil.getworkType();
        StringBuffer hql = new StringBuffer();
        hql.append("select distinct rp.workSchema.depart from RegPipelined rp where rp.workSchema.doctorExpert=null");
        hql.append(" and rp.workSchema.workDate=to_date('").append(TimeUtil.formatDate1(new Date(), "yyyy-MM-dd")).append("','yyyy-MM-dd')");
        if (deptName != null && !"".equals(deptName.trim())) {
            hql.append(" and ( rp.workSchema.depart.departName like '%").append(deptName).append("%' or ");
            hql.append(" rp.workSchema.depart.spellCode like '%").append(deptName).append("%')");
        }
        if (hospital != null && !"".equals(hospital.getHospitalCode().trim())) {
            hql.append(" and rp.workSchema.depart.hospital.hospitalCode='").append(hospital.getHospitalCode()).append("' ");
        }
        hql.append(" and rp.workType=").append(worktype);
        hql.append(" and rp.workSchema.depart.isSpecialties in (1,2)");
        try {
            page.setTotalCount(queryList(hql.toString()).size());
            List<Depart> list = queryList(hql.toString(), page.getPageNum(), page.getPageSize());
            for (Depart d : list) {
                if (d != null) {
                    WSDepartListDto wsDepart = new WSDepartListDto();
                    wsDepart.setDepart(d);
                    departDtos.add(wsDepart);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("RegisterDAOImpl.getWsDepartLists() 查询异常1：" + e.getMessage(), e);
        }

        return departDtos;
    }


    public List<RegPipelined> getDepartListRegPipelinedsByDepart(Depart depart)
            throws DaoException {
        List<RegPipelined> list = new ArrayList<RegPipelined>();
        long worktype = TimeUtil.getworkType();
        StringBuffer hql = new StringBuffer();
        hql.append("from RegPipelined rp where rp.workSchema.doctorExpert=null");
        hql.append(" and rp.workSchema.depart.departCodeNo='");
        hql.append(depart.getDepartCodeNo()).append("' ");
        hql.append(" and rp.workSchema.workDate=to_date('").append(TimeUtil.formatDate1(new Date(), "yyyy-MM-dd")).append("','yyyy-MM-dd')");
        hql.append(" and rp.workType=").append(worktype);
        try {
            list = queryList(hql.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("RegisterDAOImpl.getDepartListRegPipelinedsByDepart() 查询异常1：" + e.getMessage(), e);
        }
        return list;
    }


    public List<RegPipelined> getRegPipelinedByDepart(String departCardNo)
            throws DaoException {
        List<RegPipelined> list = new ArrayList<RegPipelined>();
        long worktype = TimeUtil.getworkType();
        StringBuffer hql = new StringBuffer();
        hql.append("from RegPipelined rp where rp.workSchema.doctorExpert=null");
        hql.append(" and rp.workSchema.depart.departCodeNo='");
        hql.append(departCardNo).append("' ");
        hql.append(" and rp.workSchema.workDate=to_date('").append(TimeUtil.formatDate1(new Date(), "yyyy-MM-dd")).append("','yyyy-MM-dd')");
        hql.append(" and rp.workType=").append(worktype);
        try {
            list = queryList(hql.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("RegisterDAOImpl.getRegPipelinedByDepart() 查询异常1：" + e.getMessage(), e);
        }
        return list;
    }


    public List<Hospital> getHospitalByworkDate(Date workDate)
            throws DaoException {
        List<Hospital> wsDoctors = new ArrayList<Hospital>();
        StringBuffer hql = new StringBuffer();
        hql.append("select distinct rp.workSchema.depart.hospital from RegPipelined rp where rp.workSchema.doctorExpert!=null");
        hql.append(" and rp.workSchema.workDate=to_date('").append(TimeUtil.formatDate1(workDate, "yyyy-MM-dd")).append("','yyyy-MM-dd')");
        try {
            wsDoctors = queryList(hql.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("RegisterDAOImpl.getHospitalByworkDate() 查询异常1：" + e.getMessage(), e);
        }
        return wsDoctors;
    }


    public List<Depart> getDepartByworkDateAndHopital(Date workDate, String code)
            throws DaoException {
        List<Depart> wsDoctors = new ArrayList<Depart>();
        StringBuffer hql = new StringBuffer();
//		hql.append("select distinct rp.workSchema.depart from RegPipelined rp where rp.workSchema.doctorExpert!=null");
//		hql.append(" and rp.workSchema.depart.hospital.hospitalCode='").append(code).append("' ");
//		hql.append(" and rp.workSchema.workDate=to_date('").append(TimeUtil.formatDate1(workDate, "yyyy-MM-dd")).append("','yyyy-MM-dd')");

        hql.append(" from Depart d where d.hospital.hospitalCode='").append(code).append("' ");
        try {
            wsDoctors = queryList(hql.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("RegisterDAOImpl.getDepartByworkDateAndHopital() 查询异常1：" + e.getMessage(), e);
        }
        return wsDoctors;
    }


    public List<DoctorExpert> getDoctorByworkDateAndDepart(Date workDate, String code)
            throws DaoException {
        List<DoctorExpert> wsDoctors = new ArrayList<DoctorExpert>();
        StringBuffer hql = new StringBuffer();
        hql.append("select distinct rp.workSchema.doctorExpert from RegPipelined rp where rp.workSchema.doctorExpert!=null");
        hql.append(" and rp.workSchema.depart.departCodeNo='").append(code).append("' ");
        hql.append(" and rp.workSchema.workDate=to_date('").append(TimeUtil.formatDate1(workDate, "yyyy-MM-dd")).append("','yyyy-MM-dd')");
        try {
            wsDoctors = queryList(hql.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("RegisterDAOImpl.getDoctorByworkDateAndDepart() 查询异常1：" + e.getMessage(), e);
        }
        return wsDoctors;
    }


    public List<WSDepartListDto> getWsDepartListDtosByDoctor(Depart depart,
                                                             Page page) throws DaoException {
        List<WSDepartListDto> departDtos = new ArrayList<WSDepartListDto>();
        long worktype = TimeUtil.getworkType();
        StringBuffer hql = new StringBuffer();
        hql.append("select distinct rp.workSchema.depart from RegPipelined rp where rp.workSchema.doctorExpert=null");
        hql.append(" and rp.workSchema.workDate=to_date('").append(TimeUtil.formatDate1(new Date(), "yyyy-MM-dd")).append("','yyyy-MM-dd')");
        if (depart != null && !"".equals(depart.getDepartCodeNo().trim())) {
            hql.append(" and rp.workSchema.depart.departCodeNo='").append(depart.getDepartCodeNo()).append("'");
        }
        hql.append(" and rp.workType=").append(worktype);
        hql.append(" and rp.workSchema.depart.isSpecialties in (1,2)");
        try {
            page.setTotalCount(queryList(hql.toString()).size());
            List<Depart> list = queryList(hql.toString(), page.getPageNum(), page.getPageSize());
            for (Depart d : list) {
                if (d != null) {
                    WSDepartListDto wsDepart = new WSDepartListDto();
                    wsDepart.setDepart(d);
                    departDtos.add(wsDepart);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("RegisterDAOImpl.getWsDepartListDtosByDoctor() 查询异常1：" + e.getMessage(), e);
        }

        return departDtos;
    }


    public List<Depart> getDepartByworkDateAndHopitalFromTime(String code)
            throws DaoException {
        List<Depart> departDtos = new ArrayList<Depart>();
        long worktype = TimeUtil.getworkType();
        StringBuffer hql = new StringBuffer();
        hql.append("select distinct rp.workSchema.depart from RegPipelined rp where rp.workSchema.doctorExpert=null");
        hql.append(" and rp.workSchema.workDate=to_date('").append(TimeUtil.formatDate1(new Date(), "yyyy-MM-dd")).append("','yyyy-MM-dd')");
        hql.append(" and rp.workType=").append(worktype);
        hql.append(" and rp.workSchema.depart.hospital.hospitalCode='").append(code).append("' ");
        try {
            departDtos = queryList(hql.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("RegisterDAOImpl.getDepartByworkDateAndHopitalFromTime() 查询异常1：" + e.getMessage(), e);
        }

        return departDtos;
    }


    public boolean isHaveRegister(RegOrder regOrder) throws DaoException {
        boolean result = false;//false 未挂，true 已挂
        StringBuffer hql = new StringBuffer();
        WSDoctor ws = new WSDoctor();
        WSDepart dp = new WSDepart();
        try {
            ws = (WSDoctor) regOrder.getRegPipelined().getWorkSchema();
        } catch (Exception e) {
        }
        try {
            dp = (WSDepart) regOrder.getRegPipelined().getWorkSchema();
        } catch (Exception e) {
        }
        hql.append(" from RegOrder r where r.regPeople.id=").append(regOrder.getRegPeople().getId());
        hql.append(" and r.state in (1,4)");
        hql.append(" and r.regPipelined.workSchema.workDate=to_date('").append(TimeUtil.formatDate1(regOrder.getRegPipelined().getWorkSchema().getWorkDate(), "yyyy-MM-dd")).append("','yyyy-MM-dd')");
        hql.append(" and r.regPipelined.workType=").append(regOrder.getRegPipelined().getWorkType());
        if (ws == null || ws.getDoctorExpert() == null) {
            hql.append(" and r.regPipelined.workSchema.depart.departCodeNo=").append(dp.getDepart().getDepartCodeNo());
        } else {
            hql.append(" and r.regPipelined.workSchema.depart.departCodeNo=").append(ws.getDepart().getDepartCodeNo());
            hql.append(" and r.regPipelined.workSchema.doctorExpert.forWorkNo=").append(ws.getDoctorExpert().getForWorkNo());
        }
        try {
            List list = queryList(hql.toString());
            if (list.size() > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("RegisterDAOImpl.isHaveRegister() 查询异常1：" + e.getMessage(), e);
        }
        return result;
    }


    public int getMinNumOrder(RegOrder regOrder) throws DaoException {
        StringBuffer hql = new StringBuffer();
        hql.append("from RegOrder r where r.regPeople.id=").append(regOrder.getRegPeople().getId());
        hql.append(" and r.state=2");
        hql.append(" order by r.treatOrder ");
        try {
            List<RegOrder> list = queryList(hql.toString());
            if (list.size() > 0) {
                return list.get(0).getTreatOrder();
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("RegisterDAOImpl.getMinNumOrder() 查询异常1：" + e.getMessage(), e);
        }
    }


    public List<WSDoctorListDto> getWsDoctorLists2() throws DaoException {
        List<WSDoctorListDto> wsDoctors = new ArrayList<WSDoctorListDto>();
        String nowDate = getFormatDate(1);
        String maxDate = getFormatDate(7);
        StringBuffer hql = new StringBuffer();
        hql.append("select distinct rp.workSchema.doctorExpert.forWorkNo,rp.workSchema.depart.departCodeNo,rp.workSchema.doctorExpert.name,rp.workSchema.depart.departName from RegPipelined rp, DoctorExpert d where d.forWorkNo=rp.workSchema.doctorExpert.forWorkNo ");
        hql.append(" and rp.workSchema.workDate>=to_date('").append(nowDate).append("','yyyy-MM-dd')");
        hql.append(" and rp.workSchema.workDate<=to_date('").append(maxDate).append("','yyyy-MM-dd')");
        hql.append(" order by rp.workSchema.doctorExpert.name,rp.workSchema.depart.departName ");
        try {
            List<Object[]> list = queryList(hql.toString());
            wsDoctors = getWsDoctors(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("RegisterDAOImpl.getWsDoctorLists() 查询异常1：" + e.getMessage(), e);
        }
        return wsDoctors;
    }

}
