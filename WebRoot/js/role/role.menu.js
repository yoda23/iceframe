var setting = {
	data : {
		simpleData : {
			enable : true,
			idKey : "id",
			pIdKey : "parentId",
			rootPId : 0
		},
		key : {
			name : "name"
		}
	},
	check : {
		enable : true,
		chkStyle : "checkbox",
		chkboxType : {
			"Y" : "ps",
			"N" : "ps"
		}
	},
	callback : {
		onCheck : menuTreeOnCheck
	}
};
$(function() {
	createTree();
	initFormValid();
	$("#close").click(function() {
		layer_close();
	});
});

function menuTreeOnCheck() {
	var treeObj = jQuery.fn.zTree.getZTreeObj("roleAddMenu");
	var nodes = treeObj.getCheckedNodes(true);
	var msg = "";
	for ( var i = 0; i < nodes.length; i++) {
		if ((i + 1) === nodes.length) {
			msg += nodes[i].id;
		} else {
			msg += nodes[i].id + ",";
		}
	}
	jQuery('#menuId').val(msg);
}
function initFormValid() {
	$("#roleMenuForm").validate({
		rules : {
			name : {
				required : true,
				minlength : 2,
				maxlength : 32
			},
			reMark : {
				required : true,
				minlength : 2,
				maxlength : 32
			}
		},
		submitHandler : function(form) {
			updateMenuForRole();
		}
	});
}
function updateMenuForRole() {
	$.ajax({
		type : "POST",
		url : ctx + '/role/updateMenuForRole',
		data : $("#roleMenuForm").serialize(),
		cache : false,
		async : true,
		success : function(data) {
            var result = $.parseJSON(data);
			if (result.success) {
				layer_close();
			} else {
				layer.alert(result.message);
			}
		}
	});
}
