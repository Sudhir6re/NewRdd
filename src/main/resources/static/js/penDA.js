$("form[name='penDA']").validate(
		{

			ignore : ".ignore",

			// Specify validation rules
			rules : {
				
				startDate : "required",
				endDate : "required",
				percentage : "required",

				payCommissionCode : {
					required : true,
					min : 1  
				},
				
				

			},

			messages : {

				startDate : " Please enter Start date",
				endDate : " Please enter End date",
				percentage : " Please enter enter DA percentage",
				payCommissionCode : " Please select pay commission",
			},
			submitHandler : function(form) {
				form.submit();
			}
		});


$("form[name='editpenDA']").validate(
		{

			ignore : ".ignore",

			// Specify validation rules
			rules : {
				
				startDate : "required",
				endDate : "required",
				percentage : "required",

				payCommissionCode : {
					required : true,
					min : 1
				},
				
				

			},

			messages : {

				startDate : " Please enter Start date",
				endDate : " Please enter End date",
				percentage : " Please enter enter DA percentage",
				payCommissionCode : " Please select pay commission",
			},
			submitHandler : function(form) {
				form.submit();
			}
		});


function validateDA() {
	// alert('inside validateUIDUniqe');
	var paycommission = document.getElementById("payCommision1").value;
	var startdate = document.getElementById("startDate").value;
	
	
	
		if (paycommission != '') {
			$
					.ajax({
						type : "GET",
						url : "validateDA/"+paycommission+"/"+startdate,
						async : false,
						contentType : 'application/json',
						error : function(data) {
						},
						success : function(data) {
							var len = data.length;
							var checkFlag = data;
							if (checkFlag == 0) {
								$('#payCommision1').val(paycommission);
								status = true;
							} else if (checkFlag > 0) {

								swal('Starting date'+startdate+' Already Present in the system for selected paycomission, Please enter the Different date !!!');

								document.getElementById("payCommision1").value = "";
								status = false;
							}
							return status;
						}
					});
		}
	}