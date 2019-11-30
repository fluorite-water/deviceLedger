package com.wlt.deviceledger.service.materialInfo.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.wlt.deviceledger.bean.materialInfo.MaterialInfoBean;
import com.wlt.deviceledger.dao.materialInfo.IMaterialInfoDao;
import com.wlt.deviceledger.service.materialInfo.IMaterialInfoService;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年10月6日 下午5:31:24 
* @Description 类说明 ：
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
@Service
public class MaterialInfoServiceImpl implements IMaterialInfoService{
	
	private static final  Logger log = LogManager.getLogger(MaterialInfoServiceImpl.class);
	
	@Autowired
	private IMaterialInfoDao dao;

	@SuppressWarnings("unchecked")
	@Override
	public List<MaterialInfoBean> findList(MaterialInfoBean bean) {
		PageHelper.startPage(1, 3);
		//分页 查询总数 不返回大于页码的数据
//		PageHelper.startPage(page,pageSize ,true,false);
		//分页 查询总数 如果页码大于最大页，返回最后一页数据
//		PageHelper.startPage(100,5,true);
		@SuppressWarnings("rawtypes")
		QueryWrapper<MaterialInfoBean> queryWrapper = new QueryWrapper();
		queryWrapper.setEntity(bean);
		bean.setIsDelete("1");
		List<MaterialInfoBean> list = dao.selectList(queryWrapper);
		return list;
	}

	
	/**
	 * 添加修改  材料信息
	 */
	@Override
	public int editMater(MaterialInfoBean bean) throws Exception {
		
		//如果id为空
		String id = bean.getId();
		int edit=0;
		//添加
		if(id == null) {
			edit = dao.insert(bean);
		//修改
		} else {
			edit = dao.updateById(bean);
		}
		return edit;
	}

	
	@Override
	public Integer remove(String id) throws Exception {
		
		MaterialInfoBean entity = new MaterialInfoBean();
		entity.setId(id);
		entity.setIsDelete("1");
		Integer update = dao.updateById(entity);
		
		return update;
	}


	@Override
	public MaterialInfoBean findbean(MaterialInfoBean bean) throws Exception {
		MaterialInfoBean b = dao.selectById(bean.getId());
		return b;
	}


	@Override
	public List<Map<String, Object>> declareList(Integer pageNum, Integer pageSize)throws Exception {
		List<Map<String, Object>> list = null;
		PageHelper.startPage(pageNum, pageSize);
		Integer roleId=3;
		if(roleId==1) {
			// 普通用户 根据自己userId 查询
			list = dao.declareList(1);
		}else if(roleId==2) {
			// 部门管理员 根据userId，或 审批人是自己 查询
			list = dao.deptDeclareList(1);
		}else if(roleId == 3) {
			// 设备管理员 查看所有
			list = dao.DeviceDeclareList();
		}else if(roleId == 4) {
			// 公司主管 查看所有
			list = dao.DeviceDeclareList();
		}else {
			return list;
		}
		return list;
	}


	@Override
	public List<Map<String, Object>> receiveList(Integer pageNum, Integer pageSize) throws Exception {
		
		List<Map<String, Object>> list = null;
		PageHelper.startPage(pageNum, pageSize);
		Integer roleId=3;
		if(roleId==1) {
			// 普通用户 根据自己userId 查询
			list = dao.receiveList(1);
		}else if(roleId==2) {
			// 部门管理员 根据userId，或 审批人是自己 查询
			list = dao.deptReceiveList(1);
		}else if(roleId == 3) {
			// 设备管理员 查看所有
			list = dao.DeviceReceiveList();
		}else if(roleId == 4) {
			// 公司主管 查看所有
			list = dao.DeviceReceiveList();
		}else {
			return list;
		}
		return list;
	}
}
 