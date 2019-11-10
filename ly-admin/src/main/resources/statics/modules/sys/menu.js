var editObj=null,ptable=null,treeGrid=null,tableId='treeTable',layer=null;

var define = layui.config({
    base : '../../../../statics/modules/extend-table/'
}).extend({
    treeGrid : 'treeGrid'
}).define(['table','treeGrid','form','layer'], function(exports) {
    var $ = layui.$
        ,table = layui.table
        ,form = layui.form
        ,admin = layui.admin
        ,layer = layui.layer
        ,treeGrid = layui.treeGrid; //很重要;

        ptable=treeGrid.render({
            id:tableId
            ,elem: '#'+tableId
            ,idField:'id'
            ,url:'../../sys/menu/list'
            ,cellMinWidth: 100
            ,treeId:'menuId'//树形id字段名称
            ,treeUpId:'parentId'//树形父id字段名称
            ,treeShowName:'name'//以树形式显示的字段
            ,cols: [[
                {type:'checkbox', fixed: 'left'}
                ,{field:'name',width:200, title: '菜单名称'}
                ,{field:'icon',width:100, title: '图标'}
                ,{field:'type',width:100, title: '类型'}
                ,{field:'menuId',width:100, title: '排序'}
                ,{field:'url',width:100, title: '路由'}
                ,{field:'url',width:100, title: '授权标识'}
                ,{width:100,title: '操作', align:'center'/*toolbar: '#barDemo'*/
                    ,templet: function(d){
                        var html='';
                        var addBtn='<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="add">添加</a>';
                        var delBtn='<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>';
                        return addBtn+delBtn;
                    }
                }
            ]]
            ,page:false
        });

        treeGrid.on('tool('+tableId+')',function (obj) {
            if(obj.event === 'del'){//删除行
                del(obj);
            }else if(obj.event==="add"){//添加行
                add(obj.data);
            }
        });

exports('menu', {})
});