/**
  * @Description:  管理员管理
  * 
  * @Author MING
  * @Email  lmm_work@163.com
  * @Date   2019/1/22 10:06
　*/

layui.define(['table','form','configs','tree','util'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form
  ,configs = layui.configs
  ,admin = layui.admin
  ,tree = layui.tree
  ,util = layui.util;



    $.ajax({
        url: configs.base_server + 'sys/menu/list'
        ,type:'get'
        ,success:function (data) {
            var menulList = data.data;
           console.log(menulList);
            var menulList = treeMenulList(menulList);

            function treeMenulList(jsonObj) {
                var parentmenulList = [];
                var childmenulList = [];
                $.each(jsonObj, function (index, item) {
                    if(item.parentId == 0){
                    //    parentmenulList.add()
                    }
                });
            }
        }
    });



      //模拟数据1
     var data1 = [
         {
          title: '江西'
          ,id: 1
          ,children: [{
              title: '南昌'
              ,id: 1000
              ,children: [{
                  title: '青山湖区'
                  ,id: 10001
              },{
                  title: '高新区'
                  ,id: 10002
              }]
          },{
              title: '九江'
              ,id: 1001
          },{
              title: '赣州'
              ,id: 1002
          }]
         },{
          title: '广西'
          ,id: 2
          ,children: [{
              title: '南宁'
              ,id: 2000
          },{
              title: '桂林'
              ,id: 2001
          }]
      },{
          title: '陕西'
          ,id: 3
          ,children: [{
              title: '西安'
              ,id: 3000
          },{
              title: '延安'
              ,id: 3001
          }]
      }];

    //按钮事件
    util.event('lay-demo', {
        getChecked: function(othis){
            var checkedData = tree.getChecked('demoId1'); //获取选中节点的数据

            //  layer.alert(JSON.stringify(checkedData), {shade:0});
            //  console.log(checkedData);
            var ids = getCheckedId(checkedData);
            console.log(ids);
            function getCheckedId(jsonObj) {
                var id = "";
                $.each(jsonObj, function (index, item) {
                    if (id != "") {
                        id = id + "," + item.id;
                    }
                    else {
                        id = item.id;
                    }
                    var i = getCheckedId(item.children);
                    if (i != "") {
                        id = id + "," + i;
                    }
                });
                return id;
            }
        }
        ,setChecked: function(){
            tree.setChecked('demoId1', [3001]); //勾选指定节点
        }
        ,reload: function(){
            //重载实例
            tree.reload('demoId1', {

            });

        }
    });

    //无连接线风格
    tree.render({
        elem: '#test13'
        ,data: data1
        ,id: 'demoId1'
        ,showLine: false  //是否开启连接线
        ,showCheckbox: true
    });


  exports('roleform', {})
});