
var RESPONSE_CODE =
{
	Success: "0x00",
	Fail: "0x99"
}
var PARAM_TYPE =
// 入参类型
{
	STRING: "String",
	INTETER: "Integer",
	DATE: "Date",
	DECIMAL: "Decimal",
	BOOLEAN: "Y/N"
};
var CONTROL_TYPE =
// 字段输入控件类型
{
	TEXT: "input:text",
	NUMBER: "input:number",
	TEL: "input:telephone",
	FILE: "file",
	PASSWORD: "input:password"
};
var REQUEST_METHOD =
// HTTP请求方式
{
	GET: "GET",
	POST: "POST",
	PUT: "PUT",
	DELETE: "DELETE"
};
var CONNECT_TYPE =
// 连接发起类型
{
	AJAX: "AJAX", // Ajax请求
	FORM: "FORM", // Form提交
	DOWNLOAD: "DOWNLOAD", // 新窗口打开并下
	HTML: "HTML",
	IMAGE: "IMAGE",
	VIDEO: "VIDEO",
	AUDIO: "AUDIO"
};
var DISPLAY_TYPE =
// 字段显示类型
{
	TEXT: "TEXT", // 文本
	IMAGE: "IMAGE" // 图片
};
var SERVICE_STATUS =
{
	YET: "<div class='alert alert-warning'><i class='glyphicon glyphicon-warning-sign'></i> 尚未开发完成<div>",
	UNAVAILABLE: "<div class='alert alert-danger'><i class='glyphicon glyphicon-remove'></i> 不可用，接口存在问题<div>",
	ISSUE: "<div class='alert alert-warning'><i class='glyphicon glyphicon-plane'></i> 已测，但存在问题<div>",
	DONE: "<div class='alert alert-success'><i class='glyphicon glyphicon-ok'></i> 可用，连接测试通过<div>"
};
var ServiceConfig =
[
	{
		name: "获取用户协议",
		code: "GetUserContract",
		status: SERVICE_STATUS.DONE,
		path: "/UserContract",
		method: REQUEST_METHOD.GET,
		category: "用户",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			
		],
		outputTitles:
		[
			"协议"
		],
		outputKeys:
		[
		 	"userContract"
		],
		output: function(data)
		{
			var titles =
			[
			 	"协议"
			];
			var keys =
			[
			 	"userContract"
			];
			var values = mkFlatData(data, keys);
			mkFlat(titles, keys, values);
		}
	},
	{
		name: "用户注册第一步",
		code: "MemberRegistStepOne",
		status: SERVICE_STATUS.DONE,
		path: "/MemberRegistStepOne",
		method: REQUEST_METHOD.PUT,
		category: "用户",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "姓名",
				key: "name",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "性别",
				key: "gender",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "男M;女F;",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: "M"
			},
			{
				name: "手机号",
				key: "mobile",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEL,
				pathVar: false,
				required: false,
				testValue: "13300000001"
			},
			{
				name: "密码",
				key: "password",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.PASSWORD,
				pathVar: false,
				required: true,
				testValue: "123456"
			},
			{
				name: "短信验证码",
				key: "verifyCode",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: "111111"
			}
		],
		outputTitles:
		[
			"用户序号","token","姓名"
		],
		outputKeys:
		[

			"id","token","name"
		],
		output: function(data)
		{
			var titles =
			[
			 	"用户序号","TOKEN","姓名"
			];
			var keys =
			[
			 	"id","token","name"
			];
			var values = mkFlatData(data, keys);
			mkFlat(titles, keys, values);
		}
	},
	{
		name: "三方平台注册",
		code: "ThirdPartRegist",
		status: SERVICE_STATUS.DONE,
		path: "/ThirdPartRegist",
		method: REQUEST_METHOD.PUT,
		category: "用户",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "名称",
				key: "name",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: "小白兔"
			},
			{
				name: "性别",
				key: "gender",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "男M;女F;",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: "M"
			},
			{
				name: "平台编号",
				key: "platform",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "微信:wx,QQ:qq,微博:wb,人人:rr",
				controlType: CONTROL_TYPE.TEL,
				pathVar: false,
				required: false,
				testValue: "qq"
			},
			{
				name: "平台用户ID",
				key: "pId",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			}
		],
		outputTitles:
		[
			"用户序号","TOKEN","名称"
		],
		outputKeys:
		[

			"id","token","name"
		],
		output: function(data)
		{
			var titles =
			[
			 	"用户序号","TOKEN","名称"
			];
			var keys =
			[
			 	"id","token","name"
			];
			var values = mkFlatData(data, keys);
			mkFlat(titles, keys, values);
		}
	},
	{
		name: "用户基本信息修改",
		code: "UserBasicInfo",
		status: SERVICE_STATUS.DONE,
		path: "/UserBasicInfo/{token}",
		method: REQUEST_METHOD.PUT,
		category: "用户",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "token",
				key: "token",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEL,
				pathVar: true,
				required: true,
				testValue: ""
			},
			{
				name: "名称",
				key: "name",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "性别",
				key: "gender",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "男:M;女:F;",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: "M"
			}
		],
		outputTitles:null,
		outputKeys:null,
		output:null
	},
	{
		name: "获取用户注册短信验证码",
		code: "Regist-mobile",
		status: SERVICE_STATUS.DONE,
		path: "/Regist/{mobile}",
		method: REQUEST_METHOD.POST,
		category: "用户",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "手机号",
				key: "mobile",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: ""
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "获取重置密码短信验证码",
		code: "Password-mobile",
		status: SERVICE_STATUS.DONE,
		path: "/Password/{mobile}",
		method: REQUEST_METHOD.POST,
		category: "用户",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "手机号",
				key: "mobile",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: ""
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "手机重置密码",
		code: "ResetPasswordByMobile",
		status: SERVICE_STATUS.DONE,
		path: "/password",
		method: REQUEST_METHOD.PUT,
		category: "用户",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "手机",
				key: "mobile",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "验证码",
				key: "verifyCode",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: "111111"
			},
			{
				name: "密码",
				key: "password",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.PASSWORD,
				pathVar: false,
				required: true,
				testValue: "123456"
			}
			
		],
		outputTitles:null,
		outputKeys:null,
		output:null
	},
	{
		name: "下载头像",
		code: "downloadUserPhoto",
		status: SERVICE_STATUS.DONE,
		path: "/User/Info/{id}/Photo",
		method: REQUEST_METHOD.GET,
		category: "用户",
		connectType: CONNECT_TYPE.IMAGE,
		param:
		[
			{
				name: "用户序号",
				key: "id",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: ""
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "上传头像",
		code: "uploadUserPhoto",
		status: SERVICE_STATUS.DONE,
		path: "/User/Info/{token}/Photo",
		method: REQUEST_METHOD.POST,
		category: "用户",
		connectType: CONNECT_TYPE.FORM,
		param:
		[
			{
				name: "TOKEN",
				key: "token",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: ""
			},{
				name: "头像",
				key: "attach",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.FILE,
				pathVar: false,
				required: true,
				testValue: ""
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "三方登录",
		code: "ThirdPartLogin",
		status: SERVICE_STATUS.DONE,
		path: "/ThirdPartLogin",
		method: REQUEST_METHOD.POST,
		category: "用户",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "平台编号",
				key: "platform",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "微信:wx,QQ:qq,微博:wb,人人:rr",
				controlType: CONTROL_TYPE.TEL,
				pathVar: false,
				required: true,
				testValue: "qq"
			},
			{
				name: "三方平台ID",
				key: "pId",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEL,
				pathVar: false,
				required: true,
				testValue: "111222333"
			}
		],
		outputTitles:
		[
			"用户名","用户id","token"
		],
		outputKeys:
		[

			"name", "id","token"
		],
		output: function(data)
		{
			var titles =
			[
			 	"用户名","用户id","token"
			];
			var keys =
			[
			 	"name", "id","token"
			];
			var values = mkFlatData(data, keys);
			mkFlat(titles, keys, values);
		}
	},
	{
		name: "外部用户登录",
		code: "LoginMember",
		status: SERVICE_STATUS.DONE,
		path: "/LoginMember",
		method: REQUEST_METHOD.POST,
		category: "用户",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "手机号",
				key: "account",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEL,
				pathVar: false,
				required: true,
				testValue: "13300000001"
			},
			{
				name: "密码",
				key: "password",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.PASSWORD,
				pathVar: false,
				required: true,
				testValue: "123456"
			},
			{
				name: "验证码",
				key: "verifyCode",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: "111111"
			}
		],
		outputTitles:
		[
			"用户名","用户id","token"
		],
		outputKeys:
		[

			"name", "id","token"
		],
		output: function(data)
		{
			var titles =
			[
			 	"用户名","用户id","token"
			];
			var keys =
			[
			 	"name", "id","token"
			];
			var values = mkFlatData(data, keys);
			mkFlat(titles, keys, values);
		}
	},
	{
		name: "内部用户登录",
		code: "LoginManager",
		status: SERVICE_STATUS.DONE,
		path: "/LoginManager",
		method: REQUEST_METHOD.POST,
		category: "用户",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "工号",
				key: "account",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEL,
				pathVar: false,
				required: true,
				testValue: "admin"
			},
			{
				name: "密码",
				key: "password",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.PASSWORD,
				pathVar: false,
				required: true,
				testValue: "123456"
			}
		],
		outputTitles:
		[
			"用户名","用户id","token"
		],
		outputKeys:
		[

			"name", "id","token"
		],
		output: function(data)
		{
			var titles =
			[
			 	"用户名","用户序号","token"
			];
			var keys =
			[
			 	"name", "id","token"
			];
			var values = mkFlatData(data, keys);
			mkFlat(titles, keys, values);
		}
	},{
		name: "登出",
		code: "Loginout",
		status: SERVICE_STATUS.DONE,
		path: "/{token}/Logout",
		method: REQUEST_METHOD.GET,
		category: "用户",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "token",
				key: "token",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEL,
				pathVar: true,
				required: true,
				testValue: ""
			}
		],
		outputTitles:null,
		outputKeys:null,
		output: null
	},{
		name: "用户地理信息设置",
		code: "updateUserLocation",
		status: SERVICE_STATUS.DONE,
		path: "/{token}/Location",
		method: REQUEST_METHOD.PUT,
		category: "用户",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "token",
				key: "token",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEL,
				pathVar: true,
				required: true,
				testValue: ""
			},
			{
				name: "经度",
				key: "lng",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "纬度",
				key: "lat",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			}
		],
		outputTitles:null,
		outputKeys:null,
		output:null
	},
	{
		name: "按名称搜索Member用户",
		code: "searchMemberByName",
		status: SERVICE_STATUS.DONE,
		path: "/{token}/searchMemberByName?name=[name]&page=[page]",
		method: REQUEST_METHOD.GET,
		category: "用户",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "token",
				key: "token",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEL,
				pathVar: true,
				required: true,
				testValue: ""
			},
			{
				name: "名称",
				key: "name",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: ""
			},{
				name: "页数",
				key: "page",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "1"
			}
		],

		outputTitles:
		[
			"用户ID", "用户名","性别"
		],

		outputKeys:
		[

			"id", "name","gender"
		],
		output: function(data)
		{
			var titles =
			[
			 	"用户ID", "用户名","性别"
			];
			var keys =
			[
			 	"id", "name","gender"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},
	{
		name: "按名称搜索Manager用户",
		code: "searchManagerByName",
		status: SERVICE_STATUS.DONE,
		path: "/{token}/searchManagerByName?name=[name]&page=[page]",
		method: REQUEST_METHOD.GET,
		category: "用户",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "token",
				key: "token",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEL,
				pathVar: true,
				required: true,
				testValue: ""
			},
			{
				name: "名称",
				key: "name",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: ""
			},{
				name: "页数",
				key: "page",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "1"
			}
		],

		outputTitles:
		[
			"用户ID", "用户名","性别"
		],

		outputKeys:
		[

			"id", "name","gender"
		],
		output: function(data)
		{
			var titles =
			[
			 	"用户ID", "用户名","性别"
			];
			var keys =
			[
			 	"id", "name","gender"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},
	{
		name: "获取最新APP版本信息",
		code: "GetLatestVersionInfo",
		status: SERVICE_STATUS.DONE,
		path: "/Appversion/{appType}",
		method: REQUEST_METHOD.GET,
		category: "关于",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "客户端类型",
				key: "appType",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "安卓0,苹果1",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: "0"
			}
		],
		outputTitles:
		[
			"最新版本号", "更新说明", "下载链接", "创建时间"
		],
		outputKeys:
		[

			"versionNo", "releaseNote", "url", "createTime"
		],
		output: function(data)
		{
			var titles =
			[
				"最新版本号", "更新说明", "下载链接", "创建时间"
			];
			var keys =
			[
				"versionNo", "releaseNote", "url", "createTime"
			];
			var values = mkFlatData(data, keys);
			mkFlat(titles, keys, values);
		}
	},
	{
		name: "获取系统消息列表",
		code: "GetSystemMsgList",
		status: SERVICE_STATUS.DONE,
		path: "/{token}/SystemMsg?token=[token]&page=[page]",
		method: REQUEST_METHOD.GET,
		category: "消息",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "TOKEN",
				key: "token",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: ""
			},{
				name: "页数",
				key: "page",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "1"
			}
		],

		outputTitles:
		[
			"ID", "内容","创建时间","发送者ID","发送者名称","接收者ID"
		],

		outputKeys:
		[

			"id", "content","createTime","fromId","fromName","toId"
		],
		output: function(data)
		{
			var titles =
			[
			 	"ID", "内容","创建时间","发送者ID","发送者名称","接收者ID"
			];
			var keys =
			[
			 	"id", "content","createTime","fromId","fromName","toId"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},{
		name: "删除系统消息",
		code: "DeleteSystemMsg",
		status: SERVICE_STATUS.DONE,
		path: "/{token}/SystemMsg/{id}",
		method: REQUEST_METHOD.DELETE,
		category: "消息",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "TOKEN",
				key: "token",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: ""
			},{
				name: "系统消息ID",
				key: "id",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: ""
			}
		],

		outputTitles:null,
		outputKeys:null,
		output: null
	},
	{
		name: "获取聊天记录",
		code: "GetUserMsgList",
		status: SERVICE_STATUS.DONE,
		path: "/{token}/Message/User/{with}?page=[page]",
		method: REQUEST_METHOD.GET,
		category: "消息",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "TOKEN",
				key: "token",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: ""
			},
			{
				name: "对方ID",
				key: "with",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: ""
			},
			{
				name: "页码",
				key: "page",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "1"
			}
		],

		outputTitles:
		[
			"ID", "内容","创建时间","发送者ID","发送者名称","接收者ID","接收者名称"
		],

		outputKeys:
		[

			"id", "content","createTime","fromId","fromName","toId","toName"
		],
		output: function(data)
		{
			var titles =
			[
			 	"ID", "内容","创建时间","发送者ID","发送者名称","接收者ID","接收者名称"
			];
			var keys =
			[
			 	"id", "content","createTime","fromId","fromName","toId","toName"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},
	{
		name: "发送消息",
		code: "SendMessage",
		status: SERVICE_STATUS.DONE,
		path: "/{token}/Message",
		method: REQUEST_METHOD.POST,
		category: "消息",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "token",
				key: "token",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: ""
			},
			{
				name: "接收者ID",
				key: "toId",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "内容",
				key: "content",
				type: PARAM_TYPE.STRING,
				length: "500",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			}
		],
		outputTitles:
			[
				"ID"
			],

			outputKeys:
			[

				"id"
			],
			output: function(data)
			{
				var titles =
				[
				 	"ID"
				];
				var keys =
				[
				 	"id"
				];
				var values = mkFlatData(data, keys);
				mkFlat(titles, keys, values);
			}
	},
	{
		name: "获取消息列表",
		code: "GetMsgList",
		status: SERVICE_STATUS.DONE,
		path: "/{token}/Message",
		method: REQUEST_METHOD.GET,
		category: "消息",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "TOKEN",
				key: "token",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: ""
			}
		],

		outputTitles:
		[
			"ID", "内容","创建时间","发送者ID","发送者名称","接收者ID","接收者名称"
		],

		outputKeys:
		[

			"id", "content","createTime","fromId","fromName","toId","toName"
		],
		output: function(data)
		{
			var titles =
			[
			 	"ID", "内容","创建时间","发送者ID","发送者名称","接收者ID","接收者名称"
			];
			var keys =
			[
			 	"id", "content","createTime","fromId","fromName","toId","toName"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},
	{
		name: "删除聊天消息",
		code: "DeleteUserMessage",
		status: SERVICE_STATUS.DONE,
		path: "/{token}/Message/{id}",
		method: REQUEST_METHOD.DELETE,
		category: "消息",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "TOKEN",
				key: "token",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: ""
			},
			{
				name: "消息ID",
				key: "id",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: ""
			}
		],

		outputTitles:null,

		outputKeys:null,
		output: null
	},{
		name: "删除对话",
		code: "DeleteUserChat",
		status: SERVICE_STATUS.DONE,
		path: "/{token}/Chat/{friendId}",
		method: REQUEST_METHOD.DELETE,
		category: "消息",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "TOKEN",
				key: "token",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: ""
			},
			{
				name: "对方id",
				key: "friendId",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: ""
			}
		],

		outputTitles:null,

		outputKeys:null,
		output: null
	},
	{
		name: "设置推送平台信息",
		code: "SetPushInfo",
		status: SERVICE_STATUS.DONE,
		path: "/{token}/PushInfo",
		method: REQUEST_METHOD.POST,
		category: "消息",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "token",
				key: "token",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: ""
			},
			{
				name: "推送平台ID",
				key: "pushUserId",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			}
		],
		outputTitles:null,
		outputKeys:null,
		output: null
	}
];

