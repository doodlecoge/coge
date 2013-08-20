<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


${docSchedules}

<table>
    <thead>
    <tr>
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
    </thead>
    <tbody>
    <c:forEach items="${docSchedules}" var="ds">
        <tr>
            <td>
                    ${ds.DOCNAME}
                <br/>
                    ${ds.DOCRANK}
            </td>

            <c:forEach items="${listDate}" var="obj">
                <td>
                </td>
            </c:forEach>

        </tr>
        <tr>
            <td>
                    ${ds.DOCNAME}
                <br/>
                    ${ds.DOCRANK}
            </td>

            <c:forEach items="${listDate}" var="obj">
                <td>
                </td>
            </c:forEach>

        </tr>
    </c:forEach>
    </tbody>
</table>