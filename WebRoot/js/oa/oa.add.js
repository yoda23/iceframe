$(function () {
    // 初始化表单
    initFormValid();

});

/*
 * 初始化表单验证
 */
function initFormValid() {
    $("#oaform").validate({
        rules: {
            signInName: {
                required: true,
                minlength: 2,
                maxlength: 50
            },
            startDate: {
                required: true,
                minlength: 2,
                maxlength: 50
            },
            endDate: {
                required: true,
                minlength: 2,
                maxlength: 50
            },
            singInState: {
                required: true,
                minlength: 1,
                maxlength: 50
                // digits:true
            },
            remark: {
                required: true,
                minlength: 2,
                maxlength: 50
            }
        },
        submitHandler: function (form) {
            saveOaAdd();
        }
    });
}

function saveOaAdd() {

    $.ajax({
        type: "POST",
        url: ctx + '/oa/saveSignInInfo',
        data: $("#oaform").serialize(),
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

