jQuery(document).ready(function($) {
	var varMessage = $("#message").val();
	if(varMessage != "" && varMessage != undefined) {
		if(varMessage=="SUCCESS")
			{
		 Swal.fire({
			  title: 'Are you sure?',
			 text: "To view paybill generation of change statement!",
			  showDenyButton: true,
			  showCancelButton: true,
			  confirmButtonText: `Ok`,
			  denyButtonText: `Cancel`,
			}).then((result) => {
			  if (result.isConfirmed) {
			   window.location.href="../paybill/payBillViewApprDelBill";
			  } else if (result.isDenied) {
			  }
			});
	}
	else
		{
		Swal.fire("Paybill Is Already Generated!!");
		}
	}
});
// is in process
function checkIsPaybillInProcess(billNumber,monthName,yearName)
{
	flag=0;
	 $.ajax({
	      type: "GET",
	      url: "../master/PaybillValidationForContract/"+billNumber+"/"+monthName +"/"+yearName,
	      async: false,
	      dataType : 'json',
	    // contentType:'application/json',
	      error: function(data){
	    	  console.log(data);
	    	 // alert(data);
	      },
	      success: function(data){
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


//is paybill already generated
function checkIsPaybillInProcess(billNumber,monthName,yearName)
{
	flag=0;
	 $.ajax({
	      type: "GET",
	      url: "../master/PaybillValidationForContract/"+billNumber+"/"+monthName +"/"+yearName,
	      async: false,
	      dataType : 'json',
	    // contentType:'application/json',
	      error: function(data){
	    	  console.log(data);
	    	 // alert(data);
	      },
	      success: function(data){
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

function checkForDDODataNotNull()
{
 	 var flag=0;
 	 
	 return flag;
}




/*function PaybillValidationForNotNullColumns(billNumber,monthName,yearName)
{
 	var flag=0;		   
 	flag=0;
	 $.ajax({
	      type: "GET",
	      url: "../master/PaybillValidationForNotNullColumns/"+billNumber+"/"+monthName +"/"+yearName,
	      async: false,
	      dataType : 'json',
	    // contentType:'application/json',
	      error: function(data){
	    	  console.log(data);
	    	 // alert(data);
	      },
	      success: function(data){
	    	  console.log(data);
	    	 flag=data;
	    }
	 });
	 return flag;
}
*/
// fetchNoof employee mapped with ddo
function fetchNoOfEmployeeMappedWithDdo(logUser,schemeBillGroupId)
{
 	var flag=0;		 
 	var yearName = $("#paybillYear").val();
 	//yearName=(parseInt(yearName) -2) ;
	var monthName = $("#paybillMonth").val();
	//alert("monthName"+monthName);
	//alert("yearName"+yearName);
 	$.ajax({
		type : "GET",
		url : "../master/getNumberOfEmployeeForContract/"+logUser+"/"+schemeBillGroupId+"/"+monthName+"/"+yearName,
		async : false,
		contentType : 'application/json',
		error : function(data) {
			// console.log(data);
		},
		success : function(data) {
			 console.log(data);
			 flag=data;
			// $('#noOfEmployee').empty();
			 $('#noOfEmployee').val(data );
		}
	});	
	 return flag;
}



// isAllowancededductionMappedWithEmp
function isAllowancedeductionMappedWithEmp(schemeBillGroupId)
{
	var msg=0;		 
	$.ajax({
		type : "GET",
		url : "../master/getEmployeeMappedWithAllowanceDeductionForContract/"+schemeBillGroupId,
		async : false,
		contentType: "application/json",
        dataType: "json",
		error : function(data) {
		 console.log(data);
	// alert("erro");
		},
		success : function(data) {
			var len = data.length;
			if (len != 0) {
			 console.log(data);
			// var msg="";
					$.each(data,function(index,value) {
						if(msg==""){
							msg=value[0];
						}else{
							msg=msg+','+value[0];
						}
					});
					// swal.fire("This sevaarth id '"+msg+"' has not mapped with
					// Allowance Deduction");
				// $("#btnSave").prop("disabled",false);
			}
		}
    });
	 return msg;
}



function listData(data)
{
	$.ajax({
				type : "GET",
				url : "../master/listSchemeDetailsForContract/"
						+ data,
						 async: false,
				contentType : 'application/json',
				error : function(data) {
					// console.log(data);
				},
				success : function(data) {
					 console.log(data);
					// alert(data);
					var len = data.length;
					if (len != 0) {
						console.log(data);
						var temp = data;
						$
								.each(
										temp,
										function(
												index,
												value) {
											$.each(value, function (index, value1)  
									                {  
									                    $('#'+index).val(value1);
									                }); 
										});
					} else {
							$('#schemeName').val("");
							$('#majorHead').val("");
							$('#minorHead').val("");
							$('#subMajorHead').val("");
							$('#subMinorHead').val("");
							$('#noOfEmployee').val("0");
						    $('#districtId').empty();
			            	$('#districtId').append("<option value='0'>Please Select</option>");
			            	$("#btnSave").prop("disabled",true);
					}
				}
			});
}



$(document).ready(function(){
	$("#btnSave").click(function(e) {
				var billNumber= $("#schemeBillGroupId").val();
				var yearName = $("#paybillYear").val();
				var monthName = $("#paybillMonth").val();
				var paybillType = $("#paybillType").val();
				//var isEmpAllDataField=PaybillValidationForNotNullColumns();
				var dataFromServer;
				var len;
				$.ajax({
					type : "GET",
					url : "../master/getSevaarthIdMappedWithBillForContract/"+billNumber+"/"+monthName+"/"+yearName,
					async : false,
					contentType: "application/json",
			        dataType: "json",
					error : function(data) {
					 console.log(data);
					// alert("erro");
					},
					success : function(data) {
						 console.log("data");
						 console.log(data);
						len = data.length;
						dataFromServer=data;
					}
			    });
				if($("#noOfEmployee").val()=="0"){
					swal.fire("No Employee Found !!!");
					$("#btnSave").prop("disabled",true);
				}
				else if (yearName == "" || yearName == "0") {
					e.preventDefault();
					swal.fire("Please select paybill year");
				}
				else if (monthName == "" || monthName == "0") {
					e.preventDefault();
					swal.fire("Please select paybill month");
				} 
				 else if (billNumber == "" || billNumber == "0") {
					e.preventDefault();
					swal.fire("Please select Bill Group");
				} 
				 else if (paybillType == "" || paybillType == "0") {
					 e.preventDefault();
					 swal.fire("Please select paybill type");
				 } 
				 else if ($("#isCityCategory").val()=="0") {
					 e.preventDefault();
					 swal.fire("City class is not available for this DDO...Please fill then details from office master");
				 } 
				 else if (len != 0){
						e.preventDefault();
						swal.fire("Paybill is already generated for this sevaarth id");
						console.log(data);
						$("#btnSave").prop("disabled",true);
					}
				
				/*
				 * else if (isEmpAllDataField != 0){ e.preventDefault();
				 * swal.fire("Employee data is incomplete"); console.log(data); }
				 */
				 else  if(paybillType==2){
					 
					var noemp= $("#noOfEmployee").val();
					  var len1;
						$.ajax({
							type : "GET",
							url : "../master/getSevaarthIdMappedWithBrokenPeriodForContract/"+noemp+"/"+billNumber+"/"+monthName+"/"+yearName,
							async : false,
							contentType: "application/json",
					        dataType: "json",
							error : function(data) {
							 console.log(data);
							// alert("erro");
							},
							success : function(data) {
//								 console.log("data");
//								 alert(data[0]);
								if(data[0]){
									 e.preventDefault();
									 swal.fire("Some Employee not  in Brokenperiod("+data[1]+")");
									 $("#btnSave").prop("disabled",true);
								}else{
									e.preventDefault();
									 $("#savePaybill").submit();
								}
//								dataFromServer=data;
							}
					    });
				 }
				 else{
					 e.preventDefault();
				 $("#savePaybill").submit();
				 }
				
			});
	$('#ddoCode').val($('#logUser').text());
	$("#schemeBillGroupId").change(function() {
		var logUser=$('#logUser').text();
		var data = $('option:selected', this).attr('data');
		var schemeBillGroupId=$(this).val();
		var billNumber= $("#schemeBillGroupId").val();
		var yearName = $("#paybillYear").val();
		var monthName = $("#paybillMonth").val();
		if(schemeBillGroupId!="0"){
			if (data != '' || data != "0" || data != "undefined" || schemeBillGroupId!="0" || schemeBillGroupId!="" ) {
				var isInProcess=checkIsPaybillInProcess(billNumber,monthName,yearName);
				if(isInProcess=="0"){
				var noEmp=fetchNoOfEmployeeMappedWithDdo(logUser,schemeBillGroupId);
				if(noEmp>0){
					$("#btnSave").prop("disabled",false);
					var isAllowanceData=isAllowancedeductionMappedWithEmp(schemeBillGroupId);
					
					if(isAllowanceData=="0"){
						$("#btnSave").prop("disabled",false);
						listData(data);
						///var isAllEmpData=PaybillValidationForNotNullColumns(billNumber,monthName,yearName);
						//if(isAllEmpData!="0"){
					
						//}else{
							//swal("emp Data not filled");
						//}*/
					}
				else{
					swal.fire("This sevaarth id '"+isAllowanceData+"' has not mapped with Allowance Deduction");
					$("#btnSave").prop("disabled",true);
					}
				}
				else{
					swal.fire("No employee is mapped with Bill Group !!!");
				}
			
			}
				else{
					$("#btnSave").prop("disabled",true);
					swal.fire("Paybill is in process !!!");
				}
			}
		}
		else{
			swal.fire("Please select Bill Group");
			$("#btnSave").prop("disabled",true);
		}
			
	});
	
	
	/* Hide and show Contents on selection of year */
	$('#paybillYear').on('change', function() {
		 /* alert( this.value ); */
		  $(".monthhide").css("visibility","visible");
		});
	$('#paybillMonth').on('change', function() {
		 /* alert( this.value ); */
		  $(".billgphide").css("visibility","visible");
		});
	$('#schemeBillGroupId').on('change', function() {
		 /* alert( this.value ); */
		  $(".disableTillyear").css("visibility","visible");
		});
});