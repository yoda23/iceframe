package com.edt.controller.index;

import com.edt.entity.Menu;
import com.edt.entity.Role;
import com.edt.entity.User;
import com.iceutils.image.IceValidateImage;
import com.iceutils.json.IceJsonStringUtils;
import com.iceutils.random.IceValidateCodeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = { "/" })
public class IndexController {

	@Resource
	private HttpSession httpSession;
	@Resource
	private HttpServletResponse httpServletResponse;
	@Resource
	private HttpServletRequest httpServletRequest;
	private List<IndexRoleMenu> listIndexRoleMenu = new ArrayList<IndexRoleMenu>();
	Logger logger = LogManager.getLogger(IndexController.class);

	/**
	 * 系统登录页
	 * 
	 * @author 刘钢 2017-05-18 11:24
	 */
	@RequestMapping("login")
	public String login() {
		return "login";
	}

	/**
	 * 系统主页
	 * 
	 * @author 刘钢 2017-05-18 11:24
	 */
	@RequestMapping("index")
	public String index(Model model) {
		logger.info("--------------index------------------");
		listIndexRoleMenu.clear();
		User user = (User) httpSession.getAttribute("USER");
		logger.info(IceJsonStringUtils.toJsonString(user));
		logger.info("---------------------------------");
		if (user != null) {
			Role role = user.getRole();
			IndexRoleMenu indexRoleMenu = new IndexRoleMenu();
			indexRoleMenu.setId("0");
			indexRoleMenu.setPid("0");
			IndexRoleMenu indexRoleMenuFather = new IndexRoleMenu();
			List<IndexRoleMenu> listIndexRoleMenuFather = new ArrayList<IndexRoleMenu>();
			indexRoleMenuFather.setId("0");
			indexRoleMenuFather.setPid("0");
			indexRoleMenuFather.setListMenuChild(listIndexRoleMenuFather);
			getIndexRoleMenuChilds(role.getListMenu(), indexRoleMenu,
					indexRoleMenuFather);
			model.addAttribute(user);
			model.addAttribute("listIndexRoleMenu", listIndexRoleMenu);
			return "index";
		} else {
			return "login";
		}
	}

	/**
	 * 功能菜单-将子节点挂载到父节点上
	 * 
	 * @author 刘钢 2017-05-18 11:24
	 */
	private void getIndexRoleMenuChilds(List<Menu> listMenu,
			IndexRoleMenu indexRoleMenu, IndexRoleMenu indexRoleMenuFather) {
		// 含有子节点
		if (hasChildNode(listMenu, indexRoleMenu)) {
			// 获取当前节点下的子节点
			List<IndexRoleMenu> listIndexRoleMenuChild = getIndexRoleMenuChild(
					listMenu, indexRoleMenu);
			indexRoleMenu.setListMenuChild(listIndexRoleMenuChild);
			// 遍历父节点，加入到父节点的集合中
			for (int i = 0; i < indexRoleMenuFather.getListMenuChild()
					.size(); i++) {
				if (indexRoleMenu.getId().equals(indexRoleMenuFather
						.getListMenuChild().get(i).getId())) {
					indexRoleMenuFather.getListMenuChild().get(i)
							.setListMenuChild(listIndexRoleMenuChild);
				}
			}
			// 根节点不放到里面
			if (indexRoleMenu.getPid().equals("0")
					&& !indexRoleMenu.getId().equals("0")) {
				indexRoleMenuFather.setListMenuChild(listIndexRoleMenuChild);
				listIndexRoleMenu.add(indexRoleMenu);
			}
			for (int i = 0; i < listIndexRoleMenuChild.size(); i++) {
				getIndexRoleMenuChilds(listMenu,
						indexRoleMenu.getListMenuChild().get(i),
						indexRoleMenuFather);
			}

		}
	}

	/**
	 * 功能菜单-返回当前节点下的所有子节点
	 * 
	 * @author 刘钢 2017-05-18 11:24
	 */
	private List<IndexRoleMenu> getIndexRoleMenuChild(List<Menu> listMenu,
			IndexRoleMenu indexRoleMenu) {
		List<IndexRoleMenu> listIndexRoleMenuChild = new ArrayList<IndexRoleMenu>();
		for (Menu menu : listMenu) {
			IndexRoleMenu indexRoleMenuChild = new IndexRoleMenu();
			// 如果父节点等于当前ID
			if (menu.getParentId().equals(indexRoleMenu.getId())) {
				indexRoleMenuChild.setId(menu.getId());
				indexRoleMenuChild.setPid(menu.getParentId());
				indexRoleMenuChild.setName(menu.getName());
				indexRoleMenuChild.setOpenUrl(menu.getOpenUrl());
				listIndexRoleMenuChild.add(indexRoleMenuChild);
			}
		}
		return listIndexRoleMenuChild;

	}

	/**
	 * 功能菜单-是否存在子节点
	 * 
	 * @author 刘钢 2017-05-18 11:24
	 */
	private boolean hasChildNode(List<Menu> listMenu,
			IndexRoleMenu indexRoleMenu) {
		return getIndexRoleMenuChild(listMenu, indexRoleMenu).size() > 0;
	}

	/**
	 * 验证码生成
	 * 
	 * @author 刘钢 2017-05-18 11:24
	 */
	@RequestMapping("getValidateCode")
	@ResponseBody
	public void getValidateCodeAction() {
		// 禁止图像缓存。
		httpServletResponse.setHeader("Pragma", "no-cache");
		httpServletResponse.setHeader("Cache-Control", "no-cache");
		httpServletResponse.setDateHeader("Expires", 0);
		httpServletResponse.setContentType("image/jpeg");
		String code = IceValidateCodeUtils.getValidateCode();
		ServletOutputStream servletOutputStream = null;
		try {
			servletOutputStream = httpServletResponse.getOutputStream();
			BufferedImage buffImg = IceValidateImage
					.getImageAsBufferedImage(code);
			ImageIO.write(buffImg, "png", servletOutputStream);
			servletOutputStream.flush();
			servletOutputStream.close();
			httpServletResponse.flushBuffer();
			httpSession.setAttribute("VALIDATECODE", code);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
