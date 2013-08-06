package com.szwx.yht.service;

import com.szwx.yht.exception.HrsExpression;
import com.szwx.yht.util.ReadFile;
import org.apache.commons.lang3.time.DateFormatUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.Calendar;
import java.util.List;

import com.szwx.yht.bind.*;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-15
 * Time: 上午11:55
 * To change this template use File | Settings | File Templates.
 */
public class GetDoctorWorkSchedule {
    private static GetDoctorWorkSchedule ins;
    static {
        ins = new GetDoctorWorkSchedule();
    }
    private GetDoctorWorkSchedule(){}

    public static GetDoctorWorkSchedule getIns() {
        return ins;
    }
    public List<DoctorCalendar> getWorkSchedule(String hospitalId) {
        String respXml = getDoctorWorkInfo(hospitalId);

        try {
            return getSchedule(respXml);
        } catch (JAXBException e) {
            throw new HrsExpression(e);
        }
    }

    private List<DoctorCalendar> getSchedule(String respXml) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Message.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Message message = (Message) jaxbUnmarshaller.unmarshal(
                new StringReader(respXml));

        return message.calendars;
    }

    // map: docId + dptId => List of work
    private String getDoctorWorkInfo(String hospitalId) {
        Calendar calStart = Calendar.getInstance();
        calStart.add(Calendar.DAY_OF_YEAR, 1);
        Calendar calEnd = Calendar.getInstance();
        calEnd.add(Calendar.DAY_OF_YEAR, 7);


        String ss = DateFormatUtils.format(calStart, "yyyy-MM-dd");
        String se = DateFormatUtils.format(calEnd, "yyyy-MM-dd");

        String req = ReadFile.read("reqs/reqwork.xml");
        req = String.format(req, hospitalId, ss, se);
        return HrsService.getInstance().getDoctorWorkInfo(req);
    }
}
