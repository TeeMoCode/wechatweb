function postByAjax(url, para,token, callback) {
    var checkCodeValue = $("#checkCode").val();
    $.ajax(url, {
        data : para,
        type : "post",
        headers :{"X-CSRF-TOKEN":token},
        success : function(data) {
            callback(data);
        }
    });

}
var wait = 60;
function time(o) {
    if (wait == 0) {
        o.removeAttr("disabled");
        o.removeClass("weui-btn_disabled");
        o.addClass("weui-btn_primary");
        o.val("获取手机验证码");
        wait = 60;
    } else {
        o.attr("disabled", true);
        o.removeClass("weui-btn_primary");
        o.addClass("weui-btn_disabled");
        o.val(wait + "秒后可重发");
        wait--;
        setTimeout(function () {
            time(o)
        }, 1000)
    }
}