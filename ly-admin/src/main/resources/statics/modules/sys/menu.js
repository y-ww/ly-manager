var ptable=null,tableId='treeTable';

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
                ,{title: '图标',toolbar: '#table-menu-icon', minWidth: 80, width: 100}
                ,{title: '类型',toolbar: '#table-menu-type', minWidth: 80, width: 100}
                ,{field:'menuId',width:100, title: '排序'}
                ,{field:'url',width:250, title: '路由'}
                ,{field:'',width:200, title: '授权标识'}
                ,{title: '操作', align:'center'
                    ,templet: function(d){
                        var html='';
                        var addBtn='<a class="layui-btn layui-btn-xs" lay-event="add"><i class="layui-icon layui-icon-edit"></i>编辑</a>';
                        var delBtn='<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete">删除</a>';
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