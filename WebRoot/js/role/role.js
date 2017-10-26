var roleTable;
$(function() {
	initroleTable();
	$("#roleAdd").click(function() {
		addrole();
	});
	$("#roleSearch").click(function() {
		searchRole();
	})
});
// 添加角色信息
function addrole() {
	layer.open({
		type : 2,
		area : [ '800px', '500px' ],
		fix : false, // 不固定
		maxmin : false,
		shade : 0.4,
		title : '角色添加',
		content : ctx + '/redirect?page=/role/roleAdd',
		end : function() {
			roleTable.api().ajax.reload();
		}
	});
}
// 跳转到给角色添加菜单页面
function toUpdateMenu(roleId) {
	layer.open({
		type : 2,
		area : [ '800px', '500px' ],
		fix : false, // 不固定
		maxmin : false,
		shade : 0.4,
		title : '添加菜单',
		content : ctx + '/role/toUpdateMenu?id=' + roleId,
		end : function() {
			roleTable.api().ajax.reload(null,false);
		}
	});

}
// 跳转到修改角色页面
function toUpdateRole(roleId) {
	layer.open({
		type : 2,
		area : [ '800px', '500px' ],
		fix : false, // 不固定
		maxmin : false,
		shade : 0.4,
		title : '角色修改',
		content : ctx + '/role/toUpdateRole?id=' + roleId,
		end : function() {
			roleTable.api().ajax.reload(null,false);
		}
	});

}
// 跳转到给角色添加权限页面
function toUpdateRights(roleId) {
	layer.open({
		type : 2,
		area : [ '800px', '500px' ],
		fix : false, // 不固定
		maxmin : false,
		shade : 0.4,
		title : '添加权限',
		content : ctx + '/role/toUpdateRights?id=' + roleId,
		end : function() {
			roleTable.api().ajax.reload(null,false);
		}
	});

}
// 删除角色信息
function deleteRole(roleid) {
	layer.confirm('确定要删除此条数据么？', {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		$.ajax({
			type : "POST",
			url : ctx + '/role/deleteRole',
			data : {
				'id' : roleid
			},
			cache : false,
			async : true,
			success : function(data) {
				result = $.parseJSON(data);
				if (result.success) {
					layer.closeAll('dialog');
					roleTable.api().ajax.reload(null,false);
				} else {
					layer.alert(result.message);
				}
			}
		});
	});
}

function searchRole() {
	var roleName = $("#roleName").val();
    roleTable.fnSettings().ajax.data = {
        "roleName": roleName,
    };
	var url = ctx + '/role/getRoleByCondition';
	roleTable.api().ajax.url(url).load();
}
