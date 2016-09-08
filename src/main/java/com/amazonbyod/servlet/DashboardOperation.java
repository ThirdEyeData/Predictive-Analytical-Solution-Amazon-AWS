package com.amazonbyod.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.amazonbyod.awsprop.AWSProjectProperties;
import com.amazonbyod.s3.S3Operations;

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
		
		//response.setContentType("application/json");
		 response.setContentType("text/event-stream");
		 response.setHeader("Cache-Control", "no-cache");

		
		PrintWriter out = response.getWriter();
		String json = buildJson("mysql","green","3");
		/*if(request.getParameter("datatype").toString()!="" || request.getParameter("datatype").toString()!=null){
		String datatype=request.getParameter("datatype").toString();
		System.out.println("Hello "+datatype);
		if(datatype.equals("weather")){
			System.out.println("Got your request");
			sendStreamingData("mysql","green","3");
			json=buildJson("mysql","green","3");
		}
		}*/
		
		out.println(json);
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
