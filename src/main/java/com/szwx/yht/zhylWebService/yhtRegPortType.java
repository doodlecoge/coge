
package com.szwx.yht.zhylWebService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "yhtRegPortType", targetNamespace = "http://yhtWebService.szsx")
@SOAPBinding(use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface yhtRegPortType {

	@WebMethod(operationName = "yyList", action = "")
	@WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
	public String yyList(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

	@WebMethod(operationName = "findCardByCheckNo", action = "")
	@WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
	public String findCardByCheckNo(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

	@WebMethod(operationName = "updateHisPaytype", action = "")
	@WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
	public String updateHisPaytype(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

	@WebMethod(operationName = "findDtWorkByDp", action = "")
	@WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
	public String findDtWorkByDp(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

	@WebMethod(operationName = "findDtList", action = "")
	@WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
	public String findDtList(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

	@WebMethod(operationName = "uploadRegInfo", action = "")
	@WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
	public String uploadRegInfo(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

	@WebMethod(operationName = "yylTj", action = "")
	@WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
	public String yylTj(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

	@WebMethod(operationName = "getWorkTime", action = "")
	@WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
	public String getWorkTime(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

	@WebMethod(operationName = "backRegNo", action = "")
	@WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
	public String backRegNo(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

	@WebMethod(operationName = "findDpWork", action = "")
	@WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
	public String findDpWork(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

	@WebMethod(operationName = "findDpList", action = "")
	@WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
	public String findDpList(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

	@WebMethod(operationName = "getDpWorkTime", action = "")
	@WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
	public String getDpWorkTime(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

	@WebMethod(operationName = "findDtWork", action = "")
	@WebResult(name = "out", targetNamespace = "http://yhtWebService.szsx")
	public String findDtWork(
            @WebParam(name = "in0", targetNamespace = "http://yhtWebService.szsx")
            String in0);

}
