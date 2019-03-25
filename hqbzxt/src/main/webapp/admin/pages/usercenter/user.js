layui.use(['form','layer'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    //添加验证规则
    form.verify({
        oldPwd : function(value, item){
            if(value.length < 1){
                return "请输入原密码！";
            }
        },
        newPwd : function(value, item){
            if(value.length < 6){
                return "密码长度不能小于6位";
            }
        },
        confirmPwd : function(value, item){
            if($("#newPwd").val()!=value){
                return "两次输入密码不一致，请重新输入！";
            }
        }
    })
    
    form.on("submit(changePwd)",function(data){
    	var jsonData = {'pwd':md5($('#oldPwd').val()).toUpperCase(),'newPwd':md5($('#newPwd').val()).toUpperCase()};
    	YJPost('/api/system/user/updatePwd',jsonData,function(data){
    		msg(data.msg);
    	})
    	return false;
    });
    //控制表格编辑时文本的位置【跟随渲染时的位置】
    $("body").on("click",".layui-table-body.layui-table-main tbody tr td",function(){
        $(this).find(".layui-table-edit").addClass("layui-"+$(this).attr("align"));
    });

})