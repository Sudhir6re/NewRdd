 $("#con").hide();
		var ddoCode='';
		var month='';
		var yearName='';
		var billGroupId='';
		var paybillId='';
		
		var context='';

 $(document).ready(function(){
 	var params = new window.URLSearchParams(window.location.search); 
 	
 	if(params.get('yearName') !=null){
 		 month=params.get('month');
 		 yearName=params.get('yearName');
 		 billGroupId=params.get('billGroupId');
 		 paybillId=params.get('paybillId');
 		ddoCode=params.get('ddoCode');
 		
 		$("#monthName").val(month);
 		$("#yearName").val(yearName);
 		$("#billNumber").val(billGroupId);
 		$("#billno").val(paybillId);
 		$("#searchDiv").hide();
 		$("#btnDiv").hide();
 		$("#con").show();
 	}
 	console.log(params.get('month'));
 	console.log(params.get('yearName'));
 	console.log(params.get('billGroupId'));
 	
 	
 	   context=$("#context").val();
 });

$('#btnouterreport').click(function() {
	 var billNumber=$('#billno').val();
	 var month=$('#monthName').val();
	 var year=$('#yearName').val();
	
	 ddoCode=$("#logUser").text().trim();
	var isContainAST=ddoCode.includes("_AST");
	 if(!isContainAST){
		 ddoCode=1;
	 }
		
	 
	 
//	 swal("welcome");
	 if(billNumber == '0'){
		 swal('Please select the valid Bill Discription');
		 return false ;
	 }
	 if (billNumber != '') {
		 $
		 .ajax({
			 type : "GET",
			 url : context+"/ddoast/outerreport/"
			 + billNumber +"/"+ ddoCode +"/"+ month +"/"+year,
				 async : true,
				 contentType : 'application/json',
				 error : function(data) {
					 swal("error");
					 console.log(data);
					 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
					 var win = window.open("","",urlstyle);
			            win.document.write(data);
//					 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
//					 	window.open("welcome","",urlstyle);
//					 swal(data);
				 },
				 success : function(data) {
					// swal("success");
//					 console.log(data);
					 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
					 var win = window.open("Outer_report","Outer_report",urlstyle);
			            win.document.write(data);

					 
				 }
		 });
	 }
	 else{
		 swal('Please select Bill Description');
	 }
});



function showouter()
{
	urlstring="hrms.htm?actionFlag=getOuterData&month=11&year=2019&billNo=99200028511 ";
	var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
 	window.open(urlstring,"",urlstyle);
}

$('#btninnerreport').click(function() {
	 var billNumber=$('#billno').val();
	
	 ddoCode=$("#logUser").text().trim();
	 var isContainAST=ddoCode.includes("_AST");
	 if(!isContainAST){
		 ddoCode=1;
	 }
	 
	 
//	 swal("welcome");
	 if(billNumber == '0'){
		 swal('Please select the valid Bill Discription');
		 return false ;
	 }
	 if (billNumber != '') {
		 $
		 .ajax({
			 type : "GET",
			 url : context+"/ddoast/getinnerreport/"
			 + billNumber +"/"+1+"/"+1+"/"+ddoCode,
				 async : true,
				 contentType : 'application/json',
				 error : function(data) {
					 swal("error");
					 console.log(data);
					 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
					 var win = window.open("","",urlstyle);
			            win.document.write(data);
//					 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
//					 	window.open("welcome","",urlstyle);
//					 swal(data);
				 },
				 success : function(data) {
					// swal("success");
//					 console.log(data);
					 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
					 var win = window.open("Inner_report","inner_report",urlstyle);
			            win.document.write(data);

					 
				 }
		 });
	 }
	 else{
		 swal('Please select Bill Description');
	 }
});


$('#btninnerreportPDF').click(function() {
	 var billNumber=$('#billno').val();
	
	 if(billNumber == '0'){
		 swal('Please select the valid Bill Discription');
		 return false ;
	 }
	 if (billNumber != '') {
		 $
		 .ajax({
			 type : "GET",
			 url : context+"/innerreport/getinnerreportPDF/"
			 + billNumber +"/"+1+"/"+1,
				 async : true,
				 contentType : 'application/json',
				 error : function(data) {
					 swal("error");
					 console.log(data);
					 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
					 var win = window.open("","",urlstyle);
			            win.document.write(data);
				 },
				 success : function(data) {
					 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
					 var win = window.open("Inner_report","Inner_report",urlstyle);
			            win.document.write(data);

					 
				 }
		 });
	 }
	 else{
		 swal('Please select Bill Description');
	 }
});

