var classType = 1;
$(function(){
	grid.datagrid({
		url:baseUrl+'/common/org/getOrgTypeBill?type='+classType
	});
	
	$('.cls_yj_add').click(function(){
		openAddDialog();
	});
	
})

function doQuery(){
	var paramObj 	= new Object();
	paramObj.name	= $('#name').val();
	paramObj.code	= $('#code').val();
	paramObj.status = $('#status').combobox('getValue');
	grid.datagrid('options').queryParams = paramObj;
	grid.datagrid('reload'); //设置好查询参数 reload 一下就可以了
}

function statusOperationFormatter(value,row,index){
	if(value==0){
		return "<span style='cursor:pointer' onclick=exeUpdateState("+row.id+",1,'停用')>停用</span>";
	}
	else
		return "<span style='cursor:pointer' onclick=exeUpdateState("+row.id+",0,'启用')>启用</span>";
}


function operationFormatter(value,row,index){
	if(row.status==1){  //已停用
		return "<span style='cursor:pointer' onclick=exeDel("+row.id+",1,'删除')>删除</span>";
	}
	else{
		var html = "<span style='cursor:pointer' onclick=exeEdit("+row.id+",'"+row.name+"')>编辑</span>&emsp;";
		html += "<span style='cursor:pointer' onclick=exeDel("+row.id+",1,'删除')>删除</span>";
		return html;
	}
}

function exeDel(id){
	affirm('您确定要删除该分类吗？',function(){
		YJPost('/manage/org/deleteOrgType',{'id':id},function(data){
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
		YJPost('/manage/org/disableOrgType',{'id':id,'status':state},function(data){
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
	openAddOrEditDialog('修改分类','form.html',function(){
		var obj = new Object();
		obj.id = id;
		obj.name = name;
		$('#frm').form('clear');
		$("#frm").form('load', obj);
	},function(){
		submitForm();
	})
}


function openAddDialog(){
	openAddOrEditDialog('增加分类','form.html',function(){
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
	
	var url = '/manage/org/updateOrgType';
	if($('#frm #id').val()==''){
		url = '/manage/org/addOrgType';
	}
	$('#frm #type').val(classType);
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