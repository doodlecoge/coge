package com.szwx.yht.action;

import com.szwx.yht.dao.HrsDao;
import com.szwx.yht.dto.WSDoctorListDto;
import com.szwx.yht.exception.ActionException;
import com.szwx.yht.exception.HrsExpression;
import com.szwx.yht.exception.ServiceException;
import com.szwx.yht.model.DocSchedule;
import com.szwx.yht.model.DocScheduleDetails;
import com.szwx.yht.model.DocWS;
import com.szwx.yht.service.IRegisterService;
import com.szwx.yht.util.Page;
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
public class LoadDoctorSchedule extends DataAccessAction {
    @Autowired
    private IRegisterService registerService;

    @Autowired
    private HrsDao hrsDao;

//    private List<WSDoctorListDto> wsDoctors;
    private List<Date> listDate;
    private String docName;
    private String deptName;
    private Page page = new Page();
    private List<DocSchedule> docSchedules;

    LoadDoctorSchedule() {
        page = new Page();
    }

    @Override
    public String exec() throws Exception {
        String hospitalId = null;

        if (session.containsKey("hospitalId"))
            hospitalId = session.get("hospitalId").toString();
        else throw new HrsExpression("-hid-");

        int num = hrsDao.getRecordCountBeforePageIndex(hospitalId, page.getPageNum());
        List<DocWS> doctorWS = hrsDao.getDoctorWS(hospitalId, page.getPageNum(), page.getPageSize());

        Map<String, DocSchedule> docSchedulesMap = new HashMap<String, DocSchedule>();

        for (DocWS docWS : doctorWS) {
            String key = docWS.getDOCNAME() + docWS.getDPTNAME();
            DocScheduleDetails docScheduleDetails = new DocScheduleDetails();
            docScheduleDetails.setApm(docWS.getWORKTYPE());
            docScheduleDetails.setDatetime(docWS.getDATETIME());
            docScheduleDetails.setRegCode(docWS.getREGCODE());
            // todo: set it properly
            docScheduleDetails.setState(0);

            if(docSchedulesMap.containsKey(key)) {
                docSchedulesMap.get(key).addDocScheduleDetails(docScheduleDetails);
            } else {
                DocSchedule ds = new DocSchedule();

                ds.setDOCNAME(docWS.getDOCNAME());
                ds.setDOCNO(docWS.getDOCNO());
                ds.setDOCRANK(docWS.getDOCRANK());
                ds.setDPTNAME(docWS.getDPTNAME());
                ds.setDPTNO(docWS.getDPTNO());

                ds.addDocScheduleDetails(docScheduleDetails);
                docSchedulesMap.put(key, ds);
            }
        }

        List<String> keyList = getSortedKeyList(docSchedulesMap);

        docSchedules = new ArrayList<DocSchedule>();
        for (String key : keyList) {
            DocSchedule docSchedule = docSchedulesMap.get(key);
            docSchedules.add(docSchedule);
        }


        GregorianCalendar calendar = new GregorianCalendar();
        listDate = new ArrayList<Date>();
        for (int i = 0; i < 7; i++) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            listDate.add(calendar.getTime());
        }


        return SUCCESS;
    }

    private List<String> getSortedKeyList(Map<String, DocSchedule> docSchedulesMap) {
        Set<String> keySet = docSchedulesMap.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        List<String> keyList = Arrays.asList(keyArray);

        Collections.sort(keyList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        return keyList;
    }


    public IRegisterService getRegisterService() {
        return registerService;
    }

    public void setRegisterService(IRegisterService registerService) {
        this.registerService = registerService;
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

    public List<DocSchedule> getDocSchedules() {
        return docSchedules;
    }

    public void setDocSchedules(List<DocSchedule> docSchedules) {
        this.docSchedules = docSchedules;
    }
}
