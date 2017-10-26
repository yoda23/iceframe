package com.edt.service.impl;

import com.edt.controller.region.RegionCondition;
import com.edt.dao.RegionDao;
import com.edt.entity.Region;
import com.edt.service.RegionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional(propagation = Propagation.SUPPORTS)
@Service
public class RegionServiceImpl implements RegionService {

	@Resource
	private RegionDao regionDao;

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<Region> getCityByProvinceId(String provinceId) {
		List<Region> listCity = regionDao.getCityByProvinceId(provinceId);
		// 集合等于0，说明查询的城市是直辖市，需要根据ID进行查询
		if (listCity.size() == 0) {
			listCity = regionDao.getCityById(provinceId);
		}
		return listCity;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<Region> getAreaByCityId(String cityId) {
		return regionDao.getAreaByCityId(cityId);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<Region> getProvinceByAll() {
		return regionDao.getProvinceByAll();
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<Region> getRegionByConditon(RegionCondition condition) {
		PageHelper.startPage(condition.getPage(), condition.getRows());
		List<Region> listRegion = regionDao.getRegionByConditon(condition);
		PageInfo<Region> pageInfo = new PageInfo<>(listRegion);
		condition.setTotalRows(pageInfo.getTotal());
		return listRegion;
	}
}
