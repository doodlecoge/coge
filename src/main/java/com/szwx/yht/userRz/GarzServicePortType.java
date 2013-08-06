
package com.szwx.yht.userRz;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "GarzServicePortType", targetNamespace = "http://webservice.smsf.com")
@SOAPBinding(use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface GarzServicePortType {

	@WebMethod(operationName = "getNameByIdno", action = "")
	@WebResult(name = "out", targetNamespace = "http://webservice.smsf.com")
	public String getNameByIdno(
            @WebParam(name = "in0", targetNamespace = "http://webservice.smsf.com")
            String in0);

	@WebMethod(operationName = "rzByIdnoAndName", action = "")
	@WebResult(name = "out", targetNamespace = "http://webservice.smsf.com")
	public String rzByIdnoAndName(
            @WebParam(name = "in0", targetNamespace = "http://webservice.smsf.com")
            String in0);

	@WebMethod(operationName = "regUsers", action = "")
	@WebResult(name = "out", targetNamespace = "http://webservice.smsf.com")
	public String regUsers(
            @WebParam(name = "in0", targetNamespace = "http://webservice.smsf.com")
            String in0);

}
