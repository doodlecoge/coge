package com.szwx.yht.bind;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-15
 * Time: 上午11:26
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "Message")
public class Message {
    @XmlElement(name = "DOCTORCALENDAR")
    public List<DoctorCalendar> calendars;


}
