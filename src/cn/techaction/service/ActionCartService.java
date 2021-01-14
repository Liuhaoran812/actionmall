package cn.techaction.service;

import cn.techaction.common.SverResponse;

public interface ActionCartService {
	/**
	 * 保存商品信息到购物车
	 * @param userId
	 * @param productId
	 * @param count
	 * @return
	 */
	SverResponse<String> saveOrUpdate(Integer userId, Integer productId, Integer count);

}
