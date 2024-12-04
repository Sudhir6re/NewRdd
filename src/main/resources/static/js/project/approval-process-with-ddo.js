$(document)
		.ready(
				function() {
					
					
					var varMessage = $("#message").val();

					if (varMessage != "" 
							&& varMessage != undefined) {
						swal("Record Mapped Successfully!", {
							icon : "success",
						});
					}
					
					
					
					$('#labelLevel1').hide();
					$('#labelLevel2').hide();
					$('#labelLevel3').hide();
					$('#labelLevel4').hide();

					$("#department_id")
							.change(
									function() {

										$('#labelLevel1').hide();
										$('#labelLevel2').hide();
										$('#labelLevel3').hide();
										$('#labelLevel4').hide();
										$('#noLevel').empty();
										var departmentId = $("#department_id")
												.val();
										// alert(departmentId);
										if (department_id != '') {
											$
													.ajax({
														type : "GET",
														url : "../master/mstApprovalProcessWithDDO/"
																+ departmentId,
														async : true,
														contentType : 'application/json',
														error : function(data) {
															// console.log(data);
														},
														success : function(data) {

															var i = 0;
															console.log(data);
															// alert(data);
															var len = data.length;
															if (len != 0) {
																$('#btnSave')
																		.prop(
																				"disabled",
																				false);

																$('#noLevel')
																		.empty();
																$('#level1')
																		.empty();
																$('#level2')
																		.empty();
																$('#level3')
																		.empty();
																$('#level4')
																		.empty();
																$('#level8')
																		.empty();
																$('#level9')
																		.empty();
																$('#level10')
																		.empty();
																$('#level11')
																		.empty();

																$('#noLevel')
																		.val("");

																$('#level1')
																		.append(
																				"<option value='0'>Please Select</option>");
																$('#level2')
																		.append(
																				"<option value='0'>Please Select</option>");
																$('#level3')
																		.append(
																				"<option value='0'>Please Select</option>");
																$('#level4')
																		.append(
																				"<option value='0'>Please Select</option>");
																$('#level8')
																		.append(
																		"<option value='0'>Please Select</option>");
																$('#level9')
																		.append(
																		"<option value='0'>Please Select</option>");
																$('#level10')
																		.append(
																		"<option value='0'>Please Select</option>");
																$('#level11')
																		.append(
																		"<option value='0'>Please Select</option>");

																// alert("call");
																// alert("hello");
																var temp = data;
																$
																		.each(
																				temp,
																				function(
																						index,
																						value) {
																					// alert(value[1]);

																					$(
																							'#noLevel')
																							.val(
																									"");
																					$(
																							'#noLevel')
																							.val(
																									value[1]);

																					var level = value[1];
																					// var
																					// j=1;
																					for (i = 1; i <= level; i++) {
																						$(
																								'#labelLevel'
																										+ i)
																								.show();
																					}
																					if (value[3] == 1) {
																						$(
																								'#level1')
																								.append(
																										"<option  value="
																												+ value[4]
																												+ ","
																												+ value[5]
																												+ ">"
																												+ value[2]
																												+ " ("
																												+ value[5]
																												+ ")</option>");
																					}
																					if (value[3] == 2) {
																						$(
																								'#level2')
																								.append(
																										"<option  value="
																												+ value[4]
																												+ ","
																												+ value[5]
																												+ ">"
																												+ value[2]
																												+ " ("
																												+ value[5]
																												+ ")</option>");
																					}
																					if (value[3] == 3) {
																						$(
																								'#level3')
																								.append(
																										"<option  value="
																												+ value[4]
																												+ ","
																												+ value[5]
																												+ ">"
																												+ value[2]
																												+ " ("
																												+ value[5]
																												+ ")</option>");
																					}
																					if (value[3] == 4) {
																						$(
																								'#level4')
																								.append(
																										"<option  value="
																												+ value[4]
																												+ ","
																												+ value[5]
																												+ ">"
																												+ value[2]
																												+ " ("
																												+ value[5]
																												+ ")</option>");
																					}
																					
																					if (value[3] == 8) {
																						$(
																								'#level8')
																								.append(
																										"<option  value="
																												+ value[4]
																												+ ","
																												+ value[5]
																												+ ">"
																												+ value[2]
																												+ " ("
																												+ value[5]
																												+ ")</option>");
																					}
																					
																					if (value[3] == 9) {
																						$(
																								'#level9')
																								.append(
																										"<option  value="
																												+ value[4]
																												+ ","
																												+ value[5]
																												+ ">"
																												+ value[2]
																												+ " ("
																												+ value[5]
																												+ ")</option>");
																					}
																					
																					if (value[3] == 10) {
																						$(
																								'#level10')
																								.append(
																										"<option  value="
																												+ value[4]
																												+ ","
																												+ value[5]
																												+ ">"
																												+ value[2]
																												+ " ("
																												+ value[5]
																												+ ")</option>");
																					}
																					
																					if (value[3] == 11) {
																						$(
																								'#level11')
																								.append(
																										"<option  value="
																												+ value[4]
																												+ ","
																												+ value[5]
																												+ ">"
																												+ value[2]
																												+ " ("
																												+ value[5]
																												+ ")</option>");
																					}
																					
																					if (value[3] == 18) {
																						$(
																								'#level18')
																								.append(
																										"<option  value="
																												+ value[4]
																												+ ","
																												+ value[5]
																												+ ">"
																												+ value[2]
																												+ " ("
																												+ value[5]
																												+ ")</option>");
																					}
																					if (value[3] == 19) {
																						$(
																								'#level19')
																								.append(
																										"<option  value="
																												+ value[4]
																												+ ","
																												+ value[5]
																												+ ">"
																												+ value[2]
																												+ " ("
																												+ value[5]
																												+ ")</option>");
																					}
																					if (value[3] == 20) {
																						$(
																								'#level20')
																								.append(
																										"<option  value="
																												+ value[4]
																												+ ","
																												+ value[5]
																												+ ">"
																												+ value[2]
																												+ " ("
																												+ value[5]
																												+ ")</option>");
																					}
																					if (value[3] == 21) {
																						$(
																								'#level21')
																								.append(
																										"<option  value="
																												+ value[4]
																												+ ","
																												+ value[5]
																												+ ">"
																												+ value[2]
																												+ " ("
																												+ value[5]
																												+ ")</option>");
																					}
																					if (value[3] == 22) {
																						$(
																								'#level22')
																								.append(
																										"<option  value="
																												+ value[4]
																												+ ","
																												+ value[5]
																												+ ">"
																												+ value[2]
																												+ " ("
																												+ value[5]
																												+ ")</option>");
																					}
																					if (value[3] == 23) {
																						$(
																								'#level23')
																								.append(
																										"<option  value="
																												+ value[4]
																												+ ","
																												+ value[5]
																												+ ">"
																												+ value[2]
																												+ " ("
																												+ value[5]
																												+ ")</option>");
																					}
																					if (value[3] == 24) {
																						$(
																								'#level24')
																								.append(
																										"<option  value="
																												+ value[4]
																												+ ","
																												+ value[5]
																												+ ">"
																												+ value[2]
																												+ " ("
																												+ value[5]
																												+ ")</option>");
																					}
																					if (value[3] == 25) {
																						$(
																								'#level25')
																								.append(
																										"<option  value="
																												+ value[4]
																												+ ","
																												+ value[5]
																												+ ">"
																												+ value[2]
																												+ " ("
																												+ value[5]
																												+ ")</option>");
																					}
																					if (value[3] == 26) {
																						$(
																								'#level26')
																								.append(
																										"<option  value="
																												+ value[4]
																												+ ","
																												+ value[5]
																												+ ">"
																												+ value[2]
																												+ " ("
																												+ value[5]
																												+ ")</option>");
																					}
																					
																				});
															} else {
																$('#noLevel')
																		.val("");
																$('#btnSave')
																		.prop(
																				"disabled",
																				true);
																swal("Record not found !!!");

															}

														}
													});
										}

									});

				});
