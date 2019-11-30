package com.wlt.deviceledger.settings.shrio;

import com.alibaba.fastjson.JSONObject;
import com.shrio.JWTToken;
import com.wlt.deviceledger.bean.auth.Permission;
import com.wlt.deviceledger.bean.user.UserBean;
import com.wlt.deviceledger.util.base.ExceptionConstantsUtils;
import com.wlt.deviceledger.util.base.ResultData;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

public class RestAuthorizationFilter extends FormAuthenticationFilter {

    private static final Logger log = LogManager.getLogger(RestAuthorizationFilter.class);

    /**
     * 如果带有 token，则对 token 进行检查，否则直接通过
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws UnauthorizedException {
        //判断请求的请求头是否带上 "token"
        if (isLoginAttempt(request, response)) {
            //如果存在，则进入 executeLogin 方法执行登入，检查 token 是否正确
            try {
                executeLogin(request, response);
                return true;
            } catch (Exception e) {
                //token 错误
                responseError(response, e.getMessage());
            }
        } else {
            responseError(response, null);
        }
        //如果请求头不存在 Token，则可能是执行登陆操作或者是游客状态访问，无需检查 token，直接返回 true
        return true;
    }

    /**
     * 判断用户是否想要登入。
     * 检测 header 里面是否包含 Token 字段
     */
    private boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("token");
        return token != null;
    }

    /**
     * 执行登陆操作
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("token");
        JWTToken jwtToken = new JWTToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(jwtToken);
        // 如果没有抛出异常则代表登入成功，返回true

        String requestURI = req.getServletPath();

        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        Subject subject = getSubject(request, response);

        // 如果没有登录，就跳转到登录页面
        if (!subject.isAuthenticated()) {
            //WebUtils.issueRedirect(request, response , "/login");
            ResultData resultData = ExceptionConstantsUtils.printErrorMessage(log, "请先登录");
            try {
                response.getWriter().println(JSONObject.toJSON(resultData).toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        UserBean user = (UserBean) subject.getSession().getAttribute("user");

        List<Permission> permissionList = user.getPermissionList();

        if (!permissionList.contains(requestURI)) {
            return true;
        } else {
            UnauthorizedException ex = new UnauthorizedException("当前用户没有访问路径 " + requestURI + " 的权限");

            subject.getSession().setAttribute("ex", ex);

            ResultData resultData = ExceptionConstantsUtils.printErrorMessage(log, "40000", "当前用户没有访问路径 " + requestURI + " 的权限");

            try {
                response.getWriter().println(JSONObject.toJSON(resultData).toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;

        }
    }

    /**
     * 将非法请求跳转到 /unauthorized/**
     */
    private void responseError(ServletResponse response, String message) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            //设置编码，否则中文字符在重定向时会变为空字符串
            message = URLEncoder.encode(message, "UTF-8");
            //httpServletResponse.sendRedirect("/unauthorized/" + message);

            ResultData resultData = ExceptionConstantsUtils.printErrorMessage(log, "请重新登录");
            httpServletResponse.getWriter().write(JSONObject.toJSONString(resultData));

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public boolean onPreHandle(ServletRequest request,ServletResponse response, Object mappedValue) {
        HttpServletResponse res = (HttpServletResponse) response;

        res.setContentType("application/json;charset=utf-8");

        //判断请求的请求头是否带上 "token"
        if (isLoginAttempt(request, res)) {
            //如果存在，则进入 executeLogin 方法执行登入，检查 token 是否正确
            try {
                executeLogin(request, res);
                return true;
            } catch (Exception e) {
                //token 错误
                responseError(res, e.getMessage());
            }
        } else {
            responseError(res, null);
        }
        //如果请求头不存在 Token，则可能是执行登陆操作或者是游客状态访问，无需检查 token，直接返回 true
        return true;
    }
}

