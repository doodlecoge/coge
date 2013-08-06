package com.szwx.yht.service;

import com.szwx.yht.exception.HrsExpression;
import com.szwx.yht.service.entity.Depart;
import com.szwx.yht.service.entity.DocWork;
import com.szwx.yht.util.ReadFile;
import com.szwx.yht.util.XmlHelper;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-16
 * Time: 上午10:46
 * To change this template use File | Settings | File Templates.
 */
public class ZhylRespBind {
    public static List<Depart> findDpList(String hosId, String dptType) {
        String req = ReadFile.read("reqs\\findDpList.xml");
        req = String.format(req, hosId, dptType); //科室类别（1 普通科室 2 专家科室）
        String resp = ZhylService.getPort().findDpList(req);

        SAXReader reader = new SAXReader();
        Document doc = null;
        try {
            doc = reader.read(new StringReader(resp));
        } catch (DocumentException e) {
            throw new HrsExpression(e);
        }

        List list = doc.selectNodes("/Message/dpInfo");

        List<Depart> departs = new ArrayList<Depart>();

        for (Object obj : list) {
            Element el = (Element) obj;
            String code = XmlHelper.getChild(el, "dpCode").getText();
            String name = XmlHelper.getChild(el, "dpName").getText();
            departs.add(new Depart(code, name));
        }

        Collections.sort(departs, new Comparator<Depart>() {
            @Override
            public int compare(Depart o1, Depart o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        return departs;
    }

    public static List<DocWork> findDtWorkByDp(String dptCode, String days) {
        String req = ReadFile.read("reqs\\findDtWorkByDp.xml");
        req = String.format(req, dptCode, days);
        String resp = ZhylService.getPort().findDtWorkByDp(req);

        SAXReader reader = new SAXReader();
        Document doc = null;
        try {
            doc = reader.read(new StringReader(resp));
        } catch (DocumentException e) {
            throw new HrsExpression(e);
        }

        List list = doc.selectNodes("/Message/dtwork");

        List<DocWork> docWorks = new ArrayList<DocWork>();

        for (Object obj : list) {
            Element el = (Element) obj;
            String docCode = XmlHelper.getChild(el, "dtCode").getText();
            String docName = XmlHelper.getChild(el, "dtName").getText();
            String date = XmlHelper.getChild(el, "workDate").getText();
            int ampm = Integer.parseInt(XmlHelper.getChild(el, "ampm").getText());
            int total = Integer.parseInt(XmlHelper.getChild(el, "totalNum").getText());
            int yyNum = Integer.parseInt(XmlHelper.getChild(el, "yyNum").getText());

            DocWork work = new DocWork();
            work.setDocCode(docCode);
            work.setDocName(docName);
            work.setDate(date);
            work.setAmpm(ampm);
            work.setTotal(total);
            work.setYyNum(yyNum);

            docWorks.add(work);
        }

        return docWorks;
    }

}
