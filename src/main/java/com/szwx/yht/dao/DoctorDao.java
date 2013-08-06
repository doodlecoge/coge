package com.szwx.yht.dao;

import com.szwx.yht.model.Doctor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-15
 * Time: 下午3:43
 * To change this template use File | Settings | File Templates.
 */
public class DoctorDao extends BaseDao {
    public List<Doctor> getDoctorsByHospitalId(String hospitalId) {
        return getHibernateTemplate().find("from Doctor where forWorkNo like ?", hospitalId + "%");
    }
}
