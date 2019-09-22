package com.wlt.deviceledger.util.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年2月23日 下午9:20:11 
* @Description 类说明 ：解决跨域问题
* @version 版本：1.0
* @since JDK 1.8.0_181
*/

public class CorsFilter implements Filter{
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		//设置请求编码集
		request.setCharacterEncoding("utf-8");
		//设置响应编码集
		response.setContentType("text/html;charset=utf-8");
		//设置跨域
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("P3P", "CP=CAO PSA OUR");
		if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
			response.addHeader("Access-Control-Allow-Methods", "POST,GET,TRACE,OPTIONS");
			response.addHeader("Access-Control-Allow-Headers", "Content-Type,Origin,Accept");
			response.addHeader("Access-Control-Max-Age", "120");
		}
		//放行
		chain.doFilter(request, response);
	}
	@Override
	public void destroy() {
	}
}
 