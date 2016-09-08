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
				dataType : 'json',
				success : function(data) {
					//alert(JSON.stringify(data))
					showTree(data)
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