/**
 * 生成表数据
 */
function mkListData(data, keys)
{
	if (!data)
	{
		return null;
	}

	var rowArray = [];
	$.each(data, function(i, v)
	{
		var cellArray = [];
		$.each(keys, function(ii, vv)
		{
			if (typeof (v[vv]) == "object")
			{
				v[vv] = JSON.stringify(v[vv]);
			}

			cellArray.push(v[vv]);
		});
		rowArray.push(cellArray);
	});

	return rowArray;
}

/**
 * 生成一维数据
 */
function mkFlatData(data, keys)
{
	if (!data)
	{
		return null;
	}

	var cellArray = [];
	$.each(keys, function(i, v)
	{
		if (typeof (data[v]) == "object")
		{
			data[v] = JSON.stringify(data[v]);
		}
		cellArray.push(data[v]);
	});

	return cellArray;
}

/**
 * 生并显示表
 * 
 * @param values
 */
function mkList(ths, keys, values)
{
	$("#list").stop(true, true).hide();
	$("#list table thead tr, #list tbody").empty();
	$.each(ths, function(i, v)
	{
		$("#list table thead tr").append($("<th>" + v + "</th>"));
	});

	if (values && values.length > 0)
	{
		$.each(values, function(i, v)
		{
			var tr = $("<tr>");
			$.each(v, function(ii, vv)
			{
				tr.append("<td>" + vv + "</td>");
			});
			$("#list table tbody").append(tr);
		});
	}
	else
	{
		$("#list table tbody").append("<td colspan='" + ths.length + "'><h4 class='text-center'><i class='glyphicon glyphicon-ban-circle'></i> 无数据<h4></td>");
	}

	$("#list").slideDown().siblings().slideUp();
}

