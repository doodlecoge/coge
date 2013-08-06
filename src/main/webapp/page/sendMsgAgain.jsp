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
	<title>短信重发页面</title>
	<link href="css/thickbox.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
	<script type="text/javascript">
		function setPhoneNo() {
			if (confirm("重发预约短信?")) {
				var setphoneNo = $("#phoneNo").val();
				var setCode = "<%=request.getParameter("postcode")%>";
				TB_remove();
				$.ajax({
					type : "post",
					dataType: "json",
					url : "queryRegister_sendMsgAgain.action",
					data: {"regOrder.code":setCode,"setToMobile":setphoneNo},
					async : true,
					success : function(data){
						alert(data.msg);
					},
					error: function(){
					alert("系统异常")
					}
				});
			}
		}
	</script>
</head>
<body>
	<div id="TB_closeAjaxWindow">
		<div class="floatL">短信重发</div>
		<img src="image/close1.jpg" width="22" height="22" border="0" id='TB_closeWindowButton' onclick='TB_remove();' class="btnClose"/>
	</div>
	<dl id="idBox" class="lightbox">
		<div class="dataTable2_set">
			<div style="padding:15px 15px;">
				<table width="100%" height = "20" border="0" cellspacing="6" cellpadding="0">
					<tr>
						<td width="9%" align="right" nowrap="nowrap"><span class="fontRed1">* </span>手机号码：</td>
						<td width="91%" nowrap="nowrap">
							<label for="select"></label>
							<input type="text" name="textfield" id="phoneNo" class="input250" 
									value="<%=request.getParameter("postmobile")%>" onFocus="this.select()"/>
							<input name="input" type="button" title="重新发送短信" value="发送" class="btnNormal"  onclick="setPhoneNo()" />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</dl>
</body>
</html>