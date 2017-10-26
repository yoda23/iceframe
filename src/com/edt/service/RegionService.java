package com.edt.service;

import com.edt.controller.region.RegionCondition;
import com.edt.entity.Region;

import java.util.List;

public interface RegionService {
    /**
     * 获取所有省份数据
     *
     * @return java.util.List<com.edt.entity.Region>
     * @author 刘钢
     * 2017-05-18 11:45
     */

    List<Region> getProvinceByAll();

    /**
     * 根据省份编码获取所有城市数据
     *
     * @param provinceId provinceId
     * @return java.util.List<com.edt.entity.Region>
     * @author 刘钢
     * 2017-05-18 11:45
     */

    List<Region> getCityByProvinceId(String provinceId);

    /**
     * 根据城市编码获取所有地区数据
     *
     * @param cityId cityId
     * @return java.util.List<com.edt.entity.Region>
     * @author 刘钢
     * 2017-05-18 11:45
     */

    List<Region> getAreaByCityId(String cityId);

    /**
     * 根据查询条件获取行政区域数据
     *
     * @param condition condition
     * @return java.util.List<com.edt.entity.Region>
     * @author 刘钢
     * 2017-05-18 11:45
     */

    List<Region> getRegionByConditon(RegionCondition condition);
}
