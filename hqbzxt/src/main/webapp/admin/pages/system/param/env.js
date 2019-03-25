$(function(){
	grid.datagrid({
		url:baseUrl+'/params/manage/list'
	});
	
	$('.cls_yj_add').click(function(){
		openAddDialog();
	});
	
	$('.cls_yj_reload').click(function(){
		YJGet('/params/manage/reload',{},function(data){
			if(data.success){
				msg(data.message);
				grid.datagrid('reload'); 
			}
			else{
				error(data.message);
			}
		});
	});
	
	
	$('.cls_yj_edit').click(function(){
		var rows = grid.datagrid('getSelections');
		if(rows.length<=0){
    		error('请选择您要修改的系统参数');
    		return;
    	}
		else{
			openEditDialog(rows[0]);
		}			
	});
	
	$('.cls_yj_delete').click(function(){
		var rows = grid.datagrid('getSelections');
		if(rows.length<=0){
    		error('请选择您要删除的参数');
    		return;
    	}
		else{
			affirm('您确定要删除该参数吗？',function(){
				YJPost('/params/manage/delete',{'id':rows[0].id},function(data){
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
		title:'修改参数',
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
			$('#frm #paramCode').textbox('textbox').attr('readonly',true); 
		}
	  });	
}

function openAddDialog(){
	$('#MyPopWindow').dialog({
		title:'增加系统参数',
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

function submitForm(){
	var r = $('#frm').form('validate');
	if(!r) {
		return false;
	}
	YJPost("/params/manage/save",$("#frm").serialize(),function(data){
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