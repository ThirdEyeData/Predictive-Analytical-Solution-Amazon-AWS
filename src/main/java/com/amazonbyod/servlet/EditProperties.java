package com.amazonbyod.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.amazonbyod.awsprop.AWSProjectProperties;

/**
 * Servlet implementation class EditProperties
 */
public class EditProperties extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AWSProjectProperties prop = new AWSProjectProperties();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProperties() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		
		String accessKey = prop.getAccessKey();
		String secretKey = prop.getSecretKey();
		String redshift_jdbc_url = prop.getRedshift_jdbc_url();
		String master_username = prop.getMaster_username();
		String master_password = prop.getMaster_password();
		String mysql_dbname = prop.getMysql_dbname();
		String mysql_username = prop.getMysql_username();
		String mysql_password = prop.getMysql_password();
		String mysql_JDBC_DRIVER = prop.getMysql_JDBC_DRIVER();
		String mysql_DB_URL = prop.getMysql_DB_URL();
		String stockDatapath = prop.getStockDatapath();
		String cloudbeamurl = prop.getCloudbeam_slave_url();
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("accessKey", accessKey);
		map.put("secretKey", secretKey);
		map.put("redshift_jdbc_url", redshift_jdbc_url);
		map.put("master_username", master_username);
		map.put("master_password", master_password);
		map.put("mysql_dbname", mysql_dbname);
		map.put("mysql_username", mysql_username);
		map.put("mysql_password", mysql_password);
		map.put("mysql_JDBC_DRIVER", mysql_JDBC_DRIVER);
		map.put("mysql_DB_URL", mysql_DB_URL);
		map.put("stockDatapath", stockDatapath);
		map.put("cloudbeamurl", cloudbeamurl);
		
		
		JSONObject obj = new JSONObject();
		try {
			obj.put("projectProp", map);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.print(obj.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
