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



    //普通图片上传

    var uploadInst = upload.render({
        elem: '#uploadimage'
        ,url: '/pic/upload/'
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#uploadimg').attr('src', result); //图片链接（base64）
            });
        }
        ,done: function(res){
            //如果上传失败
            if(res.code > 0){
                return layer.msg('上传失败');
            }
            //上传成功
            var img_url = res.url;
            alert("测试路径：："+img_url);
            $("#image_url").val('上传成功'+img_url);
        }
        ,error: function(){
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                uploadInst.upload();
            });
        }
    });

    //监听提交
    form.on('submit(lay-user-submit)', function(data){
        var content = UE.getEditor('editor').getContent();
        //  拼接 html
        var prehtml = "<html>\n" +
            "<head>\n" +
            "<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\"/>\n" +
            "</head>\n" +
            "\n" +
            "\t<body>";

        var endhtml = "\t</body>\n" +
            "</html>"

        data.field.html = prehtml + content + endhtml;
        layer.alert(JSON.stringify(data.field), {
            title: '最终的提交信息'
        })

        var field = data.field;

        $.ajax({
            url : "../../bus/content/save"
            ,type : 'post'
            ,data : field
            ,success :function (data) {
                if(data.code == 0){
                    layer.msg("添加成功",{icon: 1});

                }else{
                    layer.msg(data.msg,{icon: 2});
                }
                table.reload('lay-tab');
            }
        });

        return false;
    });


    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');

    /**
     *   自定义请求地址  参考地址 ： http://fex.baidu.com/ueditor/#qa-customurl
     *
     *   uploadimage：//执行上传图片或截图的action名称
     *   uploadscrawl：//执行上传涂鸦的action名称
     *   uploadvideo：//执行上传视频的action名称
     *   uploadfile：//controller里,执行上传视频的action名称
     *   catchimage：//执行抓取远程图片的action名称
     *   listimage：//执行列出图片的action名称
     *   listfile：//执行列出文件的action名称
     */
    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    UE.Editor.prototype.getActionUrl = function(action) {
        if (action == 'uploadimage' || action == 'uploadscrawl') {
           // alert("http://localhost:8866/ueditor/imgUpload");
            return 'http://127.0.0.1:8866/ueditor/imgUpload';
        }else if(action=='uploadvideo'){
            return 'http://127.0.0.1:8866/ueditor/vedioUpload';
        }else {
            return this._bkGetActionUrl.call(this, action);
        }
    }



    $("#getAllHtml").click(function(){
        alert(UE.getEditor('editor').getAllHtml());
        console.log(UE.getEditor('editor').getAllHtml());
    });

    $("#getContent").click(function(){
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getContent());
        alert(arr.join("\n"));
        console.log(UE.getEditor('editor').getContent());
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

    // $("#isFocus").onmousedown(function(event){
    //     alert(UE.getEditor('editor').isFocus());
    //     UE.dom.domUtils.preventDefault(event);
    // });
    //
    // $("#setblur").onmousedown(function(event){
    //     UE.getEditor('editor').blur();
    //     UE.dom.domUtils.preventDefault(event);
    // });

    // 失去焦点不生效
    function isFocus(e){
        alert(UE.getEditor('editor').isFocus());
        UE.dom.domUtils.preventDefault(e)
    }
    function setblur(e){
        UE.getEditor('editor').blur();
        UE.dom.domUtils.preventDefault(e)
    }



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



    // 图片上传


    exports('content', { });
});