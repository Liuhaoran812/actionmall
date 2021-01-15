package cn.techaction.dao.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.dbutils.QueryRunner;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.springframework.stereotype.Repository;

import cn.techaction.dao.ActionOrderDao;
import cn.techaction.pojo.ActionOrder;

@Repository
public class ActionOrderDaoImpl implements ActionOrderDao{
	
	@Resource
	private QueryRunner queryRunner;
	
	private String str = "id,order_no,uid,addr_id,amount,type,freight,"
			+ "status,payment_time,delivery_time,finish_time,close_time,updated,created";
	@Override
	public List<ActionOrder> findOrderByUid(Integer uid) {
		// TODO 自动生成的方法存根
		String sql="SELECT * FROM action_orders WHERE uid=?";
		try {
			return queryRunner.query(sql, new BeanListHandler<ActionOrder>(ActionOrder.class),uid);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}
	@Override
	public int getTotalRecord(Integer userId, Integer status) {
		// TODO 自动生成的方法存根
		String sql="SELECT count(id) as num FROM action_orders WHERE uid=?";
		List<Object> params = new ArrayList<>();
		params.add(userId);
		if (status != 0) {
			sql += " and status = ?";
			params.add(status);
		}
		try {
			return queryRunner.query(sql,new ColumnListHandler<Long>("num"),params.toArray()).get(0).intValue();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public List<ActionOrder> findOrders(Integer userId, Integer status, int startIndex, int pageSize) {
		// TODO 自动生成的方法存根
		String sql = "SELECT "+str+" FROM action_orders WHERE uid=?";
		List<Object> params = new ArrayList<>();
		params.add(userId);
		if (status != 0) {
			sql += " and status = ?";
			params.add(status);
		}
		sql += " limit ?,?";
		params.add(startIndex);
		params.add(pageSize);
		try {
			return queryRunner.query(sql, new BeanListHandler<ActionOrder>(ActionOrder.class),params.toArray());
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}	
	}

}
