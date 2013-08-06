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
	function checkReason(){
		var reason=$.trim($("#reason").val());
		if(reason.length>0){
			check(reason);
		}else{
			$("#errorMsg").html("不能为空");
		}
	}
	function check(reason){
				TB_remove();
			  var fun=iFrame3.window.checkReason;
				fun(reason,'${regPeople.id}');
			}
</script>
</head>
<body><div id="TB_closeAjaxWindow"><div class="floatL">解除黑名单原因录入</div><img src="image/close1.jpg" width="22" height="22" border="0" id='TB_closeWindowButton' onclick='TB_remove();' class="btnClose"/></div>
<dl id="idBox" class="lightbox">
	<div class="dataTable2_set">
	 <div style="padding:15px 15px;">
	
	 <table width="100%" border="0" cellspacing="6" cellpadding="0">
	 	<tr>
	     <td width="9%" align="right" nowrap="nowrap">姓名：</td>
	     <td width="91%" nowrap="nowrap">
	     	${regPeople.trueName }
	     </td>
       </tr>
       <tr>
	     <td width="9%" align="right" nowrap="nowrap">爽约次数：</td>
	     <td width="91%" nowrap="nowrap">
	     	<font color="red">${regPeople.breakPromiseCount}次</font>
	     </td>
       </tr>
	   <tr>
	     <td width="9%" align="right" nowrap="nowrap"><span class="fontRed1">*</span>原因：</td>
	     <td width="91%" nowrap="nowrap">
	     	<textarea  style="width: 450px;height: 150px;"  id="reason"></textarea>
		</td>
       </tr>
        <tr>
	     <td width="9%" align="right" nowrap="nowrap">&nbsp;</td>
	     <td width="91%" nowrap="nowrap">
	     	<label id="errorMsg" style="color: red"></label>
		</td>
       </tr>
	   <tr>
	     <td  nowrap="nowrap">&nbsp;</td>
	     <td nowrap="nowrap" align="center"><input name="input" type="button" value="确定" class="btnNormal" onclick="checkReason();" /></td>
       </tr>
      </table>
	 </div>
  </div>
</dl>
</body>
</html>