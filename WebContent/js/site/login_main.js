require.config({
	paths:{
		"jquery":"/mall/js/jquery",
	},
});

require(['jquery','common','login'],function(jquery,common,login){
	$(function(){
		//失去光标验证用户名
		login.accountCheck();
		//失去光标验证密码
		login.passwordCheck();
		//登录
		login.loginBtn();
	});
});
