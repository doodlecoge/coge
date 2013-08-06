<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">
		$(document).ready(function(){
			  	$("#workScheamDiv").load("register_loadWorkScheamDoctor.action",{methodType:0,t:0,docName:'',deptName:'','page.pageNum':1});
			});
		function loadWorkScheam(){
			$("#workScheamDiv").load("register_loadWorkScheamDoctor.action",{t:0,methodType:0,docName:$("#docotrName").val(),deptName:$("#departName").val(),hospitalId:$("#hospitalId").val(),"page.pageNum":1});
		}
		
		function loadDepartSchema(){
			$("#registerDiv").load("register_loadQueryDepart.action?ttt=<%=new java.util.Date().getTime()%>");
		}
</script>

<table width="600" border="0" cellspacing="8" cellpadding="0">
        <tr>
          <td nowrap="nowrap" class="bold">预约挂号</td>
          <td nowrap="nowrap">&nbsp;</td>
          <td nowrap="nowrap">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
  <tr>
    <td width="31%" nowrap="nowrap">挂号类型：
      <input name="radio1" type="radio" id="radio"  checked="checked" />
      <label for="radio1">专家
        <input name="radio1" type="radio" id="radio2" onclick="loadDepartSchema()" value="radio" />
        实时        </label></td>
         <td nowrap="nowrap">
			医院名称：
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
    <td width="26%" nowrap="nowrap">专家姓名：
      <input type="text" name="textfield2" id="docotrName" class="input80" /></td>
    <td width="43%" nowrap="nowrap">科室名称：
      <input type="text" name="textfield" id="departName" class="input130" /></td>
    <td width="43%"><input type="button" name="button" id="button" value=" " class="btnSearch" onclick="loadWorkScheam()"/></td>
  </tr>
</table>