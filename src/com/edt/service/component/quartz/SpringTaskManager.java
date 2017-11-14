package com.edt.service.component.quartz;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class SpringTaskManager /*implements SchedulingConfigurer*/ {

//    private Logger logger = Logger.getLogger(this.getClass());
//
//    private ScheduledTaskRegistrar registrar;
//    private Map<String,String> taskDates ;
//    @Resource
//    private AutoSendOrderService autoSendOrderService;
//    @Resource
//    private PushSettingService pushSettingService;
//
//
//
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
//        if(registrar == null){
//            registrar = scheduledTaskRegistrar;
//        }
//        registrar.setScheduler( Executors.newScheduledThreadPool(10));
//        List<PushSetting> pushSettings = pushSettingService.selectAll();
//        taskDates = new HashMap<>();
//        if(!pushSettings.isEmpty()){
//            for (final PushSetting pushSetting: pushSettings) {
//                if(pushSetting.getAutoSendDate() != null){
//                    taskDates.put(pushSetting.getCitycode()+","+pushSetting.getPlatformid(),getCron(pushSetting.getAutoSendDate()));
//                    registrar.addTriggerTask(new Runnable() {
//                        @Override
//                        public void run() {
//                            // 任务逻辑
//                            autoSendOrderService.send(pushSetting.getCitycode(),pushSetting.getPlatformid());
//                        }
//                    }, new Trigger() {
//                        @Override
//                        public Date nextExecutionTime(TriggerContext triggerContext) {
//                            String key = pushSetting.getCitycode()+","+pushSetting.getPlatformid();
//                            // 任务触发，可修改任务的执行周期
//                            CronTrigger trigger = new CronTrigger(taskDates.get(key));
//                            logger.info("newxtcron-------------"+taskDates.get(key));
//                            Date nextExec = trigger.nextExecutionTime(triggerContext);
//                            logger.info("------------------next------>"+nextExec);
//                            return nextExec;
//                        }
//                    });
//                }
//            }
//        }
//        logger.info(IceJsonStringUtils.toJsonString(taskDates));
//    }
//
//    public void updateTaskExecutionTime(PushSetting pushSetting){
//        taskDates.put(pushSetting.getCitycode()+","+pushSetting.getPlatformid(),getCron(pushSetting.getAutoSendDate()));
//        logger.info("update-----------------------"+taskDates.get(pushSetting.getCitycode()+","+pushSetting.getPlatformid()));
//    }
//
//    public void addTask(final PushSetting pushSetting){
//        if(registrar != null){
//            registrar.addTriggerTask(new Runnable() {
//                @Override
//                public void run() {
//                    // 任务逻辑
//                    autoSendOrderService.send(pushSetting.getCitycode(),pushSetting.getPlatformid());
//                }
//            }, new Trigger() {
//                @Override
//                public Date nextExecutionTime(TriggerContext triggerContext) {
//                    // 任务触发，可修改任务的执行周期
//                    CronTrigger trigger = new CronTrigger(getCron(pushSetting.getAutoSendDate()));
//                    Date nextExec = trigger.nextExecutionTime(triggerContext);
//                    return nextExec;
//                }
//            });
//        }else{
//            logger.info("添加任务失败");
//        }
//
//    }
//
//    private String getCron(Date date){
//        int hor = IceDateFormatUtils.getHourOfDay(date);
//        int min = IceDateFormatUtils.getMinuteOfHour(date);
//        int sec = IceDateFormatUtils.getSecondOfMinute(date);
//        return sec+" "+min+" "+hor+" * * ?";
//    }
//
//    private String getCron(String date){
//        if(date == null){
//            throw new RuntimeException("时间为空");
//        }
//        String[] strings = date.split(":");
//        return "00 "+strings[1]+" "+strings[0]+" * * ?";
//    }
}
