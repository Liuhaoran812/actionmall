package cn.techaction.service;

import java.util.List;

import cn.techaction.common.SverResponse;
import cn.techaction.utils.PageBean;
import cn.techaction.vo.ActionOrderVo;

public interface ActionOrderService {
	/**
	 * 订单分页列表
	 * @param userId
	 * @param status
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public SverResponse<PageBean<ActionOrderVo>> findOrders(Integer userId, Integer status, int pageNum, int pageSize);
	/**
	 * 取消订单
	 * @param userId
	 * @param orderNo
	 * @return
	 */
	public SverResponse<String> cancelOrConfirmOrder(Integer userId, Long orderNo);
	/**
	 * 根据编号获取订单详情
	 * @param userId
	 * @param orderNo
	 * @return
	 */
	public SverResponse<ActionOrderVo> findOrderDetail(Integer userId, Long orderNo);
	/**
	 * 生成订单
	 * @param userId
	 * @param addrId
	 * @return
	 */
	public SverResponse<ActionOrderVo> generateOrder(Integer userId, Integer addrId);

	/**
	 * 订单信息
	 * @param orderNo
	 * @return
	 */
	public SverResponse<List<ActionOrderVo>> findOrderForNoPages(Long orderNo);
	/**
	 * 根据订单号获得订单详情
	 * @param orderNo
	 * @return
	 */
	public SverResponse<ActionOrderVo> mgrDetail(Long orderNo);
}
