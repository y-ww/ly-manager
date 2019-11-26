/**
  * @Description:  管理员管理
  * 
  * @Author MING
  * @Email  lmm_work@163.com
  * @Date   2019/1/22 10:06
　*/

layui.define(['table', 'form'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form
  ,admin = layui.admin;

  //用户管理
  table.render({
    elem: '#LAY-user-manage'
    ,url: '/bus/content/contentlist'
    ,toolbar: true
    ,cols: [[
      {type: 'checkbox', fixed: 'left'}
      ,{field: 'title', width: 350, title: '标题', sort: true}
      ,{field: 'summary', title: '摘要', minWidth: 80, width: 350}
      ,{field: 'createTime',title: '创建时间',sort: true}
      ,{title: '状态',toolbar: '#table-content-status', minWidth: 80, width: 100}
      ,{title: '操作', width: 150, align:'center', fixed: 'right', toolbar: '#table-user-admin'}
    ]]
    ,page: true
    ,limit: 10
    ,height: 'full-80'
    ,text: '对不起，加载出现异常！'
  });


    //监听搜索
    form.on('submit(LAY-user-front-search)', function(data){
        var field = data.field;

        //执行重载
        table.reload('LAY-user-manage', {
            where: field
        });
    });



    //事件
    var active = {
        del: function(){
            var checkStatus = table.checkStatus('LAY-user-manage')
                ,checkData = checkStatus.data //得到选中的数据
                ,userIds= [];   //  定义空数组 用来存放 多个id   var userId = new Array();
            for (var index in checkData){
                console.log(checkData[index].userId);
                userIds.push(checkData[index].userId)
            }
            var  aa = JSON.stringify(userIds);

            if(checkData.length === 0){
                return layer.msg('请选择数据');
            }
            layer.confirm('确定删除吗？', function() {

                $.ajax({
                    type: "POST",
                    url : "../../sys/user/delete",
                    contentType: "application/json",
                    data: JSON.stringify(userIds),
                    success: function(data){
                        if(data.code == 0){
                            layer.msg("删除成功",{icon: 1});

                        }else{
                            layer.msg(data.msg,{icon: 2});
                        }
                        table.reload('LAY-user-manage');
                    }
                });

            });
        }
    };

    $('.layui-btn.ud-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });


  //监听工具条
  table.on('tool(LAY-user-manage)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){

      var userIds = [];
      userIds.push(data.userId);

        $.ajax({
            type: "POST",
            url : "../../sys/user/delete",
            contentType: "application/json",
            data: JSON.stringify(userIds),
            success: function(data){
                if(data.code == 0){
                    layer.msg("删除成功",{icon: 1});

                }else{
                    layer.msg(data.msg,{icon: 2});
                }
                table.reload('LAY-user-manage');
            }
        });

    } else if(obj.event === 'edit'){

       layer.open({
        type: 2
        ,title: '编辑内容'
        ,content: 'contentform.html'
        ,maxmin: true
        ,area: ['500px', '450px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
          var iframeWindow = window['layui-layer-iframe'+ index]
          ,submitID = 'LAY-user-front-submit'
          ,submit = layero.find('iframe').contents().find('#'+ submitID);

          //监听提交
          iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
            var field = data.field;
            field.userId = obj.data.userId;
              var status;
              if('switch' in field){  // 包含该元素
                  status = 1 ;
              }else{
                  status = 0;
              }
              field.status = status;
              console.log(field);
            // 修改
            $.ajax({
                type: "POST",
                url : "../../sys/user/update",
                contentType: "application/json",
                data: JSON.stringify(field),
                success: function(data){
                    if(data.code == 0){
                        layer.msg("修改成功",{icon: 1});

                    }else{
                        layer.msg(data.msg,{icon: 2});
                    }
                    table.reload('LAY-user-manage');
                }
            });
            layer.close(index); //关闭弹层
          });  
          
          submit.trigger('click');
        }
        ,success: function(layero, index){

        /*      二者等价
        var iframeWindow = window['layui-layer-iframe'+ index];
        var iframeWin = window[layero.find('iframe')[0]['name']];

         var div = layero.find('iframe').contents().find('#layuiadmin-form-useradmin');
         var body = layer.getChildFrame('body', index);
        */
          var div = layero.find('iframe').contents().find('#layuiadmin-form-useradmin');

            var userdata = obj.data;

               div.find('#title').val(userdata.title);
               div.find('#editor_val').val(userdata.content);
               div.find('#uploadimg').attr("src",userdata.minPicAddress);

        }
      });
    }
  });

    var ue = UE.getEditor('editor');

    ue.ready(function() {
        var html = $("#editor_val").val();
        UE.getEditor('editor').execCommand('insertHtml',html);
    });


    exports('contentlist', {})
});