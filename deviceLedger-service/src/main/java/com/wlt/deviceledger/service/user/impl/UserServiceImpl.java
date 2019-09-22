package com.wlt.deviceledger.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wlt.deviceledger.bean.user.UserBaseMapperDempBean;
import com.wlt.deviceledger.bean.user.UserBean;
import com.wlt.deviceledger.dao.user.IUserDao;
import com.wlt.deviceledger.service.user.IUserService;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年9月15日 下午4:20:26 
* @Description 类说明 ：
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserDao userDao;
	@Override
	public List<UserBean> findUserList() {
//		QueryWrapper<UserBean> emptryWrapper = new QueryWrapper<UserBean>();
//		emptryWrapper.setEntity(new UserBean());
//		return userDao.selectList(emptryWrapper);
		return userDao.findUserList();
	}
	@Override
	public List<UserBaseMapperDempBean> baseMapperDemo() {
		QueryWrapper<UserBaseMapperDempBean> queryWrapper = new QueryWrapper();
		return userDao.selectList(queryWrapper);
	}

}
 