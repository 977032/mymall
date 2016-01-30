<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String root = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重置密码</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="<%=root%>/lib/bootstrap3.0.2/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=root%>/lib/font-awesome-4.1.0/css/font-awesome.css" rel="stylesheet">

<link href="<%=root%>/css/style.css" rel="stylesheet">

<script type="text/javascript" src="<%=root%>/lib/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap3.0.2/js/bootstrap.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$("#submitButton").click(function(){
		var id = $("#id").val();
		var password=$("#newPassword").val();
		var passwordConfirm = $("#newPasswordConfirm").val();
		var verifyCode=$("#verifyCode").val();
		
		if (!password)
		{
			$("#modal .modal-body h4").html("请输入密码");
			$("#modal").modal("show");
			return;
		}
		
		if (!password)
		{
			$("#modal .modal-body h4").html("请输入密码！");
			$("#modal").modal("show");
			return;
		}
		
		if(!password.match(/^[0-9a-zA-Z]{6,12}$/))
		{
			$("#modal .modal-body h4").html("密码只能是6-12位，且必须为数字和字母的组合");
			$("#modal").modal("show");
			return;
		}
		
		if (password != passwordConfirm)
		{
			$("#modal .modal-body h4").html("两次输入的密码不一致！");
			$("#modal").modal("show");
			return;
		}
		
		
		$.ajax(
			{
				url: "<%=root%>/User/"+id+"/Password?verifyCode="+verifyCode,
				type: "PUT",
				dataType: "json",
				contentType: "text/plain; Charset=UTF-8",
				data: password,
				success: function(response)
				{
					$("#modal .modal-body h4").html("修改成功");
					$("#modal .modal-footer button").click(function(){
						window.close();
					});
					$("#modal").modal("show");
					return;
				},
				error: function(xhr, status, message)
				{
					$("#modal .modal-body h4").html(status +" - "+message);
					$("#modal").modal("show");
					return;
				}
			}
		);
	});
});
</script>

<style type="text/css">
* {
	font-family: "Microsoft Yahei"
}
body{
	background: #ebebeb;
}
.container{
	background: url(../image/reset-password-bg.gif) no-repeat;
	height: 914px;
	width: 1280px;
}
form{
	width: 25em;
	margin-top: 23em;
	padding: 3em;
	background: #FFF;
	border: 1px solid #b4b4b4;
}
form #submitButton{
	color: #632401;
	background: #f19149;
	border-color: #f19149;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<form role="form" class="center-block">
				<input type="hidden" id="id" value="<%=request.getAttribute("userId")%>">
				<div class="form-group">
					<input type="password" class="form-control" id="newPassword" title="新密码" placeholder="新密码">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="newPasswordConfirm" title="确认新密码" placeholder="确认新密码">
				</div>
				<div class="form-group">
					<input type="text" class="form-control" id="verifyCode" title="验证码" placeholder="验证码" readonly="readonly" value="<%=request.getAttribute("verifyCode")%>">
				</div>
				<button type="button" class="btn btn-default btn-block" id="submitButton">提交</button>
			</form>
		</div>
	</div>

<!-- 错误弹出消息 -->
<div class="modal fade" id="modal" data-backdrop="true" tabindex="-1" role="dialog" aria-hidden="true" style="padding-top: 1em">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<h4></h4>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>

</body>
</html>