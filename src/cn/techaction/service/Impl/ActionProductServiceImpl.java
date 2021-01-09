package cn.techaction.service.Impl;

import org.springframework.stereotype.Service;

import cn.techaction.common.SverResponse;
import cn.techaction.pojo.ActionProduct;
import cn.techaction.service.ActionProductService;
import cn.techaction.utils.PageBean;
@Service
public class ActionProductServiceImpl implements ActionProductService{

	@Override
	public SverResponse<PageBean<ActionProduct>> findProduct(Integer productId, Integer partsId, Integer pageNum,
			Integer pageSize) {
		// TODO 自动生成的方法存根
		//1.先要根据条件获得产品总数
		//2.调用dao层获得分页查询的商品信息
		return null;
	}

}
