package com.edt.dao;

import com.edt.entity.Rights;

import java.util.List;

public interface RightsDao {
    /**
     * 获取所有权限
     *
     * @return java.util.List<com.edt.entity.Rights>
     * @author 刘钢
     * 2017/5/17 22:47
     */
    List<Rights> getListRights();

    /**
     * 保存权限信息
     *
     * @param rights rights
     * @author 刘钢
     * 2017/5/17 22:47
     */
    void saveRights(Rights rights);

    /**
     * 删除所有权限
     *
     * @author 刘钢
     * 2017/5/17 22:47
     */
    void deleteAllRights();
}
