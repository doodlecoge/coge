package com.szwx.yht.bind;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-15
 * Time: 上午11:28
 * To change this template use File | Settings | File Templates.
 */
public class DoctorCalendarTest {
    public static void main(String[] args) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Message.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Message message = (Message) jaxbUnmarshaller.unmarshal(new File("F:\\tmp\\getDoctorWorkInfoResp.xml"));


//        for (DoctorCalendar cal : message.calendars) {
//            System.out.println(cal.DoctorId);
//        }
    }
}
