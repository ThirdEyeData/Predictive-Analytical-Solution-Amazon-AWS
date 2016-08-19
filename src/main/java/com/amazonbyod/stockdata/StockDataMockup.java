package com.amazonbyod.stockdata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fluttercode.datafactory.impl.DataFactory;

import com.amazonbyod.scheduler.StreamingMockupData;

import au.com.bytecode.opencsv.CSVWriter;

/**
 * @author abhinandan
 *
 */
public class StockDataMockup {

	/**
	 * @param directoryName
	 */
	
	static final long unixTime = System.currentTimeMillis() / 1000L;
	
	public void createDirectoryIfNeeded(String directoryName) {
		File theDir = new File(directoryName);
		// if the directory does not exist, create it
		if (!theDir.exists() && !theDir.isDirectory()) {
			theDir.mkdirs();
		}
	}

	
	public void mockupDateWise(String startDate, String endDate) {
		DataFactory df = new DataFactory();
		LocalDate start = LocalDate.parse(startDate);
		LocalDate end = LocalDate.parse(endDate);
		@SuppressWarnings("unused")
		List<LocalDate> totalDates = new ArrayList<>();
		while (!start.isAfter(end)) {
			int openvalint = df.getNumberBetween(28, 31);
			int openvalpoint = df.getNumberBetween(00, 99);

			int highvalint = df.getNumberBetween(openvalint, 31);
			int highvalpoint = df.getNumberBetween(openvalpoint, 99);

			int lowvalint = df.getNumberBetween(openvalint - 1, openvalint);
			int lowvalpoint = df.getNumberBetween(00, openvalpoint);

			int closevalint = df.getNumberBetween(28, 31);
			int closevalpoint = df.getNumberBetween(00, 99);

			float openval = Float.parseFloat(openvalint + "." + openvalpoint);
			float highval = Float.parseFloat(highvalint + "." + highvalpoint);
			float lowval = Float.parseFloat(lowvalint + "." + lowvalpoint);
			float closeval = Float.parseFloat(closevalint + "." + closevalpoint);
			start = start.plusDays(1);
			System.out.println(start + "---->" + openval + "---->" + highval + "----->" + lowval + "----->" + closeval);
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
			CSVWriter writer = new CSVWriter(new FileWriter(saveDir + "/historicalstock_"+unixTime+".csv"));
			while (!start.isAfter(end)) {
				int openvalint = df.getNumberBetween(openVal, closeVal);
				int openvalpoint = df.getNumberBetween(00, 99);

				int highvalint = df.getNumberBetween(openVal, closeVal);
				int highvalpoint = df.getNumberBetween(00, 99);

				int lowvalint = df.getNumberBetween(openVal, closeVal);
				int lowvalpoint = df.getNumberBetween(00, 99);

				int closevalint = df.getNumberBetween(openVal, closeVal);
				int closevalpoint = df.getNumberBetween(00, 99);

				int stockVolume = df.getNumberBetween(openStockVol, closeStockVol);

				float openStockVal = Float.parseFloat(openvalint + "." + openvalpoint);
				float highStockVal = Float.parseFloat(highvalint + "." + highvalpoint);
				float lowStockVal = Float.parseFloat(lowvalint + "." + lowvalpoint);
				float closeStockVal = Float.parseFloat(closevalint + "." + closevalpoint);
				if (highStockVal < lowStockVal) {
					float temp = highStockVal;
					highStockVal = lowStockVal;
					lowStockVal = temp;
				}
                
				
				String csvRow = companySymbol + "," + start.toString() + "," + openStockVal + "," + highStockVal + ","
						+ lowStockVal + "," + closeStockVal + "," + stockVolume + "," + "0" + "," + "1" + ","
						+ openStockVal + "," + highStockVal + "," + lowStockVal + "," + closeStockVal + ","
						+ stockVolume;
				writer.writeNext(csvRow.split(","));
				writer.flush();
				start = start.plusDays(1);
				
			}
			    writer.close();

		} else {
			System.out.println("Please Enter Saving Directory Path");
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
	public void realtimeMockUpData(String saveDir, String companySymbol, int openVal,
			int closeVal, int openStockVol, int closeStockVol) throws IOException {
		if (saveDir != "") {
			createDirectoryIfNeeded(saveDir);
			File file =new File("realtime"+unixTime+".csv");
			if(!file.exists()){
    			file.createNewFile();
    		}
			
			FileWriter fileWritter = new FileWriter(saveDir+"/"+file.getName(),true);
	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
	        
	        DataFactory df = new DataFactory();
	        int openvalint = df.getNumberBetween(openVal, closeVal);
			int openvalpoint = df.getNumberBetween(00, 99);

			int highvalint = df.getNumberBetween(openVal, closeVal);
			int highvalpoint = df.getNumberBetween(00, 99);

			int lowvalint = df.getNumberBetween(openVal, closeVal);
			int lowvalpoint = df.getNumberBetween(00, 99);

			int closevalint = df.getNumberBetween(openVal, closeVal);
			int closevalpoint = df.getNumberBetween(00, 99);

			int stockVolume = df.getNumberBetween(openStockVol, closeStockVol);

			float openStockVal = Float.parseFloat(openvalint + "." + openvalpoint);
			float highStockVal = Float.parseFloat(highvalint + "." + highvalpoint);
			float lowStockVal = Float.parseFloat(lowvalint + "." + lowvalpoint);
			float closeStockVal = Float.parseFloat(closevalint + "." + closevalpoint);
			if (highStockVal < lowStockVal) {
				float temp = highStockVal;
				highStockVal = lowStockVal;
				lowStockVal = temp;
			}
            
			
			String csvRow = companySymbol + "," + new Date().toString() + "," + openStockVal + "," + highStockVal + ","
					+ lowStockVal + "," + closeStockVal + "," + stockVolume + "," + "0" + "," + "1" + ","
					+ openStockVal + "," + highStockVal + "," + lowStockVal + "," + closeStockVal + ","+
					+ stockVolume+"\n";
			
			System.out.println(csvRow);
	        bufferWritter.write(csvRow);
	        bufferWritter.close();
			
		}

	}
	
	
	

	public static void main(String args[]) throws IOException {
		StreamingMockupData streaming = new StreamingMockupData();
		String startDate = "2015-04-01";
		String endDate = "2016-06-20";
		StockDataMockup mockup = new StockDataMockup();
		mockup.minMockUpData("/home/abhinandan/TE/Datasets/Project/AWS/Datasets/Mockup/Stock", startDate, endDate,
				"TED", 35, 38, 35000, 37541);
		//mockup.realtimeMockUpData("/home/abhinandan/TE/Datasets/Project/AWS/Datasets/Mockup/Stock","TED", 35, 38, 35000, 37541);
		streaming.startStreaming();
	}

}
