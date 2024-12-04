/*function checkEntryForSupplmtryBillReport(paybillMonth,paybillYear,billType)  ////checkEntryForSupplmtryBillReport/{paybillMonth}/{paybillYear}/{billType}
	{
		flag=0;
		 $.ajax({
		      type: "GET",
		      url: "../master/checkEntryForSupplmtryBillReport/"+paybillMonth+"/"+paybillYear+"/"+billType,
//		      data:{ 'yearId' : '2022'},
		      async: false,
		      dataType : 'json',
		    // contentType:'application/json',
		      error: function(data){
		    	  console.log(data);
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
    	
 	 	 var month = $('#paybillMonth').val(); 
 	 	 var year = $('#paybillYear').val(); 
 	 	 var billtype = $('#billType').val(); 
 	 	 
 	 	 
 	 	 if(year=="0"){
 	 		addErrorClass($('#paybillYear'),"Please select Year !!!");
 	 		e.preventDefault();
 	 	 }
 	 		
 	 		if(month=="0"){
 	 			addErrorClass($('#paybillYear'),"Please select Month !!!");
 	 			e.preventDefault();
 	 		}
 	 			
 	 			if(billtype== ''){
 	 				addErrorClass($('#paybillYear'),"Please select Bill Type !!!");
 	 				e.preventDefault();
 	 	 }
 	 			
 	 			
 	 	 if(year!="0"){
 	 		var ispaybillinProcess = checkEntryForSupplmtryBillReport(paybillMonth,paybillYear,billType);
 	 		if(ispaybillinProcess != 0)
 	 			{
 	 			
 	 			}else
 	 				{
 	 				var isdatapresent = checkEntryForSupplmtryBillReport(paybillMonth,paybillYear,billType);
 	 	 	 		if(isdatapresent == 0)
 	 	 	 			{
 	 	 	 			swal.fire("Record not found!");
 	 	 	 			e.preventDefault();
 	 	 	 			}
 	 				}
 	 	 }
	});
	*/
	
	
	
	$("#GenerateReport")
	.click(
			function(e) {
				
				 var month = $('#paybillMonth').val(); 
		 	 	 var year = $('#paybillYear').val(); 
		 	 	 var billtype = $('#billType').val(); 
				
				$.ajax({
					type : "GET",
					   url: "../master/checkEntryForSupplmtryBillReport/"+paybillMonth+"/"+paybillYear+"/"+billType,
							
					async : false,
					contentType : 'application/json',
					error : function(data) {
						console.log(data);
						$("#loaderMainNew").hide();
					},
					success : function(data) {
						console.log(data);
						var len = data.length;
						if (len != 0)
							{
							}
							
						else {
							swal("Record not found!");
							e.preventDefault();
							}
						
					}
				});
				
			});
