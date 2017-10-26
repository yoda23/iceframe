package com.edt.controller.user;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.edt.common.BaseController;
import com.edt.common.bean.ActionResult;
import com.edt.entity.User;
import com.edt.entity.UserMechanismsLinked;
import com.edt.service.UserService;
import com.iceutils.random.IceRandomUtils;
import com.iceutils.security.IceEncryptionUtils;
import com.iceutils.string.IceStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Resource
	private UserService userService;
	@Resource
	private HttpSession httpSession;
	@Resource
	private HttpServletResponse httpServletResponse;
	@Resource
	private HttpServletRequest httpServletRequest;
	private ActionResult actionResult = new ActionResult();

	/**
	 * 验证用户登录
	 *
	 * @param user
	 *            user
	 * @author 刘钢 2017/6/11 22:04
	 */
	@RequestMapping("login")
	@ResponseBody
	public void loginAction(User user) {
		// 验证session中的验证码
		String sessionCode = (String) httpSession.getAttribute("VALIDATECODE");
		String pageCode = httpServletRequest.getParameter("validateCode");
		if ((sessionCode != null) && (sessionCode.equalsIgnoreCase(pageCode))) {
			user.setLoginPassword(
					IceEncryptionUtils.md5Hex(user.getLoginPassword()));
			UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
					user.getLoginId(), user.getLoginPassword());
			// usernamePasswordToken.setRememberMe(true);
			Subject currentUser = SecurityUtils.getSubject();
			try {
				currentUser.login(usernamePasswordToken);
				actionResult.setSuccess(true);
			} catch (UnknownAccountException uae) {
				actionResult.setSuccess(false);
				actionResult.setMessage("未知账号");
			} catch (IncorrectCredentialsException ice) {
				actionResult.setSuccess(false);
				actionResult.setMessage("用户名或密码不正确");
			} catch (LockedAccountException lae) {
				actionResult.setSuccess(false);
				actionResult.setMessage("账号已锁定");
			} catch (ExcessiveAttemptsException eae) {
				actionResult.setSuccess(false);
				actionResult.setMessage("用户名或密码错误次数过多");
			} catch (DisabledAccountException dae) {
				actionResult.setSuccess(false);
				actionResult.setMessage("账号已停用");
			} catch (AuthenticationException ae) {
				actionResult.setSuccess(false);
				actionResult.setMessage("用户名或密码不正确");
			}
			// 验证是否登录成功
			if (!currentUser.isAuthenticated()) {
				usernamePasswordToken.clear();
			}
		} else {
			actionResult.setSuccess(false);
			actionResult.setMessage("验证码错误");
		}
		WriterToPageByJson(actionResult);
	}

	/**
	 * 用户退出
	 *
	 * @return org.springframework.web.servlet.ModelAndView
	 * @author 刘钢 2017/6/11 22:05
	 */
	@RequestMapping("logout")
	public ModelAndView logoutAction() {
		httpSession.removeAttribute("USER");
		SecurityUtils.getSubject().logout();
		return new ModelAndView("redirect:/login");
	}

	/**
	 * 进入个人信息
	 *
	 * @param user
	 *            user
	 * @return java.lang.String
	 * @author 刘钢 2017/6/11 22:05
	 */
	@RequestMapping("toUserInfo")
	public String toUserInfo(User user, Model model) {
		user = userService.getUserById(user.getId());
		model.addAttribute(user);
		return "user/userInfo";
	}

	/**
	 * 修改密码
	 *
	 * @param user
	 *            user
	 * @author 刘钢 2017/6/11 22:05
	 */
	@RequestMapping("updatePassword")
	@ResponseBody
	public void updatePassword(User user) {
		ActionResult actionResult = userService.updateUserPassword(user);
		WriterToPageByJson(actionResult);
	}

	/**
	 * 修改个人信息
	 *
	 * @param user
	 *            user
	 * @author 刘钢 2017/6/11 22:05
	 */
	@RequestMapping("updateUserInfo")
	@ResponseBody
	public void updateUserInfo(User user) {
		ActionResult actionResult = userService.updateUserByUserInfo(user);
		WriterToPageByJson(actionResult);
	}

	/**
	 * 获取所有用户信息
	 *
	 * @param condition
	 *            condition
	 * @author 刘钢 2017/6/11 22:06
	 */
	@RequestMapping("getUserByConditon")
	@ResponseBody
	public void getUserByConditon(UserCondition condition) {
		// httpSession.removeAttribute("USER");
		condition.setRows(condition.getLength());
		condition.setPage(condition.getStart() / condition.getLength() + 1);
		if (IceStringUtils.isNotBlank(condition.getUserName())) {
			condition.setUserName(condition.getUserName());
		}
		User user = (User) httpSession.getAttribute("USER");
		condition.setUserId(user.getId());
		List<User> listRole = userService.getUserByCondition(condition);
		Map<String, Object> map = new HashMap<>();
		map.put("draw", condition.getDraw());
		map.put("recordsTotal", condition.getTotalRows());
		map.put("recordsFiltered", condition.getTotalRows());
		map.put("data", listRole);
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(User.class,
				"id", "loginId", "name", "mechanismsName", "userRoleName",
				"activeDisplay", "findDisplay", "addUser", "addTime");
		WriterToPageByJsonByFilter(map, filter);

	}

	/**
	 * 保存用户
	 *
	 * @param user
	 *            user
	 * @author 刘钢 2017/6/11 22:06
	 */

	@RequestMapping("saveUser")
	@ResponseBody
	public void saveUser(User user) {
		User sessionUser = (User) httpSession.getAttribute("USER");
		user.setId(IceRandomUtils.getLongUUID());
		user.setRoleId(httpServletRequest.getParameter("adminRole"));
		user.setLoginPassword(
				IceEncryptionUtils.md5Hex(user.getLoginPassword()));
		user.setDeleteFlag(2);
		user.setActive(2);
		user.setAddTime(new Date());
		user.setAddUser(sessionUser.getName());
		user.setLogId(IceRandomUtils.getLongUUID());
		user.setLogContent(user.toString());
		user.setLogMenu("系统菜单-用户管理");
		user.setLogOperation("添加用户");
		user.setLogUser(sessionUser.getName());
		user.setLogTime(new Date());
		actionResult = userService.saveUser(user);
		WriterToPageByJson(actionResult);
	}

	/**
	 * 根据用户userId查询用户信息，用于修改页面的跳转
	 *
	 * @author 奚艺轩 2017-4-12 下午2:59:56
	 */
	@RequestMapping("toUpdateUser")
	public String toUpdateUser(User user, Model model) {
		user = userService.getUserById(user.getId());
		List<String> listStr = new ArrayList<>();
		if (user != null) {
			List<UserMechanismsLinked> listUserMechanismsLinked = userService
					.listUserMechanismsLinkedByUserId(user.getId());
			for (UserMechanismsLinked userMechanismsLinked : listUserMechanismsLinked) {
				listStr.add(userMechanismsLinked.getMechanismsId());
			}
			model.addAttribute("mechanismsIdCheck",
					IceStringUtils.join(listStr, ","));
			user.setRoleId(user.getRole().getId());
			model.addAttribute(user);
			return "user/userUpdate";
		}
		return "/none";
	}

	/**
	 * 修改用户信息
	 *
	 * @param user
	 *            user
	 * @author 刘钢 2017/6/11 22:06
	 */
	@RequestMapping("updateUser")
	@ResponseBody
	public void updateUserAction(User user) {
		User sessionUser = (User) httpSession.getAttribute("USER");
		user.setRoleId(httpServletRequest.getParameter("adminRole"));
		user.setLogId(IceRandomUtils.getLongUUID());
		user.setLogContent(user.toString());
		user.setLogMenu("系统菜单-用户管理");
		user.setLogOperation("修改用户");
		user.setLogUser(sessionUser.getName());
		user.setLogTime(new Date());
		ActionResult actionResult = userService.updateUser(user);
		WriterToPageByJson(actionResult);
	}

	/**
	 * 删除/找回用户
	 *
	 * @param user
	 *            user
	 * @author 刘钢 2017/6/11 22:06
	 */
	@RequestMapping("updateDeleteFlag")
	@ResponseBody
	public void updateDeleteFlag(User user) {
		User sessionUser = (User) httpSession.getAttribute("USER");
		user.setRoleId(httpServletRequest.getParameter("adminRole"));
		user.setLogId(IceRandomUtils.getLongUUID());
		user.setLogContent(user.toString());
		user.setLogMenu("系统菜单-用户管理");
		user.setLogOperation("删除/找回用户");
		user.setLogUser(sessionUser.getName());
		user.setLogTime(new Date());
		ActionResult actionResult = userService.updateUserDeleteFlag(user);
		WriterToPageByJson(actionResult);
	}

	/**
	 * 启用/禁用用户
	 *
	 * @author 奚艺轩 2017-4-12 下午3:00:28
	 */
	@RequestMapping("updateState")
	@ResponseBody
	public void updateState(User user) {
		User sessionUser = (User) httpSession.getAttribute("USER");
		user.setRoleId(httpServletRequest.getParameter("adminRole"));
		user.setLogId(IceRandomUtils.getLongUUID());
		user.setLogContent(user.toString());
		user.setLogMenu("系统菜单-用户管理");
		user.setLogOperation("禁用或启用用户");
		user.setLogUser(sessionUser.getName());
		user.setLogTime(new Date());
		ActionResult actionResult = userService.updateState(user);
		WriterToPageByJson(actionResult);
	}

	/**
	 * 重置密码
	 *
	 * @param user
	 *            user
	 * @author 刘钢 2017/6/11 22:06
	 */
	@RequestMapping("resetLoginPassword")
	@ResponseBody
	public void resetLoginPassword(User user) {
		User sessionUser = (User) httpSession.getAttribute("USER");
		user.setRoleId(httpServletRequest.getParameter("adminRole"));
		user.setLogId(IceRandomUtils.getLongUUID());
		user.setLogContent(user.toString());
		user.setLogMenu("系统菜单-用户管理");
		user.setLogOperation("重置用户密码");
		user.setLogUser(sessionUser.getName());
		user.setLogTime(new Date());
		ActionResult actionResult = userService.updateLoginPassword(user);
		WriterToPageByJson(actionResult);
	}

	@RequestMapping("exportXls")
	public String exportXls(ModelMap map) {
		UserCondition userCondition = new UserCondition();
		userCondition.setRows(10);
		userCondition.setPage(0);
		List<User> listUser = userService.getUserByCondition(userCondition);
		map.put(NormalExcelConstants.FILE_NAME, "用户信息");
		map.put(NormalExcelConstants.CLASS, User.class);
		map.put(NormalExcelConstants.PARAMS,
				new ExportParams("课程列表", "导出人:Jeecg", "导出信息"));
		map.put(NormalExcelConstants.DATA_LIST, listUser);
		return NormalExcelConstants.EASYPOI_EXCEL_VIEW;
	}
}
