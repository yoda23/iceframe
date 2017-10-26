package com.edt.service;

import com.edt.entity.Dictionary;

import java.util.List;

public interface DictionaryService {
	/**
	 * 获取数据字典
	 * 
	 * @return java.util.List<com.edt.entity.Dictionary>
	 * @author 刘钢 2017/5/17 22:33
	 */
	List<Dictionary> getDictionary();
}
