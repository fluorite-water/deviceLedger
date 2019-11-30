package com.wlt.deviceledger.service.purchas;

import com.wlt.deviceledger.util.base.ResultData;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年11月24日 下午12:45:39 
* @Description 类说明 ：
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
public interface IPurchasManageService {

	/**
	 * 查询采购信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	ResultData<Object> findList(Integer pageNum, Integer pageSize);

	/**
	 * 查询领取数据
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	ResultData<Object> findReceiveList(Integer pageNum, Integer pageSize);

}
 