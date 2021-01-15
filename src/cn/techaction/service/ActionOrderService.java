package cn.techaction.service;

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

}
