$(function(){ 
	grid.datagrid({
		url:'/manage/system/user/list'
	})
	
	initOrganizationCombox('#orgId');
	
	$('.cls_yj_add').click(function(){
		openAddDialog();
	});
});

layui.use(['layer'],function(){
    layer = parent.layer === undefined ? layui.layer : top.layer;
});

function openAddDialog(){
	openAddOrEditDialog('增加用户','form.html',600,400,function(){
		$('#frm').form('clear');
		initOrganizationCombox('#frm #orgId');
	},function(){
		saveSubmit();
	})
}

function exeAuth(id,name){
	openAddOrEditDialog('用户【'+name+'】授权','auth.html',600,400,function(){
		$('#frm').form('clear');
		initUserRoles(id);
	},function(){
		saveAuth(id);
	})
}

function exeResetPwd(id){
	affirm('您确定要重置该用户的密码吗？',function(){
		YJPost('manage/system/user/resetPwd',{'userid':id},function(data){
			if(data.success){
				msg(data.message);
				grid.datagrid('reload'); 
			}
			else{
				error(data.message);
			}
		});
	});	
	}

function exeEdit(id){
	openAddOrEditDialog('修改用户信息','form.html',600,400,function(){
		$('#frm').form('clear');
		initOrganizationCombox('#frm #orgId');
		loadUserInfo(id);
	},function(){
		saveSubmit();
	})
}


function saveAuth(uid){
	//获取
	var roleStr = $("input:checkbox[name='roles']:checked").map(function(index,elem) {
        return $(elem).val();
    }).get().join(',');
	
	var jsonData = {'userid':uid,'roles':roleStr};

	YJPost('/manage/system/user/saveRoles',jsonData,function(data){
		if(data.success){
			msg(data.message);
			$('#MyPopWindow').dialog('close');
		}
		else{
			error(data.message);
		}
	});
	
}

function saveSubmit(){
	var r = $('#frm').form('validate');
	if(!r) {
		return false;
	}
	YJPost('/manage/system/user/saveUser',$("#frm").serialize(),function(data){
		if(data.success){
			msg(data.message);
			$('#MyPopWindow').dialog('close');
			grid.datagrid('reload'); 
		}
		else{
			error(data.message);
		}
	})
}


function operationFormatter(value,row,index){
	var html = "<a href='#' onclick=exeResetPwd('"+row.userid+"')>重置密码</a>&emsp;";
	html += "<a href='#' onclick=exeEdit('"+row.userid+"')>修改</a>&emsp;";
	html += "<a href='#' onclick=exeAuth('"+row.userid+"','"+row.trueName+"')>授权</a>&emsp;";
	return html;
}


function doQuery(){
	var paramObj 		= new Object(); ;
	paramObj.orgId 		= $('#orgId').combobox('getValue');
	paramObj.userName 	= $('#userName').val();
	paramObj.trueName 	= $('#trueName').val();
	paramObj.beginTime 	= $('#beginTime').val();
	paramObj.endTime 	= $('#endTime').val();
	grid.datagrid('options').queryParams = paramObj;
	grid.datagrid('reload'); //设置好查询参数 reload 一下就可以了
}