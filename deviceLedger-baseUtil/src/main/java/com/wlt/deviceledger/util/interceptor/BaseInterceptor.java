package com.wlt.deviceledger.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年9月21日 下午7:40:48 
* @Description 类说明 ：
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
@Component
public class BaseInterceptor extends HandlerInterceptorAdapter{
	@Override
    public boolean preHandle(HttpServletRequest request, 
    						HttpServletResponse response, Object handler) throws Exception {
		System.out.println("=================之前调用===================");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
    	 HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("=================之后调用===================");
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
    					 HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("=================请求完成===================");
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request,
    					 HttpServletResponse response, Object handler) throws Exception {
        System.out.println("=================异步调用===================");
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

}
 