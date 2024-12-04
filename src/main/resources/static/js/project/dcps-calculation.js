function checktheEntryForDCPS(yearId) ///CheckPaybill/{billNumber}/{monthName}/{yearName}
	{
		flag=0;
		 $.ajax({
		      type: "GET",
		      url: "../master/checktheEntryForDCPS/"+yearId,
		      async: false,
		      dataType : 'json',
		    // contentType:'application/json',
		      error: function(data){
		    	  console.log(data);
//		    alert(data);
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
    	
 	 	 var yearId = $('#yearId').val(); 
 	 	 
 	 	removeErrorClass($('#yearId'));
 	 	 //alert("month and year  ="+month+''+year);
 	 	 
 	 	 
 	 	 if(yearId=="0"){
 	 		addErrorClass($('#yearId'),"Please select Year !!!");
 	 		e.preventDefault();
 	 	 }
 	 	 
 	 				var isdatapresent = checktheEntryForDCPS(yearId);
 	 	 	 		if(isdatapresent == 0)
 	 	 	 			{
 	 	 	 			swal.fire("Record not found!");
 	 	 	 			e.preventDefault();
 	 	 	 			}
 	 				
 	 		
 	 	 
	});