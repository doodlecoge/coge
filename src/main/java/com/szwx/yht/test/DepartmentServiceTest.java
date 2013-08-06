package com.szwx.yht.test;

import com.szwx.yht.service.HrsService;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-10
 * Time: 下午2:57
 * To change this template use File | Settings | File Templates.
 */
public class DepartmentServiceTest {
    public static void main(String[] args) throws IOException {
        String departReqXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Message><requestInfo><HospitalID>SLBB</HospitalID></requestInfo></Message>";
        String hospitalReqXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Message><requestInfo><HospitalID>SLBB</HospitalID></requestInfo></Message>";
        String docReqXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Message><requestInfo><HospitalID>SLBB</HospitalID></requestInfo></Message>";



//        System.out.println(service.getDepartInfo(departReqXml));
//        System.out.println(service.getHospitalInfo(hospitalReqXml));
//        System.out.println(service.getDoctorInfo(docReqXml));

        String sss = HrsService.getInstance().getDoctorInfo(docReqXml);


        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("E:\\docInfo.xml"));
        bufferedWriter.write(sss);
        bufferedWriter.flush();
        bufferedWriter.close();

    }

}
