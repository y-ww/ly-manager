/**
 * @Description: 栏目管理
 *
 * @Author LIMING
 * @Email  lmm_work@163.com
 * @Date   2019/3/1 10:21
 */

layui.define(['table','form','upload','laytpl'],function (exports) {

    var $ = layui.$,
        table = layui.table,
        form = layui.form,
        admin = layui.admin,
        configs = layui.configs,
        upload = layui.upload,
        laytpl= layui.laytpl;

    //平台属性信息显示
    table.render({
        elem: '#lay-tab'
        ,url: configs.base_server +'bus/tc/columnList'
        ,toolbar: true
        ,cols: [[
            {type: 'checkbox', fixed: 'left'}
            ,{field: 'id', title: '栏目编号'}
            ,{field: 'columnName', title: '栏目名称', minWidth: 100}
            ,{title: '显示状态',toolbar: '#table-user-status', minWidth: 80}
            ,{title: '操作', width: 160, align:'center', fixed: 'right', toolbar: '#table-user-admin'}
        ]]
        ,page: true
        ,limit: 10
        ,height: 'full-80'
        ,text: {none: '一条数据也没有^_^'}
    });


    //  按钮开关状态修改
    form.on('switch(status_filter)',function (obj) {

        var field = {};
        field.id = obj.elem.value;
        field.showList = obj.elem.checked ? 1 : 0;

        $.ajax({
            url : configs.base_server + "bus/tc/update",
            type : "post",
            contentType: "application/json",
            data: JSON.stringify(field),
            success:function (data) {
                console.log(data);
                if(data.code == 0){
                    layer.msg("操作成功",{icon: 1});
                }else{
                    layer.msg("操作失败",{icon: 2});
                }
                table.reload('lay-tab');
            }
        });


    });



    exports('column', {})
});