package com.szwx.yht.dao;

import com.szwx.yht.common.CommonDao;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-17
 * Time: 下午4:05
 * To change this template use File | Settings | File Templates.
 */

@Service("hrsDao")
public class HrsDao extends CommonDao {
    public static void getRecordCountBeforePageIndex(int pageIdx) {
        String sql = "select sum(cnt) from ( select d.name, de.for_work_no, dpt.depart_code_no, count(*) cnt from YHT_REG_PIPELINED rp, YHT_DOCTOR_EXPERT de, YHT_DOCTOR_WS ws, YHT_DEPARTS dpt, YHT_DOCTOR d where de.for_work_no = ws.doctor_expert and ws.code = rp.work_schema and to_char(ws.work_date, 'yyyy-MM-dd') > '2013-08-17' and to_char(ws.work_date, 'yyyy-MM-dd') '2013-08-25' and ws.depart = dpt.depart_code_no and dpt.hospital_id = 'SDFY' and d.FOR_WORK_NO = de.FOR_WORK_NO group by d.name, de.for_work_no, dpt.depart_code_no order by d.name ) where rownum = 4";

    }
}
