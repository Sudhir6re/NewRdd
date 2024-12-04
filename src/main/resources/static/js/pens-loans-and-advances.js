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



$("#txtSevaarthId").blur(function(){
	 var txtSevaarthId=$("#txtSevaarthId").val();
	if(txtSevaarthId!=''){
		  $("#loaderMainNew").show();
			$.ajax({
				type : "GET",
				url : "../master/fetchNameEmpNametoSevaarthid/"
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
					var len = data.length;
					if (len != 0 ) { 
						if(data[0].txtEmployeeName!="0"){
							$("#txtEmployeeName").val("");
							$("#txtEmployeeName").val(data[0].txtEmployeeName);
							$("#txtSevaarthId").val(data[0].sevaarthId);
							
						}else{
							$("#txtEmployeeName").val("");
							$("#txtSevaarthId").val("");
							   swal("Employee not Found");
						   }
				   }
					 $("#loaderMainNew").hide();
				}
			});
	}
  });
  
  
$("#cmbLoanType").change(function(){
	  var varCmbLoanType=$("#cmbLoanType").val();
	  if(varCmbLoanType=='1'){
		  $("#personLoanAndAdvanceForm").attr("action", "../master/PersonLoanAndAdvanceNewPage"); 
	  }else  if(varCmbLoanType=='2'){
		  $("#personLoanAndAdvanceForm").attr("action", "../master/HouseLoansAdvance"); 
	  }
	  else  if(varCmbLoanType=='3'){
		  $("#personLoanAndAdvanceForm").attr("action", "../master/VehicleAdvance"); 
	  }else  if(varCmbLoanType=='4'){
		  $("#personLoanAndAdvanceForm").attr("action", "../master/FestivalAdvance"); 
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
			url : "../master/isRetirementAgeLessthen5year/"+sevaarthId,
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
			url : "../master/checkAlreadyAppliedForLoan/"+sevaarthId+"/"+loanType,
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