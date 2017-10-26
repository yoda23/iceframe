package com.edt.dao;

import com.edt.controller.phonelocation.PhoneLocationCondition;
import com.edt.entity.PhoneLocation;

import java.util.List;

public interface PhoneLocationDao {

    /**
     * 根据手机号前七位查询号码归属地
     *
     * @param header header
     * @return com.edt.entity.PhoneLocation
     * @author 刘钢
     * 2017/5/17 22:43
     */
    PhoneLocation getPhoneLocationByHeader(String header);

    /**
     * 根据号码区号查询号码归属地
     *
     * @param areaCode areaCode
     * @return com.edt.entity.PhoneLocation
     * @author 刘钢
     * 2017/5/17 22:44
     */
    PhoneLocation getPhoneLocationByAreaCode(String areaCode);

    /**
     * 根据邮编查询号码归属地
     *
     * @param postCode postCode
     * @return com.edt.entity.PhoneLocation
     * @author 刘钢
     * 2017/5/17 22:44
     */
    PhoneLocation getPhoneLocationByPostCode(String postCode);

    /**
     * 根据条件查询手机号段信息
     *
     * @param condition condition
     * @return java.util.List<com.edt.entity.PhoneLocation>
     * @author 刘钢
     * 2017/5/17 22:44
     */
    List<PhoneLocation> getPhoneLocationByCondition(
            PhoneLocationCondition condition);
}
