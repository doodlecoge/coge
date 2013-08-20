package com.szwx.yht.dao;

import com.szwx.yht.common.CommonDao;
import com.szwx.yht.model.DocWS;
import org.apache.tools.ant.util.DateUtils;
import org.hibernate.classic.Session;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-17
 * Time: 下午4:05
 * To change this template use File | Settings | File Templates.
 */

@Service("hrsDao")
public class HrsDao extends CommonDao {
    public int getRecordCountBeforePageIndex(String hospital, int pageIdx) {
        Calendar now = Calendar.getInstance();
        String startDateString = DateUtils.format(now.getTime(), "yyyy-MM-dd");
        now.add(Calendar.DAY_OF_YEAR, 7);
        String endDateString = DateUtils.format(now.getTime(), "yyyy-MM-dd");


        String sql = "" +
                "select sum(cnt)" +
                "from (" +
                "  select d.name, de.for_work_no, dpt.depart_code_no, count(*) cnt" +
                "  from" +
                "      YHT_REG_PIPELINED rp," +
                "      YHT_DOCTOR_EXPERT de," +
                "      YHT_DOCTOR_WS ws," +
                "      YHT_DEPARTS dpt," +
                "      YHT_DOCTOR d" +
                "  where" +
                "    de.for_work_no = ws.doctor_expert" +
                "    and ws.code = rp.work_schema" +
                "    and to_char(ws.work_date, 'yyyy-MM-dd') > '" + startDateString + "'" +
                "    and to_char(ws.work_date, 'yyyy-MM-dd') < '" + endDateString + "'" +
                "    and ws.depart = dpt.depart_code_no" +
                "    and dpt.hospital_id = 'SDFY'" +
                "    and d.FOR_WORK_NO = de.FOR_WORK_NO" +
                "  group by d.name, de.for_work_no, dpt.depart_code_no" +
                "  order by d.name" +
                ")" +
                "where rownum < " + (pageIdx * 15);


        Session session = null;

        int num = 0;
        try {
            session = getHibernateTemplate().getSessionFactory().openSession();
            Object o = session.createSQLQuery(sql).uniqueResult();
            num = Integer.parseInt(o.toString());
        } catch (Exception e) {
            e.printStackTrace();
            num = -1;
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return num;
    }


    public List<DocWS> getDoctorWS(String hos, int pageIndex, int pageNum) {
        int startIdx = pageIndex * pageNum;
        int endIdx = startIdx + pageNum;

        Calendar now = Calendar.getInstance();
        String startDateString = DateUtils.format(now.getTime(), "yyyy-MM-dd");
        now.add(Calendar.DAY_OF_YEAR, 7);
        String endDateString = DateUtils.format(now.getTime(), "yyyy-MM-dd");

        String sql = "" +
                "select a.regCode, b.worktype, b.docNo, b.docName, b.docRank, b.dptName, a.dptNo, a.dateTime from " +
                "    (" +
                "    select rp.code regCode, ws.depart dptNo, ws.doctor_expert docNo, ws.WORK_DATE dateTime" +
                "    from" +
                "        YHT_REG_PIPELINED rp," +
                "        YHT_DOCTOR_WS ws" +
                "    where " +
                "        ws.code = rp.code" +
                "        and to_char(ws.work_date, 'yyyy-MM-dd') > '2013-08-17'" +
                "        and to_char(ws.work_date, 'yyyy-MM-dd') < '2013-08-25'" +
                "    ) A ,(" +
                "    select * " +
                "    from " +
                "        (" +
                "        select " +
                "          rp.work_type worktype, dpt.depart_code_no dptNo, d.for_work_no docNo, d.name docName, dpt.DEPART_NAME dptName, d.DOCTOR_RANK docRank, rownum rn" +
                "        from" +
                "            YHT_REG_PIPELINED rp," +
                "            YHT_DOCTOR_EXPERT de," +
                "            YHT_DOCTOR_WS ws," +
                "            YHT_DEPARTS dpt," +
                "            YHT_DOCTOR d" +
                "        where" +
                "            de.for_work_no = ws.doctor_expert" +
                "            and ws.code = rp.work_schema" +
                "            and to_char(ws.work_date, 'yyyy-MM-dd') > '2013-08-17'" +
                "            and to_char(ws.work_date, 'yyyy-MM-dd') < '2013-08-25'" +
                "            and ws.depart = dpt.depart_code_no" +
                "            and dpt.hospital_id = 'SDFY'" +
                "            and d.FOR_WORK_NO = de.FOR_WORK_NO" +
                "        order by d.name, de.for_work_no, dpt.depart_code_no" +
                "        ) " +
                "    where rn > 0 and rn < 4" +
                "    )  B " +
                "where a.docNo = b.docNo and a.dptNo = b.dptNo";


        Session session = null;

        try {
            session = getHibernateTemplate().getSessionFactory().openSession();
            return session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(DocWS.class)).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return null;
    }
}
