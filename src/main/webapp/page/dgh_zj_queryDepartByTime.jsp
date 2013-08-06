<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false"%>
<script type="text/javascript">
		$(document).ready(function(){
			  	$("#workScheamDiv").load("register_loadWorkScheamDepart.action?methodType=1&t=0&depart.departCodeNo=&page.pageNum=1&tt=<%=new java.util.Date().getTime()%>");
			});
		function loadWorkScheam(){
			$("#registerDiv").load("register_loadQueryDoctorBYTime.action?t=1&methodType=1");
		}
		
		function loadDepartSchema(){
			if($("#departid").val()==null||$("#departid").val()==""){
				alert("请先选择科室");
			}else{
				$("#workScheamDiv").load("register_loadWorkScheamDepart.action?t=1&methodType=1&depart.departCodeNo="+$("#departid").val()+"&page.pageNum=1");
			}
		}
		
		function getDepartByworkDateAndHopital(){
			$("#departid option[index!=0]").remove();
			$.post("register_getDepartByworkDateAndHopitalFromTime.action",
					{"hospital.hospitalCode":$("#hospitalid").val()},
					function(data){
						if(data.success){
						$("#departid option[index!=0]").remove();
						if(data.objects==null){
								$("#departid").append("<option value='' disabled='disabled' >暂无排班</option>")
						}else{
							for(i=0;i<data.objects.length;i++){
								$("#departid").append("<option value='"+data.objects[i].departCodeNo+"'>"+data.objects[i].departName+"</option>")
							}
						}
					}else{
						alert(data.msg);
					}
				},
				"json")
		}
</script>

<table width="600" border="0" cellspacing="8" cellpadding="0">
	<tr>
		<td width="49%" nowrap="nowrap">
			预约挂号：
			<input name="radio1" type="radio" id="radio"
				onclick="loadWorkScheam()" />
			<label for="radio1">
				专家
				<input name="radio1" type="radio" id="radio2" value="radio"
					checked="checked" />
				实时
			</label>
		</td>
		<td width="51%" nowrap="nowrap">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td nowrap="nowrap">
			挂号时间：
			<fmt:formatDate value="${today}" pattern="yyyy-MM-dd EEE" />
		</td>
		<td nowrap="nowrap">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td nowrap="nowrap">
			<div>
				预约设置：
			</div>
			<label for="select"></label>
			<select name="select2" size="10" id="hospitalid" onchange="getDepartByworkDateAndHopital()">
			<option disabled="disabled">
					—请选择医院—
				</option>
				<c:forEach items="${hospitals}" var="obj">
					<option value="${obj.hospitalCode}">
						${obj.name }
					</option>
				</c:forEach>
			</select>
			<select name="select3" size="10" id="departid">
				<option value="" disabled="disabled">
					—请选择科室—
				</option>
				
			</select>
		</td>
		<td nowrap="nowrap">
			<input type="button" name="button" id="button" value=" "
				class="btnSearch" onclick="loadDepartSchema()"/>
		</td>
	</tr>
</table>

