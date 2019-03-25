//获取系统时间
var newDate = '';
getLangDate();
//值小于10时，在前面补0
function dateFilter(date){
    if(date < 10){return "0"+date;}
    return date;
}
function getLangDate(){
    var dateObj = new Date(); //表示当前系统时间的Date对象
    var year = dateObj.getFullYear(); //当前系统时间的完整年份值
    var month = dateObj.getMonth()+1; //当前系统时间的月份值
    var date = dateObj.getDate(); //当前系统时间的月份中的日
    var day = dateObj.getDay(); //当前系统时间中的星期值
    var weeks = ["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
    var week = weeks[day]; //根据星期值，从数组中获取对应的星期字符串
    var hour = dateObj.getHours(); //当前系统时间的小时值
    var minute = dateObj.getMinutes(); //当前系统时间的分钟值
    var second = dateObj.getSeconds(); //当前系统时间的秒钟值
    var timeValue = "" +((hour >= 12) ? (hour >= 18) ? "晚上" : "下午" : "上午" ); //当前时间属于上午、晚上还是下午
    newDate = dateFilter(year)+"年"+dateFilter(month)+"月"+dateFilter(date)+"日 "+" "+dateFilter(hour)+":"+dateFilter(minute)+":"+dateFilter(second);
    document.getElementById("nowTime").innerHTML = "当前时间为： "+newDate+"　"+week;
    setTimeout("getLangDate()",1000);
}

layui.use(['form','element','layer'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        element = layui.element;
    //上次登录时间【此处应该从接口获取，实际使用中请自行更换】
   // $(".loginTime").html(newDate.split("日")[0]+"日</br>"+newDate.split("日")[1]);
    //icon动画
    $(".panel a").hover(function(){
        $(this).find(".layui-anim").addClass("layui-anim-scaleSpring");
    },function(){
        $(this).find(".layui-anim").removeClass("layui-anim-scaleSpring");
    })
    $(".panel a").click(function(){
        parent.addTab($(this));
    })
    
    $('#orgInfo').html(getStorageKey('org') );
    

    //最新文章列表trial_market_msg_div
//    getSupplierTrialOverMsg();
//    
//    //合同到期消息
//    getExpiredContractMsg();
//    //配送确认收货消息
//    getChainConfirmMsg();
    
})

function getSupplierTrialOverMsg(){
	YJGet('/api/basic/supplier/getTrialOver',null,function(data){
    	if(data.success){
    		var rec = data.data;
    		if(rec!=null && rec.length>0){
    			$('#trial_market_msg_div').show();
    			var html = '';
    			rec.forEach(function(v) { 
    				html += '<tr><td>供货商【'+v.name+'('+v.code+')'+'】试销已结束！</td></tr>';
    			});
    			$("#trial_market_msg_div .hot_news").html(html);
    		}    		
    	}
    })
}

function getExpiredContractMsg(){
	YJGet('/api/basic/supplier/contract/getExpireContracts',null,function(data){
    	if(data.success){
    		var rec = data.data;
    		if(rec!=null && rec.length>0){
    			$('#contract_expire_msg_div').show();
    			var html = '';
    			rec.forEach(function(v) { 
    				html += '<tr><td>供货商【'+v.supplierName+'】,编号为 '+v.code+' 的合同已经到期，请及时延期！</td></tr>';
    			});
    			$("#contract_expire_msg_div .hot_news").html(html);
    		}    		
    	}
    })
}

function getChainConfirmMsg(){
	YJGet('/api/business/distribution/getConfirmBill',{'confirmStatus':0,'page':0,'rows':0},function(data){
    	if(data.success){
    		var rec = data.data;
    		if(rec!=null && rec.length>0){
    			$('#chain_confirmReceive_msg_div').show();
    			var html = '';
    			rec.forEach(function(v) { 
    				html += '<tr><td>'+v.vchTypeName+'【'+v.code+'】已发货，请及时收货确认！</td></tr>';
    			});
    			$("#chain_confirmReceive_msg_div .hot_news").html(html);
    		}    		
    	}
    })
}
