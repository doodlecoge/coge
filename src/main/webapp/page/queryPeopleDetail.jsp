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
    <div class="positonL"><span class="padR20">当前位置</span>预约信息查询 &gt; 挂号人员信息&gt; <span class="fontRed1 bold">详细信息</span></div><div class="positonR"></div></div><div class="mainBox">
      <div>
      <table width="100%" border="0" cellspacing="6" cellpadding="0">
        <tr>
          <td width="9%" align="right" nowrap="nowrap">姓名：</td>
          <td width="91%" nowrap="nowrap"><label for="select2"></label>
            ${regPeople.trueName }</td>
        </tr>
        <tr>
          <td align="right" nowrap="nowrap">性别：</td>
          <td nowrap="nowrap">
			<s:if test="regPeople.sex==1">男</s:if>
			<s:else>女</s:else>
</td>
        </tr>
        <tr>
          <td align="right" nowrap="nowrap">身份证号码：</td>
          <td nowrap="nowrap">${regPeople.identityCard }</td>
        </tr>
        <tr>
          <td align="right" nowrap="nowrap">就诊方式：</td>
          <td nowrap="nowrap">
          	<s:if test="regPeople.medicalType!=null">
          		<s:if test="regPeople.medicalType==0">自费</s:if>
				<s:else>市民卡/园区医保</s:else>
          	</s:if>
			<s:else>其它</s:else>
          </td>
        </tr>
        <tr>
          <td align="right" nowrap="nowrap">市民卡号/医保编号：</td>
          <td nowrap="nowrap">${regPeople.medicalNo }</td>
        </tr>
        
        <tr>
          <td align="right" nowrap="nowrap">解除黑名单客服：</td>
          <td nowrap="nowrap">
				${regPeople.lastUser.trueName }
			</td>
        </tr>
        <tr>
          <td align="right" nowrap="nowrap">解除日期:</td>
          <td nowrap="nowrap"><fmt:formatDate value="${regPeople.cancelDate}"/></td>
        </tr>
        <tr>
          <td align="right" nowrap="nowrap">解除原因:</td>
          <td nowrap="nowrap">
          <textarea  style="width: 500px;height: 300px;" disabled="disabled">${regPeople.removeReason}</textarea>
          
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
