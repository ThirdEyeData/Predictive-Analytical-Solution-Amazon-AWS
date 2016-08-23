package com.amazonbyod.awsprop;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author abhinandan
 *
 */
public class AWSProjectProperties {

	private final String accessKey = "accessKey";
	private final String secretKey = "secretKey";
	private final String mysql_dbname = "mysql_dbname";
	private final String mysql_username = "mysql_username";
	private final String mysql_password = "mysql_password";
	private final String mysql_JDBC_DRIVER = "mysql_JDBC_DRIVER";
	private final String mysql_DB_URL = "mysql_DB_URL";

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
