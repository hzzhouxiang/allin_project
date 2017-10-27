<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.aowin.model.User"%>
<!-- 引进核心标签库core -->  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--  引进日期标签库format-->  
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<!--  引进函数标签库 --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>      
<!--  注意，它必须放在el表达式中使用，格式 ${前缀+冒号+函数名称}  -->
    

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta charset="utf-8">
<%@ include file="initVariables.jsp" %>
<style type="text/css">
.ui-sortable .panel-header{ cursor:move}
</style>
<title>测试页面 查看用户信息</title>
<meta name="keywords" content="关键词,5个左右,单个8汉字以内">
<meta name="description" content="网站描述，字数尽量空制在80个汉字，160个字符以内！">

<script type="text/javascript">
function doajax(){
	App.Ajax.request({
	    url: 'modifyuser.do',
	    data: {
	    	'userphone':$('input[name=userphone]').val(),
	        'password':$('input[name=password]').val(),
	        'username':$('input[name=username]').val(),
	        'gender':$('input[name=gender]').val(),
	        'usergameid':$('input[name=usergameid]').val(),
	        'useridcard':$('input[name=useridcard]').val(),
	        'userrole':$('input[name=userrole]').val()
	    },
	    isAutoTip: false,
	    success: function (resp) {
	        if (resp.returnCode != 200) {
	            alert("操作失败:" + resp.msg);
	        } else {
	            alert('修改成功');
	            /*然后做点什么*/
	            window.location.href = 'index.do';
	        }
	    }
	});
}

</script>
</head>
<body>
	<div id="usermsg">个人信息
		<p id="username">姓名：<c:out value="${sessionScope.user.username}" default="无"></c:out></p></p>
		<p id="usergameid">游戏id： <c:out value="${sessionScope.user.usergameid}" default="无"></c:out></p>
		<p id="userphone">联系方式： <c:out value="${sessionScope.user.userphone}" default="无"></c:out></p>
	</div>
	<br>
	<div id="teammsg">战队信息
		<p id="teamname">战队名：<c:out value="${sessionScope.team.teamname}" default="无"></c:out></p></p>
		<p id="usergameid">队长名： <c:out value="${sessionScope.team.leadername}" default="无"></c:out>
		<c:out value="${sessionScope.leader.leadergameid}" ></c:out>
		<c:out value="${sessionScope.leader.leaderphone}" ></c:out>
		</p> 
		<p id="userphone">队员：
		<c:if test="${empty sessionScope.member}">
   			<c:out value="无"/>
		</c:if>
		<c:forEach var="listM" items="${sessionScope.member}" varStatus="listMembers" begin="1">
          <c:out value="${listM.membername}" default="无"></c:out>
          <c:out value="${listM.membergameid}" default="无"></c:out>
          <c:out value="${listM.memberphone}" default="无"></c:out><br>
		</c:forEach> 
		
		</p>
	</div>
</body>

</html>
