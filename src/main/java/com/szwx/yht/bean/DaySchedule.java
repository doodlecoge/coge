package com.szwx.yht.bean;

import com.szwx.yht.service.entity.DocWork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-16
 * Time: 下午1:57
 * To change this template use File | Settings | File Templates.
 */
public class DaySchedule {
    private List<DocWork> amWorks = new ArrayList<DocWork>();
    private List<DocWork> pmWorks = new ArrayList<DocWork>();

    public List<DocWork> getAmWorks() {
        return amWorks;
    }

    public void setAmWorks(List<DocWork> amWorks) {
        this.amWorks = amWorks;
    }

    public List<DocWork> getPmWorks() {
        return pmWorks;
    }

    public void setPmWorks(List<DocWork> pmWorks) {
        this.pmWorks = pmWorks;
    }
}
