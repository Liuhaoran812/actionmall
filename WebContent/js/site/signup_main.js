require.config({
	paths:{
		"jquery":"/mall/js/jquery",
	},
});

require(['jquery','signup'],function(jquery,signup){
	$(function(){
		signup.registBtn();
	});
});
