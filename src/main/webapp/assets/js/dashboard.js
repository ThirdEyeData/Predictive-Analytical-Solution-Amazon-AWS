//Click on Ready
$( document ).ready(function() {
	
	//Generate MockUp Data
	$( "#mockupdata" ).click(function() {
		  alert( "Handler for .click() called1." );
		});
	
	//Generate Weather Data
	$( "#weatherData" ).click(function() {
		  alert( "Handler for .click() called2." );
		  
		  $.ajax({
				url : "./DashboardOperation",
				type : "POST",
				data : {
			        datatype: "weather"
			    },
				dataType : 'json',
				success : function(data) {
					alert(JSON.stringify(data))
				},
				error : function(xhr, ajaxOptions, thrownError) {
					alert("ERROR:" + xhr.responseText + " - " + thrownError);
				}

			});
		  
		  showBar("Weather Data Loading Started")
		 
		});
	
	//Generate Increment Data
	$( "#incrementalData" ).click(function() {
		  alert( "Handler for .click() called3." );
		});
	
	
	function showBar(messgae){
		 $("#messageBox").show();
		 $("#progressmsg").html('');
		 $("#progressmsg").html(messgae);
	}
	
	//Hide
	$("#messageBox").hide();
});