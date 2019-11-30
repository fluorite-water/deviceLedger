package com.wlt.deviceledger.service.receive.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wlt.deviceledger.bean.receive.ApproveReceiveRecordBean;
import com.wlt.deviceledger.bean.receive.MaterReceiveBean;
import com.wlt.deviceledger.dao.materialInfo.IMaterialInfoDao;
import com.wlt.deviceledger.dao.receive.IApproveReceiveRecordDao;
import com.wlt.deviceledger.dao.receive.IReceiveDao;
import com.wlt.deviceledger.service.receive.IReceiveService;
import com.wlt.deviceledger.util.base.ConstantUtils;
import com.wlt.deviceledger.util.base.ResultData;
import com.wlt.deviceledger.util.common.DateUtil;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年11月24日 下午3:39:30 
* @Description 类说明 ：
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
@Service
public class ReceiveServiceImpl implements IReceiveService{

	@Autowired
	private IReceiveDao dao;
	
	@Autowired
	private IApproveReceiveRecordDao approveReceiveRecordDao;
	
	@Autowired
	private IMaterialInfoDao materialInfoDao;

	@Override
	public ResultData<Object> addReceive(MaterReceiveBean bean) throws Exception{
		
		ResultData<Object> res = new ResultData<Object>();
		//这里需要获取用户信息id
		// ?????????
		
		bean.setUserId(1);
		
		bean.setApprovalState(ConstantUtils.APPROVAL_STATE0);
		bean.setApprovalUserId(1);
		bean.setCreateTime(DateUtil.getCurrenDateTime());
		bean.setIsDelete(1);
		bean.setIsPurchase(0);
		bean.setIsRecall(1);
//		bean.setIsSpeed(0);
		int dec = dao.insert(bean);
		if(dec > 0) {
			res.setCode(ConstantUtils.SUCCESS_CODE);
			res.setMsg("材料领取申报成功");
			res.setSuccess(ConstantUtils.SUCCESS_MESSAGE);
		}else {
			res.setCode(ConstantUtils.ERROR_CODE);
			res.setMsg("材料领取申报失败");
			res.setSuccess(ConstantUtils.ERROR_MESSAGE);
		}
		return res;
	}

	@Override
	public ResultData<Object> findReceiveList(Integer pageNum, Integer pageSize) throws Exception{
		
		ResultData<Object> res = new ResultData<Object>();
		List<Map<String,Object>> list = null;
		// 获取用户id，roleId
		//？？？？？
		
		PageHelper.startPage(pageNum, pageSize);
		Integer roleId=3;
		if(roleId==2) {
			// 部门管理员 根据userId查询
			list = dao.findDeptReceiveList(1);
		}else if(roleId == 3) {
			// 设备管理员 根据 审批状态未1的查询
			list = dao.findReceiveList(1);
		}else if(roleId == 4) {
			// 公司主管 根据审批状态为 2的查询
			list = dao.findReceiveList(2);
		}else {
			return res;
		}
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(list);
		res.setObj(pageInfo);
		return res;
	}

	@Override
	public ResultData<Object> receiveApprove(ApproveReceiveRecordBean bean) throws Exception {
		
		ResultData<Object> res = new ResultData<Object>();
		bean.setCreateTime(DateUtil.getCurrenDateTime());
		bean.setUserId(1);
		int dec = approveReceiveRecordDao.insert(bean);
		int id = bean.getId();
		System.out.println(id);
		if(dec > 0) {
			res.setCode(ConstantUtils.SUCCESS_CODE);
			res.setMsg("审批成功");
			res.setSuccess(ConstantUtils.SUCCESS_MESSAGE);
		}else {
			res.setCode(ConstantUtils.ERROR_CODE);
			res.setMsg("审批失败");
			res.setSuccess(ConstantUtils.ERROR_MESSAGE);
		}
		return res;
	}
	@Override
	public void updateApproveState(Integer declareId, Integer approvalState) {
		MaterReceiveBean bean = new MaterReceiveBean();
		bean.setId(declareId);
		bean.setApprovalState(approvalState);
		dao.updateById(bean);
	}

	@Override
	public ResultData<Object> receiveFinish(MaterReceiveBean bean) throws Exception {
		
		
		ResultData<Object> res = new ResultData<Object>();
		
		bean.setIsPurchase(1);
		bean.setPurchaseFinishTime(DateUtil.getCurrenDateTime());
		int dec = dao.updateById(bean);
		// 修改材料库中的数量
		int update = materialInfoDao.updateMeterReceiveNum(bean);
		if(dec > 0) {
			res.setCode(ConstantUtils.SUCCESS_CODE);
			res.setMsg("领取完成");
			res.setSuccess(ConstantUtils.SUCCESS_MESSAGE);
		}else {
			res.setCode(ConstantUtils.ERROR_CODE);
			res.setMsg("领取失败");
			res.setSuccess(ConstantUtils.ERROR_MESSAGE);
		}
		if(update > 0) {
			res.setCode(ConstantUtils.SUCCESS_CODE);
			res.setMsg("减少材料库成功");
			res.setSuccess(ConstantUtils.SUCCESS_MESSAGE);
		}else {
			res.setCode(ConstantUtils.ERROR_CODE);
			res.setMsg("减少材料库失败");
			res.setSuccess(ConstantUtils.ERROR_MESSAGE);
		}
		return res;
	}
}
 