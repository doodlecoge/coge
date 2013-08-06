package com.szwx.yht;

import com.szwx.yht.service.HrsService;
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
 * Time: 上午11:11
 * To change this template use File | Settings | File Templates.
 */
public class GetDepartWorkInfo {
    public static void main(String[] args) throws IOException {
        Calendar cal = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String s1 = df.format(cal.getTime());
        cal.add(Calendar.DAY_OF_YEAR, 6);
        String s2 = df.format(cal.getTime());

        System.out.println(s1);
        System.out.println(s2);

        String req = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Message><requestInfo><HospitalID>SLBB</HospitalID><beginTime>%s</beginTime><endTime>%s</endTime></requestInfo></Message>";
        req = String.format(req, s1, s2);
        System.out.println(req);
        String str = HrsService.getInstance().getDepartWorkInfo(req);
        IOUtils.write(str, new FileOutputStream("F:\\tmp\\getDepartWorkInfoResp.xml"));
    }
}
