package com.edt.service;

import com.edt.controller.phonelocation.PhoneLocationCondition;
import com.edt.entity.PhoneLocation;

import java.util.List;

public interface PhoneLocationService {
    /**
     * 根据手机号前七位查询号码归属地
     *
     * @param header head
     * @return com.edt.entity.PhoneLocation
     * @author 刘钢
     * 2017-05-18 11:42
     */

    PhoneLocation getPhoneLocationByHeader(String header);

    /**
     * 根据号码区号查询所有号码归属地
     *
     * @param areaCode areaCode
     * @return com.edt.entity.PhoneLocation
     * @author 刘钢
     * 2017-05-18 11:43
     */

    PhoneLocation getPhoneLocationByAreaCode(String areaCode);

    /**
     * 根据邮编查询所有号码归属地
     *
     * @param postCode postCode
     * @return com.edt.entity.PhoneLocation
     * @author 刘钢
     * 2017-05-18 11:43
     */

    PhoneLocation getPhoneLocationByPostCode(String postCode);

    /**
     * 根据条件查询手机号段信息
     *
     * @param condition dondition
     * @return java.util.List<com.edt.entity.PhoneLocation>
     * @author 刘钢
     * 2017-05-18 11:43
     */

    List<PhoneLocation> getPhoneLocationByCondition(
            PhoneLocationCondition condition);
}
