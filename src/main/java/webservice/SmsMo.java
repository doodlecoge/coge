
package webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for SmsMo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SmsMo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clstatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="jscontent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="jstime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="phone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="td" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tfh" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SmsMo", propOrder = {
    "clstatus",
    "id",
    "jscontent",
    "jstime",
    "phone",
    "td",
    "tfh",
    "userid"
})
public class SmsMo {

    @XmlElementRef(name = "clstatus", namespace = "http://webService", type = JAXBElement.class)
    protected JAXBElement<String> clstatus;
    @XmlElementRef(name = "id", namespace = "http://webService", type = JAXBElement.class)
    protected JAXBElement<Long> id;
    @XmlElementRef(name = "jscontent", namespace = "http://webService", type = JAXBElement.class)
    protected JAXBElement<String> jscontent;
    protected XMLGregorianCalendar jstime;
    @XmlElementRef(name = "phone", namespace = "http://webService", type = JAXBElement.class)
    protected JAXBElement<String> phone;
    @XmlElementRef(name = "td", namespace = "http://webService", type = JAXBElement.class)
    protected JAXBElement<String> td;
    @XmlElementRef(name = "tfh", namespace = "http://webService", type = JAXBElement.class)
    protected JAXBElement<String> tfh;
    @XmlElementRef(name = "userid", namespace = "http://webService", type = JAXBElement.class)
    protected JAXBElement<Long> userid;

    /**
     * Gets the value of the clstatus property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getClstatus() {
        return clstatus;
    }

    /**
     * Sets the value of the clstatus property.
     *
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setClstatus(JAXBElement<String> value) {
        this.clstatus = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the id property.
     *
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link Long }{@code >}
     *
     */
    public JAXBElement<Long> getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link Long }{@code >}
     *
     */
    public void setId(JAXBElement<Long> value) {
        this.id = ((JAXBElement<Long> ) value);
    }

    /**
     * Gets the value of the jscontent property.
     *
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getJscontent() {
        return jscontent;
    }

    /**
     * Sets the value of the jscontent property.
     *
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setJscontent(JAXBElement<String> value) {
        this.jscontent = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the jstime property.
     *
     * @return
     *     possible object is
     *     {@link javax.xml.datatype.XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getJstime() {
        return jstime;
    }

    /**
     * Sets the value of the jstime property.
     *
     * @param value
     *     allowed object is
     *     {@link javax.xml.datatype.XMLGregorianCalendar }
     *
     */
    public void setJstime(XMLGregorianCalendar value) {
        this.jstime = value;
    }

    /**
     * Gets the value of the phone property.
     *
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     *
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setPhone(JAXBElement<String> value) {
        this.phone = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the td property.
     *
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getTd() {
        return td;
    }

    /**
     * Sets the value of the td property.
     *
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setTd(JAXBElement<String> value) {
        this.td = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the tfh property.
     *
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getTfh() {
        return tfh;
    }

    /**
     * Sets the value of the tfh property.
     *
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setTfh(JAXBElement<String> value) {
        this.tfh = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the userid property.
     *
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link Long }{@code >}
     *
     */
    public JAXBElement<Long> getUserid() {
        return userid;
    }

    /**
     * Sets the value of the userid property.
     *
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setUserid(JAXBElement<Long> value) {
        this.userid = ((JAXBElement<Long> ) value);
    }

}
