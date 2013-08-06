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
	function upMessage(startTime,endTime,totalNum,num){
		if(num<totalNum){
			$("#timeDuan").html(startTime+"-"+endTime).css("color","");
			$("#numDuan").html(num+"/"+totalNum).css("color","");
		}else{
			$("#timeDuan").html(startTime+"-"+endTime).css("color","red");
			$("#numDuan").html(num+"/"+totalNum).css("color","red");
		}
		
	}

</script>

<style>
.tc_box{ background:#ffffe1;border:1px solid #000;box-shadow:1px 1px 0px #ccc; width:190px; text-align:center;padding:3px 0px 3px 0px; line-height:90%; }
/*.tc_table{width:160px; margin:0px auto;}
.tc_table img{display:block; margin-bottom:1px;}
.tc_table td{ vertical-align:top; }*/
.marB5{ margin-bottom:5px;}
.tc_tabletext{ font-family:"宋体";font-size:12px;}
.tc_tabletext th{ font-weight:bold;}
.Bline{ color:#ffffe1; border:none;border-top:1px dashed #000;}
.dot_box{width:184px; margin:0px auto;}
.dot_box img{display:block; margin:3px 3px; float:left;}
.floatL{ float:left; }
.clear{ clear:both;}
</style>


<div class="tc_box">
<%--  <table width="100%" border="0" cellspacing="0" cellpadding="1" class="tc_table marB5">--%>
<%--    <tr>--%>
<%--    <c:forEach items="${timeWorkSchemaDtos}" var="obj">--%>
<%--      <td align="center" onclick="" onmouseover="upMessage('${obj.startTime}','${obj.endTime}','${obj.totalNum}','${obj.num}')">--%>
<%--      	<c:choose>--%>
<%--			<c:when test="${obj.num<obj.totalNum}">--%>
<%--				<img src="image/Gdot.gif" alt="" /> --%>
<%--			</c:when>--%>
<%--			<c:otherwise>--%>
<%--				<img src="image/Odot.gif" alt="" /> --%>
<%--			</c:otherwise>--%>
<%--		</c:choose>--%>
<%--      	--%>
<%--      </td>--%>
<%--      </c:forEach>--%>
<%--    </tr>--%>
<%--  </table>--%>
  
  <div class="dot_box marB5">
   <div class="floatL">
   	 <c:forEach items="${timeWorkSchemaDtos}" var="obj">
      	<c:choose>
			<c:when test="${obj.num<obj.totalNum}">
				<img src="image/Gdot.gif" alt="" style="cursor: pointer;"
                     onclick="toRegister('${obj.regPipelined.code}','${obj.startTime}','${obj.endTime}')"
                     onmouseover="upMessage('${obj.startTime}','${obj.endTime}','${obj.totalNum}','${obj.num}')" />
			</c:when>
			<c:otherwise>
				<img src="image/Odot.gif" alt=""  onmouseover="upMessage('${obj.startTime}','${obj.endTime}','${obj.totalNum}','${obj.num}')"/> 
			</c:otherwise>
		</c:choose>
      </c:forEach>
   </div>
   <div class="clear"></div>
   </div>
  
  <hr class="Bline" />
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tc_tabletext">
  <tr>
    <th scope="col" width="50%">时间段</th>
    <th scope="col" width="50%">预约数</th>
  </tr>
  <tr height="10px;"></tr>
  <tr>
    <td nowrap="nowrap" >
    	<div id="timeDuan" style="height: 20px;"></div>
    </td>
    <td nowrap="nowrap" >
    	<div id="numDuan" style="height: 20px;"></div>
    </td>
  </tr>
</table>

</div>
