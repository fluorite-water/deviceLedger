package com.wlt.deviceledger.controller.purchase;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.wlt.deviceledger.bean.declare.MaterDeclareBean;
import com.wlt.deviceledger.bean.receive.MaterReceiveBean;
import com.wlt.deviceledger.service.declare.IMeterDeclareService;
import com.wlt.deviceledger.service.purchas.IPurchasManageService;
import com.wlt.deviceledger.service.receive.IReceiveService;
import com.wlt.deviceledger.util.base.ConstantUtils;
import com.wlt.deviceledger.util.base.ResultData;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年11月24日 下午12:39:02 
* @Description 类说明 ：采购管理  出库，入库操作
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
@Controller
@RequestMapping("/purchaseManage")
public class PurchaseManageController {

	private static final Logger log = LogManager.getLogger(PurchaseManageController.class);
	
	@Autowired
	private IPurchasManageService service;
	
	@Autowired
	private IMeterDeclareService meterDeclareService;
	
	@Autowired
	private IReceiveService receiveService;
	
	/************************* 入库 ************************/
	
	/**
	 * 查询采购信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/findList",method=RequestMethod.POST)
	@ResponseBody
	public ResultData<Object> findDeptApproveList(
			@RequestParam(defaultValue="1")Integer pageNum,
			@RequestParam(defaultValue="20")Integer pageSize){
		ResultData<Object> res = null;
		try {
			res = service.findList(pageNum,pageSize);
			res.setCode(ConstantUtils.SUCCESS_CODE);
    		res.setMsg("查询成功");
    		res.setSuccess(ConstantUtils.SUCCESS_MESSAGE);
		} catch (Exception e) {
			log.info("查询异常"+e);
    		res.setCode(ConstantUtils.ERROR_CODE);
    		res.setMsg("查询异常，请检查网络");
    		res.setSuccess(ConstantUtils.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return res;
	}
	
	
	/**
	 * 采购完成
	 */
	@RequestMapping(value = "/purchaseFinish",method=RequestMethod.POST)
	@ResponseBody
	public ResultData<Object> purchaseFinish(@RequestBody MaterDeclareBean bean){
		ResultData<Object> res = null;
		try {
			res = meterDeclareService.purchaseFinish(bean);
		} catch (Exception e) {
			log.info("采购异常"+e);
    		res.setCode(ConstantUtils.ERROR_CODE);
    		res.setMsg("采购异常，请检查网络");
    		res.setSuccess(ConstantUtils.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return res;
	}
	
	/************************* 出库 ************************/
	/**
	 * 查询领料信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/findReceiveList",method=RequestMethod.POST)
	@ResponseBody
	public ResultData<Object> findReceiveList(
			@RequestParam(defaultValue="1")Integer pageNum,
			@RequestParam(defaultValue="20")Integer pageSize){
		ResultData<Object> res = null;
		try {
			res = service.findReceiveList(pageNum,pageSize);
			res.setCode(ConstantUtils.SUCCESS_CODE);
    		res.setMsg("查询成功");
    		res.setSuccess(ConstantUtils.SUCCESS_MESSAGE);
		} catch (Exception e) {
			log.info("查询异常"+e);
    		res.setCode(ConstantUtils.ERROR_CODE);
    		res.setMsg("查询异常，请检查网络");
    		res.setSuccess(ConstantUtils.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * 领料完成
	 */
	@RequestMapping(value = "/receiveFinish",method=RequestMethod.POST)
	@ResponseBody
	public ResultData<Object> receiveFinish(MaterReceiveBean bean){
		ResultData<Object> res = null;
		try {
			res = receiveService.receiveFinish(bean);
		} catch (Exception e) {
			log.info("出库异常"+e);
    		res.setCode(ConstantUtils.ERROR_CODE);
    		res.setMsg("出库异常，请检查网络");
    		res.setSuccess(ConstantUtils.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return res;
	}
}
 