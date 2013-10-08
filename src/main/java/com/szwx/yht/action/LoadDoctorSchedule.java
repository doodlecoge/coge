package com.szwx.yht.action;

import com.szwx.yht.dao.IRegOrderDAO;
import com.szwx.yht.dao.IRegisterDAO;
import com.szwx.yht.dto.WSDoctorListDto;
import com.szwx.yht.exception.ActionException;
import com.szwx.yht.exception.DaoException;
import com.szwx.yht.exception.HrsExpression;
import com.szwx.yht.exception.ServiceException;
import com.szwx.yht.service.IRegisterService;
import com.szwx.yht.util.Page;
import com.szwx.yht.util.TimeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-8-6
 * Time: 下午8:50
 * To change this template use File | Settings | File Templates.
 */

@Controller("load_doc_schedule")
@Scope("request")
public class LoadDoctorSchedule extends DataAccessAction {
    @Autowired
    private IRegisterService registerService;





    private List<WSDoctorListDto> wsDoctors;
    private List<Date> listDate;
    private String docName;
    private String deptName;
    private Page page = new Page();
    private String regType;


    public void setRegType(String regType) {
        this.regType = regType;
    }

    LoadDoctorSchedule() {
        page = new Page();
    }

    @Override
    public String exec() throws Exception {
        session.put("regType", regType);
        String hospitalId = null;

        if (session.containsKey("hospitalId"))
            hospitalId = session.get("hospitalId").toString();
        else throw new HrsExpression("-hid-");

        //GregorianCalendar calendar = new GregorianCalendar();
        Calendar calendar = Calendar.getInstance();
        listDate = new ArrayList<Date>();
        for (int i = 0; i < 7; i++) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            listDate.add(calendar.getTime());
        }

//        wsDoctors = registerService.getWsDoctorListDtosimpl(
//                docName, deptName, page, hospitalId
//        );

//        wsDoctors = registerService.getWsDoctorListDtos(docName,deptName,page,hospitalId);



        wsDoctors = registerService.getWsDoctorListDtosimpl(docName,deptName,page,hospitalId);

        return SUCCESS;
    }





    public IRegisterService getRegisterService() {
        return registerService;
    }

    public void setRegisterService(IRegisterService registerService) {
        this.registerService = registerService;
    }

    public List<WSDoctorListDto> getWsDoctors() {
        return wsDoctors;
    }

    public void setWsDoctors(List<WSDoctorListDto> wsDoctors) {
        this.wsDoctors = wsDoctors;
    }

    public List<Date> getListDate() {
        return listDate;
    }

    public void setListDate(List<Date> listDate) {
        this.listDate = listDate;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
