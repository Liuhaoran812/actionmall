package cn.techaction.service;

import java.util.List;

import cn.techaction.common.SverResponse;
import cn.techaction.pojo.User;
import cn.techaction.vo.ActionUserVo;

public interface ActionUserService {
	/**
	 * 用户登录
	 * @param account
	 * @param password
	 * @return
	 */
	public SverResponse<User> doLogin(String account,String password);
	/**
	 * 判断用户是否是管理员
	 * @param user
	 * @return
	 */
	public SverResponse<String> isAdmin(User user);
	/**
	 * 获得所有的用户信息
	 * @return
	 */
	public SverResponse<List<ActionUserVo>> findUserList();
	/**
	 * 根据用户id获得用户对象
	 * @param id
	 * @return
	 */
	public SverResponse<ActionUserVo> findUserById(Integer id);
}
