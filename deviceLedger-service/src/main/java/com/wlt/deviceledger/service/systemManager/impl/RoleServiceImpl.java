package com.wlt.deviceledger.service.systemManager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wlt.deviceledger.bean.systemManager.RoleBean;
import com.wlt.deviceledger.dao.systemManager.IRoleDao;
import com.wlt.deviceledger.service.systemManager.IRoleService;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年10月23日 下午8:08:35 
* @Description 类说明 ：
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
@Service
public class RoleServiceImpl implements IRoleService{

	@Autowired
	private IRoleDao dao;
	
	@Override
	public List<RoleBean> findList() throws Exception {
		
		QueryWrapper<RoleBean> queryWrapper = new QueryWrapper<RoleBean>();
		RoleBean roleBean = new RoleBean();
		roleBean.setIsDel(1);
		queryWrapper.setEntity(roleBean);
		List<RoleBean> list = dao.selectList(queryWrapper);
		
		return list;
	}

	@Override
	public int addRole(RoleBean bean) throws Exception {
		bean.setIsDel(0);
		int insert = dao.insert(bean);
		return insert;
	}

	@Override
	public int updateRole(RoleBean bean) {
		int update = dao.updateById(bean);
		return update;
	}

	@Override
	public int deleteRole(RoleBean bean) {
		int delete = dao.updateById(bean);
		return delete;
	}
}
 