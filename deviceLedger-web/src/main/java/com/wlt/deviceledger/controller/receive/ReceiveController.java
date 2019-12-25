package com.wlt.deviceledger.controller.receive;

import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wlt.deviceledger.bean.receive.ApproveReceiveRecordBean;
import com.wlt.deviceledger.bean.receive.MaterReceiveBean;
import com.wlt.deviceledger.bean.user.UserBean;
import com.wlt.deviceledger.service.receive.IReceiveService;
import com.wlt.deviceledger.util.base.ConstantUtils;
import com.wlt.deviceledger.util.base.ResultData;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年11月24日 下午3:36:54 
* @Description 类说明 ：领料controller
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
@Controller
@RequestMapping("/receive")
public class ReceiveController {

	private static final Logger log = LogManager.getLogger(ReceiveController.class);
	
	@Autowired
	private IReceiveService service;
	

	/**
	 * 领取材料
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/addReceive",method=RequestMethod.POST)
	@ResponseBody
	public ResultData<Object> addReceive(MaterReceiveBean bean,HttpSession session){
		ResultData<Object> res = null;
		try {
			res = service.addReceive(bean,session);
		} catch (Exception e) {
			log.info("领取材料异常"+e);
    		res.setCode(ConstantUtils.ERROR_CODE);
    		res.setMsg("领取材料异常，请检查网络");
    		res.setSuccess(ConstantUtils.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return res;
		
	}
	/**
	 * 本部门管理员、设备管理员、公司总管查看 材料领取 申报信息list
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/findReceiveList",method=RequestMethod.POST)
	@ResponseBody
	public ResultData<Object> findReceiveList(
			@RequestParam(defaultValue="1")Integer pageNum,
			@RequestParam(defaultValue="20")Integer pageSize,HttpSession session){
		ResultData<Object> res = null;
		try {
			res = service.findReceiveList(pageNum,pageSize,session);
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
	 * 本部门审批，设备管理员、公司主管 领料审批
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/receiveApprove",method=RequestMethod.POST)
	@ResponseBody
	public ResultData<Object> receiveApprove(ApproveReceiveRecordBean bean,HttpSession session){
		ResultData<Object> res = null;
		try {
			
			// 根据roleId,判断审批到了哪一层
			Object user =  session.getAttribute("user");
			UserBean userBean = new UserBean();
			Integer roleId =0;
			if(user != null) {
				userBean = (UserBean) user;
				// 根据roleId,判断审批到了哪一层
				roleId = userBean.getRoleId();
			}
			Integer approvalState=0;
			if(roleId==2) {
				approvalState=ConstantUtils.APPROVAL_STATE1; // 本部门管理员
			}else if(roleId==3){
				approvalState=ConstantUtils.APPROVAL_STATE2; // 设备管理员
			}else if(roleId==4) {
				approvalState=ConstantUtils.APPROVAL_STATE4; // 公司主管,通过后直接下发采购
			}else {
				return res;
			}
			
			res = service.receiveApprove(bean,Integer.parseInt(userBean.getId()));
			//审批通过后需修改审批状态
			// 如果审批未通过
			if(bean.getApproveState()==0) {
				approvalState=ConstantUtils.APPROVAL_STATE9; // 审批已拒绝
			}
			service.updateApproveState(bean.getReceiveId(),approvalState);
		} catch (Exception e) {
			log.info("审批异常"+e);
    		res.setCode(ConstantUtils.ERROR_CODE);
    		res.setMsg("审批异常，请检查网络");
    		res.setSuccess(ConstantUtils.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return res;
	}
}
 