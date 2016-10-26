package com.amazonbyod.awsprop;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author abhinandan
 *
 */

public class AWSProjectProperties {

	
	private String accessKey = "accessKey";
	private String secretKey = "secretKey";
	private String bucketName = "bucketName";
	private String mysql_dbname = "mysql_dbname";
	private String mysql_username = "mysql_username";
	private String mysql_password = "mysql_password";
	private String mysql_JDBC_DRIVER = "mysql_JDBC_DRIVER";
	private String mysql_DB_URL = "mysql_DB_URL";
	private String redshift_jdbc_url = "redshift_jdbc_url";
	private String master_username = "master_username";
	private String master_password = "master_password";
	private String stockDatapath = "stockDatapath";
	private String cloudbeam_slave_url = "cloudbeam_slave_url";
	private String cloudbeam_taskname = "cloudbeam_taskname";
	private String kony_url = "kony_url";
	private String weatherDatapath = "weatherDatapath";
	private String prediction_path = "prediction_path";


	

	

	static Properties prop = new Properties();
	static InputStream input = Thread.currentThread().getContextClassLoader()
			.getResourceAsStream("ProjectConf.properties");

	/**
	 * @return the accessKey
	 * @throws IOException
	 */
	public String getAccessKey() throws IOException {
		prop.load(input);
		return prop.getProperty(accessKey);
	}

	/**
	 * @return the secretKey
	 * @throws IOException
	 */
	public String getSecretKey() throws IOException {
		prop.load(input);
		return prop.getProperty(secretKey);
	}

	/**
	 * @return
	 * @throws IOException
	 */
	public String getMysql_dbname() throws IOException {
		prop.load(input);
		return prop.getProperty(mysql_dbname);
	}

	/**
	 * @return
	 * @throws IOException
	 */
	public String getMysql_username() throws IOException {
		prop.load(input);
		return prop.getProperty(mysql_username);
	}

	/**
	 * @return
	 * @throws IOException
	 */
	public String getMysql_password() throws IOException {
		prop.load(input);
		return prop.getProperty(mysql_password);
	}
	
	/**
	 * @return
	 * @throws IOException 
	 */
	public String getMysql_JDBC_DRIVER() throws IOException {
		prop.load(input);
		return prop.getProperty(mysql_JDBC_DRIVER);
	}

	/**
	 * @return
	 * @throws IOException
	 */
	public String getMysql_DB_URL() throws IOException {
		prop.load(input);
		return prop.getProperty(mysql_DB_URL);
	}
	

	/**
	 * @return
	 * @throws IOException
	 */
	public String getRedshift_jdbc_url() throws IOException {
		prop.load(input);
		return prop.getProperty(redshift_jdbc_url);
	}

	
	/**
	 * @return
	 * @throws IOException
	 */
	public String getMaster_username() throws IOException {
		prop.load(input);
		return prop.getProperty(master_username);
	}
	
	/**
	 * @return
	 * @throws IOException
	 */	
	public String getMaster_password() throws IOException {
		prop.load(input);
		return prop.getProperty(master_password);
	}
	
	/**
	 * @return
	 * @throws IOException
	 */
	public String getStockDatapath() throws IOException {
		prop.load(input);
		return prop.getProperty(stockDatapath);
	}
    
	/**
	 * @return
	 * @throws IOException
	 */
	public String getCloudbeam_slave_url() throws IOException {
		prop.load(input);
		return prop.getProperty(cloudbeam_slave_url);
	}
	
	/**
	 * @return
	 * @throws IOException
	 */
	public String getBucketName() throws IOException {
		prop.load(input);
		return prop.getProperty(bucketName);
	}
	
	/**
	 * @return
	 * @throws IOException
	 */
	public String getCloudbeam_taskname() throws IOException {
		prop.load(input);
		return prop.getProperty(cloudbeam_taskname);
	}
    
	public String getKony_url() throws IOException {
		prop.load(input);
		return prop.getProperty(kony_url);
	}
	
	public String getWeatherDatapath() throws IOException {
		prop.load(input);
		return prop.getProperty(weatherDatapath);
	}
	
	public String getPrediction_path() throws IOException {
		prop.load(input);
		return prop.getProperty(prediction_path);
		
	}
  
	/*
	 * Setter Part Starts Here
	 * 
	 */
	

	public void setPrediction_path(String prediction_path) throws IOException {
		prop.load(input);
		prop.setProperty("prediction_path",prediction_path);
	}
	
	public void setWeatherDatapath(String weatherDatapath) throws IOException {
		prop.load(input);
		prop.setProperty("weatherDatapath",weatherDatapath);
	}
	
	public void setKony_url(String kony_url) throws IOException {
		prop.load(input);
		prop.setProperty("kony_url",kony_url);
	}
	
	public void setAccessKey(String accessKey) throws IOException {
		prop.load(input);
		prop.setProperty("accessKey", accessKey);
	}
	public void setSecretKey(String secretKey) throws IOException {
		prop.load(input);
		prop.setProperty("secretKey", secretKey);
	}
	public void setBucketName(String bucketName) throws IOException {
		prop.load(input);
		prop.setProperty("bucketName", bucketName);
	}
	public void setMysql_dbname(String mysql_dbname) throws IOException {
		prop.load(input);
		prop.setProperty("mysql_dbname", mysql_dbname);
	}
	public void setMysql_username(String mysql_username) throws IOException {
		prop.load(input);
		prop.setProperty("mysql_username", mysql_username);
	}
	public void setMysql_password(String mysql_password) throws IOException {
		prop.load(input);
		prop.setProperty("mysql_password", mysql_password);
	}
	public void setMysql_JDBC_DRIVER(String mysql_JDBC_DRIVER) throws IOException {
		prop.load(input);
		prop.setProperty("mysql_JDBC_DRIVER", mysql_JDBC_DRIVER);
	}
	public void setMysql_DB_URL(String mysql_DB_URL) throws IOException {
		prop.load(input);
		prop.setProperty("mysql_DB_URL", mysql_DB_URL);
	}
	public void setRedshift_jdbc_url(String redshift_jdbc_url) throws IOException {
		prop.load(input);
		prop.setProperty("redshift_jdbc_url", redshift_jdbc_url);
	}
	public void setMaster_username(String master_username) throws IOException {
		prop.load(input);
		prop.setProperty("master_username", master_username);
	}
	public void setMaster_password(String master_password) throws IOException {
		prop.load(input);
		prop.setProperty("master_password", master_password);
	}
	public void setStockDatapath(String stockDatapath) throws IOException {
		prop.load(input);
		prop.setProperty("stockDatapath", stockDatapath);
	}
	public void setCloudbeam_slave_url(String cloudbeam_slave_url) throws IOException {
		prop.load(input);
		prop.setProperty("cloudbeam_slave_url", cloudbeam_slave_url);
	}
	
	public void setCloudbeam_taskname(String cloudbeam_taskname) throws IOException {
		prop.load(input);
		prop.setProperty("cloudbeam_taskname", cloudbeam_taskname);
	}

	/*
	 * public static void main(String args[]) throws IOException{
	 * 
	 * AWSProperties aws = new AWSProperties();
	 * System.out.println(aws.getAccessKey());
	 * System.out.println(aws.getSecretKey());
	 * 
	 * }
	 */
}
