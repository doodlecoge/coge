<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
		<title>专家挂号</title>

		<link href="css/mainstyle.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
		<script type="text/javascript">
			function li1Onclick(){
				$("#registerDiv").load("register_loadQueryDoctor.action?t=1&methodType=0");
				$("#li2").removeClass();
				$("#li1").addClass("current");
			}
			function li2Onclick(){
				$("#registerDiv").load("register_loadQueryDoctorBYTime.action?t=1&methodType=1");
				$("#li1").removeClass();
				$("#li2").addClass("current");
				
			}
		</script>
		<script type="text/javascript">
		$(document).ready(function(){
			  	$("#registerDiv").load("register_loadQueryDoctor.action?tt=<%=new java.util.Date().getTime()%>");
			  	getTrueName();
			});
			
			
		function getTrueName(){
		var cardId='${logPeople.id}';
		var cardNo='${logPeople.identityCard}';
		if(cardId==0&&cardNo!=""){
			$("#trueName").css('color','gray').val('正在查询……');
			$.ajax({
	          type : "post",
	          dataType: "json",
	          url : "register_getTrueNameBynoid.action",
	          data : "regPeople.identityCard=" + cardNo,
	          async : true,
	          success : function(data){
	          				if(data.success){
	          					$("#trueName").val(data.msg).css('color','black');;
	          				}else{
	          					$("#trueName").val(data.msg).css('color','red');
	          				}
	                    }
	          });
		}
		
	}
		function checkName(name,sex,cardNo,id,mobile,medicalType,medicalNo,friendType,breakPromiseCount){
			$("#trueName").val(name);
			$("#cardNo").val(cardNo);
			if(cardNo.length==15){
				if(cardNo.substring(14,15)%2==0){
					sex=2;
				}else{
					sex=1;
				}
			}else if(cardNo.length==18){
				if(cardNo.substring(16,17)%2==0){
					sex=2;
				}else{
					sex=1;
				}
			}
			$("input[name=radio3]").each(function(i,each){
				if(this.value==sex){
					this.checked=true;
				}
			});
			//if(mobile==""||mobile==null){
			//	mobile="${session.call_Info.callInPhone }";
			//}
			$("#mobile").val(mobile);
			$("select[name=medicalType] option").each(function(i,each){
				if(this.value==medicalType){
					this.selected=true;
				}
			});
			$("#medicalNo").val(medicalNo);
			$("#regPeopleId").val(id);
			$("#breakPromiseCount").val(breakPromiseCount);
			if(breakPromiseCount>0&&breakPromiseCount<3){
				$("#textFont").html("已有"+breakPromiseCount+"次爽约记录。");
			}else if(breakPromiseCount>2){
				$("#textFont").html("已有"+breakPromiseCount+"次爽约记录,已被计入黑名单。");
			}else{
				$("#textFont").html("");
			}
						
		}
		function toRegister(rpCode,startTime,endTime){//&&checkMedical()
			if(checkTrueName()&&checkCardNo()&&checkMobileTel()&&checkBreakPromiseCount()){
			var str="regPipelined.code="+rpCode;
			if($.trim($("#regPeopleId").val())!=""){
				str+="&regPeople.id="+$("#regPeopleId").val();
			}else{
				str+="&regPeople.id=-1";
			}
			str+="&regPeople.identityCard="+$("#cardNo").val();
			str+="&regPeople.medicalNo="+$("#medicalNo").val();
			str+="&regPeople.medicalType="+$("#medicalType").val();
			str+="&regPeople.trueName="+$("#trueName").val();
			str+="&regPeople.sex="+$("input[name='radio3']:checked").val(); //$(":radio:checked") $("input[name='radio3']:checked").val()
			str+="&regPeople.mobile="+$.trim($("#mobile").val());
			str+="&endTime="+endTime;
			str+="&startTime="+startTime;
				$.ajax({
		          type : "post",
		          dataType: "json",
		          url : "register_updateRegister.action",
		          data : str,
		          async : false,
		          success : function(data){
          				if(data.success){
          					location='register_toSaveRegister.action';
          				}else{
          					alert(data.msg);
          				}
		                    },
		           error: function(){
		           	alert("系统异常")
		           }         
		          });
				
			}
		}
		function checkBreakPromiseCount(){
			if($("#breakPromiseCount").val()>2){
				$("#textFont").html("已有"+$("#breakPromiseCount").val()+"次爽约记录。您已被列入黑名单，不能进行预约挂号。");
				return false ;
			}else{
				return true;
			}
		}
		function checkTrueName(){
			if($.trim($("#trueName").val())==""){
				$("#trueName").css("border","solid 2px red").attr("title","请选择就诊人姓名");
				return false;
			}else{
				return true;
			}
		}
		function checkCardNo(){
			if($.trim($("#cardNo").val())==""){
				$("#cardNo").css("border","solid 2px red").attr("title","请选择身份证号码");
				return false;
			}else{
				return true;
			}
		}
		function checkMedical(){
			if($("#medicalType").val()==""){
				alert("请选择就诊类型");
				return false;
			}else if($("#medicalType").val()==0){
				$("#medicalNo").val("");
				return true;
			}else{
				if($.trim($("#medicalNo").val())==""){
					$("#medicalNo").css("border","solid 2px red").attr("title","请填写医保编号");
					return false;
				}else{
					if($.trim($("#medicalNo").val()).length!=10){
						$("#medicalNo").css("border","solid 2px red").attr("title","医保编号为10位");
						return false;
					}else{
						$("#medicalNo").css("border","solid 2px green").attr("title","");
						return true;
					}
				}
			}
		}
		function checkMobileTel(){
				var mobileTel=$.trim($("#mobile").val());
				if(mobileTel!=""){
					$("#mobile").css("border","solid 2px green").attr("title","电话不为空")//style="border-color: red;border: thin 4px" 
					return true;
				}else{
					$("#mobile").css("border","solid 2px red").attr("title","电话不能为空")
					$("#mobile").focus();
					return false;
				}
			}
		function checkMobile(id){
				mobileTel=$.trim($("#"+id).val());
				if(regExp.test(mobileTel)){
					$("#"+id).css("border","solid 2px green").attr("title","手机格式正确")//style="border-color: red;border: thin 4px" 
					return true;
				}else{
					$("#"+id).css("border","solid 2px red").attr("title","手机格式不正确")
					$("#"+id).focus();
					isok=false;
				}
			}
