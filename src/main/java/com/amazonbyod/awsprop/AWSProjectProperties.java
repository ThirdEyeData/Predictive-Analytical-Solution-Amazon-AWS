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

	static Properties prop = new Properties();
	static InputStream input = Thread.currentThread().getContextClassLoader()
			.getResourceAsStream("AwsCredentials.properties");

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
	
	/*public static void main(String args[]) throws IOException{
		
		AWSProperties aws = new AWSProperties();
		System.out.println(aws.getAccessKey());
		System.out.println(aws.getSecretKey());
		
	}*/
}
