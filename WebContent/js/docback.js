/**
 * 同行网专用接口文档和交易测试工具配置文件，尚未开源发布，请勿随意分发
 * 
 * @author 宋翔 oraplen@gmail.com
 * @date 2015-1-16 01:20:55
 */
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
		name: "获取数据字典",
		code: "getDict",
		status: SERVICE_STATUS.DONE,
		path: "/Dict/{type}?parent=[parent]&language=[language]",
		method: REQUEST_METHOD.GET,
		category: "通用",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "类型",
				key: "type",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "输入00，可查询数据字典类型代码",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: "00"
			},
			{
				name: "父级",
				key: "parent",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "该字典的父级ID",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: false,
				testValue: ""
			},
			{
				name: "语言",
				key: "language",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "zh-CN"
			}
		],

		outputTitles:
		[
			"ID", "码值", "显示值", "标记"
		],

		outputKeys:
		[

			"id", "code", "value", "tag"
		],
		output: function(data)
		{
			var titles =
			[
				"ID", "码值", "显示值", "标记"
			];
			var keys =
			[
				"id", "code", "value", "tag"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},
	{
		name: "检查App版本",
		code: "checkAppversion",
		status: SERVICE_STATUS.DONE,
		path: "/Appversion/{app}/LATEST={versionNo}",
		method: REQUEST_METHOD.GET,
		category: "关于",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "客户端应用编码",
				key: "app",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "@数据字典.TYPE=01",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: "000001"
			},
			{
				name: "当前版本号",
				key: "versionNo",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: ""
			}
		],
		outputTitles:
		[
			"最新版本号", "发布日志", "下载链接", "创建时间"
		],
		outputKeys:
		[

			"versionNo", "releaseNote", "url", "createTime"
		],
		output: function(data)
		{
			var titles =
			[
				"最新版本号", "发布日志", "下载链接", "创建时间"
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
		name: "下载App安装包",
		code: "downloadApp",
		status: SERVICE_STATUS.DONE,
		path: "/Appversion/{app}/{versionNo}",
		method: REQUEST_METHOD.GET,
		category: "关于",
		connectType: CONNECT_TYPE.DOWNLOAD,
		param:
		[
			{
				name: "客户端应用编码",
				key: "app",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "@数据字典.TYPE=01",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: "000001"
			},
			{
				name: "当前版本号",
				key: "versionNo",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: ""
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},

	{
		name: "提交意见反馈",
		code: "submitFeedback",
		status: SERVICE_STATUS.DONE,
		path: "/Feedback/{userSeq}",
		method: REQUEST_METHOD.POST,
		category: "关于",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
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
				name: "反馈内容",
				key: "feedback",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: false,
				bodyEntity: true,
				required: true,
				testValue: "我对你的App有点意见，但我说不出是啥意见"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "获取用户注册短信验证码",
		code: "getRegistVerifyCode",
		status: SERVICE_STATUS.DONE,
		path: "/Regist/{phone}",
		method: REQUEST_METHOD.POST,
		category: "用户权限相关",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "手机号",
				key: "phone",
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
		name: "验证用户注册短信验证码",
		code: "validateVerifyCode",
		status: SERVICE_STATUS.DONE,
		path: "/Regist/{phone}/ValidateVerifyCode",
		method: REQUEST_METHOD.POST,
		category: "用户权限相关",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "手机号",
				key: "phone",
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
				name: "验证码",
				key: "verifyCode",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				bodyEntity: true,
				required: true,
				testValue: ""
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "用户注册",
		code: "regist",
		status: SERVICE_STATUS.DONE,
		path: "/Regist/{phone}",
		method: REQUEST_METHOD.PUT,
		category: "用户权限相关",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "手机号（URL变量）",
				key: "phone",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEL,
				pathVar: true,
				required: true,
				testValue: "18610721773"
			},
			{
				name: "手机号",
				key: "phone",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEL,
				pathVar: false,
				required: true,
				testValue: "18610721773"
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
				testValue: ""
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
				testValue: ""
			},
			{
				name: "用户名（Email）",
				key: "username",
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
				name: "年龄范围",
				key: "ageScope",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "@数据字典.TYPE=02",
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
				note: "@数据字典.TYPE=02",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "执业医师编码",
				key: "certNo",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: false,
				testValue: ""
			},
			{
				name: "省/自治区/直辖市",
				key: "province",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "@数据字典.TYPE=04",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "市/直辖市区",
				key: "city",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "@数据字典.TYPE=05",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "区/县",
				key: "district",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "@数据字典.TYPE=06",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "单位",
				key: "institution",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "@数据字典.TYPE=07",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "科室",
				key: "dept",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "@数据字典.TYPE=08",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "职称A",
				key: "jobTitleA",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "@数据字典.TYPE=09 | TAG=A",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "职称B",
				key: "jobTitleB",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "@数据字典.TYPE=09 | TAG=B",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "学位",
				key: "degree",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "@数据字典.TYPE=10",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "学术与职业生涯规划需求",
				key: "career",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "@数据字典.TYPE=11",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "学术与职业生涯规划需求（自定义）",
				key: "careerCustom",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "#@数据字典.TYPE=11 | TAG=OTHER",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "学术领域兴趣点",
				key: "poi",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "@数据字典.TYPE=12",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "学术领域兴趣点（自定义）",
				key: "poiCustom",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "#@数据字典.TYPE=12 | TAG=OTHER",
				controlType: CONTROL_TYPE.TEXT,
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
		name: "用户登录",
		code: "login",
		status: SERVICE_STATUS.DONE,
		path: "/Login",
		method: REQUEST_METHOD.POST,
		category: "用户权限相关",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户名",
				key: "username",
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
				name: "密码",
				key: "password",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.PASSWORD,
				pathVar: false,
				required: true,
				testValue: ""
			}
		],
		outputTitles:
		[
			"用户名", "姓名", "用户序号", "是否认证用户", "是否砖家", "是否认证机构"
		],
		outputKeys:
		[

			"username", "name", "seq", "isV", "isExpert", "isInstitutionV"
		],
		output: function(data)
		{
			var titles =
			[
				"用户名", "姓名", "用户序号", "是否认证用户", "是否砖家", "是否认证机构"
			];
			var keys =
			[
				"username", "name", "seq", "isV", "isExpert", "isInstitutionV"
			];
			var values = mkFlatData(data, keys);
			mkFlat(titles, keys, values);
		}
	},
	{
		name: "发起重置密码请求",
		code: "passwordReset",
		status: SERVICE_STATUS.DONE,
		path: "/User/PasswordReset",
		method: REQUEST_METHOD.POST,
		category: "用户权限相关",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户名",
				key: "username",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				bodyEntity: true,
				required: true,
				testValue: ""
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: function(data)
		{
			mkPlain("提示", data);
		}
	},
	{
		name: "修改密码",
		code: "modifyUserPassword",
		status: SERVICE_STATUS.DONE,
		path: "/User/{seq}/Password?verifyCode=[verifyCode]",
		method: REQUEST_METHOD.PUT,
		category: "用户权限相关",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户SEQ",
				key: "seq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				bodyEntity: false,
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
				controlType: CONTROL_TYPE.TEL,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: ""
			},
			{
				name: "新密码",
				key: "password",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				bodyEntity: true,
				required: true,
				testValue: ""
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "获取修改手机号验证码",
		code: "getPhoneModificationVerifyCode",
		status: SERVICE_STATUS.DONE,
		path: "/User/{seq}/PhoneModification",
		method: REQUEST_METHOD.POST,
		category: "用户权限相关",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户SEQ",
				key: "seq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				bodyEntity: false,
				required: true,
				testValue: ""
			},
			{
				name: "新手机号",
				key: "phone",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEL,
				pathVar: false,
				bodyEntity: true,
				required: true,
				testValue: ""
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: function(data)
		{
			mkPlain("提示", "短信已发送，请注意查收");
		}
	},
	{
		name: "修改手机号",
		code: "modifyUserPhone",
		status: SERVICE_STATUS.DONE,
		path: "/User/{seq}/Phone?verifyCode=[verifyCode]",
		method: REQUEST_METHOD.PUT,
		category: "用户权限相关",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户SEQ",
				key: "seq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				bodyEntity: false,
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
				urlParam: true,
				bodyEntity: false,
				required: true,
				testValue: ""
			},
			{
				name: "手机号",
				key: "phone",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEL,
				pathVar: false,
				bodyEntity: true,
				required: true,
				testValue: ""
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "获取用户信息",
		code: "getUserInfo",
		status: SERVICE_STATUS.DONE,
		path: "/User/Info/{seq}",
		method: REQUEST_METHOD.GET,
		category: "用户权限相关",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户SEQ",
				key: "seq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				bodyEntity: false,
				required: true,
				testValue: ""
			}
		],
		outputTitles:
		[
			"手机号", "年龄范围", "性别", "执业医师编码", "省/自治区/直辖市", "市/直辖市区", "区/县", "单位", "科室", "职称A", "职称B", "学位", "学术与职业生涯规划需求", "学术与职业生涯规划需求（自定义）", "学术领域兴趣点", "学术领域兴趣点（自定义）"
		],
		outputKeys:
		[
			"phone", "ageScope", "gender", "certNo", "province", "city", "district", "institution", "dept", "jobTitleA", "jobTitleB", "degree", "career", "careerCustom", "poi", "poiCustom"
		],
		output: function(data)
		{
			var titles =
			[
				"手机号", "年龄范围", "性别", "执业医师编码", "省/自治区/直辖市", "市/直辖市区", "区/县", "单位", "科室", "职称A", "职称B", "学位", "学术与职业生涯规划需求", "学术与职业生涯规划需求（自定义）", "学术领域兴趣点", "学术领域兴趣点（自定义）"
			];
			var keys =
			[
				"phone", "ageScope", "gender", "certNo", "province", "city", "district", "institution", "dept", "jobTitleA", "jobTitleB", "degree", "career", "careerCustom", "poi", "poiCustom"
			];
			var values = mkFlatData(data, keys);
			mkFlat(titles, keys, values);
		}
	},
	{
		name: "修改用户信息",
		code: "fillUserInfo",
		status: SERVICE_STATUS.DONE,
		path: "/User/Info/{seq}",
		method: REQUEST_METHOD.PUT,
		category: "用户权限相关",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户SEQ",
				key: "seq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				bodyEntity: false,
				required: true,
				testValue: ""
			},
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
				name: "年龄范围",
				key: "ageScope",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "@数据字典.TYPE=02",
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
				note: "@数据字典.TYPE=02",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "执业医师编码",
				key: "certNo",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: false,
				testValue: ""
			},
			{
				name: "省/自治区/直辖市",
				key: "province",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "@数据字典.TYPE=04",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "市/直辖市区",
				key: "city",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "@数据字典.TYPE=05",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "区/县",
				key: "district",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "@数据字典.TYPE=06",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "单位",
				key: "institution",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "@数据字典.TYPE=07",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "科室",
				key: "dept",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "@数据字典.TYPE=08",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "职称A",
				key: "jobTitleA",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "@数据字典.TYPE=09 | TAG=A",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "职称B",
				key: "jobTitleB",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "@数据字典.TYPE=09 | TAG=B",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "学位",
				key: "degree",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "@数据字典.TYPE=10",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "学术与职业生涯规划需求",
				key: "career",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "@数据字典.TYPE=11",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "学术与职业生涯规划需求（自定义）",
				key: "careerCustom",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "#@数据字典.TYPE=11 | TAG=OTHER",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "学术领域兴趣点",
				key: "poi",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "@数据字典.TYPE=12",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: ""
			},
			{
				name: "学术领域兴趣点（自定义）",
				key: "poiCustom",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "#@数据字典.TYPE=12 | TAG=OTHER",
				controlType: CONTROL_TYPE.TEXT,
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
		name: "下载头像",
		code: "downloadUserPhoto",
		status: SERVICE_STATUS.DONE,
		path: "/User/Info/{seq}/Photo",
		method: REQUEST_METHOD.GET,
		category: "用户权限相关",
		connectType: CONNECT_TYPE.IMAGE,
		param:
		[
			{
				name: "用户序号",
				key: "seq",
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
	// TODO 修改头像
	{
		name: "获取会议列表",
		code: "getMeetingList",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/Meeting?topic=[topic]&date=[date]&location=[location]&speaker=[speaker]",
		method: REQUEST_METHOD.GET,
		category: "会议",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			},
			{
				name: "会议名称",
				key: "topic",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: false,
				testValue: ""
			},
			{
				name: "日期",
				key: "date",
				type: PARAM_TYPE.DATE,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: false,
				testValue: "2015-01-24"
			},
			{
				name: "地点",
				key: "location",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: false,
				testValue: ""
			},
			{
				name: "主持人",
				key: "speaker",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: false,
				testValue: ""
			}
		],
		outputTitles:
		[
			"序列", "主题", "时间", "状态"
		],
		outputKeys:
		[

			"seq", "topic", "time", "status"
		],
		output: function(data)
		{
			var titles =
			[
				"序列", "主题", "时间", "状态"
			];
			var keys =
			[
				"seq", "topic", "time", "status"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},

	{
		name: "获取会议详细",
		code: "getMeeting",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/Meeting/{seq}",
		method: REQUEST_METHOD.GET,
		category: "会议",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "会议序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			}
		],

		outputTitles:
		[
			"序号", "ID", "主题", "时间", "地点", "演讲者", "日程", "会前问卷", "会后问卷", "资料列表", "问题列表", "赞同数", "是否已赞同"
		],

		outputKeys:
		[
			"seq", "id", "topic", "time", "location", "speaker", "schedule", "preSurvey", "afterSurvey", "attachList", "questionList", "likeCount", "beenLike"
		],
		output: function(data)
		{
			var titles =
			[
				"序号", "ID", "主题", "时间", "地点", "演讲者", "日程", "会前问卷", "会后问卷", "资料列表", "问题列表", "赞同数", "是否已赞同"
			];
			var keys =
			[
				"seq", "id", "topic", "time", "location", "speaker", "schedule", "preSurvey", "afterSurvey", "attachList", "questionList", "likeCount", "beenLike"
			];
			var values = mkFlatData(data, keys);
			mkFlat(titles, keys, values);
		}
	},
	{
		name: "会议报名",
		code: "signupMeeting",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/Meeting/{seq}/Signup",
		method: REQUEST_METHOD.PUT,
		category: "会议",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "会议序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "会议签到",
		code: "signInMeeting",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/Meeting/{seq}/Signin",
		method: REQUEST_METHOD.PUT,
		category: "会议",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "会议序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "下载会议附件",
		code: "downloadMeetingAttach",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/Meeting/{seq}/Attach/{attachSeq}",
		method: REQUEST_METHOD.GET,
		category: "会议",
		connectType: CONNECT_TYPE.DOWNLOAD,
		param:
		[
			{
				name: "会议序号",
				key: "seq",
				type: PARAM_TYPE.INTEGER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: true,
				required: true,
				testValue: ""
			},
			{
				name: "附件序号",
				key: "attachSeq",
				type: PARAM_TYPE.INTEGER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: true,
				required: true,
				testValue: ""
			},
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "提交会议提问",
		code: "postMeetingQuestion",
		status: SERVICE_STATUS.POST,
		path: "/{userSeq}/Meeting/{seq}/Question",
		method: REQUEST_METHOD.POST,
		category: "会议",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "会议序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			},
			{
				name: "演讲者",
				key: "speaker",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: false,
				required: true,
				bodyEntity: false,
				testValue: "那谁谁"
			},
			{
				name: "问题",
				key: "question",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: false,
				required: true,
				bodyEntity: false,
				testValue: "我有一个能难得住你的问题"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "提交会前问卷",
		code: "submitMeetingPreSurvey",
		status: SERVICE_STATUS.POST,
		path: "/{userSeq}/Meeting/{seq}/PreSurvey",
		method: REQUEST_METHOD.POST,
		category: "会议",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "会议序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			},
			{
				name: "问卷回答",
				key: "answer",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: false,
				bodyEntity: true,
				required: true,
				testValue: ""
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "提交会后问卷",
		code: "submitMeetingAfterSurvey",
		status: SERVICE_STATUS.POST,
		path: "/{userSeq}/Meeting/{seq}/AfterSurvey",
		method: REQUEST_METHOD.POST,
		category: "会议",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "会议序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			},
			{
				name: "问卷回答",
				key: "answer",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: false,
				bodyEntity: true,
				required: true,
				testValue: ""
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "赞同会议",
		code: "likeMeeting",
		status: SERVICE_STATUS.POST,
		path: "/{userSeq}/Meeting/{seq}/Like",
		method: REQUEST_METHOD.POST,
		category: "会议",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "会议序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "取消会议赞同",
		code: "likeMeeting",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/Meeting/{seq}/Like",
		method: REQUEST_METHOD.DELETE,
		category: "会议",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "会议序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "获取会后问卷回答状态",
		code: "getUserMeetingStatusVO",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/Meeting/Status",
		method: REQUEST_METHOD.GET,
		category: "会议",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			}
		],

		outputTitles:
		[
			"序号", "用户SEQ", "会议SEQ", "状态"
		],

		outputKeys:
		[
			"seq", "userSeq", "meetingSeq", "status"
		],
		output: function(data)
		{
			var titles =
			[
				"序号", "用户SEQ", "会议SEQ", "状态"
			];
			var keys =
			[
				"seq", "userSeq", "meetingSeq", "status"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},
	{
		name: "获取今日热点列表",
		code: "getNews",
		status: SERVICE_STATUS.DONE,
		path: "/News",
		method: REQUEST_METHOD.GET,
		category: "今日热点",
		connectType: CONNECT_TYPE.AJAX,
		param: null,
		outputTitles:
		[
			"序列", "标题"
		],
		outputKeys:
		[

			"seq", "title"
		],
		output: function(data)
		{
			var titles =
			[
				"序列", "标题"
			];
			var keys =
			[
				"seq", "title"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},
	{
		name: "下载今日热点列表图片",
		code: "downloadNewsImage",
		status: SERVICE_STATUS.DONE,
		path: "/News/{seq}/ListImage",
		method: REQUEST_METHOD.GET,
		category: "今日热点",
		connectType: CONNECT_TYPE.IMAGE,
		param:
		[
			{
				name: "今日热点序号",
				key: "seq",
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
		name: "获取今日热点",
		code: "getNews",
		status: SERVICE_STATUS.DONE,
		path: "/News/{seq}?userSeq=[userSeq]",
		method: REQUEST_METHOD.GET,
		category: "今日热点",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "今日热点序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],

		outputTitles:
		[
			"序号", "标题", "内容", "已赞同", "赞同数", "创建时间", "评论列表", "收藏SEQ(未收藏为null)"
		],
		outputKeys:
		[
			"seq", "title", "content", "beenLike", "likeCount", "createTime", "commentList", "favoriteSeq"
		],
		output: function(data)
		{
			var titles =
			[
				"序号", "标题", "内容", "已赞同", "赞同数", "创建时间", "评论列表", "收藏SEQ(未收藏为null)"
			];
			var keys =
			[
				"seq", "title", "content", "beenLike", "likeCount", "createTime", "commentList", "favoriteSeq"
			];
			var values = mkFlatData(data, keys);
			mkFlat(titles, keys, values);
		}
	},
	{
		name: "下载今日热点详细图片",
		code: "downloadNewsImage",
		status: SERVICE_STATUS.DONE,
		path: "/News/{seq}/Image",
		method: REQUEST_METHOD.GET,
		category: "今日热点",
		connectType: CONNECT_TYPE.IMAGE,
		param:
		[
			{
				name: "今日热点序号",
				key: "seq",
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
		name: "赞今日热点",
		code: "likeNews",
		status: SERVICE_STATUS.DONE,
		path: "/News/{seq}/Like?userSeq=[userSeq]",
		method: REQUEST_METHOD.POST,
		category: "今日热点",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "今日热点序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "取消今日热点赞同",
		code: "cancelLikeNews",
		status: SERVICE_STATUS.DONE,
		path: "/News/{seq}/Like?userSeq=[userSeq]",
		method: REQUEST_METHOD.DELETE,
		category: "今日热点",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "今日热点序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "评论今日热点",
		code: "commentNews",
		status: SERVICE_STATUS.DONE,
		path: "/News/{seq}/Comment?userSeq=[userSeq]",
		method: REQUEST_METHOD.POST,
		category: "今日热点",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "今日热点序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			},
			{
				name: "评论内容",
				key: "comment",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: false,
				required: true,
				bodyEntity: true,
				testValue: "评论下你试试"
			}
		],
		outputTitles:
		[
			"序号"
		],
		outputKeys:
		[
			""
		],
		output: function(data)
		{
			mkPlain("序号", data)
		}
	},
	{
		name: "回复今日热点评论",
		code: "replyNewsComment",
		status: SERVICE_STATUS.DONE,
		path: "/News/{newsSeq}/Comment/{seq}/Reply?userSeq=[userSeq]",
		method: REQUEST_METHOD.POST,
		category: "今日热点",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "今日热点序号",
				key: "newsSeq",
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
				name: "评论序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			},
			{
				name: "回复内容",
				key: "comment",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: false,
				required: true,
				bodyEntity: true,
				testValue: "回复下你试试"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "赞今日热点评论",
		code: "likeNewsComment",
		status: SERVICE_STATUS.DONE,
		path: "/News/{newsSeq}/Comment/{seq}/Like?userSeq=[userSeq]",
		method: REQUEST_METHOD.POST,
		category: "今日热点",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "今日热点序号",
				key: "newsSeq",
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
				name: "评论序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "取消今日热点评论赞同",
		code: "cancelLikeNewsComment",
		status: SERVICE_STATUS.DONE,
		path: "/News/{newsSeq}/Comment/{seq}/Like?userSeq=[userSeq]",
		method: REQUEST_METHOD.DELETE,
		category: "今日热点",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "今日热点序号",
				key: "newsSeq",
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
				name: "评论序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "删除今日热点评论",
		code: "removeNewsComment",
		status: SERVICE_STATUS.DONE,
		path: "/News/{newsSeq}/Comment/{seq}?userSeq=[userSeq]",
		method: REQUEST_METHOD.DELETE,
		category: "今日热点",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "今日热点序号",
				key: "newsSeq",
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
				name: "评论序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "获取通知数量（除同行圈）",
		code: "getUserNoticeCount",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/NoticeCount",
		method: REQUEST_METHOD.GET,
		category: "通知",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[

			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: false,
				testValue: "8"
			}
		],
		outputTitles:
		[
			"数量"
		],
		outputKeys:
		[
			""
		],
		output: function(data)
		{
			mkPlain("数量", data)
		}
	},
	{
		name: "获取通知列表（除同行圈）",
		code: "getUserNoticeList",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/Notice",
		method: REQUEST_METHOD.GET,
		category: "通知",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: false,
				testValue: "8"
			}
		],
		outputTitles:
		[
			"类型(回复、赞、评论等)", "功能模块", "条目SEQ(如文章SEQ、课件SEQ等)", "条目标题", "消息（会议中这里为会议开始时间）", "数量"
		],
		outputKeys:
		[
			"type", "module", "itemSeq", "itemTitle", "message", "count"
		],
		output: function(data)
		{
			var titles =
			[
				"类型(回复、赞、评论等)", "功能模块", "条目SEQ(如文章SEQ、课件SEQ等)", "条目标题", "消息（会议中这里为会议开始时间）", "数量"
			];
			var keys =
			[
				"type", "module", "itemSeq", "itemTitle", "message", "count"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},
	{
		name: "获取同行圈通知数量",
		code: "getUserPeercicleNoticeCount",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/PeercicleNoticeCount",
		method: REQUEST_METHOD.GET,
		category: "通知",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[

			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: false,
				testValue: "8"
			}
		],
		outputTitles:
		[
			"数量"
		],
		outputKeys:
		[
			""
		],
		output: function(data)
		{
			mkPlain("数量", data)
		}
	},
	{
		name: "获取同行圈通知列表",
		code: "getUserPeercicleNoticeList",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/PeercicleNotice",
		method: REQUEST_METHOD.GET,
		category: "通知",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: false,
				testValue: "8"
			}
		],
		outputTitles:
		[
			"类型(回复、赞、评论等)", "功能模块", "条目SEQ(如文章SEQ、课件SEQ等)", "条目标题", "消息（会议中这里为会议开始时间）", "数量"
		],
		outputKeys:
		[
			"type", "module", "itemSeq", "itemTitle", "message", "count"
		],
		output: function(data)
		{
			var titles =
			[
				"类型(回复、赞、评论等)", "功能模块", "条目SEQ(如文章SEQ、课件SEQ等)", "条目标题", "消息（会议中这里为会议开始时间）", "数量"
			];
			var keys =
			[
				"type", "module", "itemSeq", "itemTitle", "message", "count"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},
	{
		name: "通知标记为已读",
		code: "readNotice",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/Notice/{type}/{module}/{articleSeq}",
		method: REQUEST_METHOD.DELETE,
		category: "通知",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: false,
				testValue: "8"
			},
			{
				name: "模块编号",
				key: "module",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "@数据字典.TYPE=19",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: false,
				testValue: "000001"
			},
			{
				name: "通知类型",
				key: "type",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "@数据字典.TYPE=18",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: false,
				testValue: "000001"
			},
			{
				name: "文章SEQ",
				key: "articleSeq",
				type: PARAM_TYPE.INTEGER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: true,
				urlParam: false,
				required: false,
				testValue: "1"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "获取学院列表",
		code: "getCollegeList",
		status: SERVICE_STATUS.DONE,
		path: "/College?userSeq=[userSeq]",
		method: REQUEST_METHOD.GET,
		category: "中欧心血管学院",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles:
		[
			"序号", "学院名称"
		],
		outputKeys:
		[
			"seq", "name"
		],
		output: function(data)
		{

			var titles =
			[
				"序号", "学院名称", "未读课程数量"
			];
			var keys =
			[
				"seq", "name", "newCount"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},
	{
		name: "获取学院封面",
		code: "downloadCollegeCover",
		status: SERVICE_STATUS.DONE,
		path: "/College/{seq}/Cover",
		method: REQUEST_METHOD.GET,
		category: "中欧心血管学院",
		connectType: CONNECT_TYPE.IMAGE,
		param:
		[
			{
				name: "学院SEQ",
				key: "seq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: "1"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "获取学院介绍",
		code: "getCollegeIntroduce",
		status: SERVICE_STATUS.DONE,
		path: "/College/{seq}/Introduce",
		method: REQUEST_METHOD.GET,
		category: "中欧心血管学院",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "学院序号",
				key: "seq",
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
			"学院介绍"
		],
		outputKeys:
		[
			""
		],
		output: function(data)
		{
			mkPlain("学院介绍", data);
		}
	},
	{
		name: "开通学院课程",
		code: "openCollege",
		status: SERVICE_STATUS.DONE,
		path: "/College/{seq}?userSeq=[userSeq]",
		method: REQUEST_METHOD.POST,
		category: "中欧心血管学院",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "学院序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			},
			{
				name: "激活码",
				key: "activationCode",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: false,
				bodyEntity: true,
				required: true,
				testValue: "123"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "获取课程列表",
		code: "getCourseList",
		status: SERVICE_STATUS.DONE,
		path: "/College/{seq}/Course/{type}?userSeq=[userSeq]&courseSeq=[courseSeq]",
		method: REQUEST_METHOD.GET,
		category: "中欧心血管学院",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "学院序号",
				key: "seq",
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
				name: "课程类型",
				key: "type",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "000001: 线上课件; 000002: 线下课件",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: "000001"
			},
			{
				name: "课程SEQ",
				key: "courseSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "传入后列表中仅返回指定课程",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: false,
				urlParam: true,
				testValue: "1"
			},
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles:
		[
			"序号", "课件名称", "视频ID", "总长度", "进度", "浏览数", "赞同数", "是否有新回答"
		],
		outputKeys:
		[
			"seq", "name", "videoId", "length", "progress", "visitCount", "likeCount", "hasNewAnswer"
		],
		output: function(data)
		{
			var titles =
			[
				"序号", "课件名称", "视频ID", "总长度", "进度", "浏览数", "赞同数", "是否有新回答"
			];
			var keys =
			[
				"seq", "name", "videoId", "length", "progress", "visitCount", "likeCount", "hasNewAnswer"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},
	{
		name: "获取课程详细",
		code: "getCourse",
		status: SERVICE_STATUS.DONE,
		path: "/College/{collegeSeq}/Course/{type}/{seq}?userSeq=[userSeq]",
		method: REQUEST_METHOD.GET,
		category: "中欧心血管学院",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "学院序号",
				key: "collegeSeq",
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
				name: "课件类型",
				key: "type",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "000001: 线上课件; 000002: 线下课件",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: "000001"
			},
			{
				name: "课程序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles:
		[
			"课程介绍", "课前问卷", "课后问卷", "问题列表", "已赞同", "收藏SEQ(未收藏为null)", "长度", "视频ID", "更新时间"
		],
		outputKeys:
		[
			"introduce", "preSurvey", "afterSurvey", "questionList", "beenLike", "favoriteSeq", "length", "videoId", "updateTime"
		],
		output: function(data)
		{
			var titles =
			[
				"课程介绍", "课前问卷", "课后问卷", "问题列表", "已赞同", "收藏SEQ(未收藏为null)", "长度", "视频ID", "更新时间"
			];
			var keys =
			[
				"introduce", "preSurvey", "afterSurvey", "questionList", "beenLike", "favoriteSeq", "length", "videoId", "updateTime"
			];
			var values = mkFlatData(data, keys);
			mkFlat(titles, keys, values);
		}
	},

	{
		name: "获取课程封面",
		code: "downloadCourseCover",
		status: SERVICE_STATUS.DONE,
		path: "/College/{collegeSeq}/Course/{type}/{seq}/Cover?userSeq=[userSeq]",
		method: REQUEST_METHOD.GET,
		category: "中欧心血管学院",
		connectType: CONNECT_TYPE.IMAGE,
		param:
		[
			{
				name: "学院序号",
				key: "collegeSeq",
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
				name: "课件类型",
				key: "type",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "000001: 线上课件; 000002: 线下课件",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: "000001"
			},
			{
				name: "课程序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},

	{
		name: "获取课件视频",
		code: "[static]",
		status: SERVICE_STATUS.ISSUE,
		path: "/attach/course/{seq}/$COURSEWARE.mp4?userSeq=[userSeq]",
		method: REQUEST_METHOD.GET,
		category: "中欧心血管学院",
		connectType: CONNECT_TYPE.VIDEO,
		param:
		[
			{
				name: "课程序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "提交课前问卷答案",
		code: "submitCoursePreSurvey",
		status: SERVICE_STATUS.DONE,
		path: "/College/{collegeSeq}/Course/{type}/{seq}/PreSurvey?userSeq=[userSeq]",
		method: REQUEST_METHOD.POST,
		category: "中欧心血管学院",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "学院序号",
				key: "collegeSeq",
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
				name: "课件类型",
				key: "type",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "000001: 线上课件; 000002: 线下课件",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: "000001"
			},
			{
				name: "课程序号",
				key: "seq",
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
				name: "回答",
				key: "answer",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "JSON字符串列表格式（目前仅支持问答题）",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: false,
				bodyEntity: true,
				required: true,
				testValue: "[\"121\": \"323\"]"
			},
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				bodyEntity: false,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "提交课后问卷答案",
		code: "submitCourseAfterSurvey",
		status: SERVICE_STATUS.DONE,
		path: "/College/{collegeSeq}/Course/{type}/{seq}/AfterSurvey?userSeq=[userSeq]",
		method: REQUEST_METHOD.POST,
		category: "中欧心血管学院",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "学院序号",
				key: "collegeSeq",
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
				name: "课件类型",
				key: "type",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "000001: 线上课件; 000002: 线下课件",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: "000001"
			},
			{
				name: "课程序号",
				key: "seq",
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
				name: "回答",
				key: "answer",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "JSON字符串列表格式（目前仅支持问答题）",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: false,
				bodyEntity: true,
				required: true,
				testValue: "[\"121\": \"323\"]"
			},
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				bodyEntity: false,
				required: true,
				testValue: "8"
			}
		],
		outputTitles:
		[
			"课前问卷", "课前问卷答案", "课后问卷", "课后问卷答案"
		],
		outputKeys:
		[
			"PRE", "PRE_ANSWER", "AFTER", "AFTER_ANSWER"
		],
		output: function(data)
		{
			var titles =
			[
				"课前问卷", "课前问卷答案", "课后问卷", "课后问卷答案"
			];
			var keys =
			[
				"PRE", "PRE_ANSWER", "AFTER", "AFTER_ANSWER"
			];
			var values = mkFlatData(data, keys);
			mkFlat(titles, keys, values);
		}
	},
	{
		name: "发表课程提问",
		code: "postCourseQuestion",
		status: SERVICE_STATUS.DONE,
		path: "/College/{collegeSeq}/Course/{type}/{seq}/Question?userSeq=[userSeq]",
		method: REQUEST_METHOD.POST,
		category: "中欧心血管学院",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "学院序号",
				key: "collegeSeq",
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
				name: "课件类型",
				key: "type",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "000001: 线上课件; 000002: 线下课件",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: "000001"
			},
			{
				name: "课程序号",
				key: "seq",
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
				name: "问题",
				key: "question",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: false,
				bodyEntity: true,
				required: true,
				testValue: ""
			},
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				bodyEntity: false,
				required: true,
				testValue: "8"
			}
		],
		outputTitles:
		[
			"序号"
		],
		outputKeys:
		[
			""
		],
		output: function(data)
		{
			mkPlain("序号", data)
		}
	},
	{
		name: "提交课程阅读进度",
		code: "submitCourseProgress",
		status: SERVICE_STATUS.DONE,
		path: "/College/{collegeSeq}/Course/{type}/{seq}/Progress?userSeq=[userSeq]&length=[length]",
		method: REQUEST_METHOD.PUT,
		category: "中欧心血管学院",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "学院序号",
				key: "collegeSeq",
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
				name: "课件类型",
				key: "type",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "000001: 线上课件; 000002: 线下课件",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: "000001"
			},
			{
				name: "课程序号",
				key: "seq",
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
				name: "视频总长度",
				key: "length",
				type: PARAM_TYPE.INTEGER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: ""
			},
			{
				name: "进度",
				key: "progress",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "视频已播放的毫秒数",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: false,
				urlParam: false,
				bodyEntity: true,
				required: true,
				testValue: ""
			},
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				bodyEntity: false,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "赞课程",
		code: "likeCourse",
		status: SERVICE_STATUS.DONE,
		path: "/College/{collegeSeq}/Course/{type}/{seq}/Like?userSeq=[userSeq]",
		method: REQUEST_METHOD.POST,
		category: "中欧心血管学院",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "学院序号",
				key: "collegeSeq",
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
				name: "课件类型",
				key: "type",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "000001: 线上课件; 000002: 线下课件",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: "000001"
			},
			{
				name: "课程序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				bodyEntity: false,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "取消课程赞同",
		code: "cancelLikeCourse",
		status: SERVICE_STATUS.DONE,
		path: "/College/{collegeSeq}/Course/{type}/{seq}/Like?userSeq=[userSeq]",
		method: REQUEST_METHOD.DELETE,
		category: "中欧心血管学院",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "学院序号",
				key: "collegeSeq",
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
				name: "课件类型",
				key: "type",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "000001: 线上课件; 000002: 线下课件",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: "000001"
			},
			{
				name: "课程序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				bodyEntity: false,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "赞课程问题/回答",
		code: "likeCourseQuestion",
		status: SERVICE_STATUS.DONE,
		path: "/College/{collegeSeq}/Course/{type}/{courseSeq}/Question/{seq}/Like?userSeq=[userSeq]",
		method: REQUEST_METHOD.POST,
		category: "中欧心血管学院",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "学院序号",
				key: "collegeSeq",
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
				name: "课件类型",
				key: "type",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "000001: 线上课件; 000002: 线下课件",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: "000001"
			},
			{
				name: "课程序号",
				key: "courseSeq",
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
				name: "问题/回答序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				bodyEntity: false,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "取消课程问题/回答赞同",
		code: "cancelLikeCourseQuestion",
		status: SERVICE_STATUS.DONE,
		path: "/College/{collegeSeq}/Course/{type}/{courseSeq}/Question/{seq}/Like?userSeq=[userSeq]",
		method: REQUEST_METHOD.DELETE,
		category: "中欧心血管学院",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "学院序号",
				key: "collegeSeq",
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
				name: "课件类型",
				key: "type",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "000001: 线上课件; 000002: 线下课件",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: "000001"
			},
			{
				name: "课程序号",
				key: "courseSeq",
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
				name: "问题/回答序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				bodyEntity: false,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "获取用户开通的课程列表",
		code: "getUserCourseList",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/Course",
		method: REQUEST_METHOD.GET,
		category: "中欧心血管学院",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			}
		],
		outputTitles:
		[
			"序号", "课件名称", "视频ID", "总长度", "进度", "浏览数", "赞同数", "是否有新回答"
		],
		outputKeys:
		[
			"seq", "name", "videoId", "length", "progress", "visitCount", "likeCount", "hasNewAnswer"
		],
		output: function(data)
		{
			var titles =
			[
				"序号", "课件名称", "视频ID", "总长度", "进度", "浏览数", "赞同数", "是否有新回答"
			];
			var keys =
			[
				"seq", "name", "videoId", "length", "progress", "visitCount", "likeCount", "hasNewAnswer"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},
	{
		name: "获取基本功列表",
		code: "getTrainingList",
		status: SERVICE_STATUS.DONE,
		path: "/Training?userSeq=[userSeq]",
		method: REQUEST_METHOD.GET,
		category: "基本功",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles:
		[
			"序号", "标题", "视频ID", "所属学院", "问题类型", "作答状态", "更新时间"
		],
		outputKeys:
		[
			"seq", "title", "videoId", "college", "questionType", "beenAnswer", "updateTime"
		],
		output: function(data)
		{
			var titles =
			[
				"序号", "标题", "视频ID", "所属学院", "问题类型", "作答状态", "更新时间"
			];
			var keys =
			[
				"seq", "title", "videoId", "college", "questionType", "beenAnswer", "updateTime"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},
	{
		name: "获取基本功详细",
		code: "getTraining",
		status: SERVICE_STATUS.DONE,
		path: "/Training/{seq}?userSeq=[userSeq]",
		method: REQUEST_METHOD.GET,
		category: "基本功",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "基本功序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles:
		[
			"问题类型", "问题描述", "介绍", "正确答案", "正确答案解释", "备选答案列表", "收藏SEQ(未收藏为null)", "视频ID"
		],
		outputKeys:
		[
			"questionType", "question", "introduce", "answer", "answerExplain", "candidateList", "favoriteSeq", "videoId"
		],
		output: function(data)
		{
			var titles =
			[
				"问题类型", "问题描述", "介绍", "正确答案", "正确答案解释", "备选答案列表", "收藏SEQ(未收藏为null)", "视频ID"
			];
			var keys =
			[
				"questionType", "question", "introduce", "answer", "answerExplain", "candidateList", "favoriteSeq", "videoId"
			];
			var values = mkFlatData(data, keys);
			mkFlat(titles, keys, values);
		}
	},

	{
		name: "获取基本功封面",
		code: "downloadTrainingCover",
		status: SERVICE_STATUS.DONE,
		path: "/Training/{seq}/Cover?userSeq=[userSeq]",
		method: REQUEST_METHOD.GET,
		category: "基本功",
		connectType: CONNECT_TYPE.IMAGE,
		param:
		[
			{
				name: "基本功序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "获取基本功问题附件",
		code: "downloadTrainingQuestionAttach",
		status: SERVICE_STATUS.DONE,
		path: "/Training/{seq}/QuestionAttach?userSeq=[userSeq]",
		method: REQUEST_METHOD.GET,
		category: "基本功",
		connectType: CONNECT_TYPE.DOWNLOAD,
		param:
		[
			{
				name: "基本功序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "获取基本功问题音频",
		code: "[static]",
		status: SERVICE_STATUS.DONE,
		path: "/attach/training/{seq}/$QUESTION.mp3?userSeq=[userSeq]",
		method: REQUEST_METHOD.GET,
		category: "基本功",
		connectType: CONNECT_TYPE.AUDIO,
		param:
		[
			{
				name: "基本功序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "获取基本功问题视频",
		code: "[static]",
		status: SERVICE_STATUS.DONE,
		path: "/attach/training/{seq}/$QUESTION.mp4?userSeq=[userSeq]",
		method: REQUEST_METHOD.GET,
		category: "基本功",
		connectType: CONNECT_TYPE.VIDEO,
		param:
		[
			{
				name: "基本功序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "获取基本功备选答案附件",
		code: "downloadTrainingCandidateAttach",
		status: SERVICE_STATUS.DONE,
		path: "/Training/{trainingSeq}/Candidate/{seq}/Attach?userSeq=[userSeq]",
		method: REQUEST_METHOD.GET,
		category: "基本功",
		connectType: CONNECT_TYPE.DOWNLOAD,
		param:
		[
			{
				name: "基本功序号",
				key: "trainingSeq",
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
				name: "备选答案序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "获取基本功备选答案音频",
		code: "[static]",
		status: SERVICE_STATUS.DONE,
		path: "/attach/training/{trainingSeq}/candidate/{seq}.mp3?userSeq=[userSeq]",
		method: REQUEST_METHOD.GET,
		category: "基本功",
		connectType: CONNECT_TYPE.AUDIO,
		param:
		[
			{
				name: "基本功序号",
				key: "trainingSeq",
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
				name: "备选答案序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "获取基本功备选答案视频",
		code: "[static]",
		status: SERVICE_STATUS.DONE,
		path: "/attach/training/{trainingSeq}/candidate/{seq}.mp4?userSeq=[userSeq]",
		method: REQUEST_METHOD.GET,
		category: "基本功",
		connectType: CONNECT_TYPE.VIDEO,
		param:
		[
			{
				name: "基本功序号",
				key: "trainingSeq",
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
				name: "备选答案序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "提交基本功答案",
		code: "submitTrainingAnswer",
		status: SERVICE_STATUS.DONE,
		path: "/Training/{trainingSeq}/Answer?userSeq=[userSeq]",
		method: REQUEST_METHOD.POST,
		category: "基本功",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "基本功序号",
				key: "trainingSeq",
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
				name: "备选答案序号",
				key: "seq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "备选答案序号",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: false,
				bodyEntity: false,
				required: true,
				testValue: ""
			},
			{
				name: "答案",
				key: "listenAnswer",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "听写答案",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: false,
				bodyEntity: false,
				required: true,
				testValue: ""
			},
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				bodyEntity: false,
				required: true,
				testValue: "8"
			}

		],
		outputTitles:
		[
			"备选答案序号", "回答数"
		],
		outputKeys:
		[
			"candidate", "count"
		],
		output: function(data)
		{
			var titles =
			[
				"备选答案序号", "回答数"
			];
			var keys =
			[
				"candidate", "count"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},
	{
		name: "获取文献列表",
		code: "getLiteratureList",
		status: SERVICE_STATUS.DONE,
		path: "/Literature?userSeq=[userSeq]&literatureSeq=[literatureSeq]",
		method: REQUEST_METHOD.GET,
		category: "Journal Club",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			},
			{
				name: "文献序号",
				key: "literatureSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "传入后列表中仅返回指定文献",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: false,
				testValue: "1"
			}
		],
		outputTitles:
		[
			"序号", "文献名称", "类型（已作废）", "总长度", "阅读进度", "浏览数", "赞同数", "是否有文字", "是否有音频", "是否有图集", "创建时间"
		],
		outputKeys:
		[
			"seq", "name", "type", "length", "progress", "visitCount", "likeCount", "hasText", "hasAudio", "hasAlbum", "createTime"
		],
		output: function(data)
		{
			var titles =
			[
				"序号", "文献名称", "类型（已作废）", "总长度", "阅读进度", "浏览数", "赞同数", "是否有文字", "是否有音频", "是否有图集", "创建时间"
			];
			var keys =
			[
				"seq", "name", "type", "length", "progress", "visitCount", "likeCount", "hasText", "hasAudio", "hasAlbum", "createTime"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},
	{
		name: "获取文献详情",
		code: "getLiterature",
		status: SERVICE_STATUS.DONE,
		path: "/Literature/{seq}?userSeq=[userSeq]",
		method: REQUEST_METHOD.GET,
		category: "Journal Club",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			},
			{
				name: "文献序号",
				key: "seq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "1"
			}
		],
		outputTitles:
		[
			"收藏SEQ(未收藏为null)", "已赞同", "简介"
		],
		outputKeys:
		[
			"favoriteSeq", "beenLike", "introduce"
		],
		output: function(data)
		{
			var titles =
			[
				"收藏SEQ(未收藏为null)", "已赞同", "简介"
			];
			var keys =
			[
				"favoriteSeq", "beenLike", "introduce"
			];
			var values = mkFlatData(data, keys);
			mkFlat(titles, keys, values);
		}
	},
	{
		name: "获取文献封面",
		code: "downloadLiteratureCover",
		status: SERVICE_STATUS.DONE,
		path: "/Literature/{seq}/Cover?userSeq=[userSeq]",
		method: REQUEST_METHOD.GET,
		category: "Journal Club",
		connectType: CONNECT_TYPE.IMAGE,
		param:
		[
			{
				name: "文献序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "获取文献文本",
		code: "getLiteratureRTF",
		status: SERVICE_STATUS.DONE,
		path: "/Literature/{seq}/Text?userSeq=[userSeq]",
		method: REQUEST_METHOD.GET,
		category: "Journal Club",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "文献序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles:
		[
			"文献内容"
		],
		outputKeys:
		[
			""
		],
		output: function(data)
		{
			mkPlain("文献内容", data)
		}
	},
	{
		name: "获取文献音频",
		code: "[static]",
		status: SERVICE_STATUS.DONE,
		path: "/attach/literature/{seq}/$AUDIO.mp3?userSeq=[userSeq]",
		method: REQUEST_METHOD.GET,
		category: "Journal Club",
		connectType: CONNECT_TYPE.AUDIO,
		param:
		[
			{
				name: "文献序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "获取文献视频",
		code: "[static]",
		status: SERVICE_STATUS.DONE,
		path: "/attach/literature/{seq}/$LITERATURE.mp4?userSeq=[userSeq]",
		method: REQUEST_METHOD.GET,
		category: "Journal Club",
		connectType: CONNECT_TYPE.VIDEO,
		param:
		[
			{
				name: "文献序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "获取图集文献中的指定图片",
		code: "downloadLiteratureAlbumItem",
		status: SERVICE_STATUS.DONE,
		path: "/Literature/{seq}/Literature/Album?userSeq=[userSeq]&seek=[seek]",
		method: REQUEST_METHOD.GET,
		category: "Journal Club",
		connectType: CONNECT_TYPE.IMAGE,
		param:
		[
			{
				name: "文献序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			},
			{
				name: "页码",
				key: "seek",
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
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "提交文献阅读进度",
		code: "submitLiteratureProgress",
		status: SERVICE_STATUS.DONE,
		path: "/Literature/{seq}/Progress?userSeq=[userSeq]",
		method: REQUEST_METHOD.PUT,
		category: "Journal Club",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "文献序号",
				key: "seq",
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
				name: "进度",
				key: "progress",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "视频已播放的毫秒数",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: false,
				urlParam: false,
				bodyEntity: true,
				required: true,
				testValue: ""
			},
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				bodyEntity: false,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "赞文献",
		code: "likeLiterature",
		status: SERVICE_STATUS.DONE,
		path: "/Literature/{seq}/Like?userSeq=[userSeq]",
		method: REQUEST_METHOD.POST,
		category: "Journal Club",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "文献序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				bodyEntity: false,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "取消文献赞同",
		code: "likeLiterature",
		status: SERVICE_STATUS.DONE,
		path: "/Literature/{seq}/Like?userSeq=[userSeq]",
		method: REQUEST_METHOD.DELETE,
		category: "Journal Club",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "文献序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				bodyEntity: false,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "获取大师观点列表",
		code: "getViewpointList",
		status: SERVICE_STATUS.DONE,
		path: "/Viewpoint?userSeq=[userSeq]&viewpointSeq=[viewpointSeq]",
		method: REQUEST_METHOD.GET,
		category: "大师观点",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "大师观点SEQ",
				key: "viewpointSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "传入后，列表中仅返回返回指定大师观点",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: false,
				testValue: "1"
			},
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles:
		[
			"序号", "主题", "视频ID", "总长度", "进度", "浏览数", "赞同数", "是否已赞同", "是否有新回答"
		],
		outputKeys:
		[
			"seq", "topic", "videoId", "length", "progress", "visitCount", "likeCount", "beenLike", "hasNewAnswer"
		],
		output: function(data)
		{
			var titles =
			[
				"序号", "主题", "视频ID", "总长度", "进度", "浏览数", "赞同数", "是否已赞同", "是否有新回答"
			];
			var keys =
			[
				"seq", "topic", "videoId", "length", "progress", "visitCount", "likeCount", "beenLike", "hasNewAnswer"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},
	{
		name: "获取大师观点详细",
		code: "getViewpoint",
		status: SERVICE_STATUS.DONE,
		path: "/Viewpoint/{seq}?userSeq=[userSeq]",
		method: REQUEST_METHOD.GET,
		category: "大师观点",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "大师观点序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles:
		[
			"投票列表", "问答列表", "介绍", "观前问卷", "观后问卷", "长度", "视频ID", "收藏SEQ(未收藏为null)"
		],
		outputKeys:
		[
			"voteList", "questionList", "introduce", "preSurvey", "afterSurvey", "length", "videoId", "favoriteSeq"
		],
		output: function(data)
		{
			var titles =
			[
				"投票列表", "问答列表", "介绍", "观前问卷", "观后问卷", "长度", "视频ID", "收藏SEQ(未收藏为null)"
			];
			var keys =
			[
				"voteList", "questionList", "introduce", "preSurvey", "afterSurvey", "length", "videoId", "favoriteSeq"
			];
			var values = mkFlatData(data, keys);
			mkFlat(titles, keys, values);
		}
	},
	{
		name: "提交观前问卷答案",
		code: "submitViewpointPreSurvey",
		status: SERVICE_STATUS.DONE,
		path: "/Viewpoint/{seq}/PreSurvey?userSeq=[userSeq]",
		method: REQUEST_METHOD.POST,
		category: "大师观点",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "课程序号",
				key: "seq",
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
				name: "回答",
				key: "answer",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "JSON字符串列表格式（目前仅支持问答题）",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: false,
				bodyEntity: true,
				required: true,
				testValue: "[\"121\": \"323\"]"
			},
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				bodyEntity: false,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "提交观后问卷答案",
		code: "submitViewpointAfterSurvey",
		status: SERVICE_STATUS.DONE,
		path: "/Viewpoint/{seq}/AfterSurvey?userSeq=[userSeq]",
		method: REQUEST_METHOD.POST,
		category: "大师观点",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "大师观点序号",
				key: "seq",
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
				name: "回答",
				key: "answer",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "JSON字符串列表格式（目前仅支持问答题）",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: false,
				bodyEntity: true,
				required: true,
				testValue: "[\"121\": \"323\"]"
			},
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				bodyEntity: false,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "获取大师观点封面",
		code: "downloadViewpointCover",
		status: SERVICE_STATUS.DONE,
		path: "/Viewpoint/{seq}/Cover?userSeq=[userSeq]",
		method: REQUEST_METHOD.GET,
		category: "大师观点",
		connectType: CONNECT_TYPE.IMAGE,
		param:
		[
			{
				name: "大师观点序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "获取大师观点视频",
		code: "[static]",
		status: SERVICE_STATUS.DONE,
		path: "/attach/viewpoint/{seq}/$VIEWPOINT.mp4?userSeq=[userSeq]",
		method: REQUEST_METHOD.GET,
		category: "大师观点",
		connectType: CONNECT_TYPE.VIDEO,
		param:
		[
			{
				name: "大师观点序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "发表大师观点提问",
		code: "postViewpointQuestion",
		status: SERVICE_STATUS.DONE,
		path: "/Viewpoint/{seq}/Question?userSeq=[userSeq]",
		method: REQUEST_METHOD.POST,
		category: "大师观点",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "大师观点序号",
				key: "seq",
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
				name: "问题",
				key: "question",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: false,
				bodyEntity: true,
				required: true,
				testValue: ""
			},
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				bodyEntity: false,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "提交大师观点阅读进度",
		code: "submitViewpointProgress",
		status: SERVICE_STATUS.DONE,
		path: "/Viewpoint/{seq}/Progress?userSeq=[userSeq]&length=[length]",
		method: REQUEST_METHOD.PUT,
		category: "大师观点",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "大师观点序号",
				key: "seq",
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
				name: "视频总长度",
				key: "length",
				type: PARAM_TYPE.INTEGER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: ""
			},
			{
				name: "进度",
				key: "progress",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "视频已播放的毫秒数",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: false,
				urlParam: false,
				bodyEntity: true,
				required: true,
				testValue: ""
			},
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				bodyEntity: false,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "赞大师观点",
		code: "likeViewpoint",
		status: SERVICE_STATUS.DONE,
		path: "/Viewpoint/{seq}/Like?userSeq=[userSeq]",
		method: REQUEST_METHOD.POST,
		category: "大师观点",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "大师观点序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				bodyEntity: false,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "取消大师观点赞同",
		code: "cancelLikeViewpoint",
		status: SERVICE_STATUS.DONE,
		path: "/Viewpoint/{seq}/Like?userSeq=[userSeq]",
		method: REQUEST_METHOD.DELETE,
		category: "大师观点",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "大师观点序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				bodyEntity: false,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "赞大师观点问题/回答",
		code: "likeViewpointQuestion",
		status: SERVICE_STATUS.DONE,
		path: "/Viewpoint/{viewpointSeq}/Question/{seq}/Like?userSeq=[userSeq]",
		method: REQUEST_METHOD.POST,
		category: "大师观点",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "大师观点序号",
				key: "viewpointSeq",
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
				name: "问题/回答序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				bodyEntity: false,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "取消大师观点问题/回答赞同",
		code: "cancelLikeViewpointQuestion",
		status: SERVICE_STATUS.DONE,
		path: "/Viewpoint/{viewpointSeq}/Question/{seq}/Like?userSeq=[userSeq]",
		method: REQUEST_METHOD.DELETE,
		category: "大师观点",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "大师观点序号",
				key: "viewpointSeq",
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
				name: "问题/回答序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				bodyEntity: false,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "大师观点投票",
		code: "voteViewpoint",
		status: SERVICE_STATUS.DONE,
		path: "/Viewpoint/{seq}/Vote?userSeq=[userSeq]",
		method: REQUEST_METHOD.POST,
		category: "大师观点",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "大师观点序号",
				key: "seq",
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
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			},
			{
				name: "立场",
				key: "standpoint",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "@数据字典.TYPE=23",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: false,
				bodyEntity: true,
				required: true,
				testValue: "000001"
			}
		],
		outputTitles:
		[
			"立场", "数量"
		],
		outputKeys:
		[
			"standpoint", "count"
		],
		output: function(data)
		{
			var titles =
			[
				"立场", "数量"
			];
			var keys =
			[
				"standpoint", "count"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},
	{
		name: "获取大师观点预告",
		code: "getViewpointForenotice",
		status: SERVICE_STATUS.DONE,
		path: "/ViewpointForenotice",
		method: REQUEST_METHOD.GET,
		category: "大师观点",
		connectType: CONNECT_TYPE.AJAX,
		param: null,
		outputTitles:
		[
			"标题", "内容"
		],
		outputKeys:
		[
			"title", "content"
		],
		output: function(data)
		{
			var titles =
			[
				"标题", "内容"
			];
			var keys =
			[
				"title", "content"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},
	{
		name: "获取大师观点预选",
		code: "getViewpointNomination",
		status: SERVICE_STATUS.DONE,
		path: "/ViewpointNomination",
		method: REQUEST_METHOD.GET,
		category: "大师观点",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles:
		[
			"序号", "标题", "备选项列表"
		],
		outputKeys:
		[
			"seq", "title", "candidateList"
		],
		output: function(data)
		{
			var titles =
			[
				"序号", "标题", "备选项列表"
			];
			var keys =
			[
				"seq", "title", "candidateList"
			];
			var values = mkFlatData(data, keys);
			mkFlat(titles, keys, values);
		}
	},
	{
		name: "投票给大师观点预选",
		code: "getViewpointNomination",
		status: SERVICE_STATUS.DONE,
		path: "/ViewpointNomination/{nominationSeq}/{candidateSeq}?userSeq=[userSeq]",
		method: REQUEST_METHOD.POST,
		category: "大师观点",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			},
			{
				name: "预选SEQ",
				key: "nominationSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			},
			{
				name: "预选备选项SEQ",
				key: "candidateSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "获取最近浏览课件列表",
		code: "getRecentCourseList",
		status: SERVICE_STATUS.DONE,
		path: "/Recent/Course?userSeq=[userSeq]",
		method: REQUEST_METHOD.GET,
		category: "最近浏览",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles:
		[
			"序号", "课件名称", "视频ID", "视频长度", "进度", "浏览数", "赞同数", "是否有新回答", "最近访问时间"
		],
		outputKeys:
		[
			"seq", "name", "videoId", "length", "progress", "visitCount", "likeCount", "hasNewAnswer", "visitTime"
		],
		output: function(data)
		{
			var titles =
			[
				"序号", "课件名称", "视频ID", "视频长度", "进度", "浏览数", "赞同数", "是否有新回答", "最近访问时间"
			];
			var keys =
			[
				"seq", "name", "videoId", "length", "progress", "visitCount", "likeCount", "hasNewAnswer", "visitTime"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},
	{
		name: "获取最近浏览大师观点列表",
		code: "getRecentViewpointList",
		status: SERVICE_STATUS.DONE,
		path: "/Recent/Viewpoint?userSeq=[userSeq]",
		method: REQUEST_METHOD.GET,
		category: "最近浏览",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles:
		[
			"序号", "主题", "视频ID", "视频长度", "进度", "浏览数", "赞同数", "是否有新回答", "最近访问时间"
		],
		outputKeys:
		[
			"seq", "topic", "videoId", "length", "progress", "visitCount", "likeCount", "hasNewAnswer", "visitTime"
		],
		output: function(data)
		{
			var titles =
			[
				"序号", "主题", "视频ID", "视频长度", "进度", "浏览数", "赞同数", "是否有新回答", "最近访问时间"
			];
			var keys =
			[
				"seq", "topic", "videoId", "length", "progress", "visitCount", "likeCount", "hasNewAnswer", "visitTime"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},
	{
		name: "获取最近浏览文献列表",
		code: "getRecentLiteratureList",
		status: SERVICE_STATUS.DONE,
		path: "/Recent/Literature?userSeq=[userSeq]",
		method: REQUEST_METHOD.GET,
		category: "最近浏览",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles:
		[
			"序号", "文献名称", "阅读进度", "浏览数", "赞同数", "最近访问时间"
		],
		outputKeys:
		[
			"seq", "name", "progress", "visitCount", "likeCount", "visitTime"
		],
		output: function(data)
		{
			var titles =
			[
				"序号", "文献名称", "阅读进度", "浏览数", "赞同数", "最近访问时间"
			];
			var keys =
			[
				"seq", "name", "progress", "visitCount", "likeCount", "visitTime"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},
	{
		name: "获取最近浏览基本功列表",
		code: "getRecentTrainingList",
		status: SERVICE_STATUS.DONE,
		path: "/Recent/Training?userSeq=[userSeq]",
		method: REQUEST_METHOD.GET,
		category: "最近浏览",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "8"
			}
		],
		outputTitles:
		[
			"序号", "标题", "所属学院", "问题类型", "作答状态", "最近访问时间"
		],
		outputKeys:
		[
			"seq", "title", "college", "questionType", "beenAnswer", "visitTime"
		],
		output: function(data)
		{
			var titles =
			[
				"序号", "标题", "所属学院", "问题类型", "作答状态", "最近访问时间"
			];
			var keys =
			[
				"seq", "title", "college", "questionType", "beenAnswer", "visitTime"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},
	{
		name: "获取用户收藏",
		code: "getFavorite",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/Favorite?page=[page]",
		method: REQUEST_METHOD.GET,
		category: "收藏",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			},
			{
				name: "页码",
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
			"序号", "作者用户SEQ", "作者姓名", "文章SEQ", "文章标题", "模块编号", "模块名称", "附件类型", "附件数量", "视频ID", "视频进度", "视频长度", "分享", "收藏时间"
		],
		outputKeys:
		[
			"seq", "authorSeq", "author", "articleSeq", "articleTitle", "module", "moduleName", "attachType", "attachCount", "videoId", "progress", "length", "share", "createTime"
		],
		output: function(data)
		{
			var titles =
			[
				"序号", "作者用户SEQ", "作者姓名", "文章SEQ", "文章标题", "模块编号", "模块名称", "附件类型", "附件数量", "视频ID", "视频进度", "视频长度", "分享", "收藏时间"
			];
			var keys =
			[
				"seq", "authorSeq", "author", "articleSeq", "articleTitle", "module", "moduleName", "attachType", "attachCount", "videoId", "progress", "length", "share", "createTime"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},
	{
		name: "新增收藏",
		code: "createFavorite",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/Favorite",
		method: REQUEST_METHOD.POST,
		category: "收藏",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "模块编号",
				key: "module",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				required: true,
				testValue: "000001"
			},
			{
				name: "文章SEQ",
				key: "articleSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: false,
				required: true,
				testValue: "1"
			},
			{
				name: "作者SEQ",
				key: "authorSeq",
				type: PARAM_TYPE.INTETER,
				length: "",
				defaultValue: "",
				note: "收藏同行圈时需要传",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: false,
				required: false,
				testValue: "1"
			},
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				bodyEntity: false,
				required: true,
				testValue: "8"
			}
		],
		outputTitles:
		[
			"收藏SEQ"
		],
		outputKeys:
		[
			""
		],
		output: function(data)
		{
			mkPlain("收藏SEQ", data)
		}
	},
	{
		name: "删除用户收藏",
		code: "removeFavorite",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/Favorite/{seq}",
		method: REQUEST_METHOD.DELETE,
		category: "收藏",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			},
			{
				name: "收藏序号",
				key: "seq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "1"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "获取同行圈动态",
		code: "getRecentPeercicleList",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/Peercicle?page=[page]&peercicleSeq=[peercicleSeq]&module=[module]&subfield=[subfield]&orderByLike=[orderByLike]&selExpert=[selExpert]",
		method: REQUEST_METHOD.GET,
		category: "同行圈",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			},
			{
				name: "同行圈SEQ",
				key: "peercicleSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: false,
				testValue: "8"
			},
			{
				name: "板块",
				key: "module",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: false,
				testValue: ""
			},
			{
				name: "亚领域",
				key: "subfield",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: false,
				testValue: ""
			},
			{
				name: "按人气排序",
				key: "orderByLike",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "true/false",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: false,
				testValue: "false"
			},
			{
				name: "只显示选皇冠用户帖子",
				key: "selExpert",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "true/false",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: false,
				testValue: "false"
			},
			{
				name: "页码",
				key: "page",
				type: PARAM_TYPE.INTETER,
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
			"序号", "发表人姓名", "发表人SEQ", "是否已关注发表人", "是否认证用户", "是否专家", "是否认证机构", "发表人机构", "类型消息类型(@T_DICT.TYPE=24)", "标题", "文本", "分享数", "评论数", "赞同数", "是否已收藏", "收藏SEQ", "是否已赞同", "附件数量", "分享源数据（如果是分享型文章，这里不为null）", "允许分享", "视频ID", "发表时间"
		],
		outputKeys:
		[
			"seq", "author", "authorSeq", "isFans", "isV", "isExpert", "isInstitutionV", "institution", "type", "title", "text", "shareCount", "commentCount", "likeCount", "beenFavorite", "favoriteSeq", "beenLike", "attachCount", "share", "allowShare", "videoId", "createTime"
		],
		output: function(data)
		{
			var titles =
			[
				"序号", "发表人姓名", "发表人SEQ", "是否已关注发表人", "是否认证用户", "是否专家", "是否认证机构", "发表人机构", "类型消息类型(@T_DICT.TYPE=24)", "标题", "文本", "分享数", "评论数", "赞同数", "是否已收藏", "收藏SEQ", "是否已赞同", "附件数量", "分享源数据（如果是分享型文章，这里不为null）", "允许分享", "视频ID", "发表时间"
			];
			var keys =
			[
				"seq", "author", "authorSeq", "isFans", "isV", "isExpert", "isInstitutionV", "institution", "type", "title", "text", "shareCount", "commentCount", "likeCount", "beenFavorite", "favoriteSeq", "beenLike", "attachCount", "share", "allowShare", "videoId", "createTime"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},
	{
		name: "获取同行圈详细",
		code: "getPeercicle",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/Peercicle/{seq}",
		method: REQUEST_METHOD.GET,
		category: "同行圈",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			},
			{
				name: "同行圈SEQ",
				key: "seq",
				type: PARAM_TYPE.INTETER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "1"
			}
		],
		outputTitles:
		[
			"评论列表"
		],
		outputKeys:
		[
			"commentList"
		],
		output: function(data)
		{
			var titles =
			[
				"评论列表"
			];
			var keys =
			[
				"commentList"
			];
			var values = mkFlatData(data, keys);
			mkFlat(titles, keys, values);
		}
	},
	{
		name: "获取审核中的同行圈",
		code: "getReviewPeercicleList",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/ReviewPeercicle?page=[page]&orderBy=[orderBy]",
		method: REQUEST_METHOD.GET,
		category: "同行圈",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			},
			{
				name: "页码",
				key: "page",
				type: PARAM_TYPE.INTETER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "1"
			},
			{
				name: "排序方式",
				key: "orderBy",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "01: 按时间降序; 02: 按票数降序",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "01"
			}
		],
		outputTitles:
		[
			"序号", "发表人SEQ", "发表人机构", "标题", "票数", "视频ID", "发表时间"
		],
		outputKeys:
		[
			"seq", "authorSeq", "institution", "title", "votes", "videoId", "createTime"
		],
		output: function(data)
		{
			var titles =
			[
				"序号", "发表人SEQ", "发表人机构", "标题", "票数", "视频ID", "发表时间"
			];
			var keys =
			[
				"seq", "authorSeq", "institution", "title", "votes", "videoId", "createTime"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},
	{
		name: "获取审核中的同行圈详细",
		code: "getReviewPeercicle",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/ReviewPeercicle/{seq}",
		method: REQUEST_METHOD.GET,
		category: "同行圈",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			},
			{
				name: "同行圈SEQ",
				key: "seq",
				type: PARAM_TYPE.INTETER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "1"
			}
		],
		outputTitles:
		[
			"序号", "发表人姓名", "发表人SEQ", "发表人单位", "是否认证用户", "是否砖家", "是否认证机构", "是否已关注发表人", "类型消息类型(@T_DICT.TYPE=24)", "文本", "附件数量", "是否已审核"
		],
		outputKeys:
		[
			"seq", "author", "authorSeq", "institution", "isV", "isExpert", "isInstitutionV", "isFans", "type", "text", "attachCount", "beenVote"
		],
		output: function(data)
		{
			var titles =
			[
				"序号", "发表人姓名", "发表人SEQ", "发表人单位", "是否认证用户", "是否砖家", "是否认证机构", "是否已关注发表人", "类型消息类型(@T_DICT.TYPE=24)", "文本", "附件数量", "是否已审核"
			];
			var keys =
			[
				"seq", "author", "authorSeq", "institution", "isV", "isExpert", "isInstitutionV", "isFans", "type", "text", "attachCount", "beenVote"
			];
			var values = mkFlatData(data, keys);
			mkFlat(titles, keys, values);
		}
	},
	{
		name: "获取同行圈图片",
		code: "downloadPeercicleImage",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/Peercicle/{seq}/Image/{index}",
		method: REQUEST_METHOD.GET,
		category: "同行圈",
		connectType: CONNECT_TYPE.IMAGE,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			},
			{
				name: "同行圈SEQ",
				key: "seq",
				type: PARAM_TYPE.INTETER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "1"
			},
			{
				name: "图片序号",
				key: "index",
				type: PARAM_TYPE.INTETER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "1"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "获取同行圈音频",
		code: "[static]",
		status: SERVICE_STATUS.DONE,
		path: "/attach/peercicle/{seq}/audio/$AUDIO.mp3",
		method: REQUEST_METHOD.GET,
		category: "同行圈",
		connectType: CONNECT_TYPE.AUDIO,
		param:
		[
			{
				name: "同行圈SEQ",
				key: "seq",
				type: PARAM_TYPE.INTETER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "1"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "获取同行圈视频",
		code: "[static]",
		status: SERVICE_STATUS.DONE,
		path: "/attach/peercicle/{seq}/video/$VIDEO.mp4",
		method: REQUEST_METHOD.GET,
		category: "同行圈",
		connectType: CONNECT_TYPE.VIDEO,
		param:
		[
			{
				name: "同行圈SEQ",
				key: "seq",
				type: PARAM_TYPE.INTETER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "1"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	// TODO 发表同行圈
	{
		name: "分享到同行圈",
		code: "sharePeericle",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/Share/{module}/{articleSeq}",
		method: REQUEST_METHOD.POST,
		category: "同行圈",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			},
			{
				name: "功能模块编码",
				key: "module",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "000006"
			},
			{
				name: "文章SEQ",
				key: "articleSeq",
				type: PARAM_TYPE.INTEGER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "1"
			},
			{
				name: "说点啥",
				key: "text",
				type: PARAM_TYPE.INTEGER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: false,
				bodyEntity: true,
				required: true,
				testValue: "你不鼓掌我就说点啥"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "删除同行圈",
		code: "removePeercicle",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/Peercicle/{seq}",
		method: REQUEST_METHOD.DELETE,
		category: "同行圈",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			},
			{
				name: "同行圈SEQ",
				key: "seq",
				type: PARAM_TYPE.INTEGER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "1"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "赞同行圈",
		code: "likePeercicle",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/Peercicle/{seq}/Like",
		method: REQUEST_METHOD.POST,
		category: "同行圈",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: "8"
			},
			{
				name: "同行圈序号",
				key: "seq",
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
		name: "取消同行圈赞同",
		code: "cancelLikePeercicle",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/Peercicle/{seq}/Like",
		method: REQUEST_METHOD.DELETE,
		category: "同行圈",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				required: true,
				testValue: "8"
			},
			{
				name: "同行圈序号",
				key: "seq",
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
	// {
	// name: "评论同行圈",
	// code: "commentPeercicle",
	// status: SERVICE_STATUS.DONE,
	// path: "/{userSeq}/Peercicle/{seq}/Comment",
	// method: REQUEST_METHOD.POST,
	// category: "同行圈",
	// connectType: CONNECT_TYPE.AJAX,
	// param:
	// [
	// {
	// name: "用户序号",
	// key: "userSeq",
	// type: PARAM_TYPE.STRING,
	// length: "",
	// defaultValue: "",
	// note: "",
	// controlType: CONTROL_TYPE.TEXT,
	// pathVar: true,
	// required: true,
	// testValue: "8"
	// },
	// {
	// name: "同行圈序号",
	// key: "seq",
	// type: PARAM_TYPE.STRING,
	// length: "",
	// defaultValue: "",
	// note: "",
	// controlType: CONTROL_TYPE.TEXT,
	// pathVar: true,
	// required: true,
	// testValue: ""
	// },
	// {
	// name: "评论内容",
	// key: "content",
	// type: PARAM_TYPE.STRING,
	// length: "",
	// defaultValue: "",
	// note: "",
	// controlType: CONTROL_TYPE.TEXT,
	// pathVar: false,
	// urlParam: false,
	// required: true,
	// bodyEntity: true,
	// testValue: "评论下你试试"
	// }
	// ],
	// outputTitles: null,
	// outputKeys: null,
	// output: null
	// },
	{
		name: "获取同行圈评论图片",
		code: "downloadPeercicleCommentImage",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/Peercicle/{peercicleSeq}/Comment/{seq}/Image/{index}",
		method: REQUEST_METHOD.GET,
		category: "同行圈",
		connectType: CONNECT_TYPE.IMAGE,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			},
			{
				name: "同行圈SEQ",
				key: "peercicleSeq",
				type: PARAM_TYPE.INTETER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "1"
			},
			{
				name: "评论SEQ",
				key: "seq",
				type: PARAM_TYPE.INTETER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "1"
			},
			{
				name: "图片序号",
				key: "index",
				type: PARAM_TYPE.INTETER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "1"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "回复同行圈评论",
		code: "replyPeercicleComment",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/Peercicle/{peercicleSeq}/Comment/{seq}/Reply",
		method: REQUEST_METHOD.POST,
		category: "同行圈",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			},
			{
				name: "同行圈SEQ",
				key: "peercicleSeq",
				type: PARAM_TYPE.INTETER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "1"
			},
			{
				name: "评论SEQ",
				key: "seq",
				type: PARAM_TYPE.INTETER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "1"
			},
			{
				name: "回复内容",
				key: "content",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: false,
				urlParam: false,
				required: true,
				bodyEntity: true,
				testValue: "回复下你试试"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "赞同行圈评论",
		code: "likePeercicleComment",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/Peercicle/{peercicleSeq}/Comment/{seq}/Like",
		method: REQUEST_METHOD.POST,
		category: "同行圈",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			},
			{
				name: "同行圈SEQ",
				key: "peercicleSeq",
				type: PARAM_TYPE.INTETER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "1"
			},
			{
				name: "评论SEQ",
				key: "seq",
				type: PARAM_TYPE.INTETER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "1"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "取消同行圈评论赞同",
		code: "cancelLikePeercicleComment",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/Peercicle/{peercicleSeq}/Comment/{seq}/Like",
		method: REQUEST_METHOD.DELETE,
		category: "同行圈",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			},
			{
				name: "同行圈SEQ",
				key: "peercicleSeq",
				type: PARAM_TYPE.INTETER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "1"
			},
			{
				name: "评论SEQ",
				key: "seq",
				type: PARAM_TYPE.INTETER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "1"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "删除同行圈评论",
		code: "removePeercicleComment",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/Peercicle/{peercicleSeq}/Comment/{seq}",
		method: REQUEST_METHOD.DELETE,
		category: "同行圈",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			},
			{
				name: "同行圈SEQ",
				key: "peercicleSeq",
				type: PARAM_TYPE.INTETER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "1"
			},
			{
				name: "评论SEQ",
				key: "seq",
				type: PARAM_TYPE.INTETER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "1"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "投票给一条审核中的同行圈",
		code: "voteReviewPeercicle",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/ReviewPeercicle/{seq}/Vote",
		method: REQUEST_METHOD.POST,
		category: "同行圈",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			},
			{
				name: "同行圈SEQ",
				key: "seq",
				type: PARAM_TYPE.INTETER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "1"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "反悔投票已审核过的同行圈",
		code: "cancelVoteReviewPeercicle",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/ReviewPeercicle/{seq}/Vote",
		method: REQUEST_METHOD.DELETE,
		category: "同行圈",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			},
			{
				name: "同行圈SEQ",
				key: "seq",
				type: PARAM_TYPE.INTETER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "1"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "关注用户",
		code: "watchUser",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/WatchUser/{seq}",
		method: REQUEST_METHOD.POST,
		category: "同行圈",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			},
			{
				name: "被关注用户SEQ",
				key: "seq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "10"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "取消关注用户",
		code: "cancelWatchUser",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/WatchUser/{seq}",
		method: REQUEST_METHOD.DELETE,
		category: "同行圈",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			},
			{
				name: "被关注用户SEQ",
				key: "seq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "10"
			}
		],
		outputTitles: null,
		outputKeys: null,
		output: null
	},
	{
		name: "获取用户发表的同行圈列表",
		code: "getUserPeercicleList",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/OwnPeercicle?page=[page]",
		method: REQUEST_METHOD.GET,
		category: "同行圈",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			},
			{
				name: "页码",
				key: "page",
				type: PARAM_TYPE.INTETER,
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
			"序号", "类型", "标题", "分享源数据（如果是分享型文章，这里不为null）", "已审核通过", "当前票数", "视频ID", "发表时间"
		],
		outputKeys:
		[
			"seq", "type", "title", "share", "isPublished", "votes", "videoId", "createTime"
		],
		output: function(data)
		{
			var titles =
			[
				"序号", "类型", "标题", "分享源数据（如果是分享型文章，这里不为null）", "已审核通过", "当前票数", "视频ID", "发表时间"
			];
			var keys =
			[
				"seq", "type", "title", "share", "isPublished", "votes", "videoId", "createTime"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},
	{
		name: "获取用户发表的同行圈详细",
		code: "getUserPeercicle",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/OwnPeercicle/{seq}",
		method: REQUEST_METHOD.GET,
		category: "同行圈",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			},
			{
				name: "同行圈SEQ",
				key: "seq",
				type: PARAM_TYPE.INTETER,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "1"
			}
		],
		outputTitles:
		[
			"类型", "评论数", "已赞同", "赞同数", "附件数", "评论列表"
		],
		outputKeys:
		[
			"type", "commentCount", "beenLike", "likeCount", "attachCount", "commentList"
		],
		output: function(data)
		{
			var titles =
			[
				"类型", "评论数", "已赞同", "赞同数", "附件数", "评论列表"
			];
			var keys =
			[
				"type", "commentCount", "beenLike", "likeCount", "attachCount", "commentList"
			];
			var values = mkFlatData(data, keys);
			mkFlat(titles, keys, values);
		}
	},
	{
		name: "获取随机推荐的用户",
		code: "getRandomRecommandUser",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/RandomRecommandUser?count=[count]",
		method: REQUEST_METHOD.GET,
		category: "同行圈",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			},
			{
				name: "获取数量",
				key: "count",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "5"
			}
		],
		outputTitles:
		[
			"模块", "文章SEQ", "参数1（用户医院）", "参数2（用户姓名）"
		],
		outputKeys:
		[
			"module", "articleSeq", "extra1", "extra2"
		],
		output: function(data)
		{
			var titles =
			[
				"模块", "文章SEQ", "参数1（用户医院）", "参数2（用户姓名）"
			];
			var keys =
			[
				"module", "articleSeq", "extra1", "extra2"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
	},
	{
		name: "获取随机推荐的文章和用户",
		code: "getRandomRecommand",
		status: SERVICE_STATUS.DONE,
		path: "/{userSeq}/RandomRecommand?count=[count]",
		method: REQUEST_METHOD.GET,
		category: "同行圈",
		connectType: CONNECT_TYPE.AJAX,
		param:
		[
			{
				name: "用户序号",
				key: "userSeq",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.TEXT,
				pathVar: true,
				urlParam: false,
				required: true,
				testValue: "8"
			},
			{
				name: "获取数量",
				key: "count",
				type: PARAM_TYPE.STRING,
				length: "",
				defaultValue: "",
				note: "",
				controlType: CONTROL_TYPE.NUMBER,
				pathVar: false,
				urlParam: true,
				required: true,
				testValue: "5"
			}
		],
		outputTitles:
		[
			"模块", "文章SEQ", "参数1（当module为000000时为用户医院，其他为文章标题）", "参数2（当module为000000时为用户姓名，当000002时为学院SEQ）"
		],
		outputKeys:
		[
			"module", "articleSeq", "extra1", "extra2"
		],
		output: function(data)
		{
			var titles =
			[
				"模块", "文章SEQ", "参数1（当module为000000时为用户医院，其他为文章标题）", "参数2（当module为000000时为用户姓名，当000002时为学院SEQ）"
			];
			var keys =
			[
				"module", "articleSeq", "extra1", "extra2"
			];
			var values = mkListData(data, keys);
			mkList(titles, keys, values);
		}
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