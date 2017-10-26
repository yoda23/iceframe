package com.edt.service.impl;

import com.edt.dao.DictionaryDao;
import com.edt.entity.Dictionary;
import com.edt.service.DictionaryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {
	@Resource
	private DictionaryDao dictionaryDao;

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<Dictionary> getDictionary() {
		return dictionaryDao.getDictionary();
	}

}
