package com.wlt.deviceledger.service.receive;

import com.wlt.deviceledger.bean.receive.ApproveReceiveRecordBean;
import com.wlt.deviceledger.bean.receive.MaterReceiveBean;
import com.wlt.deviceledger.util.base.ResultData;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年11月24日 下午3:39:00 
* @Description 类说明 ：
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
public interface IReceiveService {

	/**
	 * 领取材料
	 * @param bean
	 * @return
	 */
	ResultData<Object> addReceive(MaterReceiveBean bean)throws Exception;
    /**
     * 查询领取材料申报信息
     */
	ResultData<Object> findReceiveList(Integer pageNum, Integer pageSize)throws Exception;
	/**
	 * 修改审批状态
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	ResultData<Object> receiveApprove(ApproveReceiveRecordBean bean)throws Exception;
	/**
	 * 同时修改审批状态
	 * @param declareId
	 * @param approvalState
	 */
	void updateApproveState(Integer declareId, Integer approvalState);
	
	/**
	 *领料完成
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	ResultData<Object> receiveFinish(MaterReceiveBean bean)throws Exception;

}
 