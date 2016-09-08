package com.amazonbyod.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonbyod.awsprop.AWSProjectProperties;
import com.amazonbyod.mysql.MySQLConnection;
import com.amazonbyod.redshift.AwsRedshiftOperations;
import com.amazonbyod.s3.S3Operations;
import com.amazonbyod.scheduler.StreamingMockupData;

/**
 * Servlet implementation class DashboardOperation
 */
public class DashboardOperation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final long unixTime = System.currentTimeMillis() / 1000L;
	static AWSProjectProperties awscredentials = new AWSProjectProperties();
	static S3Operations s3 = new S3Operations();
	static String bucketName="amazon-aws-immersion-project";
	String companySymbol;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardOperation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		/*response.setContentType("application/json");*/
		
		//content type must be set to text/event-stream
		  response.setContentType("text/event-stream"); 

		  //encoding must be set to UTF-8
		  response.setCharacterEncoding("UTF-8");

		
		PrintWriter out = response.getWriter();
		
		
		//Mysql Connection
		MySQLConnection mysql = new MySQLConnection();
		//AWS Credentials 
		AWSCredentials credentials = new BasicAWSCredentials(awscredentials.getAccessKey(), awscredentials.getSecretKey());
	    //S3 Client
		AmazonS3 s3client = new AmazonS3Client(credentials);
		//Redshift 
	    AwsRedshiftOperations redShift = new AwsRedshiftOperations();
	    //Date format
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		
		DashboardOperation mockup = new DashboardOperation(); 

		Calendar endcal = Calendar.getInstance();
		endcal.add(Calendar.DATE, -1);
		
		Date date = new Date();
		
		if(request.getParameter("datatype")!=null){
		String datatype=request.getParameter("datatype").toString();
		System.out.println("Hello "+datatype);
		
		if(datatype.equals("weather")){
			String weatherDataStart=buildJson("weatherdata","green","Start At:"+new Date());
			out.write("data: " +weatherDataStart + "\n\n");
			
			
			String s3folder="weatherdata/weatherdata.csv";
			s3client.setRegion(Region.getRegion(Regions.US_WEST_2));
			s3.S3Upload(s3client, bucketName+"/weatherdata", s3folder, "/home/abhinandan/TE/Datasets/Project/AWS/Datasets/Weather/weather_data_updated.csv");
			
			String S3weatherdataUpload=buildJson("s3upload","green","Start At:"+new Date());
			out.write("data: " +S3weatherdataUpload + "\n\n");
		  }else if(datatype.equals("mockup")){
			  
			  
		  }else if (datatype.equals("incremental")){
			    String Incrementaldata=buildJson("incremental","green","Start At:"+new Date());
				out.write("data: " +Incrementaldata + "\n\n");
			   // Initialized Quartz
				StreamingMockupData streaming = new StreamingMockupData(companySymbol);
				
				String IncrementaldataStart=buildJson("incremental","green","Start At:"+new Date());
				out.write("data: " +IncrementaldataStart + "\n\n");
				
				streaming.startStreaming();
				
		  }
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public String buildJson(String classname,String color,String status){
		String json = "{\"class\":\""+classname+"\",\"color\":\""+color+"\",\"status\":\""+status+"\"}";
		return json;
	}
	
   public void sendStreamingData(String classname,String color,String status) {
		
		try {
			
			String json = "{\"class\":\""+classname+"\",\"color\":\""+color+"\",\"status\":\""+status+"\"}";
			@SuppressWarnings("resource")
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost("http://localhost:8080/AmazonByod/Dashboard");
				

			StringEntity input = new StringEntity(json);
			input.setContentType("application/json");
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);

			BufferedReader br = new BufferedReader(
	                        new InputStreamReader((response.getEntity().getContent())));

			String output;
			//System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println("REST API JSON DATA: "+output);
			}

			httpClient.getConnectionManager().shutdown();

		  } catch (MalformedURLException e) {

			e.printStackTrace();
		
		  } catch (IOException e) {

			e.printStackTrace();

		  }

	}

}
