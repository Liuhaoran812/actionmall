package cn.techaction.controller.portal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.techaction.common.SverResponse;
import cn.techaction.pojo.ActionProduct;
import cn.techaction.service.ActionProductService;

@Controller
@RequestMapping("/product")
public class ActionProductPortalController {
	@Autowired
	private ActionProductService actionProductService;
	/**
	 * 查询热销商品
	 * @param num
	 * @return
	 */
	@RequestMapping(value="/findhotproducts.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<List<ActionProduct>> findHotProducts(Integer num){
		return actionProductService.findHotProducts(num);
	}
}
