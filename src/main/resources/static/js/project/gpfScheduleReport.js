	$("#GenerateReport").click(function(e){
//    	alert("GenerateReport");
 	 	 var month = $('#monthId').val();
 	 	 var year = $('#yearId').val(); 
 	 	 var billGroup = $('#billGroup').val(); 
 	 	 
 	 	removeErrorClass($('#monthId'));
 	 	removeErrorClass($('#yearId'));
 	 	removeErrorClass($('#billGroup'));
// 	 	 alert("month and year  ="+month+''+year);
 	 	 
 	 	 if(month=="0"){
 	 		addErrorClass($('#monthId'),"Please select Month !!!");
 	 		e.preventDefault();
 	 	 }
 	 	 
 	 	 
 	 	 if(year=="0"){
 	 		addErrorClass($('#yearId'),"Please select Year !!!");
 	 		e.preventDefault();
 	 	 }
 	 	 
 	 	 
 	 	 
 	 	 if(billGroup=="0"){
 	 		addErrorClass($('#billGroup'),"Please select Bill Group  !!!");
 	 		e.preventDefault();
 	 	 }
	});
 	 	