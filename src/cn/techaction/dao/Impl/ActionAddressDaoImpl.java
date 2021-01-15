package cn.techaction.dao.Impl;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.springframework.stereotype.Repository;

import cn.techaction.dao.ActionAddressDao;
import cn.techaction.pojo.ActionAddress;
@Repository
public class ActionAddressDaoImpl implements ActionAddressDao {
	@Resource
	private QueryRunner queryRunner;

	@Override
	public ActionAddress findAddrsById(Integer addrId) {
		// TODO 自动生成的方法存根
		String sql = "SELECT id,user_id,name,phone,mobile,province,city,district,addr,zip"
				+ ",default_addr,del_state,created,updated FROM action_address WHERE id = ?"
				+ " and del_state = 0";
		try {
			return queryRunner.query(sql, new BeanHandler<ActionAddress>(ActionAddress.class),addrId);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int findDefaultAddrByUserId(Integer userId) {
		// TODO Auto-generated method stub
		String sql="select count(id) as num from action_adress where user_id=? and default_addr=1";
		try {
			return queryRunner.query(sql, new ColumnListHandler<Long>("num"),userId).get(0).intValue();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return 0;
		}		
	}

	@Override
	public int insertAddress(ActionAddress addr) {
		// TODO Auto-generated method stub
		/*String sql="insert into action_address(user_id,name,phone,"
				+ "mobile,province,city,district,addr,zip,default_addr,created,updated) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params= {
				//addr.getUser_id(),addr.getName(),addr
		};
		try {
			//return 
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return 0;
		}*/
		return 0;
	}
		
}
