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
 	 	 
 	 	 
 	 	
 	 	 if(month!="0" && year!="0" ){
 	 		var ispaybillinProcess = checkIsPaybillInProcess(month,year);
 	 		if(ispaybillinProcess == 0)
 	 			{
 	 			swal.fire("Paybill not generated!");
 	 			e.preventDefault();
 	 			}
 	 	 }
	});
 	 	