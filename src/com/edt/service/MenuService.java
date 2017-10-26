package com.edt.service;

import com.edt.entity.Menu;

import java.util.List;

public interface MenuService {

    /**
     * 查询所有的菜单
     *
     * @return List<Menu>
     * @author 刘钢
     * 2017-05-18 11:42
     */

    List<Menu> getListMenu();

    /**
     * 保存菜单
     *
     * @param menu menu
     * @author 刘钢
     * 2017-05-18 11:42
     */

    void saveMenu(Menu menu);

    /**
     * 删除所有菜单
     *
     * @author 刘钢
     * 2017-05-18 11:42
     */

    void deleteAllMenu();

}
