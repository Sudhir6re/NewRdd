$(".delete").click(function(e){
		var gpfAdvanceDocumentsId=$(this).attr("data");
        if(gpfAdvanceDocumentsId!=''){
        	$("#loaderMainNew").show();
    		$.ajax({
    					type : "GET",
    					url : "../../../master/deleteDocuments/"
    							+ gpfAdvanceDocumentsId,
    					async : false,
    					contentType : 'application/json',
    					error : function(data) {
    						console.log(data);
    						$("#loaderMainNew").hide();
    					},
    					success : function(data) {
    						console.log(data);
    						$("#loaderMainNew").hide();
    						if(data==true){
    							swal("Document deleted successfully");
    							 $(this).closest("tr").remove();
    						}else{
    							swal("Something went wrong");
    						}
    					}
    				});
        }
	});
	
	
	$.validator.addMethod('filesize', function (value, element, param) {
	    return this.optional(element) || (element.files[0].size <= param * 1000000)
	}, 'File size must be less than {0} MB');
	
	$.validator.addMethod('minStrict', function (value, el, param) {
	    return value > param;
	});
	
	
	$('form[id="resendGpfForm"]').validate({  
	    rules: {  
	      dateofRegularPay: 'required',  
	      advanceAmount: 'required',  
	      noOfInstallments: {  
	        required: $("#typeOfAdvance").val()=="1",  
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
	    	  required: $("#typeOfAdvance").val()=="1",  
	    	  min: 1,  
	    	  //minStrict:1,
	      },  
	      amountOfInstallPerMonth: {  
	    	  required: $("#typeOfAdvance").val()=="1",  
	    	  min: 1,  
	      },  
	      applicationDate: {  
	    	  required: true,  
	      },  
	      ddoOneRemark: {  
	    	  required: true,  
	      }, 
	      files:{
              required:($('#lstDocTbl >tbody >tr').length==0  && document.getElementById("files").files.length==0),
              accept:"jpg,jpeg,pdf",
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
              accept:"Only jpg,jpeg,pdf types of Documents are allowed ",
              filesize:"Only 2 MB Documents size allowed",
          },
          amountOfInstallPerMonth:'Amount Of Installement Should be Greater Then Zero',
	    },  
	    submitHandler: function(form,event) {  
	    if($('#lstDocTbl >tbody >tr').length==0 && document.getElementById("files").files.length==0){
	    	event.preventDefault();
	    	swal("Please add atleast one document");
	    }else{
	    	form.submit();  
	    }	
	    }  
	  });  
	
	
	
	$("#noOfInstallments").change(function(){
		if($("#noOfInstallments").val()!="0"){
		var noInst=$("#noOfInstallments").val();
		var advanceAmount=$("#advanceAmount").val();
		$("#amountOfInstallPerMonth").val(Math.round(Number(advanceAmount)/Number(noInst)));
		}
	});
	
	
	
	
	$("#advanceAmount").blur(function(){
		if($("#noOfInstallments").val()!="0" && $("#advanceAmount").val()!="0"){
			var noInst=$("#noOfInstallments").val();
			var advanceAmount=$("#advanceAmount").val();
			$("#amountOfInstallPerMonth").val(Math.round(Number(advanceAmount)/Number(noInst)));
			}
	});
	
	
	
	
	
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
	
	$(document).on('click', '#deleteApp', function(){ 
          var gpfAdvanceId=$("#gpfAdvanceid").val();
          if(gpfAdvanceId!='' && gpfAdvanceId!=undefined){
        	  swal({
            	  title: "Are you sure?",
            	  text: "Once deleted, you will not be able to recover this Application!",
            	  icon: "warning",
            	  buttons: true,
            	  dangerMode: true,
            	})
            	.then((willDelete) => {
            	  if (willDelete) {
            		 var flag=deleteAdvanceReq(gpfAdvanceId);
            		  if(flag=="1"){
            			  
            			  swal("Application has been deleted !!!", {
                    	      icon: "success",
                    	    }); 
            			  window.history.go(-3);
            		  }else{
            			  swal("Something Went Wrong !!!", {
                    	      icon: "warning",
                    	    }); 
            		  }
            	  } else {
            		  swal("Application is safe !!!", {
                	      icon: "success",
                	    }); 
            	  }
            	});
          } 
	});
	
	
	// is in process
	function deleteAdvanceReq(gpfAdvanceId)
	{
		flag=0;
		 $.ajax({
		      type: "GET",
		      url: "../../deleteAdvanceReq/"+gpfAdvanceId,
		      async: false,
		      dataType : 'json',
		      //contentType:'application/json',
		      error: function(data){
		    	  console.log(data);
		      },
		      success: function(data){
		    	  if(parseInt(data) >0 ) {
		    		  flag=1;
		    	  }else{
		    		 flag=0;
		    	 }
		    }
		 });
		 return flag;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	$("#advanceAmount").blur(function(){
		if($("#advanceAmount").val()!="0"){
			if(Number($("#advanceAmount").val())>Number($("#maximumAmtAdmissible").val())){
				swal("Maximum Admissible amount is less then or equal to requested amount");
				$("#advanceAmount").val("");
			}
		}
	});
	