$("form[name='workFlowCharter']").validate({
    // Specify validation rules
    rules: {
    	department_code:
    		{
    		required:true,
    		min:1
    		},
    		no_of_level:"required",
    		ddo_code_level1:
    			{
    			required:true,
        //		min:1
    			},
    			ddo_code_level2:
    				{
    				required:true,
    			//	min:1
    				},
  
    },
    // Specify validation error messages
    messages: {
    	department_code: "Please Select Department Name",
    	no_of_level: "Please Enter No of Levels ",
    	ddo_code_level1: "Please Select Level 1 DDO Code",
    	ddo_code_level2:"Please Select Level 2 DDO Code",
    	
    	
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
      $("#loaderMainNew").show();
    }
  });

$('#btnReset').click(
	    function(){
	    	$("#department_id").select2("val","0");
	    });









$("#level18").change(function(){
		
		 //var context=$("#context").val();
		  var srClerkId=$("#level18").val();
		  
		  var arr=srClerkId.split(",");
		  
		  var ddoRegId=arr[0];
		  var ddoCode=arr[1];
		  
		  
		  var  srDdoCode=ddoCode.substring(0,8);
		  
		  
			$.ajax({
				type : "GET",
				url : "../master/getSrAndOsList/"
							+ srDdoCode,
				async : true,
				contentType : 'application/json',
				error : function(data) {
					 console.log(data);
				// alert("error");
				},
				 beforeSend: function(){
					 $("#loaderMainNew").show();
				   },
				   complete: function(){
					   $("#loaderMainNew").hide();
				   },
				success : function(data) {
					
					   $("#loaderMainNew").hide();
					 console.log(data);
					var len = data.length;
					if (len != 0) { 
					$("#level20").empty();
					$("#level21").empty();
					 var newOption = $('<option value="0">Please Select</option>');
	            	 $('#level20').append(newOption);
	            	 $('#level21').append(newOption);
	                for(var i=0;i<len;i++){
	                	if(data[i].levelHierarchy==18){
	                		 newOption = $('<option value="'+data[i].ddoRegId+','+data[i].ddoCode+'">'+data[i].ddoCode+'</option>');
		                	 $('#level20').append(newOption); 
	                	}
	                	if(data[i].levelHierarchy==19){
	                		 newOption = $('<option value="'+data[i].ddoRegId+','+data[i].ddoCode+'">'+data[i].ddoCode+'</option>');
		                	 $('#level21').append(newOption); 
	                	}
	                }				
				   }
				}
			});
		 
			
			
	  });

