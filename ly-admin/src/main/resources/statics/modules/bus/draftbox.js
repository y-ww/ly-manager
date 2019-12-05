/**
  * @Description:  管理员管理
  * 
  * @Author MING
  * @Email  lmm_work@163.com
  * @Date   2019/1/22 10:06
　*/

layui.define(['table','form','configs'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form
  ,configs = layui.configs;


  table.render({
    elem: '#LAY-table-manage'
    ,url: configs.base_server + 'bus/content/draftlist'
    ,toolbar: true
    ,cols: [[
      {type: 'checkbox', fixed: 'left'}
      ,{field: 'title', width: 350, title: '标题', sort: true}
      ,{field: 'summary', title: '摘要', minWidth: 80, width: 350}
      ,{field: 'createTime',title: '发布时间',sort: true}
      ,{title: '状态',toolbar: '#table-content-status', minWidth: 80, width: 100}
      ,{title: '操作', width: 150, align:'center', fixed: 'right', toolbar: '#table-user-admin'}
    ]]
    ,page: true
    ,limit: 10
    ,height: 'full-80'
    ,text: {
          none : '暂无数据'
      }
  });


    //监听搜索
    form.on('submit(LAY-table-search)', function(data){
        var field = data.field;

        //执行重载
        table.reload('LAY-table-manage', {
            where: field
        });
    });



    //事件
    var active = {
        del: function(){
            var checkStatus = table.checkStatus('LAY-table-manage')
                ,checkData = checkStatus.data //得到选中的数据
                ,ids= [];   //  定义空数组 用来存放 多个id   var ids = new Array();
            for (var index in checkData){
                console.log(checkData[index].id);
                ids.push(checkData[index].id)
            }
            var  aa = JSON.stringify(ids);

            if(checkData.length === 0){
                return layer.msg('请选择数据');
            }
            layer.confirm('确定删除吗？', function() {

                $.ajax({
                    type: "POST",
                    url : configs.base_server + "bus/content/update",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function(data){
                        if(data.code == 0){
                            layer.msg("删除成功",{icon: 1});

                        }else{
                            layer.msg(data.msg,{icon: 2});
                        }
                        table.reload('LAY-table-manage');
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
  table.on('tool(LAY-table-manage)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){

      var ids = [];
        ids.push(data.id);

        $.ajax({
            type: "POST",
            url : configs.base_server + "bus/content/update",
            contentType: "application/json",
            data: JSON.stringify(ids),
            success: function(data){
                if(data.code == 0){
                    layer.msg("删除成功",{icon: 1});

                }else{
                    layer.msg(data.msg,{icon: 2});
                }
                table.reload('LAY-table-manage');
            }
        });

    } else if(obj.event === 'edit'){

      var index = layer.open({
        type: 2
        ,title: '编辑内容'
        ,content: 'draftform.html'
        ,maxmin: true
        ,area: ['500px', '450px']
      //  ,btn: ['确定', '取消']
        ,success: function(layero, index){

        /*      二者等价
        var iframeWindow = window['layui-layer-iframe'+ index];
        var iframeWin = window[layero.find('iframe')[0]['name']];

         var div = layero.find('iframe').contents().find('#layuiadmin-form-useradmin');
         var body = layer.getChildFrame('body', index);
        */
              var contentdata = obj.data;
              selectrender(layero,contentdata.colid);
              var iframeWindow = window['layui-layer-iframe'+ index];
              layero.find('iframe').contents().find('[name="id"]').val(contentdata.id);
              layero.find('iframe').contents().find('[name="title"]').val(contentdata.title);
              layero.find('iframe').contents().find('[name="summary"]').val(contentdata.summary);
              layero.find('iframe').contents().find('#editor_val').val(contentdata.content);
              layero.find('iframe').contents().find('#uploadimg').attr("src",contentdata.minPicAddress);
              layero.find('iframe').contents().find('#image_url').val(contentdata.minPicAddress);


        }
      });

      // 默认弹窗显示最大化
        layer.full(index);
    }
  });

  function selectrender(layero,data_value){

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
                  layero.find('iframe').contents().find('[name="colid"]').append($html);
                   //append后必须从新渲染
                  form.render('select');
              }
              // 渲染子页面下拉框
              layero.find('iframe').contents().find('[name="colid"]').val(data_value);
          }
      })
  }

    exports('draftbox', {});
});