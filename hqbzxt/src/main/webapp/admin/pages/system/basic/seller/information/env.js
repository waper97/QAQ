var classType = 1;
$(function(){
	initGoodsType('#goodsType');
	intOrgType("#orgType",classType);
	grid.datagrid({
		url:baseUrl+'/common/org/getOrgBill?orgStyle='+classType
	});
	
	$('.cls_yj_add').click(function(){
		openAddDialog();
	});
	
})

function queryReset(){
	$("#name").textbox('setValue','');
	$("#code").textbox('setValue','');
	$('#orgType').combobox('clear');
	$('#goodsType').combobox('clear');
	$('#status').combobox('clear'); 
}

function doQuery(){
	var paramObj 		= new Object();
	paramObj.name		= $('#name').val();
	paramObj.code		= $('#code').val();
	paramObj.orgType	= $('#orgType').combobox('getValue');
	paramObj.scopeType	= $('#goodsType').combobox('getValue');
	paramObj.status 	= $('#status').combobox('getValue');
	grid.datagrid('options').queryParams = paramObj;
	grid.datagrid('reload'); //设置好查询参数 reload 一下就可以了
}

function statusOperationFormatter(value,row,index){
	if(value==0){
		return "<span style='cursor:pointer' onclick=exeUpdateState("+row.orgid+",1,'停用')>停用</span>";
	}
	else
		return "<span style='cursor:pointer' onclick=exeUpdateState("+row.orgid+",0,'启用')>启用</span>";
}


function operationFormatter(value,row,index){
	if(row.status==1){  //已停用
		return "<span style='cursor:pointer' onclick=exeDel("+row.orgid+",1,'删除')>删除</span>";
	}
	else{
		var html = "<span style='cursor:pointer' onclick=exeEdit("+row.orgid+",'"+row.name+"')>编辑</span>&emsp;";
		html += "<span style='cursor:pointer' onclick=exeDel("+row.orgid+",1,'删除')>删除</span>";
		return html;
	}
}

function exeDel(id){
	affirm('您确定要删除该商家吗？',function(){
		YJPost('/manage/org/deleteOrg',{'orgid':id},function(data){
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

function exeUpdateState(id,state,op){
	affirm('您确定要执行【'+op+'】操作吗？',function(){
		YJPost('/manage/org/disableOrg',{'orgid':id,'status':state},function(data){
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

function exeEdit(id,name){
	openAddOrEditDialog('修改商家信息','form.html',1000,700,function(){
		initOrganizationFormData(id);
	},function(){
		submitForm();
	})
}

function initOrganizationFormData(id){
	YJGet('/common/org/getOrgDetail',{orgid:id},function(data){
		if(data.success){
			$("#frm").form('load', data.data);
		}
		else{
			//
		}
	})
}

function openAddDialog(){
	openAddOrEditDialog('增加商家','form.html',1000,700,function(){
		$('#frm').form('clear');
	},function(){
		submitForm();
	})
}

function submitForm(){
	var r = $('#frm').form('validate');
	if(!r) {
		return false;
	}
	
	var url = '/manage/org/addOrg';
	if($('#frm #id').val()==''){
		url = '/manage/org/updateOrg';
	}
	$('#frm #orgStyle').val(classType);
	$('#frm #openSeller').val(1);
	$('#frm #area').val($('#first').combobox('getValue')+'-'+$('#second').combobox('getValue')+'-'+$('#third').combobox('getValue'));
	YJPost(url,$("#frm").serialize(),function(data){
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


layui.use(['layer'],function(){
    layer = parent.layer === undefined ? layui.layer : top.layer;
})