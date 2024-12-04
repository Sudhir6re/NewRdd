/*$("#cmbLoanType").change(function(){
	var cmbLoanType=$("#cmbLoanType").val();
	var sevaarthId=$("#txtSevaarthId").val();
	isRetirementAgeLessthen5year(sevaarthId);

	switch(cmbLoanType){
	case "1":  //computer advance
		break;
	case "2": //House Advance
		break;
	case "3":  //Motor Vehicle Advance
		break;
	case "4": //Festival Advance
		break;
	}
	
});*/



$("#searchDiv").hide();

$('body').on('click', '.empdata', function() {
	var sevaarthId=$(this).attr("empsevaathid");  
	$("#txtSevaarthId").val(sevaarthId);
	var txtEmployeeName=$(this).attr("empname");  
	$("#txtEmployeeName").val(txtEmployeeName);
	document.getElementById("searchDiv").innerHTML = "";
	$("#searchDiv").hide();
	$("#cmbLoanType").val("0");
});



$("#txtSevaarthId").blur(function(){
	
	setTimeout(function () {
	$("#searchDiv").hide();
}, 200);
});


$("#txtSevaarthId").keyup(function(){
	 var txtSevaarthId=$("#txtSevaarthId").val();
	if(txtSevaarthId!=''){
		 // $("#loaderMainNew").show();
			$.ajax({
				type : "GET",
				url : "../level1/fetchNameEmployeeNametoSevaarthid/"
						+ txtSevaarthId,
					
				async : true,
				contentType : 'application/json',
				error : function(data) {
					 console.log(data);
				// alert("error");
					 $("#loaderMainNew").hide();
				},
				success : function(data) {
					 console.log(data);
					 $("#loaderMainNew").hide();
					var len = data.length;
					//start success
					
				/*	
					if (len != 0 ) { 
						if(data[0].txtEmployeeName!="0"){
							$("#txtEmployeeName").val("");
							$("#txtEmployeeName").val(data[0].txtEmployeeName);
							$("#txtSevaarthId").val(data[0].sevaarthId);
							
						}else{
							$("#txtEmployeeName").val("");
							$("#txtSevaarthId").val("");
							   alert("Employee not Found");
						   }
				   }
					 $("#loaderMainNew").hide();*/
					 
					 
					 
					 console.log(data);
						$("#loaderMainNew").hide();
						document
								.getElementById("searchDiv").innerHTML = "";
						for (var i = 0; i < data.length; i++) {
							
							$("#searchDiv").show();
							//$("#searchDiv").append(data[i].employeeName+"-"+data[i].sevaarthId);
							//$("#searchDiv").css("border:1px solid #A5ACB2;");
							$("#searchDiv")
									.append(
											"<p class='empdata' empid='"+data[i].sevaarthId+"' empname='"+data[i].txtEmployeeName+"' empsevaathid='"+data[i].sevaarthId+"'   empdesgn='"+data[i].txtEmployeeName+"'>"
													+ data[i].txtEmployeeName
													+ "-"
													+ data[i].sevaarthId
													+ "</p>");
							
							
						//	$("#sevaarthIdCopy").val(data[i].sevaarthId);
							
							$("#searchDiv")
									.css(
											"border:1px solid #A5ACB2;");
						}
						
						if(data.length==0){
							swal("No Employee Found");
						}
					 
					 //end succcess
				}
			});
	}
  });
  
  
$("#cmbLoanType").change(function(){
	  var varCmbLoanType=$("#cmbLoanType").val();
	  if(varCmbLoanType=='1'){
		  $("#personLoanAndAdvanceForm").attr("action", "../level1/PersonLoanAndAdvanceNewPage"); 
	  }else  if(varCmbLoanType=='2'){
		  $("#personLoanAndAdvanceForm").attr("action", "../level1/HouseLoansAdvance"); 
	  }
	  else  if(varCmbLoanType=='3'){
		  $("#personLoanAndAdvanceForm").attr("action", "../level1/VehicleAdvance"); 
	  }else  if(varCmbLoanType=='4'){
		  $("#personLoanAndAdvanceForm").attr("action", "../level1/FestivalAdvance"); 
	  }else  if(varCmbLoanType=='5'){
		  $("#personLoanAndAdvanceForm").attr("action", "../level1/motorVehicleAdvance"); 
	  }
	  
	   
	    var cmbLoanType=$("#cmbLoanType").val();
		var sevaarthId=$("#txtSevaarthId").val();
		var flag=isRetirementAgeLessthen5year(sevaarthId);
		if(flag=="1" && varCmbLoanType=='2'){
			$("#btnSubmitData").prop("disabled",true);
			alert("This Employee is not Eligible for House Bulilding Advance because his remaining service period is less than 5 years");
		}else{
			$("#btnSubmitData").prop("disabled",false);
		}
		
		
		
		
		flag=checkAlreadyAppliedForLoan(sevaarthId,cmbLoanType);
		if(flag=="1"){
			swal("Loan is already taken");
			
			
			  if(varCmbLoanType=='1'){
				  $("#personLoanAndAdvanceForm").attr("action", ""); 
			  }else  if(varCmbLoanType=='2'){
				  $("#personLoanAndAdvanceForm").attr("action", ""); 
			  }
			  else  if(varCmbLoanType=='3'){
				  $("#personLoanAndAdvanceForm").attr("action", ""); 
			  }else  if(varCmbLoanType=='4'){
				  $("#personLoanAndAdvanceForm").attr("action", ""); 
			  }
			  
		}
	
});





$("form[name='personLoanAndAdvanceForm']").validate({
   rules: {
   	txtSevaarthId: "required",
   	txtEmployeeName: "required",
   	cmbLoanType:{
   		required: true,
   		min:1
   	},
   },
   messages: {
   	txtSevaarthId: "Please enter SevaarthId",
   	txtEmployeeName: "Please enter employee name",
   	cmbLoanType: "Please select Request type",
   },
   submitHandler: function(form) {
   	 $("#loaderMainNew").show();
     form.submit();
   }
 });

function isRetirementAgeLessthen5year(sevaarthId){
	 	var flag=0;		 
	 	$.ajax({
			type : "GET",
			url : "../level1/isRetirementAgeLessthen5year/"+sevaarthId,
			async : false,
			contentType : 'application/json',
			error : function(data) {
				 console.log(data);
			},
			success : function(data) {
				 console.log(data);
				 flag=data;
			}
		});	
		 return flag;
}



function checkAlreadyAppliedForLoan(sevaarthId,loanType){
	 	var flag=0;		 
	 	$.ajax({
			type : "GET",
			url : "../level1/checkAlreadyAppliedForLoan/"+sevaarthId+"/"+loanType,
			async : false,
			contentType : 'application/json',
			error : function(data) {
				 console.log(data);
			},
			success : function(data) {
				 console.log(data);
				 flag=data;
			}
		});	
		 return flag;
}