$("#groupAbstractreport").click(function(){
	
	 var yearName=$('#yearName').val();
	 var monthName=$('#monthName').val();
	 var billNumber=$('#billGroup').val();
	 
	 if (billNumber != '') {
		 $
		 .ajax({
			 type : "GET",
			 
			 url : context+"/ddoast/btnShowReport/"+yearName+"/"+monthName+"/"
			 + billNumber,
				 async : true,
				 contentType : 'application/json',
				 error : function(data) {
					 console.log(data);
				 },
				 success : function(data) {
					 console.log(data);
					 if(data!=''){
						 $("#billno").val(data);
						 
						 $('#btngroupAbstractreport').click();
					 }else{
						 swal("No Data Found");
					 }
					
					 
				 }
		 });
	 }
	 
	
});


$('#btngroupAbstractreport').click(function() {
	 var billNumber=$('#billno').val();
	 var month=$('#monthName').val();
	 var year=$('#yearName').val();
	 
	 ddoCode=$("#logUser").text().trim();
	 var isContainAST=ddoCode.includes("_AST");
	 if(!isContainAST){
		 ddoCode=1;
	 }
	 
	 if(billNumber == '0'){
		 swal('Please select the valid Bill Discription');
		 return false ;
	 }
	 if (billNumber != '') {
		 $
		 .ajax({
			 type : "GET",
			 url : context+"/ddoast/groupAbstractReport/"
			 + billNumber+"/"+ddoCode+"/"+month+"/"+year,
				 async : true,
				 contentType : 'application/json',
				 error : function(data) {
					 swal("error");
					 console.log(data);
					 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
					 var win = window.open("","",urlstyle);
			            win.document.write(data);
				 },
				 success : function(data) {
					 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
					 var win = window.open("groupAbs_report","group_report",urlstyle);
			            win.document.write(data);

					 
				 }
		 });
	 }
	 else{
		 swal('Please select Bill Description');
	 }
});



function showouter()
{
	urlstring="hrms.htm?actionFlag=getOuterData&month=11&year=2019&billNo=99200028511 ";
	var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
	window.open(urlstring,"",urlstyle);
}



$("#bankStatReport").click(function(){
	
	 var yearName=$('#yearName').val();
	 var monthName=$('#monthName').val();
	 var billNumber=$('#billGroup').val();
	 
	 if (billNumber != '') {
		 $
		 .ajax({
			 type : "GET",
			 
			 url : context+"/ddoast/btnShowReport/"+yearName+"/"+monthName+"/"
			 + billNumber,
				 async : true,
				 contentType : 'application/json',
				 error : function(data) {
					 console.log(data);
				 },
				 success : function(data) {
					 console.log(data);
					 if(data!=''){
						 $("#billno").val(data);
						 
						 $('#btnbankstatementreport').click();
					 }else{
						 swal("No Data Found");
					 }
					
					 
				 }
		 });
	 }
	 
	
});


$('#btnbankstatementreport').click(function() {
	 var yearName=$('#yearName').val();
	 var monthName=$('#monthName').val();
	 var billNumber=$('#billno').val();
	 
	 ddoCode=$("#logUser").text().trim();
	 var isContainAST=ddoCode.includes("_AST");
	 if(!isContainAST){
		 ddoCode=1;
	 }
	 
	 
	 
	 
	 if(yearName == '0' || yearName==""){
		 swal("Please select Year");
		 return false ;
	 }
	 if(monthName == '0' || monthName==""){
		 swal("Please select Month Name");
		 return false ;
	 }
	 if(billNumber == '0'){
		 swal('Please select the valid Bill Discription');
		 return false ;
	 }
	 if (billNumber != '') {
		 $
		 .ajax({
			 type : "GET",
			 url : context+"/ddoast/bankStatementreport/"+yearName+"/"+monthName+"/"
			 + billNumber+"/"+ddoCode,
				 async : true,
				 contentType : 'application/json',
				 error : function(data) {
					 swal("error");
					 console.log(data);
					 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
					 var win = window.open("","",urlstyle);
			            win.document.write(data);
//					 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
//					 	window.open("welcome","",urlstyle);
//					 swal(data);
				 },
				 success : function(data) {
					// swal("success");
//					 console.log(data);
					 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
					 var win = window.open("BankSta_report","Banksta_report",urlstyle);
			            win.document.write(data);

					 
				 }
		 });
	 }
	 else{
		 swal('Please select Bill Description');
	 }
	});


