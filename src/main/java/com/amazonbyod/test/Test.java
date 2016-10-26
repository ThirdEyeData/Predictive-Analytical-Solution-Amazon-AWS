package com.amazonbyod.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import com.amazonbyod.awsprop.AWSProjectProperties;

public class Test {
	
	
/*	public static void main(String args[]) throws IOException {
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
	
	}*/
	
	
	public static void main(String args[]){
		//File file= new File("Incremental_weather_data.csv");
		String csvFile = "Incremental_weather_data.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);

                System.out.println("Country [code= " + country[0] + " , name=" + country[1] + "]");

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
}
