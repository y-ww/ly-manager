/**
 *  内容管理
 */
layui.config({
    base : '../../../../statics/modules/treeSelect/'
}).extend({
    treeSelect : 'treeSelect'
}).define(['table','form','configs','treeSelect','upload','laytpl'],function (exports) {

    var $ = layui.$,
        table = layui.table,
        form = layui.form,
        configs = layui.configs,
        treeSelect = layui.treeSelect,
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
                    layer.msg("添加成功",{icon: 1,time:1500},function () {
                        location.reload();
                    });
                }else{
                    layer.msg(data.msg,{icon: 2});
                }
                table.reload('lay-tab');
            }
        });

    }

    /* 栏目类别 */

    $.ajax({
        url : configs.base_server + "bus/tc/column",
        type:'post',
        //data:{parentId : 0},
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


    $.ajax({
        url: configs.base_server + 'bus/tc/list'
        ,type:'get'
        ,success:function (data) {
            var columnList = data.columnList;
            console.log(columnList);

            var newList = newMenuList(columnList);

            function newMenuList(arr) {

                let treeArr = arr;
                let temp = [];

                treeArr.forEach((item, index) => {

                    let newTreeArr = treeArr;

                    newTreeArr[index] = {};
                    newTreeArr[index].id = item.id;
                    newTreeArr[index].parentId = item.parentId;
                    newTreeArr[index].name = item.columnName;
                    newTreeArr[index].open = 'false';

                    temp.push(newTreeArr[index]);
                   
                });

                return temp;
            }

            var authMenulList = arrayToTree(newList , 0);

            function arrayToTree(arr, parentId) {

                //  arr 是返回的数据  parendId 父id
                let temp = [];

                let treeArr = arr;

                treeArr.forEach((item, index) => {

                    if (item.parentId == parentId) {

                        if (arrayToTree(treeArr, treeArr[index].id).length > 0) {

                            // 递归调用此函数

                           treeArr[index].children = arrayToTree(treeArr, treeArr[index].id);
                        }

                        temp.push(treeArr[index]);

                    }

                });
                return temp;
            }
            //    console.log(arrayToTree(newList , 0));   // 第一级的父目录id是0；写死调用

           // alert(authMenulList);
            console.log(authMenulList);

            /*for (let i = 0; i < authMenulList.length ; i++) {
                if(authMenulList[i].hasOwnProperty('children')){
                    // 得到子类的值，并对子类值 转换json 字符串
                   var newChildren = authMenulList[i].children;
                   let nchildren = JSON.stringify(newChildren);
                    // 移除子类
                    delete authMenulList[i].children;
                    var ccc = authMenulList[i];
                    // 对当前对象 转换json 字符串
                    let curList = JSON.stringify(authMenulList[i]);
                    // 添加子类
                    curList.children = nchildren;
                    curList;
                    console.log(true);
                }else{
                    console.log(false);
                }
            }*/


            treeSelect.render({
                // 选择器
                elem: '#tree',
                // 数据
                data: authMenulList,
                // 请求头
                headers: {},
                // 异步加载方式：get/post，默认get
                type: 'get',
                // 占位符
                placeholder: '修改默认提示信息',
                // 是否开启搜索功能：true/false，默认false
                search: true,
                // 一些可定制的样式
                style: {
                    folder: {
                        enable: false
                    },
                    line: {
                        enable: true
                    }
                },
                // 点击回调
                click: function(d){
                    console.log(d);
                },
                // 加载完成后的回调函数
                success: function (d) {
                    console.log(d);
//                选中节点，根据id筛选
                    //     treeSelect.checkNode('tree', 5);

                    console.log($('#tree').val());

//                获取zTree对象，可以调用zTree方法
                    var treeObj = treeSelect.zTree('tree');
                    console.log(treeObj);

//                刷新树结构
                    treeSelect.refresh('tree');
                }
            });


        }

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
            return configs.base_server + 'ueditor/imgUpload';
        }else if(action=='uploadvideo'){
            return configs.base_server + 'ueditor/vedioUpload';
        }else {
            return this._bkGetActionUrl.call(this, action);
        }
    }


/*
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
    }*/


    exports('content', {});
});