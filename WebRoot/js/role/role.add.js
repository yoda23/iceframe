$(function() {
	initFormValid();
	$("#closeRoleAdd").click(function() {
		closeRoleAdd();
	});
});
function initFormValid() {
	$("#roleAddForm").validate({
		rules : {
			name : {
				required : true,
				minlength : 2,
				maxlength : 32
			}
		},
		submitHandler : function(form) {
			saveRoleAdd();
		}
	});
}
function saveRoleAdd() {
	$.ajax({
		type : "POST",
		url : ctx + '/role/saveRole',
		data : $("#roleAddForm").serialize(),
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
function closeRoleAdd() {
	layer_close();
}