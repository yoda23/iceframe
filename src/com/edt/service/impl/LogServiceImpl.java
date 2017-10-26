package com.edt.service.impl;

import com.edt.dao.LogDao;
import com.edt.entity.Log;
import com.edt.service.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class LogServiceImpl implements LogService  {

	@Resource
	private LogDao logDao;

	@Transactional
	@Override
	public void insertLog(Log log) {
		logDao.insertLog(log);
	}

}
