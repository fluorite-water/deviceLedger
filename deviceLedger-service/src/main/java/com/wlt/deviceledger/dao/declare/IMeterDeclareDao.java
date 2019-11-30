package com.wlt.deviceledger.dao.declare;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wlt.deviceledger.bean.declare.MaterDeclareBean;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年10月26日 下午4:37:48 
* @Description 类说明 ：
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
public interface IMeterDeclareDao extends BaseMapper<MaterDeclareBean>{

	/**
	 * 部门管理原查询申报信息
	 * @param userId
	 * @return
	 */
	List<Map<String, Object>> findDeptApproveList(Integer userId);

	/**
	 * 设备管理员、公司主管查看
	 * @param i
	 * @return
	 */
	List<Map<String, Object>> findApproveList(int approveState);

	
}
 