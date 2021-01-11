package cn.techaction.dao.Impl;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.springframework.stereotype.Repository;

import cn.techaction.dao.ActionProductDao;
import cn.techaction.pojo.ActionProduct;
@Repository
public class ActionProductDaoImpl implements ActionProductDao{
	@Resource
	private QueryRunner queryRunner;
	private String str="id,name,product_id,parts_id,icon_url,sub_images,"
			+ "detail,spec_param,price,stock,status,is_hot,created,updated";
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
	@Override
	public List<ActionProduct> findProductsByInfo(Integer productId, Integer partsId, Integer startIndex,
			Integer pageSize) {
		// TODO 自动生成的方法存根
		String sql="SELECT "+str+" FROM action_products where 1=1";
		List<Object> params=new ArrayList<>();
		if(productId!=null) {
			sql+=" and product_id = ?";
			params.add(productId);
		}
		if(partsId!=null) {
			sql+=" and parts_id = ?";
			params.add(partsId);
		}
		sql+=" limit ?,?";
		params.add(startIndex);
		params.add(pageSize);
		try {
			return queryRunner.query(sql,new BeanListHandler<ActionProduct>(ActionProduct.class
					),params.toArray());
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}
	@Override
	public List<ActionProduct> findProductsNoPage(ActionProduct condition) {
		// TODO 自动生成的方法存根
		String sql="SELECT "+str+" FROM action_products where 1=1";
		List<Object> params=new ArrayList<>();
		if(condition.getId()!=null) {
			sql+=" and id=?";
			params.add(condition.getId());
		}
		if(condition.getName()!=null) {
			sql+=" and name like ?";
			params.add(condition.getName());
		}
		if(condition.getStatus()!=null) {
			sql+=" and status=?";
			params.add(condition.getId());
		}
		if(condition.getProduct_id()!=null) {
			sql+=" and product_id=?";
			params.add(condition.getProduct_id());
		}
		if(condition.getParts_id()!=null) {
			sql+=" and parts_id=?";
			params.add(condition.getParts_id());
		}
		if(condition.getId()!=null) {
			sql+=" and id=?";
			params.add(condition.getId());
		}
		sql+=" order by	created,id desc";
		try {
			return queryRunner.query(sql, new BeanListHandler<ActionProduct>(ActionProduct.class),params.toArray());
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public int insertProduct(ActionProduct product) {
		// TODO 自动生成的方法存根
		String sql="INSERT INTO action_products(name,product_id,"
				+ "parts_id,icon_url,sub_images,detail,spec_param,price,"
				+ "stock,status,is_hot,created,updated)"
				+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params= {product.getName(),product.getProduct_id(),product.getParts_id(),product.getIcon_url()
				,product.getSub_images(),product.getDetail(),product.getSpec_param(),product.getPrice()
				,product.getStock(),product.getStatus(),product.getIs_hot(),product.getCreated(),product.getUpdate()};
		try {
			return queryRunner.update(sql, params);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}
	
}
