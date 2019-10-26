package com.wlt.deviceledger.service.systemManager;

import java.util.List;

import com.wlt.deviceledger.bean.systemManager.DeptBean;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年10月22日 下午9:09:11 
* @Description 类说明 ：
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
public interface IDeptService {

	/**
	 * 查询部门树
	 * @return
	 */
	List<DeptBean> findTree() throws Exception;

	/**
	 * 添加部门
	 * @param bean
	 * @return
	 */
	int addDept(DeptBean bean) throws Exception;

	/**
	 * 修改部门
	 * @param bean
	 * @return
	 */
	int updateDept(DeptBean bean);

	/**
	 * 删除部门
	 * @param bean
	 * @return
	 */
	int deleteDept(DeptBean bean);

}
 