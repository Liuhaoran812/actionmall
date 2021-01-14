package cn.techaction.dao;

import java.util.List;

import cn.techaction.pojo.ActionParam;

public interface ActionParamsDao {
	/**
	 * 根据id获得商品类型信息;
	 * @param id
	 * @return
	 */
	public ActionParam findParamById(Integer id);
	/**
	 * 根据父节点id查找子节点参数
	 * @param i
	 * @return
	 */
	public List<ActionParam> findParamsByParentId(Integer parentId);
}
