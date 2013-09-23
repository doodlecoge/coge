package com.szwx.yht.util;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-10
 * Time: 下午1:30
 * To change this template use File | Settings | File Templates.
 */
public enum Hospital {
    SLBB("SLBB", "苏州市立医院本部"),
    SLDQ("SLDQ", "苏州市立医院东区"),
    SLBQ("SLBQ", "苏州市立医院北区"),
    SDFY("SDFY", "苏州大学附属第一医院"),
    SDFE("SDFE", "苏州大学附属第二医院"),
    SZET("SZET", "苏州大学附属儿童医院"),
    SZKQ("SZKQ", "苏州大学附属口腔医院"),
    ZYYY("ZYYY", "苏州中医医院"),
    JLYY("JLYY", "苏州九龙医院"),
    SZGJ("SZGJ", "苏州广济医院"),
    SZWY("SZWY", "苏州第五人民医院"),
    WZYY("WZYY", "苏州吴中人民医院")
    ;

    public String code;
    public String name;

    Hospital(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(String code) {
        return Hospital.valueOf(code).name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
