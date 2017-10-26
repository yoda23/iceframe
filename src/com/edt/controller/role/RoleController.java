package com.edt.controller.role;

import com.edt.common.BaseController;
import com.edt.common.bean.ActionResult;
import com.edt.entity.Role;
import com.edt.entity.User;
import com.edt.service.RoleService;
import com.iceutils.random.IceRandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
	@Resource
	private HttpSession httpSession;
	@Resource
	private HttpServletResponse httpServletResponse;
	@Resource
	private HttpServletRequest httpServletRequest;
	@Resource
	private RoleService roleService;

	/**
	 * 分页，按照条件查询角色信息
	 *
	 * @param condition
	 *            condition
	 * @author 奚艺轩 2017-6-1上午10:30:14
	 */
	@RequestMapping("getRoleByCondition")
	@ResponseBody
	public void getRoleByRowsByAll(RoleCondition condition) {
		condition.setRows(condition.getLength());
		condition.setPage(condition.getStart() / condition.getLength() + 1);
		List<Role> listRole = roleService.getRoleByCondition(condition);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("draw", condition.getDraw());
		map.put("recordsTotal", condition.getTotalRows());
		map.put("recordsFiltered", condition.getTotalRows());
		map.put("data", listRole);
		WriterToPageByJson(map);
	}

	/**
	 * 获取所有角色信息
	 *
	 * @param condition
	 *            condition
	 * @author 奚艺轩 2017-6-1上午10:30:25
	 */
	@RequestMapping("getRoleByAll")
	@ResponseBody
	public void getRoleByNoRowsByAll(RoleCondition condition) {
		condition.setRows(0);
		condition.setPage(1);
		List<Role> listRole = roleService.getRoleByCondition(condition);
		WriterToPageByJson(listRole);
	}

	/**
	 * 删除角色信息
	 *
	 * @param role
	 *            role
	 * @author 奚艺轩 2017-6-1上午10:30:34
	 */
	@RequestMapping("deleteRole")
	@ResponseBody
	public void deleteRole(Role role) {
		ActionResult actionResult = roleService.deleteRole(role);
		WriterToPageByJson(actionResult);
	}

	/**
	 * 保存角色信息
	 *
	 * @param role
	 *            role
	 * @author 奚艺轩 2017-6-1上午10:30:45
	 */
	@RequestMapping("saveRole")
	@ResponseBody
	public void saveRole(Role role) {
		User sessionUser = (User) httpSession.getAttribute("USER");
		role.setId(IceRandomUtils.getLongUUID());
		role.setAddTime(new Date());
		role.setAddUser(sessionUser.getId());
		ActionResult actionResult = roleService.saveRole(role);
		WriterToPageByJson(actionResult);
	}

	/**
	 * 跳转到给角色添加菜单页面
	 *
	 * @param role
	 *            role
	 * @param model
	 *            model
	 * @return String
	 * @author 奚艺轩 2017-6-1上午10:30:53
	 */
	@RequestMapping("toUpdateMenu")
	public String toUpdateMenu(Role role, Model model) {
		// String roleId = httpServletRequest.getParameter("roleId");
		// 根据roleId查询菜单集合
		role = roleService.getRoleByIdForMenu(role.getId());
		if (role != null) {
			StringBuilder menuId = new StringBuilder();
			for (int i = 0; i < role.getListMenu().size(); i++) {
				if (i + 1 == role.getListMenu().size()) {
					menuId.append(role.getListMenu().get(i).getId());
				} else {
					menuId.append(role.getListMenu().get(i).getId())
							.append(",");
				}
			}
			role.setMenuId(menuId.toString());
			model.addAttribute(role);
			return "role/roleMenu";
		} else {
			return "none";
		}

	}

	/**
	 * 给角色添加菜单
	 *
	 * @param role
	 *            role
	 * @author 奚艺轩 2017-6-1上午10:31:10
	 */
	@RequestMapping("updateMenuForRole")
	@ResponseBody
	public void updateMenuForRole(Role role) {
		// String menuId = httpServletRequest.getParameter("menuId");
		// role.setMenuId(menuId);
		ActionResult actionResult = roleService.updateRoleByIdForMenu(role);
		WriterToPageByJson(actionResult);
	}

	/**
	 * 跳转到给角色添加权限页面
	 *
	 * @param role
	 *            role
	 * @param model
	 *            model
	 * @return String
	 * @author 奚艺轩 2017-6-1上午10:31:18
	 */
	@RequestMapping("toUpdateRights")
	public String toUpdateRights(Role role, Model model) {
		// 根据roleId查询权限集合
		role = roleService.getRoleByIdForRights(role.getId());
		if (role != null) {
			StringBuilder rightsId = new StringBuilder();
			for (int i = 0; i < role.getListRights().size(); i++) {
				if (i + 1 == role.getListRights().size()) {
					rightsId.append(role.getListRights().get(i).getId());
				} else {
					rightsId.append(role.getListRights().get(i).getId())
							.append(",");
				}
			}
			role.setRightsId(rightsId.toString());
			model.addAttribute(role);
			return "role/roleRights";
		} else {
			return "none";
		}
	}

	/**
	 * 给角色添加权限
	 *
	 * @param role
	 *            role
	 * @author 奚艺轩 2017-6-1上午10:31:29
	 */
	@RequestMapping("updateRightsForRole")
	@ResponseBody
	public void updateRightsForRole(Role role) {
		ActionResult actionResult = roleService.updateRoleByIdForRights(role);
		WriterToPageByJson(actionResult);
	}

	/**
	 * 跳转到修改角色信息页面
	 *
	 * @param role
	 *            role
	 * @param model
	 *            model
	 * @return String
	 * @author 奚艺轩 2017-6-1上午10:31:39
	 */
	@RequestMapping("toUpdateRole")
	public String toUpdateRole(Role role, Model model) {
		role = roleService.getRoleById(role.getId());
		if (role != null) {
			model.addAttribute(role);
			return "role/roleUpdate";
		} else {
			return "none";
		}
	}

	/**
	 * 修改角色信息
	 *
	 * @param role
	 *            role
	 * @author 奚艺轩 2017-6-1上午10:31:50
	 */
	@RequestMapping("updateRole")
	@ResponseBody
	public void updateRole(Role role) {
		ActionResult actionResult = roleService.updateRole(role);
		WriterToPageByJson(actionResult);
	}
}
