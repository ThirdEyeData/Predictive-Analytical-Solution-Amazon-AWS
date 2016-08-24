package com.amazonbyod.test;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.amazonbyod.redshift.AwsRedshiftOperations;

import au.com.bytecode.opencsv.CSVWriter;

public class Test {
	
	public void testing(){
		Sample s = new Sample();
		System.out.println(s.getSample());
	}
	
	public static void main(String args[]) throws IOException{
		 String csv = "/home/abhinandan/TE/Datasets/Project/AWS/Datasets/Mockup/Stock/data.csv";
		 AwsRedshiftOperations redshift = new AwsRedshiftOperations();
		 Connection conn = redshift.redShiftConnect();
		 redshift.loadDatafromS3(conn, "stock_data_test", "", "");
		 
	      /*CSVWriter writer = new CSVWriter(new FileWriter(csv),CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER);
	        
	      //Create record
	      String [] record = "4,David,Miller,Australia,30".split(",");
	      //Write the record to file
	      writer.writeNext(record);
	        
	      //close the writer
	      writer.close();*/
		 
		 String[] csvtest=csv.split("/");
		 System.out.println(csvtest[csvtest.length-1]);
		
	}
}
