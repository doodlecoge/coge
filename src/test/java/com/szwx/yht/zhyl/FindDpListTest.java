package com.szwx.yht.zhyl;

import com.szwx.yht.service.ZhylService;
import com.szwx.yht.util.ReadFile;
import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-16
 * Time: 上午10:10
 * To change this template use File | Settings | File Templates.
 */
public class FindDpListTest {
    public static void main(String[] args) throws IOException {
//        String req = ReadFile.read("reqs\\findDpList.xml");
//        req = String.format(req, "SLBB", 1);
//        System.out.println(req);
//        String resp = ZhylService.getPort().findDpList(req);
//
//        IOUtils.write(resp, new FileOutputStream(Res.tmpDir + "findDpListResp.xml"));
//        System.out.println(resp);

//        List<DocWork> lst = ZhylRespBind.findDtWorkByDp("SLBB29", "7");
//
//        for(DocWork work:lst) {
//            System.out.println(work.getDocName() + "," + work.getDate() + "," + work.getAmpm());
//        }


//        String req = ReadFile.read("reqs\\findDpList.xml");
//        req = String.format(req, "SDFY", "2"); //科室类别（1 普通科室 2 专家科室）
//        String resp = ZhylService.getPort().findDpList(req);

        String req = ReadFile.read("reqs\\findDtWorkByDp.xml");
        req = String.format(req, "SDFY1100206", "7");
        String resp = ZhylService.getPort().findDtWorkByDp(req);

        IOUtils.write(resp, new FileOutputStream("F:\\tmp\\findDtWorkByDp.xml"));
    }
}
