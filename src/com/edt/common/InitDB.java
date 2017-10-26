package com.edt.common;

import com.edt.entity.*;
import com.edt.service.*;
import com.iceutils.random.IceRandomUtils;
import com.iceutils.security.IceEncryptionUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class InitDB {

	@Resource
	private UserService userService;
	@Resource
	private RoleService roleServic;
	@Resource
	private RightsService rightsService;
	@Resource
	private MenuService menuService;
	@Resource
	private MechanismsService mechanismsService;

	public void toInitDB() {
		// 清空所有用户信息
		userService.deleteAllUserInfo();
		// 清空所有机构信息
		mechanismsService.deleteAllMechanisms();
		// 清空所有角色与菜单的关联
		roleServic.deleteAllRoleMenuLinked();
		// 清空所有角色与权限的关联
		roleServic.deleteAllRoleRightsLinked();
		// 清空所有菜单数据
		menuService.deleteAllMenu();
		// 清空所有权限数据
		rightsService.deleteAllRights();
		// 清空所有角色信息
		roleServic.deleteAllRole();
		// 清空所有用户可查询机构中间表数据
		userService.deleteUserMechanismsLinkedByAll();
		// --------------------------------
		// 菜单信息
		List<Menu> listMenus = new ArrayList<Menu>();
		SAXReader reader = new SAXReader();
		String initDBPath = this.getClass().getResource("/").getPath()
				+ "initDB.xml";
		Document document = null;
		try {
			document = reader.read(new File(initDBPath));
			Element root = document.getRootElement();
			Element elementMenus = root.element("menus");
			for (Iterator iterator = elementMenus.elementIterator(); iterator
					.hasNext();) {
				Element elementMenu = (Element) iterator.next();
				Menu menu = new Menu();
				menu.setId(elementMenu.elementText("id"));
				menu.setName(elementMenu.elementText("name"));
				menu.setOpenUrl(elementMenu.elementText("open_url"));
				menu.setParentId(elementMenu.elementText("parent_id"));
				listMenus.add(menu);
			}
			for (Menu listMenu : listMenus) {
				menuService.saveMenu(listMenu);
			}
			// 权限信息
			List<Rights> listRights = new ArrayList<Rights>();
			Element elementRights = root.element("rights");
			for (Iterator iterator = elementRights.elementIterator(); iterator
					.hasNext();) {
				Element elementRight = (Element) iterator.next();
				Rights rights = new Rights();
				rights.setId(elementRight.elementText("id"));
				rights.setName(elementRight.elementText("name"));
				rights.setParentId(elementRight.elementText("parent_id"));
				listRights.add(rights);
			}
			for (Rights listRight : listRights) {
				rightsService.saveRights(listRight);
			}
			// 用户信息
			User user = new User();
			user.setId(IceRandomUtils.getLongUUID());
			user.setActive(2);
			user.setAddTime(new Date());
			user.setAddUser("系统生成");
			user.setDeleteFlag(2);
			user.setFind(1);
			user.setLoginId("admin");
			user.setLoginPassword(IceEncryptionUtils.md5Hex("admin"));
			user.setName("系统管理员");
			user.setMechanismsId("0");
			// 机构信息
			Mechanisms mechanisms = new Mechanisms();
			mechanisms.setAddTime(new Date());
			mechanisms.setAddUser(user.getId());
			mechanisms.setName("黑龙江省");
			mechanisms.setId("0");
			mechanisms.setParentId("-1");
			mechanismsService.saveMechanismsForInit(mechanisms);
			// 用户查询机构授权表
			UserMechanismsLinked userMechanismsLinked = new UserMechanismsLinked();
			userMechanismsLinked.setUser(user);
			userMechanismsLinked.setMechanisms(mechanisms);
			userService.saveUserMechanismsLinked(userMechanismsLinked);
			// 角色管理
			Role role = new Role();
			role.setAddTime(new Date());
			role.setAddUser(user.getId());
			role.setId(IceRandomUtils.getLongUUID());
			role.setName("系统管理");
			user.setRoleId(role.getId());
			roleServic.saveRole(role);
			// 给角色赋予菜单
			for (Menu listMenu : listMenus) {
				RoleMenuLinked roleMenuLinked = new RoleMenuLinked();
				roleMenuLinked.setRole(role);
				roleMenuLinked.setMenu(listMenu);
				roleServic.saveRoleMenuLinked(roleMenuLinked);
			}
			// 给角色赋予权限
			for (Rights listRight : listRights) {
				RoleRightsLinked rightsLinked = new RoleRightsLinked();
				rightsLinked.setRole(role);
				rightsLinked.setRights(listRight);
				roleServic.saveRoleRightsLinked(rightsLinked);
			}
			userService.saveUser(user);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
