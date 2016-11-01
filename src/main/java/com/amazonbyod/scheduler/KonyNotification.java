package com.amazonbyod.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.amazonbyod.kony.KonyMobilePushNotification;

public class KonyNotification implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		 KonyMobilePushNotification konyn = new KonyMobilePushNotification();
		 konyn.getStorm();
	}

}
