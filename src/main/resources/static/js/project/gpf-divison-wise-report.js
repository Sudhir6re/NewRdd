 
$("#GenerateReport")
				.click(
						function(e) {
							
							var subDepartmentId = $("#subDepartmentId").val();
							
							$.ajax({
								type : "GET",
								url : "../clerklvl/getGPFDivReportDtls/"+subDepartmentId,
										
								async : false,
								contentType : 'application/json',
								error : function(data) {
									console.log(data);
									$("#loaderMainNew").hide();
								},
								success : function(data) {
									console.log(data);
									var len = data.length;
									if (len != 0)
										{
										}
										else {
											swal("Record not found!");
										e.preventDefault();
										}
									
								}
							});
							
						});



$("form[name='GPFDivisonWiseReport']").validate({
    // Specify validation rules
    rules: {
    	subDepartmentId:
		{
		required:true,
		min:1
		},
  
    },
    // Specify validation error messages
    messages: {
    	subDepartmentId: "Please Select Division",
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });
