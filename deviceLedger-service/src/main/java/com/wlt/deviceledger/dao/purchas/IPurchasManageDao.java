package com.wlt.deviceledger.dao.purchas;

import java.util.List;
import java.util.Map;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年11月24日 下午12:47:26 
* @Description 类说明 ：
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
public interface IPurchasManageDao {

	/**
	 * 查询申报信息
	 * @return
	 */
	List<Map<String, Object>> findList();

	/**
	 * 查询领取信息
	 * @return
	 */
	List<Map<String, Object>> findReceiveList();

	
}
 