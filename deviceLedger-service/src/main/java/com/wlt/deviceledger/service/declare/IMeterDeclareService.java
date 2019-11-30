package com.wlt.deviceledger.service.declare;

import com.wlt.deviceledger.bean.declare.ApproveRecord;
import com.wlt.deviceledger.bean.declare.MaterDeclareBean;
import com.wlt.deviceledger.util.base.ResultData;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年10月26日 下午4:39:48 
* @Description 类说明 ：材料申报接口
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
public interface IMeterDeclareService {

	/**
	 * 材料申报
	 */
	ResultData<Object> addDeclare(MaterDeclareBean bean)throws Exception;

	/**
	 * 本部门审批
	 * @param bean
	 * @return
	 */
	ResultData<Object> deptApprove(ApproveRecord bean)throws Exception;

	/**
	 * 修改审批状态
	 * @param declareId  申报id
	 * @param approvalState1 状态
	 */
	void updateApproveState(Integer declareId, Integer approvalState);

	/**
	 * 本部门管理员查看申报信息list
	 * @param pageSize 
	 * @param pageNum 
	 * @return
	 */
	ResultData<Object> findDeptApproveList(Integer pageNum, Integer pageSize)throws Exception;

	/**
	 * 完成采购
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	ResultData<Object> purchaseFinish(MaterDeclareBean bean)throws Exception;

}
 