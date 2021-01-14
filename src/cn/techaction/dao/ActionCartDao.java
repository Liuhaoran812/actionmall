package cn.techaction.dao;

import cn.techaction.pojo.ActionCart;

public interface ActionCartDao {
	/**
	 * 根据用户id和产品id查询购物车
	 * @param userId
	 * @param productId
	 * @return
	 */
	public ActionCart findCartByUserAndProductId(Integer userId, Integer productId);
	/**
	 * 新增购物车记录
	 * @param cart
	 */
	public Integer insertCart(ActionCart cart);
	/**
	 * 更新购物车中的商品数量
	 * @param actionCart
	 * @return
	 */
	public Integer updateCartById(ActionCart actionCart);

}
