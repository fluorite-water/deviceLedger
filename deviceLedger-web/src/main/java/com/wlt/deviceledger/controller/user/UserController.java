package com.wlt.deviceledger.controller.user;

import com.wlt.deviceledger.bean.user.UserBean;
import com.wlt.deviceledger.service.user.IUserService;
import com.wlt.deviceledger.util.base.ExceptionConstantsUtils;
import com.wlt.deviceledger.util.base.ResultData;
import com.wlt.deviceledger.util.common.VerifyCodeUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
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
    public ResultData<Map<String, Object>> login(UserBean userBean, HttpServletRequest request) {
        Map<String, Object> resultMap = null;
        //获取cookie中的验证码
        String reqKaptcha = null;
        try {

            Cookie[] cookies = request.getCookies();

            for(Cookie cookie : cookies) {
                String name = cookie.getName();
                if("KAPTCHA".equalsIgnoreCase(name)) {
                    reqKaptcha = cookie.getValue();
                }
            }
            if(reqKaptcha == null || "".equals(reqKaptcha)) {
                return ExceptionConstantsUtils.printErrorMessage(log, "验证码过期");
            }

        } catch (Exception e) {
            return ExceptionConstantsUtils.printErrorMessage(log, e,"验证码过期");
        }

        //验证验证码
        String kaptcha = userBean.getKaptcha().toUpperCase();

        if(kaptcha == null || "".equals(kaptcha)) {
            return ExceptionConstantsUtils.printErrorMessage(log, "请填写验证码");
        }

        if(!kaptcha.equalsIgnoreCase(reqKaptcha)) {
            return ExceptionConstantsUtils.printErrorMessage(log, "验证码错误");
        }

        try {
            resultMap = userService.login(userBean);

            if(resultMap == null) {
                return ExceptionConstantsUtils.printErrorMessage(log, "获取token失败");
            }

        } catch (Exception e) {
            return ExceptionConstantsUtils.printErrorMessage(log, e, e.getMessage());
        }



        return ExceptionConstantsUtils.printSuccessMessage(log, "获取token成功" , resultMap);
    }



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

            resultMap = new HashMap<>();
            resultMap.put("user", tokenUserBean);

        } catch (Exception e) {
            return ExceptionConstantsUtils.printErrorMessage(log, e, e.getMessage());
        }



        return ExceptionConstantsUtils.printSuccessMessage(log, "登录成功", resultMap);
    }


    @SuppressWarnings("unchecked")
	@PostMapping("/regis")
    @ResponseBody
    public ResultData<Map<String, Object>> regis(UserBean userBean) {


        String loginAct = userBean.getLoginAct();
        String loginPwd = userBean.getLoginPwd();
        String email = userBean.getEmail();


        if(loginAct == null || loginAct.equals("")) {
            return ExceptionConstantsUtils.printErrorMessage(log, "请填写账号");
        }

        if(loginPwd == null || loginPwd.equals("")) {
            return ExceptionConstantsUtils.printErrorMessage(log, "请填写密码");
        }

        if(email == null || email.equals("")) {
            return ExceptionConstantsUtils.printErrorMessage(log, "请填写邮箱");
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
        response.setHeader("kaptcha", code);
        response.setContentType("image/jpeg");

        VerifyCodeUtils.outputVerifyImage(100, 30, outputStream, code);

    }


}
