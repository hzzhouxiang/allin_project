<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 引进核心标签库core -->  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--  引进日期标签库format-->  
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<!--  引进函数标签库 --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>      
<!--  注意，它必须放在el表达式中使用，格式 ${前缀+冒号+函数名称}  -->
<%@page import="com.aowin.model.User"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta charset="utf-8">


<style type="text/css">

</style>
<title>测试页面 主页</title>
<meta name="keywords" content="关键词,5个左右,单个8汉字以内">
<meta name="description" content="网站描述，字数尽量空制在80个汉字，160个字符以内！">

<script type="text/javascript">

<!--ajax验证 -->
function doajax(){
	App.Ajax.request({
	    url: 'userentryforgame.do',
	    data: {
	        'userphone':"${sessionScope.user.userphone}"
	    },
	    isAutoTip: false,
	    success: function (resp) {
	        if (resp.returnCode != 200) {
	            alert("操作失败:" + resp.msg);
	        } else {
	            alert('报名参赛成功');
	            /*然后做点什么*/
	            
	        }
	    }
	});
}



function do1(){
	
}
</script>
</head>
<body>  

<%@ include file="initVariables.jsp" %>
  Hello SSM!!!
  <div id="usermsg">
  <span id="userphone">
  <c:out value="${sessionScope.user.userphone}" default="无用户登录"></c:out>
  </span>
  </div>
  <br> <input type="button" value="用户登录"  onclick="location='login.jsp'">
  <br> <input type="button" value="用户注册"  onclick="location='adduser.jsp'">
  <br> <input type="button" value="完善用户信息"  onclick="location='modifyuser.jsp'">
  <br> <input type="button" value="报名"  onclick="doajax();">
  <br> <input type="button" value="查看信息" onclick="location='usermsg.do'">
  <br> <input type="button" value="成为队长" onclick="location='addleader.jsp'">
  <br> <input type="button" value="队长创建战队" onclick="location='addteam.jsp'">
   <br> <input type="button" value="队长添加队员" onclick="location='addmember.jsp'">
  <br>
    <br><br>



</body>  

</html>  