/**
 * 生并显示扁平数据
 * 
 * @param titles 标题
 * @param values 值
 */
function mkFlat(titles, keys, values)
{
	$("#flat").stop(true, true).hide();
	$("#flat table tbody").empty();

	$.each(titles, function(i, v)
	{
		var tr = $("<tr>");
		tr.append("<th class='success'>" + v + "</th>");
		if (values)
		{
			tr.append("<td>" + values[i] + "</td>");
		}
		else
		{
			tr.append("<td><i class='glyphicon glyphicon-ban-circle'></i> [无数据]</td>");
		}
		$("#flat table tbody").append(tr);
	});

	$("#flat").slideDown().siblings().slideUp();
}

/**
 * 生并显示纯数据
 * 
 * @param values
 */
function mkPlain(title, value)
{
	$("#plain").stop(true, true).hide();
	$("#plain").empty();
	$("#plain").html("<div class='panel panel-default'><div class='panel-heading'>" + title + "</div><div class='panel-body'>" + value + "</div></div>");
	$("#plain").slideDown().siblings().slideUp();
}

/**
 * 切换交易
 */
function switchService()
{
	var config = ServiceConfig[$("#serviceSelector").val()];

	var path = config.path;
	$("#paramList").empty();

	var newPath = path;
	$("#form input[name=path]").val(newPath);
	if (config.method == REQUEST_METHOD.PUT)
	{
		$("#form input[name=_method]").val("PUT");
		$("#form").attr("method", "POST");
	}
	else
	{
		$("#form").attr("method", config.method);
		$("#form input[name=_method]").val("");
	}

	$("#pathDisplay").html("<span class='label label-success' style='padding-bottom: .2em;'>" + config.method + "</span> " + newPath);
	$("#serviceStatus").html(config.status);

	$("#inputParamTable tbody").empty();

	var pathReplacement = {};
	if (config.param)
	{
		/*
		 * 入参
		 */
		var jsonDesc = [];
		$.each(config.param, function(i, v)
		{
			jsonDesc.push(
			{
				key: v.key,
				name: v.name
			});

			var input = null;
			var addon = $("<span class='input-group-addon'>");

			switch (v.controlType)
			{
				case CONTROL_TYPE.NUMBER:
					input = $("<input class='form-control' type='number' name='" + v.key + "' " + (v.readOnly? "readonly": "") + " />");
					break;
				case CONTROL_TYPE.TEXT:
					input = $("<input class='form-control' type='text' name='" + v.key + "' " + (v.readOnly? "readonly": "") + "  />");
					break;
				case CONTROL_TYPE.FILE:
					input = $("<input class='form-control' type='file' name='" + v.key + "' " + (v.readOnly? "readonly": "") + "  />");
					break;
				case CONTROL_TYPE.TEL:
					input = $("<input class='form-control' type='telephone' name='" + v.key + "' " + (v.readOnly? "readonly": "") + "  />");
					break;
				case CONTROL_TYPE.PASSWORD:
					input = $("<input class='form-control' type='password' name='" + v.key + "' " + (v.readOnly? "readonly": "") + "  />");
					break;
				default:
					input = $("<input class='form-control' type='text' name='" + v.key + "' data-required='" + v.required + "' " + (v.readOnly? "readonly": "") + "  />");
			}

			input.blur(function()
			{
				flushRequestJson();
			});

			if (v.note)
			{
				addon.append($("<i class='glyphicon glyphicon-info-sign' title='" + v.note + "'></i>").tooltip(
				{
					container: "body"
				}));
				addon.append("&nbsp;");
			}

			input.val(v.testValue);

			if (!v.pathVar && !v.urlParam && !v.bodyEntity)
			{
				addon.append($("<i class='glyphicon glyphicon-edit' title='字段参数'></i>").tooltip(
				{
					container: "body"
				}));
				addon.append("&nbsp;");
				input.data("joinJson", true);
			}

			if (v.pathVar || v.urlParam)
			{
				if (v.pathVar)
				{
					addon.append($("<i class='glyphicon glyphicon-link' title='路径参数'></i>").tooltip(
					{
						container: "body"
					}));
					addon.append("&nbsp;");
				}
				if (v.urlParam)
				{
					addon.append($("<i class='glyphicon glyphicon-search' title='查询参数'></i>").tooltip(
					{
						container: "body"
					}));
					addon.append("&nbsp;");
				}

				pathReplacement[v.pathVar? "{" + v.key + "}": "[" + v.key + "]"] = "<strong class='" + (v.pathVar? "pathVar": "urlParam") + "' data-key='" + v.key + "' data-name='" + v.name + "' style='color: rgb(199, 140, 65)'><u>" + v.testValue + "</u></strong>";
				input.unbind("change").change(function()
				{
					pathReplacement[v.pathVar? "{" + v.key + "}": "[" + v.key + "]"] = "<strong class='" + (v.pathVar? "pathVar": "urlParam") + "' data-key='" + v.key + "' data-name='" + v.name + "' style='color: rgb(199, 140, 65)'><u>" + input.val() + "</u></strong>";
					flushPath(config.method, config.path, pathReplacement);
				});
			}

			if (v.bodyEntity)
			{
				addon.append($("<i class='glyphicon glyphicon-file' title='实体参数'></i>").tooltip(
				{
					container: "body"
				}));
				addon.append("&nbsp;");
			}

			addon.append($("<i class='glyphicon glyphicon-map-marker' title='" + v.key + "'></i>").tooltip(
			{
				container: "body"
			}));

			var formGroup = $("<div class='form-group " + (v.fuck? "has-warning": "") + "'></div>");
			formGroup.append($("<label class='col-lg-4 control-label'>" + (v.required? "* ": "") + v.name + "</label>"));
			formGroup.append($("<div class='col-lg-7 input-group'></div>").append(input).append(addon));

			$("#paramList").append(formGroup);

			/*
			 * 文档
			 */
			var tr = $("<tr>");
			tr.append("<th>" + (1 + i) + "</th>");
			tr.append("<td>" + v.key + "</td>");
			tr.append("<td>" + v.name + "</td>");
			tr.append("<td>" + (v.type? v.type: "") + "</td>");
			tr.append("<td>" + (v.length? v.length: "") + "</td>");
			tr.append("<td>" + (v.required? "√": "") + "</td>");
			tr.append("<td>" + (v.pathVar? "√": "") + "</td>");
			tr.append("<td>" + (v.urlParam? "√": "") + "</td>");
			tr.append("<td>" + (v.bodyEntity? "√": "") + "</td>");
			tr.append("<td>" + (v.defautValue? v.defautValue: "") + "</td>");
			tr.append("<td>" + (v.note? v.note: "") + "</td>");
			$("#inputParamTable tbody").append(tr);

		});

		$("#form input[name=path]").val(newPath);
		$("#pathDisplay").html("<span class='label label-success' style='padding-bottom: .2em'>" + config.method + "</span> " + newPath);

		$("#jsonRequest").data("jsonDesc", jsonDesc);
		// JSON
		flushRequestJson();
	}
	else
	{
		$("#paramList").html("该交易无参数");
	}

	// 出参文档
	if (config.outputKeys)
	{
		$("#outputParamTable tbody").empty();
		for (var i = 0; i < config.outputKeys.length; i++)
		{
			var tr = $("<tr>");
			tr.append("<th>" + (1 + i) + "</th>");
			tr.append("<td>" + config.outputKeys[i] + "</td>");
			tr.append("<td>" + config.outputTitles[i] + "</td>");
			$("#outputParamTable tbody").append(tr);
		}
	}

	$("#execute1").unbind().click(function()
	{
		switch (config.connectType)
		{
			case CONNECT_TYPE.AJAX:
				sendAjax();
				break;
			case CONNECT_TYPE.FORM:
				sendForm();
				break;
			case CONNECT_TYPE.DOWNLOAD:
				sendDownload();
				break;
			case CONNECT_TYPE.IMAGE:
				showImage();
				break
			case CONNECT_TYPE.VIDEO:
				showVideo();
				break
			case CONNECT_TYPE.AUDIO:
				showAudio();
				break;
			case CONNECT_TYPE.HTML:
				showHTML();
				break;
			default:
				sendAjax();
		}
	});

	flushPath(config.method, config.path, pathReplacement);
}

