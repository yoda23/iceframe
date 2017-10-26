package com.edt.service;

import com.edt.common.bean.ActionResult;
import com.edt.controller.role.RoleCondition;
import com.edt.entity.Role;
import com.edt.entity.RoleMenuLinked;
import com.edt.entity.RoleRightsLinked;

import java.util.List;

public interface RoleService {
    /**
     * 按照条件查询角色信息
     *
     * @param condition condition
     * @return java.util.List<com.edt.entity.Role>
     * @author 刘钢
     * 2017-05-18 11:46
     */

    List<Role> getRoleByCondition(RoleCondition condition);

    /**
     * 保存角色
     *
     * @param role role
     * @return com.edt.common.bean.ActionResult
     * @author 刘钢
     * 2017-05-18 11:46
     */

    ActionResult saveRole(Role role);

    /**
     * 根据角色名称查询角色信息
     *
     * @param name name
     * @return com.edt.entity.Role
     * @author 刘钢
     * 2017-05-18 11:46
     */

    Role getRoleByName(String name);

    /**
     * 根据角色ID查询角色信息
     *
     * @param id id
     * @return com.edt.entity.Role
     * @author 刘钢
     * 2017-05-18 11:46
     */

    Role getRoleById(String id);

    /**
     * 修改角色
     *
     * @param role role
     * @return com.edt.common.bean.ActionResult
     * @author 刘钢
     * 2017-05-18 11:47
     */

    ActionResult updateRole(Role role);

    /**
     * 删除角色
     *
     * @param role role
     * @return com.edt.common.bean.ActionResult
     * @author 刘钢
     * 2017-05-18 11:47
     */

    ActionResult deleteRole(Role role);

    /**
     * 删除所有角色信息
     *
     * @author 刘钢
     * 2017-05-18 11:47
     */

    void deleteAllRole();

    /**
     * 根据角色id查询菜单集合
     *
     * @param id id
     * @return com.edt.entity.Role
     * @author 刘钢
     * 2017-05-18 11:47
     */

    Role getRoleByIdForMenu(String id);

    /**
     * 根据角色id查询权限集合
     *
     * @param id id
     * @return com.edt.entity.Role
     * @author 刘钢
     * 2017-05-18 11:47
     */

    Role getRoleByIdForRights(String id);

    /**
     * 修改角色对应的菜单信息
     *
     * @param role role
     * @return com.edt.common.bean.ActionResult
     * @author 刘钢
     * 2017-05-18 11:47
     */

    ActionResult updateRoleByIdForMenu(Role role);

    /**
     * 修改角色对应的权限集合
     *
     * @param role role
     * @return com.edt.common.bean.ActionResult
     * @author 刘钢
     * 2017-05-18 11:48
     */

    ActionResult updateRoleByIdForRights(Role role);

    /**
     * 删除所有角色和菜单之间的关系
     *
     * @author 刘钢
     * 2017-05-18 11:48
     */

    void deleteAllRoleMenuLinked();

    /**
     * 删除所有角色和权限之间关系
     *
     * @author 刘钢
     * 2017-05-18 11:48
     */

    void deleteAllRoleRightsLinked();

    /**
     * 保存角色和菜单之间关系
     *
     * @param roleMenuLinked roleMenuLinked
     * @author 刘钢
     * 2017-05-18 11:48
     */

    void saveRoleMenuLinked(RoleMenuLinked roleMenuLinked);

    /**
     * 保存角色和权限之间关系
     *
     * @param roleRightsLinked roleRightsLinked
     * @author 刘钢
     * 2017-05-18 11:48
     */

    void saveRoleRightsLinked(RoleRightsLinked roleRightsLinked);

}
