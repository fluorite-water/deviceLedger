package com.wlt.deviceledger.service.materialInfo;

import java.util.List;
import java.util.Map;

import com.wlt.deviceledger.bean.materialInfo.MaterialInfoBean;
import com.wlt.deviceledger.util.base.ResultData;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年10月6日 下午5:30:52 
* @Description 类说明 ：
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
public interface IMaterialInfoService {

	/**
	 * 查询 材料信息list
	 * @param bean
	 * @return
	 */
	List<MaterialInfoBean> findList(MaterialInfoBean bean);

	
	/**
	 * 修改 材料信息 和 添加材料信息
	 * @param bean
	 * @return
	 */
	int editMater(MaterialInfoBean bean) throws Exception;


	/**
	 * 删除材料信息
	 * @param id
	 * @return
	 */
	Integer remove(String id) throws Exception;


	/**
	 * 根据材料id 查询材料信息bean
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	MaterialInfoBean findbean(MaterialInfoBean bean)throws Exception;


	/**
	 * 申报记录
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Map<String, Object>> declareList(Integer pageNum, Integer pageSize)throws Exception;


	/**
	 * 领取记录
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Map<String, Object>> receiveList(Integer pageNum, Integer pageSize)throws Exception;

}
 