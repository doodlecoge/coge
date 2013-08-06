<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false"%>
<script type="text/javascript">
		$(document).ready(function(){
			  	$("#workScheamDiv").load("register_loadWorkScheamDepart.action",{t:0,methodType:0,"hospital.hospitalCode":$("#hospitalId").val(),deptName:$("#departName").val(),"page.pageNum":1});
			});
		function loadWorkScheam(){
			$("#registerDiv").load("register_loadQueryDoctor.action?t=1&methodType=0");
		}
		
		function loadDepartSchema(){
			$("#workScheamDiv").load("register_loadWorkScheamDepart.action",{t:0,methodType:0,"hospital.hospitalCode":$("#hospitalId").val(),deptName:$("#departName").val(),"page.pageNum":1});
		}
</script>

<table width="600" border="0" cellspacing="8" cellpadding="0">
	<tr>
		<td width="31%" nowrap="nowrap">
			预约挂号：
			<input name="radio1" type="radio" id="radio" value="radio"
				onclick="loadWorkScheam()" />
			<label for="radio1">
				专家
				<input name="radio1" type="radio" id="radio2" value="radio"
					checked="checked" />
				实时
			</label>
		</td>
		<td width="26%" nowrap="nowrap">
			挂号时间：
			<fmt:formatDate value="${today}" pattern="yyyy-MM-dd EEE" />
		</td>
		<td width="43%" nowrap="nowrap">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td nowrap="nowrap">
			医院名称：
			<label for="select"></label>
			<select name="select" id="hospitalId">
				<option value="">
					请选择
				</option>
				<c:forEach items="${hospitals}" var="obj">
					<option value="${obj.hospitalCode }">
						${obj.name }
					</option>
				</c:forEach>
			</select>
		</td>
		<td nowrap="nowrap">
			科室名称：
			<input type="text" name="departName" id="departName" class="input130" />
		</td>
		<td nowrap="nowrap">
			<input type="button" name="button" id="button" value=" "
				class="btnSearch" onclick="loadDepartSchema()" />
		</td>
	</tr>
</table>