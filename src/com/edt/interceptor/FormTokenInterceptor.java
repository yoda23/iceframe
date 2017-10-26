package com.edt.interceptor;

import com.edt.annotation.FormToken;
import com.iceutils.random.IceRandomUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 表单是否重复提交拦截器
 * 
 * @ClassName FormTokenInterceptor.java
 * @Description 表单是否重复提交拦截器
 * 
 * @author 刘钢
 * @version V1.0
 * @Date: 2017年4月17日 上午9:23:34
 */
public class FormTokenInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			FormToken annotation = method.getAnnotation(FormToken.class);
			if (annotation != null) {
				boolean needSaveSession = annotation.save();
				if (needSaveSession) {
					request.getSession().setAttribute("TOKEN",
							IceRandomUtils.getLongUUID());
				}
				boolean needRemoveSession = annotation.remove();
				if (needRemoveSession) {
					if (isRepeatSubmit(request)) {
						if (((HttpServletRequest) request)
								.getHeader("x-requested-with") != null
								&& ((HttpServletRequest) request)
										.getHeader("x-requested-with")
										// 如果是ajax请求响应头会有，x-requested-with
										.equalsIgnoreCase("XMLHttpRequest")) {
							((ServletResponse) response)
									.setContentType("text/html;charset=utf-8");
							String str = "{\"success\":false,\"message\":\"表单已经提交，请不要重复提交\"}";
							PrintWriter writer = ((ServletResponse) response)
									.getWriter();
							writer.write(str);
							writer.flush();
							writer.close();
						}
						return false;
					}
					// request.getSession(false).removeAttribute("TOKEN");
				}
			}
			return true;
		} else {
			return super.preHandle(request, response, handler);
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj, ModelAndView modelAndView)
			throws Exception {
	}

	private boolean isRepeatSubmit(HttpServletRequest request) {
		String serverToken = (String) request.getSession(false)
				.getAttribute("TOKEN");
		if (serverToken == null) {
			return true;
		}
		String clinetToken = request.getParameter("token");
		return clinetToken == null || !serverToken.equals(clinetToken);
	}
}
