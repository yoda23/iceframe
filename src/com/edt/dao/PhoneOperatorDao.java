package com.edt.dao;

import com.edt.common.bean.FindCondition;
import com.edt.entity.PhoneOperator;

import java.util.List;

public interface PhoneOperatorDao {

    /**
     * 通过分页条件查询所有的运营商号码
     *
     * @param condition condition
     * @return java.util.List<com.edt.entity.PhoneOperator>
     * @author 刘钢
     * 2017/5/17 22:45
     */
    List<PhoneOperator> getPhoneOperatorByCondition(FindCondition condition);


    /**
     * 保存运营商号码
     *
     * @param phoneOperator phoneOperator
     * @author 刘钢
     * 2017/5/17 22:45
     */
    void savePhoneOperator(PhoneOperator phoneOperator);

    /**
     * 修改运营商号码
     *
     * @param phoneOperator phoneOperator
     * @return void
     * @author 刘钢
     * 2017/5/17 22:45
     */
    void updatePhoneOperator(PhoneOperator phoneOperator);

    /**
     * 删除运营商号码
     *
     * @param id id
     * @return void
     * @author 刘钢
     * 2017/5/17 22:46
     */
    void deletePhoneOperator(String id);

    /**
     * 根据id查询运营商信息
     *
     * @param id id
     * @return com.edt.entity.PhoneOperator
     * @author 刘钢
     * 2017/5/17 22:46
     */
    PhoneOperator getPhoneOperatorById(String id);


    /**
     * 根据号码前缀查询运营商
     *
     * @param prefix prefix
     * @return com.edt.entity.PhoneOperator
     * @author 刘钢
     * 2017/5/17 22:46
     */
    PhoneOperator getPhoneOperatorByPrefix(String prefix);
}
