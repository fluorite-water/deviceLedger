package com.wlt.deviceledger.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wlt.deviceledger.bean.systemManager.RoleBean;
import com.wlt.deviceledger.bean.user.UserBean;
import com.wlt.deviceledger.service.user.IUserService;
import com.wlt.deviceledger.util.base.ConstantUtils;
import com.wlt.deviceledger.util.base.ExceptionConstantsUtils;
import com.wlt.deviceledger.util.base.ResultData;
import com.wlt.deviceledger.util.common.VerifyCodeUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: UserController
 * @Date: 2019/10/13 15:09
 * @Author: 杨开怀
 */
@Controller
@RequestMapping("/user")
public class UserController {


    private static final Logger log = LogManager.getLogger(UserController.class);

    @Autowired
    private IUserService userService;


    /**
     * 登录
     * @param userBean
     * @return
     */
    @SuppressWarnings("unchecked")
	@PostMapping("/login")
    @ResponseBody
    public ResultData<Map<String, Object>> login(@RequestBody UserBean userBean, HttpServletRequest request) {
        Map<String, Object> resultMap = null;
        //获取cookie中的验证码
        String sessionId = request.getSession().getId();

        try {
            resultMap = userService.login(userBean, sessionId);


            if(resultMap == null) {
                return ExceptionConstantsUtils.printErrorMessage(log, "获取token失败");
            }

            //删除返回的用户对象，并将其放入到sessioin中
            Object user = resultMap.get("user");
            request.getSession().setAttribute("user", user);
            resultMap.remove("user");

        } catch (Exception e) {
            return ExceptionConstantsUtils.printErrorMessage(log, e, e.getMessage());
        }

        return ExceptionConstantsUtils.printSuccessMessage(log, "获取token成功" , resultMap);
    }



    /**
     * 查看用户信息
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
	@PostMapping("/info")
    @ResponseBody
    public ResultData<Map<String, Object>> getInfo(HttpServletRequest request) {

        Map<String, Object> resultMap = null;


        String token = request.getHeader("token");

        try {
            UserBean tokenUserBean = userService.getUserToken(token);

            if(tokenUserBean == null) {
                return ExceptionConstantsUtils.printErrorMessage(log, "token无效");
            }
            RoleBean roleBean = userService.getRoleByRoleCode(String.valueOf(tokenUserBean.getRoleId()));
            List<RoleBean> roleBeanList = new ArrayList<>();
            roleBeanList.add(roleBean);

            resultMap = new HashMap<>();
            resultMap.put("user", tokenUserBean);
            resultMap.put("role", roleBeanList);

        } catch (Exception e) {
            return ExceptionConstantsUtils.printErrorMessage(log, e, e.getMessage());
        }



        return ExceptionConstantsUtils.printSuccessMessage(log, "登录成功", resultMap);
    }


    @RequestMapping("/detail")
    @ResponseBody
    public ResultData getInfo(@RequestBody UserBean userBean) {

        UserBean whereUser = new UserBean();
        whereUser.setId(userBean.getId());
        UserBean returnUserBean = userService.getUser(whereUser);
        return ExceptionConstantsUtils.printSuccessMessage(log, "获取用户信息", returnUserBean);
    }

    /**
     * 注册用户
     * @param userBean
     * @return
     */
    @SuppressWarnings("unchecked")
	@PostMapping("/regis")
    @ResponseBody
    public ResultData<Map<String, Object>> regis(@RequestBody UserBean userBean) {


        String loginAct = userBean.getLoginAct();
        String loginPwd = userBean.getLoginPwd();
        String email = userBean.getEmail();
        String roleCode = userBean.getRoleId()+"";
        String deptCode = userBean.getDeptId();

        if(loginAct == null || loginAct.equals("")) {
            return ExceptionConstantsUtils.printErrorMessage(log, "请填写账号");
        }

        if(loginPwd == null || loginPwd.equals("")) {
            return ExceptionConstantsUtils.printErrorMessage(log, "请填写密码");
        }

        if(email == null || email.equals("")) {
            return ExceptionConstantsUtils.printErrorMessage(log, "请填写邮箱");
        }

        if(roleCode == null || "".equals(roleCode)) {
            return ExceptionConstantsUtils.printErrorMessage(log, "请选择角色");
        }

        if(deptCode == null || "".equals(deptCode)) {
            return ExceptionConstantsUtils.printErrorMessage(log, "请选择部门");
        }

        try {

            userService.regis(userBean);

        } catch (Exception e) {
            return ExceptionConstantsUtils.printErrorMessage(log, e, e.getMessage());
        }


        return ExceptionConstantsUtils.printSuccessMessage(log, "注册成功");
    }



