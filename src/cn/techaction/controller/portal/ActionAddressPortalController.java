package cn.techaction.controller.portal;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.techaction.common.SverResponse;
import cn.techaction.pojo.ActionAddress;
import cn.techaction.pojo.User;
import cn.techaction.service.ActionAddrService;
import cn.techaction.utils.ConstUtil;

@Controller
@RequestMapping("/addr")
public class ActionAddressPortalController {

	@Autowired
	private ActionAddrService aAddrService;
	/**
	 * 新增地址
	 * @param session
	 * @param addr
	 * @return
	 */
	@RequestMapping(value="/saveaddr.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<List<ActionAddress>> saveAddress(HttpSession session,ActionAddress addr){
		User user=(User) session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorMessage("请登录后在进行操作！");
		}
		addr.setUser_id(user.getId());
		
		SverResponse<String> result=null;
		if(addr.getId()==null) {
			result=aAddrService.addAddress(addr);
		}else {
			result=aAddrService.updateAddress(addr);
		}
		if(result.isSuccess()) {
			return aAddrService.findAddrsByUserId(user.getId());
		}
		return SverResponse.createByErrorMessage(result.getMsg());
		
	}
	
	
}
