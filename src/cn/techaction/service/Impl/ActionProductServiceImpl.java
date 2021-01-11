package cn.techaction.service.Impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.techaction.common.SverResponse;
import cn.techaction.dao.ActionParamsDao;
import cn.techaction.dao.ActionProductDao;
import cn.techaction.pojo.ActionProduct;
import cn.techaction.service.ActionProductService;
import cn.techaction.utils.ConstUtil;
import cn.techaction.utils.PageBean;
import cn.techaction.vo.ActionProductListVo;
@Service
public class ActionProductServiceImpl implements ActionProductService{
	@Autowired
	private ActionProductDao actionProductDao;
	@Autowired
	private ActionParamsDao actionParamsDao;
	@Override
	public SverResponse<PageBean<ActionProduct>> findProduct(Integer productId, Integer partsId, Integer pageNum,
			Integer pageSize) {
		// TODO 自动生成的方法存根
		//1.先要根据条件获得产品总数
		int totalCount=actionProductDao.getTotalCount(productId, partsId);
		PageBean<ActionProduct> pageBean=new PageBean<ActionProduct>(pageNum, pageSize, totalCount);
		//2.调用dao层获得分页查询的商品信息
		pageBean.setData(actionProductDao.findProductsByInfo(productId, partsId, pageBean.getStartIndex(), pageSize));
		return SverResponse.createRespBySuccess(pageBean);
	}
	@Override
	public SverResponse<List<ActionProductListVo>> findProducts(ActionProduct actionProduct) {
		// TODO 自动生成的方法存根
		if(actionProduct.getName()!=null) {
			actionProduct.setName("%"+actionProduct.getName()+"%");
		}
		//1.调用dao层类中的方法查询
		List<ActionProduct> products=actionProductDao.findProductsNoPage(actionProduct);
		//2.将actionProduct对象转换为actionProductListVo对象
		List<ActionProductListVo> voList=Lists.newArrayList();
		for(ActionProduct product:products) {
			voList.add(createProductListVo(product));
		}
		return SverResponse.createRespBySuccess(voList);
	}
	/**
	 * 封装vo对象
	 * @param actionProduct
	 * @return
	 */
	private ActionProductListVo createProductListVo(ActionProduct actionProduct) {
		ActionProductListVo vo=new ActionProductListVo();
		vo.setId(actionProduct.getId());
		vo.setName(actionProduct.getName());
		vo.setPrice(actionProduct.getPrice());
		vo.setIcon_url(actionProduct.getIcon_url());
		vo.setStatus(actionProduct.getStatus());
		vo.setIs_hot(actionProduct.getIs_hot());
		//处理特殊属性
		vo.setStatusDesc(ConstUtil.ProductStatus.getStatusDesc(actionProduct.getStatus()));
		
		vo.setHotStatus(ConstUtil.HotStatus.getHotDesc(actionProduct.getIs_hot()));
		
		vo.setProductCategory(actionParamsDao.findParamById(actionProduct.getProduct_id()).getName());
		
		vo.setPartsCategory(actionParamsDao.findParamById(actionProduct.getParts_id()).getName());
		
		return vo;
	}
	@Override
	public SverResponse<String> updateStatus(Integer productId, Integer status, Integer hot) {
		// TODO 自动生成的方法存根
		if(productId==null||status==null||hot==null) {
			return SverResponse.createByErrorMessage("参数非法");
		}
		ActionProduct actionProduct=new ActionProduct();
		actionProduct.setId(productId);
		actionProduct.setUpdate(new Date());
		//判断修改上下架还是修改热销
		if(status==-1) {
			actionProduct.setIs_hot(hot);
		}else if(hot==-1) {
			actionProduct.setStatus(status);
		}
		//调用dao层类中的方法更新商品信息
		
		return null;
	}
	@Override
	public SverResponse<String> saveOrUpdate(ActionProduct actionProduct) {
		// TODO 自动生成的方法存根
		if(actionProduct==null) {
			return SverResponse.createByErrorMessage("参数非法");
		}
		//1.处理主图和子图的链接，从前端传递过来的sub_images里主图分离
		//第一个链接作为主图链接，其他作为子图链接
		//修改时:如果重新上传了图片,会清空原来的，新的链接处理和新增相同
		//修改时:如果没有重新上传图片，
		if(!StringUtils.isEmpty(actionProduct.getSub_images())) {
			String[] array=actionProduct.getSub_images().split(",");
			//拿出第一个元素作为主图
			actionProduct.setIcon_url(array[0]);
			//剩下的作为子图
			String temp=actionProduct.getSub_images();
			int index=temp.indexOf(",");
			if(index!=-1) {
				if(temp.substring(index+1).equals("null")) {
					actionProduct.setSub_images(null);
				}else {
					actionProduct.setSub_images(temp.substring(index+1));
				}
			}else {
				actionProduct.setSub_images(null);
			}
		}
		//判断是新增还是修改
		if(actionProduct.getId()!=null) {
			actionProduct.setUpdate(new Date());
			//调用dao层类中的方法修改商品信息
			int rs=actionProductDao.updateProduct(actionProduct);
			if(rs>0) {
				return SverResponse.createRespBySuccessMessage("商品修改成功!");
			}
			return SverResponse.createByErrorMessage("商品修改失败!");
		}else {
			//2.处理其他的属性
			actionProduct.setStatus(ConstUtil.ProductStatus.STATUS_NEW);
			actionProduct.setIs_hot(ConstUtil.HotStatus.NORMAL_STATUS);
			actionProduct.setCreated(new Date());
			actionProduct.setUpdate(new Date());
			//3.调用dao层类中的方法新增商品信息
			int rs=actionProductDao.insertProduct(actionProduct);
			if(rs>0) {
				return SverResponse.createRespBySuccessMessage("商品新增成功!");
			}
			return SverResponse.createByErrorMessage("商品新增失败!");
		}
	}
}
