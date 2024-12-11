function checkisdataPresent(monthName,yearName) ///CheckPaybill/{billNumber}/{monthName}/{yearName}
	{
		flag=0;
		 $.ajax({
		      type: "GET",
		      url: "../admin/checkisdataPresent/"+monthName +"/"+yearName,
		      async: false,
		      dataType : 'json',
		    // contentType:'application/json',
		      error: function(data){
		    	  console.log(data);
		    alert(data);
		      },
		      success: function(data){
		    	  console.log("response from server"+data);
		    	  if(parseInt(data) >0 ) {
		    		  flag=1;
		    	  }
		    	  else
		    		  {
		    		 flag=0;
		    		  }
		    }
		 });
		 return flag;
	}	

$("#GenerateReport").click(function(e){
    	
 	 	 var month = $('#monthId').val();
 	 	 var year = $('#yearId').val(); 
 	 	 
 	 	removeErrorClass($('#monthId'));
 	 	removeErrorClass($('#yearId'));
 	 	 //alert("month and year  ="+month+''+year);
 	 	 
 	 	 if(month=="0"){
 	 		addErrorClass($('#monthId'),"Please select Month !!!");
 	 		e.preventDefault();
 	 	 }
 	 	 
 	 	 
 	 	 if(year=="0"){
 	 		addErrorClass($('#yearId'),"Please select Year !!!");
 	 		e.preventDefault();
 	 	 }
 	 	 
 	 	 
 	 	 if(month!="0" && year!="0"){
 	 		var isdataPresent = checkisdataPresent(month,year);
 	 		if(isdataPresent == 0)
 	 			{
 	 			swal.fire("Record not found!");
 	 			e.preventDefault();
 	 			}
 	 	 }
	});





function check() {
			$(".divName").each(function() {
				var tdText = $(this).text();
				$(".divName").filter(function() {
					return tdText == $(this).text();
				}).not(":first").empty();
			});
		};
		check();
		
		
		