define(['common'],function(common){
	var isAccountValidate=false;
	var isPasswordValidate=false;
	//失去光标验证用户名
	function accountCheck(){
		$("#username").blur(function(){
			isAccountValidate=checkAccount();
		});
	}
	//失去光标验证密码
	function passwordCheck(){
		$("#password").blur(function(){
			isPasswordValidate=checkPassword();
		});
	}
	
	//登录
	function loginBtn(){
		//创建单击事件
		$("#login_btn").click(function(){
			//截断
			//失败
			if(!isAccountValidate){
				return checkAccount();
			}
			if(!isPasswordValidate){
				return checkPassword();
			}
			//成功
			$.ajax({
				url:baseUrl+"user/do_login.do",
				type:"post",
				data:{account:$("#username").val(),password:$("#password").val()},
				xhrFields:{withCredentials:true},
				crossDomain:true,
				success:function(data){
					//判断
					if(data.status==0){
						
						//管理员
						if(data.data.role==2){
//							$(window).attr("location","index_forward.html")
							$(window).attr("location","./back_web/index.html")
						}else if(data.data.role==1){
//							$(window).attr("location","./back_web/index.html")
							$(window).attr("location","index_forward.html")
						}
					}else{
						$("#passwordError").css({display:"block"});
//						$("#passwordError").html(data.msg);
						alert(data.msg);
					}
				}
			});
		});
	}
	
	
	
	
	return{
		accountCheck:accountCheck,
		passwordCheck:passwordCheck,
		loginBtn:loginBtn
	};
	
	//校验用户名
	function checkAccount(){
		var account = $("#username").val();
		$("#usernameError").css({display:"none"});
		if(account == ""){
			$("#usernameError").css({display:"block"});
			$("#usernameError").html("用户名不能为空!");
			return false;
		}
		return true;
		
	}
	
	function checkPassword(){
		var pwd = $("#password").val();
		$("#passwordError").css({display:"none"});
		if(pwd == ""){
			$("#passwordError").css({display:"block"});
			$("#passwordError").html("密码不能为空!");
			return false;
		}
		return true;
		
	}
});