$("#aquittReport").click(function(){
	
	 var yearName=$('#yearName').val();
	 var monthName=$('#monthName').val();
	 var billNumber=$('#billGroup').val();
	 
	 if (billNumber != '') {
		 $
		 .ajax({
			 type : "GET",
			 
			 url : context+"/ddoast/btnShowReport/"+yearName+"/"+monthName+"/"
			 + billNumber,
				 async : true,
				 contentType : 'application/json',
				 error : function(data) {
					 console.log(data);
				 },
				 success : function(data) {
					 console.log(data);
					 if(data!=''){
						 $("#billno").val(data);
						 
						 $('#btnaquittancerollreport').click();
					 }else{
						 swal("No Data Found");
					 }
					
					 
				 }
		 });
	 }
	 
	
});



$('#btnaquittancerollreport').click(function() {
	 var yearName=$('#yearName').val();
	 var monthName=$('#monthName').val();
	 var billNumber=$('#billno').val();
	 
	 
	 ddoCode=$("#logUser").text().trim();
	 var isContainAST=ddoCode.includes("_AST");
	 if(!isContainAST){
		 ddoCode=1;
	 }
	 
	 
	 if(yearName == '0' || yearName==""){
		 swal("Please select Year");
		 return false ;
	 }
	 if(monthName == '0' || monthName==""){
		 swal("Please select Month Name");
		 return false ;
	 }
	 if(billNumber == '0'){
		 swal('Please select the valid Bill Discription');
		 return false ;
	 }
	 if (billNumber != '') {
		 $
		 .ajax({
			 type : "GET",
			 url : context+"/ddoast/aquittancereport/"+yearName+"/"+monthName+"/"
			 + billNumber+"/"+ddoCode,
				 async : true,
				 contentType : 'application/json',
				 error : function(data) {
					 swal("error");
					 console.log(data);
					 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
					 var win = window.open("","",urlstyle);
			            win.document.write(data);
//					 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
//					 	window.open("welcome","",urlstyle);
//					 swal(data);
				 },
				 success : function(data) {
					// swal("success");
//					 console.log(data);
					 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
					 var win = window.open("Aquti_report","Aquti_report",urlstyle);
			            win.document.write(data);

					 
				 }
		 });
	 }
	 else{
		 swal('Please select Bill Description');
	 }
	});

$('#btnrevenueStampreport').click(function() {
	 var yearName=$('#yearName').val();
	 var monthName=$('#monthName').val();
	 var billNumber=$('#billno').val();
	 var ddoCode=1;
	 if(yearName == '0' || yearName==""){
		 swal("Please select Year");
		 return false ;
	 }
	 if(monthName == '0' || monthName==""){
		 swal("Please select Month Name");
		 return false ;
	 }
	 if(billNumber == '0'){
		 swal('Please select the valid Bill Discription');
		 return false ;
	 }
	 if (billNumber != '') {
		 $
		 .ajax({
			 type : "GET",
			 url : context+"/ddoast/revenueStampreport/"+yearName+"/"+monthName+"/"
			 + billNumber+"/"+ddoCode,
				 async : true,
				 contentType : 'application/json',
				 error : function(data) {
					 swal("error");
					 console.log(data);
					 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
					 var win = window.open("","",urlstyle);
			            win.document.write(data);
				 },
				 success : function(data) {
					 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
					 var win = window.open("Stamp_report","Stamp_report",urlstyle);
			            win.document.write(data);

					 
				 }
		 });
	 }
	 else{
		 swal('Please select Bill Description');
	 }
	});
