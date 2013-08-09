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
    <title>预约历史查询</title>

    <link href="css/mainstyle.css" rel="stylesheet" type="text/css"/>
    <%--<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>--%>
    <script type="text/javascript">

        function loadDiv() {
            $("#loadDiv").html('');
            $("#loadDiv").append('<img src="img/loading.gif" /> ');
            $("#loadDiv").append('加载中。。。请稍等。。。');

            $("#loadDiv").load("queryRegister_queryRegisterList.action",
                    {
                        math: Math.random(),
                        way: 0,
                        id: $("#id").val(),
                        name: $("#name").val(),
                        code: $("#code").val()
                    });
        }
    </script>

</head>

<body>

<div class="col-left">
    <ul class="menu">
        <li><a href="javascript:void(0)" class="menu-title">网上便捷预约挂号</a></li>
        <li><a href="javascript:void(0)" class="menu-fh12320sy">&nbsp;</a></li>
        <li><a href="index" class="menu-fhbjyyghsy">&nbsp;</a></li>
        <li><a href="queryRegister_toQueryRegisterList" class="menu-lscx">&nbsp;</a></li>
        <%--<li><a href="step02" class="menu-ghyy">&nbsp;</a></li>--%>
    </ul>
</div>

<div class="col-right">
    <div class="searchBox">
        <table>
            <tr>
                <td>身份证号</td>
                <td><input type="text" id="id" name="id" style="width: 200px;"/></td>

                <td>姓名</td>
                <td><input type="text" id="name" name="name" style="width: 80px;"/></td>

                <%--<td>就诊密码</td>--%>
                <%--<td><input type="text" id="code" name="code" style="width: 80px;"/></td>--%>

                <td>
                    <input type="button" name="button" id="button" value=" " class="btnSearch" onclick="loadDiv()"/>
                </td>
            </tr>
        </table>
    </div>

    <div id="loadDiv"></div>
</div>


</body>
</html>