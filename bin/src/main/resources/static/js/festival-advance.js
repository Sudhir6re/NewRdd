	$("#addDoc").click(function(){
		var rowCount = $('#documentTbl tr').length;
		var col1='<td>'+(Number(rowCount))+'</td>';
		var col2='<td align="left" style="vertical-align: middle;"><div class="custom-file"><input type="file" id="files" class="custom-file-input form-control input-sm" name="files" style="padding: 4px 11px !important;"></div></td>';
		var col3='<td align="center" class="delete" style="vertical-align: middle;"><a><span class="glyphicon glyphicon-trash delete"></span></a></td>';	
		//$("#documentTbl").append('<tr>'+col1+col2+col3+'</tr>');
		$('#documentTbl tr:last').after('<tr>'+col1+col2+col3+'</tr>');
	});
	
	
	
	
	$(document).on('click', '.delete', function(){ 
		 $(this).closest("tr").remove();
	});


$(document).ready(function(){
	
	
	//select grade_pay,pay_scale_level_id,pay_commission_code from employee_mst  where sevaarth_id='MJPSSKM7601' 
	
	// check GRADE PAY LESS THEN 4800 or Pay scale level id <17
	
	
  
	var sevaarthId=$("#sevaarthId").val();
	checkGradePay(sevaarthId);
	checkAlreadyLoanAmountPending(sevaarthId);
	
	
	
	function checkGradePay(sevaarthId){
			$.ajax({
				type : "GET",
				url : "../level1/checkGradePay/"+ sevaarthId,
				async : true,
				contentType : 'application/json',
				error : function(data) {
					 console.log(data);
				},
				success : function(data) {
					 console.log(data);
					var len = data.length;
					if (len != 0) { 
	                for(var i=0;i<len;i++){
	                	var gradePay=data[i].gradePay;
	                    var payScaleLevel=data[i].payscalelevelId;
	                    var payCommisionCode=data[i].payCommissionCode;  
	                    if(payCommisionCode=="8"){  // 8 seven pay
	                    	if(parseInt(payScaleLevel)>17){
	                    		swal("Employee Not eligible for Festival advance");
	                    		window.location.href = "../level1/PersonLoanAndAdvance";
	                    	}
	                    }else if(payCommisionCode=="2"){  //  six pay
	                    	if(parseInt(gradePay)>4800){
	                    		swal("Employee Not eligible for Festival advance");
	                    		window.location.href = "../level1/PersonLoanAndAdvance";
	                    	}
	                    }
	                }				
				   }
				}
			});
	}
	//end condition 
	
	
	$("#txtReqAmountCA").blur(function (){
		var amount=$(this).val();
		if(parseInt(amount)>12500){
			swal("Maximum festival adavance allowed is only 12,500 only");
		}
	});
	
	
	
	$("#txtSancInstallmentsCA").blur(function() {
		if (parseInt($("#txtSancInstallmentsCA").val()) > 10) {
			swal("Installment should be less then or equal to ten");
			$("#txtSancInstallmentsCA").val("");
		}else{
			var reqAmt=$("#txtReqAmountCA").val();
			$("#txtInstallmentAmountCA").val(parseInt(reqAmt)/parseInt($("#txtSancInstallmentsCA").val()));  //toFixed(2));
			
			
			if(parseInt(reqAmt)%parseInt($("#txtSancInstallmentsCA").val())!=0){
				
				var totalInst=(parseInt($("#txtSancInstallmentsCA").val()));
				var totalInst1=(parseInt(totalInst)-1);
				
				var  evenAmt=(parseInt($("#txtInstallmentAmountCA").val())*totalInst1);
				
				$("#txtOddInstallmentAmtCA").val(parseInt($("#txtSancAmountCA").val())-parseInt(evenAmt));
				
			}
		}
	});
	
	 $("#festivalAdvanceForm").validate({
		    rules: {
		    	empstaemptype:{
		    		required: true,
		    		min:1
		    	},
		    //	txtJoiningdate: "required",
		    	cmbComputerSubType:{
		    		required: true,
		    		min:1
		    	},
		    	txtSysDate: "required",
		    	txtAppDateCA: "required",
		    	txtReqAmountCA:{
		    		required:true,
		    		max:12500,
		    	},
		    	txtActualCostCA: "required",
		    	txtSancInstallmentsCA: "required",
		    	txtSanctionedDateCA: "required",
		    	txtInstallmentAmountCA:{
		    		required:true,
		    	//	max:12500,
		    	}
		    },
		    messages: {
		    	empstaemptype: "Please select Employee type",
		    //	txtJoiningdate: "Please select Joining Date",
		    	cmbComputerSubType: "Please Select Request Sub Type",
		    	txtSysDate: "Please Select System Date",
		    	txtAppDateCA: "Please Select Inward Date of Application",
		    	txtReqAmountCA: {
		    		required:"Please enter Request Amount",
		    		min:"Computer Advance Amount should be less than or Equals to 20,000",
		    	},
		    	txtActualCostCA: "Please enter Estimate Cost",
		    	txtSancInstallmentsCA: "Please enter Sanctioned No. of Installment",
		    	txtSanctionedDateCA: "Please select Sanctioned Date",
		    	txtInstallmentAmountCA:"Please enter Installment amount",
		    },
		    submitHandler: function(form) {
		      form.submit();
		    }
		  });
	
	 
	 function checkAlreadyLoanAmountPending(sevaarthId){
			$.ajax({
				type : "GET",
				url : "../level1/checkAlreadyLoanAmountPending/"+ sevaarthId,
				async : true,
				contentType : 'application/json',
				error : function(data) {
					 console.log(data);
				},
				success : function(data) {
					 console.log(data);
					if (parseInt(data)!=0) {
						swal("Previous Festival Advance Loan Emi is Pending");
						$("#btnSave").prop("disabled",true);
						$("#btnForward").prop("disabled",true);
						
					//	history.back();
						//http://localhost:8080/JalSevaarth/master/PersonLoanAndAdvance
				   }
				}
			});
	}
	//end condition 
	
	 $("#btnForward").click(function(){   $("#festivalAdvanceForm").submit();});
	 
	 $("#selectAllCheckListsComp").change(function(){
	 		if($(this).is(":checked")){
	 			$("input[name='cbDocCheckListCA']").prop('checked', true);
	 		}else{
	 			$("input[name='cbDocCheckListCA']").prop('checked', false);
	 		}
	 	});
	 
	 
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
		
//			e.preventDefault();
		});
		
		
	  	if($("#selectedDoc").val()!='undefined'){
	  	   var selectedDocIdsArr=$("#selectedDoc").val().split(",");
	  	    for(var i=0;i<selectedDocIdsArr.length;i++){
	  	    	$('.cbDocCheckListCA'+selectedDocIdsArr[i]).prop('checked', true);
	  	    }
	  	    
	  	    if(selectedDocIdsArr.length==8){
	  	    	$("#selectAllCheckListsComp").prop('checked', true);
	  	    }
	  	    
	  	   	}

	  		
	  		
	  		//for seting current date on html date picker
	  		
	  		
	  		
	  		$('#txtSysDate').val(dateToYMD(new Date()));
	  		
	  		
	  	  
	  	  
	  	  
	  	  
	  	  
	  	  
	  
	
	
	
	
});