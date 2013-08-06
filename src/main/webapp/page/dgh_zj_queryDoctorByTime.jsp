<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<script type="text/javascript">
		$(document).ready(function(){
			  	$("#workScheamDiv").load("register_loadWorkScheamDoctor.action",{methodType:1,t:0,'doctor.forWorkNo':'','page.pageNum':1});
			});
		function loadWorkScheam(){
			if($("#doctorid").val()==null||$("#doctorid").val()==""){
				alert("请先选择专家");
			}else{
				$("#workScheamDiv").load("register_loadWorkScheamDoctor.action",{t:1,'methodType':1,'doctor.forWorkNo':$("#doctorid").val(),'&page.pageNum':1});
			}
		}
		
		function loadDepartSchema(){
			$("#registerDiv").load("register_loadQueryDepartByTime.action");
		}
		
		
		function getHospitalByworkDate(){
			$("#hospitalid option[index!=0]").remove();
			$("#departid option[index!=0]").remove();
			$("#doctorid option[index!=0]").remove();
			$.post("register_getHospitalByworkDate.action",
					{"workDate":$("#workDate").val()},
					function(data){
						if(data.success){
							$("#hospitalid option[index!=0]").remove();
							$("#departid option[index!=0]").remove();
							$("#doctorid option[index!=0]").remove();
							if(data.objects==null){
								$("#hospitalid").append("<option value='' disabled='disabled' >暂无排班</option>")
							}else{
								for(i=0;i<data.objects.length;i++){
									$("#hospitalid").append("<option value='"+data.objects[i].hospitalCode+"'>"+data.objects[i].name+"</option>")
								}
							}
						}else{
							alert(data.msg);
						}
					},
					"json")
		}
		function getDepartByworkDateAndHopital(){
			$("#departid option[index!=0]").remove();
			$("#doctorid option[index!=0]").remove();
			$.post("register_getDepartByworkDateAndHopital.action",
					{"workDate":$("#workDate").val(),"hospital.hospitalCode":$("#hospitalid").val()},
					function(data){
						if(data.success){
								$("#departid option[index!=0]").remove();
								$("#doctorid option[index!=0]").remove();
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
		
		function getDoctorByworkDateAndDepart(){
			$("#doctorid option[index!=0]").remove();
			$.post("register_getDoctorByworkDateAndDepart.action",
					{"workDate":$("#workDate").val(),"depart.departCodeNo":$("#departid").val()},
					function(data){
						if(data.success){
							$("#doctorid option[index!=0]").remove();
							if(data.objects==null){
								$("#doctorid").append("<option value='' disabled='disabled' >暂无排班</option>")
							}else{
								for(i=0;i<data.objects.length;i++){
									$("#doctorid").append("<option value='"+data.objects[i].forWorkNo+"'>"+data.objects[i].name+"</option>")
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
		<td width="88%" nowrap="nowrap">
			预约挂号：
			<input name="radio1" type="radio" id="radio" checked="checked" />
			<label for="radio1">
				专家
				<input name="radio1" type="radio" id="radio2"
					onclick="loadDepartSchema()"
					value="radio" />
				实时
			</label>
		</td>
		<td width="12%" nowrap="nowrap">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td nowrap="nowrap">
			<div>
				预约设置：
			</div>
			<label for="select"></label>
			<select name="select" size="10" id="workDate" onchange="getHospitalByworkDate()">
				<option disabled="disabled">
						—请选择日期—
					</option>
				<c:forEach items="${listDate}" var="obj">
				<option value="<fmt:formatDate value="${obj}" />">
					<fmt:formatDate value="${obj}" />
				</option>
				</c:forEach>
			</select>
			<select name="select2" size="10" id="hospitalid" onchange="getDepartByworkDateAndHopital()">
				<option disabled="disabled">
					—请选择医院—
				</option>
			</select>
			<select name="select3" size="10" id="departid" onchange="getDoctorByworkDateAndDepart()">
				<option disabled="disabled">
					—请选择科室—
				</option>
			</select>
			<select name="select4" size="10" id="doctorid" >
				<option value="" disabled="disabled">
					—可预约排班医生—
				</option>
			</select>
		</td>
		<td nowrap="nowrap">
			<input type="button" name="button" id="button" value=" "
				class="btnSearch" onclick="loadWorkScheam()"/>
		</td>
	</tr>
</table>

