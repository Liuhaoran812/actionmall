package cn.techaction.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.techaction.common.SverResponse;
import cn.techaction.dao.ActionAddressDao;
import cn.techaction.dao.ActionOrderDao;
import cn.techaction.dao.ActionOrderItemDao;
import cn.techaction.pojo.ActionAddress;
import cn.techaction.pojo.ActionOrder;
import cn.techaction.pojo.ActionOrderItem;
import cn.techaction.service.ActionOrderService;
import cn.techaction.utils.ConstUtil;
import cn.techaction.utils.DateUtils;
import cn.techaction.utils.PageBean;
import cn.techaction.vo.ActionAddressVo;
import cn.techaction.vo.ActionOrderItemVo;
import cn.techaction.vo.ActionOrderVo;
@Service
public class ActionOrderServiceImpl implements ActionOrderService {
	
	@Autowired
	private ActionOrderDao actionOrderDao;
	@Autowired
	private ActionAddressDao actionAddressDao;
	@Autowired
	private ActionOrderItemDao actionOrderItemDao;
	@Override
	public SverResponse<PageBean<ActionOrderVo>> findOrders(Integer userId, Integer status, int pageNum, int pageSize) {
		// TODO 自动生成的方法存根
		//1.判断参数是否合法
		if (userId == null) {
			return SverResponse.createByErrorMessage("参数错误!");
		}
		//2.查找符合条件的总记录数
		int totalRecord = actionOrderDao.getTotalRecord(userId,status);
		//3.创建分页封装对象
		PageBean<ActionOrderVo> pageBean = new PageBean<ActionOrderVo>(pageNum,pageSize, totalRecord);
		//4.调用dao层类中方法读取数据
		List<ActionOrder> orders = actionOrderDao.findOrders(userId,status,pageBean.getStartIndex(),pageBean.getPageSize());
		//5.封装vo
		List<ActionOrderVo> voList = Lists.newArrayList();
		for (ActionOrder order : orders) {
			voList.add(createOrderVo(order,false));
		}
		pageBean.setData(voList);
		return SverResponse.createRespBySuccess(pageBean);
	}
	/**
	 * 封装vo
	 * @param order
	 * @param hasAddress
	 * @return
	 */
	private ActionOrderVo createOrderVo(ActionOrder order, boolean hasAddress) {
		// TODO 自动生成的方法存根
		ActionOrderVo orderVo = new ActionOrderVo();
		setNormalProperty(order,orderVo);
		setAddressProperty(order,orderVo,hasAddress);
		//设置订单项
		List<ActionOrderItem> orderItems = actionOrderItemDao.getItemsByOrderNo(order.getOrder_no());
		setOrderItemProperty(orderItems,orderVo);
		return orderVo;
	}
	/**
	 * 封装订单项属性
	 * @param orderItems
	 * @param orderVo
	 */
	private void setOrderItemProperty(List<ActionOrderItem> orderItems, ActionOrderVo orderVo) {
		// TODO 自动生成的方法存根
		List<ActionOrderItemVo> items = Lists.newArrayList(); 
		for (ActionOrderItem orderItem : orderItems) {
			items.add(createOrderItemVo(orderItem));
		}
		orderVo.setOrderItem(items);
	}
	/**
	 * 封装订单项vo
	 * @param orderItem
	 * @return
	 */
	private ActionOrderItemVo createOrderItemVo(ActionOrderItem orderItem) {
		// TODO 自动生成的方法存根
		ActionOrderItemVo itemVo = new ActionOrderItemVo();
		itemVo.setOrderNo(orderItem.getOrder_no());
		itemVo.setGoodsId(orderItem.getGoods_id());
		itemVo.setGoodsName(orderItem.getGoods_name());
		itemVo.setCurPrice(orderItem.getPrice());
		itemVo.setIconUrl(orderItem.getIcon_url());
		itemVo.setQuantity(orderItem.getQuantity());
		itemVo.setTotalPrice(orderItem.getTotal_price());
		itemVo.setCreated(DateUtils.date2Str(orderItem.getCreated()));
		return itemVo;
	}
	/**
	 * 封装地址属性
	 * @param order
	 * @param orderVo
	 * @param hasAddress
	 */
	private void setAddressProperty(ActionOrder order, ActionOrderVo orderVo, boolean hasAddress) {
		// TODO 自动生成的方法存根
		ActionAddress address = actionAddressDao.findAddrsById(order.getAddr_id());
		if (address != null) {
			orderVo.setDeliveryName(address.getName());
			if (hasAddress) {
				orderVo.setAddress(createAddressVo(address));
			}else {
				orderVo.setAddress(null);
			}
		}
	}
	/**
	 * 封装地址vo
	 * @param address
	 * @return
	 */
	private ActionAddressVo createAddressVo(ActionAddress address) {
		// TODO 自动生成的方法存根
		ActionAddressVo addressVo = new ActionAddressVo();
		addressVo.setName(address.getName());
		addressVo.setPhone(address.getPhone());
		addressVo.setMobile(address.getMobile());
		addressVo.setProvince(address.getProvince());
		addressVo.setCity(address.getCity());
		addressVo.setDistrict(address.getDistrict());
		addressVo.setZip(address.getZip());
		addressVo.setAddr(address.getAddr());
		return addressVo;
	}
	/**
	 * 封装订单的vo普通属性
	 * @param order
	 * @param orderVo
	 */
	private void setNormalProperty(ActionOrder order, ActionOrderVo orderVo) {
		// TODO 自动生成的方法存根
		orderVo.setOrderNo(order.getOrder_no());
		orderVo.setAmount(order.getAmount());
		orderVo.setType(order.getType());
		orderVo.setTypeDesc(ConstUtil.PaymentType.getTypeDesc(order.getType()));
		orderVo.setFreight(order.getFreight());
		orderVo.setStatus(order.getStatus());
		orderVo.setStatusDesc(ConstUtil.OrderStatus.getStatusDesc(order.getStatus()));
		orderVo.setAddrId(order.getAddr_id());
		//时间
		orderVo.setPaymentTime(DateUtils.date2Str(order.getPayment_time()));
		orderVo.setDeliveryTime(DateUtils.date2Str(order.getDelivery_time()));
		orderVo.setFinishTime(DateUtils.date2Str(order.getFinish_time()));
		orderVo.setCloseTime(DateUtils.date2Str(order.getClose_time()));
		orderVo.setCreated(DateUtils.date2Str(order.getCreated()));
	}
	@Override
	public SverResponse<String> cancelOrConfirmOrder(Integer userId, Long orderNo) {
		// TODO 自动生成的方法存根
		//1.查询订单
		ActionOrder order = actionOrderDao.findOrderByUserAndOrderNo(userId,orderNo);
		//2.判断订单是否存在
		if (order == null) {
			return SverResponse.createByErrorMessage("该用户订单不存在,或已删除!");
		}
		//3.订单是否已付款
		if (order.getStatus() == ConstUtil.OrderStatus.ORDER_PAID) {
			return SverResponse.createByErrorMessage("该订单已经付款,无法取消");
		}
		//4.根据状态修改订单信息
		ActionOrder updateOrder = new ActionOrder();
		updateOrder.setId(order.getId());
		updateOrder.setUpdated(new Date());
		//5.为取消订单操作
		if (order.getStatus() == ConstUtil.OrderStatus.ORDER_NO_PAY) {
			updateOrder.setStatus(ConstUtil.OrderStatus.ORDER_CANCELED);
			int rs = actionOrderDao.updateOrder(updateOrder);
			if (rs > 0) {
				return SverResponse.createRespBySuccessMessage("取消订单成功!");
			}
		}
		//6.为确认收货操作
		if (order.getStatus() == ConstUtil.OrderStatus.ORDER_SHIPPED) {
			updateOrder.setStatus(ConstUtil.OrderStatus.ORDER_SUCCESS);
			int rs = actionOrderDao.updateOrder(updateOrder);
			if (rs > 0) {
				return SverResponse.createRespBySuccessMessage("确认收货成功!");
			}
		}
		return SverResponse.createByErrorMessage("订单操作失败!");
	}
	@Override
	public SverResponse<ActionOrderVo> findOrderDetail(Integer userId, Long orderNo) {
		// TODO 自动生成的方法存根
		//1.判断参数是否合法
		if (userId == null || orderNo == null) {
			return SverResponse.createByErrorMessage("参数错误!");
		}
		//2.查找订单,封装vo
		ActionOrder order = actionOrderDao.findOrderByUserAndOrderNo(userId, orderNo);
		if (order == null) {
			return SverResponse.createByErrorMessage("该用户订单不存在,或已删除!");
		}
		ActionOrderVo orderVo = createOrderVo(order, true);
		return SverResponse.createRespBySuccess(orderVo);
	}
}
