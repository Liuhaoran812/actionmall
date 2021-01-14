package cn.techaction.service;

import cn.techaction.common.SverResponse;
import cn.techaction.vo.ActionCartVo;

public interface ActionCartService {
	/**
	 * 保存商品信息到购物车
	 * @param userId
	 * @param productId
	 * @param count
	 * @return
	 */
	SverResponse<String> saveOrUpdate(Integer userId, Integer productId, Integer count);
	/**
	 * 查询用户购物车中商品信息
	 * @param userId
	 * @return
	 */
	SverResponse<ActionCartVo> findAllCarts(Integer userId);
	/**
	 * 清空购物车
	 * @param userId
	 * @return
	 */
	SverResponse<String> clearCart(Integer userId);

}
