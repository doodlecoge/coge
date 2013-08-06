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
		<title>挂号详情页</title>

		<link href="css/mainstyle.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
		<script type="text/javascript"
	            src="My97DatePicker/WdatePicker.js"></script>
	            <script type="text/javascript">
	            function quitRegister(id){
	            	if(confirm("确认退号?"))
 					{
	            		$.post("queryRegister_quitRegister.action",
							{
								"regOrder.code":id
							},
							function(data)
							{
								if(!data.success){
									alert(data.msg);
								}else{
									alert(data.msg);
									location='queryRegister_toQueryRegisterList.action?tt=<%= new java.util.Date().getTime()%>';
								}
							},
							"json"
						)
	            	}
	            }
	            
	            </script>

  </head>
  
  <body>
   <div class="mainContent"><div class="position">
    <div class="positonL"><span class="padR20">当前位置</span>预约信息查询 &gt; 挂号信息查询 &gt; <span class="fontRed1 bold">详细信息</span></div><div class="positonR"></div></div><div class="mainBox">
      <div>
      <table width="100%" border="0" cellspacing="6" cellpadding="0">
        <tr>
          <td width="9%" align="right" nowrap="nowrap">就诊人姓名：</td>
          <td width="91%" nowrap="nowrap"><label for="select2"></label>
            ${regOrder.trueName }</td>
        </tr>
        <tr>
          <td align="right" nowrap="nowrap">性别：</td>
          <td nowrap="nowrap">
			<s:if test="regOrder.sex==1">男</s:if>
			<s:else>女</s:else>
