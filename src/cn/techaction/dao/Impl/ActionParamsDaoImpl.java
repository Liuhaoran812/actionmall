package cn.techaction.dao.Impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import cn.techaction.dao.ActionParamsDao;
import cn.techaction.pojo.ActionParam;

@Repository
public class ActionParamsDaoImpl implements ActionParamsDao{
	@Resource
	private QueryRunner queryRunner;
	@Override
	public ActionParam findParamById(Integer id) {
		// TODO 自动生成的方法存根
		String sql="SELECT * FROM action_params WHERE id = ?";
		try {
			return queryRunner.query(sql, new BeanHandler<ActionParam>(ActionParam.class),id);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<ActionParam> findParamsByParentId(Integer parentId) {
		// TODO 自动生成的方法存根
		String sql="SELECT * FROM action_params WHERE parent_id = ? order by sort_order";
		try {
			return queryRunner.query(sql, new BeanListHandler<ActionParam>(ActionParam.class),parentId);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
	}

}
