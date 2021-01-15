package cn.techaction.service;

import java.util.List;

import cn.techaction.common.SverResponse;
import cn.techaction.pojo.ActionAddress;

public interface ActionAddrService {

	/**
	 * 新增收件人地址信息
	 * @param addr
	 * @return
	 */
	public SverResponse<String> addAddress(ActionAddress addr);

	/**
	 * 更新收件人地址信息
	 * @param addr
	 * @return
	 */
	public SverResponse<String> updateAddress(ActionAddress addr);

	/**
	 * 查找某个用户的所有收货地址
	 * @param id
	 * @return
	 */
	public SverResponse<List<ActionAddress>> findAddrsByUserId(Integer userid);

}
