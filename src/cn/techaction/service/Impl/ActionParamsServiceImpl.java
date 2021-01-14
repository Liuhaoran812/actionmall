package cn.techaction.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.techaction.common.SverResponse;
import cn.techaction.dao.ActionParamsDao;
import cn.techaction.pojo.ActionParam;
import cn.techaction.service.ActionParamsService;
@Service
public class ActionParamsServiceImpl implements ActionParamsService {

	@Autowired
	private ActionParamsDao actionParamsDao;

	@Override
	public SverResponse<List<ActionParam>> findAllParams() {
		// TODO 自动生成的方法存根
		//1.查找一级子节点
		List<ActionParam> paramList = actionParamsDao.findParamsByParentId(0);
		//2.递归查询所有子节点
		for(ActionParam param : paramList) {
			findDirectChildren(param);
		}
		return SverResponse.createRespBySuccess(paramList);
	}
	/**
	 * 递归查找
	 * @param parentParam
	 */
	private void findDirectChildren(ActionParam parentParam) {
		// TODO 自动生成的方法存根
		//查找子节点
		List<ActionParam> paramList = actionParamsDao.findParamsByParentId(parentParam.getId());
		parentParam.setChildren(paramList);
		for (ActionParam actionParam : paramList) {
			findDirectChildren(actionParam);
		}
	}
}
