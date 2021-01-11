package cn.techaction.service.Impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.techaction.common.SverResponse;
import cn.techaction.dao.ActionUserDao;
import cn.techaction.pojo.User;
import cn.techaction.service.ActionUserService;
import cn.techaction.utils.ConstUtil;
import cn.techaction.utils.MD5Util;
import cn.techaction.vo.ActionUserVo;

@Service
public class ActionUserServiceImpl implements ActionUserService{
	@Autowired
	private ActionUserDao actionUserDao;
	@Override
	public SverResponse<User> doLogin(String account, String password) {
		// TODO 自动生成的方法存根
		//1.判断用户名是否存在
		int rs=actionUserDao.checkUserByAccount(account);
		if(rs==0) {
			return SverResponse.createByErrorMessage("用户不存在!");
		}
		//2.根据用户名和密码查找用户
		String md5pwd=MD5Util.MD5Encode(password, "utf-8", false);
//		System.out.println(md5pwd);
		User user=actionUserDao.findUserByAccountAndPassword(account, md5pwd);
		//3.判断查找用户密码是否正确
		if(user==null) {
			return SverResponse.createByErrorMessage("密码错误!");
		}
		//置空密码
		user.setPassword(StringUtils.EMPTY);
		return SverResponse.createRespBySuccess("登录成功！", user);
	}
	@Override
	public SverResponse<String> isAdmin(User user) {
		// TODO 自动生成的方法存根
		if(user.getRole()==ConstUtil.Role.ROLE_ADMIN) {
			return SverResponse.createRespBySuccess();
		}
		return SverResponse.createRespByError();
	}
	@Override
	public SverResponse<List<ActionUserVo>> findUserList() {
		// TODO 自动生成的方法存根
		List<ActionUserVo> vos=Lists.newArrayList();
		//1.调用dao层类的方法
		List<User> users=actionUserDao.findAllUser();
		//2.处理:user对象转换成ActionUserVo对象
		for(User u:users) {
			vos.add(setNormalProperty(u));
		}
		return SverResponse.createRespBySuccess(vos);
	}
	/**
	 * 将user转换成actionUserVo对象
	 * @param user
	 * @return
	 */
	private ActionUserVo setNormalProperty(User user) {
		ActionUserVo vo=new ActionUserVo();
		vo.setAccount(user.getAccount());
		vo.setAge(user.getAge());
		vo.setEmail(user.getEmail());
		vo.setId(user.getId());
		vo.setName(user.getName());
		vo.setPhone(user.getPhone());
		if(user.getSex()==1) {
			vo.setSex("男");
		}else {
			vo.setSex("女");
		}
		return vo;
	}
	@Override
	public SverResponse<ActionUserVo> findUserById(Integer id) {
		// TODO 自动生成的方法存根
		//1.调用dao层类中的方法获得用户对象
		User user=actionUserDao.findUserById(id);
		//2.将user转化成actionUserVo对象
		ActionUserVo vo=setNormalProperty(user);
		return SverResponse.createRespBySuccess(vo);
	}

}
