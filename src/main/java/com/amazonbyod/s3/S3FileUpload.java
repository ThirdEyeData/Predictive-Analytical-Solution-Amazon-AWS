package com.amazonbyod.s3;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.StringUtils;
import com.amazonbyod.awsprop.AWSProjectProperties;

public class S3FileUpload {
	
	 static AWSProjectProperties awscredentials = new AWSProjectProperties();
	 static S3Operations s3 = new S3Operations();
	 static final String bucketName = "aws-marketplace-immersion-project";
	public static void main(String args[]) throws IOException{
		
	    AWSCredentials credentials = new BasicAWSCredentials(awscredentials.getAccessKey(), awscredentials.getSecretKey());
	    AmazonS3 s3client = new AmazonS3Client(credentials);
	    //Step -1 Create Bucket
	    s3.createBucket(s3client, bucketName);
	    //Step -2 List of Buckets
	    List<Bucket> buckets = s3.listofBuckets(s3client);
	    for (Bucket bucket : buckets) {
	            System.out.println(bucket.getName() + "\t ----------------->" +
	                    StringUtils.fromDate(bucket.getCreationDate()));
	    }
	    //Step- 3 Create Folder inside Bucket
	    s3.createFolder(bucketName, "/stock", s3client);
	    //s3client.putObject(new PutObjectRequest(bucketName, "/applstock.csv", 
	    		//new File("/home/abhinandan/TE/Datasets/Project/awsproject/WIKI-FB.csv")));
	    //Step -4 List of objects
	    s3.listofObjects(s3client, bucketName);
	    

	}

}
