package com.wlt.deviceledger.controller.materialInfo;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.wlt.deviceledger.bean.materialInfo.MaterialInfoBean;
import com.wlt.deviceledger.service.materialInfo.IMaterialInfoService;
import com.wlt.deviceledger.util.base.ResultData;
import com.wlt.deviceledger.util.common.MyConstents;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年10月6日 下午5:27:26 
* @Description 类说明 ：材料信息
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
@Controller
@RequestMapping("/MaterialController")
public class MaterialController {

	@Autowired
	private IMaterialInfoService service;
	
	private static final  Logger log = LogManager.getLogger(MaterialController.class);
	/**
	 * 查询 材料信息list
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/findList",method=RequestMethod.GET)
	@ResponseBody
	public  PageInfo<MaterialInfoBean> findList(MaterialInfoBean bean) {
		ResultData result = new ResultData();
		List<MaterialInfoBean> list = service.findList(bean);
		PageInfo<MaterialInfoBean> info = new PageInfo<MaterialInfoBean>(list);
		result.setCode("200");
		result.setObj(list);
		return info;
	}
	
	/**
	 * 添加修改材料信息
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/edit",method=RequestMethod.POST)
	@ResponseBody
	public ResultData editMater(MaterialInfoBean bean) {
		
		ResultData resultData = new ResultData();
		//如果id为空
		String id = bean.getId();
		
		try {
			service.editMater(bean);
			//添加
			if(id == null) {				
				resultData.setMsg("添加成功");
				//修改
			} else {
				resultData.setMsg("修改成功");
			}
			resultData.setSuccess(true);
			resultData.setCode(MyConstents.SUCCESS);
		} catch(Exception e) {
			resultData.setCode(MyConstents.ERROR);
			resultData.setSuccess(false);
			if(id == null) {				
				//修改
				resultData.setMsg("添加失败");
			} else {
				resultData.setMsg("修改失败");
			}
			log.info(ExceptionUtils.getStackTrace(e));
		}
		
		return resultData;
	}
	
	
	/**
	 * 删除材料信息
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/del",method=RequestMethod.POST)
	@ResponseBody
	public ResultData delMater(String id) {
		
		ResultData resultData = new ResultData();
	
		try {
			Integer remove = service.remove(id);
			resultData.setSuccess(true);
			resultData.setCode(MyConstents.SUCCESS);
			resultData.setMsg("删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			resultData.setCode(MyConstents.ERROR);
			resultData.setSuccess(false);
			resultData.setMsg("删除失败");
			log.info(ExceptionUtils.getStackTrace(e));
		}
	
		return resultData;
	}
	
	
}
 