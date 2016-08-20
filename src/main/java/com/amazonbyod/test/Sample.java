package com.amazonbyod.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Sample {
	
	public static void main(String args[]){
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		System.out.println(df.format(date));
	}

}
