package cn.techaction.dao.Impl;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.springframework.stereotype.Repository;

import cn.techaction.dao.ActionProductDao;
@Repository
public class ActionProductDaoImpl implements ActionProductDao{
	@Resource
	private QueryRunner queryRunner;
	@Override
	public Integer getTotalCount(Integer productId, Integer partsId) {
		// TODO 自动生成的方法存根
		String sql="select count(*) as num from action_products where 1=1 ";
		List<Object> params=new ArrayList<>();
		if(productId!=null) {
			sql+=" and product_id = ?";
			params.add(productId);
		}
		if(partsId!=null) {
			sql+=" and parts_id = ?";
			params.add(partsId);
			System.out.println("1111");
		}
		try {
			return queryRunner.query(sql, new ColumnListHandler<Long>("num"),params.toArray())
					.get(0).intValue();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}
	
}
