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
    <title>专家挂号</title>

    <link href="css/mainstyle.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        var isHava = 0;

        function sureRegisterInfo() {
            if (isHava == 0) {
                isHava = 1;

                save();

//                window.location.href = 'step04_post_action?math=' + (new Date() - 0);
            } else {
                alert("不能重复提交");
            }
        }

        function save() {
            var xhr = $.ajax({
                url: 'step04_post_action',
                data: {t: (new Date() - 0)},
                dataType: "json"
            });

            xhr.done(function(data) {

                if(data.success == true || data.success == "true") {
                    window.location.href = 'step05?orderId=' + data.msg + '&math=' + (new Date() - 0);
                } else {
                    alert(data.msg);
                }
                isHava = 0;
            });

            xhr.fail(function(data) {
                alert("error: " + data);
                isHava = 0;
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
        <li><a href="step02" class="menu-ghyy">&nbsp;</a></li>
    </ul>
</div>

<fmt:setLocale value="zh_CN"/>

<div class="col-right">
    <div class="step04"></div>

    <div class="right-cont">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableD">
            <tr>
                <th colspan="4">就诊人信息确认</th>
            </tr>
            <tr>
                <td width="31%" class="sign">就诊日期：</td>
                <td width="22%"><fmt:formatDate value="${regPipelined.workSchema.workDate }"
                                                pattern="yyyy-MM-dd EEE"/> <c:if
                        test="${regPipelined.workType==1}">上午</c:if> <c:if
                        test="${regPipelined.workType==2}">下午</c:if></td>
                <td width="21%" class="sign">医院名称：</td>
                <td width="26%">${regPipelined.workSchema.depart.hospital.name }</td>
            </tr>
            <tr>
                <td class="sign">科室名称：</td>
                <td>${regPipelined.workSchema.depart.departName }</td>
                <s:if test="regPipelined.workSchema.doctorExpert!=null">
                    <td class="sign">医生姓名：</td>
                    <td>${regPipelined.workSchema.doctorExpert.name}</td>
                </s:if>
                <s:if test="regPipelined.workSchema.doctorExpert==null">
                    <td colspan="2"></td>
                </s:if>
            </tr>
            <tr>
                <td class="sign">就诊者姓名：</td>
                <td>${regPeople.trueName }</td>
                <td class="sign">证件号码：</td>
                <td>${regPeople.identityCard }</td>
            </tr>
            <tr>
                <td class="sign">就诊类型：</td>
                <td>
                    <c:if test="${regPeople.medicalType==0}">自费</c:if>
                    <c:if test="${regPeople.medicalType==1}">市民卡</c:if>
                    <c:if test="${regPeople.medicalType==2}">园区医保</c:if>
                </td>
                <td class="sign">市民卡号/医保编号：</td>
                <td><c:if test="${regPeople.medicalType!=0}">${regPeople.medicalNo}</c:if></td>
            </tr>
            <s:if test="regPipelined.workSchema.doctorExpert!=null">
                <tr>
                    <td class="sign">挂号费：</td>
                    <td>${regPipelined.workSchema.registryFee}</td>
                    <td class="sign">门诊费：</td>
                    <td>${regPipelined.workSchema.clinicFee }</td>
                </tr>
                <tr>
                    <td class="sign">专家费：</td>
                    <td>${regPipelined.workSchema.expertsFee }</td>
                    <td class="sign">&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
            </s:if>
            <s:else>
                <tr>
                    <td class="sign">挂号费：</td>
                    <td>${regPipelined.workSchema.depart.registryFee }</td>
                    <td class="sign">门诊费：</td>
                    <td>${regPipelined.workSchema.depart.clinicFee }</td>
                </tr>
            </s:else>
        </table>

        <br/>

        <div class="btnArea1">
            <input name="" type="button" value="确认" class="btnNormal" onclick="sureRegisterInfo()"/>
            <input name="input" type="button" value="取消" class="btnNormal" onclick="javascript:history.go(-1)"/>
            <!-- <input name="input" type="button" value="取消" class="btnNormal" onclick="javascript:window.location.href='register_toRegister.action?math=<%=new java.util.Date().getTime()%>'" /> -->
        </div>
    </div>
</div>
<%--<div>--%>
<%--【注】<span  class="fontRed1">医保卡市民挂号费在市民就医时把扣除医保费用返还苏付通</span>--%>
<%--</div>--%>
<br/>


</body>
</html>