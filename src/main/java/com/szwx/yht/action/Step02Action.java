package com.szwx.yht.action;

import com.szwx.yht.util.Hospital;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-10
 * Time: 下午1:39
 * To change this template use File | Settings | File Templates.
 */
public class Step02Action extends DataAccessAction {
    private List<Hospital> hospitals;

    public String exec() {
        hospitals = new ArrayList<Hospital>();

        for(Hospital hospital : Hospital.values()) {
            hospitals.add(hospital);
        }

        Collections.sort(hospitals, new Comparator<Hospital>() {
            @Override
            public int compare(Hospital o1, Hospital o2) {
                return o1.name.compareTo(o2.name());
            }
        });

        return SUCCESS;
    }

    public List<Hospital> getHospitals() {
        return hospitals;
    }

    public void setHospitals(List<Hospital> hospitals) {
        this.hospitals = hospitals;
    }
}
