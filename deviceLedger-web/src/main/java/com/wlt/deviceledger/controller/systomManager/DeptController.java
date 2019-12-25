package com.wlt.deviceledger.controller.systomManager;

import java.util.ArrayList;
import java.util.List;

import com.wlt.deviceledger.bean.systemManager.RoleBean;
import com.wlt.deviceledger.util.base.ExceptionConstantsUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wlt.deviceledger.bean.systemManager.DeptBean;
import com.wlt.deviceledger.controller.materialInfo.MaterialController;
import com.wlt.deviceledger.service.systemManager.IDeptService;
import com.wlt.deviceledger.util.base.ConstantUtils;
import com.wlt.deviceledger.util.base.ResultData;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年10月22日 下午9:01:07 
* @Description 类说明 ：
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
@Controller
@RequestMapping("/dept")
public class DeptController {

	private static final  Logger log = LogManager.getLogger(MaterialController.class);
	
	@Autowired
	private IDeptService servce;
	
	/**
	 * 查询部门树
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/findTree")
	@ResponseBody
	public ResultData findTree() {
		ResultData res = new ResultData();
		try {
			List<DeptBean> list = servce.findTree();
			res.setCode(ConstantUtils.SUCCESS_CODE);
			res.setSuccess(ConstantUtils.SUCCESS_MESSAGE);
			List<DeptBean> tree = toTree(list);
			res.setObj(tree);
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
	 * 添加部门
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/addDept")
	@ResponseBody
	public ResultData addDept(@RequestBody DeptBean bean) {
		ResultData res = new ResultData();
		try {
			int i  = servce.addDept(bean);
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
	 * 修改部门
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/updateDept")
	@ResponseBody
	public ResultData updateDept(@RequestBody DeptBean bean) {
		ResultData res = new ResultData();
		try {
			int i  = servce.updateDept(bean);
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
	 * 删除部门
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/deleteDept")
	@ResponseBody
	public ResultData deleteDept(@RequestBody DeptBean bean) {
		ResultData res = new ResultData();
		try {
			int i  = servce.deleteDept(bean);
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
	
	/**
     *转换树格式
    * @param list
    * @return
    */
   private static List<DeptBean> toTree(List<DeptBean> list) {
       List<DeptBean> treeList = new ArrayList<DeptBean>();
       for (DeptBean tree : list) {
           if("0".equals(tree.getPid()+"")){
               treeList.add(tree);
           }
       }
       for (DeptBean tree : list) {
           toTreeChildren(treeList,tree);
       }
       return treeList;
   }

   private static void toTreeChildren(List<DeptBean> treeList, DeptBean tree) {
       for (DeptBean node : treeList) {
           if(tree.getPid() == node.getId()){
               if(node.getChildren() == null){
                   node.setChildren(new ArrayList<DeptBean>());
               }
               node.getChildren().add(tree);
           }
           if(node.getChildren() != null){
               toTreeChildren(node.getChildren(),tree);
           }
       }
   }


    @RequestMapping("selDept")
    @ResponseBody
    public ResultData<DeptBean> deptList(@RequestBody DeptBean deptBean) {

        Integer pid = deptBean.getPid();

        if(pid == null) {
            return ExceptionConstantsUtils.printErrorMessage(log, "父节点不能为空");
        }

        List<DeptBean> list = servce.getRoleListByPid(pid);

        return ExceptionConstantsUtils.printSuccessMessage(log, "部门列表", list);
    }





}
 