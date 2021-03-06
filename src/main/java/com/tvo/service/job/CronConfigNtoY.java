package com.tvo.service.job;

import com.tvo.service.ConfigCronjobService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CronConfigNtoY extends QuartzJobBean implements InterruptableJob {

    private static final Logger logger = LoggerFactory.getLogger(CronConfigNtoY.class);

    private static final String VALUE_Y = "Y";

    @Autowired
    private ConfigCronjobService configCronjobService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey key = jobExecutionContext.getJobDetail().getKey();
        logger.info("Cron Job started with key :" + key.getName() + ", Group :" + key.getGroup() + " , Thread Name :" + Thread.currentThread().getName() + " ,Time now :" + new Date());

        configCronjobService.saveOrUpdateConfigValue(VALUE_Y);
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {
    }
}
