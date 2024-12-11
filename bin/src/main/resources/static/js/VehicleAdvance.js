function validateDate(){
	
}

$(document).ready(function(){
	
     var context=$("#context").val();
	//alert(context);
	if($("#message").val()=="1") {
		Swal.fire("Data Saved Successfully !!!");
	}
	
	

		
	
	$("#btnSave").hide();
	$("#btnForward").hide();
	
	$("#chkValidate").change(function(){
		var checked=$(this).prop('checked');
		
		
		
	if(checked==true && $("#isSave").val()=="true"){
		
		
		
		$("#btnSave").show();
	}else{
		$("#btnForward").show();
	}
	
	});
	
	
	$(".nextPage").click(function(e){
		var sevaarthid=$("#sevaarthid").val();
		
		var text=$(this).attr("href");
		$(this).attr("href",text+sevaarthid);
	
//		e.preventDefault();
	});
	
	
	
	
	
	
	
	
	//var selectedDocIds=$("#selectedDoc").val();
	
   	if($("#selectedDoc").val()!='undefined'){
   var selectedDocIdsArr=$("#selectedDoc").val().split(",");
    for(var i=0;i<selectedDocIdsArr.length;i++){
    	$('.cbDocCheckListCA'+selectedDocIdsArr[i]).prop('checked', true);
    }
    
    if(selectedDocIdsArr.length==8){
    	$("#selectAllCheckListsComp").prop('checked', true);
    }
    
   	}
   	
   	
	$("#selectAllCheckListsComp").change(function(){
   		if($(this).is(":checked")){
   			$("input[name='cbDocCheckListCA']").prop('checked', true);
   		}else{
   			$("input[name='cbDocCheckListCA']").prop('checked', false);
   		}
   	});
	
	
	//for seting current date on html date picker
	
	var now = new Date();
	var day = ("0" + now.getDate()).slice(-2);
	var month = ("0" + (now.getMonth() + 1)).slice(-2);
	var today = now.getFullYear()+"-"+(month)+"-"+(day) ;
	$('#txtSysDate').val(today);
	
	
  
  
  
  
  
  

  
  $("#cmbSchemeCode").change(function(){
	  var cmbSchemeCode=$("#cmbSchemeCode").val();
		$.ajax({
			type : "GET",
			url : "../level1/fetchVehicleAdvanceSehemeDetials/"
					+ cmbSchemeCode,
				
			async : true,
			contentType : 'application/json',
			error : function(data) {
				 console.log(data);
			// alert("error");
			},
			success : function(data) {
				 console.log(data);
				var len = data.length;
				if (len != 0 ) { 
					if(data[0].txtEmployeeName!="0"){
						$("#txtMajorHead").val("");
						$("#txtMajorHead").val(data[0].majorHead);
						$("#txtDemandNo").val(data[0].demandCode);
						$("#txtSchemeName").val(data[0].schemeName);
						$("#txtSubMajorHead").val(data[0].subMajorHead);
						$("#txtMinorHead").val(data[0].minorHead);
						$("#txtGroupHead").val(data[0].charged);
						$("#txtSubHead").val(data[0].subMinorHead);
					}else{
						$("#txtMajorHead").val("");
						$("#txtDemandNo").val("");
						$("#txtSchemeName").val("");
						$("#txtSubMajorHead").val("");
						$("#txtMinorHead").val("");
						$("#txtGroupHead").val("");
						$("#txtSubHead").val("");
					   }
			   }
			}
		});
     });
  

  
  $("#txtReqAmountMCA").keyup(function(){
	  var reqAmount=$(this).val();
	  $("#txtSancAmountMCA").val(reqAmount);
	  $("#txtSancInstallmentsMCA").val("");
	  $("#txtInstallmentAmountMCA").val("");
  });
  
  $("#txtSancInstallmentsMCA").keyup(function(){
	  if($(this).val()!=""){
	  var noOfInstallments=parseInt($(this).val());
	 var sanctionAmt=parseInt($("#txtSancAmountMCA").val());
	  var perMonthInstallmentAmount=((sanctionAmt/noOfInstallments));
	 // $("#txtInstallmentAmountCA").val(perMonthInstallmentAmount.toFixed(2));
	  $("#txtInstallmentAmountMCA").val(Math.round(perMonthInstallmentAmount));
	  }else{
		  $("#txtInstallmentAmountMCA").val("");
	  }
  });
  

});





function validateNewOrOldVehicle(){
	
	
	var reqType=document.getElementById("cmbVehicleSubType").value;
	var payCommission=document.getElementById("hidPayCommisssion").value;  //'7th Pay Commission'
	var payCom=document.getElementById("cmbPayCommissionMCA").value;  // 1,2,3,4 padmanabhan
	var lStrPC = payCommission.match(/5th/i);
	
	 
	

	
	if(payCom!=-1){
		if(lStrPC=="5th"&&payCom!=1){
			alert("Please select 5th Pay Commossion GR because Employee is in 5th Pay Commission");
			document.getElementById("cmbPayCommissionMCA").value=-1;
			return false;
		}else if(lStrPC=="6th"&&payCom!=2){
			alert("Please select 6th Pay Commossion GR because Employee is in 6th Pay Commission");
			document.getElementById("cmbPayCommissionMCA").value=-1;
			return false;
		}else if(lStrPC=="7th"&&payCom!=3){
			alert("Please select 7th Pay Commossion GR because Employee is in 7th Pay Commission");
			document.getElementById("cmbPayCommissionMCA").value=-1;
			return false;
		}
	}
	
	
	
	if(document.getElementById("rdoOld").checked){
		document.getElementById("txtExShowPriceMCA").value='';
		document.getElementById("txtExShowPriceMCA").readOnly=true;
	}
	
	else{
		document.getElementById("txtExShowPriceMCA").readOnly=false;
	}
	
	
	
	if(payCom!=-1 ){
		if(document.getElementById("rdoOld").checked&&payCom==2&&reqType!=6){
			alert("In 6th PC GR,Employee can take only Old Motor car advance");
			document.getElementById("rdoNew").checked=true;
			document.getElementById("txtExShowPriceMCA").readOnly=false;
			return false;
		}
	}
	
	
	if(payCom!=-1 ){
		if(document.getElementById("rdoOld").checked&&payCom==3&&reqType!=6){
			alert("In 7th PC GR,Employee can take only Old Motor car advance");
			document.getElementById("rdoNew").checked=true;
			document.getElementById("txtExShowPriceMCA").readOnly=false;
			return false;
		}
	}
	
	return true;
}


$("#btnSave").click(function(e){
$("form[name='vehicleAdvanceForm']").submit();
});


/*$("#btnSave,#btnForward").click(function(e){
	//$("#vehicleAdvanceForm").submit();
	  $("form[name='vehicleAdvanceForm']").validate({
		    rules: {
		    	rdoEmpType:{
		    		required: true,
		    	},
		    	cmbVehicleSubType:{
		    		required: true,
		    		min:1
		    	},
		    	rdoVehicleType: "required",
		    	cmbPayCommissionMCA:{
		    		required: true,
		    		min:1
		    	},
		    	txtAppDateMCA: "required",
		    	txtReqAmountMCA:{
		    		required:true,
		    		max:20000,
		    	},
		    	txtSanctionedDateMCA: "required",
		    	txtSancPrincipalInstallMCA: "required",
		    	txtSancInterInstallMCA: "required",
		    	cmbSchemeCode:{
		    		required: true,
		    		min:1
		    	},
		    },
		    messages: {
		    	rdoEmpType: "Please select Employee type",
		    	cmbVehicleSubType:"Please select Vehicle Sub Type",
		    	rdoVehicleType: "Please select Vehicle Type",
		    	cmbPayCommissionMCA:"Please select payCommision",
		    	txtAppDateMCA: "Please select Inward Date of Application",
		    	txtReqAmountMCA:"Please enter Request Amount ",
		    	txtSanctionedDateMCA: "Please select Sanctioned Date",
		    	txtSancPrincipalInstallMCA: "Please enter Sanctioned No. of Principal Installments ",
		    	txtSancInterInstallMCA: "Please enter Sanctioned No Of Interest Installments",
		    	cmbSchemeCode:"Please select Scheme Code",
		    },
		    submitHandler: function(form) {
		      form.submit();
		    }
		  });
});
*/


