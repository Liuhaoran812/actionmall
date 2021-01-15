package cn.techaction.dao;

import java.util.List;

import cn.techaction.pojo.ActionOrder;

public interface ActionOrderDao {
	/**
	 * 根据用户id获得订单信息
	 * @param uid
	 * @return
	 */
	public List<ActionOrder> findOrderByUid(Integer uid);
	/**
	 * 前台:获取用户订单总数(各种状态下)
	 * @param userId
	 * @param status
	 * @return
	 */
	public int getTotalRecord(Integer userId, Integer status);
	/**
	 * 获取用户订单分页列表
	 * @param userId
	 * @param status
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<ActionOrder> findOrders(Integer userId, Integer status, int startIndex, int pageSize);
}
