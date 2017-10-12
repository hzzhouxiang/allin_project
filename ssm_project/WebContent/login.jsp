<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 引进核心标签库core -->  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--  引进日期标签库format-->  
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<!--  引进函数标签库 --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>      
<!--  注意，它必须放在el表达式中使用，格式 ${前缀+冒号+函数名称}  -->
    
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	String msg = (String)session.getAttribute("login_failed");
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.min.css" />
<style type="text/css">
.ui-sortable .panel-header{ cursor:move}
</style>
<title>测试页面 用户登录</title>
<meta name="keywords" content="关键词,5个左右,单个8汉字以内">
<meta name="description" content="网站描述，字数尽量空制在80个汉字，160个字符以内！">
</head>
<body>
	<div >
	<form action="adduser.do" method="post">
		用户信息 :
		<!-- 错误信息 -->
			<span id="r_msg" style="color:red;">
			<!--如果注册错误信息不为空 则显示红字-->
  			<c:if test="${not empty regist_failed}">   
   				<span style="color:red;">
  						<c:out value="${regist_failed}" />
  						 <c:remove  var="regist_failed"  scope="session"  />
   				</span>  
			</c:if> 
			</span>
			

			
		<!--  placeholder 规定可描述输入字段预期值的简短的提示信息。 -->
		<br> <input maxlength="15"  placeholder="用户名" name="username">
		<input  maxlength="20" placeholder="密码" name="password"><br> 
		<br> <input maxlength="1" placeholder="性别" name="gender">
		<input maxlength="11"  placeholder="联系电话" name="userphone" ><br> 
		<br> <input  maxlength="20" placeholder="游戏id" name="usergameid">
		<input  maxlength="18" placeholder="身份证" name="useridcard"><br> 
		<br> <input  maxlength="15" placeholder="角色所在大区" name="userrole">
		<br> 
		<!--  
		User
		Role :<br> <input placeholder="user Role Id"
			name="roleList[0].roleId"> <input
			placeholder="user Role Name" name="roleList[0].roleName"> <br>
		<input placeholder="user Role Id" name="roleList[1].roleId"> <input
			placeholder="user Role Name" name="roleList[1].roleName"> <br>
		-->
		<br> <input type="submit" value="提交">
	</form>
	</div>
	
</body>

</html>
