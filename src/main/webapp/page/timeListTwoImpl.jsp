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
<base href="<%=basePath%>">
<link href="css/mainstyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
<script type="text/javascript">
$(function() {
	$('tbody>tr').hover(function() {
		$(this).addClass('selected').siblings().removeClass('selected').end()
	});
})


</script>
 <table width="100%" border="0" cellspacing="0" cellpadding="1"
		class="tableB">
		<thead>
			<tr>
				<th>
					时段
				</th>
				<th>
					预约数
				</th>
			</tr>
		</thead>
		<tbody>
				<c:choose>
				<c:when test="${timeWorkSchemaDto.totalNum==-1}">
					<tr>
						<td colspan="2" align="center">
							<font color="red">此排班已停诊</font>
						</td>
					</tr>
				</c:when>
					<c:otherwise>
						<tr <c:if test="${timeWorkSchemaDto.num<timeWorkSchemaDto.totalNum  }">onclick="toRegister('${timeWorkSchemaDto.regPipelined.code}','${timeWorkSchemaDto.startTime}','${timeWorkSchemaDto.endTime}')"</c:if>>
							<td>
								${timeWorkSchemaDto.startTime}- ${timeWorkSchemaDto.endTime}
							</td>
							<td>
							<c:choose>
								<c:when test="${timeWorkSchemaDto.num==timeWorkSchemaDto.totalNum}">
									[<font color="red">${timeWorkSchemaDto.num }/${timeWorkSchemaDto.totalNum }</font>]
								</c:when>
								<c:otherwise>
									[${timeWorkSchemaDto.num }/${timeWorkSchemaDto.totalNum }]
								</c:otherwise>
							</c:choose>
							</td>
						</tr>
					</c:otherwise>
				</c:choose>
		</tbody>
	</table>