$('#btnadvanceInterestreport').click(function() {
	 var yearName=$('#yearName').val();
	 var monthName=$('#monthName').val();
	 var billNumber=$('#billNumber').val();
	 var schemeBillGrp=$('#billNumber').val();
	 var ddoCode=1;
	 if(yearName == '0' || yearName==""){
		 swal("Please select Year");
		 return false ;
	 }
	 if(monthName == '0' || monthName==""){
		 swal("Please select Month Name");
		 return false ;
	 }
	 if(billNumber == '0'){
		 swal('Please select the valid Bill Discription');
		 return false ;
	 }
	 if(schemeBillGrp==null){
		 schemeBillGrp=billGroupId;
	 }
	 if (billNumber != '') {
		 $
		 .ajax({
			 type : "GET",
			/* url : context+"/paybill/advanceInterestreport/"+yearName+"/"+monthName+"/"
			 + billNumber+"/"+schemeBillGrp+"/"+ddoCode,*/
			 url : context+"/master/faReportBillWise/"+yearName+"/"+monthName+"/"
				+ billNumber,
				 async : true,
				 contentType : 'application/json',
				 error : function(data) {
					 swal("error");
					 console.log(data);
					 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
					 var win = window.open("","",urlstyle);
			            win.document.write(data);
				 },
				 success : function(data) {
					 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
					 var win = window.open("FA_report","FA_report",urlstyle);
			            win.document.write(data);

					 
				 }
		 });
	 }
	 else{
		 swal('Please select Bill Description');
	 }
	});

$('#btnadvanceFormBreport').click(function() {
	var yearName=$('#yearName').val();
	var monthName=$('#monthName').val();
	var billNumber=$('#billno').val();
	if(yearName == '0' || yearName==""){
		swal("Please select Year");
		return false ;
	}
	if(monthName == '0' || monthName==""){
		swal("Please select Month Name");
		return false ;
	}
	if(billNumber == '0'){
		swal('Please select the valid Bill Discription');
		return false ;
	}
	if (billNumber != '') {
		$
		.ajax({
			type : "GET",
			url : context+"/paybill/advanceInterestFormBreport/"+yearName+"/"+monthName+"/"
			+ billNumber,
			async : true,
			contentType : 'application/json',
			error : function(data) {
				swal("error");
				console.log(data);
				var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
				var win = window.open("","",urlstyle);
				win.document.write(data);
			},
			success : function(data) {
				var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
				var win = window.open("FormB_report","FormB_report",urlstyle);
				win.document.write(data);
				
				
			}
		});
	}
	else{
		swal('Please select Bill Description');
	}
});

$('#btnincomeTaxreport').click(function() {
	var yearName=$('#yearName').val();
	var monthName=$('#monthName').val();
	var billNumber=$('#billno').val();
	var ddoCode=1;
	if(yearName == '0' || yearName==""){
		swal("Please select Year");
		return false ;
	}
	if(monthName == '0' || monthName==""){
		swal("Please select Month Name");
		return false ;
	}
	if(billNumber == '0'){
		swal('Please select the valid Bill Discription');
		return false ;
	}
	if (billNumber != '') {
		 $
		 .ajax({
			 type : "GET",
			 url : context+"/paybill/incomeTaxreport/"+yearName+"/"+monthName+"/"
			 + billNumber+"/"+ddoCode,
				 async : true,
				 contentType : 'application/json',
				 error : function(data) {
					 swal("error");
					 console.log(data);
					 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
					 var win = window.open("","",urlstyle);
			            win.document.write(data);
				 },
				 success : function(data) {
					 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
					 var win = window.open("IcomeTax_report","IcomeTax_report",urlstyle);
			            win.document.write(data);

					 
				 }
		 });
	 
		
	}
	else{
		swal('Please select Bill Description');
	}
});

