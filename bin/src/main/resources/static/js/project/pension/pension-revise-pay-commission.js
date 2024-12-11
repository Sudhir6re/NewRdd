jQuery(document).ready(function() {
	$("#searchDiv").hide();
	
	if($("#message").val()!=''){
		alert($("#message").val());
	}
	
	$('input[type=radio][name=haveSevaarthId]').change(function(){
		
	var ischecked=$('input[type=radio][name=haveSevaarthId]:checked').val();	
		
	    if (ischecked == 'Yes') {
	    //	$("#sevaarthIdBlock").show();
	    	$("#txtSevaarthId").attr("readonly", false); 
	    }
	    else if (ischecked == 'No') {
	    	//$("#sevaarthIdBlock").hide();
	   	 $("#procceed").attr("disabled", false); 
	 	$("#txtSevaarthId").attr("readonly", true); 
	    }
	});
	
	/*$('body').on('click', '.empdata', function() {
		 $("#procceed").attr("disabled", false); 
		var sevaarthId=$(this).attr("empsevaathid");  
		$("#txtSevaarthId").val(sevaarthId);
		var txtEmployeeName=$(this).attr("empname");  
		$("#txtEmployeeName").val(txtEmployeeName);
		document.getElementById("searchDiv").innerHTML = "";
		$("#searchDiv").hide();
		getPensSevaarthIdAlreadyExist(sevaarthId);
	});*/
	$("#txtSevaarthId").blur(function(){
		setTimeout(function () {
		$("#searchDiv").hide();
	}, 200);
		 
	});
	
	$("#rebasicPer").blur(function(){
	var percentage=$("#rebasicPer").val();
	var newBasicPen= (((Number($("#oldpan").val())*Number(percentage)))/100);
	
	newBasicPen=Number(newBasicPen.toFixed(2))+Number($("#oldpan").val());
	
	$("#newpan").val(newBasicPen);
	
	var newFpAmount= (((Number($("#OldFpAmt").val())*Number(percentage)))/100);	
	
	newFpAmount=Number(newBasicPen.toFixed(2))+Number($("#OldFpAmt").val());
	
	       $("#NewFpAmt").val(newFpAmount);
	       
	var newEfpAmount=(((Number($("#OldEfpAmt").val())*Number(percentage)))/100);
	
	newEfpAmount=Number(newBasicPen.toFixed(2))+Number($("#OldEfpAmt").val());
	
	            $("#NewEfpAmt").val(newEfpAmount);
	            
	});
	$("#PpoNumber").blur(function(){
		 var PpoNumber=$("#PpoNumber").val();
		if(PpoNumber!=''){
			 // $("#loaderMainNew").show();
				$.ajax({
					type : "GET",
					url : "../master/findEmployeeBySevaarthId/"
							+ PpoNumber,
						
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
					
						console.log(data);
							$("#loaderMainNew").hide();
							document
									.getElementById("searchDiv").innerHTML = "";
							for (var i = 0; i < data.length; i++) {
								
							/*	$("#searchDiv").show();
								$("#searchDiv")
										.append(
												"<p class='empdata' empid='"+data[i].ppoNo+"' empname='"+data[i].employeeName+"' empsevaathid='"+data[i].ppoNo+"' ppoNo='"+data[i].employeeName+"'>"
														+ data[i].employeeName
														+ "-"
														+ data[i].ppoNo
														+ "</p>");
								
							//	$("#sevaarthIdCopy").val(data[i].sevaarthId);
								
								$("#searchDiv")
										.css(
												"border:1px solid #A5ACB2;");*/
								
								
								//$("#PpoNumber").val(data[i].employeeName);
								$("#oldpayCommision").val(data[i].oldPayCommissionCode);
							//	$("#newpayCommision").val(data[i].newPayCommissionCode);
								$("#oldpan").val(data[i].oldPensionAmt);
								$("#newpan").val(data[i].newPensionAmt);
								$("#OldFpAmt").val(data[i].oldFpAmt);
								$("#NewFpAmt").val(data[i].newFpAmt);
								$("#OldEfpAmt").val(data[i].oldEfpAmt);
								$("#NewEfpAmt").val(data[i].newEfpAmt);
								$("#rebasicPer").val(data[i].revisedBasicPercent);
								
								
							}
							
							if(data.length==0){
								$("#txtSevaarthId").val("");
								swal("Please enter valid sevaarth id");
							}
						 //end succcess
					}
				});
		}
	  });
	
});


/*function getPensSevaarthIdAlreadyExist(sevaarthId){
	$.ajax({
		type : "GET",
		url : "../pension/getPensSevaarthIdAlreadyExist/"
				+ sevaarthId,
		async : true,
		contentType : 'application/json',
		error : function(data) {
			 console.log(data);
		},
		success : function(data) {
			 console.log(data);
			var len = data;
			if (len != 0) { 
		    	  swal("SevaarthId Already Present In System!", {
		    	      icon: "warning",
		    	  });
		    	  $("#txtSevaarthId").val("");
		    	  $("#procceed").attr("disabled", true); 
		    	  setTimeout(function() {
					    location.reload(true);
					}, 3000);
		   }
		}
	});
}*/

$("#procceed").click(function(e){
	
	
	var ischecked=$('input[type=radio][name=haveSevaarthId]:checked').val();	
		
	if($("#txtSevaarthId").val()=='' && ischecked=="Yes"){
		 swal("Please enter SevaarthId id!", {
   	      icon: "warning",
   	   
   	  });
		 $("#procceed").attr("disabled", true); 
		 e.preventDefault();
	}else if($("#cmbClassOfPnsn").val()=='0'){
		 swal("Please select pension type!", {
	   	      icon: "warning",
	   	
	   	  });
		   e.preventDefault();
	}
	else{
		if($("#txtSevaarthId").val()!='' && $('input[type=radio][name=haveSevaarthId]').val()=="Yes"){
		getPensSevaarthIdAlreadyExist($("#txtSevaarthId").val());
		}
		 $("#procceed").attr("disabled", false); 
	}
});
$("form[name='myForm']").validate({
    // Specify validation rules
    rules: {
		PpoNumber : {
			required : true,
			//min : 1
		},
		oldPensionAmt : {
			required : true,
			
		},
		oldpayCommision : {
			required : true,
			
		},
		revisedBasicPercent : {
			required : true,
			
		},
		newFpAmt : {
			required : true,
			
		},
		newEfpAmt : {
			required : true,
			
		},
		newpayCommision : {
			required : true,
			
		},
		newPensionAmt : {
			required : true,
			
		},
		oldFpAmt : {
			required : true,
			
		},
		oldEfpAmt : {
			required : true,
			
		},
		withEffectiveDate : {
			required : true,
			
		},
		rebasicPer : {
			required : true,
			
		},
    },
    // Specify validation error messages
    messages: {
    	PpoNumber: "Please Enter SevaarthId",
    	oldPensionAmt: "Please Enter Old Pension Amount",
    	oldpayCommision: "Please select Old pay commission",
    	revisedBasicPercent: "Please Enter Percent of Increment",
    	oldFpAmt: "Please select Old pay commission",
    	oldEfpAmt: "Please select Old pay commission",
    	withEffectiveDate: "Please select With Effective Date",
    	newpayCommision: "Please enter New pay commission",
    	rebasicPer: "Please enter your revised basic percentage",
    },
    // in the "action" attribute of the form when valid
    submitHandler: function(form,event) {
    	form.submit();
    }
  });







