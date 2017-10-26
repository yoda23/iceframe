package com.edt.service.impl;

import com.edt.common.ActionResultService;
import com.edt.common.bean.ActionResult;
import com.edt.common.constant.CommonConstant;
import com.edt.controller.user.UserCondition;
import com.edt.dao.UserDao;
import com.edt.entity.Mechanisms;
import com.edt.entity.Role;
import com.edt.entity.User;
import com.edt.entity.UserMechanismsLinked;
import com.edt.service.RoleService;
import com.edt.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iceutils.security.IceEncryptionUtils;
import com.iceutils.string.IceStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;
	@Resource
	private RoleService roleService;
	@Resource
	private ActionResultService actionResultService;

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public User login(String loginId) {
		return userDao.login(loginId);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<User> getUserByCondition(UserCondition condition) {
		PageHelper.startPage(condition.getPage(), condition.getRows());
		// List<User> listUser = new ArrayList<>();
		List<User> listUser = userDao.getUserByCondition(condition);
		for (User aListUser : listUser) {
			switch (aListUser.getActive()) {
			case 1:
				aListUser.setActiveDisplay("禁用");
				break;
			case 2:
				aListUser.setActiveDisplay("启用");
				break;
			default:
				aListUser.setActiveDisplay("");
			}
			switch (aListUser.getFind()) {
			case 1:
				aListUser.setFindDisplay("管理员");
				break;
			case 2:
				aListUser.setFindDisplay("普通员工");
				break;
			default:
				aListUser.setFindDisplay("");

			}
			Role role = aListUser.getRole();
			aListUser.setUserRoleName(role.getName());
			Mechanisms mechanisms = aListUser.getMechanisms();
			aListUser.setMechanismsName(mechanisms.getName());
		}
		PageInfo<User> pageInfo = new PageInfo<>(listUser);
		condition.setTotalRows(pageInfo.getTotal());
		return listUser;
	}

	@Transactional
	@Override
	public ActionResult saveUser(User user) {
		ActionResult actionResult;
		User loginIdUser = getUserByLoginId(user.getLoginId());
		if (loginIdUser == null) {
			// 根据角色ID查询所属角色
			Role role = roleService.getRoleById(user.getRoleId());
			if (role != null) {
				// 保存用户表
				userDao.saveUser(user);
				// 保存用户机构查询关联表
				saveUserMechanismsLinked(user);
				actionResult = actionResultService.callBackResult(
						CommonConstant.ACTIONRESULT_TRUE, "用户保存成功", null);
			} else {
				actionResult = actionResultService.callBackResult(
						CommonConstant.ACTIONRESULT_FAIL, "角色关联失败", null);
			}
		} else {
			actionResult = actionResultService.callBackResult(
					CommonConstant.ACTIONRESULT_FAIL, "登录账号不能重复", null);

		}
		return actionResult;
	}

	/**
	 * 保存用户可查询机构中间表
	 *
	 * @param user
	 *            user
	 * @author 刘钢 2017-07-07 16:56
	 */

	private void saveUserMechanismsLinked(User user) {
		// 保存机构关联表
		if (user.getMechanismsIdCheck() != null) {
			String[] mechanismsIdCheck = user.getMechanismsIdCheck().split(",");
			List<String> listMechanismsId = new ArrayList<>();
			if (mechanismsIdCheck.length > 1) {
				listMechanismsId = new ArrayList<>(
						Arrays.asList(mechanismsIdCheck));
			} else {
				// 只有一个ID
				listMechanismsId.add(user.getMechanismsIdCheck());
			}
			for (String s : listMechanismsId) {
				if (IceStringUtils.isNotEmpty(s)) {
					UserMechanismsLinked userMechanismsLinked = new UserMechanismsLinked();
					Mechanisms mechanisms = new Mechanisms();
					mechanisms.setId(s);
					userMechanismsLinked.setUser(user);
					userMechanismsLinked.setMechanisms(mechanisms);
					userDao.saveUserMechanismsLinked(userMechanismsLinked);
				}
			}
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public User getUserById(String userId) {
		return userDao.getUserById(userId);
	}

	@Transactional
	@Override
	public ActionResult updateUser(User user) {
		ActionResult actionResult;
		// 查询对应的角色信息
		Role role = roleService.getRoleById(user.getRoleId());
		if (role != null) {
			userDao.updateUser(user);
			// 保存用户机构查询关联表
			userDao.deleteUserMechanismsLinked(user.getId());
			saveUserMechanismsLinked(user);
			actionResult = actionResultService.callBackResult(
					CommonConstant.ACTIONRESULT_TRUE, "修改成功", null);
		} else {
			actionResult = actionResultService.callBackResult(
					CommonConstant.ACTIONRESULT_FAIL, "关联角色不存在,修改失败", null);
		}

		return actionResult;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public User getUserByLoginId(String loginId) {
		return userDao.getUserByLoginId(loginId);
	}

	@Transactional
	@Override
	public ActionResult deleteUser(String id) {
		ActionResult actionResult;
		// 删除用户可查询机构的中间表
		userDao.deleteUserMechanismsLinked(id);
		// 删除用户信息
		userDao.deleteUser(id);
		actionResult = actionResultService.callBackResult(
				CommonConstant.ACTIONRESULT_TRUE, "用户删除成功", null);
		return actionResult;
	}

	@Transactional
	@Override
	public ActionResult updateState(User user) {
		ActionResult actionResult;
		userDao.updateState(user);
		if (user.getActive() == 1) {
			actionResult = actionResultService.callBackResult(
					CommonConstant.ACTIONRESULT_TRUE, "禁用成功", null);
		} else {
			actionResult = actionResultService.callBackResult(
					CommonConstant.ACTIONRESULT_TRUE, "启用成功", null);
		}
		return actionResult;
	}

	@Transactional
	@Override
	public ActionResult updateLoginPassword(User user) {
		ActionResult actionResult;
		user = userDao.getUserById(user.getId());
		user.setLoginPassword(IceEncryptionUtils.md5Hex(user.getLoginId()));
		userDao.updateLoginPassword(user);
		actionResult = actionResultService.callBackResult(
				CommonConstant.ACTIONRESULT_TRUE,
				"密码重置成功，已经将密码重置为" + user.getLoginId(), null);
		return actionResult;
	}

	@Transactional
	@Override
	public ActionResult updateUserByUserInfo(User user) {
		ActionResult actionResult;
		userDao.updateUserInfo(user);
		actionResult = actionResultService.callBackResult(
				CommonConstant.ACTIONRESULT_TRUE, "修改个人信息成功", null);
		return actionResult;
	}

	@Transactional
	@Override
	public ActionResult updateUserPassword(User user) {
		ActionResult actionResult;
		User userId = userDao.getUserById(user.getId());
		if (IceEncryptionUtils.md5Hex(user.getOldPassword())
				.equals(userId.getLoginPassword())) {
			user.setLoginPassword(
					IceEncryptionUtils.md5Hex(user.getLoginPassword()));
			userDao.updateLoginPassword(user);
			actionResult = actionResultService.callBackResult(
					CommonConstant.ACTIONRESULT_TRUE, "修改密码成功", null);
		} else {
			actionResult = actionResultService.callBackResult(
					CommonConstant.ACTIONRESULT_FAIL, "旧密码输入错误，请重新输入!", null);
		}
		return actionResult;
	}

	@Transactional
	@Override
	public ActionResult updateUserDeleteFlag(User user) {
		ActionResult actionResult;
		userDao.updateDeleteFlag(user);
		if (user.getDeleteFlag() == 1) {
			actionResult = actionResultService.callBackResult(
					CommonConstant.ACTIONRESULT_TRUE, "删除用户成功", null);
		} else {
			actionResult = actionResultService.callBackResult(
					CommonConstant.ACTIONRESULT_TRUE, "找回用户成功", null);
		}
		return actionResult;
	}

	@Transactional
	@Override
	public void deleteAllUserInfo() {
		userDao.deleteAllUserInfo();
	}
    @Transactional
	@Override
	public List<UserMechanismsLinked> listUserMechanismsLinkedByUserId(
			String userId) {
		return userDao.listUserMechanismsLinkedByUserId(userId);
	}
    @Transactional
	@Override
	public void deleteUserMechanismsLinkedByAll() {
		userDao.deleteUserMechanismsLinkedByAll();
	}
    @Transactional
	@Override
	public void saveUserMechanismsLinked(
			UserMechanismsLinked userMechanismsLinked) {
		userDao.saveUserMechanismsLinked(userMechanismsLinked);
	}
    @Transactional
	@Override
	public List<User> listUserByMechanismsId(String mechanismsId) {
		return userDao.listUserByMechanismsId(mechanismsId);
	}

}
