$(function () {
    // 初始化表单
    initFormValid();

});

/*
 * 初始化表单验证
 */
function initFormValid() {
    $("#gzhForm").validate({
        rules: {
            appId: {
                required: true,
                minlength: 2,
                maxlength: 50
            }
        },
        submitHandler: function (form) {
            saveGzhAdd();
        }
    });
}

function saveGzhAdd() {

    $.ajax({
        type: "POST",
        url: ctx + '/gzh/saveAppInfo',
        data: $("#gzhForm").serialize(),
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

