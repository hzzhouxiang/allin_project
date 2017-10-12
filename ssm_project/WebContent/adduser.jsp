<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<title>测试页面 添加用户</title>
<meta name="keywords" content="关键词,5个左右,单个8汉字以内">
<meta name="description" content="网站描述，字数尽量空制在80个汉字，160个字符以内！">

<script type="text/javascript">
function doajax(){
	App.Ajax.request({
	    url: 'checkadduser.do',
	    data: {
	        'userphone': $('input[name=userphone]').val(),
	        'password':$('input[name=password]').val()
	    },
	    isAutoTip: false,
	    success: function (resp) {
	        if (resp.returnCode != 200) {
	            alert("操作失败:" + resp.msg);
	        } else {
	            alert('添加成功');
	            /*然后做点什么*/
	            
	        }
	    }
	});
}


</script>
</head>
<body>
	<div >
	<form action="javascript:;" method="post">
		用户信息 :

		<!--  placeholder 规定可描述输入字段预期值的简短的提示信息。 -->
		<br> <input maxlength="15"  placeholder="用户名" name="username">
		<input type="password"  maxlength="20" placeholder="密码" name="password"><br> 
		<br> <input maxlength="1" placeholder="性别" name="gender">
		<input maxlength="11"  placeholder="联系电话" name="userphone" ><br> 
		<br> <input  maxlength="20" placeholder="游戏id" name="usergameid">
		<input  maxlength="18" placeholder="身份证" name="useridcard"><br> 
		<br> <input  maxlength="15" placeholder="角色所在大区" name="userrole">
		<br> 
	
		<br> <input type="button" value="提交"  onclick="doajax();">
	</form>
	</div>
	
</body>

</html>
