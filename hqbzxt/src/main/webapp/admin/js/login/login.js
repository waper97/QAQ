var layer;
layui.use(['form','layer'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
        sessionStorage.clear();
    	localStorage.clear();
    
    //登录按钮
    form.on("submit(login)",function(data){
        $(this).text("登录中...").attr("disabled","disabled").addClass("layui-disabled");
        var jsonData = {
        		'username':$('#username').val(),
        		'pwd':getMd52($('#password').val()),
        		'code':$('#code').val()};
        post('/user/manage/login',jsonData,function(data){
        	if(data.success){
        		setTokenData(data.data.token);
        		setStorageKey("user",data.data.name);
//        		setStorageKey("org",data.data.org);
//        		setStorageKey("org_id",data.data.orgid);
//        		setStorageKey("locKey",getMd52($('#password').val()));
        		location.href='index.html';
        	}
        	else{
        		error(data.message);
        		imgCode();        		
        		$('#btn_login').text("登录").removeAttr("disabled").removeClass("layui-disabled");
        	}
        })
        
        return false;
    })

    //表单输入效果
    $(".loginBody .input-item").click(function(e){
        //e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function(){
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function(){
        $(this).parent().removeClass("layui-input-focus");
        if($(this).val() != ''){
            $(this).parent().addClass("layui-input-active");
        }else{
            $(this).parent().removeClass("layui-input-active");
        }
    })
    

    function imgCode(){
    	$("[name='img_code']").attr('src','js/images.jsp?'+ Math.floor(Math.random()*100));
    }
});
