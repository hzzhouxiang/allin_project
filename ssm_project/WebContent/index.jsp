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


<style type="text/css">

</style>
<title>测试页面 添加用户</title>
<meta name="keywords" content="关键词,5个左右,单个8汉字以内">
<meta name="description" content="网站描述，字数尽量空制在80个汉字，160个字符以内！">

<script type="text/javascript">
<!--随机生成随机验证码-->
function loadimage() {
	$("#randImage").attr('src',"${contextPath}"+"image.jsp?jsessionid=" + Math.random());
}

<!--ajax验证-->
function doajax(){
	App.Ajax.request({
	    url: 'userentryforgame.do',
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
	            alert('成功报名参赛');
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
  Hello SSM!!! <c:out value="${usermsg}" default=""></c:out>
  <br> <input type="button" value="添加用户"  onclick="location='adduser.jsp'">
  <br> <input type="button" value="修改用户"  onclick="location='loaduser.do'">
  <br> <input type="button" value="报名"  onclick="doajax();">
  <br>
  <br>
    <br><br>



</body>  

</html>  