$('#btnaccidentalInsuranceSchemeReport').click(function() {
	var yearName=$('#yearName').val();
	var monthName=$('#monthName').val();
	var billNumber=$('#billno').val();
	
	
	 ddoCode=$("#logUser").text().trim();
	 var isContainAST=ddoCode.includes("_AST");
	 if(!isContainAST){
		 ddoCode=1;
	 }
	
	
	
	
	if(yearName == '0' || yearName==""){
		swal("Please select Year");
		return false ;
	}
	if(monthName == '0' || monthName==""){
		swal("Please select Month Name");
		return false ;
	}
	if(billNumber == '0'){
		swal('Please select the valid Bill Discription');
		return false ;
	}
	if (billNumber != '') {
		$
		.ajax({
			type : "GET",
			url : context+"/paybill/accidentalInsuranceSchemereport/"+yearName+"/"+monthName+"/"
			+ billNumber+"/"+ddoCode,
			async : true,
			contentType : 'application/json',
			error : function(data) {
				swal("error");
				console.log(data);
				var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
				var win = window.open("","",urlstyle);
				win.document.write(data);
			},
			success : function(data) {
				var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
				var win = window.open("accidentalInsuranceScheme_report","accidentalInsuranceScheme_report",urlstyle);
				win.document.write(data);
				
				
			}
		});
	}
	else{
		swal('Please select Bill Description');
	}
});


$('#btnshowreport').click(function() {
	 var yearName=$('#yearName').val();
	 var monthName=$('#monthName').val();
	 var billNumber=$('#billGroup').val();
	 if(yearName == '0' || yearName==""){
		 swal("Please select Year");
		 return false ;
	 }
	 if(monthName == '0' || monthName==""){
		 swal("Please select Month Name");
		 return false ;
	 }
	 if(billNumber == '0'){
		 swal('Please select the valid Bill Discription');
		 return false ;
	 }
	 if (billNumber != '') {
		 $.ajax({
				type : "GET",
				url : context+"/ddoast/btnShowReport/"+yearName+"/"+monthName+"/"+ billNumber,
				async : false,
				contentType : 'application/json',
				error : function(data) {
				},
				success : function(data) {
					
					$("#billno").val(data);
					 if(parseInt(data) >0 ) {
							 $("#con").show();
							 
						 }
					 else{
						 swal("Bill not generated!!!");
			    		  $("#con").hide();
						 }
				}
			});
	 	}
	});


$('#btnpageWiseAbstractreport').click(function() {
	 var billNumber=$('#billno').val();
	
	 ddoCode=$("#logUser").text().trim();
	 var isContainAST=ddoCode.includes("_AST");
	 if(!isContainAST){
		 ddoCode=1;
	 }
	 
	 
	 
	 
//	 swal("welcome");
	 if(billNumber == '0'){
		 swal('Please select the valid Bill Discription');
		 return false ;
	 }
	 if (billNumber != '') {
		 $
		 .ajax({
			 type : "GET",
			 url : context+"/ddoast/getpageWiseAbstractreport/"
			 + billNumber +"/"+1+"/"+1+"/"+ddoCode,
				 async : true,
				 contentType : 'application/json',
				 error : function(data) {
					 swal("error");
					 console.log(data);
					 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
					 var win = window.open("","",urlstyle);
			            win.document.write(data);
				 },
				 success : function(data) {
					 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
					 var win = window.open("PageWiseAbstract_report","PageWiseAbstract_report",urlstyle);
			            win.document.write(data);

					 
				 }
		 });
	 }
	 else{
		 swal('Please select Bill Description');
	 }
});

$('#btnsvnpcbasicreport').click(function() {
	 var yearName=$('#yearName').val();
	 var monthName=$('#monthName').val();
	 var billNumber=$('#billno').val();
	 
	 ddoCode=$("#logUser").text().trim();
	 var isContainAST=ddoCode.includes("_AST");
	 if(!isContainAST){
		 ddoCode=1;
	 }
	 
	 
	 
	 if(yearName == '0' || yearName==""){
		 swal("Please select Year");
		 return false ;
	 }
	 if(monthName == '0' || monthName==""){
		 swal("Please select Month Name");
		 return false ;
	 }
	 if(billNumber == '0'){
		 swal('Please select the valid Bill Discription');
		 return false ;
	 }
	 if (billNumber != '') {
		 $
		 .ajax({
			 type : "GET",
			 url : context+"/d/sevenPCBasicReport/"+yearName+"/"+monthName+"/"
			 + billNumber+"/"+ddoCode,
				 async : true,
				 contentType : 'application/json',
				 error : function(data) {
					 swal("error");
					 console.log(data);
					 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
					 var win = window.open("","",urlstyle);
			            win.document.write(data);
				 },
				 success : function(data) {
					 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
					 var win = window.open("SeventhPCBasic_report","SeventhPCBasic_report",urlstyle);
			            win.document.write(data);

					 
				 }
		 });
	 }
	 else{
		 swal('Please select Bill Description');
	 }
	});


