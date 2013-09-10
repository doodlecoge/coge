package com.szwx.yht;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-7-2
 * Time: 下午10:25
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String[] args) {
        String str = StringUtils.escape("您在12320网上预约挂号的手机验证码为{code}，如果不是您本人请忽略此消息。");
        System.out.println(str);

        List<String> lst = new ArrayList<String>();

        lst.add("a");
        lst.add("b");
        lst.add("c");

        int len = lst.size();
        for (int i = 0; i < len; i++) {

            System.out.println(lst.get(i));
            lst.remove(i);
        }
    }
}
