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
	callback : {
		onClick : menuTreeOnClick
	}
};
$(function() {
	createTree();
	initFormValid();
	$("#close").click(function() {
		layer_close();
	});
});
function createTree() {
	$.ajax({
		url : ctx + '/mechanisms/getMechanismsForMenuTree',
		type : 'POST',
		success : function(data) {
			jQuery.fn.zTree.init(jQuery("#mechanismsTree"), setting, eval('('
					+ data + ')'));
			var treeObj = jQuery.fn.zTree.getZTreeObj("mechanismsTree");
			treeObj.expandAll(true);
		},
		error : function(msg) {
			layer.alert(msg);
		}
	});
}
function menuTreeOnClick(event, treeId, treeNode) {
	$("#parentName").val(treeNode.name);
	$("#parentId").val(treeNode.id);
}
function initFormValid() {
	$("#mechanismsAddForm").validate({
		rules : {
			name : {
				required : true,
				minlength : 2,
				maxlength : 32
			},
			parentName : {
				required : true,
				minlength : 2,
				maxlength : 32
			}
		},
		submitHandler : function(form) {
			UpdateMechanisms();
		}
	});
}
function UpdateMechanisms() {
	$.ajax({
		type : "POST",
		url : ctx + '/mechanisms/updateMechanisms',
		data : $("#mechanismsAddForm").serialize(),
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