$('#btnbrokenPeriodreport').click(function() {
	var yearName=$('#yearName').val();
	var monthName=$('#monthName').val();
	var billno=$('#billno').val();
	 var billNumber=$('#billGroup').val();
	if(yearName == '0' || yearName==""){
		swal("Please select Year");
		return false ;
	}
	if(monthName == '0' || monthName==""){
		swal("Please select Month Name");
		return false ;
	}
	if(billNumber == '0'){
		swal('Please select the valid Bill Discription');
		return false ;
	}
	else{
		$.ajax({
			type : "GET",
			url : context+"/ddoast/monthyearBill/"+monthName+"/"+yearName+"/"+billNumber,
			async : true,
			contentType : 'application/json',
			error : function(data) {
				swal("error");
				console.log(data);
			},
			success : function(data) {
				console.log(data);
			if(data!='0'){
				$
				.ajax({
					type : "GET",
					url : context+"/ddoast/brokenPeriodReport/"+billNumber+"/"+1+"/"+1+"/"+billno,
					async : true,
					contentType : 'application/json',
					error : function(data) {
						swal("error");
						console.log(data);
						var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
						var win = window.open("","",urlstyle);
						win.document.write(data);
					},
					success : function(data) {
						var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
						var win = window.open("SeventhPCBasic_report","SeventhPCBasic_report",urlstyle);
						win.document.write(data);
						
						
					}
				});
			}
			else{
				swal("Please generate broken period bill");
			}
				
			}
		});
		
	}
	/*if (billNumber != '') {
		$
		.ajax({
			type : "GET",
			url : "/MJP/paybill/brokenPeriodReport/"+billNumber+"/"+1+"/"+1,
			async : true,
			contentType : 'application/json',
			error : function(data) {
				swal("error");
				console.log(data);
				var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
				var win = window.open("","",urlstyle);
				win.document.write(data);
			},
			success : function(data) {
				var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
				var win = window.open("SeventhPCBasic_report","SeventhPCBasic_report",urlstyle);
				win.document.write(data);
				
				
			}
		});
	}
*/	
});

$('#btnconsolidatedreport').click(function() {
	var yearName=$('#yearName').val();
	var monthName=$('#monthName').val();
	var billNumber=$('#billno').val();
	if(yearName == '0' || yearName==""){
		swal("Please select Year");
		return false ;
	}
	if(monthName == '0' || monthName==""){
		swal("Please select Month Name");
		return false ;
	}
	if(billNumber == '0'){
		swal('Please select the valid Bill Discription');
		return false ;
	}
	if (billNumber != '') {
		$
		.ajax({
			type : "GET",
			url : context+"/paybill/consolidatedreport/"+yearName+"/"+monthName+"/"
			+ billNumber,
			async : true,
			contentType : 'application/json',
			error : function(data) {
				swal("error");
				console.log(data);
				var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
				var win = window.open("","",urlstyle);
				win.document.write(data);
			},
			success : function(data) {
				var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
				var win = window.open("SeventhPCBasic_report","SeventhPCBasic_report",urlstyle);
				win.document.write(data);
				
				
			}
		});
	}
	else{
		swal('Please select Bill Description');
	}
});