</td>
        </tr>
        <tr>
          <td align="right" nowrap="nowrap">身份证号码：</td>
          <td nowrap="nowrap">${regOrder.identityCard }</td>
        </tr>
        <tr>
          <td align="right" nowrap="nowrap">就诊方式：</td>
          <td nowrap="nowrap">
          	<s:if test="regOrder.medicalType!=null">
          		<s:if test="regOrder.medicalType==0">自费</s:if>
          		<s:if test="regOrder.medicalType==1">市区医保(市民卡)</s:if>
          		<s:if test="regOrder.medicalType==2">园区医保</s:if>
          	</s:if>
			<s:else>其它</s:else>
          </td>
        </tr>
        <tr>
          <td align="right" nowrap="nowrap">市民卡号/医保编号：</td>
          <td nowrap="nowrap">${regOrder.medicalNo }</td>
        </tr>
        <tr>
          <td align="right" nowrap="nowrap">医院名称：</td>
          <td nowrap="nowrap">${regOrder.hospitalName }</td>
        </tr>
        <tr>
          <td align="right" nowrap="nowrap">科室名称：</td>
          <td nowrap="nowrap">${regOrder.departName }</td>
        </tr>
        <tr>
          <td align="right" nowrap="nowrap">专家姓名：</td>
          <td nowrap="nowrap">
				${regOrder.docName }
		</td>
        </tr>
        <tr>
          <td align="right" nowrap="nowrap">代挂客服：</td>
          <td nowrap="nowrap">
				${regOrder.opeateName}
			</td>
        </tr>
        <tr>
          <td align="right" nowrap="nowrap">预约日期：</td>
          <td nowrap="nowrap"><fmt:formatDate value="${regOrder.creatTimeee}"/></td>
        </tr>
        <tr>
          <td align="right" nowrap="nowrap">预约号码：</td>
          <td nowrap="nowrap">${regOrder.treatOrder }</td>
        </tr>
        <tr>
          <td align="right" nowrap="nowrap">就诊日期：</td>
          <td nowrap="nowrap"><fmt:formatDate value="${regOrder.workDate}" pattern="yyyy-MM-dd EEE"/>
          <fmt:formatDate value="${regOrder.stateTime }" pattern="HH:mm"/>——<fmt:formatDate value="${regOrder.endTime }" pattern="HH:mm"/></td>
        </tr>
        <s:if test="regOrder.regPipelined.workSchema.doctorExpert!=null">
	        <tr>
	          <td align="right" nowrap="nowrap">挂号费：</td>
	          <td nowrap="nowrap">${regOrder.regPipelined.workSchema.registryFee}</td>
	        </tr>
	        <tr>
	          <td align="right" nowrap="nowrap">诊疗费：</td>
	          <td nowrap="nowrap">${regOrder.regPipelined.workSchema.clinicFee }</td>
	        </tr>
	        <tr>
	          <td align="right" nowrap="nowrap">专家费：</td>
	          <td nowrap="nowrap">
	          	${regOrder.regPipelined.workSchema.expertsFee  }
	          </td>
	        </tr>
	        <tr>
	          <td align="right" nowrap="nowrap">费用总计：</td>
	          <td nowrap="nowrap">
					<fmt:formatNumber pattern=".0">${regOrder.regPipelined.workSchema.registryFee+regOrder.regPipelined.workSchema.clinicFee+regOrder.regPipelined.workSchema.expertsFee }</fmt:formatNumber>
				</td>
	        </tr>
        </s:if>
        <s:else>
        	<tr>
	          <td align="right" nowrap="nowrap">挂号费：</td>
	          <td nowrap="nowrap">${regOrder.regPipelined.workSchema.depart.registryFee}</td>
	        </tr>
	        <tr>
	          <td align="right" nowrap="nowrap">诊疗费：</td>
	          <td nowrap="nowrap">${regOrder.regPipelined.workSchema.depart.clinicFee }</td>
	        </tr>
	        <tr>
	          <td align="right" nowrap="nowrap">费用总计：</td>
	          <td nowrap="nowrap">
					<fmt:formatNumber pattern=".0">${regOrder.regPipelined.workSchema.depart.registryFee+regOrder.regPipelined.workSchema.depart.clinicFee}</fmt:formatNumber>
				</td>
	        </tr>
        </s:else>
         <tr>
          <td align="right" nowrap="nowrap">就诊密码：</td>
          <td nowrap="nowrap">${regOrder.checkCode }</td>
        </tr>
        <c:if test="${regOrder.state==2}">
         <tr>
          <td align="right" nowrap="nowrap">退号时间：</td>
          <td nowrap="nowrap"><font color="red"><fmt:formatDate value="${regOrder.quitTimeee }" pattern="yyyy-MM-dd HH:mm:ss"/></font></td>
        </tr>
         <tr>
          <td align="right" nowrap="nowrap">退号操作人：</td>
          <td nowrap="nowrap"><font color="red">${regOrder.canclePeople.trueName }</font></td>
        </tr>
        </c:if>
        <tr>
          <td align="right" nowrap="nowrap">挂号状态：</td>
          <td nowrap="nowrap">
				<c:if test="${regOrder.state==1}">
					<c:if test="${nowTime<regOrder.regPipelined.workSchema.workDate}" var="hava">
						<input name="input" type="button" value="退号" class="btnNormal" onclick="quitRegister('${regOrder.code }')" />
					</c:if>
					<c:if test="${!hava}">
						<font color="red">未取号</font>
					</c:if>
				</c:if>
				<c:if test="${regOrder.state==2}">
					<s:if test="regOrder.quitType==1">
						<font color="red">已停诊</font>
					</s:if>
					<s:else>
						已退号
					</s:else>
				
				</c:if>
				<c:if test="${regOrder.state==3}">爽约</c:if>
				<c:if test="${regOrder.state==4}">已取号</c:if>
			</td>
        </tr>
        <tr>
          <td align="right" nowrap="nowrap">&nbsp;</td>
          <td nowrap="nowrap">
            <input name="input" type="button" value="返回" class="btnNormal" onclick="javascript:history.back(-1);" />
         </td>
        </tr>
      </table>
    </div>
</div></div>
  </body>
</html>