    @RequestMapping("/kaptcha")
    public void kaptcha(HttpServletResponse response, HttpServletRequest request) throws IOException {

        ServletOutputStream outputStream = response.getOutputStream();

        String code = VerifyCodeUtils.generateVerifyCode(4);

        Cookie cookie = new Cookie("kaptcha", code);
        response.addCookie(cookie);
        cookie.setMaxAge(60);
        cookie.setSecure(false);
        response.setHeader("kaptcha", code);
        //response.getWriter().write(jsonObject.toJSONString());
        VerifyCodeUtils.outputVerifyImage(100, 30, outputStream, code);

    }

    /**
     *  添加用户
     * @param bean
     * @return
     */
    @RequestMapping(value="/addUser",method=RequestMethod.POST)
    @ResponseBody
    public ResultData<Object> addUser(UserBean bean){
    	ResultData<Object> res = new ResultData<Object>();
    	try {
    		res = userService.addUser(bean);
		} catch (Exception e) {
			log.info("用户添加异常"+e);
			res.setCode(ConstantUtils.ERROR_CODE);
			res.setMsg("用户添加异常，请检查网络");
			res.setSuccess(false);
			e.printStackTrace();
		}
		return res;
    	
    }
    /**
     *  修改用户信息
     * @param bean
     * @return
     */
    @RequestMapping(value="/updateUser",method=RequestMethod.POST)
    @ResponseBody
    public ResultData<Object> updateUser(@RequestBody UserBean bean){
    	ResultData<Object> res = new ResultData<Object>();
    	try {
    		res = userService.updateUser(bean);
    	} catch (Exception e) {
    		log.info("修改用户信息异常"+e);
    		res.setCode(ConstantUtils.ERROR_CODE);
    		res.setMsg("修改用户信息异常，请检查网络");
    		res.setSuccess(false);
    		e.printStackTrace();
    	}
    	return res;
    	
    }

    @PostMapping(value = "/list")
    @ResponseBody
    public ResultData getUserList(HttpSession session, @RequestBody UserBean
                                                  userBean) {
        IPage<Map<String, Object>> resultData = null;
        try {
            resultData = userService.getUserList(userBean);
        } catch (Exception e) {
            return ExceptionConstantsUtils.printErrorMessage(log, e ,"获取用户列表异常");
        }
        return ExceptionConstantsUtils.printSuccessMessage(log, "获取用户列表成功" , resultData);
    }


    @PostMapping("/del")
    @ResponseBody
    public ResultData delUser(@RequestBody UserBean userBean) {

        ResultData resultData = null;

        try {

            userService.delUser(userBean.getId());

        } catch (Exception e) {
            return ExceptionConstantsUtils.printErrorMessage(log, e, "删除用户失败");
        }


        return ExceptionConstantsUtils.printSuccessMessage(log, "删除成功");
    }



    @PostMapping("/all")
    @ResponseBody
    public ResultData all(@RequestBody UserBean userBean) {

        ResultData resultData = null;

        try {

            List<UserBean> list = userService.allUser();

        } catch (Exception e) {
            return ExceptionConstantsUtils.printErrorMessage(log, e, "删除用户失败");
        }


        return ExceptionConstantsUtils.printSuccessMessage(log, "删除成功");
    }


}
