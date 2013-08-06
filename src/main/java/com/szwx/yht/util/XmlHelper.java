package com.szwx.yht.util;

import org.dom4j.Element;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-16
 * Time: 上午11:05
 * To change this template use File | Settings | File Templates.
 */
public class XmlHelper {
    public static List getChildren(Element el) {
        return el.selectNodes("./*");
    }

    public static List getChildren(Element el, String nodeName) {
        return el.selectNodes("./" + nodeName);
    }

    public static Element getChild(Element el, String nodeName) {
        List lst = el.selectNodes("./" + nodeName);
        if(lst == null || lst.size() == 0) return null;
        return (Element) lst.get(0);
    }
}
