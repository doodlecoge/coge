package com.szwx.yht.model;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-20
 * Time: 下午6:27
 * To change this template use File | Settings | File Templates.
 */
public class DocSchedule {
    private String DOCNO;
    private String DOCNAME;
    private String DOCRANK;
    private String DPTNAME;
    private String DPTNO;
    Map<String, DocScheduleDetails> schedules = new HashMap<String, DocScheduleDetails>();

    public void addDocScheduleDetails(DocScheduleDetails dsd) {
        String key = dsd.getDatetime() + "," + dsd.getApm();
        schedules.put(key, dsd);
    }


    public String getDOCNO() {
        return DOCNO;
    }

    public void setDOCNO(String DOCNO) {
        this.DOCNO = DOCNO;
    }

    public String getDOCNAME() {
        return DOCNAME;
    }

    public void setDOCNAME(String DOCNAME) {
        this.DOCNAME = DOCNAME;
    }

    public String getDOCRANK() {
        return DOCRANK;
    }

    public void setDOCRANK(String DOCRANK) {
        this.DOCRANK = DOCRANK;
    }

    public String getDPTNAME() {
        return DPTNAME;
    }

    public void setDPTNAME(String DPTNAME) {
        this.DPTNAME = DPTNAME;
    }

    public String getDPTNO() {
        return DPTNO;
    }

    public void setDPTNO(String DPTNO) {
        this.DPTNO = DPTNO;
    }

    public Map<String, DocScheduleDetails> getSchedules() {
        return schedules;
    }

    public void setSchedules(Map<String, DocScheduleDetails> schedules) {
        this.schedules = schedules;
    }
}