$('#allReport').click(function(e) {
//	e.preventDefault();
	 var yearName=$('#yearName').val();
	 var monthName=$('#monthName').val();
	 var billNumber=$('#billno').val();
		
	
    
       
	var data="";
	
	    $(".multiSelect:checked").each(function() {
	    //	alert(this.value);
	    		data=data+getfristpageReport();
	    	
	        if(this.value=="outer"){
	    		data=data+getOuterReport(billNumber);
	    	}
	        else if(this.value=="inner"){
	        	data=data+getInnerReport(billNumber)
	    	}
	        else if(this.value=="abstract"){
	        	data=data+getabstractReport(billNumber)
	        }
	        else if(this.value=="bank"){
	        	data=data+getbankReport(yearName,monthName,billNumber)
	        }
	        else if(this.value=="quito"){
	        	data=data+getquitoReport(yearName,monthName,billNumber)
	        }
	        else if(this.value=="sevenpc"){
	        	data=data+getsevenpcReport(yearName,monthName,billNumber)
	        }
	        else if(this.value=="revenueStamp"){
	        	data=data+getsevenpcReport(yearName,monthName,billNumber)
	        }
	        else if(this.value=="brokenPeriod"){
	        	data=data+getbrokenPeriodReport(billNumber)
	        }
	        else if(this.value=="advance"){
	        	data=data+getadvanceReport(yearName,monthName,billNumber)
	        }
	        else if(this.value=="friadvance"){
	        	data=data+getfriadvanceReport(yearName,monthName,billNumber)
	        }
	        else if(this.value=="incometax"){
	        	data=data+getincometaxReport(yearName,monthName,billNumber)
	        }
	        else if(this.value=="accidins"){
	        	data=data+getaccidinsReport(yearName,monthName,billNumber)
	        }
	        else if(this.value=="pageWiseAbstract"){
	        	data=data+getpageWiseAbstractreport(yearName,monthName,billNumber)
	        }
	    });
	    
	   // e.preventDefault();
	   // console.log(data);
	    var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
		 var win = window.open("Complete_Reports","Complete_Reports",urlstyle);
	   win.document.write(data);
	  
});



function getfristpageReport(){
	var rData;
	$.ajax({
		 type : "GET",
		 url : context+"/paybill/reportdesignsearch",
			 async : false,
			 contentType : 'application/json',
			 error : function(data) {
				 swal("error");
				 console.log(data);
				 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
				 var win = window.open("","",urlstyle);
		            win.document.write(data);
			 },
			 success : function(data) {
		            console.log(data);
				 rData=data;
			 }
	 });
	 return rData;
}

function getOuterReport(billNumber){
	var rData;
	$.ajax({
		 type : "GET",
		 url : context+"/paybill/outerreport/"
		 + billNumber,
			 async : false,
			 contentType : 'application/json',
			 error : function(data) {
				 swal("error");
				 console.log(data);
				 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
				 var win = window.open("","",urlstyle);
		            win.document.write(data);
			 },
			 success : function(data) {
		            console.log(data);
				 rData=data;
			 }
	 });
	 return rData;
}

function getInnerReport(billNumber){
	var rDate;
	 $.ajax({
		 type : "GET",
		 url : context+"/innerreport/getinnerreport/"+ billNumber +"/"+1+"/"+1,
			 async : false,
			 contentType : 'application/json',
			 error : function(data) {
				 swal("error");
				 console.log(data);
				 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
				 var win = window.open("","",urlstyle);
		            win.document.write(data);
			 },
			 success : function(data) {
		            rDate=data;
			 }
	 });
	 return rDate;
}

function getabstractReport(billNumber){
	var rDate;
	 $.ajax({
		 type : "GET",
		 url : context+"/paybill/groupAbstractReport/"
		 + billNumber,
			 async : false,
			 contentType : 'application/json',
			 error : function(data) {
				 swal("error");
				 console.log(data);
				 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
				 var win = window.open("","",urlstyle);
		            win.document.write(data);
			 },
			 success : function(data) {
				 console.log(data);
				 rDate=data;
			 }
	 });

	 return rDate;
}

