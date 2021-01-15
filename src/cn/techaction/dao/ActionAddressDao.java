package cn.techaction.dao;

import cn.techaction.pojo.ActionAddress;

public interface ActionAddressDao {
	/**
	 * 根据id查询收货人地址信息
	 * @param addrId
	 * @return
	 */
	public ActionAddress findAddrsById(Integer addrId);

}
