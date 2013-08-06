package com.szwx.yht;

import com.szwx.yht.service.HrsService;
import com.szwx.yht.util.ReadFile;
import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-15
 * Time: 上午10:19
 * To change this template use File | Settings | File Templates.
 */
public class GetDoctorWorkInfo {
    public static void main(String[] args) throws IOException {
        Calendar cal = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String s1 = df.format(cal.getTime());
        cal.add(Calendar.DAY_OF_YEAR, 6);
        String s2 = df.format(cal.getTime());

        System.out.println(s1);
        System.out.println(s2);

        String req = ReadFile.read("reqs/reqwork.xml");
        req = String.format(req, "SLBB", s1, s2);
        System.out.println(req);
        String str = HrsService.getInstance().getDoctorWorkInfo(req);


        IOUtils.write(str, new FileOutputStream("F:\\tmp\\getDoctorWorkInfoResp.xml"));
    }
}