</script>
	</head>

	<body >
		<div class="mainContent">
			<div class="position">
				<div class="positonL">
					<span class="padR20">当前位置</span>12320代挂号 >
					<span class="fontRed1 bold">按专家或科室挂号</span>
				</div>
				<div class="positonR"></div>
			</div>
			<div class="mainBox">
				<div class="tab_bg">
					<ul>
						<li class="current" id="li1" onclick="li1Onclick()" >
							按专家或科室挂号
						</li>
						<li class="line">
							|
						</li>
						<li  id="li2" onclick="li2Onclick()">
							按时间或医院挂号
						</li>
					</ul>
				</div>
				<div class="searchBox">
					<table width="600" border="0" cellspacing="8" cellpadding="0">
						<tr>
							<td nowrap="nowrap" class="bold">
								挂号人信息
							</td>
							<td nowrap="nowrap">
								&nbsp;
							</td>
							<td nowrap="nowrap">
								&nbsp;
							</td>
							<td>
								&nbsp;
							</td>
						</tr>
						<!---->
						<tr>
							<td nowrap="nowrap">
								<input type="button" name="button5"
									onclick="javascript: window.parent.TB_show('','register_toAddFriend.action?height=360&width=550&time=<%=new java.util.Date().getTime() %>') "
									value="新增" class="btnNormal" />
							</td>
							<td nowrap="nowrap">
								&nbsp;
								<font id="textFont" color="red">
								<c:if test="${! empty logPeople and logPeople.breakPromiseCount>0 and logPeople.breakPromiseCount<3}">
									已有${logPeople.breakPromiseCount}次爽约记录
								</c:if>
								<c:if test="${! empty logPeople and logPeople.breakPromiseCount>2}">
									已有${logPeople.breakPromiseCount}次爽约记录,已被计入黑名单。
								</c:if>
								</font>
								<input type="hidden" id="breakPromiseCount" value="${logPeople.breakPromiseCount}" />
							</td>
							<td nowrap="nowrap">
								&nbsp;
							</td>
							<td nowrap="nowrap" align="right">
								<input type="button" name="button5"
									onclick="javascript: window.parent.TB_show('','register_toAddResult.action?height=360&width=550&time=<%=new java.util.Date().getTime() %>') "
									value="原因登记" class="btnNormal5" />
							</td>
						</tr>  
						<tr>
							<td width="31%" nowrap="nowrap">
								姓名： <input type="text" name="trueName"  id="trueName" value="${logPeople.trueName }"  />
								<input type="hidden" name="regPeopleId" id="regPeopleId" value="${logPeople.id }"/>
							</td>
							<td width="26%" nowrap="nowrap">
								性&nbsp;&nbsp;&nbsp;&nbsp;别：
								<input type="radio" name="radio3" id="sex" value="1" <c:if test="${logPeople.sex!=2}"> checked="checked"</c:if> />
								男
								<input type="radio" name="radio3" id="sex1" value="2" <c:if test="${logPeople.sex==2}"> checked="checked"</c:if> />
								女
							</td>
							<td width="43%" nowrap="nowrap">
								身份证号： <input type="text" name="cardNo"  id="cardNo"  value="${logPeople.identityCard}"  readonly="readonly"  />
							</td>
							<td width="43%">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap">
								电话：
								<c:if test="${!empty session.call_Info.callInPhone }">
									<input type="text" name="mobile" id="mobile"
									class="input130" value="${session.call_Info.callInPhone }"/>
								</c:if>
								<c:if test="${empty session.call_Info.callInPhone }">
								<input type="text" name="mobile" id="mobile"
									class="input130" value="${logPeople.mobile }"/>
									</c:if>
							</td>
							<td nowrap="nowrap">
								就诊类型：
								<select name="medicalType" id="medicalType">
									<option value="0">
										请选择
									</option>
									<option value="0" <c:if test="${logPeople.medicalType==0}">selected="selected"</c:if>>
										自费
									</option>
									<option value="1" <c:if test="${logPeople.medicalType==1}">selected="selected"</c:if>>
										市区医保
									</option>
									<option value="2" <c:if test="${logPeople.medicalType==2}">selected="selected"</c:if>>
										园区医保
									</option>
									
								</select>
							</td>
							<td nowrap="nowrap">
								市民卡号/医保编号：
								<input type="text" name="medicalNo" id="medicalNo"
									class="input130"  value="${logPeople.medicalNo }"/>
							</td>
						</tr>
					</table>
					<div class="lineGrey"></div>
					<div id="registerDiv">

					</div>

				</div>
				<div id="workScheamDiv"></div>
				<div style="height: 100px"></div>
			</div>
		</div>
	</body>
</html>