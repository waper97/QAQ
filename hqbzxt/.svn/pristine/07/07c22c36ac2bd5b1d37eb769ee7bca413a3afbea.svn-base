var layer;
var sysVariable = {};
var $,tab,dataStr,layer;
layui.config({
	base : "js/"
}).extend({
	"bodyTab" : "bodyTab"
})
layui.use(['bodyTab','form','element','layer'],function(){
	var form = layui.form,
		element = layui.element;
    	layer = parent.layer === undefined ? layui.layer : top.layer;
		tab = layui.bodyTab({
			openTabNum : "10"
		});
	var mainNav = [];
	
	
	
//getUserNavigations();
	
	
//	function getUserNavigations(){
//		var nav = [];
//		var obj1 = new Object();
//		obj1.id=1;
//		obj1.name = '控制中心';
//		
//		var child1 = [];
//		var cobj1 = new Object();
//		cobj1.id='11';
//		cobj1.name='系统参数';
//		cobj1.htmlUrl = 'pages/system/param/index.html';
//		child1.push(cobj1);
//		obj1.children = child1;
//		
//		nav.push(obj1);
//		
//		var cobj2 = new Object();
//		cobj2.id='12';
//		cobj2.name='角色管理';
//		cobj2.htmlUrl = 'pages/system/role/index.html';
//		child1.push(cobj2);
//		
//		var cobj3 = new Object();
//		cobj3.id='13';
//		cobj3.name='资源管理';
//		cobj3.htmlUrl = 'pages/system/res/index.html';
//		child1.push(cobj3);
//		
//		obj1.children = child1;
//		
//		var obj2 = new Object();
//		obj2.id=2;
//		obj2.name = '信息中心';
//		
//		var child2 = [];
//		var cobj2 = new Object();
//		cobj2.id='21';
//		cobj2.name='计量单位';
//		cobj2.htmlUrl = 'page/system/sysparams.html';
//		child2.push(cobj2);
//		obj2.children = child2;
//		
//		nav.push(obj2);	
		
//		return nav;
//	}
	
	mainNav = getUserNavigations();
//	initMenus();
	
	function getUserNavigations(){
		YJGet('/user/getInfo',null,function(data){
			if(data.success){
				$('.adminName').html(data.data.name);				
				mainNav = data.data.menu; 
				initMenus();	
			}
			else{
				if(data.code=='401'){
					error(data.message,function(){
						location.href='login.html';
					})
				}
				else
					msg(data.message);
			}
					
	    });
	}
	
	$('#main_frm').attr('src','pages/main.html');//?Authorization='+getTokenData());  

	function initMenus(){
		var html = '';
		var first = 0;
		mainNav.forEach(function(v) { 
			if(first == 0)
				first = v.id;
		    html += '<li class="layui-nav-item" data-menu="'+v.id+'"><a href="javascript:;" style="padding:0 10px"><cite>'+v.name+'</cite></a></li>';
		});
		$('.topLevelMenus').html(html);
		getData(first);
	}

	//通过顶部菜单获取左侧二三级菜单   注：此处只做演示之用，实际开发中通过接口传参的方式获取导航数据
	function getData(id){
		mainNav.forEach(function(v) {  
			if(v.id==id){
				dataStr = v.children;
				tab.render();
			}
		})
	}
	//页面加载时判断左侧菜单是否显示
	//通过顶部菜单获取左侧菜单
	$(".topLevelMenus li").click(function(){
		$(".topLevelMenus li").eq($(this).index()).addClass("layui-this").siblings().removeClass("layui-this");
		
		$(".layui-layout-admin").removeClass("showMenu");
		$("body").addClass("site-mobile");
		getData($(this).data("menu"));
		//渲染顶部窗口
		tab.tabMove();
	})

	//隐藏左侧导航
	$(".hideMenu").click(function(){
		if($(".topLevelMenus li.layui-this a").data("url")){
			layer.msg("此栏目状态下左侧菜单不可展开");  //主要为了避免左侧显示的内容与顶部菜单不匹配
			return false;
		}
		$(".layui-layout-admin").toggleClass("showMenu");
		//渲染顶部窗口
		tab.tabMove();
	})

	//手机设备的简单适配
    $('.site-tree-mobile').on('click', function(){
		$('body').addClass('site-mobile');
	});
    $('.site-mobile-shade').on('click', function(){
		$('body').removeClass('site-mobile');
	});

	// 添加新窗口
	$("body").on("click",".layui-nav .layui-nav-item a:not('.mobileTopLevelMenus .layui-nav-item a')",function(){
		//如果不存在子级
		if($(this).siblings().length == 0){
			addTab($(this));
			$('body').removeClass('site-mobile');  //移动端点击菜单关闭菜单层
		}
		$(this).parent("li").siblings().removeClass("layui-nav-itemed");
	})
	//登出
	$(".signOut").on("click",function(){
		debugger;
		YJGet('/manager/system/user/loginOut',null,function(data){
			if(data.success){
				location.href='login.html';
			}			
	    });
	})
})

//打开新窗口
function addTab(_this){
	tab.tabAdd(_this);
}

function openTab(title,url){
	tab.openOrAddTab(title,url);
}
