package com.wlt.deviceledger.service.systemManager;

import java.util.List;

import com.wlt.deviceledger.bean.systemManager.RoleBean;
import com.wlt.deviceledger.bean.systemManager.RoleBean;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年10月23日 下午8:07:44 
* @Description 类说明 ：
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
public interface IRoleService {

	/**
	 * 查询部门树
	 * @return
	 */
	List<RoleBean> findList() throws Exception;

	/**
	 * 添加部门
	 * @param bean
	 * @return
	 */
	int addRole(RoleBean bean) throws Exception;

	/**
	 * 修改部门
	 * @param bean
	 * @return
	 */
	int updateRole(RoleBean bean);

	/**
	 * 删除部门
	 * @param bean
	 * @return
	 */
	int deleteRole(RoleBean bean);
}
 