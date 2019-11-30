package com.wlt.deviceledger.service.purchas.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wlt.deviceledger.dao.purchas.IPurchasManageDao;
import com.wlt.deviceledger.service.purchas.IPurchasManageService;
import com.wlt.deviceledger.util.base.ResultData;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年11月24日 下午12:46:12 
* @Description 类说明 ：
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
@Service
public class PurchasManageServiceImpl implements IPurchasManageService{

	@Autowired
	private IPurchasManageDao dao;

	@Override
	public ResultData<Object> findList(Integer pageNum, Integer pageSize) {
		ResultData<Object> res = new ResultData<Object>();
		List<Map<String,Object>> list = null;
		PageHelper.startPage(pageNum, pageSize);
		list = dao.findList();
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(list);
		res.setObj(pageInfo);
		return res;
	}

	@Override
	public ResultData<Object> findReceiveList(Integer pageNum, Integer pageSize) {
		
		ResultData<Object> res = new ResultData<Object>();
		List<Map<String,Object>> list = null;
		PageHelper.startPage(pageNum, pageSize);
		list = dao.findReceiveList();
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(list);
		res.setObj(pageInfo);
		return res;
	}
	
	
	
}
 