
package szsx.yhtwebservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "yhtRegPortType", targetNamespace = "http://yhtWebService.szsx")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface YhtRegPortType {


    /**
     * 
     * @param in0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
    @RequestWrapper(localName = "getDpWorkTime", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.GetDpWorkTime")
    @ResponseWrapper(localName = "getDpWorkTimeResponse", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.GetDpWorkTimeResponse")
    public String getDpWorkTime(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

    /**
     * 
     * @param in0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
    @RequestWrapper(localName = "findDtWork", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.FindDtWork")
    @ResponseWrapper(localName = "findDtWorkResponse", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.FindDtWorkResponse")
    public String findDtWork(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

    /**
     * 
     * @param in0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
    @RequestWrapper(localName = "yyList", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.YyList")
    @ResponseWrapper(localName = "yyListResponse", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.YyListResponse")
    public String yyList(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

    /**
     * 
     * @param in0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
    @RequestWrapper(localName = "backRegNo", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.BackRegNo")
    @ResponseWrapper(localName = "backRegNoResponse", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.BackRegNoResponse")
    public String backRegNo(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

    /**
     * 
     * @param in0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
    @RequestWrapper(localName = "findDpWork", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.FindDpWork")
    @ResponseWrapper(localName = "findDpWorkResponse", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.FindDpWorkResponse")
    public String findDpWork(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

    /**
     * 
     * @param in0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
    @RequestWrapper(localName = "findDtWorkByDp", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.FindDtWorkByDp")
    @ResponseWrapper(localName = "findDtWorkByDpResponse", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.FindDtWorkByDpResponse")
    public String findDtWorkByDp(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

    /**
     * 
     * @param in0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
    @RequestWrapper(localName = "jcHmdInfo", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.JcHmdInfo")
    @ResponseWrapper(localName = "jcHmdInfoResponse", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.JcHmdInfoResponse")
    public String jcHmdInfo(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

    /**
     * 
     * @param in0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
    @RequestWrapper(localName = "getWorkTime", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.GetWorkTime")
    @ResponseWrapper(localName = "getWorkTimeResponse", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.GetWorkTimeResponse")
    public String getWorkTime(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

    /**
     * 
     * @param in0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
    @RequestWrapper(localName = "findCardByCheckNo", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.FindCardByCheckNo")
    @ResponseWrapper(localName = "findCardByCheckNoResponse", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.FindCardByCheckNoResponse")
    public String findCardByCheckNo(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

    /**
     * 
     * @param in0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
    @RequestWrapper(localName = "findDtList", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.FindDtList")
    @ResponseWrapper(localName = "findDtListResponse", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.FindDtListResponse")
    public String findDtList(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

    /**
     * 
     * @param in0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
    @RequestWrapper(localName = "updateHisPaytype", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.UpdateHisPaytype")
    @ResponseWrapper(localName = "updateHisPaytypeResponse", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.UpdateHisPaytypeResponse")
    public String updateHisPaytype(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

    /**
     * 
     * @param in0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
    @RequestWrapper(localName = "findDpList", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.FindDpList")
    @ResponseWrapper(localName = "findDpListResponse", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.FindDpListResponse")
    public String findDpList(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

    /**
     * 
     * @param in0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
    @RequestWrapper(localName = "findHmdInfo", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.FindHmdInfo")
    @ResponseWrapper(localName = "findHmdInfoResponse", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.FindHmdInfoResponse")
    public String findHmdInfo(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

    /**
     * 
     * @param in0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
    @RequestWrapper(localName = "findBackNoCs", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.FindBackNoCs")
    @ResponseWrapper(localName = "findBackNoCsResponse", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.FindBackNoCsResponse")
    public String findBackNoCs(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

    /**
     * 
     * @param in0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
    @RequestWrapper(localName = "yylTj", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.YylTj")
    @ResponseWrapper(localName = "yylTjResponse", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.YylTjResponse")
    public String yylTj(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

    /**
     * 
     * @param in0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
    @RequestWrapper(localName = "uploadRegInfo", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.UploadRegInfo")
    @ResponseWrapper(localName = "uploadRegInfoResponse", targetNamespace = "http://yhtWebService.szsx", className = "szsx.yhtwebservice.UploadRegInfoResponse")
    public String uploadRegInfo(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

}