function validateReqAmountMCA(flag){
	
	
	var reqAmount;
	var tmpSanctionAmt = document.getElementById("hdnSancAmountMCA").value;
	var reqAmountMCAl = document.getElementById("txtReqAmountMCA").value;
	var sancAmountMCAl = document.getElementById("txtSancAmountMCA").value;

	if(flag==1){
		reqAmount = document.getElementById("txtReqAmountMCA");
	}else if (flag==2){
		
		reqAmount = document.getElementById("txtSancAmountMCA");
	}

	
	var reqType=document.getElementById("cmbVehicleSubType").value;
	
	var empGroup=document.getElementById("hidEmpGroup").value;
	//alert("empGroup"+empGroup);
	var reqPCType=document.getElementById("cmbPayCommissionMCA").value;
	//alert("reqPCType"+reqPCType);
	var payCommission=document.getElementById("hidPayCommisssion").value;
	//alert("payCommission"+payCommission);
	var payinPB=document.getElementById("hidPayinPB").value;
	//alert('payinPB'+payinPB);
	
	var empDesignation=document.getElementById("hidDesignation").value;	
	//alert("empDesignation"+empDesignation);
	var lStrDsgn = empDesignation.match(/driver/i);
	//alert("lStrDsgn"+lStrDsgn);
	var basic = document.getElementById("hidBasicPay").value;
	//alert("basic"+basic);
	
	
	
	
	var radioValue = $("input[name='rdoVehicleType']").val();
	
	
	/* Interest Rate logic before 22 Dec 2012 
	if(reqType!=-1&&reqPCType!=-1){
		if(reqType==6){
				if(reqPCType==1){
					document.getElementById("txtInterestRateMCA").value=12;
				}
				else{
					document.getElementById("txtInterestRateMCA").value=10;
				}
		}
		else{
			
			document.getElementById("txtInterestRateMCA").value=10;
		}
	}
	*/
	
	
	
	// Motor Car Interest Rate Logic 
	// Added by Mubeen on 22 Dec 2012 - Start
	if(reqType!=-1&&reqPCType!=-1){
		
		if(reqType==6){
		// Motor Car 11.5%
				if(reqPCType==1){
					// 5th Pay Commoission
					document.getElementById("txtInterestRateMCA").value=11.5;
				}
				else if(reqPCType==2){
					// 6th Pay Commoission
					document.getElementById("txtInterestRateMCA").value=11.5;
				}
				else if(reqPCType==3){
					// 7th Pay Commoission
					document.getElementById("txtInterestRateMCA").value=11.5;
				}
		} else if(reqType==5){
			
			
			// Bycyle 0%
			document.getElementById("txtInterestRateMCA").value=0;
			
		}
		else{
			// Two-wheeler
			document.getElementById("txtInterestRateMCA").value=9;
		}
		
	}
	// Added by Mubeen on 22 Dec 2012 - End
	
	if(reqType!=-1){
		if(lStrDsgn==null){
			if(empGroup=="D"&&reqType!=5&&basic<8560){
				//alert("empGroup d reqType not 5 basic less than 8560");
				alert('Group D Employees having basic pay less than 8560 are only Eligible for Bicycle Advance');
				document.getElementById("cmbVehicleSubType").value = -1;
				return false;			
			}
			
			if(empGroup=="D"&&reqType==6&&basic>8560){
				//alert("empGroup d reqType 6 basic > 8560");
				alert('Group D Employees are not eligible for Motor Car Advance');
				document.getElementById("cmbVehicleSubType").value = -1;
				return false;			
			}
			
			
			if(empGroup=="A"&&reqType==5){
				alert('Group A Employees are not Eligible to take Bycle Advance');
				document.getElementById("cmbVehicleSubType").value = -1;
				return false;
			}
			if(empGroup=="B"&&reqType==5){
				alert('Group B Employees are not Eligible to take Bycle Advance');
				document.getElementById("cmbVehicleSubType").value = -1;
				return false;
			}
			if(empGroup=="BnGz"&&reqType==5){
				alert('Group BnGz Employees are not Eligible to take Bycle Advance');
				document.getElementById("cmbVehicleSubType").value = -1;
				return false;
			}
			if(empGroup=="C"&&reqType==6){
				alert('Group C Employees are not Eligible to take Motor Car Advance');
				document.getElementById("cmbVehicleSubType").value = -1;
				return false;
			}
		}else{
			if(reqType==6){
				alert('Employees whose Designation is Driver, are not Eligible for Motor Car Advance');
				document.getElementById("cmbVehicleSubType").value = -1;
				return false;
			}
		}
	}
	if(lStrDsgn==null){
		if(payCommission=="5th Pay Commission"){
			if(reqType==6){
				if(basic<10500){
					alert('Employee is not Eligible for Motor Car Advance because Basic pay is less than 10,500');
					document.getElementById("cmbVehicleSubType").value = -1;
					return false;
				}
			}else if(reqType==5){
				if(basic>5000){
					alert('Employee is not Eligible for Bycle Advance because Basic pay is greater than 5,000');
					document.getElementById("cmbVehicleSubType").value = -1;
					return false;
				}
			}
		}
		
		
		//6TH pay commission
		else if(payCommission=="6th Pay Commission"){
			//alert("in 6th pay commission");
			//alert("basic"+basic);
			//alert("payinPB"+payinPB);
			//alert("reqType"+reqType);
			if(reqType==1 || reqType==2 || reqType==3 || reqType==4){
				if(basic<8650){	
					
					alert('Employee is not Eligible for Advance because Basic Pay is less than 8,650');
					document.getElementById("cmbVehicleSubType").value = -1;
					return false;
				}
			}
			
			else if(reqType==6){
				if(payinPB<19530){
					alert('Employee is not Eligible for Motor Car Advance because Pay in Pay Band is less than 19,530');
					document.getElementById("cmbVehicleSubType").value = -1;
					return false;
				}
			}
			
			else if(reqType==5){
				//gokarna For bycycle change .. its based on grade pay according to GR 
				//if(payinPB>2800){
				var grade_pay=document.getElementById("hidGradePay").value;
				//alert("grade_pay"+grade_pay);
				if(grade_pay>2800){
					
					//alert('Employee is not Eligible for Bycle Advance because Pay in Pay Band is greater than 2,800');
					alert('Employee is not Eligible for Bycle Advance because Grade Pay is greater than 2,800');
					document.getElementById("cmbVehicleSubType").value = -1;
					return false;
				}
			}
		}
		
		//7TH pay commission
		else if(payCommission=="7th Pay Commission"){
			//alert("in 6th pay commission");
			//alert("basic"+basic);
			//alert("payinPB"+payinPB);
			//alert("reqType"+reqType);
			if(reqType==1 || reqType==2 || reqType==3 || reqType==4){
				if(basic<8650){	
					
					alert('Employee is not Eligible for Advance because Basic Pay is less than 8,650');
					document.getElementById("cmbVehicleSubType").value = -1;
					return false;
				}
			}
			
			else if(reqType==6){
				if(payinPB<19530){
					alert('Employee is not Eligible for Motor Car Advance because Pay in Pay Band is less than 19,530');
					document.getElementById("cmbVehicleSubType").value = -1;
					return false;
				}
			}
			
			else if(reqType==5){
				//gokarna For bycycle change .. its based on grade pay according to GR 
				//if(payinPB>2800){
				var grade_pay=document.getElementById("hidGradePay").value;
				//alert("grade_pay"+grade_pay);
				if(grade_pay>2800){
					
					//alert('Employee is not Eligible for Bycle Advance because Pay in Pay Band is greater than 2,800');
					alert('Employee is not Eligible for Bycle Advance because Grade Pay is greater than 2,800');
					document.getElementById("cmbVehicleSubType").value = -1;
					return false;
				}
			}
		}
	}
	
	
	
	//5th pay commission
	if(reqPCType==1&&radioValue=="N"){
		if(reqAmount.value!="" || reqAmount.value!=null){
			if(reqType==1){
					if(reqAmount.value>70000){
						alert('For New With Gears (Motor Cycle) Advance Amount should be less than or Equals to 70,000');
						if (flag==2){
							//alert("tmpSanctionAmt"+tmpSanctionAmt);
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}	
					else if(sancAmountMCAl>reqAmountMCAl){
						alert('Sanction amount cannot be greater than Request amount');
						if (flag==2){
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}
			}
			else if(reqType==2){
					if(reqAmount.value>60000){
						
						//alert("tmpSanctionAmt"+tmpSanctionAmt);
						alert('For New Without Gears (Scooter) Advance Amount should be less than or Equals to 60,000');
						if (flag==2){
							//alert("flag"+flag);
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}
					else if(sancAmountMCAl>reqAmountMCAl){
						alert('Sanction amount cannot be greater than Request amount');
						if (flag==2){
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}
					
					//gokarna 
					
			}
			else if(reqType==3){
					if(reqAmount.value>25000){
						alert('For New Moped Advance Amount should be less than or Equals to 25,000');
						if (flag==2){
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}
					else if(sancAmountMCAl>reqAmountMCAl){
						alert('Sanction amount cannot be greater than Request amount');
						if (flag==2){
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}
			}
			else if(reqType==4){
					if(reqAmount.value>70000){
						alert('For New Motor bike for Handicapped Advance Amount should be less than or Equals to 70,000');
						if (flag==2){
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}
					else if(sancAmountMCAl>reqAmountMCAl){
						alert('Sanction amount cannot be greater than Request amount');
						if (flag==2){
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}
			}
			else if(reqType==5){
				if(reqAmount.value>3500){
					alert('For New Bycle Advance Amount should be less than or Equals to 3,500');
					if (flag==2){
						reqAmount.value = tmpSanctionAmt;
					}
					return false;
				}
				else if(sancAmountMCAl>reqAmountMCAl){
					alert('Sanction amount cannot be greater than Request amount');
					if (flag==2){
						reqAmount.value = tmpSanctionAmt;
					}
					return false;
				}
			}
			else if(reqType==6){
				if(reqAmount.value>175000){
					alert('For New Motor Car Advance Amount should be less than or Equals to 1,75,000');
					if (flag==2){
						reqAmount.value = tmpSanctionAmt;
					}
					return false;
				}
				else if(sancAmountMCAl>reqAmountMCAl){
					alert('Sanction amount cannot be greater than Request amount');
					if (flag==2){
						reqAmount.value = tmpSanctionAmt;
					}
					return false;
				}
			}
		}
	}
	
	
	//6th pay commission
	//1 New With Gears (Motor Cycle) <=70000
	//2 New Without Gears (Scooter)  <=60000
	//3  New Moped  25000 
	//4   Motor bike   70000
	//5 New Bycle  <= 3,50
	//6 New Motor Car  3,50,000
	else if(reqPCType==2&&radioValue=="N"){
		
	//	alert(reqAmount);
		if(reqAmount.value!="" || reqAmount.value!=null){
			if(reqType==1){
					if(reqAmount.value>70000){
						alert('For New With Gears (Motor Cycle) Advance Amount should be less than or Equals to 70,000');
						reqAmount.value ="";
						if (flag==2){
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}
					else if(sancAmountMCAl>reqAmountMCAl){
						alert('Sanction amount cannot be greater than Request amount');
						if (flag==2){
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}	
			}
			else if(reqType==2){
					if(reqAmount.value>60000){
						alert('For New Without Gears (Scooter) Advance Amount should be less than or Equals to 60,000');
						reqAmount.value ="";
						if (flag==2){
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}	
					else if(sancAmountMCAl>reqAmountMCAl){
						alert('Sanction amount cannot be greater than Request amount');
						if (flag==2){
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}
			}
			else if(reqType==3){
					if(reqAmount.value>25000){
						alert('For New Moped Advance Amount should be less than or Equals to 25,000');
						reqAmount.value ="";
						if (flag==2){
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}
					else if(sancAmountMCAl>reqAmountMCAl){
						alert('Sanction amount cannot be greater than Request amount');
						if (flag==2){
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}
			}
			else if(reqType==4){
					if(reqAmount.value>70000){
						alert('For New Motor bike for Handicapped Advance Amount should be less than or Equals to 70,000');
						reqAmount.value ="";
						if (flag==2){
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}
					else if(sancAmountMCAl>reqAmountMCAl){
						alert('Sanction amount cannot be greater than Request amount');
						if (flag==2){
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}
			}
			else if(reqType==5){
				if(reqAmount.value>3500){
					alert('For New Bycle Advance Amount should be less than or Equals to 3,500');
					reqAmount.value ="";
					if (flag==2){
						reqAmount.value = tmpSanctionAmt;
					}
					return false;
				}
				else if(sancAmountMCAl>reqAmountMCAl){
					alert('Sanction amount cannot be greater than Request amount');
					if (flag==2){
						reqAmount.value = tmpSanctionAmt;
					}
					return false;
				}
			}
			else if(reqType==6){
				if(reqAmount.value>350000){
					alert('For New Motor Car Advance Amount should be less than or Equals to 3,50,000');
					reqAmount.value ="";
					if (flag==2){
						reqAmount.value = tmpSanctionAmt;
					}
					return false;
				}
				else if(sancAmountMCAl>reqAmountMCAl){
					alert('Sanction amount cannot be greater than Request amount');
					if (flag==2){
						reqAmount.value = tmpSanctionAmt;
					}
					return false;
				}
			}
		}
	}
	
	
	
	
	//7th pay commission
	//1 New With Gears (Motor Cycle) <=70000
	//2 New Without Gears (Scooter)  <=60000
	//3  New Moped  25000 
	//4   Motor bike   70000
	//5 New Bycle  <= 3,50
	//6 New Motor Car  3,50,000
	else if(reqPCType==3&&radioValue=="N"){
		
	//	alert(reqAmount);
		if(reqAmount.value!="" || reqAmount.value!=null){
			if(reqType==1){
					if(reqAmount.value>70000){
						alert('For New With Gears (Motor Cycle) Advance Amount should be less than or Equals to 70,000');
						reqAmount.value ="";
						if (flag==2){
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}
					else if(sancAmountMCAl>reqAmountMCAl){
						alert('Sanction amount cannot be greater than Request amount');
						if (flag==2){
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}	
			}
			else if(reqType==2){
					if(reqAmount.value>60000){
						alert('For New Without Gears (Scooter) Advance Amount should be less than or Equals to 60,000');
						reqAmount.value ="";
						if (flag==2){
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}	
					else if(sancAmountMCAl>reqAmountMCAl){
						alert('Sanction amount cannot be greater than Request amount');
						if (flag==2){
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}
			}
			else if(reqType==3){
					if(reqAmount.value>25000){
						alert('For New Moped Advance Amount should be less than or Equals to 25,000');
						reqAmount.value ="";
						if (flag==2){
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}
					else if(sancAmountMCAl>reqAmountMCAl){
						alert('Sanction amount cannot be greater than Request amount');
						if (flag==2){
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}
			}
			else if(reqType==4){
					if(reqAmount.value>70000){
						alert('For New Motor bike for Handicapped Advance Amount should be less than or Equals to 70,000');
						reqAmount.value ="";
						if (flag==2){
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}
					else if(sancAmountMCAl>reqAmountMCAl){
						alert('Sanction amount cannot be greater than Request amount');
						if (flag==2){
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}
			}
			else if(reqType==5){
				if(reqAmount.value>3500){
					alert('For New Bycle Advance Amount should be less than or Equals to 3,500');
					reqAmount.value ="";
					if (flag==2){
						reqAmount.value = tmpSanctionAmt;
					}
					return false;
				}
				else if(sancAmountMCAl>reqAmountMCAl){
					alert('Sanction amount cannot be greater than Request amount');
					if (flag==2){
						reqAmount.value = tmpSanctionAmt;
					}
					return false;
				}
			}
			else if(reqType==6){
				if(reqAmount.value>350000){
					alert('For New Motor Car Advance Amount should be less than or Equals to 3,50,000');
					reqAmount.value ="";
					if (flag==2){
						reqAmount.value = tmpSanctionAmt;
					}
					return false;
				}
				else if(sancAmountMCAl>reqAmountMCAl){
					alert('Sanction amount cannot be greater than Request amount');
					if (flag==2){
						reqAmount.value = tmpSanctionAmt;
					}
					return false;
				}
			}
		}
	}
	
	
	
	
	else if(reqPCType==1&&radioValue=="O"){
		if(reqAmount.value!="" || reqAmount.value!=null){
			if(reqType==1){
					if(reqAmount.value>22500){
						alert('For Old With Gears (Motor Cycle) Advance Amount should be less than or Equals to 22,500');
						if (flag==2){
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}		
			}
			else if(reqType==2){
					if(reqAmount.value>14000){
						alert('For Old Without Gears (Scooter) Advance Amount should be less than or Equals to 14,000');
						if (flag==2){
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}			
			}
			else if(reqType==3){
					if(reqAmount.value>7000){
						alert('For Old Moped Advance Amount should be less than or Equals to 7,000');
						if (flag==2){
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}
			}
			else if(reqType==4){
					if(reqAmount.value>22500){
						alert('For Old Motor bike for Handicapped Advance Amount should be less than or Equals to 22,500');
						if (flag==2){
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}
			}
			else if(reqType==5){
				if(reqAmount.value>1250){
					alert('For Old Bycle Advance Amount should be less than or Equals to 1,250');
					if (flag==2){
						reqAmount.value = tmpSanctionAmt;
					}
					return false;
				}
			}
			else if(reqType==6){
					if(reqAmount.value>75000){
						alert('For Old Motor Car Advance Amount should be less than or Equals to 75,000');
						if (flag==2){
							reqAmount.value = tmpSanctionAmt;
						}
						return false;
					}
			}
		}
	}
	
	
	
	else if(reqPCType==2&&radioValue=="O"){
		if(reqAmount.value!="" || reqAmount.value!=null){
			if(reqType==6){
					if(reqAmount.value>175000){
						alert('For Old Motor Car Advance Amount should be less than or Equals to 1,75,000');
						if (flag==2){
							reqAmount.value = tmpSanctionAmt;
						}
						return false;						
					}
			}
		}
	}
	
	
	else if(reqPCType==3&&radioValue=="O"){
		if(reqAmount.value!="" || reqAmount.value!=null){
			if(reqType==6){
				if(reqAmount.value>175000){
					alert('For Old Motor Car Advance Amount should be less than or Equals to 1,75,000');
					if (flag==2){
						reqAmount.value = tmpSanctionAmt;
					}
					return false;						
				}
			}
		}
	}
	
	
	
	if(flag!=1){
		if(payCommission==1){
			if(reqAmount.value!="" || reqAmount.value!=null){
				var exShowPrice = document.getElementById("txtExShowPriceMCA").value
				if(reqAmount.value>Number(exShowPrice)){
					//alert('Sanction Amount is greater than Ex Showroom Price');
					reqAmount.value = tmpSanctionAmt;
					reqAmount.focus();
				}	
				var basicAndDP;
				if(radioValue=="N"){
						if(reqType==1||reqType==4){
							basicPlusDP = (Number(basic)+Number(basic)*0.5)*6;
						}else if(reqType==2){
							basicPlusDP = (Number(basic)+Number(basic)*0.5)*4;
						}else if(reqType==3){
							basicPlusDP = (Number(basic)+Number(basic)*0.5)*2;
						}else if(reqType==6){
							basicPlusDP = (Number(basic)+Number(basic)*0.5)*8;
						}
						if(reqAmount.value>Number(basicAndDP)){
							alert('Maximum Sanction Amount for this Employee is '+basicAndDP +'\n Sanction Amount is Greater than this Amount');
							reqAmount.value = tmpSanctionAmt;
							reqAmount.focus();
						}	
				}else{
					if(reqType==1||reqType==4){
						basicPlusDP = (Number(basic)+Number(basic)*0.5)*3;
					}else if(reqType==2){
						basicPlusDP = (Number(basic)+Number(basic)*0.5)*2;
					}else if(reqType==3){
						basicPlusDP = (Number(basic)+Number(basic)*0.5)*2;
					}else if(reqType==6){
						basicPlusDP = (Number(basic)+Number(basic)*0.5)*4;
					}
					if(reqAmount.value>Number(basicAndDP)){
						alert('Maximum Sanction Amount for this Employee is '+basicAndDP +'\n Sanction Amount is Greater than this Amount');
						reqAmount.value = tmpSanctionAmt;
						reqAmount.focus();
					}	
				}
			}
		}else if(payCommission==2){
			if(reqAmount.value!="" || reqAmount.value!=null){
				var exShowPrice = document.getElementById("txtExShowPriceMCA").value
				if(reqAmount.value>Number(exShowPrice)){
					//alert('Sanction Amount is greater than Ex Showroom Price');
					reqAmount.value = tmpSanctionAmt;
					reqAmount.focus();
				}	
			}
			var payInPB=document.getElementById("hidPayinPB").value;
			var compareAmount;
			if(radioValue=="N"){
				if(reqType==1||reqType==4){
					//compareAmount = Number(payInPB)*6;
					compareAmount = Number(payInPB)*9;
				}else if(reqType==2){
					compareAmount = Number(payInPB)*4;
				}else if(reqType==3){
					compareAmount = Number(payInPB)*2;
				}else if(reqType==6){
					compareAmount = Number(payInPB)*17;
				}
			}else{
				if(reqType==6){
					compareAmount = Number(payInPB)*8;
				}
			}
			if(reqAmount.value>Number(compareAmount)){
				alert('Maximum Sanction Amount for this Employee is '+payInPB +'\n Sanction Amount is Greater than this Amount');
				reqAmount.value = tmpSanctionAmt;
				reqAmount.focus();
			}	
		}else if(payCommission==3){
			if(reqAmount.value!="" || reqAmount.value!=null){
				var exShowPrice = document.getElementById("txtExShowPriceMCA").value
				if(reqAmount.value>Number(exShowPrice)){
					//alert('Sanction Amount is greater than Ex Showroom Price');
					reqAmount.value = tmpSanctionAmt;
					reqAmount.focus();
				}	
			}
			var payInPB=document.getElementById("hidPayinPB").value;
			var compareAmount;
			if(radioValue=="N"){
				if(reqType==1||reqType==4){
					//compareAmount = Number(payInPB)*6;
					compareAmount = Number(payInPB)*9;
				}else if(reqType==2){
					compareAmount = Number(payInPB)*4;
				}else if(reqType==3){
					compareAmount = Number(payInPB)*2;
				}else if(reqType==6){
					compareAmount = Number(payInPB)*17;
				}
			}else{
				if(reqType==6){
					compareAmount = Number(payInPB)*8;
				}
			}
			if(reqAmount.value>Number(compareAmount)){
				alert('Maximum Sanction Amount for this Employee is '+payInPB +'\n Sanction Amount is Greater than this Amount');
				reqAmount.value = tmpSanctionAmt;
				reqAmount.focus();
			}	
		}
	}
	return true;
}
function popupSancDtlsMCA(flag){
	
	
	
	var payCommission = document.getElementById("cmbPayCommissionMCA").value;
	//alert("payCommission"+payCommission);
	var reqType=document.getElementById("cmbVehicleSubType").value;
	//alert("reqType"+reqType);
	var tmpSanctionAmt = document.getElementById("hdnSancAmountMCA").value;
	//alert("tmpSanctionAmt"+tmpSanctionAmt);
	var maxSancAmt;	
	var reqAmount;
	
	if(flag==1){
		reqAmount = document.getElementById("txtReqAmountMCA");
		//alert(' reqAmount '+reqAmount);
	}else if (flag==2){
		reqAmount = document.getElementById("txtSancAmountMCA");
		//alert('3 reqAmount ' + reqAmount);
	}
	var exShowPrice = document.getElementById("txtExShowPriceMCA").value;
	//alert('value from textbox exShowPrice 1 '+exShowPrice);
	var basic = document.getElementById("hidBasicPay").value;
	//alert("basic"+basic);
	var basicPlusDP;
	var payInPB=document.getElementById("hidPayinPB").value;
	//alert("payInPB"+payInPB);
	var compareAmount;
		
	var radioValue = $("input[name='rdoVehicleType']").val();
	
	
	//for 5th pay com
	if(payCommission==1){
		if(reqAmount.value != "" && reqType!="-1"){
							
			
			if(radioValue=="N"){
					if(reqType==1||reqType==4){
						basicPlusDP = (Number(basic)+Number(basic)*0.5)*6;
						maxSancAmt = 70000;
					}
					
					else if(reqType==2){
						//alert ("2 ");
						basicPlusDP = (Number(basic)+Number(basic)*0.5)*4;
						//alert(basicPlusDP);
						maxSancAmt = 60000;
					}
					
					else if(reqType==3){
						basicPlusDP = (Number(basic)+Number(basic)*0.5)*2;
						maxSancAmt = 25000;
					}
					
					else if(reqType==6){
						basicPlusDP = (Number(basic)+Number(basic)*0.5)*8;
						maxSancAmt = 175000;
					}
					
					
					
					if(reqType==5){	
						maxSancAmt = 3500;
						
						
						if(reqAmount.value<maxSancAmt){
							if(Number(reqAmount.value)<Number(exShowPrice)){
								document.getElementById("txtSancAmountMCA").value = reqAmount.value;
								//alert(' 4 txtSancAmountMCA'+reqAmount);
							}else{
								document.getElementById("txtSancAmountMCA").value = exShowPrice;
								//alert(' 5 exShowPrice 1'+exShowPrice);
							}
						}else{
							if(Number(exShowPrice)>maxSancAmt){
								document.getElementById("txtSancAmountMCA").value = maxSancAmt;
								//alert(' 6 maxSancAmt'+maxSancAmt);
								
							}
							else{
								document.getElementById("txtSancAmountMCA").value = exShowPrice;
								//alert(' 7 exShowPrice 2'+exShowPrice);
							}			
						}
					}else{
						if(Number(reqAmount.value)<Number(basicPlusDP)){
							if(Number(reqAmount.value)<Number(exShowPrice)){
								document.getElementById("txtSancAmountMCA").value = reqAmount.value;
								//alert(' 8 exShowPrice 2'+exShowPrice);
								
							}else{
								document.getElementById("txtSancAmountMCA").value = exShowPrice;
								//alert(' 9 exShowPrice 2'+exShowPrice);
							}
						}else if(Number(exShowPrice)<Number(basicPlusDP)){
								document.getElementById("txtSancAmountMCA").value = exShowPrice;
								//alert('10 exShowPrice 2'+exShowPrice);
						}
						else if(Number(reqAmount.value) == Number(maxSancAmt)){
									document.getElementById("txtSancAmountMCA").value = maxSancAmt;
									//alert('11 maxSancAmt 2'+maxSancAmt);
						}else{
								document.getElementById("txtSancAmountMCA").value = basicPlusDP;
								//alert('12 basicPlusDP 2'+basicPlusDP);
						}
						
						if(document.getElementById("txtSancAmountMCA").value>maxSancAmt){
							document.getElementById("txtSancAmountMCA").value = maxSancAmt;
							//alert('13 maxSancAmt 2'+maxSancAmt);
						}
					}
				}
							
				
				
				
				else{
					if(reqType==1||reqType==4){
						basicPlusDP = (Number(basic)+Number(basic)*0.5)*3;
						maxSancAmt = 22500;
					}else if(reqType==2){
						basicPlusDP = (Number(basic)+Number(basic)*0.5)*2;
						maxSancAmt = 14000;
					}else if(reqType==3){
						basicPlusDP = (Number(basic)+Number(basic)*0.5)*2;
						maxSancAmt = 7000;
					}else if(reqType==6){
						basicPlusDP = (Number(basic)+Number(basic)*0.5)*4;
						maxSancAmt = 75000;
					}
					if(reqType==5){
						maxSancAmt = 1250;
						if(reqAmount.value<maxSancAmt){
							document.getElementById("txtSancAmountMCA").value = reqAmount.value;
							//alert(' 14 reqAmount 3'+reqAmount);
						
						}else{
							document.getElementById("txtSancAmountMCA").value = maxSancAmt;
							//alert('15 maxSancAmt 3'+maxSancAmt);
							
						}					
					}
					else{
						if(Number(reqAmount.value)<Number(basicPlusDP)){
							document.getElementById("txtSancAmountMCA").value = reqAmount.value;
							//alert(' 16 reqAmount 3'+reqAmount);
							
						}
						else{
							document.getElementById("txtSancAmountMCA").value = basicPlusDP;
							//alert('17 basicPlusDP 3'+basicPlusDP);
						}
						
						if(document.getElementById("txtSancAmountMCA").value>maxSancAmt){
							document.getElementById("txtSancAmountMCA").value = maxSancAmt;
							//alert(' 18 maxSancAmt 3'+maxSancAmt);
						}
					}
				}
			}
	}
	
	
	
	
	//for other than 5th pay com
	else{
		//alert("for 6thy pay");
		if(reqAmount.value != "" && reqType!="-1"){
			if(radioValue=="N"){
				if(reqType==1||reqType==4){
					//compareAmount = Number(payInPB)*6;
					compareAmount = Number(payInPB)*9;
					maxSancAmt = 70000;
				//alert('compareAmount 1 and max sanc amount is 70,000'+compareAmount);
				}
				
				else if(reqType==2){
					//alert("in reqType 2");
					compareAmount = Number(payInPB)*4;
					//alert(compareAmount);
					maxSancAmt = 60000;
					//a/lert(maxSancAmt);
					//alert('compareAmount 2'+compareAmount);
				}
				
				else if(reqType==3){
					compareAmount = Number(payInPB)*2;
					maxSancAmt = 25000;
				//alert('compareAmount 3'+compareAmount);
				}
				
			else if(reqType==6){
					compareAmount = Number(payInPB)*17;
					maxSancAmt = 350000;
				//	alert('compareAmount 4'+compareAmount);
				}
				
				if(reqType==5){
					maxSancAmt = 3500;
					if(reqAmount.value<maxSancAmt){
						if(Number(reqAmount.value)<Number(exShowPrice)){
							document.getElementById("txtSancAmountMCA").value = reqAmount.value;
							//alert(' 22 bexShowPrice'+ exShowPrice);
						}else{
							document.getElementById("txtSancAmountMCA").value = exShowPrice;
							//alert(' 23 exShowPrice'+ exShowPrice);
						}
					}
					
					else{
						if(Number(exShowPrice)>maxSancAmt){
							document.getElementById("txtSancAmountMCA").value = maxSancAmt;
							//alert(' 24 maxSancAmt'+ maxSancAmt);
						}
						
						else{
							document.getElementById("txtSancAmountMCA").value = exShowPrice;
							//alert(' 25 exShowPrice'+ exShowPrice);
						}			
					}
				}
				
				
				else{
					//alert('value from textbox exShowPrice 1345 '+exShowPrice);
					//alert(Number(reqAmount.value));
					//alert("compareAmount7"+compareAmount);
					if(Number(reqAmount.value)<Number(compareAmount))
					{
						//alert('in if req amt is less than compareAmount');
						  
						if(Number(reqAmount.value)<Number(exShowPrice)){
							//alert('in exShowPrice is greater than reqAmount');
							document.getElementById("txtSancAmountMCA").value = reqAmount.value;
							//alert(' 26 reqAmount'+reqAmount);
						}
						
						if((Number(reqAmount.value)>Number(exShowPrice))){
							//alert('in reqAmount is greater than exShowPrice');
							document.getElementById("txtSancAmountMCA").value = exShowPrice;
							//alert(' 27 exShowPrice'+exShowPrice);
						}
					//gokarna 
					 if((Number(reqAmount.value)==Number(exShowPrice))){
						//alert('in reqAmount is equal to exShowPrice');
						document.getElementById("txtSancAmountMCA").value = exShowPrice;
						//alert(' 28 exShowPrice'+exShowPrice);
					}
					
					
					
					}
					//gokarna on 23/09/2015
						 else if((Number(reqAmount.value)>Number(compareAmount)))
						 {
								//alert('in reqAmount greater compareAmount');
								document.getElementById("txtSancAmountMCA").value = Number(reqAmount.value);
								//alert(' 28 exShowPrice'+exShowPrice);
							    }
					else if(Number(exShowPrice)<Number(compareAmount)){
						 // alert('in compareAmount is greater than exShowPrice');
							document.getElementById("txtSancAmountMCA").value = exShowPrice;
							//alert(' txtSancAmountMCA is '+exShowPrice);
							
					}
					
					else if(Number(reqAmount.value) == Number(maxSancAmt)){
						document.getElementById("txtSancAmountMCA").value = maxSancAmt;
						//alert(' 29 exShowPrice'+maxSancAmt);
					}
					
					else{
						document.getElementById("txtSancAmountMCA").value = Number(reqAmount.value);
						if(Number(reqAmount.value) > Number(maxSancAmt)){
							document.getElementById("txtSancAmountMCA").value = maxSancAmt;
							//alert(' 30 maxSancAmt'+maxSancAmt);
						}
						//gokarna
						if(Number(exShowPrice) == Number(maxSancAmt)){
							//alert('exShowPrice = maxSancAmt'+reqAmount );
							document.getElementById("txtSancAmountMCA").value = reqAmount.value;
							//alert(' 31 maxSancAmt'+reqAmount);
						}
						
						//document.getElementById("txtSancAmountMCA").value = compareAmount;
					}  
					
					if(document.getElementById("txtSancAmountMCA").value>maxSancAmt){
						document.getElementById("txtSancAmountMCA").value = maxSancAmt;
						//alert(' 31 maxSancAmt'+maxSancAmt);
					}
				}
			}
			
			else{
				compareAmount = Number(payInPB)*8;
				//alert("compareAmount"+compareAmount);
				maxSancAmt = 175000;
				if(Number(reqAmount.value)<Number(compareAmount)){
					document.getElementById("txtSancAmountMCA").value = reqAmount.value;
					//alert(' 32 maxSancAmt'+reqAmount);
				}
				else{
					document.getElementById("txtSancAmountMCA").value = compareAmount;
				//	alert(' 33 maxSancAmt'+compareAmount);
					
				}				
				if(document.getElementById("txtSancAmountMCA").value>maxSancAmt){
					document.getElementById("txtSancAmountMCA").value = maxSancAmt;
					//alert(' 34 maxSancAmt'+maxSancAmt);
					
				}
			}
		}
	}
}


function validateNoOfInstallmentsMCA(){
	
	
	
	document.getElementById("txtPrinInstallmentAmountMCA").value = "";
	document.getElementById("txtInterInstallmentAmountMCA").value = "";
	document.getElementById("txtOddPrincipalInstallmentAmtMCA").value = "";
	document.getElementById("txtOddInterestInstallmentAmtMCA").value = "";
	document.getElementById("txtInterestAmountMCA").value = "";
	var reqType=document.getElementById("cmbVehicleSubType").value;
	var sancPrinInst = document.getElementById("txtSancPrincipalInstallMCA").value;
	
	var sancInterInst = document.getElementById("txtSancInterInstallMCA").value;
	var radioValue = $("input[name='rdoVehicleType']").val();

	
	
	var DOS = document.getElementById("hidDateOfSuperAnnuation").value;
	var lArrDate = DOS.split("/");	
	var dateofSuperAnnu = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);
	var currDate = new Date();
	var futureDate = new Date(currDate);
	var d1Y = currDate.getFullYear();
    var d2Y = dateofSuperAnnu.getFullYear();
    var d1M = currDate.getMonth();
    var d2M = dateofSuperAnnu.getMonth();
    var maxInstallment=(d2M+12*d2Y)-(d1M+12*d1Y);
	futureDate.setMonth(futureDate.getMonth() + (Number(sancPrinInst)+Number(sancInterInst)));
	if(futureDate>dateofSuperAnnu){
		alert('Considering Retirement Date Maximum Number of installments for Employee are '+maxInstallment);
		document.getElementById("txtSancPrincipalInstallMCA").value="";
		document.getElementById("txtSancInterInstallMCA").value="";		
		document.getElementById("txtSancPrincipalInstallMCA").focus();
		return false;
	}
	if(sancPrinInst!=""){
		if(Number(sancPrinInst==0)){
			alert('Sanctioned No. of Principal Installments Should be greater than 0');
			document.getElementById("txtSancPrincipalInstallMCA").value="";
			document.getElementById("txtSancPrincipalInstallMCA").focus();
			return false;
		}
	}
	if(sancInterInst!=""){
		if(Number(sancInterInst==0)){ 
			alert('Sanctioned No. of Interest Installments Should be greater than 0');
			document.getElementById("txtSancInterInstallMCA").value="";
			document.getElementById("txtSancInterInstallMCA").focus();
			return false;
		}
	}
	if(sancPrinInst!="" || sancInterInst!=""){		
		if(reqType==1&&radioValue=="N"){
				if(Number(sancPrinInst)+Number(sancInterInst)>60){
					alert('Sanctioned No. of Total Installments Should be less than 60');
					document.getElementById("txtSancPrincipalInstallMCA").value="";
					document.getElementById("txtSancInterInstallMCA").value="";
					document.getElementById("txtSancPrincipalInstallMCA").focus();
					return false;
				}
		}else if(reqType==1&&radioValue=="O")
		{
			if(Number(sancPrinInst)+Number(sancInterInst)>30){
				alert('Sanctioned No. of Total Installments Should be less than 30');
				document.getElementById("txtSancPrincipalInstallMCA").value="";
				document.getElementById("txtSancInterInstallMCA").value="";
				document.getElementById("txtSancPrincipalInstallMCA").focus();
				return false;
			}
		}else if(reqType==2&&radioValue=="N"){
			if(Number(sancPrinInst)+Number(sancInterInst)>48){
				alert('Sanctioned No. of Total Installments Should be less than 48');
				document.getElementById("txtSancPrincipalInstallMCA").value="";
				document.getElementById("txtSancInterInstallMCA").value="";
				document.getElementById("txtSancPrincipalInstallMCA").focus();
				return false;
			}
		}else if(reqType==2&&radioValue=="O"){
			if(Number(sancPrinInst)+Number(sancInterInst)>24){
				alert('Sanctioned No. of Total Installments Should be less than 24');
				document.getElementById("txtSancPrincipalInstallMCA").value="";
				document.getElementById("txtSancInterInstallMCA").value="";
				document.getElementById("txtSancPrincipalInstallMCA").focus();
				return false;
			}
		}else if(reqType==3&&radioValue=="N"){
			if(Number(sancPrinInst)+Number(sancInterInst)>30){
				alert('Sanctioned No. of Total Installments Should be less than 30');
				document.getElementById("txtSancPrincipalInstallMCA").value="";
				document.getElementById("txtSancInterInstallMCA").value="";
				document.getElementById("txtSancPrincipalInstallMCA").focus();
				return false;
			}
		}else if(reqType==3&&radioValue=="O"){
			if(Number(sancPrinInst)+Number(sancInterInst)>15){
				alert('Sanctioned No. of Total Installments Should be less than 15');
				document.getElementById("txtSancPrincipalInstallMCA").value="";
				document.getElementById("txtSancInterInstallMCA").value="";
				document.getElementById("txtSancPrincipalInstallMCA").focus();
				return false;
			}
		}else if(reqType==4&&radioValue=="N"){
			if(Number(sancPrinInst)+Number(sancInterInst)>60){
				alert('Sanctioned No. of Total Installments Should be less than 60');
				document.getElementById("txtSancPrincipalInstallMCA").value="";
				document.getElementById("txtSancInterInstallMCA").value="";
				document.getElementById("txtSancPrincipalInstallMCA").focus();
				return false;
			}
		}else if(reqType==4&&radioValue=="O"){
			if(Number(sancPrinInst)+Number(sancInterInst)>30){
				alert('Sanctioned No. of Total Installments Should be less than 30');
				document.getElementById("txtSancPrincipalInstallMCA").value="";
				document.getElementById("txtSancInterInstallMCA").value="";	
				document.getElementById("txtSancPrincipalInstallMCA").focus();
				return false;
			}
		}else if(reqType==5&&radioValue=="N"){
			if(Number(sancPrinInst)+Number(sancInterInst)>10){
				alert('Sanctioned No. of Total Installments Should be less than 10');
				document.getElementById("txtSancPrincipalInstallMCA").value="";
				document.getElementById("txtSancInterInstallMCA").value="";
				document.getElementById("txtSancPrincipalInstallMCA").focus();
				return false;
			}
		}else if(reqType==5&&radioValue=="O"){
			if(Number(sancPrinInst)+Number(sancInterInst)>5){
				alert('Sanctioned No. of Total Installments Should be less than 5');
				document.getElementById("txtSancPrincipalInstallMCA").value="";
				document.getElementById("txtSancInterInstallMCA").value="";
				document.getElementById("txtSancPrincipalInstallMCA").focus();
				return false;
			}
		}
		
		
		
		else if(reqType==6&&radioValue=="N"){
			if(Number(sancPrinInst)>101){
				alert('Sanctioned No. of Principal Installments Should be less than or equal to 100');
				document.getElementById("txtSancPrincipalInstallMCA").value="";
				document.getElementById("txtSancPrincipalInstallMCA").focus();
				return false;
			}
			if(Number(sancInterInst)>60){
				alert('Sanctioned No. of Interest Installments Should be less than 60');
				document.getElementById("txtSancInterInstallMCA").value="";
				document.getElementById("txtSancInterInstallMCA").focus();
				return false;
			}
		}
		
		else if(reqType==6&&radioValue=="O"){
		
			if(Number(sancPrinInst)>50){
				alert('Sanctioned No. of Principal Installments Should be less than 50');
				document.getElementById("txtSancPrincipalInstallMCA").value="";
				return false;
			}
			if(Number(sancInterInst)>30){
				alert('Sanctioned No. of Interest Installments Should be less than 30');
				document.getElementById("txtSancInterInstallMCA").value="";
				document.getElementById("txtSancInterInstallMCA").focus();
				return false;
			}
		}
	}
	
	var sancAmount = document.getElementById("txtSancAmountMCA").value;
	//alert(' 1 sancAmount'+ sancAmount);
	if(sancAmount != "" && sancPrinInst != "" && sancInterInst != ""){
		
		var inst= Math.floor(sancAmount/sancPrinInst);
		var oddValue = sancAmount - (inst*(sancPrinInst-1));
		if (inst==oddValue){
			document.getElementById("txtPrinInstallmentAmountMCA").value = inst;
			document.getElementById("cmbOddPrincipalInstallNoMCA").value = -1;
			document.getElementById("cmbOddPrincipalInstallNoMCA").disabled = true;
		} else{
			document.getElementById("txtPrinInstallmentAmountMCA").value = inst;
			document.getElementById("txtOddPrincipalInstallmentAmtMCA").value = oddValue;
			document.getElementById("cmbOddPrincipalInstallNoMCA").disabled = false;
			document.getElementById("cmbOddPrincipalInstallNoMCA").value = 800057;
		}
		var capInstAmount = document.getElementById("txtPrinInstallmentAmountMCA").value;
		var interestRate = document.getElementById("txtInterestRateMCA").value;
		if(interestRate!=""){
			var interestAmount = Math.round(Number(sancPrinInst)*(Number(sancPrinInst)+1)/2*(Number(capInstAmount)/12)*(Number(interestRate)/100));
			inst = Math.floor(Number(interestAmount)/Number(sancInterInst));
			oddValue = interestAmount - (inst*(sancInterInst-1));
		}
		if (inst==oddValue){
			document.getElementById("txtInterInstallmentAmountMCA").value = inst;
			document.getElementById("cmbOddInterestInstallNoMCA").value = -1;
			document.getElementById("cmbOddInterestInstallNoMCA").disabled = true;
		} else{
			document.getElementById("txtInterInstallmentAmountMCA").value = inst;
			document.getElementById("txtOddInterestInstallmentAmtMCA").value = oddValue;
			document.getElementById("cmbOddInterestInstallNoMCA").disabled = false;
			document.getElementById("cmbOddInterestInstallNoMCA").value = 800057;
		}
		document.getElementById("txtInterestAmountMCA").value = interestAmount;		
	}
}
function changeSancDtlsMCA(){
	
	var reqType=document.getElementById("cmbVehicleSubType").value;	
	var obj;
	if(reqType==4){
		document.getElementById('ChecklistMCAHandicap').style.display = '';
		document.getElementById('ChecklistMCA').style.display = 'none';
		
		document.getElementById("selectAllCheckListsM").checked = false;		
		
		obj = document.LNARequestProcessForm.cbDocCheckListMCA;
		for(var i =0 ; i < obj.length;i++){
			obj[i].checked = false;
		}		
		
		tableName='tblChecklistMCAHandicap';
	}else {
		document.getElementById('ChecklistMCAHandicap').style.display = 'none';
		document.getElementById('ChecklistMCA').style.display = '';
		document.getElementById("selectAllCheckListsM").checked = false;		
		
		obj = $("input[name='cbDocCheckListMCAHC']");
		for( i =0 ; i < obj.length;i++){
			obj[i].checked = false;
		}
				
		tableName='tblChecklistMCA';
	}
}
var tableName;
/*function addNewCheckListMCA(){
	
	if(document.getElementById('ChecklistMCAHandicap').style.display == ''){
		tableName="tblChecklistMCAHandicap";
	}else if(document.getElementById('ChecklistMCA').style.display == ''){
		tableName="tblChecklistMCA";
	}else{
		tableName="tblChecklistMCANew";
	}
	var newRow =  document.getElementById(tableName).insertRow(document.getElementById(tableName).rows.length);	
	var rowCnt=document.getElementById("hidRowCountMCA").value;
	
	var Cell1 = newRow.insertCell(0);
	var Cell2 = newRow.insertCell(1);
	
	Cell1.innerHTML = 'Enter Document Name     <input type="text" name="txtChecklistNameMCA" id="txtChecklistNameMCA"/>';
	Cell2.innerHTML = '<input type="button" class="bigbutton" style="width: 100px" value="OK" id="btnOk" name="btnOk" onclick=\'displayChecklistMCA(this,"tblChecklistMCA",'+rowCnt+')\'"/> <img name="Image" id="Image" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,tableName)\'/>';
	document.getElementById("btnAddNewCheckListMCA").style.display='none';
	//document.getElementById("btnAddNewCheckListMCA").disabled=true;
}*/
/*function displayChecklistMCA(obj, tblId,rowCnt){
	var label=document.getElementById("txtChecklistNameMCA").value;
	if(label!=""){
		var colCnt = document.getElementById("hidCheckListsMCA").value;
		var newRow =  document.getElementById(tableName).insertRow(document.getElementById(tableName).rows.length);
		var rowID = showRowCell(obj);
		document.getElementById(tableName).deleteRow(rowID);	
		var Cell1 = newRow.insertCell(0);
		if(document.getElementById('ChecklistMCAHandicap').style.display == ''){
			Cell1.innerHTML = '<input type="checkbox" name="cbDocCheckListMCAHC" id="" value="'+label+'">'+label+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img name="Image" id="Image" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,tableName)\'/>';
		}else if(document.getElementById('ChecklistMCA').style.display == ''){
			Cell1.innerHTML = '<input type="checkbox" name="cbDocCheckListMCA" id="" value="'+label+'">'+label+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img name="Image" id="Image" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,tableName)\'/>';
		}else {
			Cell1.innerHTML = '<input type="checkbox" name="cbDocCheckListMCANew" id="" value="'+label+'">'+label+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img name="Image" id="Image" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,tableName)\'/>';
		}
		document.getElementById("btnAddNewCheckListMCA").style.display='';
		//document.getElementById("btnAddNewCheckListCA").disabled=false;
	}else{
		alert('Please Enter Document Name');
	}
}
*/

function checkSubType()
{
	var subtype=document.getElementById("SubType").value;
	
	var VehicleSubType=document.getElementById("cmbVehicleSubType").value;
	
	if((subtype==1 || subtype==2 || subtype==3) && (VehicleSubType==1 || VehicleSubType==2 || VehicleSubType==3))
		alert('This employee has already taken two-wheeler loan advance.\n He/She can take only Motor Car Advance');
	else if(subtype==6 && VehicleSubType==6)
		alert('This employee has already taken four-wheeler loan advance.\n He/She can only take two-wheeler Advance');
	
}
/*//By vivek sharma 09-02-2017
function disableIntertextBox(){
	alert("disableIntertextBox");
	var VehicleSubType=document.getElementById("cmbVehicleSubType").value;
	alert("VehicleSubType"+VehicleSubType);
	if(VehicleSubType == 5){
		alert("if VehicleSubType"+VehicleSubType);
		//document.getElementById("txtSancInterInstallMCA").disabled = false;
	}
	else{
		alert("else VehicleSubType"+VehicleSubType);
		//document.getElementById("txtSancInterInstallMCA").disabled = false;
	}
		
}*/

/*$("#selectAllCheckListsM").change(function(){
	if($(this).is(":checked")){
		$("input[name='cbDocCheckListMCA']").prop('checked', true);
		$("input[name='cbDocCheckListMCAHC']").prop('checked', true);
		
	}else{
		$("input[name='cbDocCheckListMCA']").prop('checked', false);
		$("input[name='cbDocCheckListMCAHC']").prop('checked', false);
	}
});
*/


function validateYearOfManufacturing(){
	
	
var yearofmanuf = document.getElementById("txtYrofManufac").value;

var phyappdate = document.getElementById("txtAppDateMCA").value;



var lArrDate = phyappdate.split("/");	
var dateofapp = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);



var d2Y = dateofapp.getFullYear();



var radioValue = $("input[name='rdoVehicleType']").val();


if(radioValue=='O'){

	document.getElementById("txtYrofManufac").style.display='';
	document.getElementById("yearofmanuf").style.display='';
	document.getElementById("yearofmanuf2").style.display='';
	
	
	var diff = d2Y - yearofmanuf;
	
	if(yearofmanuf > d2Y){
		
		alert('Year of Manufacturing cannot be greater than current year.');
	}
	
	if(diff > 5){
		
		alert('Maximum difference between Year of Manufacturing and Physical application Date can be 5 years');
	}
}
if(radioValue=='N'){
	
	document.getElementById("txtYrofManufac").style.display='none';
	document.getElementById("yearofmanuf").style.display='none';
	document.getElementById("yearofmanuf2").style.display='none';
}



}


/*function displayJoiningdate(htmlElement){
	if(htmlElement.value=='E'){
		$("#txtJoiningdate").prop("disabled",false);
	}else{
		$("#txtJoiningdate").prop("disabled",true);	
	}
	
}*/



