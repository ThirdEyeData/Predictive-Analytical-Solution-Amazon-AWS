package com.amazonbyod.stockdata;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fluttercode.datafactory.impl.DataFactory;

import au.com.bytecode.opencsv.CSVWriter;

/**
 * @author abhinandan
 *
 */
public class StockDataMockup {
	
	public void createDirectoryIfNeeded(String directoryName) {

		File theDir = new File(directoryName);

		// if the directory does not exist, create it
		if (!theDir.exists() && !theDir.isDirectory()) {
			theDir.mkdirs();
		}

	}
	
	public void mockupDateWise(String startDate,String endDate){
		DataFactory df = new DataFactory();
		LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<LocalDate> totalDates = new ArrayList<>();
        while (!start.isAfter(end)) {
           int openvalint=df.getNumberBetween(28,31);
            int openvalpoint=df.getNumberBetween(00,99);
            
            int highvalint=df.getNumberBetween(openvalint,31);
            int highvalpoint=df.getNumberBetween(openvalpoint,99);
            
            int lowvalint=df.getNumberBetween(openvalint-1,openvalint);
            int lowvalpoint=df.getNumberBetween(00,openvalpoint);
            
            int closevalint=df.getNumberBetween(28,31);
            int closevalpoint=df.getNumberBetween(00,99);
            
            float openval=Float.parseFloat(openvalint+"."+openvalpoint);
            float highval=Float.parseFloat(highvalint+"."+highvalpoint);
            float lowval=Float.parseFloat(lowvalint+"."+lowvalpoint);
            float closeval=Float.parseFloat(closevalint+"."+closevalpoint);
            start = start.plusDays(1);
            System.out.println(start+"---->"+openval+"---->"+highval+"----->"+lowval+"----->"+closeval);
        	start = start.plusDays(1);
            System.out.println(start);
        }
	}
	
	
	/**
	 * @param startDate
	 * @param endDate
	 * @param companySymbol
	 * @param openVal
	 * @param closeVal
	 * @param stockVol
	 * @throws IOException 
	 */
	public void minMockUpData(String saveDir, String startDate, String endDate, String companySymbol, int openVal,
			int closeVal, int openStockVol, int closeStockVol) throws IOException {
		DataFactory df = new DataFactory();
		LocalDate start = LocalDate.parse(startDate);
		LocalDate end = LocalDate.parse(endDate);
		if (saveDir != "") {
			createDirectoryIfNeeded(saveDir);
			CSVWriter writer = new CSVWriter(new FileWriter(saveDir+"/sample.csv"));
			while (!start.isAfter(end)) {
				int openvalint = df.getNumberBetween(openVal, closeVal);
				int openvalpoint = df.getNumberBetween(00, 99);
				int closevalint = df.getNumberBetween(openVal, closeVal);
				int closevalpoint = df.getNumberBetween(00, 99);
				int stockVolume = df.getNumberBetween(openStockVol, closeStockVol);

				float openStockVal = Float.parseFloat(openvalint + "." + openvalpoint);
				float closeStockVal = Float.parseFloat(closevalint + "." + closevalpoint);
				String csvRow = companySymbol + "," + start.toString() + "," + openStockVal + "," + closeStockVal + ","
						+ stockVolume;
				writer.writeNext(csvRow.split(","));
				start = start.plusDays(1);
			}

		} else {
			System.out.println("Please Enter Saving Directory Path");
		}

	}
	public static void main(String args[]) throws IOException{
		
		/*DataFactory df = new DataFactory();
		Date minDate = df.getDate(2000, 1, 1);
        Date maxDate = df.getDate(2000, 1, 20);
        for (int i = 0; i < 100; i++) {          
            String name = df.getFirstName() + " "+ df.getLastName();
            System.out.println(name);
        }
        
        
        for (int i = 0; i < 10; i++) {
            Date start = df.getDateBetween(minDate, maxDate);
            System.out.println("Date = "+start);
        }
        
        String s = "2014-05-01";
        String e = "2014-05-10";
        LocalDate start = LocalDate.parse(s);
        LocalDate end = LocalDate.parse(e);
        List<LocalDate> totalDates = new ArrayList<>();
        while (!start.isAfter(end)) {
            //totalDates.add(start);
            start = start.plusDays(1);
            System.out.println(start);
            
        }*/
		
		String startDate="2015-04-01";
		String endDate="2016-06-20";
		
		StockDataMockup mockup = new StockDataMockup();
		/*System.out.println("Date"+"--------->"+"OpenVal"+"--->"+"Highval"+"--->"+"Lowval"+"--->"+"Closeval");
		mockup.mockupDateWise(startDate, endDate);*/
		
		mockup.minMockUpData("/home/abhinandan/TE/Datasets/Project/AWS/Datasets/Mockup/Stock",startDate, endDate, "TED", 35, 38, 35000, 37541);
	}

}
