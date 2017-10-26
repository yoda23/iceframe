package com.edt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.edt.dao.RightsDao;
import com.edt.entity.Rights;
import com.edt.service.RightsService;

@Service
public class RightsServiceImpl implements RightsService {

	@Resource
	private RightsDao rightsDao;

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<Rights> getListRights() {
		return rightsDao.getListRights();
	}

	@Transactional
	@Override
	public void saveRights(Rights rights) {
		rightsDao.saveRights(rights);
	}

	@Transactional
	@Override
	public void deleteAllRights() {
		rightsDao.deleteAllRights();
	}

}
