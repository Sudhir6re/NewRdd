jQuery(document)
		.ready(
				function($) {
					// $('#tblShowPayBill').hide();
					
					
					
					if($('#tblShowPayBill').length){
						$('#tblShowPayBill').DataTable();
					}
					
					var date = new Date();
					var currentMonth = date.getMonth() + 1;
					var currentYear = date.getFullYear();

					var currentYearval = currentYear.toString().substr(-2);
					 console.log("currentyear"+currentYearval);
					//var selectedMonth = $('#gotMonthName').val();         --> commented because id is wrong 
					var selectedMonth = $('#monthName').val();
					if (selectedMonth == "" && currentMonth != "") {
						$('#monthName').val(currentMonth);
					} else {
						$('#monthName').val(selectedMonth);
					}
					$('#monthName option:eq('+ currentMonth +')').attr('selected', 'selected');       /*selected the current Month*/
					$('#yearName').val(parseInt(currentYearval) + 1);

					// $("#yearName").find("option[text=" + currentYear +
					// "]").attr("selected", true);

					/*$("#yearName option[text='" + currentYearval + "']").attr(
							"selected", "selected");*/
					$('#yearName option[value="'+ (parseInt(currentYearval) + 1) +'"]' ).attr('selected', 'selected');                        /*selected the current Year*/
					// $("#yearName").text(currentYear);

					// console.log("currentMonth"+currentMonth);
					console.log("currentMonth" + currentMonth);
					// console.log($('#lstGenerateBillDetails').val());
					 $("#btnForwardChangeStatment").attr("disabled", true);// 1
                     $("#btnForwardBillToBeams").attr("disabled", true);   //5,6,7
                     $("#btnForwardBill").attr("disabled", true);  //generate bill   3
                     $("#btnVoucherEntry").attr("disabled", true);  //generate bill   3

								$('body').on('change','.generatedChkId',function(){
								if (this.checked) {
									var id = $(this).val();

									$('#radioval').val(id);
									$('#radioid').val(id);

									 console.log($(this).attr("data"));
                                     var status=$(this).attr("data");
                                     
                                     if(status==1){
                                    	   $("#btnForwardChangeStatment").attr("disabled", false); 
                                    	   $("#btnForwardBillToBeams").attr("disabled", true);   //5,6,7
                                           $("#btnForwardBill").attr("disabled", true);  //generate bill   3
                                           $("#btnVoucherEntry").attr("disabled", true);  //generate bill   3
                                           $("#btnForwardBillToDDO").attr("disabled", true);  //generate bill   3
                                    	    $("#btnForwardBillToLevel2").attr("disabled", true);
                                     }
                                     else if(status==2){
                                  	   $("#btnForwardChangeStatment").attr("disabled", true);
                                  	   $("#btnForwardBillToBeams").attr("disabled", true);   //5,6,7
                                         $("#btnForwardBill").attr("disabled", true);  //generate bill   3
                                         $("#btnDeleteBill").attr("disabled", true);  //
                                         $("#btnVoucherEntry").attr("disabled", true);  //
                                         $("#btnForwardBillToDDO").attr("disabled", true);  //
                                         
                                         $("#btnForwardBillToLevel2").attr("disabled", true);
                                         
                                  	 //  $("#btnDeleteBill").attr("disabled", false);
                                   }
                                     else if(status==3){
                                    	  $("#btnForwardBill").attr("disabled", false);  //generate bill   3
                                          $("#btnForwardChangeStatment").attr("disabled", true);// 1
                                          $("#btnForwardBillToBeams").attr("disabled", true);   //5,6,7
                                          $("#btnDeleteBill").attr("disabled", true); 
                                          $("#btnVoucherEntry").attr("disabled", true); 
                                          $("#btnForwardBillToDDO").attr("disabled", true); 
                                          $("#btnForwardBillToLevel2").attr("disabled", true);
                                          
                                     }
                                     else if(status==5){
                                    	 $("#btnForwardBill").attr("disabled", true);  //generate bill   3
                                    	 $("#btnForwardChangeStatment").attr("disabled", true);// 1
                                    	 $("#btnForwardBillToBeams").attr("disabled", false);   //5,6,7
                                    	// $("#btnVoucherEntry").attr("disabled", false);   //5,6,7
                                    	 $("#btnDeleteBill").attr("disabled", false); 
                                    	 
                                     }
                                     else if(status==6 || status==7)
                                    	 {
                                    	  $("#btnForwardBillToBeams").attr("disabled", true);   //5,6,7
                                    	  $("#btnForwardChangeStatment").attr("disabled", true);// 1
                                          $("#btnForwardBill").attr("disabled", true);  //generate bill   3
                                          $("#btnVoucherEntry").attr("disabled", true);  //generate bill   3
                                          $("#btnForwardBillToDDO").attr("disabled", true);  //generate bill   3
                                          $("#btnForwardBillToLevel2").attr("disabled", true);
                                    	 }
                                     else if(status==14)
                                     {
                                    	 $("#btnForwardBillToBeams").attr("disabled", true);   //5,6,7
                                    	 $("#btnForwardChangeStatment").attr("disabled", true);// 1
                                    	 $("#btnForwardBill").attr("disabled", true);  //generate bill   3
                                    	  $("#btnDeleteBill").attr("disabled", true); 
                                    	 // $("#btnVoucherEntry").attr("disabled", true); 
                                    	  $("#btnForwardBillToDDO").attr("disabled", true); 
                                    	    $("#btnForwardBillToLevel2").attr("disabled", true);
                                    		 $("#btnVoucherEntry").attr("disabled", true); 
                                    	    
                                     }
                                     else if(status==9)
                                     {
                                    	 $("#btnForwardBillToBeams").attr("disabled", true);   //5,6,7
                                    	 $("#btnForwardChangeStatment").attr("disabled", true);// 1
                                    	 $("#btnForwardBill").attr("disabled", true);  //generate bill   3
                                    	 $("#btnDeleteBill").attr("disabled", true); 
                                    	 $("#btnVoucherEntry").attr("disabled", true); 
                                    	 $("#btnForwardBillToDDO").attr("disabled", true); 
                                    	 $("#btnForwardBillToLevel2").attr("disabled", true); 
                                    	// $("#btnVoucherEntry").attr("disabled", false); 
                                     }
                                     else if(status==11)
                                     {
                                    	 $("#btnForwardBillToBeams").attr("disabled", true);   //5,6,7
                                    	 $("#btnForwardChangeStatment").attr("disabled", true);// 1
                                    	 $("#btnForwardBill").attr("disabled", true);  //generate bill   3
                                    	 $("#btnDeleteBill").attr("disabled", true); 
                                    	 $("#btnVoucherEntry").attr("disabled", false); 
                                    	 $("#btnForwardBillToDDO").attr("disabled", false); 
                                    	    $("#btnForwardBillToLevel2").attr("disabled", true);
                                    	    $("#btnVoucherEntry").attr("disabled", false); 
                                     }
                                     else{
                                    	  $("#btnForwardChangeStatment").attr("disabled", true);// 1
                                          $("#btnForwardBillToBeams").attr("disabled", true);   //5,6,7
                                          $("#btnForwardBill").attr("disabled", true);  //generate bill   3
                                          $("#btnVoucherEntry").attr("disabled", true);  //generate bill   3
                                          $("#btnForwardBillToDDO").attr("disabled", true);  //generate bill   3
                                          $("#btnForwardBillToLevel2").attr("disabled", true);
                                         // $("#btnDeleteBill").attr("disabled", true);  //all
                                     }
                                     
									/*if ($(this).attr("data") == '2'
											|| $(this).attr("data") == '3'
											|| $(this).attr("data") == '5') {
										$("#btnForwardChangeStatment").attr(
												"disabled", true);
										// $("#btnForwardBill").attr("disabled",
										// true);
										if ($(this).attr("data") == '5') {
											$("#btnForwardBill").attr(
													"disabled", true);
										} else if ($(this).attr("data") == '3') {
											$("#btnForwardBillToBeams").attr(
													"disabled", false);
										} else {
											$("#btnForwardBill").attr(
													"disabled", false);
											$("#btnForwardBillToLevel2").attr(
													"disabled", true);
										}
									} else {
										if ($(this).attr("data") == '2'){
										$("#btnForwardChangeStatment").attr(
												"disabled", false);
										$("#btnForwardBillToLevel2").attr(
												"disabled", true);
									}
									}
									*/
									
								}
							});

					$('#btnForwardChangeStatment')
							.click(
									function() {
										var paybillGenerationTrnId = $(
												'#radioval').val();
										var userId = $(
										'#userId').val();

										// alert(paybillGenerationTrnId);
										if (paybillGenerationTrnId != '') {
											$
													.ajax({
														type : "GET",
														url : "../ddoast/forwardChangeStatement/"
																+ paybillGenerationTrnId +"/"+userId,
														async : true,
														contentType : 'application/json',
														error : function(data) {
															console.log(data);
														},
														success : function(data) {
															console.log(data);
															// alert(data);

															if ($("#is_changed")
																	.val() == 1) {

																swal(
																		"Change Statement Forward Successfully",
																		{
																			icon : "success",
																		});
																setTimeout(
																		function() {
																			location
																					.reload(true);
																		}, 3000);

															} else {
																swal(
																		"Change Statement Forward Successfully",
																		{
																			icon : "success",
																		});
																setTimeout(
																		function() {
																			location
																					.reload(true);
																		}, 3000);
															}

														}
													});
										}
									});

					$('#btnApprChangState')
							.click(
									function() {
										var paybillGenerationTrnId = $(
												'#radioid').val();
										var userId = $(
										'#userId').val();
										
										var billStatus=$('input[name="paybillId"]:checked').attr('data');
										
										if(billStatus==4){
											swal("Rejected Paybill can not Approved");
										}
										else if(billStatus==3){
											swal("Paybill is already Approved ");
										}
										else if (paybillGenerationTrnId != '') {
											$
													.ajax({
														type : "GET",
														url : "../ddo/approveChangeStatement/"
																+ paybillGenerationTrnId +"/"+userId,
														async : true,
														contentType : 'application/json',
														error : function(data) {
															console.log(data);
														},
														success : function(data) {
															console.log(data);
															// alert(data);

															if ($("#is_changed")
																	.val() == 1) {

																swal(
																		"Change Statement Approved Successfully",
																		{
																			icon : "success",
																		});
																setTimeout(
																		function() {
																			location
																					.reload(true);
																		}, 3000);

															} else {
																swal(
																		"Change Statement Approved Successfully",
																		{
																			icon : "success",
																		});
																setTimeout(
																		function() {
																			location
																					.reload(true);
																		}, 3000);
															}

														}
													});
										}
									});
					
					
					
					
					//for reject change statement
					$('#btnRejectChangState')
					.click(
							function() {
								var paybillGenerationTrnId = $(
										'#radioid').val();
								var userId = $(
								'#userId').val();
								
								var billStatus=$('input[name="paybillId"]:checked').attr('data');
								
								if(billStatus==4){
									swal("Paybill is already Rejected");
								}
								else if(billStatus==3){
									swal("Approved Paybill can not Reject");
								}
								else if (paybillGenerationTrnId != '') {
									$
											.ajax({
												type : "GET",
												url : "../ddo/rejectChangeStatement/"
														+ paybillGenerationTrnId+"/"+userId,
												async : true,
												contentType : 'application/json',
												error : function(data) {
													console.log(data);
												},
												success : function(data) {
													console.log(data);
													// alert(data);

													if ($("#is_changed")
															.val() == 1) {

														swal(
																"Change Statement Rejected Successfully",
																{
																	icon : "success",
																});
														setTimeout(
																function() {
																	location
																			.reload(true);
																}, 3000);

													} else {
														swal(
																"Change Statement Rejected Successfully",
																{
																	icon : "success",
																});
														setTimeout(
																function() {
																	location
																			.reload(true);
																}, 3000);
													}

												}
											});
								}
								
								
								
							});
					
					
					
					//end reject change statement

					
					
					$('#btnDeleteBill')
							.click(
									function() {
										var paybillGenerationTrnId = $(
												'#radioval').val();
										var userId = $(
										'#userId').val();
										swal({
											  title: "Are you sure?",
											  text: "Delete Paybill !",
											  icon: "warning",
											  buttons: true,
											  dangerMode: true,
											}).then((willDelete) => {
							    if (willDelete) {   
										// alert(paybillGenerationTrnId);
										if (paybillGenerationTrnId != '') {
											$
													.ajax({
														type : "GET",
														url : "../ddoast/deleteBill/"
																+ paybillGenerationTrnId+"/"+userId,
														async : true,
														contentType : 'application/json',
														error : function(data) {
															console.log(data);
														},
														success : function(data) {
															console.log(data);
															// alert(data);

															if ($("#is_changed")
																	.val() == 1) {

																swal(
																		"Paybill has been deleted successfully",
																		{
																			icon : "success",
																		});
																setTimeout(
																		function() {
																			location
																					.reload(true);
																		}, 2000);

															} else {
																swal(
																		"Paybill has been deleted successfully",
																		{
																			icon : "success",
																		});
																setTimeout(
																		function() {
																			location
																					.reload(true);
																		}, 2000);
															}

														}
													});
										}
							    }
											})
									});

					$('#btnForwardBill').click(function() {
						var paybillGenerationTrnId = $('#radioval').val();
						var userId = $('#userId').val();
						swal({
							  title: "Are you sure?",
							  text: "PayBill Generate !",
							  icon: "warning",
							  buttons: true,
							  dangerMode: true,
							}).then((willDelete) => {
							    if (willDelete) {   
									$.ajax({
									      type: "GET",
									      url: "../ddoast/generatePaybill/"+paybillGenerationTrnId+"/"+userId,
									      async: true,
									      success: function(data){
									    	  swal("PayBill Generate successfully !", {
									    	      icon: "success",
									    	  });
									    	  setTimeout(function() {
												    location.reload(true);
												}, 3000);
									      }
									 });
							     }
							})
						});
					$('#btnForwardBillToLevel2').click(function() {
						var paybillGenerationTrnId = $('#radioval').val();
						var userId = $('#userId').val();
						swal({
							  title: "Are you sure?",
							  text: "PayBill will be Forwarded to Level 2 !",
							  icon: "warning",
							  buttons: true,
							  dangerMode: true,
							}).then((willDelete) => {
							    if (willDelete) {   
									$.ajax({
									      type: "GET",
									      url: "../ddoast/forwardPayBillToLevel2/"+paybillGenerationTrnId+"/"+userId,
									      async: true,
									      success: function(data){
									    	  swal("PayBill Forwarded successfully !", {
									    	      icon: "success",
									    	  });
									    	  setTimeout(function() {
												    location.reload(true);
												}, 3000);
									      }
									 });
							     }
							})
						});

					//Forward to Beams 
					
				/*	$('#btnForwardBillToBeams').click(function() {
						var paybillGenerationTrnId = $('#radioval').val();
						swal({
							  title: "Are you sure?",
							  text: "PayBill will be Forwarded to Level 2 !",
							  icon: "warning",
							  buttons: true,
							  dangerMode: true,
							}).then((willDelete) => {
							    if (willDelete) {   
									$.ajax({
									      type: "GET",
									      url: "../paybill/forwardPayBillToLevel2/"+paybillGenerationTrnId,
									      async: true,
									      success: function(data){
									    	  swal("PayBill Forwarded successfully !", {
									    	      icon: "success",
									    	  });
									    	  setTimeout(function() {
												    location.reload(true);
												}, 3000);
									      }
									 });
							     }
							})
						});*/
					
					});



