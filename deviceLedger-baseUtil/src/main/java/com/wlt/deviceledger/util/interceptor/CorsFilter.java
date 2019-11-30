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

		String id = ((HttpServletRequest) req).getSession().getId();
		System.out.println("--------------->" + id);

		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");//允许跨域的请求方式
		response.setHeader("Access-Control-Max-Age", "3600");//预检请求的间隔时间
		response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token,Access-Control-Allow-Headers,Set-Cookie");//允许跨域请求携带的请求头
		response.setHeader("Access-Control-Allow-Credentials","true");//若要返回cookie、携带seesion等信息则将此项设置我true
		//放行
		chain.doFilter(request, response);
	}
	@Override
	public void destroy() {
	}
}
 