function flushPath(method, path, pathReplacement)
{
	var newPath = path;
	var stylePath = path;
	$.each(pathReplacement, function(k, v)
	{
		newPath = newPath.replace(k, $(v).text());
		stylePath = stylePath.replace(k, v);
	});

	$("#form input[name=path]").val(newPath);
	$("#pathDisplay").html("<span class='label label-success' style='padding-bottom: .2em'>" + method + "</span> " + stylePath);
	$("#pathDisplay").find("strong").each(function()
	{
		if ($(this).hasClass("pathVar"))
		{
			$(this).tooltip(
			{
				container: "body",
				html: true,
				title: "<i class='glyphicon glyphicon-link'></i> | " + $(this).attr("data-name") + " - " + $(this).attr("data-key")
			});
		}
		else
		{
			$(this).tooltip(
			{
				container: "body",
				html: true,
				title: "<i class='glyphicon glyphicon-search'></i> | " + $(this).attr("data-name") + " - " + $(this).attr("data-key")
			});
		}
	})
}

/**
 * 刷新请求JSON
 */
function flushRequestJson()
{
	var json = {};
	$("#paramList :input").each(function()
	{
		if ($(this).data("joinJson"))
		{
			json[$(this).attr("name")] = $(this).val();
		}
	});

	if ($.isEmptyObject(json))
	{
		$("#jsonRequest").html("<center>无JSON入参</center>");
		return;
	}

	$("#jsonRequest").html(jsonFormat(json));
	var jsonDesc = $("#jsonRequest").data("jsonDesc");
	$("#jsonRequest").find(".PropertyName").each(function()
	{
		var propName = $(this);
		if (jsonDesc)
		{
			$.each(jsonDesc, function(i, v)
			{
				if (propName.text() == ("\"" + v.key + "\""))
				{
					propName.tooltip(
					{
						placement: "right",
						container: "body",
						title: v.name
					});
				}
			});
		}
	});
}

