package cn.techaction.controller.portal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.techaction.common.SverResponse;
import cn.techaction.pojo.User;
import cn.techaction.service.ActionUserService;
import cn.techaction.utils.ConstUtil;

@Controller
@RequestMapping("/user")
public class ActionUserPortalController {

	@Autowired
	private ActionUserService userService;
	/**
	 * 用户登录
	 * @param session
	 * @param account
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/do_login.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<User> doLogin(HttpSession session,String account,String password){
		SverResponse<User> response = userService.doLogin(account, password);
		if(response.isSuccess()) {
			//登录成功,将用户信息存入session
			session.setAttribute(ConstUtil.CUR_USER, response.getData());
		}
		return response;
	}
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/do_register.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<String> registerUser(User user){
		return userService.doRegister(user);
	}
	/**
	 * 验证用户并且获得用户对象
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/getuserbyaccount.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<User> getUserByAccount(String account){
		return userService.findUserByAccount(account);
	}
	/**
	 * 验证用户密码提示问题答案
	 * @param account
	 * @param question
	 * @param asw
	 * @return
	 */
	@RequestMapping(value="/checkuserasw.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<String> checkUserAnswer(String account,String question,String asw){
		return userService.checkUserAnswer(account,question,asw);
	}
	/**
	 * 重置密码
	 * @param userId
	 * @param newPwd
	 * @return
	 */
	@RequestMapping(value="/resetpassword.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<String> resetPassword(Integer userId,String newPwd){
		return userService.resetPassword(userId,newPwd);
	}
}
