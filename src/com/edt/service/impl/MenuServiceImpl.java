package com.edt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.edt.dao.MenuDao;
import com.edt.entity.Menu;
import com.edt.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Resource
	private MenuDao menuDao;

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<Menu> getListMenu() {
		return menuDao.getListMenu();
	}

	@Transactional
	@Override
	public void saveMenu(Menu menu) {
		menuDao.saveMenu(menu);
	}

	@Transactional
	@Override
	public void deleteAllMenu() {
		menuDao.deleteAllMenu();
	}

}
