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
        onCheck: userTreeOnCheck,
    }
};
$(function () {
    createTree();
    // 初始化表单
    initFormValid();
    // 初始化角色数据
    initRole();
    $("#closeUserAdd").click(function () {
        closeUserAdd();
    })
    $("#menuAll").click(function () {
        menuAll();
    })
    $("#menuCancelAll").click(function () {
        menuCancelAll();
    })
    $("#closeUserAdd").click(function () {
        closeUserAdd();
    })
});

function createTree() {
    $.ajax({
        url: ctx + '/mechanisms/getMechanismsForUserMenuTree',
        type: 'POST',
        success: function (data) {
            jQuery.fn.zTree.init(jQuery("#userTree"), setting, eval('('
                + data + ')'));
            var treeObj = jQuery.fn.zTree.getZTreeObj("userTree");
            treeObj.expandAll(true);
        },
        error: function (msg) {
            layer.alert(msg);
        }
    });
}

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
            var result = $.parseJSON(data);
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

/*
 * 初始化表单验证
 */
function initFormValid() {
    $("#userAddForm").validate({
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
            },
            loginPassword: {
                required: true
            },
            userAddPassword2: {
                required: true,
                equalTo: "#userAddPassword"
            }, parentName: {
                required: true,
                minlength: 2,
                maxlength: 32
            }
        },
        submitHandler: function (form) {
            saveUserAdd();
        }
    });
}

function saveUserAdd() {
    $.ajax({
        type: "POST",
        url: ctx + '/user/saveUser',
        data: $("#userAddForm").serialize(),
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
}

function closeUserAdd() {
    layer_close();
}
