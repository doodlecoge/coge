<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>挂号结果</title>

    <link href="css/mainstyle.css" rel="stylesheet" type="text/css"/>
    <%--<script type="text/javascript" src="js/jquery-1.4.2.js"></script>--%>
</head>


<body>
<div class="mainContent">
    <div class="position">
        <div class="positonL">
            <span class="padR20">当前位置</span>12320代挂号 &gt; 按专家或科室挂号 &gt; 确认就诊人信息
            &gt;
            <span class="fontRed1 bold">挂号成功</span>
        </div>
        <div class="positonR"></div>
    </div>
    <div class="mainBox">


            <div class="successBg">
                请于
                <span class="fontRed1">
                    <fmt:formatDate
                        value="${regOrder.regPipelined.workSchema.workDate}"
                        pattern="yyyy-MM-dd EEE"/>
                    <fmt:formatDate value="${regOrder.stateTime }" pattern="HH:mm"/>
                        ——
                    <fmt:formatDate value="${regOrder.endTime }" pattern="HH:mm"/>
                </span>
                至
                <span class="fontRed1">
                    ${regOrder.regPipelined.workSchema.depart.hospital.name }
                </span>
                的一卡通终端机，选择"智慧医疗取号"。

                <s:if test="regOrder.regPipelined.workSchema.doctorExpert!=null">
                    您预约的专家为
                    <span class="fontRed1">
                        ${regOrder.regPipelined.workSchema.depart.departName }
                        ${regOrder.regPipelined.workSchema.doctorExpert.name }
                    </span>
                    ,您的就诊序号为
                    <span class="fontRed1">
                        ${regOrder.treatOrder }
                    </span>
                    <s:if test="showCode">
                        ，就诊密码为
                    <span class="fontRed1">
                            ${regOrder.checkCode }
                    </span>
                    </s:if>。
                    <s:if test="regOrder.idealRegisterTime!=null">
                        您的预期就诊时间为<span class="fontRed1">
                        <fmt:formatDate
                                value="${regOrder.idealRegisterTime}"
                                pattern="HH:mm"/></span>,此时间仅供参考。
                    </s:if>
                </s:if>
                <s:else>
                    您的就诊密码为<span class="fontRed1"> ${regOrder.checkCode }</span>。
                </s:else>
            </div>
        <div class="btnArea1">
            <input name="" type="button" value="确认" class="btnNormal"
                   onclick="location='index.action'"/>
        </div>
    </div>
</div>
</body>
</html>