//New code added


$('#btnForwardBillToBeams').click(function() {
	var paybillGenerationTrnId = $('#radioval').val();
	//var consolidatedId=$("#consolidatedId").val();
//  $("#consolidatedId").val($(this).text());
	
	
		    if (paybillGenerationTrnId != '') {   
				$.ajax({
				      type: "POST",
				      contentType : 'application/json',
				      dataType : 'json',
				      url: "../operator/beams/frwdbilldatabeams/"+paybillGenerationTrnId,
				      async: true,
				      error : function(data) {
							console.log(data);
					},
				      success: function(data){
				    	  console.log(data);
				    	  var newData;
							 for(var i=0;i<data.length;i++){
								 if(i==0){
									 newData=data[i];
								 }else{
									 newData=newData+"\n"+data[i];
								 }
							 }
							 console.log(newData);
							 
							 
							//var newData=
							 swal(" " + newData, {
					    	      icon: "success",
					    	    
					    	  });
							 setTimeout(
										function() {
											location
													.reload(true);
										}, 1000);
							
						}
				 });
		     }
//		})
	});

///////end


//$('.paybillGenerationTrnId').click(function() {
	
	$('body').on('click','.paybillGenerationTrnId',function(){
	 //  alert("hello");
	   var paybillGenerationTrnId=$(this).text();
		 if (paybillGenerationTrnId != '') {
			 $.ajax({
				 type : "GET",
				 url : "../ddoast/viewChangeStatementReport/"+paybillGenerationTrnId,
					 async : true,
					 error : function(data) {
						 console.log(data);
					 },
					 success : function(data) {
						 console.log(data);
						 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
						 var win = window.open("","",urlstyle);
				            win.document.write("");
				            win.document.write(data);
				            win.focus();
					}
			 });
		 }
});

