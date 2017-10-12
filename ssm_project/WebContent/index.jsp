<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<!DOCTYPE html>  
<html>  
<head>  
<base href="<%=basePath%>">  
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<!-- 搜索引擎 -->
	<meta http-equiv="keywords" content="allin,奥义电竞,奥义">
	<meta http-equiv="description" content="This is my page">
</head>  
<body>  
  Hello SSM!!!
</body>  

</html>  