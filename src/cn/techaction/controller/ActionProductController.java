package cn.techaction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.techaction.common.SverResponse;
import cn.techaction.pojo.ActionProduct;
import cn.techaction.utils.PageBean;

@Controller
@RequestMapping("/product")
public class ActionProductController {
	public SverResponse<PageBean<ActionProduct>>findProducts(Integer productId,Integer partsId
			,Integer pageNum,Integer pageSize) {
		//调用Service层的方法分页查询
		
		return null;
	}
}
