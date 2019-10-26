package com.wlt.deviceledger.controller.systomManager;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wlt.deviceledger.bean.systemManager.RoleBean;
import com.wlt.deviceledger.controller.materialInfo.MaterialController;
import com.wlt.deviceledger.service.systemManager.IRoleService;
import com.wlt.deviceledger.util.base.ConstantUtils;
import com.wlt.deviceledger.util.base.ResultData;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年10月22日 下午9:01:30 
* @Description 类说明 ：
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
@Controller
@RequestMapping("role")
public class RoleController {

private static final  Logger log = LogManager.getLogger(MaterialController.class);
	
	@Autowired
	private IRoleService servce;
	/**
	 * 查询角色
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/findList")
	@ResponseBody
	public ResultData findTree() {
		ResultData res = new ResultData();
		try {
			List<RoleBean> list = servce.findList();
			res.setCode(ConstantUtils.SUCCESS_CODE);
			res.setSuccess(ConstantUtils.SUCCESS_MESSAGE);
			res.setObj(list);
			res.setMsg("查询成功");
		} catch (Exception e) {
			log.info("查询失败");
			res.setCode(ConstantUtils.ERROR_CODE);
			res.setSuccess(ConstantUtils.ERROR_MESSAGE);
			res.setMsg("查询失败");
			e.printStackTrace();
		}
		return res;
		
	}
	/**
	 * 添加角色
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/addRole")
	@ResponseBody
	public ResultData addRole(RoleBean bean) {
		ResultData res = new ResultData();
		try {
			int i  = servce.addRole(bean);
			if(i > 0) {
				res.setCode(ConstantUtils.SUCCESS_CODE);
				res.setSuccess(ConstantUtils.SUCCESS_MESSAGE);
				res.setMsg("添加成功");
			}else {
				res.setMsg("添加失败");
				res.setCode(ConstantUtils.ERROR_CODE);
				res.setSuccess(ConstantUtils.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			log.info("添加失败");
			res.setMsg("添加失败");
			res.setCode(ConstantUtils.ERROR_CODE);
			res.setSuccess(ConstantUtils.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return res;
		
	}
	/**
	 * 修改角色
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/updateRole")
	@ResponseBody
	public ResultData updateRole(RoleBean bean) {
		ResultData res = new ResultData();
		try {
			int i  = servce.updateRole(bean);
			if(i > 0) {
				res.setCode(ConstantUtils.SUCCESS_CODE);
				res.setSuccess(ConstantUtils.SUCCESS_MESSAGE);
				res.setMsg("修改成功");
			}else {
				res.setMsg("修改失败");
				res.setCode(ConstantUtils.ERROR_CODE);
				res.setSuccess(ConstantUtils.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			log.info("修改失败");
			res.setMsg("修改失败");
			res.setCode(ConstantUtils.ERROR_CODE);
			res.setSuccess(ConstantUtils.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return res;
		
	}
	/**
	 * 删除角色
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/deleteRole")
	@ResponseBody
	public ResultData deleteRole(RoleBean bean) {
		ResultData res = new ResultData();
		try {
			int i  = servce.deleteRole(bean);
			if(i > 0) {
				res.setCode(ConstantUtils.SUCCESS_CODE);
				res.setSuccess(ConstantUtils.SUCCESS_MESSAGE);
				res.setMsg("删除成功");
			}else {
				res.setMsg("删除失败");
				res.setCode(ConstantUtils.ERROR_CODE);
				res.setSuccess(ConstantUtils.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			log.info("删除失败");
			res.setMsg("删除失败");
			res.setCode(ConstantUtils.ERROR_CODE);
			res.setSuccess(ConstantUtils.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return res;
		
	}
}
 