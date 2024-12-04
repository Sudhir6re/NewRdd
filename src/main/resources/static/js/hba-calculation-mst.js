
$("form[name='hbacalculationform']").validate({
    // Specify validation rules
    rules: {
    	cityClass:{
    		required: true,
    		min:1
    	},
    	reqSubType:{
    		required: true,
    		min:1
    	},
    	interestRate: "required",
    	maxAmount: "required",
    	fromDate: "required",
    	noInstallment: "required",
    },
    // Specify validation error messages
    messages: {
    	cityClass: "Please Select city class",
    	reqSubType: "Please Select Request Sub type",
    	interestRate: "Please enter Interest Rate",
    	maxAmount: "Please enter maximum amount",
    	fromDate: "Please select From Date",
    	noInstallment: "Please enter No of Installments",
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      $("#loaderMainNew").shw();	
      form.submit();
    }
  });
