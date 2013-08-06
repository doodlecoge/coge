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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选择挂号病人页面</title>
<link href="css/thickbox.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
<script type="text/javascript">
	function checkTrueNameAndCardNo(){
		var name=$("#trueName").val();
		var cardNo=$("#cardNo").val();
		var regExp=/^[\u4e00-\u9fa5]{2,10}$/;
		if(regExp.test(name)){
			$("#trueName").css("border","solid 2px green").attr("title","姓名格式正确")//style="border-color: red;border: thin 4px" 
		}else{
			$("#trueName").css("border","solid 2px red").attr("title","姓名为2-10位中文")
			$("#trueName").focus();
			return false;
		}
		if(!isIdCardNo("cardNo")){
			return false;
		};
		
		$.ajax({
	          type : "post",
	          dataType: "json",
	          url : "register_getPeopleByCard.action",
	          data : "regPeople.identityCard=" + cardNo,
	          async : false,
	          success : function(data){
	          				if(data.success){
	          					 check(name,data.object.sex,data.object.identityCard,data.object.id,
	          					 data.object.mobile,data.object.medicalType,data.object.medicalNo==null?"":data.object.medicalNo,'2',data.object.breakPromiseCount);
	          				}else{
	          					if(data.state==2){
	          						check(name,"",cardNo,"","","","","","");
	          					}else{
	          						alert(data.msg);
	          					}
	          					
	          				}
	                    }
	          });
		
	}
	
	
	function getName(){
		var cardNo=$("#cardNo").val();
		if(!isIdCardNo("cardNo")){
			return false;
		};
		$.ajax({
	          type : "post",
	          dataType: "json",
	          url : "register_getTrueNameBynoid.action",
	          data : "regPeople.identityCard=" + cardNo,
	          async : false,
	          success : function(data){
	          				$("#trueName").attr('disabled',false);
	          				if(data.success){
	          					$("#trueName").val(data.msg);
	          				}else{
	          					alert(data.msg);
	          				}
	                    }
	          });
	}
	
	function isIdCardNo(id) {
	var num=$("#"+id).val();
	num = num.toUpperCase();
	if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num))) {
		$("#"+id).css("border","solid 2px red").attr("title","身份证格式不正确")
		$("#"+id).focus();
		return false;
	}
	var len, re;
	len = num.length;
	if (len == 15) {
		re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
		var arrSplit = num.match(re);
		var dtmBirth = new Date("19" + arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
		var bGoodDay;
		bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
		if (!bGoodDay) {
			$("#"+id).css("border","solid 2px red").attr("title","身份证格式不正确");
			$("#"+id).focus();
			return false;
		} 
		else {
			$("#"+id).css("border","solid 2px green").attr("title","身份证格式正确");
			return true;
		//	birth.value = "19" + arrSplit[2] + "-" + arrSplit[3] + "-" + arrSplit[4];
		//	var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
		//	var arrCh = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
		//	var nTemp = 0, i;
		//	num = num.substr(0, 6) + "19" + num.substr(6, num.length - 6);
		//	for (i = 0; i < 17; i++) {
		//		nTemp += num.substr(i, 1) * arrInt[i];
		//	}
		//	num += arrCh[nTemp % 11];
		//	msg.innerHTML = "";
		//	return true;
		}
	}
	if (len == 18) {
		re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
		var arrSplit = num.match(re);
		var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
		var bGoodDay;
		bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
		if (!bGoodDay) {
			$("#"+id).css("border","solid 2px red").attr("title","身份证格式不正确");
			$("#"+id).focus();
			return false;
		} else {
		  	//birth.value = arrSplit[2] + "-" + arrSplit[3] + "-" + arrSplit[4];
		  	var valnum;
		  	var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
		  	var arrCh = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
		  	var nTemp = 0, i;
		  	for (i = 0; i < 17; i++) {
		  		nTemp += num.substr(i, 1) * arrInt[i];
		  	}
		  	valnum = arrCh[nTemp % 11];
		  	if (valnum != num.substr(17, 1)) {
		  		$("#"+id).css("border","solid 2px red").attr("title","身份证格式不正确");
				$("#"+id).focus();
				return false;
		  	}
		  	$("#"+id).css("border","solid 2px green").attr("title","身份证格式正确");
		  	return true;
		}
	}
}
	
	
	function check(name,sex,cardNo,id,mobile,medicalType,medicalNo,friendType,breakPromiseCount){
				TB_remove();
			  var fun=iFrame3.window.checkName;
				fun(name,sex,cardNo,id,mobile,medicalType,medicalNo,friendType,breakPromiseCount);
			}
