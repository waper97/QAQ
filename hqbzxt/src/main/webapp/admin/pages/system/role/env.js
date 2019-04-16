$(function(){
	grid.datagrid({
		url:baseUrl+'/role/manage/getRoleList'
	});
	
	$('.cls_yj_add').click(function(){
		openAddDialog();
	});
	
	
	$('.cls_yj_edit').click(function(){
		var rows = grid.datagrid('getSelections');
		if(rows.length<=0){
    		error('请选择您要修改的角色！');
    		return;
    	}
		else{
			openEditDialog(rows[0]);
		}			
	});
	
	$('.cls_yj_auth').click(function(){
		var rows = grid.datagrid('getSelections');
		if(rows.length<=0){
    		error('请选择您要授权的角色！');
    		return;
    	}
		else{
			openAuthWindow(rows[0]);
		}			
	});
	
	$('.cls_yj_delete').click(function(){
		var rows = grid.datagrid('getSelections');
		if(rows.length<=0){
    		error('请选择您要删除的角色！');
    		return;
    	}
		else{
			affirm('您确定要删除该角色吗？',function(){
				YJPost('/role/manage/deleteRole',{'id':rows[0].id},function(data){
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
	});
})

function openEditDialog(obj){
	$('#MyPopWindow').dialog({
		title:'修改角色',
		closed:false,
		cache:false,
		iconCls: 'icon-edit',
		href:'form.html',
		width:600,
		resizable:true,
		height:400,
		modal:true,
		buttons:[
		         {	 text:'保存',
		        	 iconCls:'icon-ok',
		        	 id:'pop_window_save',
		        	 handler:function(){
		        		 submitForm();
		        	 }},
		         {	text:'取消',
		        	iconCls:'icon-cancel',
		        	handler:function(){
		        		$('#MyPopWindow').dialog('close');
		        	}}],
		onLoad: function(){
			$('#frm').form('clear');
			$("#frm").form('load', obj);
			$('#frm #roleCode').textbox('textbox').attr('readonly',true); 
		}
	  });	
}

function openAddDialog(){
	$('#MyPopWindow').dialog({
		title:'增加角色',
		closed:false,
		cache:false,
		iconCls: 'icon-add',
		href:'form.html',
		width:600,
		resizable:true,
		height:500,
		modal:true,
		buttons:[
		         {	 text:'保存',
		        	 iconCls:'icon-ok',
		        	 id:'pop_window_save',
		        	 handler:function(){
		        		 submitForm();
		        	 }},
		         {	text:'取消',
		        	iconCls:'icon-cancel',
		        	handler:function(){
		        		$('#MyPopWindow').dialog('close');
		        	}}],
		onLoad: function(){
			$('#frm').form('clear');
		}
	  });
}


function openAuthWindow(obj){
	$('#MyPopWindow').dialog({
		title:'角色授权【'+obj.roleName+'】',
		closed:false,
		cache:false,
		iconCls: 'icon-add',
		href:'auth.html',
		width:600,
		resizable:true,
		height:500,
		modal:true,
		buttons:[
		         {	 text:'保存',
		        	 iconCls:'icon-ok',
		        	 id:'pop_window_save',
		        	 handler:function(){
		        		 submitForm();
		        	 }},
		         {	text:'取消',
		        	iconCls:'icon-cancel',
		        	handler:function(){
		        		$('#MyPopWindow').dialog('close');
		        	}}],
		onLoad: function(){
			$('#frm').form('clear');
			getRoleHaveRes(obj.id,obj.roleType);
		}
	  });
}

function submitForm(){
	var r = $('#frm').form('validate');
	if(!r) {
		return false;
	}
	YJPost("/res/manage/saveRole",$("#frm").serialize(),function(data){
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