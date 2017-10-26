package com.edt.service.impl;

import com.edt.controller.phonelocation.PhoneLocationCondition;
import com.edt.dao.PhoneLocationDao;
import com.edt.entity.PhoneLocation;
import com.edt.service.PhoneLocationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iceutils.string.IceStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PhoneLocationServiceImpl implements PhoneLocationService {

	@Resource
	private PhoneLocationDao phoneLocationDao;

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public PhoneLocation getPhoneLocationByHeader(String header) {
		if (IceStringUtils.isBlank(header)
				|| IceStringUtils.length(header) < 7) {
			return null;
		}
		header = IceStringUtils.substring(header, 0, 7);
		return phoneLocationDao.getPhoneLocationByHeader(header);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public PhoneLocation getPhoneLocationByAreaCode(String areaCode) {
		PageHelper.startPage(0, 1);
		return phoneLocationDao.getPhoneLocationByAreaCode(areaCode);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public PhoneLocation getPhoneLocationByPostCode(String postCode) {
		PageHelper.startPage(0, 1);
		return phoneLocationDao.getPhoneLocationByPostCode(postCode);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<PhoneLocation> getPhoneLocationByCondition(
			PhoneLocationCondition condition) {
		PageHelper.startPage(condition.getPage(), condition.getRows());
		List<PhoneLocation> listPhoneLocation = phoneLocationDao
				.getPhoneLocationByCondition(condition);
		PageInfo<PhoneLocation> pageInfo = new PageInfo<>(listPhoneLocation);
		condition.setTotalRows(pageInfo.getTotal());
		return listPhoneLocation;
	}

}
