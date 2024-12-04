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
			url : "../level1/fetchSehemeDetials/"
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
  

  
  $("#txtReqAmountCA").keyup(function(){
	  var reqAmount=$(this).val();
	  $("#txtSancAmountCA").val(reqAmount);
	  $("#txtSancInstallmentsCA").val("");
	  $("#txtInstallmentAmountCA").val("");
  });
  
  $("#txtSancInstallmentsCA").keyup(function(){
	  if($(this).val()!=""){
	  var noOfInstallments=parseInt($(this).val());
	 var sanctionAmt=parseInt($("#txtSancAmountCA").val());
	  var perMonthInstallmentAmount=((sanctionAmt/noOfInstallments));
	 // $("#txtInstallmentAmountCA").val(perMonthInstallmentAmount.toFixed(2));
	  $("#txtInstallmentAmountCA").val(Math.round(perMonthInstallmentAmount));
	  }else{
		  $("#txtInstallmentAmountCA").val("");
	  }
  });
  
  
 	
	$("#selectAllCheckListsComp").change(function(){
 		if($(this).is(":checked")){
 			$("input[name='cbDocCheckListCA']").prop('checked', true);
 		}else{
 			$("input[name='cbDocCheckListCA']").prop('checked', false);
 		}
 	});
  
  
  $("form[name='computerAdvanceForm']").validate({
	    rules: {
	    	empstaemptype:{
	    		required: true,
	    		min:1
	    	},
	    	txtJoiningdate: "required",
	    	cmbComputerSubType:{
	    		required: true,
	    		min:1
	    	},
	    	txtSysDate: "required",
	    	txtAppDateCA: "required",
	    	
	    	txtReqAmountCA:{
	    		required:true,
	    		max:20000,
	    	},
	    	txtActualCostCA: "required",
	    	txtSancInstallmentsCA: "required",
	    	txtSanctionedDateCA: "required",
	    	cmbSchemeCode:{
	    		required: true,
	    		min:1
	    	},
	    },
	    messages: {
	    	empstaemptype: "Please select Employee type",
	    	txtJoiningdate: "Please select Joining Date",
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
	    	cmbSchemeCode:"Please select Scheme Code",
	    },
	    submitHandler: function(form) {
	      form.submit();
	    }
	  });
  
  
  
  
  

});


