
package com.szwx.yht.smsWebService;

import webservice.ArrayOfSmsMo;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "notePortType", targetNamespace = "http://webService")
@SOAPBinding(use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface notePortType {

	@WebMethod(operationName = "send", action = "")
	@WebResult(name = "out", targetNamespace = "http://webService")
	public String send(
            @WebParam(name = "in0", targetNamespace = "http://webService")
            String in0,
            @WebParam(name = "in1", targetNamespace = "http://webService")
            String in1,
            @WebParam(name = "in2", targetNamespace = "http://webService")
            String in2,
            @WebParam(name = "in3", targetNamespace = "http://webService")
            String in3,
            @WebParam(name = "in4", targetNamespace = "http://webService")
            int in4,
            @WebParam(name = "in5", targetNamespace = "http://webService")
            String in5,
            @WebParam(name = "in6", targetNamespace = "http://webService")
            String in6);

	@WebMethod(operationName = "getReceive", action = "")
	@WebResult(name = "out", targetNamespace = "http://webService")
	public ArrayOfSmsMo getReceive(
            @WebParam(name = "in0", targetNamespace = "http://webService")
            String in0,
            @WebParam(name = "in1", targetNamespace = "http://webService")
            String in1,
            @WebParam(name = "in2", targetNamespace = "http://webService")
            String in2,
            @WebParam(name = "in3", targetNamespace = "http://webService")
            String in3,
            @WebParam(name = "in4", targetNamespace = "http://webService")
            String in4);

	@WebMethod(operationName = "getSendMessageUSER", action = "")
	@WebResult(name = "out", targetNamespace = "http://webService")
	public boolean getSendMessageUSER(
            @WebParam(name = "in0", targetNamespace = "http://webService")
            String in0,
            @WebParam(name = "in1", targetNamespace = "http://webService")
            String in1,
            @WebParam(name = "in2", targetNamespace = "http://webService")
            String in2,
            @WebParam(name = "in3", targetNamespace = "http://webService")
            String in3,
            @WebParam(name = "in4", targetNamespace = "http://webService")
            int in4,
            @WebParam(name = "in5", targetNamespace = "http://webService")
            String in5,
            @WebParam(name = "in6", targetNamespace = "http://webService")
            String in6);

	@WebMethod(operationName = "getSendAIMA1", action = "")
	@WebResult(name = "out", targetNamespace = "http://webService")
	public boolean getSendAIMA1(
            @WebParam(name = "in0", targetNamespace = "http://webService")
            String in0,
            @WebParam(name = "in1", targetNamespace = "http://webService")
            String in1,
            @WebParam(name = "in2", targetNamespace = "http://webService")
            String in2,
            @WebParam(name = "in3", targetNamespace = "http://webService")
            String in3,
            @WebParam(name = "in4", targetNamespace = "http://webService")
            String in4,
            @WebParam(name = "in5", targetNamespace = "http://webService")
            int in5,
            @WebParam(name = "in6", targetNamespace = "http://webService")
            String in6);

	@WebMethod(operationName = "getSendMessageUSER4", action = "")
	@WebResult(name = "out", targetNamespace = "http://webService")
	public boolean getSendMessageUSER4(
            @WebParam(name = "in0", targetNamespace = "http://webService")
            String in0,
            @WebParam(name = "in1", targetNamespace = "http://webService")
            String in1,
            @WebParam(name = "in2", targetNamespace = "http://webService")
            String in2,
            @WebParam(name = "in3", targetNamespace = "http://webService")
            int in3,
            @WebParam(name = "in4", targetNamespace = "http://webService")
            String in4,
            @WebParam(name = "in5", targetNamespace = "http://webService")
            String in5);

	@WebMethod(operationName = "getSendMessageTD", action = "")
	@WebResult(name = "out", targetNamespace = "http://webService")
	public boolean getSendMessageTD(
            @WebParam(name = "in0", targetNamespace = "http://webService")
            String in0,
            @WebParam(name = "in1", targetNamespace = "http://webService")
            String in1,
            @WebParam(name = "in2", targetNamespace = "http://webService")
            String in2,
            @WebParam(name = "in3", targetNamespace = "http://webService")
            String in3,
            @WebParam(name = "in4", targetNamespace = "http://webService")
            String in4,
            @WebParam(name = "in5", targetNamespace = "http://webService")
            int in5,
            @WebParam(name = "in6", targetNamespace = "http://webService")
            String in6,
            @WebParam(name = "in7", targetNamespace = "http://webService")
            String in7);

	@WebMethod(operationName = "getSendMessageUSER2", action = "")
	@WebResult(name = "out", targetNamespace = "http://webService")
	public boolean getSendMessageUSER2(
            @WebParam(name = "in0", targetNamespace = "http://webService")
            String in0,
            @WebParam(name = "in1", targetNamespace = "http://webService")
            String in1,
            @WebParam(name = "in2", targetNamespace = "http://webService")
            String in2,
            @WebParam(name = "in3", targetNamespace = "http://webService")
            String in3,
            @WebParam(name = "in4", targetNamespace = "http://webService")
            int in4,
            @WebParam(name = "in5", targetNamespace = "http://webService")
            String in5,
            @WebParam(name = "in6", targetNamespace = "http://webService")
            String in6);

	@WebMethod(operationName = "getSendQXT", action = "")
	@WebResult(name = "out", targetNamespace = "http://webService")
	public boolean getSendQXT(
            @WebParam(name = "in0", targetNamespace = "http://webService")
            String in0,
            @WebParam(name = "in1", targetNamespace = "http://webService")
            String in1,
            @WebParam(name = "in2", targetNamespace = "http://webService")
            String in2,
            @WebParam(name = "in3", targetNamespace = "http://webService")
            String in3,
            @WebParam(name = "in4", targetNamespace = "http://webService")
            String in4);

	@WebMethod(operationName = "getSendMessageUSER3", action = "")
	@WebResult(name = "out", targetNamespace = "http://webService")
	public boolean getSendMessageUSER3(
            @WebParam(name = "in0", targetNamespace = "http://webService")
            String in0,
            @WebParam(name = "in1", targetNamespace = "http://webService")
            String in1,
            @WebParam(name = "in2", targetNamespace = "http://webService")
            String in2,
            @WebParam(name = "in3", targetNamespace = "http://webService")
            int in3,
            @WebParam(name = "in4", targetNamespace = "http://webService")
            String in4,
            @WebParam(name = "in5", targetNamespace = "http://webService")
            String in5);

	@WebMethod(operationName = "getSendAIMA2", action = "")
	@WebResult(name = "out", targetNamespace = "http://webService")
	public boolean getSendAIMA2(
            @WebParam(name = "in0", targetNamespace = "http://webService")
            String in0,
            @WebParam(name = "in1", targetNamespace = "http://webService")
            String in1,
            @WebParam(name = "in2", targetNamespace = "http://webService")
            String in2,
            @WebParam(name = "in3", targetNamespace = "http://webService")
            String in3,
            @WebParam(name = "in4", targetNamespace = "http://webService")
            String in4,
            @WebParam(name = "in5", targetNamespace = "http://webService")
            int in5,
            @WebParam(name = "in6", targetNamespace = "http://webService")
            String in6);

}
