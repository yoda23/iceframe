package com.edt.common;

import com.edt.common.bean.Config;
import com.edt.entity.Dictionary;
import com.edt.service.DictionaryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SysInit implements ApplicationListener<ContextRefreshedEvent> {
	@Resource
	private DictionaryService dictionaryService;
	private Logger logger = LogManager.getLogger(getClass());

	/**
	 * spring容器初始化时进行加载
	 *
	 * @author 刘钢 2017/5/17 22:37
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		// 避免容器多次加载
		if (event.getApplicationContext().getParent() == null) {
			// 初始化数据字典
			List<Dictionary> listDictionary = dictionaryService.getDictionary();
			logger.info("---------------开始初始化------------------");
			for (Dictionary dictionary : listDictionary) {
				logger.info(
						dictionary.getdKey() + "->" + dictionary.getdValue());
				Config.mapConfig.put(dictionary.getdKey(),
						dictionary.getdValue());
			}
			logger.info("---------------结束初始化------------------");
        }

	}
}
