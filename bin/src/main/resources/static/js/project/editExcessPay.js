$("form[id='myEForm']").validate({  
	    rules: {  
	    	txtSancAmountCA:"required",  
	    	txtSancInstallmentsCA: 'required',  
	    	txtInstallmentAmountCA: 'required',  
	    	//totalpaidInst: 'required',  
	    	//totalPaidAmt: 'required',  
	     
	    
	    },  
	    messages: {  
	    	txtSancAmountCA: "Please enter Sanctioned Amount",  
	    	txtSancInstallmentsCA: 'Please enter Sanctioned No. of installments',  
	    	txtInstallmentAmountCA: 'Please Installment Amount',  
	    	//totalpaidInst: 'Please enter no of paid installments',  
	    	//totalPaidAmt: 'Please enter amount recieved',  
	    },  
	    submitHandler: function(form) {
	        form.submit();
	        $("#loaderMainNew").show();
	      }
	  });  