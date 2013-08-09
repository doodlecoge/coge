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
		<title>专家挂号</title>

		<link href="css/mainstyle.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
		<%--<script type="text/javascript"--%>
	            <%--src="My97DatePicker/WdatePicker.js"></script>--%>
		<script type="text/javascript">
			function queryDetail(code){
	    		window.location.href='queryRegister_getRegOrderDetail.action?regOrder.code='+code;
	    	}
		</script>

	</head>

	<body>
		<div class="mainContent">
			<div class="position">
				<div class="positonL">
					<span class="padR20">当前位置</span>预约信息查询 &gt;
					<span class="fontRed1 bold">违规记录详细查询</span>
				</div>
				<div class="positonR"></div>
			</div>
			<div class="mainBox">
				<div >
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="tableC">
							<tr>
								<th width="11%">
									电话号码
								</th>
								<th width="8%">
									就诊人
								</th>
								<th width="15%">
									医院名称
								</th>
								<th width="11%">
									科室名称
								</th>
								<th width="8%">
									医生姓名
								</th>
								<th width="7%">
									费用总计
								</th>
								<th width="6%">
									预约日期
								</th>
								<th width="8%">
									预约号码
								</th>
								<th width="8%">
									就诊日期
								</th>
								<th width="8%">
									预约人
								</th>
								<th width="6%">
									代挂客服
								</th>
								<th width="4%">
									状态
								</th>
							</tr>
							<s:if test="regOrders!=null">
							<s:iterator value="regOrders" var="obj" >
							<tr>
								<td>
									${obj.treatPeople.mobile }&nbsp;
								</td>
								<td>
									<a href="javascript:void(0);" onclick="queryDetail('${obj.code }')">${obj.treatPeople.trueName }</a>&nbsp;
								</td>
								<td>
									${obj.regPipelined.workSchema.depart.hospital.name }&nbsp;
								</td>
								<td>
									${obj.regPipelined.workSchema.depart.departName }&nbsp;
								</td>
								<td>
									<s:if test="#obj.regPipelined.workSchema.doctorExpert!=null">
									${obj.regPipelined.workSchema.doctorExpert.name }
									</s:if>&nbsp;
								</td>
								<td>
									<s:if test="#obj.regPipelined.workSchema.doctorExpert!=null">
									${obj.regPipelined.workSchema.registryFee+obj.regPipelined.workSchema.clinicFee+obj.regPipelined.workSchema.expertsFee }
									</s:if>
									<s:else>
									${obj.regPipelined.workSchema.depart.registryFee+obj.regPipelined.workSchema.depart.clinicFee}
									</s:else>&nbsp;
								</td>
								<td>
								<fmt:formatDate value="${obj.creatTimeee}"/>&nbsp;
								</td>
								<td>
									${obj.treatOrder }&nbsp;
								</td>
								<td>
									<fmt:formatDate value="${obj.regPipelined.workSchema.workDate}"/>&nbsp;
								</td>
								<td>
									${obj.regPeople.trueName }&nbsp;
								</td>
								<td>
									${obj.opeateESQ.trueName }&nbsp;
								</td>
								<td align="center">
									<s:if test="#obj.state==1">
									<s:if test="nowTime<=#obj.regPipelined.workSchema.workDate">
									<input name="input" type="button" value="退号" class="btnNormal" onclick="quitRegister('${obj.code }')" />
									<input name="input" type="button" value="重发" class="btnNormal" onclick="sendMsgAgain('${obj.code }')" />
									</s:if>
									<s:else><font color="red">未取号</font></s:else></s:if>
									<s:if test="#obj.state==2">
										<s:if test="#obj.quitType==1">
											<font color="red">已停诊</font>
										</s:if>
										<s:else>已退号</s:else>
									</s:if>
									<s:if test="#obj.state==3"><font color="red">爽约</font></s:if>
									<s:if test="#obj.state==4">已取号</s:if>
								</td>
							</tr>
							</s:iterator>
							</s:if>
							
							<s:if test="regOrders.size==0">
							<tr>
								<td colspan="12">
									<font color="red">暂无数据</font>
								</td>
							</tr>
							</s:if>
							<tr>
								<td colspan="12" align="right">
									<input name="input" type="button" value="返回" class="btnNormal" onclick="javascript:history.back(-1);" />
								</td>
							</tr>
						</table>
					</div>
			</div>
		</div>
	</body>
</html>

