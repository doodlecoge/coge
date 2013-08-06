package com.szwx.yht.dao;

import com.szwx.yht.model.Department;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-15
 * Time: 下午1:56
 * To change this template use File | Settings | File Templates.
 */
public class DepartmentDao extends BaseDao {
    public List<Department> getDepartmentsByHospitalId(String hospitalId) {
        return getHibernateTemplate().find("from Department where hospitalId=?", hospitalId);
    }
}
