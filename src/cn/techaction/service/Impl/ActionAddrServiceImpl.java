package cn.techaction.service.Impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.techaction.common.SverResponse;
import cn.techaction.dao.ActionAddressDao;
import cn.techaction.pojo.ActionAddress;
import cn.techaction.service.ActionAddrService;
@Service
public class ActionAddrServiceImpl implements ActionAddrService {

	@Autowired
	private ActionAddressDao aAddressDao;

	@Override
	public SverResponse<String> addAddress(ActionAddress addr) {
		// TODO Auto-generated method stub
		if(addr==null) {
			return SverResponse.createByErrorMessage("参数错误！");
		}
		int count=aAddressDao.findDefaultAddrByUserId(addr.getUser_id());
		if(count==0) {
			addr.setDefault_addr(1);
		}else {
			addr.setDefault_addr(0);
		}
		addr.setCreated(new Date());
		addr.setUpdated(new Date());
		int rs=aAddressDao.insertAddress(addr);
		return null;
	}
}
