package cn.techaction.dao;

import cn.techaction.pojo.ActionAddress;

public interface ActionAddressDao {
	/**
	 * 根据id查询收货人地址信息
	 * @param addrId
	 * @return
	 */
	public ActionAddress findAddrsById(Integer addrId);

	/**
	 * 查询是否存在默认地址
	 * @param user_id
	 * @return
	 */
	public int findDefaultAddrByUserId(Integer userId);

	/**
	 * 新增收货人地址信息
	 * @param addr
	 * @return
	 */
	public int insertAddress(ActionAddress addr);

}