</script>
</head>
<body><div id="TB_closeAjaxWindow"><div class="floatL">人工服务</div><img src="image/close1.jpg" width="22" height="22" border="0" id='TB_closeWindowButton' onclick='TB_remove();' class="btnClose"/></div>
<dl id="idBox" class="lightbox">
	<div class="dataTable2_set">
	 <div style="padding:15px 15px;">
	 
	   <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableC">
	   <!-- 
	   <tr>
	       <th>姓名</th>
	       <th>性别</th>
	       <th>身份证号码</th>
         </tr>
         <c:if test="${logPeople!=null and logPeople.id!=''}">
         <tr style="cursor: pointer;" title="本人" style="color: red" onclick="check('${logPeople.trueName}','${logPeople.sex}','${logPeople.identityCard}','${logPeople.id }','${logPeople.mobile}','${logPeople.medicalType}','${logPeople.medicalNo}','1');" >
	       <td><font color="red">${logPeople.trueName}</font> </td>
	       <td>
	       <c:if test="${logPeople.sex==1}">男</c:if>
	       <c:if test="${logPeople.sex==2}">女</c:if>
	       </td>
	       <td>${logPeople.identityCard}</td>
         </tr>
         </c:if>
	   <c:forEach items="${regPeoples}" var="obj" >
	   <c:if test="${obj.id!=logPeople.id}">
	     <tr style="cursor: pointer;" onclick="check('${obj.trueName}','${obj.sex}','${obj.identityCard}','${obj.id }','${logPeople.mobile}','${logPeople.medicalType}','${logPeople.medicalNo}','');">
	       <td>${obj.trueName}</td>
	       <td>
	       <c:if test="${obj.sex==1}">男</c:if>
	       <c:if test="${obj.sex==2}">女</c:if>
	       </td>
	       <td>${obj.identityCard}</td>
         </tr>
         </c:if>
        </c:forEach>
        -->
       </table> 
	
	 <table width="100%" border="0" cellspacing="6" cellpadding="0">
	   <tr>
	     <td width="9%" align="right" nowrap="nowrap"><span class="fontRed1">*</span>姓名：</td>
	     <td width="91%" nowrap="nowrap"><label for="select2"></label>
	       <label for="textfield"></label>
	       <input type="text" name="textfield3" id="trueName" class="input250" disabled="disabled" title="请填写身份证后点击'获取'按钮获取用户姓名"/></td>
       </tr>
	   <tr>
	     <td align="right" nowrap="nowrap"><span class="fontRed1">*</span>身份证：</td>
	     <td nowrap="nowrap"><label for="select"></label>
	       <input type="text" name="textfield" id="cardNo" class="input250"   />
	       <input name="input" type="button" title="点击获取姓名" value="获取" class="btnNormal"  onclick="getName()" />
	       </td>
       </tr>
	   <tr>
	     <td align="right" nowrap="nowrap">&nbsp;</td>
	     <td nowrap="nowrap"><input name="input" type="button" value="确定" class="btnNormal" onclick="checkTrueNameAndCardNo();" /></td>
       </tr>
      </table>
	 </div>
  </div>
</dl>
</body>
</html>