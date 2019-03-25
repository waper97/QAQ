
    (function($){
        function buildMenu(target){
            var state = $(target).data('datagrid');
            if (!state.columnMenu){
                state.columnMenu = $('<div></div>').appendTo('body');
                state.columnMenu.menu({
                    onClick: function(item){
                        if (item.iconCls == 'tree-checkbox1'){
                            $(target).datagrid('hideColumn', item.name);
                            $(this).menu('setIcon', {
                                target: item.target,
                                iconCls: 'tree-checkbox0'
                            });
                        } else {
                            $(target).datagrid('showColumn', item.name);
                            $(this).menu('setIcon', {
                                target: item.target,
                                iconCls: 'tree-checkbox1'
                            });
                        }
                    }
                })
                var fields = $(target).datagrid('getColumnFields',true).concat($(target).datagrid('getColumnFields',false));
                for(var i=0; i<fields.length; i++){
                    var field = fields[i];
                    if(field!='fk'){
                    	var col = $(target).datagrid('getColumnOption', field);
                    	if(col.hidden=='true'){
                    		state.columnMenu.menu('appendItem', {
    	                        text: col.title,
    	                        name: field,
    	                        iconCls: 'tree-checkbox0'
    	                    });
                    	}
                    	else{
    	                    state.columnMenu.menu('appendItem', {
    	                        text: col.title,
    	                        name: field,
    	                        iconCls: 'tree-checkbox1'
    	                    });
                    	}
                    }                
                }
            }
            return state.columnMenu;
        }
        $.extend($.fn.datagrid.methods, {
            columnMenu: function(jq){
                return buildMenu(jq[0]);
            }
        });
    })(jQuery);  
    
    //如果需要自动合并,则设置merge=true，在表格LoadSuccess 中调用$(this).datagrid("autoMergeCells");
    $.extend($.fn.datagrid.methods, {
        autoMergeCells : function (jq, fields) {
            return jq.each(function () {
                var target = $(this);
                if (!fields) {
                    fields = target.datagrid("getColumnFields");
                }
                
                var rows = target.datagrid("getRows");
                var i = 0,
                j = 0,
                temp = {};
                for (i; i < rows.length; i++) {
                    var row = rows[i];
                    j = 0;
                    for (j; j < fields.length; j++) {
                        var field = fields[j];
                        var col = $(target).datagrid('getColumnOption', field);
                    	if(field!='fk' && col.merge){
                    		var tf = temp[field];
                            if (!tf) {
                                tf = temp[field] = {};
                                tf[row[field]] = [i];
                            } else {
                                var tfv = tf[row[field]];
                                if (tfv) {
                                    tfv.push(i);
                                } else {
                                    tfv = tf[row[field]] = [i];
                                }
                            }
                    	}                        
                    }
                }
                $.each(temp, function (field, colunm) {
                	var col = $(target).datagrid('getColumnOption', field);
                	if(field!='fk' && col.merge){
                		var mergeCol = col.mergeCol;
                		$.each(colunm, function () {
                            var group = this;
                            if (group.length > 1) {
                                var before,
                                after,
                                megerIndex = group[0];
                                for (var i = 0; i < group.length; i++) {
                                    before = group[i];
                                    after = group[i + 1];
                                    if (after && (after - before) == 1 ) {
                                    	if(mergeCol!=null && mergeCol!=''){
                                    		if(rows[after][mergeCol]==rows[megerIndex][mergeCol]){
                                    			continue;
                                    		}
                                    	}
                                    	else{
                                    		continue;
                                    	}                                        
                                    }
                                    var rowspan = before - megerIndex + 1;
                                    //进行处理，h
                                    
                                    if (rowspan > 1) {
                                        target.datagrid('mergeCells', {
                                            index : megerIndex,
                                            field : field,
                                            rowspan : rowspan
                                        });
                                    }
                                    
                                    if(mergeCol!=null && mergeCol!=''){
                                    	if(after && (after - before) != 1 || (rows[after]!=null && field!=mergeCol && rows[after][mergeCol]!=rows[megerIndex][mergeCol])){
                                   		 	megerIndex = after;
                                    	}
                                    }
                                    else{
                                    	if(after && (after - before) != 1){
                                    		 megerIndex = after;
                                    	}
                                    }
                                }
                            }
                        });
                	}                    
                });
//                target.datagrid('appendRow',{ 关于小计的处理
//                	total: '123.14'
//                });
            });
        }
    });
    
   
    