package cn.techaction.service;

import java.util.List;

import cn.techaction.common.SverResponse;
import cn.techaction.pojo.ActionProduct;
import cn.techaction.utils.PageBean;
import cn.techaction.vo.ActionProductFloorVo;
import cn.techaction.vo.ActionProductListVo;

public interface ActionProductService {
	/**
	 * 查询商品
	 * @param productId
	 * @param partsId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public SverResponse<PageBean<ActionProduct>> findProduct(Integer productId,Integer partsId
			,Integer pageNum,Integer pageSize);
	/**
	 * 多条件查询商品信息
	 * @param actionProduct
	 * @return
	 */
	public SverResponse<List<ActionProductListVo>> findProducts(ActionProduct actionProduct);
	/**
	 * 保存商品信息(新增、修改)
	 * @param actionProduct
	 * @return
	 */
	public SverResponse<String> saveOrUpdate(ActionProduct actionProduct);
	/**
	 * 更新商品状态:上下架、热销
	 * @param productId
	 * @param status
	 * @param hot
	 * @return
	 */
	public SverResponse<String> updateStatus(Integer productId,Integer status,Integer hot);
	/**
	 * 前台:查找热销商品
	 * @param num	查找数量
	 * @return
	 */
	public SverResponse<List<ActionProduct>> findHotProducts(Integer num);
	/**
	 * 前台:获得首页所有楼层数据
	 * @return
	 */
	public SverResponse<ActionProductFloorVo> findFloorProducts();
}
