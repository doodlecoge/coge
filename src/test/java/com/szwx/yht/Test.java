package com.szwx.yht;

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
        List<Integer> lst = new ArrayList<Integer>();

        lst.add(1);
        lst.add(1);
        lst.add(1);
        lst.add(1);
        lst.add(1);


        lst.removeAll(lst);

        System.out.println(lst.size());
    }
}
