<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ page isELIgnored="false" %>
<base href="<%=basePath%>">
<link href="css/mainstyle.css" rel="stylesheet" type="text/css"/>
<%--<script type="text/javascript" src="js/jquery-1.4.2.js"></script>--%>

<style type="text/css">
    #pipList {
        position: absolute;
        background: #eee;
        border: 1px solid #000;
        background: #ffffe1;
        color: #333;
    }
</style>
<script type="text/javascript">
    function loadTimeList(node, doctorId, departId, workType, workDate, workScheamId, hospitalCode) {
        var autoNode = $("#pipList");
        autoNode.html('查询排班中。。。请稍等。。。');//清除之前的load内容

        var wordInputOffset = $(node).offset();

        // 自动补全框最开始应该隐藏起来
        autoNode.show()
                .css("top", wordInputOffset.top + 20 + "px")
                .css("left", wordInputOffset.left - 100 + "px");

        // alert(doctorId+"---"+departId+"---"+workType+"------"+workDate+"----"+workScheamId);
        autoNode.load("register_loadTimeList",
                {
                    t: 0, methodType: 0, 'doctor.forWorkNo': doctorId,
                    'depart.departCodeNo': departId, workType: workType,
                    workDate: workDate, workScheamId: workScheamId,
                    'hospital.hospitalCode': hospitalCode, math: Math.random()
                });


    }


    function toHide() {
        $("#pipList").hide();
    }
    function toShow() {
        $("#pipList").show();
    }
    $(document).ready(function () {
        $(".trtr").mouseover(
                function () {
                    $(this).css("background", "pink");
                    $(this).next("tr").css("background", "pink");
                }
        ).mouseout(
                function () {
                    $(this).css("background", "#FFFFFF");
                    $(this).next("tr").css("background", "#FFFFFF");
                }
        )
        $(".trtr2").mouseover(
                function () {
                    $(this).css("background", "pink");
                    $(this).prev("tr").css("background", "pink");
                }
        ).mouseout(
                function () {
                    $(this).css("background", "#FFFFFF");
                    $(this).prev("tr").css("background", "#FFFFFF");
                }
        )
    });
</script>

<div>
    <table width="100%" border="0" cellspacing="0" cellpadding="0"
           class="tableA">
        <tr>
            <%--<th>--%>
                <%--医院名称--%>
            <%--</th>--%>
            <th>
                专家姓名
            </th>
            <th>
                科室名称
            </th>
            <th>
                上午/下午
            </th>
            <c:forEach items="${listDate}" var="obj">
                <th>
                    <div>
                        <fmt:setLocale value="zh_CN"/>
                        <fmt:formatDate value="${obj}" pattern="EEE" timeZone="CST"/>
                    </div>
                    <div class="fontRed1">
                        <fmt:formatDate pattern="yyyy-MM-dd" value="${obj}" timeZone="CST"/>
                    </div>
                </th>
            </c:forEach>

        </tr>
        <c:if test="${!empty wsDoctors}" var="hava">
            <c:forEach items="${wsDoctors}" var="obj">
                <tr class="trtr">
                    <%--<td rowspan="2">--%>
                            <%--${obj.depart.hospital.name }--%>
                    <%--</td>--%>
                    <td rowspan="2" title="${obj.doctor.specialty }">
                        <font color="red">${obj.doctor.name }</font><br/>
                        <font color="#BIBIBI">${obj.doctor.doctorRank}</font>
                    </td>
                    <td rowspan="2">
                            ${obj.depart.departName }
                        <c:if test="${! empty obj.doctor.departNameForDoctor}">(${obj.doctor.departNameForDoctor })</c:if>
                    </td>
                    <td>
                        上午
                    </td>
                    <c:forEach items="${obj.wsDoctors}" var="ws" varStatus="status">
                        <c:if test="${status.count%2!=0 }">
                            <td>
                                <c:if test="${status.count==ws.order }">
                                    <c:if test="${ws.isFull==0}">
                                        <input type="button" name="button2" id="button2" value="" class="btnFull"
                                                />
                                    </c:if>
                                    <c:if test="${ws.isFull==1}">
                                        <input type="button" name="button3" id="button3" value=" "
                                               onclick="loadTimeList(this,'${obj.doctor.forWorkNo }','${obj.depart.departCodeNo }','${ws.workType}','<fmt:formatDate value="${ws.workDate }"/>','${ws.code }','${obj.depart.hospital.hospitalCode }')"
                                               class="btnUnfull"/>
                                    </c:if>
                                    <!-- 已停诊 -->
                                    <c:if test="${ws.isFull==9}">
                                        <input type="button" name="button2" id="button2" value="已停诊"
                                               disabled="disabled"/>
                                    </c:if>
                                    <c:if test="${ws.isFull==8}">
                                        <input type="button" name="button2" id="button2" value="时间未到"
                                               disabled="disabled"/>
                                    </c:if>
                                </c:if>
                                &nbsp;
                            </td>
                        </c:if>
                    </c:forEach>

                </tr>
                <tr class="trtr2">
                    <td>
                        下午
                    </td>
                    <c:forEach items="${obj.wsDoctors}" var="ws" varStatus="status">
                        <c:if test="${status.count%2==0 }">
                            <td>
                                <c:if test="${status.count==ws.order }">
                                    <c:if test="${ws.isFull==0}">
                                        <input type="button" name="button2" id="button2" value=" " class="btnFull"/>
                                    </c:if>
                                    <c:if test="${ws.isFull==1}">
                                        <input type="button" name="button3" id="button3" value=" "
                                               onclick="loadTimeList(this,'${obj.doctor.forWorkNo }','${obj.depart.departCodeNo }','${ws.workType}','<fmt:formatDate value="${ws.workDate }"/>','${ws.code }','${obj.depart.hospital.hospitalCode }')"
                                               class="btnUnfull"/>
                                    </c:if>
                                    <c:if test="${ws.isFull==9}">
                                        <input type="button" name="button2" id="button2" value="已停诊"
                                               disabled="disabled"/>
                                    </c:if>
                                    <c:if test="${ws.isFull==8}">
                                        <input type="button" name="button2" id="button2" value="时间未到"
                                               disabled="disabled"/>
                                    </c:if>
                                </c:if>
                                &nbsp;
                            </td>
                        </c:if>
                    </c:forEach>

                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${!hava}">
            <tr>
                <td colspan="11">
                    <font color="red">暂无数据……</font>
                </td>
            </tr>
        </c:if>
    </table>
</div>
<c:if test="${hava}">
    <jsp:include page="workScheamPage.jsp">
        <jsp:param name="actionURL" value="register_loadWorkScheamDoctor.action"/>
    </jsp:include>
</c:if>
<div id="pipList" style="height: auto;width: 164px;" onmouseout="toHide()" onmouseover="toShow()"></div>
			