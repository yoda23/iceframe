package com.edt.service;

import com.edt.entity.Rights;

import java.util.List;

public interface RightsService {

    /**
     * 获取所有权限
     *
     * @return java.util.List<com.edt.entity.Rights>
     * @author 刘钢
     * 2017-05-18 11:46
     */

    List<Rights> getListRights();

    /**
     * 保存权限信息
     *
     * @param rights rights
     * @author 刘钢
     * 2017-05-18 11:46
     */

    void saveRights(Rights rights);

    /**
     * 删除所有权限
     *
     * @author 刘钢
     * 2017-05-18 11:46
     */

    void deleteAllRights();
}
