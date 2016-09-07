package com.amazonbyod.test;

import java.io.File;
import java.io.IOException;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import com.amazonbyod.awsprop.AWSProjectProperties;

public class Test {
	
	
	public static void main(String args[]) throws IOException {
		 AWSProjectProperties awscredentials = new AWSProjectProperties();
		AWSCredentials credentials = new BasicAWSCredentials(awscredentials.getAccessKey(), awscredentials.getSecretKey());
		// Each instance of TransferManager maintains its own thread pool
		// where transfers are processed, so share an instance when possible
		TransferManager tx = new TransferManager(credentials);
		 
		// The upload and download methods return immediately, while
		// TransferManager processes the transfer in the background thread pool
		File file= new File("Incremental_weather_data.csv");
		Upload upload = tx.upload("", "sample.csv", file);
		
		 
		// While the transfer is processing, you can work with the transfer object
		while (upload.isDone() == false) {
		    System.out.println(upload.getProgress().getPercentTransferred() + "%");
		}	
		
	}
}
