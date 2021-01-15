package cn.techaction.dao.Impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import cn.techaction.dao.ActionOrderItemDao;
import cn.techaction.pojo.ActionOrderItem;
@Repository
public class ActionOrderItemDaoImpl implements ActionOrderItemDao {
	@Resource
	private QueryRunner queryRunner;

	private String str = "id,uid,order_no,goods_id,goods_name,icon_url,price,quantity,total_price,created,updated";
	@Override
	public List<ActionOrderItem> getItemsByOrderNo(Long orderNo) {
		// TODO 自动生成的方法存根
		String sql = "SELECT " + str + " FROM action_order_items WHERE order_no = ?";
		try {
			return queryRunner.query(sql, new BeanListHandler<ActionOrderItem>(ActionOrderItem.class),orderNo);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public int[] batchInsert(List<ActionOrderItem> orderItems) {
		// TODO 自动生成的方法存根
		String sql = "INSERT INTO action_order_items(uid,order_no,good_id,good_name) VALUES()";
		return null;
	}
}
