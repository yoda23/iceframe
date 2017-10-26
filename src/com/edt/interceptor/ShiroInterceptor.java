package com.edt.interceptor;

import com.edt.entity.User;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class ShiroInterceptor extends AuthorizationFilter {

	@Resource
	private HttpServletResponse httpServletResponse;
	@Resource
	private HttpServletRequest httpServletRequest;

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object obj) throws Exception {
		User user = (User) getSubject(request, response).getSession()
				.getAttribute("USER");
		if (user == null) {
			if (((HttpServletRequest) request).getHeader("x-requested-with") != null
					&& ((HttpServletRequest) request).getHeader(
							"x-requested-with").equalsIgnoreCase(
							"XMLHttpRequest")) { // 如果是ajax请求响应头会有，x-requested-with
				((HttpServletResponse) response).addHeader("sessionstatus",
						"timeout");// 在响应头设置session状态
				((ServletResponse) response)
						.setContentType("text/html;charset=utf-8");
				String str = "{\"success\":false,\"message\":\"用户登陆超时,请重新登录\"}";
				PrintWriter writer = ((ServletResponse) response).getWriter();
				writer.write(str);
				writer.flush();
				writer.close();
			} else {
				((HttpServletResponse) response)
						.sendRedirect(((HttpServletRequest) request)
								.getContextPath() + "/timeout");
			}
			return false;
		}
		return true;
	}

}
