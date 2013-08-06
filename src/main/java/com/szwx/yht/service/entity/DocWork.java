package com.szwx.yht.service.entity;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-16
 * Time: 下午1:22
 * To change this template use File | Settings | File Templates.
 */

//    <dtwork>
//    <hosCode>SLBB</hosCode>
//    <dpCode>SLBB29</dpCode>
//    <dpName>妇科专</dpName>
//    <dtCode>SLBBA244</dtCode>
//    <dtName>蒋创</dtName>
//    <workDate>2013-06-17</workDate>
//    <ampm>1</ampm>
//    <totalNum>21</totalNum>
//    <yyNum>21</yyNum>
//    <ghf>1.0</ghf>
//    <mzf>0.0</mzf>
//    <zjf>9.0</zjf>
//    </dtwork>
public class DocWork {
    private String docCode;
    private String docName;
    private String date;
    private int ampm;
    private int total;
    private int yyNum;

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmpm() {
        return ampm;
    }

    public void setAmpm(int ampm) {
        this.ampm = ampm;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getYyNum() {
        return yyNum;
    }

    public void setYyNum(int yyNum) {
        this.yyNum = yyNum;
    }
}
