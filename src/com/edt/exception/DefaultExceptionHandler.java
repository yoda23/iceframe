package com.edt.exception;

import com.edt.common.bean.ActionResult;
import com.edt.common.constant.CommonConstant;
import com.iceutils.json.IceJsonStringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DefaultExceptionHandler implements HandlerExceptionResolver {
	@Resource
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		ex.printStackTrace();
		// 数据库外键删除异常
		if (ex instanceof DataIntegrityViolationException) {
			return processException(request, response, "操作失败，违反数据库约束");
		}
		return processException(request, response, CommonConstant.CONFIG_ERRORMSG);
	}

	private ModelAndView processException(HttpServletRequest request,
			HttpServletResponse response, String msg) {
		ActionResult actionResult = new ActionResult();
		// 不是ajax请求，直接返回错误页面
		if (!(request.getHeader("accept").contains("application/json")
				|| (request.getHeader("X-Requested-With") != null
						&& request.getHeader("X-Requested-With")
								.contains("XMLHttpRequest")))) {
			return new ModelAndView("error");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			actionResult.setSuccess(false);
			actionResult.setMessage(msg);
			PrintWriter writer;
			try {
				writer = response.getWriter();
				writer.write(IceJsonStringUtils.toJsonString(actionResult));
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new ModelAndView("error");
	}
}
