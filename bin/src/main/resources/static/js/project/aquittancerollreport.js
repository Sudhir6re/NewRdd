$('#btnaquittancerollreport').click(function() {
	 var yearName=$('#yearName').val();
	 var monthName=$('#monthName').val();
	 var ddoCode=$('#logUser').text();
	
	 
//	 alert("welcome");
	 if(yearName == '0' || yearName==""){
		 swal("Please select year");
		 return false ;
	 }
	 if(monthName == '0' || monthName==""){
		 swal("Please select monthName");
		 return false ;
	 }
	 
	 location.href="../paybill/aquittancereport/"+yearName+"/"+monthName;
	 
	
	});

$('#btnbankstatementreport').click(function() {
	 var yearName=$('#yearName').val();
	 var monthName=$('#monthName').val();
	 var ddoCode=$('#logUser').text();
	
	 
//	 alert("welcome");
	 if(yearName == '0' || yearName==""){
		 swal("Please select year");
		 return false ;
	 }
	 if(monthName == '0' || monthName==""){
		 swal("Please select monthName");
		 return false ;
	 }
	 
	 location.href="../paybill/bankStatementreport/"+yearName+"/"+monthName;
	 
	
	});

$('#btnrevenueStampreport').click(function() {
	var yearName=$('#yearName').val();
	var monthName=$('#monthName').val();
	var ddoCode=$('#logUser').text();
	
	
//	 alert("welcome");
	if(yearName == '0' || yearName==""){
		swal("Please select year");
		return false ;
	}
	if(monthName == '0' || monthName==""){
		swal("Please select monthName");
		return false ;
	}
	
	location.href="../paybill/revenueStampreport/"+yearName+"/"+monthName;
	
	
});

