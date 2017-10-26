package com.edt.service;

import com.edt.common.bean.ActionResult;
import com.edt.common.bean.FindCondition;
import com.edt.entity.PhoneOperator;

import java.util.List;

public interface PhoneOperatorService {
    /**
     * 根据条件查询获取运营商号码识别数据信息
     *
     * @param condition condition
     * @return java.util.List<com.edt.entity.PhoneOperator>
     * @author 刘钢
     * 2017-05-18 11:43
     */

    List<PhoneOperator> getListPhoneOperatorByCondition(FindCondition condition);

    /**
     * 删除运营商号码识别信息
     *
     * @param id id
     * @return com.edt.common.bean.ActionResult
     * @author 刘钢
     * 2017-05-18 11:44
     */

    ActionResult deletePhoneOperator(String id);

    /**
     * 根据ID查询运营商号码识别信息，跳转修改页面
     *
     * @param id id
     * @return com.edt.entity.PhoneOperator
     * @author 刘钢
     * 2017-05-18 11:44
     */

    PhoneOperator getPhoneOperatorById(String id);

    /**
     * 保存运营商号码识别信息
     *
     * @param phoneOperator phoneOperator
     * @return com.edt.common.bean.ActionResult
     * @author 刘钢
     * 2017-05-18 11:44
     */

    ActionResult savePhoneOperator(PhoneOperator phoneOperator);

    /**
     * 更新运营商号码识别信息
     *
     * @param phoneOperator phoneOperator
     * @return com.edt.common.bean.ActionResult
     * @author 刘钢
     * 2017-05-18 11:44
     */

    ActionResult updatePhoneOperator(PhoneOperator phoneOperator);

    /**
     * 根据电话前缀获取运营商号码信息
     *
     * @param prefix prefix
     * @return com.edt.entity.PhoneOperator
     * @author 刘钢
     * 2017-05-18 11:44
     */

    PhoneOperator getPhoneOperatorByPrefix(String prefix);
}
