$(document)
		.ready(
				function() {
					var allow_deduct_id = [];
					var is_type = [];
					var serialid = [];
					var logUserId = $('#logUser').text();
					$('#dbData').DataTable();
					var departmentId; // department Id
					var department_allowdeduc_id = []; // department_allowdeduc_id
					$("#btnUpdate").hide();
					// $("#btnSave").hide();

					var sevarthId = 0;
					$('#reset').click(function() {
						allow_deduct_id = [];
						is_type = [];
						serialid = [];

						departmentId; // department Id
						department_allowdeduc_id = []; // department_allowdeduc_id

						$('#myform')[0].reset();
					});
					
					
					$("#selectAll").change(function(){
						serialid = [];
						department_allowdeduc_id = []; 
						if($(this).prop("checked")){
							  $(".chk").prop("checked",true);
						    $('.chk:checkbox:checked').each(function () {
						    	department_allowdeduc_id.push($(this).val());
								  serialid.push($(this).attr("data-serialid"));
								});
						}else{
							  $(".chk").prop("checked",false);
						}
					});
					
					
					
					
				
					
					$(document).on(
							'change',
							'.chk',
							function() {
								// code here
								if ($(this).is(":checked")) {
									// departmentId.push($(this).attr("value"));5

									console.log("before  adding element"
											+ department_allowdeduc_id);

									departmentId = $(this)
											.attr("data-serialid");

									department_allowdeduc_id.push($(this).attr(
											"value"));

									console.log("after adding element"
											+ department_allowdeduc_id);
									
									
									var componentName=$(this).attr("typecomponent"); 
									
									
									if(componentName.includes("NPS_EMPR")){
										$("input[typecomponent*='NPS_EMPR']").prop("checked",true);
									}
									
									
									
									
									if(componentName.includes("5th PC TA")){
										$("input[typecomponent*='5th PC TA']").prop("checked",true);
										$("input[typecomponent*='6th PC TA']").prop("checked",false);
										$("input[typecomponent*='7th PC TA']").prop("checked",false);
										$("input[typecomponent*='Transport Allowance (T.A)']").prop("checked",false);
									}else if(componentName.includes("6th PC TA")){
										$("input[typecomponent*='6th PC TA']").prop("checked",true);
										$("input[typecomponent*='5th PC TA']").prop("checked",false);
										$("input[typecomponent*='7th PC TA']").prop("checked",false);
										$("input[typecomponent*='Transport Allowance (T.A)']").prop("checked",false);
									}else if(componentName.includes("7th PC TA")){
										$("input[typecomponent*='7th PC TA']").prop("checked",true);
										$("input[typecomponent*='5th PC TA']").prop("checked",false);
										$("input[typecomponent*='6th PC TA']").prop("checked",false);
										$("input[typecomponent*='Transport Allowance (T.A)']").prop("checked",false);
									}
									else if(componentName.includes("Transport Allowance (T.A)")){
										$("input[typecomponent*='Transport Allowance (T.A)']").prop("checked",true);
										$("input[typecomponent*='7th PC TA']").prop("checked",false);
										$("input[typecomponent*='5th PC TA']").prop("checked",false);
										$("input[typecomponent*='6th PC TA']").prop("checked",false);
									}
									
									if(componentName.includes("5th PC HRA")){
										$("input[typecomponent*='5th PC HRA']").prop("checked",true);
										$("input[typecomponent*='6th PC HRA']").prop("checked",false);
										$("input[typecomponent*='6th PC HRA']").prop("checked",false);
										$("input[typecomponent*='House Rent Allowance (H.R.A)']").prop("checked",false);
									}else if(componentName.includes("6th PC HRA")){
										$("input[typecomponent*='6th PC HRA']").prop("checked",true);
										$("input[typecomponent*='5th PC HRA']").prop("checked",false);
										$("input[typecomponent*='7th PC HRA']").prop("checked",false);
										$("input[typecomponent*='House Rent Allowance (H.R.A)']").prop("checked",false);
									}else if(componentName.includes("7th PC HRA")){
										$("input[typecomponent*='7th PC HRA']").prop("checked",true);
										$("input[typecomponent*='5th PC HRA']").prop("checked",false);
										$("input[typecomponent*='6th PC HRA']").prop("checked",false);
										$("input[typecomponent*='House Rent Allowance (H.R.A)']").prop("checked",false);
									}
									else if(componentName.includes("House Rent Allowance (H.R.A)")){
										$("input[typecomponent*='House Rent Allowance (H.R.A)']").prop("checked",true);
										$("input[typecomponent*='7th PC HRA']").prop("checked",false);
										$("input[typecomponent*='5th PC HRA']").prop("checked",false);
										$("input[typecomponent*='6th PC HRA']").prop("checked",false);
									}
										
									
									//$("input[typecomponent*='"+componentName+"']").prop("checked",true);   
									//for four component selection 
									

								} else if ($(this).is(":not(:checked)")) {

									
	                                var componentName=$(this).attr("typecomponent"); 
									
									
									if(componentName.includes("NPS_EMPR")){
										$("input[typecomponent*='NPS_EMPR']").prop("checked",false);
									}
									
									
									//$("input[typecomponent*='"+componentName+"']").prop("checked",false);   
									//for four component deselection  
									
									var removeItem = $(this).attr("id");

								////	departmentId = 15L;

									var rm = $(this).attr("value");

									department_allowdeduc_id = jQuery.grep(
											department_allowdeduc_id, function(
													value) {
												return value != rm;
											});

									// alert($(this).attr("value"));
									console.log("after removing element"
											+ department_allowdeduc_id);

								}
							});
					$(".btnSave")
							.click(
									function() {
										var checkPaybillInProcess = isPaybillIsInProcess(sevarthId);

										if (checkPaybillInProcess == "0" ) {
											var action = 1;
											// serialid.push(0);
											
											
											
											
											
											    serialid = [];
										     	department_allowdeduc_id = []; 
											    $('.chk:checkbox:checked').each(function () {
											    	department_allowdeduc_id.push($(this).val());
													  serialid.push($(this).attr("data-serialid"));
													});
											
											
											
											

											var effectiveDate = $(
													'#effectiveDate').val();

											var empId = $("#empId").val();
											var sevaarthId = $("#sevaarthId")
													.val();

										////	console.log(departmentId);
											console.log("save data");
											console
													.log(department_allowdeduc_id);
                                            if($("input:radio[name='chkId']").is(":checked")==false){
                                            	swal("Please Select Atleast One Employee");
                                            } 
                                            else if (effectiveDate == ""
													|| effectiveDate == "undefined") {
												swal("Please select effective date");
											}  else if(department_allowdeduc_id.length>0){
												
													$(".loaderMainNew").show();
													
												$
														.ajax({
															type : "GET",
															url : "../ddoast/saveEmpMpgDdoAllowDeduc/"
																	+ department_allowdeduc_id
																	+ "/"
																	+ empId
																	+ "/"
																	+ sevaarthId
																	+ "/"
																	+ effectiveDate,
															async : true,
															contentType : 'application/json',
															error : function(
																	data) {
																console
																		.log(data);
																
																$(".loaderMainNew").hide();
																// alert(data);
															},
															success : function(
																	data) {
																$(".loaderMainNew").hide();
																console
																		.log(data);

																swal(
																		"Saved Successfuly !",
																		{
																			icon : "success",
																		});
																setTimeout(
																		function() {
																			location
																					.reload(true);
																		}, 3000);
															}
														});
												}else {
												swal("Please Select or De-select Some Component");
											}
										} else {
											swal("PayBill Is In Process !!!");
										}

									});
					$("#ddo_code")
							.change(
									function() {
										$('input[type=checkbox]').each(
												function() {
													this.checked = false;
												});
										$("#customFields").empty();

										$("#customFields").show();

										$("#selectedemp").hide();

										$("#componentsList1").hide();

										// alert("ddo code changed");

										var input = $("#ddo_code").val();
										// alert("DDO CODE is "+input);
										if (input != '') {
											$
													.ajax({
														type : "GET",
														url : "../ddoast/empEligibilityForAllowAndDeduct1/"
																+ input,
														async : true,
														contentType : 'application/json',
														error : function(data) {
															// console.log(data);
														},
														success : function(data) {
															var len = data.length;
															if (len != 0) {

																// console.log(data);
																var temp = data;
																$(
																		"#customFields")
																		.append(
																				'<tr><th width = "55" style="border:none;"><b>Sevaarth Id</b></th><th width = "55" style="border:none;"><b>Employee Name</b></th><th width = "90" style="border:none;"><b>Designation name</b></th><th width = "55" style="border:none;"><b>Department Name</b></th><th width = "55" style="border:none;"><b>Sub Department Name</b></th><th width="90" style="border:none;"><b>Action</b></th></tr>');
																$
																		.each(
																				temp,
																				function(
																						index,
																						value) {
																					console
																							.log(value[2]);
																					$(
																							"#customFields")
																							.append(
																									'<tr ><td style="border:none;display:none;" class="employee" >'
																											+ value[4]
																											+ '</td><td style="border:none;display:none;" >'
																											+ value[0]
																											+ '</td><td style="border:none;" class="employee">'
																											+ value[0]
																											+ '</td><td style="border:none;" class="employee">'
																											+ value[1]
																											+ '</td><td style="border:none;" class="employee">'
																											+ value[2]
																											+ '</td><td style="border:none;" class="employee">'
																											+ value[4]
																											+ '</td><td style="border:none;" class="employee">'
																											+ value[3]
																											+ '</td><td style="border:none;" class="employee" ><span class="btn btn-primary" style="color:white;" >Add</span></td></tr>');

																					$(
																							"#customFields")
																							.css(
																									"border-color",
																									"white");

																				});
															} else {
																swal("Record not found !!!");
															}

														}
													});
										}
										{
											// alert("Please Select DDO Code");
										}
									});

					$("#customFields")
							.on(
									'click',
									'.employee',
									function(e) {
										// $("#customFields").fadeOut(1000);
										// get the current row

										$("#componentsList1").show();

										var currentRow = $(this).closest("tr");
										// var emp_id =
										// currentRow.find("td:eq(0)").text();
										// // get current row 2nd table cell TD
										// value
										var emp_id = currentRow
												.find("td:eq(3)").text();
										var department_id = $("#ddo_code")
												.val();
										$("#emp_sevaarthid").text(
												currentRow.find("td:eq(2)")
														.text());
										$("#emp_name").text(
												currentRow.find("td:eq(3)")
														.text());
										$("#emp_designation").text(
												currentRow.find("td:eq(4)")
														.text());
										$("#emp_subdept").text(
												currentRow.find("td:eq(5)")
														.text());
										$("#emp_dept").text(
												currentRow.find("td:eq(6)")
														.text());

										// alert(emp_id+department_id);
										$
												.ajax({
													type : "GET",
													url : "../ddoast/empEligibilityForAllowAndDeductMappingList/"
															+ department_id,
													async : true,
													contentType : 'application/json',
													error : function(data) {
														console.log(data);
														// alert(data);
													},
													success : function(data) {
														console.log(data);

														$("#customFields")
																.hide();

														$("#selectedemp")
																.show();

														var temp = data;

														$
																.each(
																		temp,
																		function(
																				index,
																				value) {

																			/////departmentId = value[0];
																			if (value[4] == '1') {
																				$(
																						"#tbl1")
																						.append(
																								'<tr><td style="border:none;"><input type="checkbox" class="chk1"  name="test" data-serialid="'
																										+ value[0]
																										+ '" value="'
																										+ value[2]
																										+ '" id="'
																										+ value[4]
																										+ '"/>'
																										+ value[3]
																										+ '</td></tr>');

																				// $("#tbl1").append('<tr><td><input
																				// type="checkbox"
																				// class="chk1"/></td></tr>')

																			}
																			if (value[4] == '2') {
																				$(
																						"#tbl2")
																						.append(
																								'<tr><td style="border:none;"><input type="checkbox" class="chk1" name="test"  data-serialid="'
																										+ value[0]
																										+ '" value="'
																										+ value[2]
																										+ '" id="'
																										+ value[4]
																										+ '"/>'
																										+ value[3]
																										+ '</td></tr>');
																			}
																			if (value[4] == '3') {
																				$(
																						"#tbl3")
																						.append(
																								'<tr><td style="border:none;"><input type="checkbox" class="chk1"  name="test"   data-serialid="'
																										+ value[0]
																										+ '" value="'
																										+ value[2]
																										+ '" id="'
																										+ value[4]
																										+ '"/>'
																										+ value[3]
																										+ '</td></tr>');
																			}
																			if (value[4] == '4') {
																				$(
																						"#tbl4")
																						.append(
																								'<tr><td style="border:none;"><input type="checkbox" class="chk1" name="test"   data-serialid="'
																										+ value[0]
																										+ '" value="'
																										+ value[2]
																										+ '" id="'
																										+ value[4]
																										+ '"/>'
																										+ value[3]
																										+ '</td></tr>');
																			}
																		});

														var sevaarth_id = $(
																"#emp_sevaarthid")
																.text();

														// alert(sevaarth_id);
														if (sevaarth_id != '') {
															$
																	.ajax({
																		type : "GET",
																		url : "../ddoast/empEligibilityForAllowAndDeductCheckBoxId/"
																				+ sevaarth_id,
																		// url:
																		// "empEligibilityForAllowAndDeductMappingList/"+department_id,
																		async : true,
																		contentType : 'application/json',
																		error : function(
																				data) {
																			console
																					.log(data);
																			// alert("controller
																			// missing");
																		},
																		success : function(
																				data) {

																			// console.log("first
																			// controller
																			// data");
																			console
																					.log(data);
																			// alert("success");

																			var len = data.length;
																			if (len == 0) {
																				$(
																						"#btnSave")
																						.show();
																				$(
																						"#btnUpdate")
																						.hide();

																				// swal("No
																				// component
																				// mapped");
																			} else {
																				$(
																						"#btnUpdate")
																						.show();
																				$(
																						"#btnSave")
																						.hide();
																				var temp = data;
																				var tem = [];
																				serialid = [];
																				allow_deduct_id = [];
																				$
																						.each(
																								temp,
																								function(
																										index,
																										value) {
																									console
																											.log("before value"
																													+ value[0]); // 15
																									// alert(value[2]);
																									setTimeout(
																											function() {
																												$(
																														'input[name="test"][value="'
																																+ value[0]
																																+ '"]')
																														.prop(
																																"checked",
																																true);
																												console
																														.log("hhhhhhh");
																											},
																											2000);

																									allow_deduct_id
																											.push(value[0]);

																									// $("#"+value[2]).attr("data-serialid",value[0]);

																									// serialid.push(value[0]);

																								});
																			}
																		}
																	});
														}

													}
												});
									});

					// tblid

					// class

					$("#dbData")
							.on(
									'change',
									'.AddAllowanceDeduction',
									function(e) {

										allow_deduct_id = [];
										is_type = [];
										serialid = [];

										departmentId; // department Id
										department_allowdeduc_id = []; // department_allowdeduc_id

										$('input[type=checkbox]').prop(
												"checked", false);

										var currentRow = $(this).closest("tr");
										// var emp_id =
										// currentRow.find("td:eq(0)").text();
										// // get current row 2nd table cell TD
										// value
										sevarthId = $(this).attr("data");
										// alert("in sevarthId"+sevarthId);

										var empName = currentRow.find(
												"td:eq(1)").text();
										var designationName = currentRow.find(
												"td:eq(2)").text();
										var departmentName = currentRow.find(
												"td:eq(3)").text();

										var empName = currentRow.find(
												"td:eq(1)").text();
										var designationName = currentRow.find(
												"td:eq(2)").text();
										var departmentName = currentRow.find(
												"td:eq(3)").text();

										var isDcpsGpf = currentRow.find(
												"td:eq(7)").text();

										var payCommission = currentRow.find(
												"td:eq(5)").text();
										// alert(payCommission);

										if (payCommission == "Sixth Pay Commission") {
											$("input[typecomponent*='7PC DA']")
													.css("pointer-events",
															"none");
											$(
													"input[typecomponent*='6PC DA']")
													.css("pointer-events",
															"auto");
										} else {
											$(
													"input[typecomponent*='6PC DA']")
													.css("pointer-events",
															"none");
											$("input[typecomponent*='7PC DA']")
													.css("pointer-events",
															"auto");
										}

										// alert(isDcpsGpf);

										// .none {cursor: none;}

										if (isDcpsGpf == "GPF") {
											$("input[typecomponent*='DCPS']")
													.css("pointer-events",
															"none");
											$("input[typecomponent*='GPF']")
													.css("pointer-events",
															"auto");

											// $("input[typecomponent*='DCPS']").prop('checked',
											// false);

										} else {
											$("input[typecomponent*='DCPS']")
													.css("pointer-events",
															"auto");

											// $("input[typecomponent*='GPF']").prop('checked',
											// false);

											$("input[typecomponent*='GPF']")
													.css("pointer-events",
															"none");

										}
										var empId = $(this).val();

										$('#empId').val(empId);
										$('#sevaarthId').val(sevarthId);

										var department_id = $(this).val();
										// alert("empId is"+empId);
										// alert("okay i am ");

										$("#customFields").empty();

										$("#componentsList1").show();

										$("#customFields")
												.append(
														'<tr><th width = "55" ><b>Sevaarth Id</b></th><th width = "55" ><b>Employee Name</b></th><th width = "90" ><b>Designation name</b></th><th width = "55" ><b>Department Name</b></th><th width = "55" ><b>Sub Department Name</b></th></tr>');
										$("#customFields")
												.append(
														'<tr><th width = "55" >'
																+ sevarthId
																+ '</th><th width = "55" >'
																+ empName
																+ '</th><th width = "90" >'
																+ designationName
																+ '</th><th width = "55" >'
																+ departmentName
																+ '</th><th width = "55" >'
																+ empId
																+ '</th></tr>');

										if (sevarthId != '') {
											$
													.ajax({
														type : "GET",
														url : "../ddoast/empEligibilityForAllowAndDeductCheckBoxId/"
																+ sevarthId,
														// url:
														// "empEligibilityForAllowAndDeductMappingList/"+department_id,
														async : true,
														contentType : 'application/json',
														error : function(data) {
															console.log(data);
															// alert("controller
															// missing");
														},
														success : function(data) {

															$('html, body')
																	.animate(
																			{
																				scrollTop : $(
																						"#tblDataTable")
																						.offset().top
																			},
																			2000);

															console
																	.log("first controller data");
															console.log(data);
															// alert("success");

															var len = data.length;
															if (len == 0) {
																$("#btnSave")
																		.show();
																$("#btnUpdate")
																		.hide();
															} else {
																department_allowdeduc_id = [];
																serialid = [];
																$("#btnUpdate")
																		.show();
																$("#btnSave")
																		.hide();
																var temp = data;
																var tem = [];

																$
																		.each(
																				temp,
																				function(
																						index,
																						value) {
																					console
																							.log(value[0]); // 15
																					// alert(value[2]);

																					var today = new Date(
																							value[3]);

																					// console.log("e
																					// date"+d);

																					var genDate = today
																							.getFullYear()
																							+ '-'
																							+ ('0' + (today
																									.getMonth() + 1))
																									.slice(-2)
																							+ '-'
																							+ ('0' + today
																									.getDate())
																									.slice(-2);

																					document
																							.getElementById("effectiveDate").value = genDate;

																					document
																							.getElementById(
																									"effectiveDate")
																							.setAttribute(
																									"min",
																									genDate);
																									
																									
																									
																									if(value[3]!="" && value[3]!=null && value[3]!=undefined){
																															$("#effectiveDate").prop("readonly",true);
																										}

																					////departmentId = value[0];

																					$(
																							'input[name="test"][value="'
																									+ value[0]
																									+ '"]')
																							.prop(
																									"checked",
																									true);

																					department_allowdeduc_id
																							.push(value[0]);

																					console
																							.log("after adding element"
																									+ department_allowdeduc_id);

																					// $("#"+value[2]).attr("data-serialid",value[0]);

																					serialid
																							.push(value[0]);

																					/*
																					 * if
																					 * (isDcpsGpf ==
																					 * "GPF") {
																					 * $("input[typecomponent*='DCPS']").prop('checked',
																					 * false); }
																					 * else {
																					 * $("input[typecomponent*='GPF']").prop('checked',
																					 * false); }
																					 * 
																					 * 
																					 * if
																					 * (payCommission ==
																					 * "Sixth
																					 * Pay
																					 * Commission") {
																					 * $("input[typecomponent*='7PC
																					 * DA']").prop('checked',
																					 * false);
																					 * $("input[typecomponent*='Dearness
																					 * Allowance
																					 * (D.A)']").prop('checked',
																					 * true); }
																					 * else {
																					 * $("input[typecomponent*='Dearness
																					 * Allowance
																					 * (D.A)']").prop('checked',
																					 * false);
																					 * $("input[typecomponent*='7PC
																					 * DA']").prop('checked',
																					 * true); }
																					 */

																				});
															}
														}
													});
										}

									}); // check box end

				});

