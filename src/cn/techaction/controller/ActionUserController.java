package cn.techaction.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.techaction.common.SverResponse;
import cn.techaction.pojo.User;
import cn.techaction.service.ActionUserService;
import cn.techaction.utils.ConstUtil;

@Controller
@RequestMapping("/mgr/user")
public class ActionUserController {
	@Autowired
	private ActionUserService actionUserService;
	@RequestMapping("/login.do")
	@ResponseBody
	public SverResponse<User> doLogin(HttpSession session,String account,String password) {
			//1.调用Service：登陆
			SverResponse<User> response=actionUserService.doLogin(account, password);
			//2.判断能否登陆
			if(response.isSuccess()) {
				//3.能登陆判断是否是管理员，是管理员存放session，否则错误信息
				User user=response.getData();
				if(user.getRole()==ConstUtil.Role.ROLE_ADMIN) {
					session.setAttribute(ConstUtil.CUR_USER, user);
					return response;
				}
				return SverResponse.createByErrorMessage("不是管理员，无法登陆!");
			}
			return response;
			
	}
}
