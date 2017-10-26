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
		onCheck : rightsTreeOnCheck
	}
};
$(function() {
	createRightsTree();
	initFormValid();
	$("#close").click(function() {
		layer_close();
	});
});

function rightsTreeOnCheck() {
	var treeObj = jQuery.fn.zTree.getZTreeObj("roleAddRights");
	var nodes = treeObj.getCheckedNodes(true);
	var msg = "";
	for ( var i = 0; i < nodes.length; i++) {
		if ((i + 1) === nodes.length) {
			msg += nodes[i].id;
		} else {
			msg += nodes[i].id + ",";
		}
	}
	jQuery('#rightsId').val(msg);
}
function initFormValid() {
	$("#roleRightsForm").validate({
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
			updateRightsForRole();
		}
	});
}
function updateRightsForRole() {
	$.ajax({
		type : "POST",
		url : ctx + '/role/updateRightsForRole',
		data : $("#roleRightsForm").serialize(),
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
