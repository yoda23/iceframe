package com.edt.common;

import com.edt.quartz.QuartzJobExample;
import com.edt.service.component.quartz.QuartzManager;
import com.iceutils.json.IceJsonStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Scheduler;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class QuartzInit implements ApplicationListener<ContextRefreshedEvent> {
    @Resource
    private Scheduler sche;

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
			String job_name = "jjjjjjj";
			QuartzManager.addJob(sche, job_name, QuartzJobExample.class, "0 57 0 * * ?");
            logger.info(IceJsonStringUtils.toJsonString(sche));
        }

	}
}
