package com.edt.service.impl;

import com.edt.common.ActionResultService;
import com.edt.common.bean.ActionResult;
import com.edt.common.constant.CommonConstant;
import com.edt.controller.role.RoleCondition;
import com.edt.dao.RoleDao;
import com.edt.entity.*;
import com.edt.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iceutils.string.IceStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleDao roleDao;
	@Resource
	private ActionResultService actionResultService;

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<Role> getRoleByCondition(RoleCondition condition) {
		PageHelper.startPage(condition.getPage(), condition.getRows());
		List<Role> listRole = roleDao.getRoleByCondition(condition);
		for (Role aListRole : listRole) {
			aListRole.setAddUser_display(aListRole.getUser().getName());
		}
		PageInfo<Role> pageInfo = new PageInfo<>(listRole);
		condition.setTotalRows(pageInfo.getTotal());
		return listRole;
	}

	@Transactional
	@Override
	public ActionResult saveRole(Role role) {
		ActionResult actionResult;
		Role roleTemp = getRoleByName(role.getName());
		if (roleTemp == null) {
			roleDao.saveRole(role);
			actionResult = actionResultService.callBackResult(
					CommonConstant.ACTIONRESULT_TRUE, "角色保存成功", null);
		} else {
			actionResult = actionResultService.callBackResult(
					CommonConstant.ACTIONRESULT_FAIL, "角色名称不能重复", null);
		}
		return actionResult;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Role getRoleByName(String name) {
		return roleDao.getRoleByName(name);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Role getRoleById(String id) {
		return roleDao.getRoleById(id);
	}

	@Transactional
	@Override
	public ActionResult updateRole(Role role) {
		ActionResult actionResult;
		Role updateRole = getRoleByName(role.getName());
		if (updateRole != null && !updateRole.getId().equals(role.getId())) {
			actionResult = actionResultService.callBackResult(
					CommonConstant.ACTIONRESULT_FAIL, "角色名称不能重复", null);
		} else {
			roleDao.updateRole(role);
			actionResult = actionResultService.callBackResult(
					CommonConstant.ACTIONRESULT_TRUE, "角色修改成功", null);
		}
		return actionResult;
	}

	@Transactional
	@Override
	public ActionResult deleteRole(Role role) {
        // 删除角色菜单对应表
		RoleMenuLinked roleMenuLinked = new RoleMenuLinked();
		roleMenuLinked.setRole(role);
		roleDao.deleteRoleMenuLinked(roleMenuLinked);
		// 删除权限角色对应表
		RoleRightsLinked roleRightsLinked = new RoleRightsLinked();
		roleRightsLinked.setRole(role);
		roleDao.deleteRoleRightsLinked(roleRightsLinked);
		// 删除角色
		roleDao.deleteRole(role);
		return actionResultService.callBackResult(CommonConstant.ACTIONRESULT_TRUE,
				"角色删除成功", null);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Role getRoleByIdForMenu(String id) {
		return roleDao.getRoleByIdForMenu(id);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Role getRoleByIdForRights(String id) {
		return roleDao.getRoleByIdForRights(id);
	}

	@Transactional
	@Override
	public ActionResult updateRoleByIdForMenu(Role role) {
		ActionResult actionResult;
		String[] menuId = role.getMenuId().split(",");
		if (IceStringUtils.isBlank(menuId[0])) {
			actionResult = actionResultService.callBackResult(
					CommonConstant.ACTIONRESULT_FAIL, "菜单管理不能为空", null);
		} else {
			// 删除角色菜单对应信息
			RoleMenuLinked roleMenuLinked = new RoleMenuLinked();
			roleMenuLinked.setRole(role);
			roleDao.deleteRoleMenuLinked(roleMenuLinked);
			// 保存角色菜单对应关系表
			for (String aMenuId : menuId) {
				RoleMenuLinked roleMenuLinkedTemp = new RoleMenuLinked();
				Menu menu = new Menu();
				menu.setId(aMenuId);
				roleMenuLinkedTemp.setMenu(menu);
				roleMenuLinkedTemp.setRole(role);
				saveRoleMenuLinked(roleMenuLinkedTemp);
			}
			actionResult = actionResultService.callBackResult(
					CommonConstant.ACTIONRESULT_TRUE, "菜单添加成功", null);
		}
		return actionResult;
	}

	@Transactional
	@Override
	public ActionResult updateRoleByIdForRights(Role role) {
		ActionResult actionResult;
		String[] RightsId = role.getRightsId().split(",");
		if (IceStringUtils.isBlank(RightsId[0])) {
			actionResult = actionResultService.callBackResult(
					CommonConstant.ACTIONRESULT_FAIL, "权限管理不能为空", null);
		} else {
			// 删除角色权限对应表
			RoleRightsLinked roleRightsLinked = new RoleRightsLinked();
			roleRightsLinked.setRole(role);
			roleDao.deleteRoleRightsLinked(roleRightsLinked);
			// 保存权限对应关系表
			for (String aRightsId : RightsId) {
				RoleRightsLinked roleRightsLinkedTemp = new RoleRightsLinked();
				Rights rights = new Rights();
				rights.setId(aRightsId);
				roleRightsLinkedTemp.setRights(rights);
				roleRightsLinkedTemp.setRole(role);
				saveRoleRightsLinked(roleRightsLinkedTemp);
			}
			actionResult = actionResultService.callBackResult(
					CommonConstant.ACTIONRESULT_TRUE, "权限添加成功", null);
		}
		return actionResult;
	}

	@Transactional
	@Override
	public void deleteAllRole() {
		roleDao.deleteAllRole();
	}

	@Transactional
	@Override
	public void deleteAllRoleMenuLinked() {
		roleDao.deleteAllRoleMenuLinked();
	}

	@Transactional
	@Override
	public void deleteAllRoleRightsLinked() {
		roleDao.deleteAllRoleRightsLinked();
	}

	@Transactional
	@Override
	public void saveRoleMenuLinked(RoleMenuLinked roleMenuLinked) {
		roleDao.saveRoleMenuLinked(roleMenuLinked);
	}

	@Transactional
	@Override
	public void saveRoleRightsLinked(RoleRightsLinked roleRightsLinked) {
		roleDao.saveRoleRightsLinked(roleRightsLinked);
	}

}
