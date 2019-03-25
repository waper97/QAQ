//var http_sy = 'http://localhost:8080/syglxt';
//var baseUrl = 'http://localhost:8080/syglxt';
var http_sy = GetUrlRelativePath();
var baseUrl = GetUrlRelativePath();

var topWin = window.top;
	
function GetUrlRelativePath()
{
	 var curWwwPath = window.document.location.href;
	    var pathName = window.document.location.pathname;
	    var pos = curWwwPath.indexOf(pathName);
	    var localhostPaht = curWwwPath.substring(0, pos);
	    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	    if(projectName == '/admin')
	    	projectName = "";
	    return (localhostPaht + projectName)
	    
//	var url = window.location.href;
//	var arrUrl = url.split("//");
//	var start = arrUrl[1].indexOf("/");
//	var relUrl = arrUrl[0]+"//"+arrUrl[1].substring(0,start);
//	return relUrl;
//	return 'http://localhost:8080/syglxt';
}


	$(function(){
		var userAgent = navigator.userAgent; 
		var isOpera = userAgent.indexOf("Opera") > -1;
		var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera;
	
		if(isIE){
		  //获取版本号
		  var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
		  reIE.test(userAgent);
		  var fIEVersion = parseFloat(RegExp["$1"]);
		  if(fIEVersion<9){
		    window.location.href=url()+'/obsolete.htm';
		  }
		}
		
		document.onkeydown  = backSpaceCheck;  
		document.onkeypress = backSpaceCheck; 
		
		//checkTokenIsValid();
	});
	
	function checkOrgIsStartBusiness(){
		YJGet('/api/system/org/isStartBusiness',null,function(data){
			if(data.success){
				if(data.data.isstartbusiness=='0'){
					location.href='/page/common/unused.htm';
				}
			}
		})
	}

	String.prototype.endWith=function(str){    
		  var reg=new RegExp(str+"$");    
		  return reg.test(this);       
	};
	
	function backSpaceCheck(e) {  
	    var ev = e || window.event;//获取event对象  
	    var obj = ev.target || ev.srcElement;//获取事件源  
	    var t = obj.type || obj.getAttribute('type');//获取事件源类型  
	    if(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea"){  
	        return false;  
	    }   
	} 
	
	
	function getParamValueFromUrl(name){
		var str=window.location.search;
		if (str.indexOf(name)!=-1){
			var pos_start=str.indexOf(name)+name.length+1;
			var pos_end=str.indexOf("&",pos_start);
			if (pos_end==-1){
				return str.substring(pos_start);
			}
			else{
				return str.substring(pos_start,pos_end);
			}
		}
		else{
			return "";
		}
	}
	
	function toDecimal2(value){
		if(value == null || value==''){
			return "0.00";
		}
		else {
			return Number(value).toFixed(2);
		}
	}
	
	function checkTokenIsValid(){
		var  locationPage = window.location.pathname;
		if(locationPage.indexOf("login.html")<0){
			var token = getTokenData();
			if(typeof(token) == "undefined" || token==null || token==''){
				location.href='/login.html';
			}
		}	
	}
	
	String.prototype.endWith=function(str){    
		  var reg=new RegExp(str+"$");    
		  return reg.test(this);       
	};
	
	String.prototype.replaceAll = function(s1,s2){ 
		return this.replace(new RegExp(s1,"gm"),s2); 
	};
	
	
	Number.prototype.toFixed = function (n) { 
		if (n > 20 || n < 0) { 
			throw new RangeError('toFixed() digits argument must be between 0 and 20'); } 
		const number = this; 
		if (isNaN(number) || number >= Math.pow(10, 21)) { 
			return number.toString(); } 
		if (typeof (n) == 'undefined' || n == 0) { 
			return (Math.round(number)).toString(); 
		} 
		
		var result = number.toString(); 
		var arr = result.split('.');  
		if (arr.length < 2) { 
			result += '.'; 
			for (var i = 0; i < n; i += 1) { 
				result += '0'; 
			} 
			return result; 
		} 
		
		const integer = arr[0]; 
		var decimal = arr[1]; 
		if (decimal.length == n) { 
			return result;
		} 
		
		if (decimal.length < n) { 
			for (var i = 0; i < n - decimal.length; i += 1) { 
				result += '0'; 
			}
			return result; 
		} 
		result = integer + '.' + decimal.substr(0, n); 
		const last = decimal.substr(n, 1);  
		if (parseInt(last, 10) >= 5) { 
			const x = Math.pow(10, n); 
			result = (Math.round((parseFloat(result) * x)) + 1) / x;
			result = result.toFixed(n); 
		} 
		return result; 
	};

	//格式化日期
	Date.prototype.Format = function (fmt) { //author: meizz 
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "H+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	}

	
	function setStorageKey(key,data){
		localStorage.setItem(key,data);
	}
	
	function getStorageKey(key){
		var value = localStorage.getItem(key);
		if(typeof(value) == "undefined" || value=='undefined'){
			value = '';
		}
		return value;
	}
	
	function setTokenData(token){
		setStorageKey("token",token);
	}
	
	function getTokenData(){
		return getStorageKey("token");
	}
	
	function getMd52(str){
		return md5(md5(str).toUpperCase()).toUpperCase();
	}
	
	function accSub(arg1, arg2) {
		var r1, r2, m, n;
		try { r1 = arg1.toString().split(".")[1].length } catch (e) { r1 = 0 }
		try { r2 = arg2.toString().split(".")[1].length } catch (e) { r2 = 0 }
		m = Math.pow(10, Math.max(r1, r2));
		n = (r1 >= r2) ? r1 : r2;
		return ((arg1 * m - arg2 * m) / m).toFixed(n);
	}
	
	function accAdd(arg1, arg2) {
	    var r1, r2, m;
	    try { r1 = arg1.toString().split(".")[1].length } catch (e) { r1 = 0 }
	    try { r2 = arg2.toString().split(".")[1].length } catch (e) { r2 = 0 }
	    m = Math.pow(10, Math.max(r1, r2))
	    return (arg1 * m + arg2 * m) / m
	} 
	
	function accDiv(num1, num2) {
		var baseNum1 = 0, baseNum2 = 0;
		var baseNum3, baseNum4;
		try {
			baseNum1 = num1.toString().split(".")[1].length;
		} catch (e) {
			baseNum1 = 0;
		}
		try {
			baseNum2 = num2.toString().split(".")[1].length;
		} catch (e) {
			baseNum2 = 0;
		}
		with (Math) {
			baseNum3 = Number(num1.toString().replace(".", ""));
			baseNum4 = Number(num2.toString().replace(".", ""));
			return (baseNum3 / baseNum4) * pow(10, baseNum2 - baseNum1);
		}
	};
	
	function accMul(num1,num2)     
	{     
		var baseNum = 0;
		try {
			baseNum += num1.toString().split(".")[1].length;
		} catch (e) {
		}
		try {
			baseNum += num2.toString().split(".")[1].length;
		} catch (e) {
		}
		return Number(num1.toString().replace(".", "")) * Number(num2.toString().replace(".", "")) / Math.pow(10, baseNum);    
	}     
		//给Number类型增加一个mul方法，调用起来更加方便。     
	
	Number.prototype.mul = function (arg){     
		return accMul(arg, this);     
	}

	function accMul6(arg1,arg2){
		var mul = accMul(arg1,arg2).toFixed(6);
		return parseFloat(mul);
	}
	
	function accMul8(arg1,arg2){
		var mul = accMul(arg1,arg2).toFixed(8);
		return parseFloat(mul);
	}
	
	function accMul2(arg1,arg2){
		var mul = accMul(arg1,arg2).toFixed(2);
		return mul;
	}
	
	
	function dateObjectShortFormatter(value,row,index){
		if(value!=null && value!=''){
			var dt = new Date(parseInt(value));
			return dt.Format("yyyy-MM-dd");
		}
		else{
			return '';
		}
	}
	
	function weekEndFormatter1(value,row,index){
		return weekEndFormatter(row.promotionDays,1);
	}
	function weekEndFormatter2(value,row,index){
		return weekEndFormatter(row.promotionDays,2);
	}
	function weekEndFormatter3(value,row,index){
		return weekEndFormatter(row.promotionDays,3);
	}
	function weekEndFormatter4(value,row,index){
		return weekEndFormatter(row.promotionDays,4);
	}
	function weekEndFormatter5(value,row,index){
		return weekEndFormatter(row.promotionDays,5);
	}
	function weekEndFormatter6(value,row,index){
		return weekEndFormatter(row.promotionDays,6);
	}
	function weekEndFormatter7(value,row,index){
		return weekEndFormatter(row.promotionDays,7);
	}
	
	function weekEndFormatter(value,day){
		if(value!='' && value.indexOf(day)>=0){
			return "<input type='checkbox' checked disabled/>";
		}
		else{
			return "<input type='checkbox' disabled/>";
		}
	}
	
	function  roleTypeFormatter(value,row,index){
		//0-正常,1-试销,2-冻结订货,3-冻结付款,4-冻结订货/付款,5-退场
		if(value==0){
			return '后台管理角色';
		}
		else if(value==1){
			return '卖家角色';
		}
		else if(value==2){
			return '买家角色';
		}
		else
			return '其他';
	}
	
	
	function yesOrNoFormatter(value,row,index){
		if(value=='0'){
			return '否';
		}
		else{
			return '是';
		}
	}

	function statusFormatter(value,row,index){
		if(value=='0'){
			return '正常';
		}
		else{
			return '<font color=red>已停用</font>';
		}
	}
	
	function goodsBusinessModeFormatter(value,row,index){
		if(value=='0'){
			return '经销';
		}
		else if(value=='1'){
			return '联营';
		}
	}
	
	
	function goodsTaxFormatter(value,row,index){
		if(value==1){
			return '免税';
		}
		else if(value==2){
			return '10%';
		}
		else if(value==3){
			return '16%';
		}
		else {
			return '其他';
		}
	}
	
	function getTaxString(value){
		if(value==1){
			return '免税';
		}
		else if(value==2){
			return '10%';
		}
		else if(value==3){
			return '16%';
		}
		else {
			return '其他';
		}
	}
	
	function allowOrForbidFormatter(value,row,index){
		if(value==0){
			return '禁止';
		}
		else if(value==1){
			return '允许';
		}
		else{
			return '';
		}
	}
	
	
	function totalFormatter(value,row,index){
		if(value != null && value!=undefined)
			return Number(value).toFixed(2);
		else
			return '';
	}
	
	function billStatusFormatter(value,row,index){
		if(value == 0){
			return '待审核';
		}
		else if(value==1){
			return '已审核';
		}
		else if(value==-1){
			return '已作废';
		}
		else
			return '';
	}
	
	function adjustBillStatusFormatter(value,row,index){
		if(value == 0){
			return '未审核';
		}
		else if(value==1){
			return '已审核未执行';
		}
		else if(value==2){
			return '已执行';
		}
		else if(value==-1){
			return '已作废';
		}
		else
			return '';
	}
	
	function orderBillStatusFormatter(value,row,index){
		if(value == 0){
			return '未审核';
		}
		else if(value==1){
			return '已审核';
		}
		else if(value==2){
			return '已入库';
		}
		else if(value==-1){
			return '已作废';
		}
		else
			return '';
	}
	
	
	
	message = function(msg,callback){
		if(callback==null || callback==undefined)
			layer.alert(msg,{icon: 1});
		else{
			layer.alert(msg,{icon: 1},function(index){
				layer.close(index);
				callback();
			});
		}
	}
	
	msg = function(msg){
		layer.msg(msg);
	}
	
	error = function(msg,callback){
		if(callback==null || callback==undefined)
			layer.alert(msg,{title:'提示',icon: 5});
		else{
			layer.alert(msg,{title:'提示',icon: 5},function(index){
				layer.close(index);
				callback();
			});
		}
	}
	
	affirm = function(msg,callback){
		layer.confirm(msg, {
			  icon: 3,
			  btn: ['确定','取消'] //按钮
			}, function(){
				layer.closeAll('dialog');
				callback();
			}, function(){
			  ;
			});
	}
	
	post = function(url,jsonData,callback){
		$.ajax({
            url:baseUrl+url,
            type:"post",
            dataType:"json",
            data:jsonData,
            timeout:10000,
            beforeSend: function(){  //开始loading
            	layer.load();                
            },
            success:function(data){
            	if(data.code=='402'){
            		error(data.msg,function(){location.href='/login.html'});
            	}
            	else{
            		layer.closeAll('loading');
                    callback(data);
            	}            	
            },
            error:function(res){
            	layer.closeAll('loading'); 
            	setTimeout(function () { 
            		if(res.status == 404){ 
            			error('请求失败，请求未找到!'); 
            			}
            		else if(res.status == 503){ 
            			error('请求失败，服务器内部错误'); 
            			}
            		else { 
            			error('请求失败,网络连接超时'); 
            			} 
            		ajaxStatus = true; 
            		},500);
            }
        });
    };
    
    postWithToken = function(url,jsonData,token,callback){
		$.ajax({
            url:baseUrl+url,
            type:"post",
            dataType:"json",
            data:jsonData,
            headers: {
                'Authorization':token
  		  	},
            timeout:10000,
            beforeSend: function(){  //开始loading
            	layer.load();                
            },
            success:function(data){
            	if(data.code=='402'){
            		error(data.msg,function(){location.href='/login.html'});
            	}
            	else{
            		layer.closeAll('loading');
            		callback(data);
            	}
            },
            error:function(res){
            	layer.closeAll('loading'); 
            	setTimeout(function () { 
            		if(res.status == 404){ 
            			error('请求失败，请求未找到!'); 
            			}
            		else if(res.status == 503){ 
            			error('请求失败，服务器内部错误'); 
            			}
            		else { 
            			error('请求失败,网络连接超时'); 
            			} 
            		ajaxStatus = true; 
            		},500);
            }
        });
    };
    
   YJPost = function(url,jsonData,callback){
	   $.ajax({
            url:baseUrl+url,
            type:"post",
            dataType:"json",
            data:jsonData,
            headers: {
                'Authorization':getTokenData()
  		  	},
            timeout:10000,
            beforeSend: function(){  //开始loading
            	layer.load();                
            },
            success:function(data){
            	if(data.code=='402'){
            		error(data.msg,function(){location.href='/login.html'});
            	}
            	else{
            		layer.closeAll('loading');
            		callback(data);
            	}
            },
            error:function(res){
            	layer.closeAll('loading'); 
            	setTimeout(function () { 
            		if(res.status == 404){ 
            			error('请求失败，请求未找到!'); 
            			}
            		else if(res.status == 503){ 
            			error('请求失败，服务器内部错误'); 
            			}
            		else { 
            			error('请求失败,网络连接超时'); 
            			} 
            		ajaxStatus = true; 
            		},500);
            }
        });
    };
    
    YJPostStr = function(url,jsonData,callback){
 	   $.ajax({
             url:baseUrl+url,
             type:"post",
             dataType:"json",
             contentType:"application/json",
             data : JSON.stringify(jsonData),
             headers: {
                 'Authorization':getTokenData()
   		  	},
             timeout:10000,
             beforeSend: function(){  //开始loading
             	layer.load();                
             },
             success:function(data){
            	if(data.code=='402'){
             		error(data.msg,function(){location.href='/login.html'});
             	}
            	else{
            		layer.closeAll('loading');
            		callback(data);
            	}             	
             },
             error:function(res){
             	layer.closeAll('loading'); 
             	setTimeout(function () { 
             		if(res.status == 404){ 
             			error('请求失败，请求未找到!'); 
             			}
             		else if(res.status == 503){ 
             			error('请求失败，服务器内部错误'); 
             			}
             		else { 
             			error('请求失败,网络连接超时'); 
             			} 
             		ajaxStatus = true; 
             		},500);
             }
         });
     };   
    
    YJGet = function(url,jsonData,callback){
        $.ajax({
            url:baseUrl+url,
            type:"get",
            contentType:"application/json",
            dataType:"json",
            data:jsonData,
            headers: {
                'Authorization':getTokenData()
  		  	},
            timeout:60000,
            success:function(data){
            	if(data.code=='402'){
            		error(data.msg,function(){location.href='/login.html'});
            	}
            	else
            		callback(data);
            },
             error:function(res){
             	setTimeout(function () { 
             		if(res.status==200){ //成功
             			callback(res.responseText);
             		}
             		else if(res.status == 404){ 
             			error('请求失败，请求未找到!'); 
             		}
             		else if(res.status == 503){ 
             			error('请求失败，服务器内部错误'); 
             		}
             		else { 
             			error('请求失败,网络连接超时'); 
             		} 
             		ajaxStatus = true; 
             		},500);
             }
        });
    };
    
    getAsynJSON = function(url,jsonData,callback){
        $.ajax({
            url:baseUrl+url,
            type:"get",
            contentType:"application/json",
            dataType:"json",
            data:jsonData,
            async:false,
            headers: {
                'Authorization':getTokenData()
  		  	},
            timeout:60000,
            success:function(data){
            	if(data.code=='402'){
            		error(data.msg,function(){location.href='/login.html'});
            	}
            	else
            		callback(data);
            },
             error:function(res){
             	layer.closeAll('loading'); 
             	setTimeout(function () { 
             		if(res.status == 404){ 
             			error('请求失败，请求未找到!'); 
             			}
             		else if(res.status == 503){ 
             			error('请求失败，服务器内部错误'); 
             			}
             		else { 
             			error('请求失败,网络连接超时'); 
             			} 
             		ajaxStatus = true; 
             		},500);
             }
        });
    };
    
    function getDialogHeight(){
    	var win_height = document.body.clientHeight-50;
    	if(win_height>700){
    		win_height = 700;
    	}
    	return win_height;
    }
    
    function getDialogWidth(){
    	var win_width = document.body.clientWidth-100;
    	win_width = win_width*3/4;
//    	if(win_width>1000){
//    		win_width = 1000;
//    	}
    	return win_width;
    }
    
    function autoCreatePy(obj,vid){
    	var py = makePy(obj.value);
    	if(py.length>0){
    		py = py[0].substring(0,15);
    	}
    	$('#'+vid).textbox("setValue", py); 
    }
  
    
    
    $.extend($.fn.validatebox.defaults.rules, {
        minLength: {
            validator: function(value, param) {
                return value.length >= param[0]
            },
            message: '请输入至少{0}个字符.'
        },
        maxLength: {
            validator: function(value, param) {
                return param[0] >= value.length
            },
            message: '请输入最多{0}位汉字.'
        },
        maxNumerLength: {
            validator: function(value, param) {
                return param[0] >= value.length
            },
            message: '请输入最多{0}位数字.'
        },
        checkIp:{
        	validator:function(value,param){
                var reg = /^((1?\d?\d|(2([0-4]\d|5[0-5])))\.){3}(1?\d?\d|(2([0-4]\d|5[0-5])))$/ ;  
                return reg.test(value);  
        	},
        	message:'IP地址输入非法'
        },
        dateValid:{
        	validator:function(value,param){
        		return checkDateIsValid(value);
        	},
        	message:'日期输入格式错误'
        },
        dayIsValid:{
        	validator:function(value,param){
        		return checkMonthDayIsValid(value);
        	},
        	message:'日期输入格式错误'
        },
        mobile: {
            validator: function(value) {
                var reg = /^1[3|4|5|6|7|8|9]\d{9}$/;
                return reg.test(value)
            },
            message: '输入手机号码格式不准确.'
        },
        passwordCheck: {
            validator: function(value) {
                if (/^\d+$/.test(value)) {
                    return false
                } else if (/^[A-Za-z]+$/.test(value)) {
                    return false
                } else {
                    return true
                }
            },
            message: '密码必须同时包含字母和数字！'
        },
        englishCheckSub: {
            validator: function(value) {
                return /^[a-zA-Z0-9]+$/.test(value)
            },
            message: "只能包括英文字母、数字"
        },
        numberCheck: {
            validator: function(value) {
                return /^[0-9]+$/.test(value)
            },
            message: "只能包括数字"
        },
        idcardCheck:{
        	validator: function(value) {
                return /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(value)
            },
            message: "身份证号输入非法"
        }
    });
    
    function getParamValueFromUrl(name){
    	var str=window.location.search;
    	if (str.indexOf(name)!=-1){
    		var pos_start=str.indexOf(name)+name.length+1;
    		var pos_end=str.indexOf("&",pos_start);
    		if (pos_end==-1){
    			return str.substring(pos_start);
    		}
    		else{
    			return str.substring(pos_start,pos_end);
    		}
    	}
    	else{
    		return "";
    	}
    }
    
    function checkDateIsValid(date){
    	if(date == null || date==''){
    		return true;
    	}
    	var format_date = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;
		return format_date.test(date);
    }
    
    function checkMonthDayIsValid(date){
    	if(date == null || date==''){
    		return true;
    	}
    	var format_date = /^(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;
		return format_date.test(date);
    }
    
    function checkInputFileIsValid(fileName,allowFileExt){
    	var file_typename = fileName.substring(fileName.lastIndexOf('.'), fileName.length);
    	if(file_typename==''){
    		error("对不起，文件格式错误，文件应该是"+allowFileExt+"后缀，请重新选择文件！");
    		return false;
    	}

    	file_typename = file_typename.toUpperCase();
    	var obj2 = allowFileExt.split(",");
    	for(var i=0;i<obj2.length;++i){
    		if(obj2[i] == file_typename){
    			return true;
    		}
    	}
    	error("对不起，文件格式错误，文件应该是"+allowFileExt+"后缀，请重新选择文件！");
    	return false;
    }
  
    
    /**
     * 查看商品信息
     * @param id
     */
    function viewGoodsInfo(id,name){
    	$('#MyPopWindow').dialog({
			title:'查看商品详情【'+name+'】',
			closed:false,
			cache:false,
			iconCls: 'icon-edit',
			href:'/page/info/basic/goods/view.html',
			width:getDialogWidth(),
			resizable:true,
			height:getDialogHeight(),
			modal:true,
			buttons:[
			         {	text:'关闭',
			        	iconCls:'icon-cancel',
			        	handler:function(){
			        		$('#MyPopWindow').dialog('close');
			        	}}],
			onLoad: function(){
				loadGoodsDetailInfo(id);
			}
		  }); 
    	$('#MyPopWindow').window('center');
    }
    
    function formartNullString(str){
    	if(str == null){
    		return '';
    	}
    	else 
    		return str;
    }
    
    function exportExcel(grid,title){
    	var obj = getDataGridFieldAndColumns(grid);
		var params = grid.datagrid('options').queryParams;
		params.page = 0;
		params.rows = 0;
		params.xls_title = title;
		params.xls_fields = obj.fields.join(',');
		params.xls_columns = obj.columns.join(',');	
		params.xls_mapping = obj.mapping.join(',');
		params.token   = getTokenData();
		
		var exportUrl = grid.datagrid('options').url;
		if(exportUrl == null || exportUrl == ''){
			error('没有数据可导出！');
		}
		else{
			var inputs = '';
			jQuery.each(params, function(i, val) { 
				inputs += '<input type="hidden" name="' + i + '" value="' + val + '" />';  
			});
			//console.log(inputs);
			jQuery('<form action="' + exportUrl + '" method="post" target="_blank">' + inputs + '</form>')  
	           .appendTo('body').submit().remove(); 
		}
    }
    
    function exportExcelWithUrl(exportUrl,grid,title){
    	var obj = getDataGridFieldAndColumns(grid);
		var params = grid.datagrid('options').queryParams;
		params.page = 0;
		params.rows = 0;
		params.xls_title = title;
		params.xls_fields = obj.fields.join(',');
		params.xls_columns = obj.columns.join(',');	
		params.xls_mapping = obj.mapping.join(',');
		params.token   = getTokenData();
		
		var inputs = '';
		jQuery.each(params, function(i, val) { 
			inputs += '<input type="hidden" name="' + i + '" value="' + val + '" />';  
		});
		//console.log(inputs);
		jQuery('<form action="' + exportUrl + '" method="post" target="_blank">' + inputs + '</form>')  
           .appendTo('body').submit().remove(); 
    }
    
    
    /**
     * 获取列信息和字段名
     */
    function getDataGridFieldAndColumns(target){
    	var obj = new Object();
    	var allfields = target.datagrid("getColumnFields");
    	var columns = [];
    	var mapping = [];
    	var fields  = [];
    	
    	$.each(allfields,function(index,field){
    		if(field!='fk' && field.hidden!='true'){
    			fields.push(field);
    			var col = target.datagrid('getColumnOption', field);
        		columns.push(col.title);
        		if(col.mapping!=''){
        			mapping.push(col.mapping);
        		}
        		else
        			mapping.push('');
    		}    		
    	});
    	mapping.push('a');
    	
    	obj.fields  = fields;
    	obj.columns = columns;
    	obj.mapping = mapping;
    	return obj;
    }  
    
    /**
     * 日期增加运算,返回字符串
     * @param currentDate
     * @param day
     */
    function dateAdd(date,days){
    	var _date = new Date(date);
    	_date.setDate(_date.getDate() + Number(days));
    	return _date.Format('yyyy-MM-dd');
    }
    
    function contracttypeFormatter(value,row,index){
    	if(value==0){
    		return "经销";
    	}
    	else if(value==1){
    		return "联营";
    	}
    }
    
    function sexFormatter(value,row,index){
    	if(value==0){
    		return "男";
    	}
    	else if(value==1){
    		return "女";
    	}
    	else 
    		return '';
    }

    function settlementmethodFormatter(value,row,index){
    	switch (value) 
    	{ 
    	  case 0:return '货到付款'; 
    	  case 1:return '账期结算'; 
    	  case 2:return '预付货款'; 
    	  case 3:return '售后付款'; 
    	  case 4:return '月结'; 
    	  default:return '其他';
    	}
    }
    
    function clearNoNumForPrice(obj){
        obj.value = obj.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符  
        obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字而不是  
        obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
        obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");  
        obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d\d\d\d\d\d\d).*$/,'$1$2.$3'); //只能输入两个小数 
    }
    
    function clearNoNumForInt(obj){
    	var reg = new RegExp("[^0-9]","g"); 
    	obj.value = obj.value.replace(reg,""); 
    }
    
    function clearNoNumForQty(obj){
        obj.value = obj.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符  
        obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字而不是  
        obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
        obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");  
        obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数 
    }
    
    function digitFormatter(digit) {
        var ex = /^\d+$/;
        var ex1 = /^\d+(.[0]{1})$/;
        if (digit != '') {
            if (ex.test(digit) || ex1.test(digit)) {
                return parseInt(digit)
            } else {
                return digit;
            }
        } else {
            return digit;
        }
    }
    
    function clearNoNumForDiscount(obj){
        obj.value = obj.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符  
        obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字而不是  
        obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
        obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");  
        obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数 
        if(obj.value>=1){
        	msg('优惠率不能大于1');
        	obj.value=0;
        }
    }
    
    function calculateAllTotal(arr){
    	var total = 0 ;
    	for(var i=0;i<arr.length;i++){
    		var gobj = $('#'+arr[i]+'_gid');
    	    if(gobj!=null && gobj.val().length>0){
    	    	var _itemTotal = $('#'+arr[i]+'_total').val();
    	        if(_itemTotal!='')
    	        	total = accAdd(total,_itemTotal);
    	    }
    	}
    	
    	total = total.toFixed(2);
    	$('#billTotal').html(total);
    	return total;
    }
    
    /**
     * 增加使用
     * @param obj
     * @param event
     */
    function inputKeyupEvent(obj,event){
    	var e = event || Window.event;
    	var name = obj.name;
    	var id = obj.getAttribute("att");

    	if(e.keyCode == 13){
    		if(name=='goodsCode'){
    			doGoodsSearchByCode(id,obj.value,'',$('#frm #supplierid').val(),$('#frm #lockSupplier').prop('checked'));
    		}
    		else if(name=='goodsBarcode'){
    			doGoodsSearchByCode(id,'',obj.value,$('#frm #supplierid').val(),$('#frm #lockSupplier').prop('checked'));
    		}
    		else if(name=='qty'){  //数量敲入回车，计算合计金额
    			calculateTotal(id);
    		}
    		else if(name=='price'){
    			calculateTotal(id);
    		}
    	}
    	else{
    		if(name=='goodscode' || name=='goodsbarcode'){  //商品编号或条码信息
    			obj.value=obj.value.replace(/\D/g,'');			
    		}
    		else if(name=='qty'){ //4位小数
    			clearNoNumForQty(obj);
    		}
    		else if(name=='price'){ //格式化 4位小数
    			clearNoNumForPrice(obj);
    		}
    	}
    }
    
    /**
     * 修改使用
     * @param obj
     * @param event
     */
    function inputKeyupEventForEdit(obj,event){
    	var e = event || Window.event;
    	var name = obj.name;
    	var id = obj.getAttribute("att");

    	if(e.keyCode == 13){
    		if(name=='qty'){  //数量敲入回车，计算合计金额
    			var old = obj.getAttribute('previous');
    			if(Number(obj.value)>Number(old)){
    				msg('数量不能大于原数量！');
    				obj.value = old;
    				calculateTotal(id);
    				return;
    			}
    			else
    				calculateTotal(id);
    		}
    		else if(name=='price'){
    			calculateTotal(id);
    		}
    	}
    	else{
    		if(name=='qty'){ //4位小数
    			clearNoNumForQty(obj);
    		}
    		else if(name=='price'){ //格式化 4位小数
    			clearNoNumForPrice(obj);
    		}
    	}
    }
    
    function formatString(value){
    	if(value!=null && value!='' && value!='null'){
    		return value;
    	}
    	else{
    		return '';
    	}
    }
    
    /**
     * 检查数量并计算总额
     * @param obj
     */
    function checkQtyAndCalculateTotal(checked,obj){
    	var id = obj.getAttribute('att');
    	if(checked){
    		var old = obj.getAttribute('previous');    		
    		if(Number(obj.value)>Number(old)){
    			msg('数量不能大于原数量！');
    			obj.value = old;
    			calculateTotal(id);
    			return false;
    		}
    		else
    			calculateTotal(id);
    	}
    	else{
    		calculateTotal(id);
    	}
    }
    
    //取当前日期
    function getCurentDateStr()  
    {   
        var now = new Date();  
        return now.Format('yyyy-MM-dd');  
    }
    
    //取本月第一天
    function getCurrentMonthFirstDay(){
    	var now = new Date();
    	var day = now.Format('yyyy-MM');
    	return day+'-01';
    }
    

	function calculateSumAndShowGridFoot(tableObj){
		//判断是否有total属性和qty属性
		var fields = tableObj.datagrid('getColumnFields');
		var footerObj = {};
		for(var i = 0 ;i<fields.length;++i){
			var field = fields[i];
			var col = tableObj.datagrid('getColumnOption', field);
			if(col.cal!='false'){
				if(field.indexOf('qty')>=0 || field.indexOf('Qty')>=0 || field.indexOf("total")>=0 || field.indexOf("Total")>=0 || field=='profit'){
					footerObj[field] = getTableSum(tableObj,field);
				}
			}			
		}
		tableObj.datagrid('reloadFooter',[footerObj]); 
	}
	
	function getTableSum(tableObj,field){
		var sum = 0;
		var rows = tableObj.datagrid("getRows");
		for(var i=0;i<rows.length;i++){
			var obj = rows[i];
			if(obj[field] && obj[field]!='')
				sum += Number(obj[field]);
		}
		return sum.toFixed(2);
	}
	
	
	function checkGoodsIsExist(obj,arr){
		for(var i = 0 ;i<arr.length;++i){
			var goodsObj = $('#'+arr[i]+'_gid');
			if(goodsObj!=null && goodsObj.val().length>0){
				if(goodsObj.val()==obj.goodsid){
					msg('商品'+obj.name+'已添加，不允许重复！');
					return true;
				}
			}
		}
		return false;
	}
    
    function checkLodopIsInstall() {
    	try{
    	     var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
    		 if ((LODOP!=null)&&(typeof(LODOP.VERSION)!="undefined")) return true;
    	 }catch(err){
    		msg('请安装或升级Lodop插件!'+err.msg);
    		return false;
    	}
    };
    
    function printViewTable(w,h){
    	var mmtoPx= 3.3*10;
    	var mm = 3.8*10;

    	var header_height = 0;
    	var footer_height = 0;
    	var table_height = 300;
    	
    	var pageHeight = Number(h)*mmtoPx;
    	var pageWidth = Number(w)*mm;		
    	
    	var header = $("#headerInfo");
    	var footer = $("#footerInfo");
    	
    	$("#headerInfo table").css("width","100%");
    	$("#contentInfo table").css("width","100%");
    	$("#footerInfo table").css("width","100%");
    	
    	$("#headerInfo table").css("border-collapse","separate");
    	$("#headerInfo table").css("border-spacing","2px");
    	
    			
    	$("#footerInfo table").css("border-collapse","separate");
    	$("#footerInfo table").css("border-spacing","2px");
    	
    	$("#headerInfo").css("height","");
    	$("#footerInfo").css("height","");	
    	$("#headerInfo table").css("height","");
    	$("#footerInfo table").css("height","");
    	$("#headerInfo").css("width",pageWidth+"px");
    	$("#contentInfo").css("width",pageWidth+"px");
    	$("#footerInfo").css("width",pageWidth+"px");
    	
    	$('#printDiv').show();
    	header_height = header.height();
    	if(footer!=null){
    		footer_height = footer.height();
    	}
    	$('#printDiv').hide();
    	
    	var LODOP  = getLodop();
    	LODOP.PRINT_INIT("Print");
    	var width = parseInt(w*100);
    	var height = parseInt(h*100);
    	LODOP.SET_PRINT_PAGESIZE(1,width,height,'');

    	
    	//高度与像素的关系  1厘米＝28像素
    	table_height = pageHeight-header_height-footer_height-40;
    	var printWidth = parseInt(w*10)-13;

    	var strStyle="<style>table { border-collapse: collapse; border: none;} td { border: solid #000 1px;padding:3px 1px 3px 1px} </style>";
    	LODOP.ADD_PRINT_TABLE(header_height+6,0,printWidth+"mm",table_height,strStyle+document.getElementById("contentInfo").innerHTML);		
    	
    	LODOP.ADD_PRINT_HTM(0,0,printWidth+"mm",header_height,header.html());
    	//设置打印项风格A,只对单个打印项有效.序号为0，代表当前（最后加入的那个）数据项
    	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);//1--页眉页脚
    	LODOP.SET_PRINT_STYLEA(0,"LinkedItem",1);//设置关联内容项的项目编号,1代表当前对象

    	if(footer!=null){
    		LODOP.ADD_PRINT_HTM(header_height+table_height+9,0,printWidth+"mm",footer_height,footer.html());
    		LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
    		LODOP.SET_PRINT_STYLEA(0,"LinkedItem",1);
    	}
    	LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",1);//横向打印的预览默认旋转90度（正向显示）
    	LODOP.SET_SHOW_MODE("NP_NO_RESULT" ,true);//设置NP插件无返回，这可避免chrome（谷歌）等np插件浏览器对弹窗超时误报崩溃
    	LODOP.SET_SHOW_MODE("BKIMG_IN_PREVIEW",0);
    	LODOP.SET_PRINT_MODE ("AUTO_CLOSE_PREWINDOW",1);
    	
    	LODOP.PREVIEW();
    }
    
    function printDataTableList(){
    	var LODOP  = getLodop();
    	LODOP.PRINT_INIT("打印列表页-零售系统");
    	LODOP.SET_PRINT_PAGESIZE(0,0,0,"");	
    	$("#contentInfo table").css("line-height","22px");
    	var strStyle="<style>table { border-collapse: collapse; border: none; } td { border: solid #000 1px; } th { border: solid #000 1px}</style>";
    	LODOP.ADD_PRINT_TABLE("1%","1%","96%","98%",strStyle+document.getElementById("contentInfo").innerHTML.replaceAll("xx","#"));
    	LODOP.PREVIEW();
    }    
    
    function closeWin(){
    	$('#MyPopWindow').dialog('close');
    }
    
    /**
     *  打印预览价签
     * @param html
     * @param width
     */
    function previewPriceTag(html,width){
    	if(html == '' || width=='' || Number(width)<=0){
    		msg('没有模板，无法预览！');
    		return;
    	}
    	LODOP = getLodop();  
    	LODOP.PRINT_INIT("priview");    		
    		
    	var printWith = Number(width)*38;
    	var strStyle="<style>.printPriceTagDiv{ border: 1px solid #151515}</style>";
    		
    	html = html.replace('name="printPriceTagDiv"','name="printPriceTagDiv" style="width:'+printWith+'px;margin-left:10px;margin-right:10px;margin-bottom:10px;float:left"');
    	html = html.replace('name="printPriceTagTable"','name="printPriceTagTable" style="width:100%"');
    		
    	html = html.replace('[商品编码]','00000002');
    	html = html.replace('[品牌]','康师傅');
    	html = html.replace('[品名]','统一方便面');
    	html = html.replace('[规格]','120g');
    	html = html.replace('[单位]','包');
    	html = html.replace('[零售价]','4.50');
    	html = html.replace('[会员价]','4.20');
    	html = html.replace('[原售价]','4.50');
    	html = html.replace('[促销价]','4.00');
    	html = html.replace('[条码]','123456789');
    	html = html.replace('[厂编]','000002');

    	LODOP.ADD_PRINT_HTM(0,0,'100%','100%',strStyle+html);
    		
    	LODOP.PREVIEW();
    }
    
    /**
     * 打印价签
     * @param htmlArray A数组 
     * @param pageCount 每页显示条数
     */
    function printPriceTag(htmlArray){
    	LODOP = getLodop();  
    	LODOP.PRINT_INIT("printPriceTag");
    	
    	for(var i = 0 ;i<htmlArray.length;++i){
    		LODOP.ADD_PRINT_HTM(0,0,'100%','100%',htmlArray[i]);
    		if(i!=htmlArray.length-1)
    			LODOP.NewPage();
    	}
    	LODOP.PREVIEW();
    }
    
    function showBillStatus(status){
    	var image = '';
    	if(status == 0){
    		image = 'noCheck.png';
    	}
    	else if(status == -1){
    		image = 'discard.png';
    	}
    	else if(status == 1){
    		image = 'audit.png';
    	}
    	else if(status==2){
    		image = 'execute.png';
    	}
    	if(image!='')
    		$('#statusDiv').html("<img src='/css/images/"+image+"'>");
    }
