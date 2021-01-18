define(['jquery'],function(jquery){
	//name是否有效
	var isUserNameValidate = false;
	var isUserPwdValidate = false;
	var isUserRePwdValidate = false;
	var isPwdValidate = false;
	var isEmailValidate = false;
	var isPhoneValidate = false;
	var isQuestionValidate = false;
	var isAnswerValidate = false;
	
	$("#username").blur(function(){
		isUserNameValidate=checkUserName("username");
	});
	
	function checkUserName(objId){
		//获取
		var userName = $("#"+objId).val();
		$("#usernameError").css({display:"none"});
		//empty?
		if(userName == ""){
			$("#usernameError").css({display:"block"});
			$("#usernameError").html("请输入用户名");
			return false;
		}
		//len?
		if(userName.length<3 || userName.length>16){
			$("#usernameError").css({display:"block"});
			$("#usernameError").html("用户名长度错误");
			return false;
		}
		//规范
		var reg =/^[0-9a-zA-Z]+$/;
		var str =document.getElementById("username").value;
		if(!reg.test(str)){
			$("#usernameError").css({display:"block"});
			$("#usernameError").html("用户名只能为数字和英文");
			return false;
		}
		//存在？
		var flag =false;
		$.ajax({
			url:baseUrl+"user/do_check_info.do",
			type:"post",
			data:{info:userName,type:"account"},
			async:false,
			success:function(rs){
				//判断是否成功
				if(rs.status==1){
					//显示错误
					$("#usernameError").css({display:"block"});
					$("#usernameError").html(data.msg);
					//错误信息
				}else{
					//隐藏错误信息
					$("#usernameError").css({display:"none"});
					flag=true;
				}
				//
				
			}
		});
		return flag;
	}
	
	//2.是否为空
	$("#password").blur(function(){
		isUserPwdValidate=checkUserPwd("password");
		if(isUserRePwdValidate){
			isPwdValidate = checkPwdAndRePwd("password","rePssword");
		}
	});
	function checkUserPwd(objId){
		var pwd = $("#"+objId).val();
		$("#userpasswordError").css({display:"none"});
		//空
		if(pwd == ""){
			$("#userpasswordError").css({display:"block"});
			$("#userpasswordError").html("请输入密码！");
			return false;
		}
		//len
		if(pwd.length<6 || pwd.length>12){
			$("#userpasswordError").css({display:"block"});
			$("#userpasswordError").html("密码长度为6-12位！");
			return false;
		}
		//格式
		var reg =/^[0-9a-zA-Z]+$/;
		var str =document.getElementById("password").value;
		if(!reg.test(str)){
			$("#userpasswordError").css({display:"block"});
			$("#userpasswordError").html("密码只能为数字和英文！");
			return false;
		}
		return true;
	}
	
	//3.repwd是否为空
	$("#rePssword").blur(function(){
		isUserRePwdValidate=checkReUserPwd("rePssword");
		if(isUserPwdValidate && isUserRePwdValidate){
			isPwdValidate = checkPwdAndRePwd("password","rePssword");
		}
	});
	function checkReUserPwd(reObjId){
		var rePwd=$("#"+objId).val();
		$("#rePsswordError").css({display:"none"});
		if(rePwd == ""){
			$("#rePsswordError").css({display:"block"});
//			$("#rePsswordError").html("请输入");
			return false;
		}
		return true;
	}
	
	//是否一致
	function checkPwdAndRePwd(ObjId,reObjId){
		//get
		var pwd = $("#"+objId).val();
		var rePwd=$("#"+objId).val();
		$("#rePsswordError").css({display:"none"});
		if(!(pwd===ewPwd)){
			$("#rePsswordError").css({display:"block"});
			$("#rePsswordError").html("两次密码不一致！");
			return false;
		}
		return true;
	}
	
	//4.手机失去光标
	$("#phone").blur(function(){
		isPhoneValidate=checkPhone("phone");
	});
	//检测是否为空
	function checkPhone(){
		var phone = $("#"+objId).val();
		$("#phoneError").css({display:"none"});
		if(phone==""){
			$("#phoneError").css({display:"block"});
			$("#phoneError").html("请输入手机号码！");
			return false;
		}
		//格式
		var reg = /^1[0-9]{10}$/;
		var str = document.getElementById("phone").value;
		if(!reg.test(str)){
			$("#phoneError").css({display:"block"});
			$("#phoneError").html("请输入正确手机号码！");
			return false;
		}
		return true;
		
	}
	
	
	//5.email
	$("#email").blur(function(){
		isEmailValidate=checkEmail("email");
	});
	//检测是否为空
	function checkEmail(){
		var email = $("#"+objId).val();
		$("#emailError").css({display:"none"});
		if(email==""){
			$("#emailError").css({display:"block"});
			$("#emailError").html("请输入电子邮箱！");
			return false;
		}
		//格式
		var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$")
		var obj = document.getElementById("email").value;
		if(!reg.test(obj)){
			$("#emailError").css({display:"block"});
			$("#emailError").html("邮箱格式错误！");
			return false;
		}
		return true;
	}
	
	
	//6.question
	$("#question").blur(function(){
		isQuestionValidate=checkQuestion("question");
	});
	//检测是否为空
	function checkQuestion(){
		var question = $("#"+objId).val();
		$("#questionError").css({display:"none"});
		if(question == ""){
			$("#questionError").css({display:"block"});
			$("#questionError").html("请输入问题！");
			return false;
		}
		return true;
	}
	
	//7.answer
	$("#answer").blur(function(){
		isAnswerValidate=checkAnswer("answer");
	});
	//检测是否为空
	function checkAnswer(){
		var answer = $("#"+objId).val();
		$("#answerError").css({display:"none"});
		if(answer == ""){
			$("#answerError").css({display:"block"});
			$("#answerError").html("请输入答案！");
			return false;
		}
		return true;
	}
	
	//8.点击注册
	function registBtn(){
		$("#register_btn").click(function(){
			$(window).attr("location","login.html")
			//1
			if(!isUserNameValidate){
				return checkUserName("username");
			}
			//2
			if(!isUserPwdValidate){
				return checkUserPwd("password");
			}
			//3
			if(!isUserRePwdValidate){
				return checkReUserPwd("rePssword");
			}
			//4
			if(!isPwdValidate){
				return checkPwdAndRePwd("password","rePssword");
			}
			//5
			if(!isEmailValidate){
				return checkEmail("email");
			}
			//6
			if(!isPhoneValidate){
				return checkPhone("phone");
			}
			//7
			if(!isQuestionValidate){
				return checkQuestion("question");
			}
			//8
			if(!isAnswerValidate){
				return checkAnswer("answer");
			}
			var formData={	account:$("#username").val(),
							password:$("#password").val(),
							email:$("#email").val(),
							phone:$("#phone").val(),
							question:$("#question").val(),
							asw:$("#asw").val()
							};
			$.ajax({
				url:baseUrl+"user/do_register.do",
				type:"post",
				data:formData,
				success:function(rs){
					//判断是否成功
					if(rs.status==0){
						//注册成功
						alert("注册成功！");
						$(window).attr("location","login.html")
					}else{
						alert(re.msg);
						//错误信息
					}
					//
					
				}
			});
		});
	}
	
	return{
		registBtn:registBtn
	};
	
});