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
<head>
		<base href="<%=basePath%>">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>专家挂号</title>

		<link href="css/mainstyle.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
		<%--<script type="text/javascript"--%>
	            <%--src="My97DatePicker/WdatePicker.js"></script>--%>
	    <script type="text/javascript">
	    	function updateBlack(id){
	    	
	    	if(window.confirm("是否解除此用户黑名单？", "标题")){
	    		$.post("queryRegister_quitBlack.action",
					{
						"regPeople.id":id
					},
					function(data)
					{
						if(!data.success){
							alert(data.msg);
						}else{
							alert(data.msg);
							location='queryRegister_toQueryBlackPeople.action?tt=<%= new java.util.Date().getTime()%>';
						}
					},
					"json"
				)
	    	}
	            		
	            	}
	    </script>
</head>
<div>
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		class="tableC">
		<tr>
			<th width="20%">
				姓名
			</th>
			<th width="25%">
				证件号码
			</th>
			<th width="25%">
				电话
			</th>
			<th >
				状态
			</th>
		</tr>
		<s:if test="peoples!=null">
		<s:iterator value="peoples" var="obj" >
		<tr>
			<td>
				${obj.trueName }
			</td>
			<td>
				${obj.identityCard }	
			</td>
			<td>
				${obj.mobile }
			</td>
			<td nowrap="nowrap">
				<s:if test="#obj.breakPromiseCount>=3">
					<input type="button" value="解除黑名单" style="border-bottom: #adb9c2 1px solid; border-left: #adb9c2 1px solid; width: 100px; background: url(image/btn_normal_repeat.jpg) repeat-x 0px 0px; height: 23px; border-top: #adb9c2 1px solid; cursor: pointer; border-right: #adb9c2 1px solid; color: red;"
					onclick="updateBlack('${obj.id }')">
				</s:if>
				<s:else>正常</s:else>
			</td>
		</tr>
		</s:iterator>
		</s:if>
		
		<s:if test="peoples.size==0">
		<tr>
			<td colspan="4">
				<font color="red">暂无数据</font>
			</td>
		</tr>
		</s:if>
	</table>
</div>
<s:if test="peoples.size!=0">
	<jsp:include page="page.jsp">
		<jsp:param name="actionURL"
			value="queryRegister_queryBlackPeople.action" />
	</jsp:include>
</s:if>
