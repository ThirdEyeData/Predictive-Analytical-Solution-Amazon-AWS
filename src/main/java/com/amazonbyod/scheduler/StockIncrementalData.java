package com.amazonbyod.scheduler;

import java.io.IOException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.amazonbyod.stockdata.StockDataMockup;

public class StockIncrementalData implements Job{
	StockDataMockup stockMockup = new StockDataMockup();
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			stockMockup.realtimeMockUpData("/home/abhinandan/TE/Datasets/Project/AWS/Datasets/Mockup/Stock","TED", 35, 38, 35000, 37541);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
