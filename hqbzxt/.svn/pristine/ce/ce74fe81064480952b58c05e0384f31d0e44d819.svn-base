$(function(){
	$("#tt").tree({  
        url:baseUrl+'/res/manage/getResTree',
        onLoadSuccess:function(node,data){
        },
        onBeforeLoad: function (node, param) {
   		 $("<div class=\"datagrid-mask\"></div>").css({ display: "block", width: "100%", height: "100%" }).appendTo("#tt");
            $("<div class=\"datagrid-mask-msg\"></div>").html("正在加载数据，请稍待...").appendTo("#tt").css({ display: "block" });
        }
	});
	
	
	grid.datagrid({
		url:baseUrl+'/manage/system/auth/res/list'
	});
	
	$('.cls_yj_add').click(function(){
		openAddDialog();
	});
	
	
	$('.cls_yj_edit').click(function(){
		var rows = grid.datagrid('getSelections');
		if(rows.length<=0){
    		error('请选择您要修改的资源！');
    		return;
    	}
		else{
			openEditDialog(rows[0]);
		}			
	});
	
	$('.cls_yj_delete').click(function(){
		var rows = grid.datagrid('getSelections');
		if(rows.length<=0){
    		error('请选择您要删除的资源！');
    		return;
    	}
		else{
			var id = rows[0].id;
			
			affirm('您确定要删除该资源吗？',function(){
				YJPost('/manage/system/auth/res/delete',{'id':id},function(data){
					if(data.success){
						msg(data.message);
						grid.datagrid('reload'); 
						var node = $('#tt').tree('find',id); 
						if(node)
							$('#tt').tree('remove',node.target); 
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
		title:'修改资源',
		closed:false,
		cache:false,
		iconCls: 'icon-edit',
		href:'form.html',
		width:600,
		resizable:true,
		height:550,
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
			$('#frm #parentDiv').hide();
		}
	  });	
}


function submitForm(){
	var r = $('#frm').form('validate');
	if(!r) {
		return false;
	}
	YJPost("/manage/system/auth/res/save",$("#frm").serialize(),function(data){
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


function doQuery(){
	var paramObj 	= new Object(); ;
	paramObj.parentid 	= $('#pid').val();
	grid.datagrid('options').queryParams = paramObj;
	grid.datagrid('reload'); //设置好查询参数 reload 一下就可以了
}
	
	function clickTreeNode(node){
		var gridPanel = $("#gridFrm").datagrid("getPanel");//先获取panel对象
		gridPanel.panel('setTitle', '【'+node.text+'】的下级资源列表');
		$('#pid').val(node.id);
		$('#pname').val(node.text); 
		$('#ptype').val(node.type);
		doQuery();
	}
	
	function openAddDialog(){
		if($('#pid').val()==""){
			$.messager.alert('提示','请选择您要添加的资源所属的上级资源！','info');
			return;
		}
		$('#MyPopWindow').dialog({
			title:'增加资源',
			closed:false,
			cache:false,
			iconCls: 'icon-add',
			href:'form.html',
			width:600,
			height:550,
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
				initForm($('#pid').val(),$('#pname').val(),$('#ptype').val());
			}
		  });
	}

	
	function refreshTree(node){
		var pnode = $('#tt').tree('find',node.parentid); 
		var cnode = $('#tt').tree('find',node.id); 
		
		if(cnode){ //该节点已存在	
			cnode.text = node.name;
			cnode.type = node.rightType;
			$("#tt").tree("update", cnode);					
		}
		else { //新增的节点		
			var treeNode = new Object();
			treeNode.id = node.id;
			treeNode.text = node.name;
			treeNode.type = node.rightType;
			
			$('#tt').tree('append', {
				parent:pnode.target,
				data:treeNode
			});
		}
	}
	
	function initForm(id,name,type){			
		$('#parentName').textbox('setValue',name);
		$('#parentid').val(id);	
		$('#rightType').combobox('setValue',0);
	}
	
	function submitForm(){
		var r = $('#frm').form('validate');
		if(!r) {
			return false;
		}		
	
		YJPost("/manage/system/auth/res/save",$("#frm").serialize(),function(data){
			if(data.success){
				$('#MyPopWindow').window('close');
				$('#gridFrm').datagrid('reload');
				msg(data.message);
				refreshTree(data.data);
			}
			else{
				error(data.message);
			}
		})		
	}	