package com.amazonbyod.scheduler;

import java.sql.Connection;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.amazonbyod.listclass.WeatherData;
import com.amazonbyod.mockupdata.DataMockupGenerator;
import com.amazonbyod.redshift.AwsRedshiftOperations;

public class WeatherIncrementalData implements Job {
	DataMockupGenerator weather = new DataMockupGenerator();
	AwsRedshiftOperations redshift = new AwsRedshiftOperations();
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Connection conn = redshift.redShiftConnect();
		List<WeatherData> row = weather.incrementalWeatherData();
		redshift.insertWeatherData(conn, row);
		redshift.redShiftConnect();
	}
}
