<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<title>电话历史记录统计</title>
		<link href="css/mainstyle.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery-1.4.2.js">
</script>
		<%--<script language="javascript" type="text/javascript"--%>
			<%--src="./My97DatePicker/WdatePicker.js" defer="defer">--%>
<%--</script>--%>

		<script type="text/javascript">
			function loadDiv(){
				$("#loadDiv").load("queryRegister_queryCallList.action",{beginTime:$("#beginTime").val(),endTime:$("#endTime").val(),type:$("#type").val(),userName:$("#userName").val(),telPhone:$("#telPhone").val(),'page.pageNum':1,math:<%=new java.util.Date().getTime()%>});
			}
		</script>

	</head>

	<body>
		<div class="mainContent">
			<div class="position">
				<div class="positonL">
					<span class="padR20">当前位置</span>综合统计查询 &gt; 通话统计 &gt;
					<span class="fontRed1 bold">电话历史</span>
				</div>
				<div class="positonR"></div>
			</div>
			<div class="mainBox">
				<div class="searchBox">
						<table width="600" border="0" cellspacing="8" cellpadding="0">
							<tr>
								<td width="31%" nowrap="nowrap">
									日期：
									<input type="text" name="beginTime" id="beginTime"
										class="input120"
										value="<fmt:formatDate value='${beginTime}'/>"
										onfocus="WdatePicker();" readonly="readonly" />
									到
									<input type="text" name="endTime" id="endTime" class="input120"
										value="<fmt:formatDate value='${endTime}'/>"
										onfocus="WdatePicker();" readonly="readonly" />
								</td>
								<td width="35%" nowrap="nowrap">
									通话类型：
									<select name="type" id="type">
										<option value="0"
											<c:if test="${type==0}"> selected="selected" </c:if>>
											接听
										</option>
										<option value="1"
											<c:if test="${type==1}"> selected="selected" </c:if>>
											呼出
										</option>
									</select>
									&nbsp;
								</td>
								<td width="43%">
									<input type="hidden" name="page.pageNum" id="page.pageNum"
										value="1" />
									<input type="button" name="button3" id="button3" value=" "
										class="btnSearch" onclick="loadDiv()"/>
								</td>
								<td>
									<OBJECT id="mp" WIDTH="192" HEIGHT="45"
										CLASSID="CLSID:22D6F312-B0F6-11D0-94AB-0080C74C7E95"
										STANDBY="Loading Windows Media Player components..."
										TYPE="application/x-oleobject">

										<PARAM NAME="FileName" VALUE="">

										<PARAM name="autostart" VALUE="false">

										<PARAM name="ShowControls" VALUE="true">

										<param name="ShowStatusBar" value="false">

										<PARAM name="ShowDisplay" VALUE="false">

										<EMBED TYPE="application/x-mplayer2" ShowControls="1"
											ShowStatusBar="0" ShowDisplay="0" autostart="0">
										</EMBED>

									</OBJECT>
								</td>
								<td id="mp3Name"></td>
							</tr>
							<tr>
								<td nowrap="nowrap">
									坐席:
									<input name="userName" id="userName" value="${userName}" />
									电话:
									<input name="telPhone" id="telPhone" value="${telPhone }" />
								</td>
								<td>
									&nbsp;
								</td>
								<td>
									&nbsp;
								</td>
								<td>
									&nbsp;
								</td>
								<td>
									&nbsp;
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