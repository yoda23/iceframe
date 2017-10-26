package com.edt.dao;

import com.edt.entity.Menu;

import java.util.List;

public interface MenuDao {
    /**
     * 查询所有的菜单
     *
     * @return List<Menu>
     * @author 刘钢
     * 2017/5/17 22:43
     */
    List<Menu> getListMenu();

    /**
     * 保存菜单
     *
     * @param menu menu
     * @author 刘钢
     * 2017/5/17 22:43
     */
    void saveMenu(Menu menu);

    /**
     * 删除所有菜单
     *
     * @author 刘钢
     * 2017/5/17 22:43
     */
    void deleteAllMenu();
}
