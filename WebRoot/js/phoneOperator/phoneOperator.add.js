$(function() {
	initFormValid();
});

/*
 * 初始化表单验证
 */
function initFormValid() {
	$("#addForm").validate({
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
			savePhoneOperator();
		}
	});
}
// 保存运营商信息
function savePhoneOperator() {
	$.ajax({
		type : "POST",
		url : ctx + '/phoneOperator/savePhoneOperator',
		data : $("#addForm").serialize(),
		cache : false,
		async : true,
		success : function(data) {
			result = $.parseJSON(data);
			if (result.success) {
				layer_close();
			} else {
				layer.alert(result.message);
			}
		}
	});

}
