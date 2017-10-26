package com.edt.dao;

import com.edt.common.bean.FindCondition;
import com.edt.entity.Role;
import com.edt.entity.RoleMenuLinked;
import com.edt.entity.RoleRightsLinked;

import java.util.List;

public interface RoleDao {

    /**
     * 按照条件查询所有角色
     *
     * @param condition condition
     * @return List<Role>
     * @author 刘钢
     * 2017/5/17 22:48
     */
    List<Role> getRoleByCondition(FindCondition condition);

    /**
     * 保存角色
     *
     * @param role role
     * @author 刘钢
     * 2017/5/17 22:48
     */
    void saveRole(Role role);

    /**
     * 根据角色名称查询角色信息
     *
     * @param name name
     * @return com.edt.entity.Role
     * @author 刘钢
     * 2017/5/17 22:48
     */
    Role getRoleByName(String name);

    /**
     * 根据角色ID查询角色信息
     *
     * @param id id
     * @return com.edt.entity.Role
     * @author 刘钢
     * 2017/5/17 22:48
     */
    Role getRoleById(String id);

    /**
     * 修改角色
     *
     * @param role role
     * @author 刘钢
     * 2017/5/17 22:48
     */
    void updateRole(Role role);

    /**
     * 删除角色
     *
     * @param role role
     * @author 刘钢
     * 2017/5/17 22:49
     */
    void deleteRole(Role role);

    /**
     * 删除所有角色信息
     *
     * @author 刘钢
     * 2017/5/17 22:49
     */
    void deleteAllRole();

    /**
     * 根据角色id查询菜单集合
     *
     * @param id di
     * @return com.edt.entity.Role
     * @author 刘钢
     * 2017/5/17 22:49
     */
    Role getRoleByIdForMenu(String id);

    /**
     * 根据角色id查询权限集合
     *
     * @param id id
     * @return com.edt.entity.Role
     * @author 刘钢
     * 2017/5/17 22:49
     */
    Role getRoleByIdForRights(String id);

    /**
     * 保存角色和菜单之间关系
     *
     * @param roleMenuLinked roleMenuLinked
     * @author 刘钢
     * 2017/5/17 22:49
     */
    void saveRoleMenuLinked(RoleMenuLinked roleMenuLinked);

    /**
     * 保存角色和权限之间关系
     *
     * @param roleRightsLinked roleRightsLinked
     * @author 刘钢
     * 2017/5/17 22:49
     */
    void saveRoleRightsLinked(RoleRightsLinked roleRightsLinked);

    /**
     * 删除角色和菜单之间关系
     *
     * @param roleMenuLinked roleMenuLinked
     * @author 刘钢
     * 2017/5/17 22:50
     */
    void deleteRoleMenuLinked(RoleMenuLinked roleMenuLinked);

    /**
     * 删除所有角色和菜单之间的关系
     *
     * @author 刘钢
     * 2017/5/17 22:50
     */
    void deleteAllRoleMenuLinked();

    /**
     * 删除角色和权限之间关系
     *
     * @param roleRightsLinked roleRightsLinked
     * @author 刘钢
     * 2017/5/17 22:50
     */
    void deleteRoleRightsLinked(RoleRightsLinked roleRightsLinked);

    /**
     * 删除所有角色和权限之间关系
     *
     * @author 刘钢
     * 2017/5/17 22:50
     */
    void deleteAllRoleRightsLinked();

}
