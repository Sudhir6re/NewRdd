
/*$("#toDate")
				.change(
						function() {
							
							var Fromdate = $("#Fromdate").val();
							var toDate = $("#toDate").val();
							
							$.ajax({
								type : "POST",
								url : "../master/fetchEmpInfoBySevaarthId/"
										+Fromdate+"/"+toDate,
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
									//alert("data"+data.length);
									var len = data.length;
									if (len != 0) {
										
										
									for (var i = 0; i < data.length; i++) {
										//$("#searchDiv").append(data[i].employeeName+"-"+data[i].sevaarthId);
										//$("#searchDiv").css("border:1px solid #A5ACB2;");
//										$("#searchDiv")
//												.append(
//														"<li class='empdata' empid='"+data[i].employeeid+"' empname='"+data[i].employeeName+"' empsevaathid='"+data[i].sevaarthId+"' gpfNo='"+data[i].gpfNo+"'  " +
//																" empdesgn='"+data[i].designName+"' orgInstName='"+data[i].orgInstName+"' MemberShipDate='"+data[i].MemberShipDate+"'> Neme and Sevaarth Id :"
//																+ data[i].employeeName
//																+ "-"
//																+ data[i].sevaarthId
//																+ "</li>");
										$("#searchDiv")
												.css(
														"border:1px solid #A5ACB2;");
										$("#employeeFullNameEn").val(data[i].employeeFullNameEn);
										$("#dcpsid").val(data[i].dcpsid);
										$("#sevaarthId").val(data[i].sevaarthId);
										$("#pranNo").val(data[i].pranNo);
										
										
										$("#dob").val(dateToYMD(data[i].dob));
										$("#doj").val(dateToYMD(data[i].doj)); 
										$("#accountmaintainby").val(data[i].accountmaintainby);
										
										
									}
										}
									
									else
										{
										swal("Record not found !!!");
										$("#sevaarth").val("");
										}
									
								}
							});
							
						});
*/



$("#sevaarth")
				.blur(
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
											url : "../master/fetchEmpInfoBySevaarthId/"
													+sevaarthId,
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
												//alert("data"+data.length);
												var len = data.length;
												if (len != 0) {
												for (var i = 0; i < data.length; i++) {
													//$("#searchDiv").append(data[i].employeeName+"-"+data[i].sevaarthId);
													//$("#searchDiv").css("border:1px solid #A5ACB2;");
//													$("#searchDiv")
//															.append(
//																	"<li class='empdata' empid='"+data[i].employeeid+"' empname='"+data[i].employeeName+"' empsevaathid='"+data[i].sevaarthId+"' gpfNo='"+data[i].gpfNo+"'  " +
//																			" empdesgn='"+data[i].designName+"' orgInstName='"+data[i].orgInstName+"' MemberShipDate='"+data[i].MemberShipDate+"'> Neme and Sevaarth Id :"
//																			+ data[i].employeeName
//																			+ "-"
//																			+ data[i].sevaarthId
//																			+ "</li>");
													/*$("#searchDiv")
															.css(
																	"border:1px solid #A5ACB2;");*/
													$("#employeeFullNameEn").val(data[i].employeeFullNameEn);
													$("#dcpsid").val(data[i].dcpsid);
													$("#sevaarthId").val(data[i].sevaarthId);
													$("#pranNo").val(data[i].pranNo);
													
													
													$("#dob").val(dateToYMD(data[i].dob));
													$("#doj").val(dateToYMD(data[i].doj)); 
													$("#accountmaintainby").val(data[i].accountmaintainby);
													
													
												}
													}
												
												else
													{
													swal("Record not found !!!");
													$("#sevaarth").val("");
													}
												
											}
										});
							}
						});