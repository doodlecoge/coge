package com.szwx.yht.zhyl;

import com.szwx.yht.service.ZhylService;
import com.szwx.yht.util.ReadFile;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-6-23
 * Time: 下午4:28
 * To change this template use File | Settings | File Templates.
 */
public class QuitZhyl {
    public static void main(String[] args) {
        String req = ReadFile.read("zhyl/backNo.xml");
        String no = "113910";
        req = String.format(req, no);
        System.out.println(req);
        String resp = ZhylService.getPort().backRegNo(req);
        System.out.println(resp);
    }
}
