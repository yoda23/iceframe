$(function () {
    // 初始化表单
    initFormValid();

});

/*
 * 初始化表单验证
 */
function initFormValid() {
    $("#tpForm").validate({
        rules: {
            tpdescription: {
                required: true,
                minlength: 2,
                maxlength: 32
            }
        },
        submitHandler: function (form) {
            saveTpAdd();
        }
    });
}

function saveTpAdd() {
    $.ajax({
        type: "POST",
        url: ctx + '/tp/saveTp',
        data: $("#tpForm").serialize(),
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

