<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<base href="<%=basePath%>">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>黑名单</title>

		<link href="css/mainstyle.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
		<script type="text/javascript">
			$(function(){
				$("#loadDiv").load("queryRegister_queryPeoples.action",{telPhone:$("#telPhone").val(),registerName:$("#registerName").val(),way:0,'page.pageNum':1,math:<%=new java.util.Date().getTime()%>});
			});
		function loadDiv(){
			$("#loadDiv").load("queryRegister_queryPeoples.action",{telPhone:$("#telPhone").val(),registerName:$("#registerName").val(),way:1,'page.pageNum':1,math:<%=new java.util.Date().getTime()%>});
		}
		</script>
	</head>

	<body>
		<div class="mainContent">
			<div class="position">
				<div class="positonL">
					<span class="padR20">当前位置</span>预约信息查询 &gt;
					<span class="fontRed1 bold">代挂号人员信息</span>
				</div>
				<div class="positonR"></div>
			</div>
			<div class="mainBox">
				<div class="searchBox">
					<table width="600" border="0" cellspacing="8" cellpadding="0">
						<tr>
							<td nowrap="nowrap">
								姓名：
								<input type="text" name="registerName" id="registerName"
									class="input80" />
							</td>
							<td nowrap="nowrap">
								电话：
								<input type="text" name="telPhone" id="telPhone"
									class="input80" />
							</td>
							<td>
								<input type="button" name="button" id="button" value=" "
									class="btnSearch" onclick="loadDiv()" />
							</td>
						</tr>
					</table>
				</div>
				<div id="loadDiv">
				</div>
			</div>
		</div>

	</body>
</html>
