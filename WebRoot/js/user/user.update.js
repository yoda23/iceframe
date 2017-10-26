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
    }, check: {
        enable: true,
        chkStyle: "checkbox",
        chkboxType: {
            "Y": "",
            "N": ""
        }
    },
    callback: {
        onClick: userTreeOnClick,
        onCheck: userTreeOnCheck
    }
};
$(function () {
    createTree();
    initFormValid();
    initRole();
    $("#menuAll").click(function () {
        menuAll();
    });
    $("#menuCancelAll").click(function () {
        menuCancelAll();
    });
    $("#close").click(function () {
        layer_close();
    });
});

function userTreeOnClick(event, treeId, treeNode) {
    $("#parentName").val(treeNode.name);
    $("#mechanismsId").val(treeNode.id);
}

function userTreeOnCheck(event, treeId, treeNode) {
    var treeObj = jQuery.fn.zTree.getZTreeObj("userTree");
    var nodes = treeObj.getCheckedNodes(true);
    var msg = "";
    for (var i = 0; i < nodes.length; i++) {
        if ((i + 1) === nodes.length) {
            msg += nodes[i].id;
        } else {
            msg += nodes[i].id + ",";
        }
    }
    console.info(msg);
    jQuery('#mechanismsIdCheck').val(msg);
}

//树形全选
function menuAll() {
    var treeObj = $.fn.zTree.getZTreeObj("userTree");
    treeObj.checkAllNodes(true);
    var nodes = treeObj.getCheckedNodes(true);
    var msg = "";
    for (var i = 0; i < nodes.length; i++) {
        if ((i + 1) === nodes.length) {
            msg += nodes[i].id;
        } else {
            msg += nodes[i].id + ",";
        }
    }
    console.info(msg);
    jQuery('#mechanismsIdCheck').val(msg);
}

//树形全取消
function menuCancelAll() {
    var treeObj = $.fn.zTree.getZTreeObj("userTree");
    treeObj.checkAllNodes(false);
    var nodes = treeObj.getCheckedNodes(true);
    var msg = "";
    for (var i = 0; i < nodes.length; i++) {
        //if (!nodes[i].getCheckStatus().half) {
        if ((i + 1) === nodes.length) {
            msg += nodes[i].id;
        } else {
            msg += nodes[i].id + ",";
        }
        // }
    }
    console.info(msg);
    jQuery('#mechanismsIdCheck').val(msg);
}

/*
 * 初始化表单验证
 */
function initFormValid() {
    $("#userUpdateForm").validate({
        rules: {
            loginId: {
                required: true,
                minlength: 2,
                maxlength: 32
            },
            name: {
                required: true,
                minlength: 2,
                maxlength: 32
            }
        },
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function (form) {
            saveUserUpdate();
        }
    });
}

/*
 * 初始化角色数据
 */
function initRole() {
    var roleSelect = $("#roleSelect");
    $.ajax({
        type: "POST",
        url: ctx + '/role/getRoleByAll',
        cache: false,
        async: false,
        success: function (data) {
            result = $.parseJSON(data);
            if (result !== null && result.length > 0) {
                roleSelect.empty();
                for (var i = 0; i < result.length; i++) {
                    roleSelect.append(
                        "<option value='" + result[i].id + "'>"
                        + result[i].name + "</option>");
                }
            }
        }
    });
}

function saveUserUpdate() {
    layer.confirm('确定要修改此条数据么？', {
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        $.ajax({
            type: "POST",
            url: ctx + '/user/updateUser',
            data: $("#userUpdateForm").serialize(),
            cache: false,
            async: true,
            success: function (data) {
                var result = $.parseJSON(data);
                if (result.success) {
                    layer_close();
                } else {
                    layer.alert(result.message);
                }
            }
        });
    }, function () {

    });
}