jQuery(document).ready(function($) {
	var varMessage = $("#message").val();
	if (varMessage != "" && varMessage != undefined) {
		swal('' + varMessage, {
			icon : "success",
		});
	}
});

/*
$("#btnSearch")
.click(
		function(e) {
			e.preventDefault();
			var roleId= $("#roleId").val();
			var billNumber= $('option:selected',"#billNumber").attr('data');
			var yearName = $("#yearName").val();
			var monthName = $("#monthName").val();
			if (monthName == "" || monthName == "0") {
				e.preventDefault();
				swal("Please select month");
			} 
			 else if (yearName == "" || yearName == "0") {
					e.preventDefault();
					swal("Please select year");
				}
			 else{
				 $("#action").val("search");
				  $("#viewPaybillFrm").submit(); 
			 }
		});		*/

$("#btnSearch")
.click(
		function(e) {
			e.preventDefault();
			//var billNumber = $("#billNumber").val();
			var roleId= $("#roleId").val();
			var billNumber= $('option:selected',"#billNumber").attr('data');
			var yearName = $("#yearName").val();
			var monthName = $("#monthName").val();
			if (monthName == "" || monthName == "0") {
				e.preventDefault();
				swal("Please select month");
			} 
			 else if (yearName == "" || yearName == "0") {
					e.preventDefault();
					swal("Please select year");
				}
			/* else if (billNumber == "" || billNumber == "0") {
				e.preventDefault();
				swal("Please select billNumber");
			} */else  {
				$("#loaderMainNew").show();
				
				var urlCall;
				
				if(billNumber == "" || billNumber == "0" || billNumber == undefined){
					//alert("In findPayBillByMonthYear");
					 urlCall="../ddoast/findPayBillByMonthYear/"+yearName+"/"+monthName;
				}
				else{
					 urlCall="../ddoast/findPayBillByBillNumber/"+billNumber+"/"+yearName+"/"+monthName;
				}
				
				if(roleId !=3 || roleId !="3"){
					if(billNumber == "" || billNumber == "0" || billNumber == undefined){
						//alert("In findPayBillByMonthYear");
						 urlCall="../ddo/findPayBillByMonthYear/"+yearName+"/"+monthName;
					}
					else{
						 urlCall="../ddo/findPayBillByBillNumber/"+billNumber+"/"+yearName+"/"+monthName;
					}
				}
				
				$
						.ajax({
							type : "GET",
							//url : "../paybill/findPayBillByBillNumber/"+billNumber+ "/" + yearName + "/" + monthName,
							url : urlCall,
							async : false,
							error : function(data) {
								console.log(data);
								$("#loaderMainNew").hide();
							},
							contentType : 'application/json',
							success : function(data) {
								$("#loaderMainNew").hide();
								console.log(data);
								
								$('#tblShowPayBill').show();
								$('#tblShowPayBill_wrapper').show();
								$('#tblShowPayBill tbody').html('');
								if (parseInt(data.length) > 0) {
									console.log("code updated");
									//alert(data.length);
									console.log(data);
							 $("#tblShowPayBill").dataTable().fnClearTable();
//									var paybillGenerationTrnId,status,billDescription, schemeCode, schemeName, noOfEmployee,authno, billGrossAmt, billNetAmt, isActive,ddoCode;
									var paybillGenerationTrnId,status,billDescription,  noOfEmployee,RTGS, billGrossAmt, billNetAmt, isActive,ddoCode,schemeCode, schemeName;
									for(var i=0;i<data.length;i++){
										
													paybillGenerationTrnId = data[i].paybillGenerationTrnId;
													billDescription = data[i].billDescription;
													console.log(data[i].billDescription);
													schemeCode = data[i].schemeCode;
													schemeName = data[i].schemeName;
													noOfEmployee = data[i].noOfEmployee;
													//authno=result[8];
													RTGS=data[i].paybillGenerationTrnId;
													billGrossAmt =data[i].billGrossAmt;
													billNetAmt = data[i].billNetAmt;
													//ddoCode = result[5];
			                                        status= data[i].isActive;
			                                        authNo= data[i].authno;
			                                        console.log(status);
                                                        
                                                       var   change1;
                                                       var inner5;
                                                       var RTGS;
                                                         change1="<a    class='paybillGenerationTrnId'>"+paybillGenerationTrnId+"</a>"; 
                                                                  
                                       inner5="<a   data-bill-number='"+paybillGenerationTrnId+"'  class='showinneRreport' >"+paybillGenerationTrnId+"</a></td>";
                                       
                                       if(RTGS!=null){
                                    	   if(status==14){
                                    		   RTGS="<a  target='_blank' href='../ddoast/generateRTGSReport/"+paybillGenerationTrnId+"' >"+RTGS+"</a>";
                                    	   }
                                    	   else{
                                    		   RTGS=paybillGenerationTrnId; 
                                    	   }
                                       }else{
                                    	   RTGS="-";
                                       }
                                       
                                       
                                       var chk = "<input type='radio' name='paybillId' class='generatedChkId' data-pid='"
																+ paybillGenerationTrnId
																+ "' data='"
																+ status
																+ "'  value='"
																+ paybillGenerationTrnId
																+ "'>";
														if (status == 1) {
															//change1=paybillGenerationTrnId;
															inner5=paybillGenerationTrnId;
															
															isActive = '<span class="label label-success text-center payBillTrnId" data="'
																	+ paybillGenerationTrnId
																	+ '">Change Statement Generated</span>';
														} else if (status == 2) {
															/*change1=paybillGenerationTrnId;*/
															inner5=paybillGenerationTrnId;
															
															isActive = '<span class="label label-success text-center" data="'
																	+ paybillGenerationTrnId
																	+ '">Change Stement Forwarded</span>';
														}
														else if (status == 3) {
															/*change1=paybillGenerationTrnId;*/
															inner5=paybillGenerationTrnId;
															
															isActive = '<span class="label label-success text-center" data="'
																	+ paybillGenerationTrnId
																	+ '">Change Statement Approved</span>';
														}
														else if (status == 4) {
															/*change1=paybillGenerationTrnId;*/
															inner5=paybillGenerationTrnId;
															
															isActive = '<span class="label label-success text-center" data="'
																	+ paybillGenerationTrnId
																	+ '">Change Statement Reject</span>';
														}
														else if (status == 5) {
															/*change1=paybillGenerationTrnId;*/
															//inner5=paybillGenerationTrnId;
															
															isActive = '<span class="label label-success text-center" data="'
																	+ paybillGenerationTrnId
																	+ '">Created</span>';
														}
														else if (status == 6) {
															/*change1=paybillGenerationTrnId;*/
															//inner5=paybillGenerationTrnId;
															
															isActive = '<span class="label label-success text-center" data="'
																	+ paybillGenerationTrnId
																	+ '">Forwarded</span>';
														}
														else if (status == 7) {
															/*change1=paybillGenerationTrnId;
															inner5=paybillGenerationTrnId;*/
															
															isActive = '<span class="label label-success text-center" data="'
																	+ paybillGenerationTrnId
																	+ '">Paybill Reject</span>';
														}
														else if (status == 8) {
															/*change1=paybillGenerationTrnId;
															inner5=paybillGenerationTrnId;*/
															
															isActive = '<span class="label label-success text-center" data="'
																	+ paybillGenerationTrnId
																	+ '">Paybill Delete</span>';
														}
														else if (status == 9) {
															/*change1=paybillGenerationTrnId;
															inner5=paybillGenerationTrnId;*/
															
															isActive = '<span class="label label-success text-center" data="'
																+ paybillGenerationTrnId
																+ '">Consolidated Paybill</span>';
														}
														else if (status == 10) {
															/*change1=paybillGenerationTrnId;
															inner5=paybillGenerationTrnId;*/
															
															isActive = '<span class="label label-success text-center" data="'
																+ paybillGenerationTrnId
																+ '">Paybill Forward</span>';
														}
														else if (status == 11) {
															/*change1=paybillGenerationTrnId;
															inner5=paybillGenerationTrnId;
*/															
															isActive = '<span class="label label-success text-center" data="'
																+ paybillGenerationTrnId
																+ '">Consolidate Paybill Approved</span>';
														}
														else if (status == 12) {
															/*change1=paybillGenerationTrnId;*/
															//inner5=paybillGenerationTrnId;
															
															isActive = '<span class="label label-success text-center" data="'
																+ paybillGenerationTrnId
																+ '">Paybill Rejected</span>';
														}
														else if (status == 13) {
															/*change1=paybillGenerationTrnId;*/
															inner5=paybillGenerationTrnId;
															
															isActive = '<span class="label label-success text-center" data="'
																+ paybillGenerationTrnId
																+ '">Consolidated Deleted</span>';
														}
														else if (status == 14) {
															/*change1=paybillGenerationTrnId;*/
															/*inner5=paybillGenerationTrnId;*/
															
															isActive = '<span class="label label-success text-center" data="'
																+ paybillGenerationTrnId
																+ '">Voucher Entry Done</span>';
														}
														else if (status == 15) {
															/*change1=paybillGenerationTrnId;*/
														/*	inner5=paybillGenerationTrnId;*/
															
															isActive = '<span class="label label-success text-center" data="'
																+ paybillGenerationTrnId
																+ '">Consolidated Deleted</span>';
														}
														console.log(isActive);
														var b="--";
														$(
																"#tblShowPayBill")
																.dataTable()
																.fnAddData(
																		[
																				chk,
																				change1,
																				inner5,
																				billDescription,
																			
																				schemeCode,
																				schemeName,
																				
																				noOfEmployee,
																				billGrossAmt,
																				billNetAmt,
																				RTGS,
																				isActive]);
														
														
														
														 $('#tblShowPayBill tr').each(function(row, tr){
														        $(tr).find('td:eq(7)').text();
														        $(tr).find('td:eq(8)').text();
														        $(tr).find('td:eq(7)').text(toPlainString($(tr).find('td:eq(7)').text()));
														        $(tr).find('td:eq(8)').text(toPlainString($(tr).find('td:eq(8)').text()));
														    });         	


														    function toPlainString(num) {
														      return (''+ +num).replace(/(-?)(\d*)\.?(\d*)e([+-]\d+)/,
														        function(a,b,c,d,e) {
														          return e < 0
														            ? b + '0.' + Array(1-e-c.length).join(0) + c + d
														            : b + c + d + Array(e-d.length+1).join(0);
														        });
														    } 
														    
														    
													}   //loop end
								}
								else{
									swal("No Records Found");
								}
							}
						});
			}
		});



