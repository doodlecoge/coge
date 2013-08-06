package com.szwx.yht.bind;

import com.szwx.yht.util.ReadFile;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-15
 * Time: 下午12:03
 * To change this template use File | Settings | File Templates.
 */
public class GetFileContentTest {
    public static void main(String[] args) throws IOException {
//        InputStream is = GetFileContentTest.class.getClassLoader().getResourceAsStream("getDoctorWorkInfoReq.xml");
//        InputStream is = GetFileContentTest.class.getResourceAsStream("/getDoctorWorkInfoReq.xml");
//        String str = IOUtils.toString(is);
//        System.out.println(str);

        String sss = ReadFile.read("reqs/reqwork.xml");
        System.out.println(sss);
    }
}
