function checkisdataPresent(SevaarthId,year) ///CheckGPFEntry/{billNumber}/{monthName}/{yearName}
	{
		flag=0;
		 $.ajax({
		      type: "GET",
		      url: "../master/checkisdataPresentForGpfEmpsDtls/"+SevaarthId +"/" +year,
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



$("#search").click(function(e){
    	
 	 	 var sevaarthId = $('#sevaarthId').val();
 	 	 var year = $('#year').val();
 	 	 
 	 	removeErrorClass($('#sevaarthId'));
 	 	removeErrorClass($('#year'));
 	 	 //alert("month and year  ="+month+''+year);
 	 	 
 	 	 if(sevaarthId==""){
 	 		///addErrorClass($('#sevaarthId'),"Please Enter SevaarthId !!!");
 	 		swal("Please Enter SevaarthId !!!")
 	 		e.preventDefault();
 	 	 }
 	 	 
 	 	 if(year=="0"){
 	 		 //addErrorClass($('#year'),"Please Select Year !!!");
 	 		swal("Please Select Year !!!")
 	 		 e.preventDefault();
 	 	 }
 	 	 
 	 	 
 	 	 
 	 	 
 	 	 
 	 	 if(sevaarthId!="" && year!="0"){
 	 		var isdataPresent = checkisdataPresent(sevaarthId,year);
 	 		if(isdataPresent == 0)
 	 			{
 	 			swal("Record not found!");
 	 			e.preventDefault();
 	 			}
 	 	 }
	});