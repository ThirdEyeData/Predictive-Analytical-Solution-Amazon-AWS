//Click on Ready
$( document ).ready(function() {
	 var d =  new Date().toLocaleString();
	var startProcess='{"class":"start","color":"green","status":"Process Start At: '+d+'"}';
	//Generate MockUp Data
	$( "#mockupdata" ).click(function() {
		showTree(JSON.parse(startProcess))
		  
		  $.ajax({
				url : "./DashboardOperation",
				type : "POST",
				data : {
			        datatype: "mockup"
			    },
				
				success : function(data) {
					//alert(JSON.stringify(data))
					var dataSplit=data.split("---");
					showTree(JSON.parse(dataSplit[0]))
					showTree(JSON.parse(dataSplit[1]))
					showTree(JSON.parse(dataSplit[2]))
					showTree(JSON.parse(dataSplit[3]))
				},
				error : function(xhr, ajaxOptions, thrownError) {
					alert("ERROR:" + xhr.responseText + " - " + thrownError);
				}

			});
		  
		  showBar("MockUp Data Loading Started")
		});
	
	//Generate Weather Data
	$( "#weatherData" ).click(function() {
		showTree(JSON.parse(startProcess))
		  
		  $.ajax({
				url : "./DashboardOperation",
				type : "POST",
				data : {
			        datatype: "weather"
			    },
				success : function(data) {
					var dataSplit=data.split("---");
					showTree(JSON.parse(dataSplit[0]))
					showTree(JSON.parse(dataSplit[1]))
					//showTree(JSON.parse(data))
				},
				error : function(xhr, ajaxOptions, thrownError) {
					alert("ERROR:" + xhr.responseText + " - " + thrownError);
				}

			});
		  
		  showBar("Weather Data Loading Started")
		 
		});
	
	//Generate Increment Data
	$( "#incrementalData" ).click(function() {
		showTree(JSON.parse(startProcess))
		  
		  $.ajax({
				url : "./DashboardOperation",
				type : "POST",
				data : {
			        datatype: "incremental"
			    },
				success : function(data) {
					//alert(JSON.stringify(data))
					var dataSplit=data.split("---");
					showTree(JSON.parse(dataSplit[0]))
					showTree(JSON.parse(dataSplit[1]))
				},
				error : function(xhr, ajaxOptions, thrownError) {
					alert("ERROR:" + xhr.responseText + " - " + thrownError);
				}

			});
		  
		  showBar("Incremental Data Loading Started")
		  
		});
	
	
	function showBar(messgae){
		 $("#messageBox").show();
		 $("#progressmsg").html('');
		 $("#progressmsg").html(messgae);
	}
	
	function showTree(obj){
		
		//alert(JSON.stringify(obj))
		
		$("path."+obj.class)
        .delay(800)
        .queue(function (next) { 
        $(this).css('stroke', obj.color); 
        next(); 
        });

        $("circle."+obj.class)
        .delay(1000)
        .queue(function (next) { 
        $(this).css('stroke', 'black'); 
        next(); 
        });

        $("circle."+obj.class)
        .delay(1200)
        .queue(function (next) { 
        $(this).css('fill', obj.color); 
        next(); 
        });

        $("text."+obj.class).attr('id',obj.status);
		
	}
	
	//Hide
	$("#messageBox").hide();
	
	
	//Modal
	$( "#weathermodal" ).click(function() {
		var body="Step 1 uploads historical weather data for three years to S3 and then to Redshift. This data is used as the training set for developing the machine learning algorithm for forecasting the storm in the incremental data set. The dataset contains, among other columns, information on the weather conditions, like minimum and maximum temperatures, the amount of precipitation, wind speed etc.";
		
		
		$('#myModal').modal() 
		$('#modaltitle').html('')
		$('#modaltitle').html('Weather Data (Historical) load')
		$('#modalbody').html('')
		$('#modalbody').html(body)
		
	});
	
	$( "#companyData" ).click(function() {
		var body="Step 2 generates and uploads the historical stock data into S3 and then to Redshift. This dataset contains and information on the opening, closing, highest, lowest price trends, volume, etc of the stocks of a particular company. It also generates and uploads historical company data into MySQL. These multiple datasets contains information on the company, its product releases and press announcements.";
		
		$('#myModal').modal() 
		$('#modaltitle').html('')
		$('#modaltitle').html('Stock and Company Data (Historical) load')
		$('#modalbody').html('')
		$('#modalbody').html(body)
		
	});
	
	$( "#nonstockData" ).click(function() {
		var body="Step 3 uploads the non-stock company data from MySQL to Redshift using the ETL tool Attunity Cloudbeam.;"
		$('#myModal').modal() 
		$('#modaltitle').html('')
		$('#modaltitle').html('Non-stock Company data - Redshift Upload')
		$('#modalbody').html('')
		$('#modalbody').html(body)
		
	});
	
	$( "#incrementalDataModal" ).click(function() {
		var body="Step 4 generates and uploads the the incremental stock data to Redshift. It also uploads the  data for the incremental weather data for the upcoming seven days, for which we want to predict storm."
		$('#myModal').modal() 
		$('#modaltitle').html('')
		$('#modaltitle').html('Stock and Weather data (Incremental) load')
		$('#modalbody').html('')
		$('#modalbody').html(body)
		
	});
	
	$( "#stormPredictionModal" ).click(function() {
		var body="Step 5 executes the R-script for the machine learning algorithm. This script predicts the occurrence of storm in the incremental weather dataset for the next seven days, and saves the results on Redshift. It also sends alerts on the BYOD mobile app."
		$('#myModal').modal() 
		$('#modaltitle').html('')
		$('#modaltitle').html('Storm Prediction')
		$('#modalbody').html('')
		$('#modalbody').html(body)
		
	});
	
	$('#nonstockDataBtn').prop('disabled', true);
	
	//Setting page
	$("#setting").click(function() {
		
		 $.ajax({
				url : "./EditProperties",
				success : function(data) {
					
					$("#settingconfigfrom").trigger("reset");
					
					$("#awsaccesskey").val(data.projectProp.accessKey);
					$("#awssecretkey").val(data.projectProp.secretKey);
					$("#bucketname").val(data.projectProp.accessKey);
					$("#redshiftjdbcurl").val(data.projectProp.redshift_jdbc_url);
					$("#redshiftusername").val(data.projectProp.master_username);
					$("#redshiftuserpassword").val(data.projectProp.master_password);
					$("#mysqldbname").val(data.projectProp.mysql_dbname);
					$("#mysqlusername").val(data.projectProp.mysql_username);
					$("#mysqlpassword").val(data.projectProp.mysql_password);
					$("#mysqljdbcurl").val(data.projectProp.mysql_DB_URL);
					$("#jdbcclass").val(data.projectProp.mysql_JDBC_DRIVER);
					$("#stockdatapath").val(data.projectProp.stockDatapath);
					$("#cloudbeam_url").val(data.projectProp.cloudbeamurl);
					
					$('#settingmodal').modal();
				},
				error : function(xhr, ajaxOptions, thrownError) {
					alert("ERROR:" + xhr.responseText + " - " + thrownError);
				}

			});
		
		//$('#settingmodal').modal();
	});
	
});



function ajaxCall(dataTypeValue){
	$.ajax({
		url : "./DashboardOperation",
		type : "POST",
		data : {
	        datatype: dataTypeValue
	    },
		dataType : 'json',
		success : function(data) {
			//alert(JSON.stringify(data))
			showTree(data)
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("ERROR:" + xhr.responseText + " - " + thrownError);
		}

	});
}

