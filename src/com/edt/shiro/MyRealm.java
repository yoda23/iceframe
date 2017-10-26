package com.edt.shiro;

import com.edt.entity.Rights;
import com.edt.entity.Role;
import com.edt.entity.User;
import com.edt.entity.UserMechanismsLinked;
import com.edt.service.RoleService;
import com.edt.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class MyRealm extends AuthorizingRealm {
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;

	/**
	 * 权限认证
	 *
	 * @param principalCollection
	 *            principalCollection
	 * @return org.apache.shiro.authz.AuthorizationInfo
	 * @author 刘钢 2017-06-12 9:22
	 */

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principalCollection) {
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		String loginId = (String) super.getAvailablePrincipal(
				principalCollection);
		User user = userService.login(loginId);
		if (user != null) {
			// 获取角色对应的权限
			Role roleRights = roleService
					.getRoleByIdForRights(user.getRole().getId());
			if (roleRights != null) {
				user.setRole(roleRights);
				List<String> listString = new ArrayList<String>();
				for (Rights rights : roleRights.getListRights()) {
					listString.add(rights.getId());
				}
				simpleAuthorInfo.addStringPermissions(listString);
			}
		}
		return simpleAuthorInfo;
	}

	/**
	 * 登陆认证
	 *
	 * @param authenticationToken
	 *            authenticationToken
	 * @return org.apache.shiro.authc.AuthenticationInfo
	 * @author 刘钢 2017-06-12 9:22
	 */

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken)
			throws AuthenticationException {
		SimpleAuthenticationInfo info = null;
		// UsernamePasswordToken对象用来存放提交的登录信息
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		// 查询数据库中用户信息
		User user = userService.login(token.getUsername());
		if (user != null) {
			// 账户锁定
			if (user.getActive() == 1) {
				throw new LockedAccountException();
			}
			// 账户删除
			if (user.getDeleteFlag() == 1) {
				throw new DisabledAccountException();
			}
			// 获取角色对应的菜单
			Role roleMenu = roleService
					.getRoleByIdForMenu(user.getRole().getId());
			if (roleMenu != null) {
				user.getRole().setListMenu(roleMenu.getListMenu());
			}
			info = new SimpleAuthenticationInfo(user.getLoginId(),
					user.getLoginPassword(), getName());
			// 获取角色对应的权限
			Role roleRights = roleService
					.getRoleByIdForRights(user.getRole().getId());
			if (roleRights != null) {
				user.getRole().setListRights(roleRights.getListRights());
			}
			// 加载机构查询列表
			List<UserMechanismsLinked> listUserMechanismsLinked = userService
					.listUserMechanismsLinkedByUserId(user.getId());
			for (UserMechanismsLinked userMechanismsLinked : listUserMechanismsLinked) {
				user.getListMechanisms()
						.add(userMechanismsLinked.getMechanismsId());
			}
			setSession("USER", user);
		}
		return info;
	}

	private void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}

}
