$(document)
		.ready(
				function() {
					var departmentId = $("#departmentCode").val();

					$("#ddoCodeLevel").prop("disabled", true);

					var districtCode = $('#districtId').val();
					var talukaId1 = $('#talukaId1').val();
					var ddoName = $('#ddoName').val();
					var grOrderId = $('#grOrderId').val();

					var subDepartmentId = $('#subDepartmentCode').val();

					if (districtCode != '') {
						$
								.ajax({
									type : "GET",
									url : "../allDistrictWiseDDO/"
											+ districtCode,
									async : true,
									contentType : 'application/json',
									error : function(data) {
										console.log(data);
									},
									success : function(data) {
										console.log(data);

										var len = data.length;
										if (len != 0) {

											$('#ddoCode').empty();

											$('#ddoCodeLevel').empty();
											$('#ddoCode')
													.append(
															"<option value='0'>Please Select</option>");

											var temp = data;
											var chk;
											$
													.each(
															temp,
															function(index,
																	value) {

																ddoName

																if (ddoName == value[0]) {
																	chk = "selected=selected";
																} else {
																	chk = "";
																}

																$('#ddoCode')
																		.append(
																				"<option   "
																						+ chk
																						+ " data-level="
																						+ value[9]
																						+ "       value="
																						+ value[0]
																						+ " data_id ='"
																						+ value[3]
																						+ "' >"
																						+ value[1]
																						+ "_"
																						+ value[6]
																						+ "_"
																						+ value[5]
																						+ "</option>");

																$(
																		"#ddoCodeLevel")
																		.val(
																				value[9]);

																if (talukaId1 == value[0]) {
																	chk = "selected=selected";
																} else {
																	chk = "";
																}

																$('#talukaId')
																		.append(
																				"<option "
																						+ chk
																						+ " value="
																						+ value[0]
																						+ " >"
																						+ value[8]
																						+ "</option>");

															});
										}
									}
								});
					}
					
					if (subDepartmentId != '') {
						$.ajax({
									type : "GET",
									url : "../allSubDeptDDO/" + subDepartmentId,
									async : true,
									contentType : 'application/json',
									error : function(data) {
										console.log(data);
									},
									
									success : function(data) {
										console.log("dat is" + data);
										// alert(data);
										var len = data.length;
										
										if (len != 0) {
											
											$('#districtId').empty();

											var chk;
											
											 * $('#ddoCode') .empty();
											 * $('#ddoCode') .append( "<option
											 * value='0'>Please Select</option>");
											 
											$('#districtId')
													.append(
															"<option value='0'>Please Select</option>");
											var temp = data;
											
											$.each(
													temp,
													function(index,
															value) {
														if (districtCode == value[1]) {
															chk = "selected=selected";
														} else {
															chk = "";
														}

														$('#districtId')
																.append(
																		"<option "
																				+ chk
																				+ " value="
																				+ value[1]
																				+ " >"
																				+ value[2]
																				+ "</option>");

														$('#grOrderId')
																.append(
																		"<option   value="
																				+ value[0]
																				+ " data-id="
																				+ value[2]
																				+ ">"
																				+ value[1]
																				+ "</option>");

													});
											
											$.ajax({
												type : "GET",
												url : "../allGrOrderbyDDO1/"
														+ subDepartmentId,
												async : true,
												contentType : 'application/json',
												error : function(data) {
													console.log(data);
												},
													 
													 success : function(data) {
															var len = data.length;
															if (len != 0) {

																$('#grOrderId')
																		.empty();
																$('#grOrderId')
																		.append(
																				"<option value='0'>Please Select</option>");
																var temp = data;
																var chk;
																$
																		.each(
																				temp,
																				function(
																						index,
																						value) {

																					// alert("okay
																					// n"+value[0]);
																					if (grOrderId == value[0]) {
																						chk = "selected=selected";

																					} else {
																						chk = "";
																					}

																					$(
																							'#grOrderId')
																							.append(
																									"<option "
																											+ chk
																											+ " value="
																											+ value[0]
																											+ " data-id="
																											+ value[2]
																											+ ">"
																											+ value[1]
																											+ "</option>");
																				});
															}
														}
												
												
											});
												
											
										}
										
									}
						});
					}
					
					
					$("#departmentCode")
					.change(
											function() {
												
												var departmentId = $("#departmentCode").val();
												// alert("DDO
												// CODE
												// is
												// "+departmentId);
												
												if (departmentCode != '') {
													$
															.ajax({
																type : "GET",
																url : "mstSubDept/"
																		+ departmentId,
																async : true,
																contentType : 'application/json',
																error : function(data) {
																	// console.log(data);
																},
																
																success : function(data) {
																	// console.log(data);
																	// alert(data);
																	var len = data.length;
																	
																	if (len != 0) {
																		// console.log(data);
																		$(
																				'#subDepartmentCode')
																				.empty();
																		$(
																				'#subDepartmentCode')
																				.append(
																						"<option value='1000'>---------- Select All ----------</option>");
																		var temp = data;
																	
																		$
																		.each(
																				temp,
																				function(
																						index,
																						value) {
																					
																					$(
																							'#subDepartmentCode')
																							.append(
																									"<option value="
																											+ value[0]
																											+ ">"
																											+ value[2]
																											+ "</option>");
																				});
																		
																}
																	else {
																		$(
																				'#subDepartmentCode')
																				.empty();
																		$(
																				'#subdepartmentCode')
																				.append(
																						"<option value='0'>Please Select</option>");
																		swal("Record not found !!!");
																	}
																
																
															}):
												}
												
												
											});
					
					
					
					$("#subDepartmentCode")
					.change(
							function() {
								var subDepartmentId = $("#subDepartmentCode").val();
								// alert("DDO CODE is "+departmentId);
								
								if (subDepartmentId != '') {
								
									
									$
									.ajax({
										type : "GET",
										url : "../allSubDeptDDO/"
												+ subDepartmentId,
										async : true,
										contentType : 'application/json',
										error : function(data) {
											// console.log(data);
										},
										
										success : function(data) {
											// console.log(data);
											// alert(data);
											var len = data.length;
											
											if (len != 0) {
												$('#districtId')
												.empty();
												$('#districtId')
												.append(
														"<option value='0'>Please Select</option>");
										var temp = data;
										$
										.each(
												temp,
												function(
														index,
														value) {
													$(
															'#districtId')
															.append(
																	"<option value="
																			+ value[1]
																			+ " >"
																			+ value[2]
																			+ "</option>");
													$(
															'#grOrderId')
															.append(
																	"<option value="
																			+ value[0]
																			+ " data-id="
																			+ value[2]
																			+ ">"
																			+ value[1]
																			+ "</option>");

												});
										
										$
										.ajax({
											type : "GET",
											url : "../allGrOrderbyDDO1/"
													+ subDepartmentId,
											async : true,
											contentType : 'application/json',
											error : function(
													data) {
												console
														.log(data);
											},
											
											success : function(
													data) {
												var len = data.length;
												if (len != 0) {

													$(
															'#grOrderId')
															.empty();
													$(
															'#grOrderId')
															.append(
																	"<option value='0'>Please Select</option>");
													var temp = data;
													$
													.each(
															temp,
															function(
																	index,
																	value) {

																$(
																		'#grOrderId')
																		.append(
																				"<option value="
																						+ value[0]
																						+ " data-id="
																						+ value[2]
																						+ ">"
																						+ value[1]
																						+ "</option>");
															});
											}
												
												else {
													$(
															'#grOrderId')
															.empty();
													$(
															'#grOrderId')
															.append(
																	"<option value='0'>Please Select</option>");
													swal("Record not found !!!");
												}
												
												
										});
										
										
										
										
											}
											
											
											
											
										}
											
											else {
												
												 
												$('#grOrderId')
														.empty();

												$('#districtId')
														.empty();

												$('#districtId')
														.append(
																"<option value='0'>Please Select</option>");

												swal("Record not found !!!");
											}
											
											
										
									
									});
									
									
									
								}
								
								
								
							});
					
				});
						
						$("#ddoCode").change(
								function() {
									var subDepartmentId = $("#subDepartmentCode")
											.val();
									var ddoCode = $("#ddoId").val();

									var departmentId = $("#departmentCode").val();

									var ddoCode = $("#ddoCode option:selected")
											.text();
									$("#ddoForDB").val(ddoCode);

									// alert(" ddoCode "+ddoCode);
									if (departmentCode != '') {
										$.ajax({
											type : "GET",
											url : "../allGrOrderbyDDO/" + ddoCode
													+ "/" + subDepartmentId + "/"
													+ departmentId,
											async : true,
											contentType : 'application/json',
											error : function(data) {
											},
											success : function(data) {
												console.log(data);
											}
										});
									}
								});
						
						$("#grOrderId").change(function() {

							var date = $('option:selected', this).attr('data-id');
							$('#grDate').val(date)
						});
						
						$("#ddoCode").change(
								function() {

									var officeName = $('option:selected', this)
											.attr('data_id');

									var level = $('option:selected', this).attr(
											'data-level');

									// alert(officeName);
									$('#officeName').val(officeName)

									$("#ddoCodeLevel").val(level);
									$("#ddoCodeLevel").prop("disabled", true);

								});
						
						$
						.ajax({
							url : "../mstPostList",
							success : function(result) {
								console.log(result);
								// $('#oldData').empty();
								var len = result.length;
								var i = 0;
								if (len != 0) {
									var temp = result;
									$
											.each(
													temp,
													function(index, value) {

														$('#oldData')
																.append(
																		"<tbody class='panel' ><tr class='btnSelect'  data-parent='#oldData' data-toggle='collapse' data-target=#demo"
																				+ i
																				+ " ><td><center>"
																				+ value[8]
																				+ "</center></td><td><center>"
																				+ value[9]
																				+ "</center></td><td><center>"
																				+ value[0]
																				+ "</center></td><td><center>"
																				+ value[1]
																				+ "</center></td><td  class='example'><center>"
																				+ value[2]
																				+ "</td><td><center>"
																				+ value[4]
																				+ "</center></td><td><center></center></td><td><center>"
																				+ value[5]
																				+ "</center><td><center></center></td><td><center>"
																				+ value[3]
																				+ "</center></td></td><td><center>"
																				+ value[7]
																				+ "</center></td><td ><center></center></td><td>"
																				+ "<div class='form-group'><div class='col-sm-offset-1 col-sm-1'>  <a  href='/master/editMstPost' ><span class='glyphicon glyphicon-edit' id='edit' style='position: relative; right: 5px;'></span></a></div>       <a href='/master/deleteMstPost'> <span class='glyphicon glyphicon-trash' id='delete' style='width: 0px;'></span> </a></div></td> <td style='display:none;'>"
																				+ value[6]
																				+ "</td>   <td class="
																				+ i
																				+ " styl"
																				+ "e='display:none;'>"
																				+ i
																				+ "</td></tr>    <tr id=demo"
																				+ i
																				+ " class='collapse'><td class="
																				+ i
																				+ "  colspan='11'></td></tr>  </tbody> ");

														i++;

													});
								}

							}
						});

						$("#oldData")
						.on(
								'click',
								'.btnSelect',
								function() {
									// get the current row
									var currentRow = $(this).closest("tr");

									var classid = currentRow.find(
											"td:eq(14)").text();

									var postId = currentRow.find(
											"td:eq(13)").text(); // get
																	// current
																	// row
																	// 2nd
																	// table
																	// cell
																	// TD
																	// value

									$.ajax({
												url : "../mstPostDetailsList/"
														+ postId,
												error : function(data) {
													// console.log("error");
													// console.log(data);

												},
												success : function(result) {
													// console.log("latest
													// controller data");
													// console.log(result);
													// $('#oldData').empty();
													var len = result.length;
													var i = 0;

													if (len != 0) {
														var temp = result;
														$
																.each(
																		temp,
																		function(
																				index,
																				value) {
																			i++;

																			$(
																					'.'
																							+ classid)
																					.append(
																							'<ol >'
																									+ i
																									+ '. '
																									+ value[1]
																									+ '</ol>');

																		});
													}
												}
											});
								});
						
						
						
											});

$(document).ready(function($) {
	var varMessage = $("#message").val();
	if (varMessage != "" && varMessage != undefined) {
		swal('' + varMessage, {
			icon : "success",
		});
	}
	$(".mstposttbl").DataTable();
});
