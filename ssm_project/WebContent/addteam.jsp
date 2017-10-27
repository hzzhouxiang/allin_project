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
<title>测试页面 创建战队</title>
<meta name="keywords" content="关键词,5个左右,单个8汉字以内">
<meta name="description" content="网站描述，字数尽量空制在80个汉字，160个字符以内！">

<script type="text/javascript">
function doajax(){
	App.Ajax.request({
	    url: 'addteam.do',
	    data: {
	    	 'teamname':$('input[name=teamname]').val()
	    },
	    isAutoTip: false,
	    success: function (resp) {
	        if (resp.returnCode != 200) {
	            alert("操作失败:" + resp.msg);
	        } else {
	            alert('创建战队成功');
	            /*然后做点什么*/
	            window.location.href = 'index.do';
	        }
	    }
	});
}

</script>
</head>
<body>
	<div >
	<form action="javascript:;" method="post" id="team-form">
		填写战队名称：

		<!--  placeholder 规定可描述输入字段预期值的简短的提示信息。 -->
		<br> <input maxlength="12"  placeholder="战队名称" name="teamname">
		<br> <input type="button" value="提交"  onclick="doajax();">
	</form>
	</div>
	
</body>

</html>
