package com.wlt.deviceledger.service.systemManager.impl;

import java.util.List;

import com.wlt.deviceledger.bean.user.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wlt.deviceledger.bean.systemManager.DeptBean;
import com.wlt.deviceledger.dao.systemManager.IDeptDao;
import com.wlt.deviceledger.service.systemManager.IDeptService;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年10月22日 下午9:10:02 
* @Description 类说明 ：
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
@Service
public class DeptServiceImpl implements IDeptService{

	@Autowired
	private IDeptDao dao;
	
	@Override
	public List<DeptBean> findTree() throws Exception {
		
		QueryWrapper<DeptBean> queryWrapper = new QueryWrapper<DeptBean>();

		List<DeptBean> list = dao.selectList(queryWrapper);
		
		return list;
	}

	@Override
	public int addDept(DeptBean bean) throws Exception {
		
		int insert = dao.insert(bean);
		return insert;
	}

	@Override
	public int updateDept(DeptBean bean) {
		int update = dao.updateById(bean);
		return update;
	}

	@Override
	public int deleteDept(DeptBean bean) {
		int delete = dao.deleteById(bean);
		return delete;
	}

    @Override
    public List<DeptBean> getRoleListByPid(Integer pid) {

	    QueryWrapper<DeptBean> queryWrapper = new QueryWrapper<>();
	    DeptBean deptBean = new DeptBean();
	    deptBean.setPid(pid);
	    queryWrapper.setEntity(deptBean);
        List<DeptBean> list = dao.selectList(queryWrapper);

        return list;
    }


}
 