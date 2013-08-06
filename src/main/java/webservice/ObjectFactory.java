
package webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SmsMoTfh_QNAME = new QName("http://webService", "tfh");
    private final static QName _SmsMoId_QNAME = new QName("http://webService", "id");
    private final static QName _SmsMoJscontent_QNAME = new QName("http://webService", "jscontent");
    private final static QName _SmsMoClstatus_QNAME = new QName("http://webService", "clstatus");
    private final static QName _SmsMoUserid_QNAME = new QName("http://webService", "userid");
    private final static QName _SmsMoTd_QNAME = new QName("http://webService", "td");
    private final static QName _SmsMoPhone_QNAME = new QName("http://webService", "phone");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetSendMessageTDResponse }
     * 
     */
    public GetSendMessageTDResponse createGetSendMessageTDResponse() {
        return new GetSendMessageTDResponse();
    }

    /**
     * Create an instance of {@link GetSendMessageTD }
     *
     */
    public GetSendMessageTD createGetSendMessageTD() {
        return new GetSendMessageTD();
    }

    /**
     * Create an instance of {@link GetSendMessageUSER4 }
     *
     */
    public GetSendMessageUSER4 createGetSendMessageUSER4() {
        return new GetSendMessageUSER4();
    }

    /**
     * Create an instance of {@link GetSendMessageUSERResponse }
     *
     */
    public GetSendMessageUSERResponse createGetSendMessageUSERResponse() {
        return new GetSendMessageUSERResponse();
    }

    /**
     * Create an instance of {@link GetSendMessageUSER3Response }
     *
     */
    public GetSendMessageUSER3Response createGetSendMessageUSER3Response() {
        return new GetSendMessageUSER3Response();
    }

    /**
     * Create an instance of {@link GetSendAIMA1Response }
     *
     */
    public GetSendAIMA1Response createGetSendAIMA1Response() {
        return new GetSendAIMA1Response();
    }

    /**
     * Create an instance of {@link GetSendMessageUSER2 }
     *
     */
    public GetSendMessageUSER2 createGetSendMessageUSER2() {
        return new GetSendMessageUSER2();
    }

    /**
     * Create an instance of {@link ArrayOfSmsMo }
     *
     */
    public ArrayOfSmsMo createArrayOfSmsMo() {
        return new ArrayOfSmsMo();
    }

    /**
     * Create an instance of {@link GetSendMessageUSER }
     *
     */
    public GetSendMessageUSER createGetSendMessageUSER() {
        return new GetSendMessageUSER();
    }

    /**
     * Create an instance of {@link GetSendAIMA2 }
     *
     */
    public GetSendAIMA2 createGetSendAIMA2() {
        return new GetSendAIMA2();
    }

    /**
     * Create an instance of {@link GetSendAIMA1 }
     *
     */
    public GetSendAIMA1 createGetSendAIMA1() {
        return new GetSendAIMA1();
    }

    /**
     * Create an instance of {@link GetSendMessageUSER4Response }
     *
     */
    public GetSendMessageUSER4Response createGetSendMessageUSER4Response() {
        return new GetSendMessageUSER4Response();
    }

    /**
     * Create an instance of {@link GetSendAIMA2Response }
     *
     */
    public GetSendAIMA2Response createGetSendAIMA2Response() {
        return new GetSendAIMA2Response();
    }

    /**
     * Create an instance of {@link GetSendQXT }
     *
     */
    public GetSendQXT createGetSendQXT() {
        return new GetSendQXT();
    }

    /**
     * Create an instance of {@link Send }
     *
     */
    public Send createSend() {
        return new Send();
    }

    /**
     * Create an instance of {@link GetReceive }
     *
     */
    public GetReceive createGetReceive() {
        return new GetReceive();
    }

    /**
     * Create an instance of {@link SendResponse }
     *
     */
    public SendResponse createSendResponse() {
        return new SendResponse();
    }

    /**
     * Create an instance of {@link SmsMo }
     *
     */
    public SmsMo createSmsMo() {
        return new SmsMo();
    }

    /**
     * Create an instance of {@link GetReceiveResponse }
     *
     */
    public GetReceiveResponse createGetReceiveResponse() {
        return new GetReceiveResponse();
    }

    /**
     * Create an instance of {@link GetSendMessageUSER2Response }
     *
     */
    public GetSendMessageUSER2Response createGetSendMessageUSER2Response() {
        return new GetSendMessageUSER2Response();
    }

    /**
     * Create an instance of {@link GetSendQXTResponse }
     *
     */
    public GetSendQXTResponse createGetSendQXTResponse() {
        return new GetSendQXTResponse();
    }

    /**
     * Create an instance of {@link GetSendMessageUSER3 }
     *
     */
    public GetSendMessageUSER3 createGetSendMessageUSER3() {
        return new GetSendMessageUSER3();
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://webService", name = "tfh", scope = SmsMo.class)
    public JAXBElement<String> createSmsMoTfh(String value) {
        return new JAXBElement<String>(_SmsMoTfh_QNAME, String.class, SmsMo.class, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link Long }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://webService", name = "id", scope = SmsMo.class)
    public JAXBElement<Long> createSmsMoId(Long value) {
        return new JAXBElement<Long>(_SmsMoId_QNAME, Long.class, SmsMo.class, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://webService", name = "jscontent", scope = SmsMo.class)
    public JAXBElement<String> createSmsMoJscontent(String value) {
        return new JAXBElement<String>(_SmsMoJscontent_QNAME, String.class, SmsMo.class, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://webService", name = "clstatus", scope = SmsMo.class)
    public JAXBElement<String> createSmsMoClstatus(String value) {
        return new JAXBElement<String>(_SmsMoClstatus_QNAME, String.class, SmsMo.class, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link Long }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://webService", name = "userid", scope = SmsMo.class)
    public JAXBElement<Long> createSmsMoUserid(Long value) {
        return new JAXBElement<Long>(_SmsMoUserid_QNAME, Long.class, SmsMo.class, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://webService", name = "td", scope = SmsMo.class)
    public JAXBElement<String> createSmsMoTd(String value) {
        return new JAXBElement<String>(_SmsMoTd_QNAME, String.class, SmsMo.class, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService", name = "phone", scope = SmsMo.class)
    public JAXBElement<String> createSmsMoPhone(String value) {
        return new JAXBElement<String>(_SmsMoPhone_QNAME, String.class, SmsMo.class, value);
    }

}
