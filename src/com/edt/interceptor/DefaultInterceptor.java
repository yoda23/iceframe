package com.edt.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class DefaultInterceptor implements HandlerInterceptor {
	@Resource
	private HttpSession httpSession;
	@Resource
	private HttpServletResponse httpServletResponse;
	@Resource
	private HttpServletRequest httpServletRequest;

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception exception)
			throws Exception {
		//System.err.println("默认拦截器afterCompletion方法开始工作");
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj, ModelAndView modelAndView)
			throws Exception {
		//System.err.println("默认拦截器postHandle方法开始工作");
	}

	/*
	 * 用户登录session拦截器
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj) throws Exception {
		//System.err.println("默认拦截器preHandle方法开始工作");
		return true;
	}
}
