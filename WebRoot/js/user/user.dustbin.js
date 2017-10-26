var userTable;
$(function() {
	initUserTable();
	$("#userSearch").click(function() {
		searchUser();
	});
});
function findUser(userid) {
	layer.confirm('确定要找回此条数据么？', {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		$.ajax({
			type : "POST",
			url : ctx + '/user/updateDeleteFlag',
			data : {
				'id' : userid,
				'deleteFlag' : 2
			},
			cache : false,
			async : true,
			success : function(data) {
				result = $.parseJSON(data);
				if (result.success) {
					window.location.href = ctx
							+ '/redirect?page=user/userDustbin'
				} else {
					layer.alert(result.message);
				}
			}
		});
	});
}

function searchUser() {
	var searchUserName = $("#searchUserName").val();
    userTable.fnSettings().ajax.data = {
        "userName": searchUserName,
        "deleteFlag": "1"
    };
	var url = ctx + '/user/getUserByConditon';
	userTable.api().ajax.url(url).load();
}