$(document).ready(
		function() {
			var today = new Date();
			var day = today.getDate() > 9 ? today.getDate() : "0"
					+ today.getDate(); // format should be "DD" not "D" e.g 09
			var month = (today.getMonth() + 1) > 9 ? (today.getMonth() + 1)
					: "0" + (today.getMonth() + 1);
			var year = today.getFullYear();

			$("#dpFromDate").attr('max', year + "-" + month + "-" + day);
		});

function isPaybillIsInProcess(sevaarthId) {
	// alert("hello");
	var flag = 0;
	$.ajax({
		type : "GET",
		url : "../ddoast/isPaybillIsInProcess/" + sevaarthId,
		async : false,
		contentType : 'application/json',
		error : function(data) {
			console.log(data);
			// alert("error is"+data);
		},
		success : function(data) {
			console.log(data);
			// alert("data");
			flag = data.length;
		}
	});
	return flag;
}

// all validation
var salutationError;
var  fNameError;
var deptNameEnglishError;
var deptNameMarathiError;
var warning;
if ($('#language').val() == "en") {
	 salutationError = "Please Select salutation !!!";
	 fNameError = "Please enter first name !!!";
     deptNameEnglishError = "Please enter department name in english !!!";
	deptNameMarathiError = "Please enter department name in marathi !!!";
	var warning = "warning !";
} else {
	deptCodeError = "कृपया विभाग कोड प्रविष्ट करा  !!!";
	deptShortNameError = "कृपया विभाग लहान नाव प्रविष्ट करा !!!";
	deptNameEnglishError = "कृपया विभागाचे नाव इंग्रजीमध्ये प्रविष्ट करा !!!";
	deptNameMarathiError = "कृपया मराठी मध्ये विभाग नाव प्रविष्ट करा !!!";
	warning = "चेतावणी !";
}
$('#btnSave').click(function(e) {
	var deptCode = $('#departmentCode').val();
	var deptShortName = $('#departmentShortName').val();
	var deptNameEnglish = $('#departmentNameEn').val();
	var deptNameMarathi = $('#departmentNameMr').val();

	if (deptCode == "") {
		Swal.fire({
			title : warning,
			text : deptCodeError,
			icon : "warning",
			timer : 4000
		});

	} else if (deptShortName == "") {
		Swal.fire({
			title : warning,
			text : deptShortNameError,
			icon : "warning",
			timer : 4000
		});
		e.preventDefault();

	} else if (deptNameEnglish == "") {
		Swal.fire({
			title : warning,
			text : deptNameEnglishError,
			icon : "warning",
			timer : 4000
		});
		e.preventDefault();
	} else if (deptNameMarathi == "") {
		Swal.fire({
			title : warning,
			text : deptNameMarathiError,
			icon : "warning",
			timer : 4000
		});
		e.preventDefault();
	} else {
		$(".LockOn").css("display", " block");

	}

});
