package com.wlt.deviceledger.dao.receive;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wlt.deviceledger.bean.receive.MaterReceiveBean;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年11月24日 下午3:40:36 
* @Description 类说明 ：
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
public interface IReceiveDao  extends BaseMapper<MaterReceiveBean>{

	/**
	 * 根据 本部门管理员 userId 查询
	 * @param userId
	 * @return
	 */
	List<Map<String, Object>> findDeptReceiveList(int userId);

	/**
	 * 设备管理部、公司主管查询
	 * @param approveState
	 * @return
	 */
	List<Map<String, Object>> findReceiveList(int approveState);

}
 