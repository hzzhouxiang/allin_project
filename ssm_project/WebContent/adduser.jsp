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
<link rel="stylesheet" href="css/bootstrap.min.css">
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
	    url: 'adduser.do',
	    data: {
	        'userphone': $('input[name=userphone]').val(),
	        'password':$('input[name=password]').val(),
	        'phonevcode':$('input[name=phonevcode]').val()
	    },
	    isAutoTip: false,
	    success: function (resp) {
	        if (resp.returnCode != 200) {
	            alert("操作失败:" + resp.msg);
	        } else {
	            alert('添加成功');
	            /*然后跳转页面*/
	            window.location.href = 'index.do';
	        }
	    }
	});
}

function doajax1(){
	App.Ajax.request({
	    url: 'verificationcode.do',
	    data: {
	    	'vcode':$('input[name=vcode]').val(),
	    	'userphone':$('input[name=userphone]').val()
	    },
	    isAutoTip: false,
	    success: function (resp) {
	        if (resp.returnCode != 200) {
	            alert("操作失败:" + resp.msg);
	            loadimage();
	        } else {
	            alert('验证码与手机号格式正确,启动短信接口');
				doajax2()	            
	        }
	    }
	});
}


function doajax2(){
	App.Ajax.request({
	    url: 'usersendmsg.do',
	    data: {
	    	'userphone':$('input[name=userphone]').val()
	    },
	    isAutoTip: false,
	    success: function (resp) {
	        if (resp.returnCode != 200) {
	            alert("操作失败:" + resp.msg);
	            loadimage();
	        } else {
	            alert('短信已发送，请注意查收');
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
		<br>
		<input maxlength="11"  placeholder="联系电话" name="userphone" ><br> <br>
		<input type="password"  maxlength="20" placeholder="密码" name="password"><br> 
		<br> <input  maxlength="6" placeholder="手机验证码" name="phonevcode">
		<br> <br>
		<!-- 按钮触发模态框 -->
		<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" onclick='loadimage()'>
		获取手机验证码
		</button>


		<!-- 模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					请输入验证码
				</h4>
				</div>
				<div class="modal-body">
 					 <input  maxlength="4" placeholder="输入验证码" name="vcode" >
 					 <img src="${contextPath}/image.jsp" alt="点击刷新" id="randImage">
					<a id="kanbuq" href="javascript:loadimage();" style="font-size:12px;color:#888;">看不清?换一张.</a> 
				</div>
				<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消
				</button>
				<button type="button" class="btn btn-primary" onclick="doajax1();">
					确定
				</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	 </div><!-- /.modal fade -->
		<br>
		<br> <input type="button" value="提交"  onclick="doajax();">
	</form>
	</div>
	
</body>

</html>