/**
 * 刷新响应JSON
 */
function flushResponseJson()
{
	// TODO
}

/**
 * JSON格式化
 */
function jsonFormat(json, param)
{
	SetTab();
	window.IsCollapsible = false;
	var html = $("<pre>").html(ProcessObject(json, 0, false, false, false));

	return html;
}

function sendForm(){
//	debugger;
	var $form = $("#form");
	$form.attr("action","/beautyfamily"+$("#form input[name=path]").val()).attr("enctype","multipart/form-data");
	document.getElementById("form").submit();
//	$form.submit();
}

/**
 * 发送Ajax请求
 */
function sendAjax()
{
	$("#requireCheck").html("").hide();

	var config = ServiceConfig[$("#serviceSelector").val()];

	var path = $("#form input[name=path]").val();
	var method = config.method;
	var queryCond = {};
	if (config.param)
	{
		var pass = true;
		var name = "";
		$.each(config.param, function(i, v)
		{
			if (!pass)
			{
				return;
			}

			var value = $("#paramList input[name=" + v.key + "]").val();
			if (v.required && pass && !value)
			{
				pass = false;
				name = v.name;
			}

			if (v.pathVar || v.urlParam)
			{
				return;
			}

			if (v.bodyEntity)
			{
				queryCond = $("#paramList input[name=" + v.key + "]").val();
				return;
			}

			queryCond[v.key] = $("#paramList input[name=" + v.key + "]").val();

			queryCond[v.key] = queryCond[v.key] == ""? null: queryCond[v.key];

		});
		if (!pass)
		{
			$("#requireCheck").html("<i class='glyphicon glyphicon-minus-sign'></i> <strong>" + name + "</strong>不能为空").show();
			return;
		}
	}

	$("#loading").siblings().hide();
	$("#loading .progress-bar").removeClass("progress-bar-warning").removeClass("progress-bar-danger").addClass("progress-bar-success").parent().addClass("active");
	$("#loading:hidden").fadeIn();

	var sendData = queryCond;
	if ($.isPlainObject(queryCond) && !$.isEmptyObject(queryCond))
	{
		sendData = JSON.stringify(queryCond);
	}

	$.ajax(
	{
		type: method,
		url: EnvConfig.ROOT + path,
		contentType: "text/plain; charset=UTF-8",
		dataType: "json",
		data: sendData,
		success: function(response)
		{

			/*
			 * JSON
			 */
			$("#jsonResponse").html(jsonFormat(response));

			if (response.code == RESPONSE_CODE.Success)
			{
				if (!config.output)
				{
					mkPlain("执行结果", "<div class='alert alert-success' role='alert'>" + response.message + "</div>");
				}
				else
				{
					config.output(response.data);
				}
			}
			else
			{
				$("#bizErrorModal h5").text(response.message);
				$("#bizErrorModal").modal("show");
				$("#loading .progress-bar").removeClass("progress-bar-success").removeClass("progress-bar-danger").addClass("progress-bar-warning").parent().removeClass("active");
				$("#loading:hidden").fadeIn();
			}
		},
		error: function(xhr, status, msg)
		{
			$("#ajaxErrorModal h5").text("status" + msg);
			$("#ajaxErrorModal").modal("show");
			$("#loading .progress-bar").removeClass("progress-bar-success").removeClass("progress-bar-warning").addClass("progress-bar-danger").parent().removeClass("active");
			$("#loading:hidden").fadeIn();
		}
	});
}

