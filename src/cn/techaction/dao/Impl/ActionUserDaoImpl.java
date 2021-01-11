package cn.techaction.dao.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.springframework.stereotype.Repository;

import cn.techaction.dao.ActionUserDao;
import cn.techaction.pojo.User;

@Repository
public class ActionUserDaoImpl implements ActionUserDao{
	@Resource
	private QueryRunner queryRunner;
	@Override
	public int checkUserByAccount(String account) {
		// TODO 自动生成的方法存根
		String sql="select count(*) as num from action_users where account=?";
		try {
			List<Long> rs=queryRunner.query(sql, new ColumnListHandler<Long>("num"),account);
			return rs.size()>0?rs.get(0).intValue():0;
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public User findUserByAccountAndPassword(String account, String password) {
		// TODO 自动生成的方法存根
		String sql="select * from action_users where account=? and password=?";
		try {
			return queryRunner.query(sql, new BeanHandler<User>(User.class),account,password);
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<User> findAllUser() {
		// TODO 自动生成的方法存根
		String sql="SELECT * FROM action_users WHERE del=0";
		try {
			return queryRunner.query(sql, new BeanListHandler<User>(User.class));
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public User findUserById(Integer id) {
		// TODO 自动生成的方法存根
		String sql="SELECT * FROM action_users WHERE id=?";
		try {
			return queryRunner.query(sql, new BeanHandler<User>(User.class),id);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public int updateUserInfo(User user) {
		// TODO 自动生成的方法存根
		String sql="UPDATE action_users SET account=?,password=?,email=?,"
				+ "phone=?,question=?,asw=?,role=?,create_time=?,update_time=?,"
				+ "age=?,sex=?,del=?,name=? WHERE id=?";
		List<Object> param=new ArrayList<>();
		param.add(user.getAccount());
		param.add(user.getPassword());
		param.add(user.getEmail());
		param.add(user.getPhone());
		param.add(user.getQuestion());
		param.add(user.getAsw());
		param.add(user.getRole());
		param.add(user.getCreate_time());
		param.add(user.getUpdate_time());
		param.add(user.getAge());
		param.add(user.getSex());
		param.add(user.getDel());
		param.add(user.getName());
		param.add(user.getId());
		try {	
			return queryRunner.update(sql,param.toArray());
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}

}
