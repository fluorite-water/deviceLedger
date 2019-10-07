package com.wlt.deviceledger.service.materialInfo;

import java.util.List;

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

}
 