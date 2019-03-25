function sexFormatter(value,row,index){
	if(value==0){
		return '男';
	}
	else if(value==1){
		return '女';
	}
	else
		return '未知';
}


function initGoodsType(componentid){
	YJGet('/manage/goods/getGoodsTypeTree',null,function(datas){
		if(datas.success){    			
			$(componentid).combobox({
				data:datas.data
			});
		}
	});
}

initAreaByParentId = function(parentId,componentid,callback){
	YJGet('/common/area/getAreaByParent',{parentid:parentId},function(datas){
		if(datas.success){    			
			$(componentid).combobox({
				data:datas.data
			});
			callback();
		}
	});
}

function initGoodsUnit(componentid){
	YJGet('/manage/goods/getGoodsUnitList',null,function(datas){
		if(datas.success){    			
			$(componentid).combobox({
				data:datas.data
			});
		}
	});
}

function intOrgType(componentid,style){
	YJGet('/common/org/getOrgTypeBill',{type:style,status:0},function(datas){
		if(datas.success){    			
			$(componentid).combobox({
				data:datas.data
			});
		}
	});
}


function initCustomerTypeForSelect(componentid){
	YJGet('/common/org/getOrgTypeTree?type=2&isParent=1&status=0',null,function(datas){
		if(datas.success){    			
			$(componentid).combobox({
				data:datas.data
			});
		}
	});
}

function initOrganizationCombox(componentid){
	YJGet('/common/org/getOrgBill?rows=0',null,function(datas){
		if(datas.success){    			
			$(componentid).combobox({
				data:datas.data
			});
		}
	});
}



openAddOrEditDialog = function(title,url,width,height,loadCallback,callback){
	$('#MyPopWindow').dialog({
		title:title,
		closed:false,
		cache:false,
		href:url,
		width:width,
		resizable:true,
		height:height,
		modal:true,
		buttons:[
		         {	 text:'保存',
		        	 iconCls:'icon-ok',
		        	 id:'pop_window_save',
		        	 handler:function(){
		        		 callback();
		        	 }},
		         {	text:'取消',
		        	iconCls:'icon-cancel',
		        	handler:function(){
		        		$('#MyPopWindow').dialog('close');
		        	}}],
		onLoad: function(){
			loadCallback();
		}
	  });
}


function uploadFile(fileId,fileUrlId,type){
	var filepath = $('#'+fileId).val();
	if(checkInputFileIsValid(filepath,".JPG,.JPEG,.GIF,.PNG,.BMP")){		
		$.ajaxFileUpload({
				url : '/common/file/upload',
				fileElementId:fileId,
				type : 'POST',
				dataType : 'json',
				data:{type:type,name:'hqfile'},
				error : function(data,status,e) {			
					var str = data.responseText;
					var index = str.indexOf("{");
					var last  = str.lastIndexOf("}");
					  
					str = str.substring(index,last+1);
					var json = JSON.parse(str);
					if(json.success){
						$('#fileUrlId').val(json.data.url);
						$('#'+fileId+'_div').html("<img src='"+json.data.smallUrl+"' width='50' height='50'/>");
					}
					else
						error(json.message);
				},
				success : function(json) {
					console.log('success:'+json);
					//fileUrlId
				}
			});

	}
}