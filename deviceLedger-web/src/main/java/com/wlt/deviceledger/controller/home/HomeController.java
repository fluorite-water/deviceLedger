package com.wlt.deviceledger.controller.home;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.wlt.deviceledger.bean.auth.Permission;
import com.wlt.deviceledger.bean.user.UserBean;
import com.wlt.deviceledger.controller.user.UserController;
import com.wlt.deviceledger.service.user.IUserService;
import com.wlt.deviceledger.util.base.ConstantUtils;
import com.wlt.deviceledger.util.base.ExceptionConstantsUtils;
import com.wlt.deviceledger.util.base.ResultData;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年10月20日 下午9:28:29 
* @Description 类说明 ：主页controller
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
@Controller
@RequestMapping("/home")
public class HomeController {
	
	private static final Logger log = LogManager.getLogger(UserController.class);

    @Autowired
    private IUserService userService;
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/manue")
	@ResponseBody
	public ResultData getMenue(HttpServletRequest request,HttpSession session) {
		ResultData res = new ResultData();
        try {
        	Object user =  session.getAttribute("user");
    		UserBean userBean = new UserBean();
    		if(user != null) {
    			userBean = (UserBean) user;
    		}
            List<Permission> list =  userService.findMenueList(userBean.getId());
            List<Permission> tree = toTree(list);
            res.setCode(ConstantUtils.SUCCESS_CODE);
            res.setMsg("菜单获取成功!");
            res.setObj(tree);
            res.setSuccess(ConstantUtils.SUCCESS_MESSAGE);
        } catch (Exception e) {
            return ExceptionConstantsUtils.printErrorMessage(log, e, e.getMessage());
        }
		return res;
	}
	
	/**
	 * 1.根据角色查资源
	 * @param roleId
	 * @return
	 */
	@PostMapping("/roleManue")
	@ResponseBody
	public ResultData<Object> roleManue(@RequestBody JSONObject jsonObject) {
        Integer roleId = jsonObject.getInteger("roleId");

		ResultData<Object> res = new ResultData<Object>();
        try {
            List<Permission> list =  userService.roleManue(roleId);
            List<Permission> tree = toTree(list);
            res.setCode(ConstantUtils.SUCCESS_CODE);
            res.setMsg("菜单获取成功!");
            res.setObj(tree);
            res.setSuccess(ConstantUtils.SUCCESS_MESSAGE);
        } catch (Exception e) {
            return ExceptionConstantsUtils.printErrorMessage(log, e, e.getMessage());
        }
		return res;
	}
	
	/**
	 * 2.查询所有资源
	 * @return
	 */
	@PostMapping("/manueAll")
	@ResponseBody
	public ResultData<Object> manueAll() {
		ResultData<Object> res = new ResultData<Object>();
        try {
            List<Permission> list =  userService.manueAll();
            List<Permission> tree = toTree(list);
            res.setCode(ConstantUtils.SUCCESS_CODE);
            res.setMsg("菜单获取成功!");
            res.setObj(tree);
            res.setSuccess(ConstantUtils.SUCCESS_MESSAGE);
        } catch (Exception e) {
            return ExceptionConstantsUtils.printErrorMessage(log, e, e.getMessage());
        }
		return res;
	}
	
	/**
      *转换树格式
     * @param list
     * @return
     */
    private static List<Permission> toTree(List<Permission> list) {
        List<Permission> treeList = new ArrayList<Permission>();
        for (Permission tree : list) {
            if("0".equals(tree.getPid())){
                treeList.add(tree);
            }
        }
        for (Permission tree : list) {
            toTreeChildren(treeList,tree);
        }
        return treeList;
    }
 
    private static void toTreeChildren(List<Permission> treeList, Permission tree) {
        for (Permission node : treeList) {
        	
            if(tree.getPid().equals(node.getId()+"")){
                if(node.getChildren() == null){
                    node.setChildren(new ArrayList<Permission>());
                }
                node.getChildren().add(tree);
            }
            if(node.getChildren() != null){
                toTreeChildren(node.getChildren(),tree);
            }
        }
    }

}
 