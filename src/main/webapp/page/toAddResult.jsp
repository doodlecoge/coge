<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选择挂号病人页面</title>
<link href="css/thickbox.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
<script type="text/javascript">
	function checkTrueNameAndCardNo(){
		var result=$("input[name='resultxxx']:checked").val();
		TB_remove();
		$.ajax({
	          type : "post",
	          dataType: "json",
	          url : "advisory_addForRegister.action",
	          data : "advisory.advisoryDesc=" + result+"&advisory.moudle.moudleCode=020000000000",
	          async : true,
	          success : function(data){
	          				alert(data.msg);
	                    }
	          });
		
	}
	
</script>
</head>
<body><div id="TB_closeAjaxWindow"><div class="floatL">未预约原因登记</div><img src="image/close1.jpg" width="22" height="22" border="0" id='TB_closeWindowButton' onclick='TB_remove();' class="btnClose"/></div>
<dl id="idBox" class="lightbox">
	<div class="dataTable2_set">
	 <div style="padding:15px 15px;">
	 <table width="100%" border="0" cellspacing="6" cellpadding="0">
	   <tr>
	     <td width="9%" align="right" nowrap="nowrap"><span class="fontRed1">*</span>请选择：</td>
	     <td width="91%" nowrap="nowrap">
	       <input type="radio" name="resultxxx" value="号源已满" checked="checked" />&nbsp;号源已满
	       </td>
       </tr>
       <tr>
	     <td width="9%" align="right" nowrap="nowrap"></td>
	     <td width="91%" nowrap="nowrap">
	       <input type="radio" name="resultxxx" value="非挂号时间段" />&nbsp;非挂号时间段
	       </td>
       </tr>
       <tr>
	     <td width="9%" align="right" nowrap="nowrap"></td>
	     <td width="91%" nowrap="nowrap">
	       <input type="radio" name="resultxxx" value="未提供身份证" />&nbsp;未提供身份证
	       </td>
       </tr>
       <tr>
	     <td width="9%" align="right" nowrap="nowrap"></td>
	     <td width="91%" nowrap="nowrap">
	       <input type="radio" name="resultxxx" value="不知道专家姓名或科室" />&nbsp;不知道专家姓名或科室
	       </td>
       </tr>
       <tr>
	     <td width="9%" align="right" nowrap="nowrap"></td>
	     <td width="91%" nowrap="nowrap">
	       <input type="radio" name="resultxxx" value="园区医保不支持" />&nbsp;园区医保不支持
	       </td>
       </tr>
       <tr>
	     <td width="9%" align="right" nowrap="nowrap"></td>
	     <td width="91%" nowrap="nowrap">
	       <input type="radio" name="resultxxx" value="专家未坐诊" />&nbsp;专家未坐诊
	       </td>
       </tr>
       <tr>
	     <td width="9%" align="right" nowrap="nowrap"></td>
	     <td width="91%" nowrap="nowrap">
	       <input type="radio" name="resultxxx" value="其它" />&nbsp;其它
	       </td>
       </tr>
        <tr>
	     <td width="9%" align="right" nowrap="nowrap"><span class="fontRed1">*</span>来电电话：</td>
	     <td width="91%" nowrap="nowrap">
	       	${call_Info_for_result.callInPhone}&nbsp;
	       </td>
       </tr>
	   <tr>
	     <td align="right" nowrap="nowrap">&nbsp;</td>
	     <td nowrap="nowrap"><input name="input" type="button" value="确定" class="btnNormal" onclick="checkTrueNameAndCardNo();" /></td>
       </tr>
      </table>
	 </div>
  </div>
</dl>
</body>
</html>