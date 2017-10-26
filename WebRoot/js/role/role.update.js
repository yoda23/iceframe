$(function() {
	initFormValid();
	$("#close").click(function() {
		layer_close();
	});
});
function initFormValid() {
	$("#roleUpdateForm").validate({
		rules : {
			name : {
				required : true,
				minlength : 2,
				maxlength : 32
			}
		},
		submitHandler : function(form) {
			updateRole();
		}
	});
}
function updateRole() {
	layer.confirm('确定要修改此条数据么？', {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		$.ajax({
			type : "POST",
			url : ctx + '/role/updateRole',
			data : $("#roleUpdateForm").serialize(),
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
	}, function() {

	});
}
