/**
 * @Description: 平台属性管理
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
        ,url: configs.base_server +'attrInfo/attrInfoList'
        ,toolbar: true
        ,cols: [[
            {type: 'checkbox'}
            ,{field: 'id', title: '编号', templet: '<div>{{d.id}}</div>'}
            ,{field: 'attrName',title: '属性名称', templet: '<div>{{d.attrName}}</div>'}
            ,{title: '操作',  align:'center', fixed: 'right', toolbar: '#table-operation'}
        ]]
        ,page: true
        ,limit: 10
        ,height: 'full-80'
        ,text: {none: '一条数据也没有^_^'}
    });


    /* 一级分类 */
    $.ajax({
        url : configs.base_server + "catalog/catalog1List",
        type:'get',
        data:{},
        success:function (data) {

            var $html = "";
            if(data.catalog1List != null){
                $.each(data.catalog1List, function (index, item) {
                    $html += "<option value='" + item.id + "'>" + item.name + "</option>";
                });
                $("select[name='catalog1name']").append($html);

                //append后必须从新渲染
                form.render('select');
            }
        }
    })

    /**
     *  二级分类
     */
    form.on('select(catalog1name)', function(data){

        // 移除除了第一个之外的option
        $("select[name='catalog2name'] option:gt(0)").remove();
        $("select[name='catalog3name'] option:gt(0)").remove();

        form.render('select');

        var catalog1Id = data.value;

        $.ajax({
            url : configs.base_server + "catalog/catalog2List",
            type:'get',
            data:{catalog1Id : catalog1Id},
            success:function (data) {

                var $html = "";
                if(data.catalog2List != null && data.catalog2List.length > 0){
                    $.each(data.catalog2List, function (index, item) {
                        $html += "<option value='" + item.id + "'>" + item.name + "</option>";
                    });
                    $("select[name='catalog2name']").append($html);

                   //append后必须从新渲染
                    form.render('select');

                }

            }
        })
    });

    /**
     *  三级分类
     */
    form.on('select(catalog2name)', function(data){

        // $("select[name='catalog3name']").html("");
        $("select[name='catalog3name'] option:gt(0)").remove();

        form.render('select');

        var catalog2Id = data.value;

        $.ajax({
            url : configs.base_server + "catalog/catalog3List",
            type:'get',
            data:{catalog2Id : catalog2Id},
            success:function (data) {

                var $html = "";
                if(data.catalog3List != null && data.catalog3List.length > 0){
                    $.each(data.catalog3List, function (index, item) {
                        $html += "<option value='" + item.id + "'>" + item.name + "</option>";
                    });
                    $("select[name='catalog3name']").append($html);

                    //append后必须从新渲染
                    form.render('select');
                }
            }
        })
    });



    //监听搜索
    form.on('submit(lay-submit-search)', function(data){

        //执行重载
        table.reload('lay-tab', {
            where:{
                catalog3Id: data.field.catalog3name
            }
        });
    });




    exports('attrinfo', {})
});