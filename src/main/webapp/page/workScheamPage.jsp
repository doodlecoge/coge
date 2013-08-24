<%@ page import="java.util.Calendar" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page isELIgnored="false" %>
<script>


</script>
<div style="height: 45px; line-height: 45px; text-align: right;">

    <span>${page.pageSize }</span> 条/页 |&nbsp; 共
    <span>${page.totalCount }</span> 条 | 共
    <span>${page.pageCount }</span> 页 | 第
    <span>${page.pageNum }</span> 页

    <c:if test="${page.pageCount>1}">

        <c:if test="${page.pageNum==1}">
            [<span>首页</span> |
            <span>上一页</span> |
            <a class="changePic" href="javascript:void(0);"
               onclick="check(${page.pageNum+1})">下一页</a> |
            <a class="changePic"
               onclick="check(${page.pageCount})"
               href="javascript:void(0);">末页</a>]
        </c:if>

        <c:if test="${page.pageNum>1 and page.pageNum<page.pageCount}">
            [<a class="changePic"
            onclick="check(1)"
            href="javascript:void(0);">首页</a> |
            <a class="changePic"
               onclick="check(${page.pageNum-1})"
               href="javascript:void(0);">上一页</a> |
            <a class="changePic"
               onclick="check(${page.pageNum+1})"
               href="javascript:void(0);">下一页</a> |
            <a class="changePic"
               onclick="check(${page.pageCount})"
               href="javascript:void(0);">末页</a>]
        </c:if>

        <c:if test="${page.pageNum==page.pageCount}">
            [<a class="changePic"
            onclick="check(1)"
            h   ref="javascript:void(0);">首页</a> |
            <a class="changePic"
               onclick="check(${page.pageNum-1})"
               href="javascript:void(0);">上一页</a> |
            <span>下一页</span> |
            <span>末页</span>]
        </c:if>

    </c:if>
</div>
