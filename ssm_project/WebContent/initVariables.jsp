<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String contextPath = request.getContextPath();
	String requestServer = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
	String basePath = requestServer + contextPath;
%>

<c:set var="contextPath" value="<%=contextPath%>"/>

<script src="js/jquery-1.11.1.js"></script>
<script src="js/bootstrap.js"></script>
<script src="${contextPath }/js/zkl.js" type="text/javascript"></script>
<script type="text/javascript">

	Config = {
		ROOT  		: '<%=contextPath%>',
		BasePath  	: '<%=basePath%>',
	};

	(function($){
		$.fn.extend({
			serializeObject : function() {
				var o = {};
				var a = this.serializeArray();
				$.each(a, function() {
					var val = this.value || '';
					if(o[this.name]){
						if(Array.isArray(o[this.name])){
							o[this.name].push(val);
						} else {
							var ary = new Array();
							ary.push(o[this.name]);
							ary.push(val);
							o[this.name] = ary;
						}
					} else {
						o[this.name] = val;
					}
				});
				return o;
			}
		});
	})(jQuery);
</script>