/**
 * 发送下载请求
 */
function sendDownload()
{
	$("#requireCheck").html("").hide();

	var config = ServiceConfig[$("#serviceSelector").val()];

	if (config.param)
	{
		var pass = true;
		var name = "";
		$.each(config.param, function(i, v)
		{
			if (!pass)
			{
				return;
			}

			var value = $("#paramList input[name=" + v.key + "]").val();
			if (v.required && pass && !value)
			{
				pass = false;
				name = v.name;
			}

			if (v.pathVar)
			{
				return;
			}
		});
		if (!pass)
		{
			$("#requireCheck").html("<i class='glyphicon glyphicon-minus-sign'></i> <strong>" + name + "</strong>不能为空").show();
			return;
		}
	}

	$("#form").attr("action", $("#form input[name=path]").val());
	$("#form").submit();

	// 这句才起作用，但只能用来GET
	window.open(EnvConfig.ROOT + $("#form input[name=path]").val(), "_target");
}

/**
 * 显示图片
 */
function showImage()
{
	var src = EnvConfig.ROOT + $("#form input[name=path]").val();

	$("#requireCheck").html("").hide();

	$("#image").stop(true, true).hide();
	$("#image").empty();
	$("#image").html("<div class='panel panel-default'><div class='panel-heading'>图片</div><div class='panel-body'><img style='width: 100%' src='" + src + "'></div></div>");
	$("#image").slideDown().siblings().slideUp();
}