function getbankReport(yearName,monthName,billNumber){
	var rDate;

	 $.ajax({
		 type : "GET",
		 url : context+"/paybill/bankStatementreport/"+yearName+"/"+monthName+"/"
		 + billNumber,
			 async : false,
			 contentType : 'application/json',
			 error : function(data) {
				 swal("error");
				 console.log(data);
				 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
				 var win = window.open("","",urlstyle);
		            win.document.write(data);
			 },
			 success : function(data) {
				 rDate=data;
			 }
	 });
	 return rDate;
}
function getquitoReport(yearName,monthName,billNumber){
	var rDate;

	 $.ajax({
		 type : "GET",
		 url : context+"/paybill/aquittancereport/"+yearName+"/"+monthName+"/"
		 + billNumber,
			 async : false,
			 contentType : 'application/json',
			 error : function(data) {
				 swal("error");
				 console.log(data);
				 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
				 var win = window.open("","",urlstyle);
		            win.document.write(data);
			 },
			 success : function(data) {
				 rDate=data;
			 }
	 });
	return rDate;
}
function getsevenpcReport(yearName,monthName,billNumber){
	var rDate;
	

	 $.ajax({
		 type : "GET",
		 url : context+"/paybill/sevenPCBasicReport/"+yearName+"/"+monthName+"/"
		 + billNumber,
			 async : false,
			 contentType : 'application/json',
			 error : function(data) {
				 swal("error");
				 console.log(data);
				 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
				 var win = window.open("","",urlstyle);
		            win.document.write(data);
			 },
			 success : function(data) {
				 rDate=data; 
			 }
	 });

	return rDate;
}
function getbrokenPeriodReport(billNumber){
	var rDate;
	
	
	$.ajax({
		type : "GET",
		url : context+"/paybill/brokenPeriodReport/"+billNumber+"/"+1+"/"+1,
		async : false,
		contentType : 'application/json',
		error : function(data) {
			swal("error");
			console.log(data);
			var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
			var win = window.open("","",urlstyle);
			win.document.write(data);
		},
		success : function(data) {
			 rDate=data;			
		}
	});
	
	return rDate;
}
function getadvanceReport(yearName,monthName,billNumber){
	var rDate;
	 $.ajax({
		 type : "GET",
		 url : context+"/paybill/advanceInterestreport/"+yearName+"/"+monthName+"/"+ billNumber,
			 async : false,
			 contentType : 'application/json',
			 error : function(data) {
				 swal("error");
				 console.log(data);
				 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
				 var win = window.open("","",urlstyle);
		            win.document.write(data);
			 },
			 success : function(data) {
				 rDate=data;
			 }
	 });

	
	return rDate;
}
function getfriadvanceReport(yearName,monthName,billNumber){
	var rDate;
	$.ajax({
		type : "GET",
		url : context+"/master/faReportBillWise/"+yearName+"/"+monthName+"/"
		+ billNumber,
		 async : false,
		contentType : 'application/json',
		error : function(data) {
			swal("error");
			console.log(data);
			var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
			var win = window.open("","",urlstyle);
			win.document.write(data);
		},
		success : function(data) {
			 rDate=data;
		}
	});
	return rDate;
}
function getincometaxReport(yearName,monthName,billNumber){
	var rDate;
	$.ajax({
		type : "GET",
		url : context+"/paybill/incomeTaxreport/"+yearName+"/"+monthName+"/"
		+ billNumber,
		 async : false,
		contentType : 'application/json',
		error : function(data) {
			swal("error");
			console.log(data);
			var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
			var win = window.open("","",urlstyle);
			win.document.write(data);
		},
		success : function(data) {
			 rDate=data;
		}
	});
	return rDate;
}
function getaccidinsReport(yearName,monthName,billNumber){
	var rDate;
	$
	.ajax({
		type : "GET",
		url : context+"/paybill/accidentalInsuranceSchemereport/"+yearName+"/"+monthName+"/"
		+ billNumber,
		 async : false,
		contentType : 'application/json',
		error : function(data) {
			swal("error");
			console.log(data);
			var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
			var win = window.open("","",urlstyle);
			win.document.write(data);
		},
		success : function(data) {
			rDate=data;
		}
	});
	return rDate;
}
function getsevenpcReport(yearName,monthName,billNumber){
	var rDate;
	$.ajax({
		 type : "GET",
		 url : context+"/paybill/revenueStampreport/"+yearName+"/"+monthName+"/"
		 + billNumber,
			 async : false,
			 contentType : 'application/json',
			 error : function(data) {
				 swal("error");
				 console.log(data);
				 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
				 var win = window.open("","",urlstyle);
		            win.document.write(data);
			 },
			 success : function(data) {
				 rDate=data;
			 }
	 });
	return rDate;
}
function getpageWiseAbstractreport(yearName,monthName,billNumber){
	var rDate;
	$.ajax({
		type : "GET",
		 url : context+"/paybill/getpageWiseAbstractreport/"
			 + billNumber +"/"+1+"/"+1,
		async : false,
		contentType : 'application/json',
		error : function(data) {
			swal("error");
			console.log(data);
			var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
			var win = window.open("","",urlstyle);
			win.document.write(data);
		},
		success : function(data) {
			rDate=data;
		}
	});
	return rDate;
}





