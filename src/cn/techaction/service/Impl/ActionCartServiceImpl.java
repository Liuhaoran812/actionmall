package cn.techaction.service.Impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.techaction.common.SverResponse;
import cn.techaction.dao.ActionCartDao;
import cn.techaction.pojo.ActionCart;
import cn.techaction.service.ActionCartService;
@Service
public class ActionCartServiceImpl implements ActionCartService {
	@Autowired
	private ActionCartDao actionCartDao;

	@Override
	public SverResponse<String> saveOrUpdate(Integer userId, Integer productId, Integer count) {
		// TODO 自动生成的方法存根
		//1.验证参数是否正确
		if(userId == null || productId == null || count == null) {
			return SverResponse.createByErrorMessage("参数错误!");
		}
		//2.查看用户的购物车中是否存在商品
		ActionCart actionCart = actionCartDao.findCartByUserAndProductId(userId,productId);
		if (actionCart == null) {
			//3.不存在,则新增
			ActionCart cart = new ActionCart();
			cart.setUser_id(userId);
			cart.setProduct_id(productId);
			cart.setQuantity(count);
			Date curDate = new Date();
			cart.setCreated(curDate);
			cart.setUpdated(curDate);
			int rs = actionCartDao.insertCart(cart);
			if (rs == 0) {
				return SverResponse.createByErrorMessage("商品新增失败!");
			}
		}else {
			//4.存在,则数量增加
			int cartCount = actionCart.getQuantity() + count;
			actionCart.setQuantity(cartCount);
			actionCart.setUpdated(new Date());
			int rs = actionCartDao.updateCartById(actionCart);
			if (rs == 0) {
				return SverResponse.createByErrorMessage("商品数量增加失败!");
			}
		}
		return SverResponse.createRespBySuccessMessage("商品已成功加入到购物车!");
	}
}
