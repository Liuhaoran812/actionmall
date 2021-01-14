package cn.techaction.dao.Impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import cn.techaction.dao.ActionCartDao;
import cn.techaction.pojo.ActionCart;
@Repository
public class ActionCartDaoImpl implements ActionCartDao {
	@Resource
	private QueryRunner queryRunner;
	
	private String str = "id,user_id,product_id,quantity,created,updated,checked";
	@Override
	public ActionCart findCartByUserAndProductId(Integer userId, Integer productId) {
		// TODO 自动生成的方法存根
		String sql = "SELECT "+str+" FROM action_carts WHERE user_id = ? and product_id = ?";
		try {
			return queryRunner.query(sql, new BeanHandler<ActionCart>(ActionCart.class),userId,productId);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Integer insertCart(ActionCart cart) {
		// TODO 自动生成的方法存根
		String sql = "INSERT INTO action_carts(user_id,product_id,quantity,created,updated) VALUES(?,?,?,?,?)";
		Object[] params = {cart.getUser_id(),cart.getProduct_id(),cart.getQuantity(),cart.getCreated(),cart.getUpdated()};
		try {
			return queryRunner.update(sql,params);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public Integer updateCartById(ActionCart actionCart) {
		// TODO 自动生成的方法存根
		String sql = "UPDATE action_carts set quantity = ?,updated = ?,checked = ? WHERE id = ?";
		Object[] params = {actionCart.getQuantity(),actionCart.getUpdated(),actionCart.getChecked(),actionCart.getId()};
		try {
			return queryRunner.update(sql,params);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public List<ActionCart> findCartByUser(Integer userId) {
		// TODO 自动生成的方法存根
		String sql = "SELECT "+str+" FROM action_carts WHERE user_id = ?";
		try {
			return queryRunner.query(sql, new BeanListHandler<ActionCart>(ActionCart.class),userId);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public int deleteCartByUserId(Integer userId) {
		// TODO 自动生成的方法存根
		String sql = "DELETE FROM action_carts WHERE user_id = ?";
		try {
			return queryRunner.update(sql, userId);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return 0;
		}
	}
}
