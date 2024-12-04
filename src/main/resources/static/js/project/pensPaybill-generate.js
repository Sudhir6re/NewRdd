jQuery(document).ready(function($) {
	var varMessage = $("#message").val();
	if(varMessage != "" && varMessage != undefined) {
		if(varMessage=="SUCCESS")
			{
		 Swal.fire({
			  title: 'Are you sure?',
			 text: "To view Pension bill generation of change statement!",
			  showDenyButton: false,
			  showCancelButton: true,
			  confirmButtonText: `Ok`,
			  /* denyButtonText: `Cancel`, */
			}).then((result) => {
			  if (result.isConfirmed) {
			   window.location.href="../paybill/penspayBillViewApprDelBill";
			  } else if (result.isDenied) {
			  }
			});
	}
	else
		{
		Swal.fire("Pension bill Is Already Generated!!");
		}
	}
});
// is in process

function checkIsPaybillInProcess(schemeBillGroupId ,monthName,yearName)
{
	$("#loaderMainNew").show();
	flag=0;
	 $.ajax({
	      type: "GET",
	      url: "../master/checkBillIsInProcess/"+schemeBillGroupId+"/"+monthName +"/"+yearName,
	      async: false,
	      dataType : 'json',
	      error: function(data){
	    	  console.log(data);
	    		$("#loaderMainNew").hide();
	      },
	      success: function(data){
	  		$("#loaderMainNew").hide();
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




/*
 * function PaybillValidationForNotNullColumns(billNumber,monthName,yearName) {
 * var flag=0; flag=0; $.ajax({ type: "GET", url:
 * "../master/PaybillValidationForNotNullColumns/"+billNumber+"/"+monthName
 * +"/"+yearName, async: false, dataType : 'json', //
 * contentType:'application/json', error: function(data){ console.log(data); //
 * alert(data); }, success: function(data){ console.log(data); flag=data; } });
 * return flag; }
 */
// fetchNoof employee mapped with ddo
function fetchNoOfEmployeeMappedWithDdo(yearName,paybillType,schemeBillGroupId)
{
	$("#loaderMainNew").show();
 	var flag=0;		 
 	var yearName = $("#paybillYear").val();
	var monthName = $("#paybillMonth").val();
 	$.ajax({
		type : "GET",
		url : "../master/getNumberOfEmp/"+monthName+"/"+yearName+"/"+schemeBillGroupId,
		async : false,
		contentType : 'application/json',
		error : function(data) {
			 console.log(data);
			$("#loaderMainNew").hide();
		},
		success : function(data) {
			$("#loaderMainNew").hide();
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
	$("#loaderMainNew").show();
	var msg=0;		 
	$.ajax({
		type : "GET",
		url : "../master/getPensEmployeeMappedWithAllowanceDeduction/"+schemeBillGroupId,
		async : false,
		contentType: "application/json",
        dataType: "json",
		error : function(data) {
		 console.log(data);
		 $("#loaderMainNew").hide();
	// alert("erro");
		},
		success : function(data) {
			$("#loaderMainNew").hide();
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
	$("#loaderMainNew").show();
	
	$.ajax({
				type : "GET",
				url : "../master/listSchemeDetails/"
						+ data,
						 async: false,
				contentType : 'application/json',
				error : function(data) {
					$("#loaderMainNew").hide();
					// console.log(data);
				},
				success : function(data) {
					$("#loaderMainNew").hide();
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
		
			// var billNumber= $("#bankId").val();
			// var schemeBillGroupId= $("#bankId").val();
				var yearName = $("#paybillYear").val();
				var monthName = $("#paybillMonth").val();
				var paybillType = $("#paybillType").val();
				//alert("paybillType"+paybillType);
				// var isEmpAllDataField=PaybillValidationForNotNullColumns();
			
				   const date1 = new Date();
				    var year=$("#paybillYear").val();
				    var month=$("#paybillMonth").val();
					
					var day=date1.getDate();
					
				    var g3=month+"/"+day+"/20"+(parseInt(year)-1);
				    
				    
				       
					const date2 = new Date(g3);
					
				if(date2.getFullYear()>date1.getFullYear()){
					swal.fire("Future date not allowed for pension bill!!!");	
				}
				else if(month>Number(date1.getMonth())+1 && date2.getFullYear()>=date1.getFullYear() ){
					swal.fire("Future date not allowed for pension bill!!!");
				}else{
				var retired='';
				$("#loaderMainNew").show();
				/*
				 * $.ajax({ type : "GET", url :
				 * "../master/isEmpRetired/"+monthName+"/"+yearName+"/"+schemeBillGroupId+"/"+paybillType,
				 * async : false, contentType: "application/json", // dataType:
				 * "json", error : function(data) { console.log(data);
				 * $("#loaderMainNew").hide(); e.preventDefault(); }, success :
				 * function(data) { retired=data; $("#loaderMainNew").hide(); }
				 * });
				 */
				var dataFromServer;
				var len;
	
		 /*$.ajax({
			 type : "GET", // url :
		 
		  "../level1/getSevaarthIdMappedWithBill/"+billNumber+"/"+monthName+"/"+yearName,
		  url :
		 "../level1/getSevaarthIdMappedWithBill/"+monthName+"/"+yearName,
		  async : false, contentType: "application/json", dataType: "json",
		  error : function(data) {
			  console.log(data);
			  },
		  success : function(data)
		  { 
			  $("#loaderMainNew").hide();
		  console.log("data"); console.log(data); len = data.length;
		  dataFromServer=data;
		  }
			  });
		*/
				if($("#noOfEmployee").val()=="0"){
					swal.fire("No Employee Found !!!");
					$("#btnSave").prop("disabled",true);
				}
				else if (yearName == "" || yearName == "0") {
					e.preventDefault();
					swal.fire("Please select pension bill  year");
				}
				else if (monthName == "" || monthName == "0") {
					e.preventDefault();
					swal.fire("Please select pension bill month");
				} 
				
				 else{
					 e.preventDefault();
					 $("#loaderMainNew").show();
				 $("#savePaybill").submit();
				 }
				
				}
				// end save button
				
			});
	$('#ddoCode').val($('#logUser').text());
	
	
	
	
	$("#pensSchemebillGroupId").change(function() {
		var logUser=$('#logUser').text();
		var data = $('option:selected', this).attr('data');
		var schemeBillGroupId=$(this).val();
		var billNumber= $("#pensSchemebillGroupId").val();
		var yearName = $("#paybillYear").val();
		var monthName = $("#paybillMonth").val();
		if(schemeBillGroupId!="0"){
			if (schemeBillGroupId!="0" || schemeBillGroupId!="" ) {
				var isInProcess=checkIsPaybillInProcess(billNumber,monthName,yearName);
				if(isInProcess=="0"){
				var noEmp=fetchNoOfEmployeeMappedWithDdo(monthName,logUser,schemeBillGroupId);
				if(noEmp>0){
					$("#btnSave").prop("disabled",false);
					var isAllowanceData=isAllowancedeductionMappedWithEmp(schemeBillGroupId);
					
					if(isAllowanceData=="0"){
						$("#btnSave").prop("disabled",false);
						//listData(data);
						///var isAllEmpData=PaybillValidationForNotNullColumns(billNumber,monthName,yearName);
						//if(isAllEmpData!="0"){
					
						//}else{
							//swal("emp Data not filled");
						//}*/
					}
				else{
				     //	swal.fire("This ppo no '"+isAllowanceData+"' has not mapped with Allowance Deduction");
					//$("#btnSave").prop("disabled",true);
					}
				}
				else{
					swal.fire("No employee is mapped with Bill Group !!!");
				}
			
			}
				else{
					$("#btnSave").prop("disabled",true);
					swal.fire("pension bill is in process !!!");
				}
			}
		}
		else{
			swal.fire("Please select Bill Group");
			$("#btnSave").prop("disabled",true);
		}
			
	});
	
	
	
	$("#paybillType").change(function(){
		
		var paybillType=$(this).val();
		 var schemeBillGroupId= $("#pensSchemebillGroupId").val(); 
		var yearName = $("#paybillYear").val();
		var monthName = $("#paybillMonth").val();
		
		if((paybillType!=''|| paybillType==undefined)   
				&& (yearName!='0'|| yearName==undefined)  && (monthName!='0'|| monthName==undefined)){
			var flag=checkPensPaybillIsInprocess(monthName,yearName,schemeBillGroupId,paybillType);
	//		var noEmp=fetchNoOfEmployeeMappedWithDdo(yearName,paybillType,schemeBillGroupId);
			if(parseInt(flag)>0){
				swal.fire("pension bill Already Generated !!!!!");
				$("#btnSave").prop("disabled",true);
			}else{
				$("#btnSave").prop("disabled",false);
			}
		}
		
	});
	
	// fetchNoof employee mapped with ddo
	function checkPensPaybillIsInprocess(monthName,yearName,schemeBillGroupId,paybillType)
	{
		$("#loaderMainNew").show();
		
	////	var schemeBillGroupId=0;
		
	 	var flag=0;		 
	 	$.ajax({
			type : "GET",
			url : "../master/checkPensPaybillIsInprocess/"+monthName+"/"+yearName+"/"+schemeBillGroupId+"/"+paybillType,
			async : false,
			contentType : 'application/json',
			error : function(data) {
				$("#loaderMainNew").hide();
			},
			success : function(data) {
				 console.log(data);
				 flag=data;
				 $("#loaderMainNew").hide();
			}
		});	
		 return flag;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/* Hide and show Contents on selection of year */
	$('#paybillYear').on('change', function() {
		 /* alert( this.value ); */
		
		  $(".monthhide").css("visibility","visible");
		});
	$('#paybillMonth').on('change', function() {
		 /* alert( this.value ); */
		//var yearName = $("#paybillYear").val();
		//var noEmp=fetchNoOfEmployeeMappedWithDdo(yearName);
		  $(".billgphide").css("visibility","visible");
		});
	$('#schemeBillGroupId').on('change', function() {
		 /* alert( this.value ); */
		  $(".disableTillyear").css("visibility","visible");
		});
});