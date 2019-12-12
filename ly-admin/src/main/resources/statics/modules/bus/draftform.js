/**
 *  内容管理
 */
layui.define(['table','form','configs','upload','laytpl'],function (exports) {

    var $ = layui.$,
        table = layui.table,
        form = layui.form,
        configs = layui.configs,
        upload = layui.upload,
        laytpl= layui.laytpl;



    //普通图片上传

    var uploadInst = upload.render({
        elem: '#uploadimage'
        ,url: configs.base_server + 'pic/upload/'
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
            //alert("测试路径：："+img_url);
            $("#image_url").val(img_url);
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

    //监听提交 保存到数据库
    form.on('submit(lay-user-submit)', function(data){

        data.field.isFbtype = 1;
        addContent(data);
    });

    //监听提交 保存到草稿箱
    form.on('submit(lay-user-content)', function(data){
        data.field.isFbtype = 0;
        addContent(data);
    });

    function addContent(data){

        var uecontent = UE.getEditor('editor').getContent();
        //  拼接 html
        var prehtml = "<html>\n" +
            "<head>\n" +
            "<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\"/>\n" +
            "</head>\n" +
            "\n" +
            "\t<body>";

        var endhtml = "\t</body>\n" +
            "</html>"

        data.field.content = prehtml + uecontent + endhtml;
        /*layer.alert(JSON.stringify(data.field), {
            title: '最终的提交信息'
        });*/

        var field = data.field;

        $.ajax({
            url : configs.base_server + "bus/content/save"
            ,type : 'post'
            ,data : field
            ,success :function (data) {
                if(data.code == 0){
                    layer.msg("添加成功",{icon: 1,time:2000},function () {
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                        parent.location.reload();//刷新
                    });
                }else{
                    layer.msg(data.msg,{icon: 2});
                }
              //  table.reload('lay-tab');
            }
        });

    }

    /* 栏目类别 */

    $.ajax({
        url : configs.base_server + "bus/tc/column",
        type:'post',
        data:{parentId : 0},
        success:function (data) {

            var $html = "";
            if(data.columnList != null){
                $.each(data.columnList, function (index, item) {
                    if(item.columnName != "首页" && item.columnName != "十大平台" && item.columnName != "平台排行榜"){
                        $html += "<option value='" + item.id + "'>" + item.columnName + "</option>";
                    }
                });
                $("select[name='colid']").append($html);

                //append后必须从新渲染
                form.render('select');
            }
        }
    })




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
            return configs.base_server + 'ueditor/imgUpload';
        }else if(action=='uploadvideo'){
            return configs.base_server + 'ueditor/vedioUpload';
        }else {
            return this._bkGetActionUrl.call(this, action);
        }
    }

    exports('draftform', {});
});