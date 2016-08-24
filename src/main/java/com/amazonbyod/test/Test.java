package com.amazonbyod.test;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.amazonbyod.mysql.MySQLConnection;
import com.amazonbyod.redshift.AwsRedshiftOperations;

import au.com.bytecode.opencsv.CSVWriter;

public class Test {
	
	public void testing(){
		Sample s = new Sample();
		System.out.println(s.getSample());
	}
	
	public static void main(String args[]) throws IOException{
		 String csv = "/home/abhinandan/TE/Datasets/Project/AWS/Datasets/Mockup/Stock/data.csv";
			System.out.println("-------- MySQL JDBC Connection Testing ------------");

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Where is your MySQL JDBC Driver?");
				e.printStackTrace();
				return;
			}
            
			System.out.println("MySQL JDBC Driver Registered!");
			Connection connection = null;
			
			

			try {
				connection = DriverManager.getConnection("jdbc:mysql://54.149.197.125:3306/immersiondb","root", "root");
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("SHOW TABLES LIKE 'company_master'");
				while(rs.next()){
					System.out.println("tables name: "+rs.getString(1));
				}
				

			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
				
			}

			
		
	}
}
