 /* jQuery(document).ready(function($) {
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
*/


	$("#noOfInstallments").change(function(){
		if($("#noOfInstallments").val()!="0"){
		var noInst=$("#noOfInstallments").val();
		var advanceAmount=$("#advanceAmount").val();
	     var remMonForRestirement=$("#remMonForRestirement").val();
	 	$("#amountOfInstallPerMonth").val(Math.round(Number(advanceAmount)/Number(noInst)));
		if(Number(noInst)>Number(remMonForRestirement)){
			swal("Total 1 year service remaining.Not applicable for advances !");
			$("#noOfInstallments").val("0");
			$("#forwardToDDO").prop("disabled",true);
		}else{
			$("#forwardToDDO").prop("disabled",false);
		}
		}
	});
	
	
	
	
	$("#advanceAmount").blur(function(){
		if($("#noOfInstallments").val()!="0" && $("#advanceAmount").val()!="0"){
			var noInst=$("#noOfInstallments").val();
			var advanceAmount=$("#advanceAmount").val();
			$("#amountOfInstallPerMonth").val(Math.round(Number(advanceAmount)/Number(noInst)));
			}
	});
	
	
	
	

	$('body').on('click', '#saveAsdraft', function() {
		$("#gpfAdvanceForm").attr("action", "../saveGPFAdvanceAsDraft");
	})
	
	

	
	
	
	
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
	
	
	
	$("#advanceAmount").blur(function(){
		if($("#advanceAmount").val()!="0"){
			if(Number($("#advanceAmount").val())>Number($("#balCreditOnDtOfApp").val()/2)){
				addErrorClass($("#advanceAmount"),"Requested amount is less then or equal to Maximum Admissible amount");
				$("#advanceAmount").val("");
			}else{
				removeErrorClass($("#advanceAmount"));
			}
		}else{
			removeErrorClass($("#advanceAmount"));
		}
	});
	
	
	$.validator.addMethod('filesize', function (value, element, param) {
	    return this.optional(element) || (element.files[0].size <= param * 1000000)
	}, 'File size must be less than {0} MB');
	
	$.validator.addMethod('minStrict', function (value, el, param) {
	    return value > param;
	});
	
	
	$('form[id="gpfAdvanceForm"]').validate({  
	    rules: {  
	      dateofRegularPay: 'required',  
	      advanceAmount: 'required',  
	      noOfInstallments: {  
	        required: true,  
	        min: 1,  
	       // minStrict:1,
	      },  
	      purposeOfAdvance: {  
	        required: true,  
	        min: 1,  
	        //minStrict:1,
	      },  
	      dateOfDrawingTheLastAdvance: {  
	    	  required: true,  
	      },  
	      isCmpltRepaidInterest: {  
	    	  required: true,  
	    	  min: 1,  
	    	  //minStrict:1,
	      },  
	      amountOfInstallPerMonth: {  
	    	  required: true,  
	    	  min: 1,  
	      },  
	      applicationDate: {  
	    	  required: true,  
	      },  
	      ddoOneRemark: {  
	    	  required: true,  
	      }, 
	      files:{
              required: true,
              accept:"jpg,jpeg,pdf,png",
              filesize:2,
          },  
	    },  
	    messages: {  
	      dateofRegularPay: 'Please Select Date of Regular Pay',  
	      advanceAmount: 'Please enter Advance Amount',  
	      noOfInstallments: 'Please select No Of Installments',  
	      purposeOfAdvance: 'Please select Purpose of Advance',  
	      dateOfDrawingTheLastAdvance: 'Please select Date of Drawing the last Advance',  
	      isCmpltRepaidInterest: 'Please select Repaid with interest select Yes or No',  
	      applicationDate: 'Please select Application Date',  
	      ddoOneRemark: 'Please enter DDO One remark', 
	      files:{
              required: "Please Upload atlest One Document",
              accept:"Only jpg,jpeg,pdf,png types of Documents are allowed ",
              filesize:"Only 2 MB Documents size allowed",
          },
          amountOfInstallPerMonth:'Amount Of Installement Should be Greater Then Zero',
	    },  
	    submitHandler: function(form) {  
	    	
	    	ConfirmBeforeSubRecord("Want to Forward To Next Level !",form,);
	    	
	   //   form.submit();  
	    }  
	  });  
	
	
	
	
	
	
	
	
	
	
	
	
	