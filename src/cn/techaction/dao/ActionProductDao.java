package cn.techaction.dao;

public interface ActionProductDao {
	/**
	 * 根据条件获得查询商品的数量
	 * @param productId
	 * @param partsId
	 * @return
	 */
	public Integer getTotalCount(Integer productId,Integer partsId);
}
