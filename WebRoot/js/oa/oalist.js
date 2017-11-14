var tableList;
$(function() {
    initTable();
    $("#add").click(function () {
        oaAdd();
    });
});

function oaAdd() {
    layer.open({
        type : 2,
        area : [ '800px', '500px' ],
        fix : false, // 不固定
        maxmin : false,
        shade : 0.4,
        title : '公众号',
        content : ctx + '/redirect?page=oa/addOa',
        end : function() {
            tableList.api().ajax.reload();
        }
    });
}

