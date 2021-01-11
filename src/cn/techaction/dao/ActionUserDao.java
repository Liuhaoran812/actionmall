package cn.techaction.dao;

import java.util.List;

import cn.techaction.pojo.User;

public interface ActionUserDao {
	/**
	 * 根据用户名查找用户
	 * @param account
	 * @return
	 */
	public int checkUserByAccount(String account);
	/**
	 * 根据用户名和密码查找用户
	 * @param account
	 * @param password
	 * @return
	 */
	public User findUserByAccountAndPassword(String account,String password);
	/**
	 * 获得所有用户信息
	 * @return
	 */
	public List<User> findAllUser();
	/**
	 * 根据用户id获得用户对象
	 * @param id
	 * @return
	 */
	public User findUserById(Integer id);
}
