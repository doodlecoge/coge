<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
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
    <%--<script type="text/javascript" src="js/jquery-1.4.2.js"></script>--%>
    <%--<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>--%>
    <script type="text/javascript">
        function queryDetail(code) {
            window.location.href = 'queryRegister_getRegOrderDetail.action?regOrder.code=' + code;
        }

        function quitRegister(id) {
            var num = -1;
            var sendMsgInfo = "确认退号?";
            $.ajax({
                type: "post",
                dataType: "json",
                url: "queryRegister_queryQuitNum.action",
                data: {"regOrder.code": id, "math": Math.random()},
                async: false,
                success: function (data) {
                    num = data.state;
                }
            });
            if (num > 0) {
                sendMsgInfo = "您一周内已经有" + num + "次退号，再次退号就将被计入黑名单。" + sendMsgInfo;
            }
            if (confirm(sendMsgInfo)) {

                $.post("queryRegister_quitRegister.action",
                        {
                            "regOrder.code": id
                        },
                        function (data) {
                            if (!data.success) {
                                alert(data.msg);
                            } else {
                                alert(data.msg);
                                loadDiv();
                                //location='queryRegister_toQueryRegisterList.action?tt=<%= new java.util.Date().getTime()%>';
                            }
                        },
                        "json"
                )
            }
        }
    </script>
</head>
<div>
    <table width="100%" border="0" cellspacing="0" cellpadding="0"
           class="tableC">
        <tr>
            <th width="6%">
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
                预约时间
            </th>
            <th width="8%">
                就诊序号
            </th>
            <th width="8%">
                就诊日期
            </th>
            <%--<th width="8%">--%>
                <%--预约人--%>
            <%--</th>--%>
            <th width="6%">
                代挂客服
            </th>
            <th width="4%">
                状态
            </th>
        </tr>
        <s:if test="regOrders!=null">
            <s:iterator value="regOrders" var="obj">
                <tr>
                    <td>
                            ${obj.mobile }&nbsp;
                    </td>
                    <td>
                        ${obj.trueName }
                    </td>
                    <td>
                            ${obj.hospitalName }&nbsp;
                    </td>
                    <td>
                            ${obj.departName }&nbsp;
                    </td>
                    <td>
                            ${obj.docName }
                        &nbsp;
                    </td>
                    <td>
                        <s:if test="#obj.regType==0">
                            <fmt:formatNumber
                                    pattern=".0">${obj.registryFee+obj.clinicFee+obj.expertsFee }</fmt:formatNumber>
                        </s:if>
                        <s:else>
                            <fmt:formatNumber pattern=".0">${obj.clinicFee+obj.registryFee}</fmt:formatNumber>
                        </s:else>&nbsp;
                    </td>
                    <td>
                        <fmt:formatDate value="${obj.creatTimeee}" pattern="yyyy-MM-dd"/>
                    </td>
                    <td>
                            ${obj.treatOrder }&nbsp;
                    </td>
                    <td>
                        <fmt:formatDate value="${obj.workDate}" pattern="yyyy-MM-dd"/>&nbsp;
                        <br/>
                        <span style="color: #f00;">
                            <fmt:formatDate value="${obj.stateTime}" pattern="HH:mm"/>~<fmt:formatDate value="${obj.endTime}" pattern="HH:mm"/>
                            </span>
                    </td>
                    <%--<td>--%>
                            <%--${obj.trueName }&nbsp;--%>
                    <%--</td>--%>
                    <td>
                            ${obj.opeateESQ.softPhone.phoneNo}
                    </td>
                    <td align="center">
                        <%--<s:property value="nowTime"></s:property>,--%>
                        <%--<s:property value="#obj.workDate"></s:property>--%>
                        <s:if test="#obj.state==1">
                            <s:if test="nowTime<=#obj.workDate">
                                <s:if test="nowTime<#obj.workDate">
                                    <input name="input" type="button" value="退号" class="btnNormal"
                                           onclick="quitRegister('${obj.code }')"/>
                                </s:if>
                                <%--<input name="input" type="button" value="重发" class="btnNormal" onclick="javascript: window.parent.TB_show('','page/dgh/sendMsgAgain.jsp?height=160&width=450&postcode=${obj.code}&postmobile=${obj.mobile}')" />--%>
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
    </table>
</div>
<s:if test="regOrders.size>0">
    <jsp:include page="page.jsp">
        <jsp:param name="actionURL"
                   value="queryRegister_queryRegisterList.action"/>
    </jsp:include>
</s:if>
