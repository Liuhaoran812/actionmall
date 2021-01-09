package cn.techaction.dao.Impl;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import cn.techaction.dao.ActionProductDao;
@Repository
public class ActionProductDaoImpl implements ActionProductDao{

	@Override
	public Integer getTotalCount(Integer productId, Integer partsId) {
		// TODO 自动生成的方法存根
		String sql="select count(*) from action_products where 1=1 ";
		if(productId!=null) {
			sql+=" and product_id = ?";
		}
		if(partsId!=null) {
			sql+=" and parts_id = ?";
		}
		try {
			System.out.println("!!!");
			return 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}
	
}
