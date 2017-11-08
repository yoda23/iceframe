package com.edt.quartz;

import com.edt.service.RedisService;
import com.edt.websocket.handler.DemoWsHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/*
 【秒】   【分】  【时】   【日】  【月】   【周】  【年】
 "*"可用在所有字段中，表示对应时间域的每一个时刻,在月份表示每一个月；在天表示每一天
 "?"该字符只在日期和星期字段中使用，它通常指定为“无意义的值”，相当于点位符
 "-"表达一个范围，如在小时字段中使用“10-12”，则表示从10到12点，即10,11,12
 ","表达一个列表值，如在星期字段中使用“MON,WED,FRI”，则表示星期一，星期三和星期五；
 "/"字符用来指定数值的增量,0/15表示从第0分钟开始，没15分钟；3/20表示从第3分钟开始，每20分钟
 "0 0 12 * * ?"    每天中午十二点触发 
 "0 15 10 ? * *"    每天早上10：15触发 
 "0 15 10 * * ?"    每天早上10：15触发 
 "0 15 10 * * ? *"    每天早上10：15触发 
 "0 15 10 * * ? 2005"    2005年的每天早上10：15触发 
 "0 * 14 * * ?"    每天从下午2点开始到2点59分每分钟一次触发 
 "0 0/5 14 * * ?"    每天从下午2点开始到2：55分结束每5分钟一次触发 
 "0 0/5 14,18 * * ?"    每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发 
 "0 0-5 14 * * ?"    每天14:00至14:05每分钟一次触发 
 "0 10,44 14 ? 3 WED"    三月的每周三的14：10和14：44触发 
 "0 15 10 ? * MON-FRI"    每个周一、周二、周三、周四、周五的10：15触发
 */
@Component
public class Quartz {
	private Logger logger = LogManager.getLogger(Quartz.class);
	@Resource
	private RedisService redisService;
	@Resource(name = "demoWSHandler")
	private DemoWsHandler handler;
	public void quartz() {
		int i = 0;
		while(redisService.opsValue_get("key",Boolean.class)){
			logger.info("我是定时器"+(++i));
		}
	}
}
