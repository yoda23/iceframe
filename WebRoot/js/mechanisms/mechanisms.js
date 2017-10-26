var mechanismsTable;
var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "parentId",
            rootPId: 0
        },
        key: {
            name: "name"
        }
    },
    callback: {
        onClick: menuTreeOnClick
    }
};
$(function () {
    createTree();
    initMechanismsTable();
    $("#mechanismsAdd").click(function () {
        addMechanisms();
    });
    $("#mechanismsSearch").click(function () {
        mechanismsSearch();
    })
});

function createTree() {
    $.ajax({
        url: ctx + '/mechanisms/getMechanismsForMenuTree',
        type: 'POST',
        success: function (data) {
            jQuery.fn.zTree.init(jQuery("#mechanismsTree"), setting, eval('('
                + data + ')'));
            var treeObj = jQuery.fn.zTree.getZTreeObj("mechanismsTree");
            treeObj.expandAll(true);
        },
        error: function (msg) {
            layer.alert(msg);
        }
    });
}

function menuTreeOnClick(event, treeId, treeNode) {
    console.info(treeNode);
    $("#mechanismsId").val(treeNode.id);
    mechanismsOnClick();
}

function addMechanisms() {
    layer.open({
        type: 2,
        area: ['800px', '500px'],
        fix: false, // 不固定
        maxmin: false,
        shade: 0.4,
        title: '地市添加',
        content: ctx + '/redirect?page=/mechanisms/mechanismsAdd&parentName='
        + $("#parentName").val() + '&parentId=' + $("#parentId").val(),
        end: function () {
            window.location.href = ctx
                + '/redirect?page=/mechanisms/mechanisms';
        }
    });
}

function toShowUpdateMechanisms(id) {
    layer.open({
        type: 2,
        area: ['800px', '500px'],
        fix: false, // 不固定
        maxmin: false,
        shade: 0.4,
        title: '组织地市修改',
        content: ctx + '/mechanisms/toUpdateMechanisms?id=' + id,
        end: function () {
            window.location.href = ctx
                + '/redirect?page=/mechanisms/mechanisms';
        }
    });
}

function deleteMechanisms(id) {
    layer.confirm('确定要删除此条数据么？', {
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        $.ajax({
            type: "POST",
            url: ctx + '/mechanisms/deleteMechanisms',
            data: {
                'id': id
            },
            cache: false,
            async: true,
            success: function (data) {
                var result = $.parseJSON(data);
                if (result.success) {
                    window.location.href = ctx
                        + '/redirect?page=/mechanisms/mechanisms'
                } else {
                    layer.alert(result.message);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            }
        });
    });
}

function mechanismsSearch() {
    var mechanismsName = $("#mechanismsName").val();
    var extParam;
    extParam = {
        "mechanismsName": mechanismsName
    };
    mechanismsTable.fnSettings().ajax.data = extParam;
    var url = ctx + '/mechanisms/getListMechanismByCondition';
    mechanismsTable.api().ajax.url(url).load();
}

function mechanismsOnClick() {
    var mechanismsId = $("#mechanismsId").val();
    mechanismsTable.fnSettings().ajax.data = {
        "mechanismsId": mechanismsId,
    };
    var url = ctx + '/mechanisms/getListMechanismByMechanismsFlag';
    mechanismsTable.api().ajax.url(url).load();
}
