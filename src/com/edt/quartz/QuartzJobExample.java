package com.edt.quartz;

import com.edt.service.GzhService;
import com.edt.service.OaService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class QuartzJobExample implements Job {
    @Resource
    private OaService oaService;
    @Resource
    private GzhService gzhService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "★★★★★★★★★★★");
        System.out.println(oaService);
        System.out.println(gzhService);
    }
}
