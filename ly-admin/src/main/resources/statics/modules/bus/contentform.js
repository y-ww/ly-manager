/**
  * @Description:  管理员管理
  * 
  * @Author MING
  * @Email  lmm_work@163.com
  * @Date   2019/1/22 10:06
　*/

layui.define(['table','form','configs','upload','laytpl'], function(exports){
    var $ = layui.$,
        table = layui.table,
        form = layui.form,
        configs = layui.configs,
        upload = layui.upload,
        laytpl= layui.laytpl;


    var ue = UE.getEditor('editor');

    ue.ready(function() {
        var html = $("#editor_val").val();
        UE.getEditor('editor').execCommand('insertHtml',html);
    });

    exports('contentform', {});
});