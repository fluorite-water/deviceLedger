package com.wlt.deviceledger.dao.materialInfo;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wlt.deviceledger.bean.declare.MaterDeclareBean;
import com.wlt.deviceledger.bean.materialInfo.MaterialInfoBean;
import com.wlt.deviceledger.bean.receive.MaterReceiveBean;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年10月6日 下午5:28:56 
* @Description 类说明 ：
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
public interface IMaterialInfoDao extends BaseMapper<MaterialInfoBean>{

	/**
	 * 采购成功后，修改材料库中材料的数量
	 * @param bean
	 * @return
	 */
	int updateMeterNum(MaterDeclareBean bean);

	/**
	 * 领取成功后修改 材料库中的数量
	 * @param bean
	 * @return
	 */
	int updateMeterReceiveNum(MaterReceiveBean bean);

	/**
	 * 普通用户 申报记录查询
	 * @param userId
	 * @return
	 */
	List<Map<String, Object>> declareList(int userId);

	/**
	 * 部门管理员 申报记录查询
	 * @param i
	 * @return
	 */
	List<Map<String, Object>> deptDeclareList(int i);

	/**
	 * 设备管理员、公司主管  申报记录查询
	 * @return
	 */
	List<Map<String, Object>> DeviceDeclareList();
	/**
	 * 普通用户 领取记录查询
	 * @param userId
	 * @return
	 */
	List<Map<String, Object>> receiveList(int userId);
	
	/**
	 * 部门管理员 领取记录查询
	 * @param i
	 * @return
	 */
	List<Map<String, Object>> deptReceiveList(int i);
	
	/**
	 * 设备管理员、公司主管  领取记录查询
	 * @return
	 */
	List<Map<String, Object>> DeviceReceiveList();


}
 