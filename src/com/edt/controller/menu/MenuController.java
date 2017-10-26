package com.edt.controller.menu;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edt.common.BaseController;
import com.edt.entity.Menu;
import com.edt.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {

	@Resource
	private MenuService menuService;

	/**
	 * 查询所有的菜单
	 * 
	 * @author 奚艺轩 2017-6-1上午10:18:41
	 */
	@RequestMapping("getListMenu")
	@ResponseBody
	public void getAllMenu() {
		List<Menu> listMenu = menuService.getListMenu();
		WriterToPageByJson(listMenu);
	}

}