/*$("#chqNo")
.blur(
		function(e) {
			
			var chkNo=$(this).val();
			if(chkNo!=""){
				$("#accNo").attr("disabled", true);
				$("#ifscCode").attr("disabled",true);
				$("#chqNo").attr("disabled", false);
				$("#chqDate").attr("disabled",false);
			}
			
		});
$("#accNo")
.blur(
		function(e) {
			
			var accNo=$(this).val();
			if(accNo!=""){
				$("#accNo").attr("disabled", false);
				$("#ifscCode").attr("disabled",false);
				$("#chqNo").attr("disabled", true);
				$("#chqDate").attr("disabled",true);
			}
			
		});*/



$("#btnUpdate")
.click(
		function(e) {
			  $("#btnUpdate").attr("disabled", true); 
			$("#loaderMainNew").show();
			var paybillGenerationTrnId = $('#radioval').val();
			var voucherNo=$("#voucherNo").val();
			var voucherDate=$("#voucherDate").val();
			var chqNo=$("#chqNo").val();
			var chqDate=$("#chqDate").val();
			var accNo=$("#accNo").val();
			var ifscCode=$("#ifscCode").val();
			var userId=$("#userId").val();
			
			
			if (voucherNo == "" || voucherNo == "0") {
				e.preventDefault();
				swal("Please Enter Voucher Number");
			} else if (voucherDate == "" || voucherDate == "0") {
				e.preventDefault();
				swal("Please Enter Voucher Date");
			}
			else
				{
				$("#loaderMainNew").show();
				$("#btnUpdate").attr("disabled", true); 
				$
				.ajax({
					type : "GET",
					url : "../ddoast/updateVoucherDtls/"+paybillGenerationTrnId+ "/" + voucherNo + "/" + voucherDate + "/" + chqNo + "/" + chqDate + "/" + accNo + "/" + ifscCode + "/" + userId,
					async : false,
					error : function(data) {
						console.log(data);
						$("#loaderMainNew").hide();
					},
					 success: function(data){
				    	  console.log(data);
				    	  $("#loaderMainNew").show();

							 swal("Record Updated Successfully");
							 setTimeout(
										function() {
											location
													.reload(true);
										}, 1000);
							
						}
				
				});
				}
			$("#loaderMainNew").hide();
		});


	
	
$(document).on('click','.showinneRreport', function(event){	
	var billnm = billNumber;
	var ddoCode = "1";
	
	 var   billNumber = $(this).data('bill-number');     //$(this).attr('bill-number'); 

	$("#loaderMainNew").show();

	$
			.ajax({
				type : "GET",
				url : "../ddoast/getinnerreport/" + billNumber + "/" + 1
						+ "/" + 1 + "/" + ddoCode,
				async : true,
				contentType : 'application/json',
				error : function(data) {
					alert("error");
					console.log(data);
				},
				success : function(data) {
					$("#loaderMainNew").hide();
					var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
					var win = window.open("", "", urlstyle);
					win.document.write(data);
					win.focus();

					// self.close();

				}
			});
});

