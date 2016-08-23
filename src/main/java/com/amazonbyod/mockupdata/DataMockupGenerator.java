package com.amazonbyod.mockupdata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.fluttercode.datafactory.impl.DataFactory;
import org.joda.time.DateTime;

import com.amazonbyod.listclass.CompanyProducts;
import com.amazonbyod.listclass.CompanyProfile;
import com.amazonbyod.listclass.Companyannouncements;
import com.amazonbyod.listclass.StockData;
import com.amazonbyod.mysql.MySQLConnection;
import com.amazonbyod.scheduler.StreamingMockupData;

import au.com.bytecode.opencsv.CSVWriter;

/**
 * @author abhinandan
 *
 */
public class DataMockupGenerator {

	static final long unixTime = System.currentTimeMillis() / 1000L;

	/**
	 * @param directoryName
	 */

	public void createDirectoryIfNeeded(String directoryName) {
		File theDir = new File(directoryName);
		// if the directory does not exist, create it
		if (!theDir.exists() && !theDir.isDirectory()) {
			theDir.mkdirs();
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
		DateFormat dformater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
		DateFormat tformater = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
		if (saveDir != "") {
			createDirectoryIfNeeded(saveDir);
			CSVWriter writer = new CSVWriter(new FileWriter(saveDir + "/historicalstock_" + unixTime + ".csv"));
			while (!start.isAfter(end)) {

				try {
					Date startTime = dformater.parse(start.toString() + " 06:00:00");
					Date endTime = dformater.parse(start.toString() + " 14:00:00");
					DateTime dateTime = new DateTime(startTime);
					int seconds = (int) ((endTime.getTime()-startTime.getTime())/1000);
					
					for(int i=5;i<=seconds;i+=5){

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
                    
					
					
					String csvRow = companySymbol + "," + start.toString() +","+tformater.format(dateTime.plusSeconds(i).toDate())+ "," + openStockVal + "," + highStockVal
							+ "," + lowStockVal + "," + closeStockVal + "," + stockVolume + "," + "0" + "," + "1" + ","
							+ openStockVal + "," + highStockVal + "," + lowStockVal + "," + closeStockVal + ","
							+ stockVolume;
					writer.writeNext(csvRow.split(","));
					writer.flush();
					}
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
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
	public void realtimeMockUpData(String saveDir, String companySymbol, int openVal, int closeVal, int openStockVol,
			int closeStockVol) throws IOException {
		DateFormat tformater = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
		if (saveDir != "") {
			createDirectoryIfNeeded(saveDir);
			File file = new File("realtime" + unixTime + ".csv");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fileWritter = new FileWriter(saveDir + "/" + file.getName(), true);
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

			Date date = new Date();
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

			String csvRow = companySymbol + "," + dateformat.format(date) + "," +tformater.format(date) + "," + openStockVal + "," + highStockVal
					+ "," + lowStockVal + "," + closeStockVal + "," + stockVolume + "," + "0" + "," + "1" + ","
					+ openStockVal + "," + highStockVal + "," + lowStockVal + "," + closeStockVal + "," + +stockVolume
					+ "\n";

			System.out.println(csvRow);
			bufferWritter.write(csvRow);
			bufferWritter.close();
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
	public List<StockData> realtimeMockUpDataRedShift(String companySymbol, int openVal, int closeVal, int openStockVol,
			int closeStockVol) throws IOException {
		Date date = new Date();
		DateFormat tformater = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
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

		String csvRow = companySymbol + "," + dateformat.format(date) + "," + tformater.format(date) + ","
				+ openStockVal + "," + highStockVal + "," + lowStockVal + "," + closeStockVal + "," + stockVolume + ","
				+ "0" + "," + "1" + "," + openStockVal + "," + highStockVal + "," + lowStockVal + "," + closeStockVal
				+ "," + +stockVolume + "\n";
		
		List<StockData> stockrow=new ArrayList<StockData>();
		try {
			StockData stock = new StockData(companySymbol, dateformat.parse(dateformat.format(date)),
					tformater.parse(tformater.format(date)), openStockVal, highStockVal, lowStockVal, closeStockVal,
					stockVolume, 0, 1, openStockVal, highStockVal, lowStockVal,
					closeStockVal, stockVolume);
			stockrow.add(stock);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stockrow;

	}


	/**
	 * @param stationCode
	 * @param stationName
	 * @param lat
	 * @param lng
	 * @param tmaxOpen
	 * @param tmaxClose
	 * @param tminOpen
	 * @param tminClose
	 * @param avgWindSpeedOpen
	 * @param avgWindSpeedclose
	 * @param rainfallOpen
	 * @param rainfallClose
	 */
	public void realtimeWeatherData(String stationCode, String stationName, String lat, String lng, int tmaxOpen,
			int tmaxClose, int tminOpen, int tminClose, int avgWindSpeedOpen, int avgWindSpeedclose, int rainfallOpen,
			int rainfallClose) {

		DataFactory df = new DataFactory();

		int tmax = df.getNumberBetween(tmaxOpen, tmaxClose);
		int tmin = df.getNumberBetween(tminOpen, tminClose);

		int winSpeed = df.getNumberBetween(avgWindSpeedOpen, avgWindSpeedclose);
		int winSpeedpoint = df.getNumberBetween(00, 99);

		int rainfall = df.getNumberBetween(rainfallOpen, rainfallClose);
		int rainfallpoint = df.getNumberBetween(00, 99);

		float avgWindSpeed = Float.parseFloat(winSpeed + "." + winSpeedpoint);
		float avgRainFall = Float.parseFloat(rainfall + "." + rainfallpoint);

	}
	
	
	public List<CompanyProfile> createCompanyProfile(){
		
		DataFactory df = new DataFactory();
		
		String companyId=df.getNumberText(5);
		String ComapanyName=df.getBusinessName();
		String companySymbol=df.getRandomChars(4);
		String companyAddress=df.getAddress();
		Date company_foundedon=df.getBirthDate();
		String company_ceo=df.getFirstName()+" "+df.getLastName();
		String company_assets=df.getNumberText(8);
		String company_revenue=df.getNumberText(8);
		
		CompanyProfile cp = new CompanyProfile(companyId, ComapanyName, companySymbol,  companyAddress,
				 company_foundedon,  company_ceo,  company_assets,  company_revenue);
        List<CompanyProfile> list = new ArrayList<CompanyProfile>();
        list.add(cp);
		return list;
	}
	
	
	public List<CompanyProducts> createCompanyProduct(String companyId){
		
		DataFactory df = new DataFactory();
		
		String productId=df.getNumberText(4);
		String companyID=companyId;
		String product_name=df.getRandomText(10);
		String product_description=df.getRandomText(100);
		String product_type=df.getRandomChars(10);
		Date product_initaldate=df.getBirthDate();
		int marketvol=df.getNumberBetween(10, 40);
		float lat=37.6197f;
		float lng=-122.365f;
		String product_loc="SFO";
		
		CompanyProducts cp =new CompanyProducts(productId, companyID, product_name, product_description, product_type, product_initaldate, marketvol, lat, lng, product_loc);
		List<CompanyProducts> list = new ArrayList<CompanyProducts>();
		list.add(cp);
		
		return list;
	}
	
	public List<Companyannouncements> createCompanyannouncements(String companyId){
		
		DataFactory df = new DataFactory();
		
		String announcementId=df.getNumberText(5);
		String companyID=companyId;
		Date announcementDate=df.getBirthDate();
		String announcemnType=df.getRandomText(6);
		String announcemnBy=df.getFirstName()+" "+df.getLastName();
		String announcemnFrom="Press Release";
		String documentDoc="";
		
		Companyannouncements ca = new Companyannouncements(announcementId, companyID, announcementDate, announcemnType, announcemnBy, announcemnFrom, documentDoc);
		List<Companyannouncements> list = new ArrayList<Companyannouncements>();
		list.add(ca);
		
		return list;
	}

	public static void main(String args[]) throws IOException {
		StreamingMockupData streaming = new StreamingMockupData();
		MySQLConnection mysql = new MySQLConnection();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

		Calendar endcal = Calendar.getInstance();
		endcal.add(Calendar.DATE, -1);
        
		/*3 Years Previous date*/
		/*Calendar startcal = Calendar.getInstance();
		startcal.add(Calendar.YEAR, -3);*/
		
		Calendar startcal = Calendar.getInstance();
		startcal.add(Calendar.MONTH, -6);

		String startDate = df.format(startcal.getTime());
		String endDate = df.format(endcal.getTime());

		 DataMockupGenerator mockup = new DataMockupGenerator();
		 //Generate Company Profile
		 List<CompanyProfile> cplist=mockup.createCompanyProfile();
		 String companyID=cplist.get(0).getCompanyId();
		 //Generate Company Products
		 List<CompanyProducts> cproList = mockup.createCompanyProduct(companyID);
		//Generate Company announcement
		 List<Companyannouncements> calist = mockup.createCompanyannouncements(companyID);
		 
		 //Mysql Connection
		 Connection conn = mysql.mysqlConnect();
		 //Insert Data Into Mysql Table
		 mysql.insertDataCompanyMaster(conn, cplist);
		 mysql.insertDataCompanyAnnouncement(conn, calist);
		 mysql.insertDataCompanyProduct(conn, cproList);
		 
		 mockup.minMockUpData("/home/abhinandan/TE/Datasets/Project/AWS/Datasets/Mockup/Stock",
		 startDate, endDate,
		 companyID, 35, 42, 35000, 37541);
		// mockup.realtimeMockUpData("/home/abhinandan/TE/Datasets/Project/AWS/Datasets/Mockup/Stock","TED",
		// 35, 38, 35000, 37541);
		// streaming.startStreaming();
		 
	}

}
