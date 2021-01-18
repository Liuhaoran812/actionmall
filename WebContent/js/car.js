
//减数量
function reductionOf(obj) {
    //减前判断
    if ($(obj).next().val() == '') {
        $(obj).next().val(1);
    }
    $(obj).next().val(parseInt($(obj).next().val()) - 1);//数值减
    $(obj).next().val($(obj).next().val());//赋值给框
};
//加数量
function add(obj) {
    //加前判断
    if ($(obj).prev().val() == '') {
        $(obj).prev().val(1);
    }
    $(obj).prev().val(parseInt($(obj).prev().val()) + 1);//数值加
    $(obj).prev().val($(obj).prev().val());//赋值给框
};
//校验数字格式（只能输入正整数）
function checkNumber(obj) {
    var reg = /^[1-9]\d*$/;
    if(!reg.test($(obj).val()) || $(obj).val()==''){
        $(obj).val(1);
    }
}