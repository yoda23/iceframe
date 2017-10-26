$(function() {
	initFormValid();
});

/*
 * 初始化表单验证
 */
function initFormValid() {
	$("#updateForm").validate({
		rules : {
			prefix : {
				required : true,
				digits : true,
				minlength : 3,
				maxlength : 3
			},
			operator : {
				required : true

			}

		},
		submitHandler : function(form) {
			updatePhoneOperator();
		}
	});
}
// 修改运营商信息
function updatePhoneOperator() {
	layer.confirm('确定要修改此条数据么？', {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		$.ajax({
			type : "POST",
			url : ctx + '/phoneOperator/updatePhoneOperator',
			data : $("#updateForm").serialize(),
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

	});
}
