/**
 *  内容管理
 */

layui.define(['table','form','upload','laytpl'],function (exports) {

    var $ = layui.$,
        table = layui.table,
        form = layui.form,
        admin = layui.admin,
        configs = layui.configs,
        upload = layui.upload,
        laytpl= layui.laytpl;



    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');



    $("#getAllHtml").click(function(){
        alert(UE.getEditor('editor').getAllHtml());
    });

    $("#getContent").click(function(){
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getContent());
        alert(arr.join("\n"));
    });

    $("#setContent").click(function(){
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getContent());
        alert(arr.join("\n"));
    });


    $("#zsetContent").click(function(){
        var isAppendTo = true;
        var arr = [];
        arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
        UE.getEditor('editor').setContent('欢迎使用ueditor', isAppendTo);
        alert(arr.join("\n"));

    });

    $("#getContentTxt").click(function(){
        var arr = [];
        arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
        arr.push("编辑器的纯文本内容为：");
        arr.push(UE.getEditor('editor').getContentTxt());
        alert(arr.join("\n"));
    });


    $("#getPlainTxt").click(function(){
        var arr = [];
        arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getPlainTxt());
        alert(arr.join('\n'));
        console.log(UE.getEditor('editor').getPlainTxt());
    });

    $("#hasContent").click(function(){

        var arr = [];
        arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
        arr.push("判断结果为：");
        arr.push(UE.getEditor('editor').hasContents());
        alert(arr.join("\n"));
    });

    $("#setFocus").click(function(){
        UE.getEditor('editor').focus();
    });


    $("#getText").click(function(){
        //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
        var range = UE.getEditor('editor').selection.getRange();
        range.select();
        var txt = UE.getEditor('editor').selection.getText();
        alert(txt)
    });

    $("#insertHtml").click(function(){
        var value = prompt('插入html代码', '');
        UE.getEditor('editor').execCommand('insertHtml', value)
    });

    $("#enable").click(function(){
        UE.getEditor('editor').setEnabled();
        enableBtn();
    });

    $("#setDisabled").click(function(){
        UE.getEditor('editor').setDisabled('fullscreen');
        disableBtn("enable");
    });

    $("#setHide").click(function(){
        UE.getEditor('editor').setHide();
    });

    $("#setShow").click(function(){
        UE.getEditor('editor').setShow();
    });

    $("#setHeight").click(function(){
        UE.getEditor('editor').setHeight(300);
    });

    $("#getLocalData").click(function(){
        alert(UE.getEditor('editor').execCommand( "getlocaldata" ));
    });

    $("#clearLocalData").click(function(){
        UE.getEditor('editor').execCommand( "clearlocaldata" );
        alert("已清空草稿箱")
    });


    $("#createEditor").click(function(){
        enableBtn();
        UE.getEditor('editor');
    });

    $("#deleteEditor").click(function(){
        disableBtn();
        UE.getEditor('editor').destroy();
    });

    $("#isFocus").onmousedown(function(event){
        alert(UE.getEditor('editor').isFocus());
        UE.dom.domUtils.preventDefault(event);
    });

    $("#setblur").onmousedown(function(event){
        UE.getEditor('editor').blur();
        UE.dom.domUtils.preventDefault(event);
    });

    // 失去焦点不生效
    /*function isFocus(e){
        alert(UE.getEditor('editor').isFocus());
        UE.dom.domUtils.preventDefault(e)
    }
    function setblur(e){
        UE.getEditor('editor').blur();
        UE.dom.domUtils.preventDefault(e)
    }*/



    function disableBtn(str) {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            if (btn.id == str) {
                UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
            } else {
                btn.setAttribute("disabled", "true");
            }
        }
    }


    function enableBtn() {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
        }
    }


    exports('content', {})
});