package com.szwx.yht.action;

import com.szwx.yht.exception.HrsExpression;
import com.szwx.yht.bean.DaySchedule;
import com.szwx.yht.service.ZhylRespBind;
import com.szwx.yht.service.entity.DocWork;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-16
 * Time: 下午1:37
 * To change this template use File | Settings | File Templates.
 */
public class GetDocWorkByDpt extends DataAccessAction {
    private String dptCode;
    private List<DaySchedule> workSchedules;
    private List<String> dates;
    private GetDocWorkByDpt gdwbd = this;

    public String exec() {
        List<DocWork> works = ZhylRespBind.findDtWorkByDp(dptCode, "7");
        Map<String, DaySchedule> _schedules = new HashMap<String, DaySchedule>();


        for (DocWork work : works) {
            String date = work.getDate();
            int apm = work.getAmpm();

            if (!_schedules.containsKey(date))
                _schedules.put(date, new DaySchedule());

            DaySchedule ds = _schedules.get(date);

            if (apm == 1) {
                ds.getAmWorks().add(work);
            } else if (apm == 2) {
                ds.getPmWorks().add(work);
            } else {
                throw new HrsExpression("wrong work type: " + apm);
            }
        }

        dates = new ArrayList<String>();

        for (String key : _schedules.keySet()) {
            dates.add(key);
        }

        Collections.sort(dates, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        workSchedules = new ArrayList<DaySchedule>();

        for (String key : dates) {
            workSchedules.add(_schedules.get(key));
        }

        return SUCCESS;
    }


    public String getWeekFormDate(String date) {
        Pattern p = Pattern.compile("^(\\d{4})[-]?(\\d{2})[-]?(\\d{2})$");
        Matcher m = p.matcher(date);

        Calendar cal = Calendar.getInstance();

        if(m.find()) {
            String year = m.group(1);
            String month = m.group(2);
            String day = m.group(3);

            cal.set(Calendar.YEAR, Integer.parseInt(year));
            cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));

            int dow = cal.get(Calendar.DAY_OF_WEEK);

            String weekNames = "日,一,二,三,四,五,六";

            return "星期" + weekNames.split(",")[dow - 1];
        }

        return "";
    }


    public void setDptCode(String dptCode) {
        this.dptCode = dptCode;
    }

    public List<DaySchedule> getWorkSchedules() {
        return workSchedules;
    }

    public List<String> getDates() {
        return dates;
    }

    public GetDocWorkByDpt getGdwbd() {
        return gdwbd;
    }
}
