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
		<script language="javascript" type="text/javascript"
			src="./My97DatePicker/WdatePicker.js" defer="defer">
</script>
	    <script type="text/javascript">
	    	function player(recordId) {
				var fileName = parent.getCallFileName(recordId);
			
				$("#mp3Name").text(fileName);
			
				$("#mp").get(0).FileName = "http://172.25.130.230:8008/sysrec/" + fileName;
				$("#mp").get(0).play();
			}
			
			function callReturn(noo) {
				var c = window.confirm("回拨号码为：" + noo);
				if (c) {
					if (noo.length != 4) {
						noo = "9" + noo;
					}
					parent.document.getElementById("callOutNo").value = noo;
					parent.Button_Call_onclick();
				}
			}
	    </script>
</head>
<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="tableC">
						<tr>
							<th width="8%">
								日期
							</th>
							<th width="9%">
								电话号码
							</th>
							<th width="8%">
								通话类型
							</th>
							<th width="8%">
								通话开始时间
							</th>
							<th width="8%">
								通话结束时间
							</th>
							<th width="10%">
								通话时长
							</th>
							<th width="10%">
								坐席姓名
							</th>
							<c:if test="${type==0}">
								<th width="10%">
									评价
								</th>
							</c:if>
							<c:if test="${type==1}">
								<th width="10%">
									呼出类型
								</th>
							</c:if>
							<th width="11%">
								听取录音/回拨
							</th>
						</tr>
						<c:if test="${type==0}">
							<c:forEach items="${calls}" var="obj">
								<tr>
									<td>
										<fmt:formatDate value="${obj.callInTime }" />
										&nbsp;
									</td>
									<td>
										${obj.callInPhone }&nbsp;
									</td>
									<td>
										<c:if test="${obj.isConnect==1}">未接</c:if>
										<c:if test="${obj.isConnect==2}">接通</c:if>
										&nbsp;
									</td>
									<td>
										<fmt:formatDate value="${obj.connectTime}" pattern="HH:mm:ss" />
										&nbsp;
									</td>
									<td>
										<fmt:formatDate value="${obj.offTime}" pattern="HH:mm:ss" />
										&nbsp;
									</td>
									<td>
										${obj.spareOne }&nbsp;
									</td>
									<td>
										${obj.opeateESQ.trueName }&nbsp;
									</td>
									<td>
										<c:if test="${obj.approving==0}">未评价</c:if>
										<c:if test="${obj.approving==1}">满意</c:if>
										<c:if test="${obj.approving==2}">一般</c:if>
										<c:if test="${obj.approving==3}">不满意</c:if>
										&nbsp;
									</td>
									<td width="7%">
										<input type="button" value="播放"
											onclick="player('${obj.recordIng}')" />
										<input type="button" value="回拨"
											onclick="callReturn('${obj.callInPhone }')" />
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${type==1}">
							<c:forEach items="${calls}" var="obj">
								<tr>
									<td>
										<fmt:formatDate value="${obj.callTime }" />
										&nbsp;
									</td>
									<td>
										${obj.toPhone }&nbsp;
									</td>
									<td>
										<c:if test="${obj.isConnect==1}">打通</c:if>
										<c:if test="${obj.isConnect==2}">接通</c:if>
										&nbsp;
									</td>
									<td>
										<fmt:formatDate value="${obj.connectTime}" pattern="HH:mm:ss" />
										&nbsp;
									</td>
									<td>
										<fmt:formatDate value="${obj.offTime}" pattern="HH:mm:ss" />
										&nbsp;
									</td>
									<td>
										${obj.spareOne }&nbsp;
									</td>
									<td>
										${obj.outUser.trueName }&nbsp;
									</td>
									<td>
										<c:if test="${obj.outType==1}">回访</c:if>
										<c:if test="${obj.outType==2}">其他</c:if>
										&nbsp;
									</td>
									<td width="7%">
										<input type="button" value="播放" onclick="player('${obj.recordIng}')" />
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<s:if test="calls.size==0">
							<tr>
								<td colspan="9">
									<font color="red">暂无数据</font>
								</td>
							</tr>
						</s:if>
					</table>
				</div>
				<s:if test="calls.size!=0">
					<jsp:include page="page.jsp">
						<jsp:param name="actionURL"
							value="queryRegister_queryCallList.action" />
					</jsp:include>
				</s:if>
