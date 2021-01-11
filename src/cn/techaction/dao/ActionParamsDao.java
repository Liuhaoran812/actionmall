package cn.techaction.dao;

import cn.techaction.pojo.ActionParam;

public interface ActionParamsDao {
	/**
	 * 根据id获得商品类型信息;
	 * @param id
	 * @return
	 */
	public ActionParam findParamById(Integer id);
}
