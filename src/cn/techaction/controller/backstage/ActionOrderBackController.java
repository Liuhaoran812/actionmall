package cn.techaction.controller.backstage;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.techaction.common.ResponseCode;
import cn.techaction.common.SverResponse;
import cn.techaction.pojo.User;
import cn.techaction.service.ActionOrderService;
import cn.techaction.service.ActionUserService;
import cn.techaction.utils.ConstUtil;
import cn.techaction.utils.PageBean;
import cn.techaction.vo.ActionOrderVo;

@Controller
@RequestMapping("/mgr/order")
public class ActionOrderBackController {

	@Autowired
	private ActionOrderService actionOrderService;
	@Autowired
	private ActionUserService userService;
	
	@RequestMapping("/findorders_nopages.do")
	@ResponseBody
	public SverResponse<List<ActionOrderVo>> findOrder(HttpSession session,Long orderNo){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作!");
		}
		//2.用户是不是管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法:查询订单
			return actionOrderService.findOrderForNoPages(orderNo);
		}
				
		return SverResponse.createByErrorMessage("您无操作权限!");

	}
	/**
	 * 查看订单详情
	 * @param session
	 * @param orderNo
	 * @return
	 */
	@RequestMapping("/getdetail.do")
	@ResponseBody
	public SverResponse<ActionOrderVo> getDetail(HttpSession session,Long orderNo){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作!");
		}
		//2.用户是不是管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法:查询订单详情
			return actionOrderService.mgrDetail(orderNo);
		}
				
		return SverResponse.createByErrorMessage("您无操作权限!");
	}
	/**
	 * 获取所有订单
	 * @param session
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/findorders.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<PageBean<ActionOrderVo>> getList(HttpSession session,
			@RequestParam(value="pageNum",defaultValue="1") int pageNum,
			@RequestParam(value="pageSize",defaultValue="10") int pageSize){
		//判断用户是否登陆
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null) {
			return SverResponse.createByErrorMessage("请登录后再操作!");
		}
		//2.用户是不是管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法:查询订单列表
			return actionOrderService.findOrders(user.getId(),0,pageNum,pageSize);
		}
		return SverResponse.createByErrorMessage("您无操作权限!");
	}
}
