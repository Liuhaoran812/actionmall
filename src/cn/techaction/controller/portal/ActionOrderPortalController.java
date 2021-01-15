package cn.techaction.controller.portal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.techaction.common.SverResponse;
import cn.techaction.pojo.User;
import cn.techaction.service.ActionOrderService;
import cn.techaction.utils.ConstUtil;
import cn.techaction.utils.PageBean;
import cn.techaction.vo.ActionOrderVo;

@Controller
@RequestMapping("/order")
public class ActionOrderPortalController {
	@Autowired
	private ActionOrderService actionOrderService;
	
	/**
	 * 获取订单列表
	 * @param session
	 * @param status
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/getlist.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<PageBean<ActionOrderVo>> getList(HttpSession session,Integer status,
			@RequestParam(value="pageNum",defaultValue="1") int pageNum,
			@RequestParam(value="pageSize",defaultValue="10") int pageSize){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null) {
			return SverResponse.createByErrorMessage("请登录后再操作!");
		}
		return actionOrderService.findOrders(user.getId(),status,pageNum,pageSize);
	}
	/**
	 * 取消订单
	 * @param session
	 * @param orderNo
	 * @return
	 */
	@RequestMapping(value="/cancelorder.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<String> cancelOrder(HttpSession session,Long orderNo){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null) {
			return SverResponse.createByErrorMessage("请登录后再操作!");
		}
		return actionOrderService.cancelOrConfirmOrder(user.getId(),orderNo);
	}
	/**
	 * 确认收货
	 * @param session
	 * @param orderNo
	 * @return
	 */
	@RequestMapping(value="/confirmreceipt.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<String> confirmOrder(HttpSession session,Long orderNo){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null) {
			return SverResponse.createByErrorMessage("请登录后再操作!");
		}
		return actionOrderService.cancelOrConfirmOrder(user.getId(),orderNo);
	}
	/**
	 * 获取订单详细信息
	 * @param session
	 * @param OrderNo
	 * @return
	 */
	@RequestMapping(value="/getdetail.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<ActionOrderVo> getDetail(HttpSession session,Long orderNo){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null) {
			return SverResponse.createByErrorMessage("请登录后再操作!");
		}
		return actionOrderService.findOrderDetail(user.getId(),orderNo);
	}
}