/**
 * 显示视频
 */
function showVideo()
{
	var src = EnvConfig.ROOT + $("#form input[name=path]").val();

	$("#requireCheck").html("").hide();

	$("#video").stop(true, true).hide();
	$("#video").empty();
	$("#video").html("<div class='panel panel-default'><div class='panel-heading'>视频</div><div class='panel-body'><video style='width: 100%' preload='auto' controls src='" + src + "'> </video></div></div>");
	$("#video").slideDown().siblings().slideUp();
}

/**
 * 显示音频
 */
function showAudio()
{
	var src = EnvConfig.ROOT + $("#form input[name=path]").val();

	$("#requireCheck").html("").hide();

	$("#audio").stop(true, true).hide();
	$("#audio").empty();
	$("#audio").html("<div class='panel panel-default'><div class='panel-heading'>音频</div><div class='panel-body'><audio style='width: 100%' preload='auto' controls src='" + src + "'> </audio></div></div>");
	$("#audio").slideDown().siblings().slideUp();
}

/**
 * 显示HTML
 */
function showHTML()
{
	var src = EnvConfig.ROOT + $("#form input[name=path]").val();

	$("#requireCheck").html("").hide();
	$("#html").stop(true, true).hide();
	$("#html").empty();
	$("#html").html("<div class='panel panel-default'><div class='panel-heading'>HTML</div><div class='panel-body'>加载中...</div></div>");
	$("#html .panel-body").load(src);
	$("#html").slideDown().siblings().slideUp();
}

$(document).ready(function()
{
	$("#result > *").hide();
	$("#requireCheck").hide();

	$.each(ServiceConfig, function(i, v)
	{
		var category = $("#serviceSelector optgroup[label='" + v.category + "']");
		if (category.size() == 0)
		{
			category = $("<optgroup label='" + v.category + "'>");
			$("#serviceSelector").append(category);
		}

		category.append($("<option value='" + i + "' data-subtext='" + v.code + "'>" + v.name + "</option>"));
	});
	$("#form").submit(function()
	{
		return false;
	});
	$("#serviceSelector").change(switchService).val(
	[
		0
	]).selectpicker(
	{
		height: "3.6em"
	});
	switchService();
});