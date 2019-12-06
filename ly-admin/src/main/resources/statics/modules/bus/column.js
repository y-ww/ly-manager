/**
 * @Description:  栏目菜单
 * @Email: lmm_work@163.com
 * @Author: SLIGHTLEE
 * @Date: 2019/11/27 5:18 下午
 */
layui.define(function (exports) {
    var element = layui.element
        ,$ = layui.$,
        configs = layui.configs,

        column ={

            getColumn : function(){

                $.ajax({
                    url: configs.base_server + 'bus/tc/columnList'
                    ,type:'post'
                    ,data:{ parentId : parentId,ptCode : ptCode}
                    ,success:function (data) {
                        console.log(data);
                        // 获取栏目列表
                        var columnList = data.columnList;
                        // 遍历栏目
                        for (var i = 0;i < columnList.length;i++){


                        }

                    }
                });

            }
        }

    //对外暴露的接口
    exports('column',column);
});


