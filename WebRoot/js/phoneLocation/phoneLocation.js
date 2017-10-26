var phoneLocationTable;
$(function() {
	initTable();
	
	$("#search").click(function() {
		search();
	});
});

//按照条件查询号段信息
function search() {
    phoneLocationTable.fnSettings().ajax.data = {
        "phoneHeader": $("#phoneHeader").val()
    };
	var url = ctx + '/phoneLocation/getPhoneLocationByCondition';
	phoneLocationTable.api().ajax.url(url).load();
}
