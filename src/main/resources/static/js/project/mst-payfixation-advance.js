/*		jQuery(document)
				.ready(
						function($) {
							//alert("qwe");
							var varMessage = $("#message").val();
							///alert("varMessage"+varMessage);

							if (varMessage != "" && varMessage != undefined) {
								swal("Record Saved Successfully..");
							}

							$("#btnSave")
									.click(
											function(e) {
												if ($("#loanStatus").val == "0") {
													e.preventDefault();
													if ($("#PriAmount").val() == $(
															"#preRecovAmount")
															.val()) {
														$("#form1").submit();
													} else {
														swal("Loan can't be Inactive as the total amount is not paid....!!")
													}
												}
											});

						});
	
	*/



$("#searchDiv").hide();
$("#sevaarth")
				.keyup(
						function() {
							var sevaarthId = $("#sevaarth").val();
							if (sevaarthId.length == 0) {
								document.getElementById("searchDiv").innerHTML = "";
								document.getElementById("searchDiv").style.border = "0px";
								return;
							}
							if (sevaarthId != '' && sevaarthId.length != 0) {
								$("#loaderMainNew").show();
								$.ajax({
											type : "POST",
											url : "../level1/getPayfixationEmpInfoBySevaarthId/"
													+ sevaarthId,
											async : false,
											contentType : 'application/json',
											error : function(data) {
												console.log(data);
												$("#loaderMainNew").hide();
											},
											success : function(data) {
												console.log(data);
												$("#loaderMainNew").hide();
												document
														.getElementById("searchDiv").innerHTML = "";
												for (var i = 0; i < data.length; i++) {
													
													$("#searchDiv").show();
													//$("#searchDiv").append(data[i].employeeName+"-"+data[i].sevaarthId);
													//$("#searchDiv").css("border:1px solid #A5ACB2;");
													$("#searchDiv")
															.append(
																	"<li class='empdata' empsevaathid='"+data[i].sevaarthId+"' empname='"+data[i].employeeName+"' " +
																			" empdesgn='"+data[i].designName+"' empid='"+data[i].employeeid+"'>"
																			+ data[i].employeeName
																			+ "-"
																			+ data[i].sevaarthId
																			+ "</li>");
													$("#searchDiv")
															.css(
																	"border:1px solid #A5ACB2;");
												}
												
												
											}
										});
								
								
								
								
								/*  loan details end*/
								
							}
						});

		$('body').on('click', '.empdata', function() {
			$("#sevaarthId").val($(this).attr("empsevaathid"));
			$("#employeeid").val($(this).attr("empid"));
			$("#designName").val($(this).attr("empdesgn"));
			$("#gpfNo").val($(this).attr("gpfNo"));
			$("#sevaarth").val($(this).attr("empname"));
			$("#orgInstName").val($(this).attr("orgInstName"));
			document.getElementById("searchDiv").innerHTML = "";
			
			
			
			/* for loan recovering */
			var empid=$("#employeeid").val();
			var sevaarthId=$("#sevaarthId").val();
		
			
			$("#searchDiv").hide();
			
		});
		
		
		
		
		
		
		$("#btnSave").click(function(e){
			var sevaartrhId=$("#sevaarthId").val();
			var empId=$("#employeeid").val();
				 $.ajax({
				      type: "POST",
				      url: "../level1/checkPayfixAlreadyTaken/"+empId,
				      async: false,
				      dataType : 'json',
				    // contentType:'application/json',
				      error: function(data){
				    	  console.log(data);
				    	 // alert(data);
				      },
				      success: function(data){
				    	  if(parseInt(data) >0 ) {
				    		$("#btnSave").prop("disabled",true);
				    		e.preventDefault();
				    	  }
				    	  else
				    		  {
				    		  $("#btnSave").prop("disabled",false);
				    		  }
				    }
				 });
		});
		
		

		$("form[name='Payfixationform']").validate({
		    // Specify validation rules
		    rules: {
		    	
		    	loanDate:{
		    		"required":true,
		    	},
		    	/*loansancorderdate:{
		    		"required":true,
		    	},*/
		    	loanprinamt:{
		    		"required":true,
		    	},
		    	loanprininstno:{
		    		"required":true,
		    	},
		    	loanprinemiamt:{
		    		"required":true,
		    	},
		    	voucherno:{
		    		"required":true,
		    	},
		    	voucherdate:{
		    		"required":true,
		    	},
		    	
		  
		    },
		    // Specify validation error messages
		    messages: {
		    	loanDate: "Please enter State Name in Marathi",
		    	loanprinamt: "Please enter loan principal amount",
		    	loanprininstno: "Please enter total installment number",
		    	loanprinemiamt: "Please enter emi amount",
		    	voucherno: "Please enter voucher number",
		    	voucherdate: "Please select voucher date"
		    },
		    // Make sure the form is submitted to the destination defined
		    // in the "action" attribute of the form when valid
		    submitHandler: function(form) {
		    		  form.submit();
		    }
		  });
		

		
	
		
		$("#preInstNo").change(function(e){
			var sevaarthId=$("#sevaarthId").val();
			var appId = $('option:selected', this).attr('data-id');
			var preInstNo =$("#preInstNo").val();
			var PriAmount =$("#PriAmount").val();
			 $("#preEMIAmount").val(Number(PriAmount)/Number(preInstNo));
			 var preEMIAmount =$("#preEMIAmount").val();
			 preEMIAmount.setTwoDecimalDigit(0);
			 $("#preEMIAmount").val(preEMIAmount);
		});
		
		
		
		function isPayfixAdvanceTaken(empId){
			 $.ajax({
			      type: "POST",
			      url: "../level1/checkPayfixAlreadyTaken/"+empId,
			      async: false,
			      dataType : 'json',
			    // contentType:'application/json',
			      error: function(data){
			    	  console.log(data);
			    	 // alert(data);
			      },
			      success: function(data){
			    	  if(parseInt(data) >0 ) {
			    		$("#btnSave").prop("disabled",true);
			    		e.preventDefault();
			    	  }
			    	  else
			    		  {
			    		  $("#btnSave").prop("disabled",false);
			    		  }
			    }
			 });
		}
