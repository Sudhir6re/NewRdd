	$("#GenerateReport").click(function(e){
		///alert("hiii");
    	
 	 	 var month = $('#monthId').val();
 	 	 var year = $('#yearId').val(); 
 	 	 var deptAllowDedId = $('#deptAllowDedId').val(); 
 	 	 var billNumber = $('#billNumber').val(); 
 	 	 
 	 	removeErrorClass($('#monthId'));
 	 	removeErrorClass($('#year Id'));
 	 	removeErrorClass($('#billNumber'));
 	 	removeErrorClass($('#deptAllowDedId'));
 	 	 //alert("month and year  ="+month+''+year);
 	 	 
 	 	 if(month=="0"){
 	 		addErrorClass($('#monthId'),"Please Select Month !!!");
 	 		e.preventDefault();
 	 	 }
 	 	 
 	 	 
 	 	 if(year=="0"){
 	 		addErrorClass($('#yearId'),"Please Select Year !!!");
 	 		e.preventDefault();
 	 	 }
 	 	 
 	 	 
 	 	 
 	 	 if(billNumber=="0"){
 	 		addErrorClass($('#billNumber'),"Please Select Bill Group  !!!");
 	 		e.preventDefault();
 	 	 }
 	 	 
 	 	 if(deptAllowDedId=="0"){
 	 		 addErrorClass($('#deptAllowDedId'),"Please Select Society Name !!!");
 	 		 e.preventDefault();
 	 	 }
 	 	 
 	 	 if(month!="0" && year!="0" && billNumber!="0" && deptAllowDedId!="0"){
 	 		var isPaybillgenerate =  checkIsPaybillGenerate(billNumber,month,year,deptAllowDedId,e);
 	 		
 			if(isPaybillgenerate == 0)
	 			{
 				swal.fire("Paybill is Not Generated!");
	 			e.preventDefault();
	 			}else
	 				{
	 				var dataExists = checkIsDataExists(billNumber,month,year,deptAllowDedId,e);
	 				
	 	 	 		if( dataExists == 0)
	 	 	 			{
	 	 	 			swal.fire("No Record Found For the Selected Component!");
	 	 	 			e.preventDefault();
	 	 	 			}else{
	 	 	 				$("#societyForm").attr("action","../master/allsocietyReportNew");
	 	 	 				$("#societyForm").submit();
	 	 	 			}
	 				}
	 		
 	 	 }
	});
 	 	
	

	function checkIsDataExists(billNumber,monthName,yearName,deptAllowDedId,e) ///CheckPaybill/{billNumber}/{monthName}/{yearName}
	{
		flag=0;
		 $.ajax({
		      type: "GET",
		      url: "../master/checkIsDataExists/"+billNumber+"/"+monthName +"/"+yearName +"/" + deptAllowDedId,
		      async: false,
		      dataType : 'json',
		     contentType:'application/json',
		      error: function(data){
		    	  console.log(data);
		    alert(data);
		      },
		      success: function(data){
		    	  console.log("response from server"+data);
		    	  if(parseInt(data) >0 ) {
		    		  flag=1;
		    		//	e.preventDefault();
		    	  }
		    	  else
		    		  {
		    		 flag=0;
		    		  }
		    }
		 });
		 return flag;
	}
	

	function checkIsPaybillGenerate(billNumber,monthName,yearName,deptAllowDedId,e) ///CheckPaybill/{billNumber}/{monthName}/{yearName}
	{
		e.preventDefault();
		
		flag=0;
		 $.ajax({
		      type: "GET",
		      url: "../master/checkIsPaybillGenerate/"+billNumber+"/"+monthName +"/"+yearName +"/" + deptAllowDedId,
		      async: false,
		      dataType : 'json',
		     contentType:'application/json',
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
		 
			e.preventDefault();
	}