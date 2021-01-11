package cn.techaction.dao;

import java.util.List;

import cn.techaction.pojo.ActionProduct;

public interface ActionProductDao {
	/**
	 * 根据条件获得查询商品的数量
	 * @param productId
	 * @param partsId
	 * @return
	 */
	public Integer getTotalCount(Integer productId,Integer partsId);
	/**
	 * 查找某一页的商品信息
	 * @param productId
	 * @param partsId
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<ActionProduct> findProductsByInfo(Integer productId,Integer partsId
			,Integer startIndex,Integer pageSize);
	/**
	 * 多条件查询商品信息
	 * @param condition
	 * @return
	 */
	public List<ActionProduct> findProductsNoPage(ActionProduct condition);
	/**
	 * 新增商品
	 * @param product
	 * @return
	 */
	public int insertProduct(ActionProduct product);
	/**
	 * 修改商品
	 * @param product
	 * @return
	 */
	public int updateProduct(ActionProduct product);
}
