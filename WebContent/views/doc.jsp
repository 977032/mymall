<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 
电商平台专用接口文档和接口测试工具配置文件
-->
<!DOCTYPE html>
<% String root=request.getContextPath(); %>
<html>
<head>
<link rel="stylesheet" href="<%=root%>/lib/bootstrap3.0.2/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=root%>/lib/silviomoreto-bootstrap-select-9656fcf/bootstrap-select.min.css">
<link rel="stylesheet" href="<%=root%>/css/teletext.css">
<link rel="stylesheet" href="<%=root%>/lib/jsonformat/s.css">
<script type="text/javascript" src="<%=root%>/lib/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap3.0.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=root%>/lib/silviomoreto-bootstrap-select-9656fcf/bootstrap-select.min.js"></script>
<script type="text/javascript" src="<%=root%>/lib/jquery.base64.js"></script>
<script type="text/javascript" src="<%=root%>/lib/jsonformat/c.js"></script>
<script type="text/javascript">
var EnvConfig={
	ROOT: "<%=root%>" 
}
</script>
<script type="text/javascript" src="<%=root%>/js/doc.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>电商平台文档和接口测试</title>
</head>
<body>
<div class="container" style="width: 100%;">
	<div class="page-header" style="margin: 20px 0 0">
	  <h1><small>接口文档和接口测试</small></h1>
	</div>
	<div class="well row">
		<div class="col-lg-3"><select id="serviceSelector" class="form-control selectpicker" data-live-search="true" style="font-size: 1.4em"></select></div>
		<div class="col-lg-9"><h4 id="pathDisplay" class="alert alert-info" style="margin: 0; padding: 7px; font-size: 1.2em; font-family: 'Courier New'; font-weight: bold; line-height: 1.1em; cursor: default"></h4></div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div id="serviceStatus"></div>
			<ul class="nav nav-tabs">
				<li class="active"><a href="#GUI" data-toggle="tab">GUI</a></li>
				<li><a href="#JSON" data-toggle="tab">JSON</a></li>
				<li><a href="#DOC" data-toggle="tab">文档</a></li>
			</ul>
			<div class="tab-content" style="margin-top: 1em">
				<div class="tab-pane active fade in" id="GUI">
					<div class="row">
						<div class="col-lg-3 col-md-3">
							<div class="panel panel-primary">
								<div class="panel-heading">参数</div>
								<div class="panel-body">
									<form class="form-horizontal" id="form" method="POST" target="_blank">
										<input type="hidden" name="path" />
										<input type="hidden" name="_method" value="PUT">
										<div id="paramList"></div>
									</form>
									<form action="" target="_blank" id="tempForm" class="hidden"></form>
									<div class="alert alert-danger" id="requireCheck"></div>
								</div>
								<div class="panel-footer">
									<button type="button" id="execute1" class="btn btn-primary btn-lg btn-block"><i class="glyphicon glyphicon-play"></i> 执行</button>
								</div>
							</div>
						</div>
						<div class="col-lg-9 col-md-9" id="result">
							<div id="teletext">
							</div>
							<div id="flat">
								<table class="table table-hover">
									<tbody></tbody>
								</table>
							</div>
							<div id="list">
								<table class="table table-hover">
									<thead><tr></tr></thead>
									<tbody></tbody>
								</table>
							</div>
							<div id="plain"></div>
							<div id="image"></div>
							<div id="video"></div>
							<div id="audio"></div>
							<div id="html"></div>
							<div id="loading">
								<div class="progress progress-striped active">
									<div class="progress-bar"  role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
										<span class="sr-only">Loading</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="tab-pane fade" id="JSON">
					<div class="row">
						<div class="col-lg-3 col-md-3 json">
							<div class="panel panel-primary">
								<div class="panel-heading">参数</div>
								<div class="panel-body">
									<div id="jsonRequest"></div>
								</div>
							</div>
						</div>
						<div class="col-lg-9 col-md-9 json" id="jsonResponse">
						</div>
					</div>
				</div>
				<div class="tab-pane fade" id="DOC">
					<div class="panel panel-primary">
						<div class="panel-heading">输入参数</div>
						<div class="panel-body">
							<table id="inputParamTable" class="table table-hover">
								<thead>
									<tr>
										<th></th>
										<th>字段名</th>
										<th>含义</th>
										<th>类型</th>
										<th>长度/格式</th>
										<th>必填</th>
										<th>路径参数</th>
										<th>查询参数</th>
										<th>报文体参数</th>
										<th>默认值</th>
										<th>备注</th>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
					</div>
					<div class="panel panel-primary">
						<div class="panel-heading">输出参数</div>
						<div class="panel-body">
							<table id="outputParamTable" class="table table-hover">
								<thead>
									<tr>
										<th></th>
										<th>字段名</th>
										<th>含义</th>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Ajax请求错误弹出框 -->
<div class="modal fade" id="ajaxErrorModal" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">请求发生异常</h4>
			</div>
			<div class="modal-body">
				<h5 class="alert alert-danger"></h5>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
			</div>
		</div>
	</div>
</div>
<!-- Ajax请求错误弹出框 -->

<!-- 数据请求返回错误消息弹出框 -->
<div class="modal fade" id="bizErrorModal" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<h5 class="alert alert-warning"></h5>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
			</div>
		</div>
	</div>
</div>
<!-- 数据请求返回错误消息弹出框 -->
</body>
</html>