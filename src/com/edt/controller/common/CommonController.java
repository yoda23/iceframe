package com.edt.controller.common;

import com.baidu.ueditor.ActionEnter;
import com.edt.common.BaseController;
import com.edt.common.InitDB;
import com.edt.common.constant.CommonConstant;
import com.edt.common.bean.ActionResult;
import com.iceutils.string.IceStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/")
public class CommonController extends BaseController {
	@Resource
	private HttpSession httpSession;
	@Resource
	private HttpServletResponse httpServletResponse;
	@Resource
	private HttpServletRequest httpServletRequest;
	@Resource
	private InitDB initDB;

	/**
	 * 页面跳转公共controller
	 *
	 * @author 刘钢 2017年4月12日 下午2:08:26
	 */
	@RequestMapping("redirect")
	public String redirect() {
		return httpServletRequest.getParameter("page");
	}

	/**
	 * session超时处理页
	 *
	 * @author 刘钢 2017年4月12日 下午2:09:55
	 */
	@RequestMapping("timeout")
	public String timeout() {
		try {
			if (httpServletRequest.getHeader("x-requested-with") != null
					&& httpServletRequest.getHeader("x-requested-with")
							.equalsIgnoreCase("XMLHttpRequest")) { // 如果是ajax请求响应头会有，x-requested-with
				httpServletResponse.setHeader("sessionstatus", "timeout");// 在响应头设置session状态
				httpServletResponse.setContentType("text/html;charset=utf-8");
				String str = "{\"success\":false,\"message\":\"用户登陆超时,请重新登录\"}";
				PrintWriter writer = httpServletResponse.getWriter();
				writer.write(str);
				writer.flush();
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "timeout";
	}

	/**
	 * 获取UE后台配置的config.json配置
	 *
	 * @author 刘钢 2017年4月12日 下午2:10:27
	 */
	@RequestMapping("/getUEConfig")
	@ResponseBody
	public void config() {
		httpServletResponse.setContentType("application/json");
		String rootPath = httpServletRequest.getSession().getServletContext()
				.getRealPath("/");
		String exec = new ActionEnter(httpServletRequest, rootPath).exec();
		WriterToPageByString(exec);
	}

	/**
	 * 跳转到初始化页面
	 *
	 * @author 奚艺轩 2017-5-4 下午1:43:47
	 */
	@RequestMapping("/toInit")
	public String toInit() {
		return "init";
	}

	/**
	 * 登录进入初始化
	 *
	 * @author 奚艺轩 2017-5-4 下午2:50:41
	 */
	@RequestMapping("/init")
	@ResponseBody
	public void init() {
		ActionResult actionResult = new ActionResult();
		String initPassword = httpServletRequest.getParameter("initPassword");
		if (IceStringUtils.equals("edt123456", initPassword)) {
			// 执行初始化程序
			initDB.toInitDB();
			actionResult.setSuccess(CommonConstant.ACTIONRESULT_TRUE);
			actionResult.setMessage("数据初始化成功");
		} else {
			actionResult.setSuccess(CommonConstant.ACTIONRESULT_FAIL);
			actionResult.setMessage("密码错误");
		}
		WriterToPageByJson(actionResult);
	}

	@RequestMapping("ue")
	public String ue() {
		return "ueditor";
	}
}
