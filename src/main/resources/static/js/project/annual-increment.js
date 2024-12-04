	var contextPath="";
$(document)
		.ready(
				function() {
					
					 contextPath = $("#appRootPath").val();
					var empIds = [];
					var sevaarthIds = [];
					var basicPays = [];

					var basicPaysIncrementes = [];
					var certificateNumber;

					var remarks = [];
					
					var withEffectiveDate;
					var nextIncrementDate;

					$("#vo").hide();

					$("#btnGo")  
							.click(
									function(e) {
									 certificateNumber = $(
												"#certificateNumber").val();
										var isAlreadyPresent = "0";// checkOrderNoAlreadyExists(certificateNumber);
										if (isAlreadyPresent == '0') {

											e.preventDefault();
											var orderDate = $("#orderDate")
													.val();
											var schemeBillGroupId = $("#billNo")
													.val();
											var payCommision = $(
													"#payCommision").val();
											var month = $("#month").val();
											if (certificateNumber == ""
													|| certificateNumber == "Undefined") {
												swal("Please enter certificate number");
											} else if (orderDate == ""
													|| orderDate == "Undefined") {
												swal("Please select order date");
											} else if (schemeBillGroupId == ""
													|| schemeBillGroupId == "0") {
												swal("Please select bill group name");
											} else if (payCommision == ""
													|| payCommision == "0") {
												swal("Please select pay commission");
											} else if (month == ""
													|| month == "0") {
												swal("Please select month");
											} else {
												$
														.ajax({
															type : "GET",
															url : context+"/ddoast/findAllEmpForDue/"
																	+ schemeBillGroupId
																	+ "/"
																	+ payCommision,
															async : true,
															contentType : 'application/json',
															error : function(
																	data) {
																console
																		.log(data);
															},
															success : function(
																	data) {

																var dataTable = $(
																		"#pendingIncrement")
																		.dataTable();
																dataTable
																		.fnClearTable();
																$(
																		'#pendingIncrement')
																		.show();
																$(
																		'#pendingIncrement_wrapper')
																		.show();
																$(
																		'#pendingIncrement tbody')
																		.html(
																				'');

																var len = data.length;
																$
																		.each(
																				data,
																				function(
																						index,
																						value) {
																					console
																							.log(value[2]);
																					// var
																					// chk
																					// =
																					// "<input
																					// type='checkbox'
																					// name='empId'
																					// class='empId'
																					// data-empId='"+
																					// value[0]"'>";
																					var chk = "<input type='checkbox'  name='empId' class='empId' data-empId="
																							+ value[0]
																							+ "  data-empName="
																							+ value[2]
																							+ "  data-sevaarthId="
																							+ value[1]
																							+ "  data-level="
																							+ value[4]
																							+ "  />";
																					$(
																							"#pendingIncrement")
																							.dataTable()
																							.fnAddData(
																									[
																											chk,
																											value[0],
																											value[2],
																											value[3],
																											orderDate ]);
																				});
															}
														});
											}
											// alert("hello");
										} else {
											swal("Order No is Already Exists!!");
										}
										$(".addrowmain").hide();
										$("#addnewOrder").click(function() {
											$(".addrowmain").show();
										});
									});

					$("#add")
							.click(
									function(e) {
										e.preventDefault();
										var dataTable = $("#releaseIncrement")
												.dataTable();
										// dataTable.fnClearTable();
										$(
												"#pendingIncrement input[type=checkbox]:checked")
												.each(
														function() {

															var basicPay = $(
																	this)
																	.closest(
																			"tr")
																	.find(
																			'td:eq(3)')
																	.text();
															
															
															var empId = $(this)
																	.attr(
																			'data-empId');
															var level = $(this)
																	.attr(
																			'data-level');
															var sevaarthId = $(
																	this)
																	.attr(
																			'data-sevaarthId');
															var empName = $(
																	this)
																	.attr(
																			'data-empName');

															basicPay1 = getUpdatedBasicPay(
																	empId,
																	basicPay,
																	level);
															 certificateNumber = $(
																	"#certificateNumber")
																	.val();
															
															
															var date=new Date();
															var year= date.getFullYear();
															var month=$("#month").val();
															
															if(month=='2')
																month='7';
															
															
															if(parseInt(month)<10)
																month='0'+month;
															var orderDate= year+"-"+month+"-01";
															
															
															var nxtIncDate= new Date(orderDate);
															nxtIncDate.setFullYear(nxtIncDate.getFullYear()+1);
															
															
															
															var parsenxtIncDate=nxtIncDate.getFullYear()+'-0'+(nxtIncDate.getMonth()+1)+'-0'+nxtIncDate.getDate();
															

															
															/*var orderDate = $(
																	"#orderDate")
																	.val();*/
															
															var remark = "<input type='text' placeholder='Remark' class='form-control' >"; //
															var chk = "<input type='checkbox'  name='empId' class='empId' data-empId="
																	+ empId
																	+ "  data-empName="
																	+ empName
																	+ "   data-level="
																	+ level
																	+ " data-basicPay="
																	+ basicPay
																	+ "  data-sevaarthId="
																	+ sevaarthId
																	+ " />";
															// var chk=null;
															dataTable
																	.fnAddData([
																			chk,
																			empName,
																			basicPay1,
																			orderDate,
																			parsenxtIncDate,
																			remark ]);

															$(this).closest(
																	'tr')
																	.remove();
															$("#tblDataTable")
																	.show();
														});
									});

					$("#remove")
							.click(
									function(e) {
										e.preventDefault();
										$(
												"#releaseIncrement input[type=checkbox]:checked")
												.each(
														function() {

															var basicPay = $(
																	this)
																	.closest(
																			"tr")
																	.find(
																			'td:eq(3)')
																	.text();
															var empId = $(this)
																	.attr(
																			'data-empId');
															var level = $(this)
																	.attr(
																			'data-level');
															var sevaarthId = $(
																	this)
																	.attr(
																			'data-sevaarthId');
															var empName = $(
																	this)
																	.attr(
																			'data-empName');
															var orderDate = $(
																	"#orderDate")
																	.val();
															var chk = "<input type='checkbox'  name='empId' class='empId' data-empId="
																	+ empId
																	+ "  data-empName="
																	+ empName
																	+ "   data-level="
																	+ level
																	+ " data-basicPay="
																	+ basicPay1
																	+ "  data-sevaarthId="
																	+ sevaarthId
																	+ " />";
															var chk = null;

															$(
																	"#pendingIncrement")
																	.dataTable()
																	.fnAddData(
																			[
																					chk,
																					sevaarthId,
																					empName,
																					basicPay,
																					orderDate ]);

														});
									});

					function getUpdatedBasicPay(empId, basicPay, level) {
						var basic = "";
						var context = $("#appRootPath").val();
						$.ajax({
							type : "GET",
							url : context+"/ddoast/findNextMatrix/" + empId
									+ "/" + basicPay + "/" + level,
							async : false,
							contentType : 'application/json',
							error : function(data) {
								console.log(data);
							},
							success : function(data) {
								// console.log(data);
								console.log("return val" + data[0]);
								basic = data[0][0];
							}
						});
						return basic;
					}

					$("#btnSave")
							.click(
									function(e) {
										e.preventDefault();
										 certificateNumber = $("#certificateNumber").val();
										 payCommision = $("#payCommision").val();
										//var orderDate = $("#orderDate").val();
										var month1 = $("#month").val();
										$(
												"#releaseIncrement input[type=checkbox]")
												.each(
														function() {
															var empId = $(this)
																	.attr(
																			'data-empId');
															var sevaarthId = $(
																	this)
																	.attr(
																			'data-sevaarthId');
															var basicPay = $(
																	this)
																	.attr(
																			'data-basicPay');
															
															var basicPayIncremented = $(
																	this)
																	.closest(
																			"tr")
																	.find(
																			'td:eq(2)')
																	.text();
															withEffectiveDate = $(
																	this)
																	.closest(
																			"tr")
																	.find(
																			'td:eq(3)')
																	.text();
															nextIncrementDate = $(
																	this)
																	.closest(
																			"tr")
																	.find(
																			'td:eq(4)')
																	.text();
															var remark = $(this)
																	.closest(
																			"tr")
																	.find(
																			"td:eq(5) input[type='text']")
																	.val();
															
															

															/*
															 * console.log(empId);
															 * console.log(sevaarthId);
															 * console.log(basicPay);
															 * console.log(basicPayIncremented);
															 * console.log(withEffectiveDate);
															 * console.log(nextIncrementDate);
															 * console.log(remark);
															 * console.log(certificateNumber);
															 */ console.log(orderDate);
															 

															empIds.push(empId);
															sevaarthIds
																	.push(sevaarthId);
															basicPays
																	.push(basicPay);
															basicPaysIncrementes
																	.push(basicPayIncremented);
															remarks
																	.push(remark);
															/*nextIncrementDate
															.push(nextIncrementDate);*/
														});

										$
												.ajax({
													type : "GET",
													url : context+"/ddoast/saveAnnualIncrementData/"
															+ empIds
															+ "/"
															+ sevaarthIds
															+ "/"
															+ basicPays
															+ "/"
															+ basicPaysIncrementes
															+ "/"
															+ certificateNumber
															+ "/"
															+ withEffectiveDate
															+ "/"
															+ remarks
															+ "/" + month1
															+"/"
															+ nextIncrementDate
															+"/"
															+ payCommision,
													async : true,
													contentType : 'application/json',
													error : function(data) {
														console.log(data);
													},
													success : function(data) {

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

														$("#tblDataTable")
																.show();
													}
												});

									});

				});
function checkOrderNoAlreadyExists(certificateNumber) {

	///alert("certificateNumber" + certificateNumber);
	flag = 0;
	$.ajax({
		type : "GET",
		url : context+"/ddoast/checkOrderNoAlreadyExists/"
				+ certificateNumber,
		async : false,
		dataType : 'json',
		// contentType:'application/json',
		error : function(data) {
			console.log(data);
			// alert(data);
		},
		success : function(data) {
			if (parseInt(data) > 0) {
				flag = 1;
			} else {
				flag = 0;
			}
		}
	});
	return flag;
}