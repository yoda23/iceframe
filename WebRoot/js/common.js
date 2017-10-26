var ctx = getRootPath();
function getRootPath() {
	// 获取当前网址，如： http://localhost:8080/iceframe/view/center/jsps/user/user.jsp
	var curWwwPath = window.document.location.href;
	// 获取主机地址之后的目录，如：iceframe/view/center/jsps/user/user.jsp
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	// 获取主机地址，如： http://localhost:8080
	var localhostPaht = curWwwPath.substring(0, pos);
	// 获取带"/"的项目名，如：/iceframe
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPaht + projectName);
}

$.ajaxSetup({
	contentType : "application/x-www-form-urlencoded;charset=utf-8",
	complete : function(XMLHttpRequest, textStatus) {
		var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
		if (sessionstatus == "timeout") {
			top.location.href = ctx + '/login';
			layer.alert("用户登陆超时,请重新登录");
			return;
		}
		if (textStatus != "success") {
			layer.alert("服务器繁忙，请稍后重试");
			return;
		}
	}
});
