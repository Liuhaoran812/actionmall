package cn.techaction.controller.portal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.techaction.common.SverResponse;
import cn.techaction.pojo.User;
import cn.techaction.service.ActionCartService;
import cn.techaction.utils.ConstUtil;
import cn.techaction.vo.ActionCartVo;

@Controller
@RequestMapping("/cart")
public class ActionCartPortalController {
	@Autowired
	private ActionCartService actionCartService;
	/**
	 * 加入购物车
	 * @param session
	 * @param productId
	 * @param count
	 * @return
	 */
	@RequestMapping(value="/savecart.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<String> addProductCart(HttpSession session,Integer productId,Integer count){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null) {
			return SverResponse.createByErrorMessage("请登录后再操作!");
		}
		return actionCartService.saveOrUpdate(user.getId(),productId,count);
	}
	/**
	 * 查看购物车
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/findallcarts.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<ActionCartVo> findAllCarts(HttpSession session){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null) {
			return SverResponse.createByErrorMessage("请登录后再操作!");
		}
		return actionCartService.findAllCarts(user.getId());
	}
}
