function checktheEntryPensionDaArr(ppoNo,month,year)
	{
		flag=0;
		 $.ajax({
		      type: "GET",
		      url: "../master/checktheEntryPensionDaArr/"+ppoNo+"/"+month +"/"+year,
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
		 var ppoNo = $('#txtSearchName').val();
 	 	 var year = $('#yearName').val();
 	 	 var month = $('#monthName').val(); 
 	 	
 	 	 
 	 	removeErrorClass($('#txtSearchName'));
 	 	removeErrorClass($('#yearName'));
 	 	removeErrorClass($('#monthName'));
 	 	
 	 	// alert("month and year  ="+month+''+year);
 	 	//alert("ppoNo"+ppoNo);
 	 	 
 	 	if(ppoNo == "")
 	 		{
 	 	 		addErrorClass($('#txtSearchName'),"Please enter Ppo No !!!");
 	 	 		e.preventDefault();
 	 		}
 	 	
 	 	 if(month=="0"){
 	 		addErrorClass($('#monthName'),"Please select Month !!!");
 	 		e.preventDefault();
 	 	 }
 	 	 
 	 	 
 	 	 if(year=="0"){
 	 		addErrorClass($('#yearName'),"Please select Year !!!");
 	 		e.preventDefault();
 	 	 }
 	 	 
 	 	 if(month!="0" && year!="0" && ppoNo != ""){
  	 		var isdatapresent = checktheEntryPensionDaArr(ppoNo,month,year);
  	 		if(isdatapresent == 0)
  	 			{
  	 			swal("Record not found!");
  	 			e.preventDefault();
  	 			}
  	 	 }
	});
 	 	