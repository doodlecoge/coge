package com.szwx.yht.action;

//import com.szwx.yht.dao.UserDao;

import com.szwx.yht.common.CommonDao;
import com.szwx.yht.dao.DepartmentDao;
import com.szwx.yht.dao.DoctorDao;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: huaiwang
 * Date: 13-1-23
 * Time: 下午4:27
 * To change this template use File | Settings | File Templates.
 */
public class DataAccessAction extends ApplicationAction {
    protected DepartmentDao departmentDao;
    protected DoctorDao doctorDao;
    protected CommonDao commonDao;

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public void setDoctorDao(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    public CommonDao getCommonDao() {
        return commonDao;
    }

    public void setCommonDao(CommonDao commonDao) {
        this.commonDao = commonDao;
    }

    @Override
    public String exec() throws Exception {
        return SUCCESS;
    }
}
