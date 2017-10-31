var tableList;
$(function() {
    initTable();
	$("#tpAdd").click(function() {
		addTp();
	});

});
//添加运营商
function addTp() {
	layer.open({
		type : 2,
		area : [ '800px', '500px' ],
		fix : false, // 不固定
		maxmin : false,
		shade : 0.4,
		title : '投票项',
		content : ctx + '/redirect?page=tp/addtpInfo',
		end : function() {
            tableList.api().ajax.reload();
		}
	});
}
//跳转到修改页面
// function toUpdatePhoneOperator(id) {
// 	layer.open({
// 		type : 2,
// 		area : [ '800px', '500px' ],
// 		fix : false, // 不固定
// 		maxmin : false,
// 		shade : 0.4,
// 		title : '运营商修改',
// 		content : ctx + '/phoneOperator/toUpdatePhoneOperator?id=' + id,
// 		end : function() {
// 			phoneOperatorTable.api().ajax.reload(null, false);
// 		}
// 	});
// }
// //删除运营商
// function deletePhoneOperator(id) {
// 	layer.confirm('确定要删除此条数据么？', {
// 		btn : [ '确定', '取消' ]
// 	// 按钮
// 	}, function() {
// 		$.ajax({
// 			type : "POST",
// 			url : ctx + '/phoneOperator/deletePhoneOperator?id=' + id,
// 			cache : false,
// 			async : true,
// 			success : function(data) {
// 				result = $.parseJSON(data);
// 				if (result.success) {
// 					layer.closeAll('dialog');
// 					phoneOperatorTable.api().ajax.reload(null, false);
// 				} else {
// 					layer.alert(result.message);
// 				}
// 			}
// 		});
// 	});
// }
//按照条件查询运营商识别信息
// function searchPhoneOperator() {
//     phoneOperatorTable.fnSettings().ajax.data = {
//         "name": $("#searchOperator").val()
//     };
// 	var url = ctx + '/phoneOperator/getPhoneOperatorByCondition';
// 	phoneOperatorTable.api().ajax.url(url).load();
// }
