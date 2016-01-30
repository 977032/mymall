<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String root = request.getContextPath();
%>
<html>
<head>
<style>
form{
	border: 1px solid #333;
	margin: 1em;
}
</style>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=root%>/lib/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("button").click(function(){
		var data={
			username: $("#username").val(),
			password: $("form input[name='password']").val()
		}
		
		$.ajax(
			{
				url: "<%=root%>/Login",
				type: "POST",
				data: JSON.stringify(data),
				dataType: "json",
				contentType: "text/plain; Charset=UTF-8",
				success: function(response)
				{
					alert(response.message);
				},
				error: function(xhr, status, message)
				{
					alert(status);
					alert(message);
				}
			}
		);
	});
});

</script>
<title>Login</title>
</head>
<body>
	<form class='form1'>
		<div>登录</div>
		<label>用户名：<input type="text" name="username" /></label>
		<label>密码：<input type="text" name="password" /></label>
		<button type="button">提交</button>
	</form>
</html>