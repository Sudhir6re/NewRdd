$("#addDoc").click(function() {
					var rowCount = $('#documentTbl tr').length;
					var col1 = '<td>' + (Number(rowCount)) + '</td>';
					var col2 = '<td align="left" style="vertical-align: middle;"><div class="custom-file"><input type="file" id="files" class="custom-file-input form-control input-sm" name="files" style="padding: 4px 11px !important;"></div></td>';
					var col3 = '<td align="center" class="delete" style="vertical-align: middle;"><a><span class="glyphicon glyphicon-trash delete"></span></a></td>';
					//$("#documentTbl").append('<tr>'+col1+col2+col3+'</tr>');
					$('#documentTbl tr:last').after(
							'<tr>' + col1 + col2 + col3 + '</tr>');
				});

$(document).on('click', '.delete', function() {
	$(this).closest("tr").remove();
});




$("#advanceAmount").blur(function(){
	if($("#advanceAmount").val()!="0"){
		if(Number($("#advanceAmount").val())>Number($("#maximumAmtAdmissible").val())){
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


$('form[id="gpfWithdrawalForm"]').validate({  
    rules: {  
      dateofRegularPay: 'required',  
      advanceAmount: 'required',  
      purposeOfAdvance: {  
        required: true,  
        min: 1,  
      },  
      dateOfDrawingTheLastAdvance: {  
    	  required: true,  
      },  
      applicationDate: {  
    	  required: true,  
      },  
      ddoOneRemark: {  
    	  required: true,  
      }, 
      files:{
          required: true,
        //  accept:"jpg,jpeg,pdf",
          filesize:2,
      },  
    },  
    messages: {  
      dateofRegularPay: 'Please Select Date of Regular Pay',  
      advanceAmount: 'Please enter Advance Amount',  
      purposeOfAdvance: 'Please select Purpose of Advance',  
      dateOfDrawingTheLastAdvance: 'Please select Date of Drawing the last Advance',  
      applicationDate: 'Please select Application Date',  
      ddoOneRemark: 'Please enter DDO One remark',  
      files:{
          required: "Please Upload atlest One Document",
          //accept:"Only pdf types of Documents are allowed ",
          filesize:"Only 2 MB Documents size allowed",
      }  ,
    },  
    submitHandler: function(form) {  
    	//alert("hii");
    	ConfirmBeforeSubRecord("Want to Forward To Next Level !",form,);
   //   form.submit();  
    }  
  });  


$('body').on('click', '#saveAsdraft', function() {
	$("#gpfWithdrawalForm").attr("action", "../saveGPFAdvanceAsDraft");
})

	
	

