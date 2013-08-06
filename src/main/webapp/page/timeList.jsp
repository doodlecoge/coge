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
<script type="text/javascript">
$(function() {
	$('tbody>tr').hover(function() {
		$(this).addClass('selected').siblings()
                .removeClass('selected').end()
	});
})
</script>
<div style="">
<%--<div style="border: 1px solid #000; background: #ffffe1; color: #333;">--%>
	<table width="100%" border="0" cellspacing="0" cellpadding="1" class="tableB">
		<thead>
			<tr>
				<th>时段</th>
                <th>预约数</th>
            </tr>
		</thead>
		<tbody>
			<s:iterator value="timeWorkSchemaDtos" var="obj">
				<s:if test="#obj.totalNum==-1">
					<tr>
						<td colspan="2" align="center">
							<font color="red">此排班已停诊</font>
						</td>
					</tr>
				</s:if>
				<s:else>
					<tr
						<s:if test="#obj.num<#obj.totalNum or #obj.totalNum==null">
						onclick="toRegister('${obj.regPipelined.code}','${obj.startTime}','${obj.endTime}')"
                        </s:if>>
						<td>
							${obj.startTime}- ${obj.endTime}
						</td>
					
							<td>
								<s:if test="#obj.num==#obj.totalNum">
									[<font color="red">${obj.num }/${obj.totalNum }</font>]
								</s:if>
								<s:else>
									[${obj.num }/${obj.totalNum }]
								</s:else>
							</td>
<%--						<s:if test="#obj.regPipelined.workSchema.doctorExpert!=null ">--%>
<%--						</s:if>--%>
<%--						<s:else>--%>
<%--							<td>--%>
<%--								[${obj.num }]--%>
<%--							</td>--%>
<%--						</s:else>--%>
					</tr>
				</s:else>

			</s:iterator>
		</tbody>
	</table>
</div>