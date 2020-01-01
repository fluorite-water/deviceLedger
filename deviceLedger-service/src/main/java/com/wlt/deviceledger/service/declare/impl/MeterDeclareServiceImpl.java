package com.wlt.deviceledger.service.declare.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wlt.deviceledger.bean.declare.ApproveRecord;
import com.wlt.deviceledger.bean.declare.MaterDeclareBean;
import com.wlt.deviceledger.bean.user.UserBean;
import com.wlt.deviceledger.dao.declare.IApproveRecordDao;
import com.wlt.deviceledger.dao.declare.IMeterDeclareDao;
import com.wlt.deviceledger.dao.materialInfo.IMaterialInfoDao;
import com.wlt.deviceledger.service.declare.IMeterDeclareService;
import com.wlt.deviceledger.util.base.ConstantUtils;
import com.wlt.deviceledger.util.base.ResultData;
import com.wlt.deviceledger.util.common.DateUtil;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年10月26日 下午4:40:15 
* @Description 类说明 ：材料申报实体类
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
@Service
public class MeterDeclareServiceImpl implements IMeterDeclareService{

	@Autowired
	private IMeterDeclareDao dao;
	@Autowired
	private IApproveRecordDao approveRecordDao;
	@Autowired
	private IMaterialInfoDao materialInfoDao;

	@Override
	public ResultData<Object> addDeclare(MaterDeclareBean bean,HttpSession session) throws Exception {
		ResultData<Object> res = new ResultData<Object>();
		Object user =  session.getAttribute("user");
		UserBean userBean = new UserBean();
		if(user != null) {
			userBean = (UserBean) user;
		}
		bean.setUserId(Integer.valueOf(userBean.getId()));
		bean.setApprovalState(ConstantUtils.APPROVAL_STATE0);
//		bean.setApprovalUserId(Integer.parseInt(userBean.getId()));
		bean.setCreateTime(DateUtil.getCurrenDateTime());
		bean.setIsDelete(1);
		bean.setIsPurchase(0);
		bean.setIsRecall(1);
//		bean.setIsSpeed(0);
		int dec = dao.insert(bean);
		if(dec > 0) {
			res.setCode(ConstantUtils.SUCCESS_CODE);
			res.setMsg("材料申报成功");
			res.setSuccess(ConstantUtils.SUCCESS_MESSAGE);
		}else {
			res.setCode(ConstantUtils.ERROR_CODE);
			res.setMsg("材料申报失败");
			res.setSuccess(ConstantUtils.ERROR_MESSAGE);
		}
		return res;
	}

	@Override
	public ResultData<Object> deptApprove(ApproveRecord bean,Integer userId) throws Exception {
		ResultData<Object> res = new ResultData<Object>();
		bean.setCreateTime(DateUtil.getCurrenDateTime());
		bean.setUserId(userId);
		int dec = approveRecordDao.insert(bean);
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
		MaterDeclareBean bean = new MaterDeclareBean();
		bean.setId(declareId);
		bean.setApprovalState(approvalState);
		dao.updateById(bean);
	}

	@Override
	public ResultData<Object> findDeptApproveList(Integer pageNum, Integer pageSize,HttpSession session) throws Exception {
		Object user =  session.getAttribute("user");
		UserBean userBean = new UserBean();
		Integer roleId =0;
		if(user != null) {
			userBean = (UserBean) user;
			// 根据roleId,判断审批到了哪一层
			roleId = userBean.getRoleId();
		}
		ResultData<Object> res = new ResultData<Object>();
		List<Map<String,Object>> list = null;
		PageHelper.startPage(pageNum, pageSize);
		if(roleId==2) {
			// 部门管理员 根据userId查询
			list = dao.findDeptApproveList(Integer.parseInt(userBean.getId()));
		}else if(roleId == 3) {
			// 设备管理员 根据 审批状态未1的查询
			list = dao.findApproveList(1);
		}else if(roleId == 4) {
			// 公司主管 根据审批状态为 2的查询
			list = dao.findApproveList(2);
		}else {
			return res;
		}
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(list);
		res.setObj(pageInfo);
		return res;
	
	}

	@Override
	public ResultData<Object> purchaseFinish(MaterDeclareBean bean) throws Exception {
		
		ResultData<Object> res = new ResultData<Object>();
		
		bean.setIsPurchase(1);
		bean.setPurchaseFinishTime(DateUtil.getCurrenDateTime());
		int dec = dao.updateById(bean);
		// 修改材料库中的数量
		int update = materialInfoDao.updateMeterNum(bean);
		if(dec > 0) {
			res.setCode(ConstantUtils.SUCCESS_CODE);
			res.setMsg("采购完成");
			res.setSuccess(ConstantUtils.SUCCESS_MESSAGE);
		}else {
			res.setCode(ConstantUtils.ERROR_CODE);
			res.setMsg("采购失败");
			res.setSuccess(ConstantUtils.ERROR_MESSAGE);
		}
		if(update > 0) {
			res.setCode(ConstantUtils.SUCCESS_CODE);
			res.setMsg("添加材料库成功");
			res.setSuccess(ConstantUtils.SUCCESS_MESSAGE);
		}else {
			res.setCode(ConstantUtils.ERROR_CODE);
			res.setMsg("添加材料库失败");
			res.setSuccess(ConstantUtils.ERROR_MESSAGE);
		}
		return res;
	}
}
 