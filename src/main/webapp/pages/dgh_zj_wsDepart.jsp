<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ page isELIgnored="false"%>
<base href="<%=basePath%>">
<link href="css/mainstyle.css" rel="stylesheet" type="text/css" />
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
    function loadTimeListDepart(node, departId, workScheamId) {
        $("#pipList").html('查询排班中。。。请稍等。。。');//清除之前的load内容
        var autoNode = $("#pipList");
        var wordInputOffset = $(node).offset();
        // 自动补全框最开始应该隐藏起来

        autoNode.show()
                .css("top", wordInputOffset.top + 20 + "px")
                .css("left", wordInputOffset.left - 60 + "px");

        autoNode.load("register_loadTimeListDeaprt.action",
                {
                    t: 0, methodType: 0, 'depart.departCodeNo': departId,
                    workScheamId: workScheamId, math: Math.random()
                });
    }
    function toHide() {
        $("#pipList").hide();
    }
    function toShow() {
        $("#pipList").show();
    }
</script>

<div>
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		class="tableC">
		<tr>
			<th>
				医院名称
			</th>
			<th>
				科室名称
			</th>
			<th>
				已挂号人数
			</th>
			<th>
				操作
			</th>
		</tr>
		<c:if test="${!empty wsDeparts}" var="hava">
			<c:forEach items="${wsDeparts}" var="obj">
		<tr>
			<td>
				${obj.depart.hospital.name }
			</td>
			<td>
				${obj.depart.departName }
			</td>
			<td>
				${obj.regNum }
			</td>
			<td>
				<c:choose>
					<c:when test="${obj.isFull==0}">
						<input type="submit" name="button3" id="button3" value=" " class="btnFull" />
					</c:when>
					<c:otherwise>
						<input type="button" name="button3" id="button3" value=" "  class="btnUnfull" 
				onclick="loadTimeListDepart(this,'${obj.depart.departCodeNo }','${obj.code }')" />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		</c:forEach>
			</c:if>
		<c:if test="${!hava}">
			<tr>
				<td colspan="11">
					<font color="red">暂无数据或不在挂号时间内……</font>
				</td>
			</tr>
		</c:if>
	</table>
</div>
<c:if test="${hava}">
	<jsp:include page="../page/workScheamPage.jsp">
		<jsp:param name="actionURL" value="register_loadWorkScheamDepart.action" />
	</jsp:include>
</c:if>
<div id="pipList" style="height: auto; width: auto;"  onmouseout="toHide()" onmouseover="toShow()"></div>
