jQuery(document)
		.ready(
				function($) {
					  $("#btnVoucherEntry").hide();  //
					
					function hideAllPensBillButtons(){
						$("#btnForwardChangeStatment").hide();
						$("#btnForwardChangeStatmentToDYCAO1").hide();
						$("#btnRejectChangeStatmentByAO").hide();
						$("#btnApproveChangeStatment").hide();
						$("#btnRejectChangeStatmentByDYCAO").hide();
						$("#btnForwardBill").hide();
						$("#btnApprPensBillForwardToDYCAO").hide();
						$("#btnRejectPensBilByAO").hide();
						$("#btnApprovePensBillByDYCAO1").hide();
						$("#btnRejctPensionBillByDYCAO").hide();
						 $("#btnDeleteBill").hide();
                         $("#btnVoucherEntry").hide();  //
					}
					

					var date = new Date();
					var currentMonth = date.getMonth() + 1;
					var currentYear = date.getFullYear();

					var currentYearval = currentYear.toString().substr(-2);
					 console.log("currentyear"+currentYearval);
					var selectedMonth = $('#monthName').val();
					if (selectedMonth == "" && currentMonth != "") {
						$('#monthName').val(currentMonth);
					} else {
						$('#monthName').val(selectedMonth);
					}
					$('#monthName option:eq('+ currentMonth +')').attr('selected', 'selected');       /*selected the current Month*/
					$('#yearName').val(parseInt(currentYearval) + 1);

					$('#yearName option[value="'+ (parseInt(currentYearval) + 1) +'"]' ).attr('selected', 'selected');                        /*selected the current Year*/

					console.log("currentMonth" + currentMonth);
					/* $("#btnForwardChangeStatment").attr("disabled", true);// 1
                     $("#btnForwardBillToBeams").attr("disabled", true);   //5,6,7
                     $("#btnForwardBill").attr("disabled", true);  //generate bill   3
                     $("#btnVoucherEntry").attr("disabled", true);  //generate bill   3
*/                     
                     
                     

								$('body').on('change','.generatedChkId',function(){
								if (this.checked) {
									var id = $(this).val();

									$('#radioval').val(id);
									$('#radioid').val(id);

									 console.log($(this).attr("data"));
                                     var status=$(this).attr("data");
                                     
                                     if(status==1){
                                    	    hideAllPensBillButtons();
                                    	    if(Number($("#roleId").val())==34){
                                    	    	 $("#btnForwardChangeStatment").show();
                                    	    	   $("#btnDeleteBill").show();
                                    	    }
                                    	    
                                    	    
                                    	 
                                     }
                                     else if(status==2){
                                 	    hideAllPensBillButtons();
                                 	    if(Number($("#roleId").val())==31){
                                 	    	$("#btnForwardChangeStatmentToDYCAO1").show();
                      						$("#btnRejectChangeStatmentByAO").show();
                                 	    }
                                 	    
                                 	    
                                 	   if(Number($("#roleId").val())==34){
                                 		  $("#btnDeleteBill").show();
                              	       }
                                 	    
                                 	    
                                   }
                                     else if(status==3){
                                    	 hideAllPensBillButtons();
                                     	    if(Number($("#roleId").val())==33){
                                     	    	$("#btnApproveChangeStatment").show();
                          						$("#btnRejectChangeStatmentByDYCAO").show();
                                     	    }
                                     	    
                                     	    
                                     	   if(Number($("#roleId").val())==34){
                                     		  $("#btnDeleteBill").show();
                                  	       }
                                     	
                                     }
                                     else if(status==4)
                                	 {
                                 	    hideAllPensBillButtons();
                                 	  if(Number($("#roleId").val())==34){
                                 		 $("#btnDeleteBill").show();
                             	       }
                                	 }
                                     else if(status==5){
                                    	  hideAllPensBillButtons();
                  					   	 $("#btnDeleteBill").attr("disabled", false);  //
                                     	    if(Number($("#roleId").val())==34){
                                     	    	 $("#btnForwardBill").show();
                                     	    	 $("#btnDeleteBill").show();
                                     	    }
                                     }
                                     else if(status==6)
                                    	 {
                                     	    hideAllPensBillButtons();
                                     	  if(Number($("#roleId").val())==34){
                                     		 $("#btnDeleteBill").show();
                                 	       }
                                    	 }
                                     else if(status==7)
                                	 {
                                 	    hideAllPensBillButtons();
                                 	  if(Number($("#roleId").val())==34){
                                 		 $("#btnDeleteBill").show();
                             	       }
                                 	  if(Number($("#roleId").val())==31){
                                 		 $("#btnApprPensBillForwardToDYCAO").show();
                 						$("#btnRejectPensBilByAO").show();
                                 	  }
                                	 }
                                     else if(status==14)
                                     {
                                     	    hideAllPensBillButtons();
                                     }
                                     else if(status==9)
                                     {
                                    	 hideAllPensBillButtons();
                  					   	
                                     	    if(Number($("#roleId").val())==33){
                                     	    	$("#btnApprovePensBillByDYCAO1").show();
                          						$("#btnRejctPensionBillByDYCAO").show();
                                     	    }
                                     	    
                                     	    
                                     	   if(Number($("#roleId").val())==34){
                                     		  $("#btnDeleteBill").show();
                                  	       }
                                     }
                                     else if(status==11)
                                     {
                                  	       hideAllPensBillButtons();
                                     	    if(Number($("#roleId").val())==34){
                                     	    	  $("#btnVoucherEntry").show(); //
                                     	    	 $("#btnDeleteBill").show();
                                     	    }
                                     }
                                     else{
                                     	    hideAllPensBillButtons();
                                     }
								}
							});
								
								
								
								
						

					$('.btnForwardChangeStatment')
							.click(
									function() {
										var paybillGenerationTrnId = $(
												'#radioval').val();

										// alert(paybillGenerationTrnId);
										if (paybillGenerationTrnId != '') {
											$
													.ajax({
														type : "GET",
														url : "../paybill/forwardPensChangeStatement/"
																+ paybillGenerationTrnId,
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

					
					
					
					$('.btnApprChangState')
							.click(
									function() {
										var paybillGenerationTrnId = $(
												'#radioid').val();
										
										var billStatus=$('input[name="paybillId"]:checked').attr('data');
										
										if(billStatus==4 ||  billStatus==6 ){
											swal("Rejected Paybill can not Approved");
										}
									/*	else if(billStatus==3 ){
											swal("Pension bill is already Approved ");
										}*/
										else if (paybillGenerationTrnId != '') {
											$
													.ajax({
														type : "GET",
														url : "../paybill/approvePensChangeStatement/"
																+ paybillGenerationTrnId,
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
					$('.btnRejectChangState')
					.click(
							function() {
								var paybillGenerationTrnId = $(
										'#radioid').val();
								
								var billStatus=$('input[name="paybillId"]:checked').attr('data');
								
								if(billStatus==4 ||  billStatus==6 ){
									swal("Pension bill is already Rejected");
								}
								/*else if(billStatus==3){
									swal("Approved Pension bill can not Reject");
								}*/
								else if (paybillGenerationTrnId != '') {
									$
											.ajax({
												type : "GET",
												url : "../paybill/rejectPensChangeStatement/"
														+ paybillGenerationTrnId,
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
					//for reject change statement
					$('.btnRejectBill')
					.click(
							function() {
								var paybillGenerationTrnId = $(
								'#radioid').val();
								
								var billStatus=$('input[name="paybillId"]:checked').attr('data');
								
								if(billStatus==4  || billStatus==6){
									swal("Pension bill is already Rejected");
								}
							/*	else if(billStatus==3){
									swal("Approved Pension bill can not Reject");
								}*/
								else if (paybillGenerationTrnId != '') {
									$
									.ajax({
										type : "GET",
										url : "../paybill/rejectPensChangeStatement/"
											+ paybillGenerationTrnId,
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
															"Pension Bill Rejected Successfully",
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
															"Pension Bill Rejected Successfully",
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
										
									
										
										swal({
											  title: "Are you sure?",
											  text: "Delete Pension bill !",
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
														url : "../paybill/deletePensBill/"
																+ paybillGenerationTrnId,
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
																		"Pension bill has been deleted successfully",
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
																		"Pension bill has been deleted successfully",
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
					
					
					
					

					$('.btnForwardBill').click(function() {
						var paybillGenerationTrnId = $('#radioval').val();
						
						
						
						var msg;
						
						var roleId=$("#roleId").val();
						
						if(Number(roleId)==31){
							msg="Pension Bill Forward To DYCAO1";
						}else if(Number(roleId)==33){
							msg="Pension Bill Approve";
						}else if(Number(roleId)==34){
							msg="Pension bill Generate !";
						}
						
						
						
						
						swal({
							  title: "Are you sure?",
							  text: ""+msg,
							  icon: "warning",
							  buttons: true,
							  dangerMode: true,
							}).then((willDelete) => {
							    if (willDelete) {   
									$.ajax({
									      type: "GET",
									      url: "../paybill/generatePensPaybill/"+paybillGenerationTrnId,
									      async: true,
									      success: function(data){
									    	
									    	  
									    	      msg;
												
												 roleId=$("#roleId").val();
												
												if(Number(roleId)==31){
													msg="Pension Bill Approved Succesfully and forwarded to SYCAO1 Successfully !";
												}else if(Number(roleId)==33){
													msg="Pension Bill Approved Successfully !";
												}else if(Number(roleId)==34){
													msg="Pension bill Generated Successfully !";
												}
									    	  
									    	  
									    	  
									    	  swal(""+msg, {
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
						swal({
							  title: "Are you sure?",
							  text: "Pension bill will be Forwarded to Level 2 !",
							  icon: "warning",
							  buttons: true,
							  dangerMode: true,
							}).then((willDelete) => {
							    if (willDelete) {   
									$.ajax({
									      type: "GET",
									      url: "../paybill/forwardPensPayBillToLevel2/"+paybillGenerationTrnId,
									      async: true,
									      success: function(data){
									    	  swal("Pension bill Forwarded successfully !", {
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
				 url : "../paybill/displayPensChangeStatementReport/"+paybillGenerationTrnId,
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
$("#btnSearch")
.click(
		function(e) {
			e.preventDefault();
			//var billNumber = $("#billNumber").val();
			var billNumber= $("#billNumber").val();
			var yearName = $("#yearName").val();
			var monthName = $("#monthName").val();
			var context = $("#context").val();
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
				var urlCall;
				
				if(billNumber == "" || billNumber == "0" || billNumber == undefined){
					//alert("In findPayBillByMonthYear");
					 urlCall="../paybill/findPensPayBillByMonthYear/"+yearName+"/"+monthName;
				}
				else{
					 urlCall="../paybill/findPensPayBillByBillNumber/"+billNumber+"/"+yearName+"/"+monthName;
				}
				
				$
						.ajax({
							type : "GET",
							//url : "../paybill/findPayBillByBillNumber/"+billNumber+ "/" + yearName + "/" + monthName,
							url : urlCall,
							async : false,
							error : function(data) {
								console.log(data);
							},
							success : function(data) {
								
								$('#tblShowPayBill').show();
								$('#tblShowPayBill_wrapper').show();
								$('#tblShowPayBill tbody').html('');
								if (parseInt(data.length) > 0) {
									console.log("code updated");
									//alert(data.length);
									console.log(data);
							 $("#tblShowPayBill").dataTable().fnClearTable();
//									var paybillGenerationTrnId,status,billDescription, schemeCode, schemeName, noOfEmployee,authno, billGrossAmt, billNetAmt, isActive,ddoCode;
									
							 //a.pens_paybill_generation_trn_id,a.bill_gross_amt,a.bill_net_amount,a.is_active,b.bank_name,a.no_of_employee,a.auth_no
							 
							 var paybillGenerationTrnId,status,billDescription,  noOfEmployee,RTGS, billGrossAmt, billNetAmt, isActive,ddoCode;
									$
											.each(
													data,
													function(i, result) {
														paybillGenerationTrnId = result[0],
														billDescription = result[4];
														console.log(result[0]);
														noOfEmployee = result[4];
														//authno=result[8];
														RTGS=result[0];
														billGrossAmt = result[1];
														billNetAmt = result[2];
														//ddoCode = result[5];
                                                        status= result[3];
                                                        console.log(status);
                                                        
                                                       var   change1;
                                                       var inner5;
                                                       var RTGS;
                                                       
                                                       
                                            change1="<a class='paybillGenerationTrnId'>"+paybillGenerationTrnId+"</a>"; 
                                            
                                            
                                            
                                            // th:href="@{${'/paybill/pensAllInnerReportPage/'+billNumber}}" target="_blank"
                                                                  
                                      // inner5="<a   href='"+context+"/paybill/pensAllInnerReportPage/"+paybillGenerationTrnId+"'  onclick='showinnerreportPension("+paybillGenerationTrnId+");'>"+paybillGenerationTrnId+"</a></td>";
                                       inner5="<a  target='_blank'    href='"+context+"/paybill/pensAllInnerReportPage/"+paybillGenerationTrnId+"'  >"+paybillGenerationTrnId+"</a></td>";
                                       
                                       if(RTGS!=null){
                                    	   if(status==14){
                                    		   RTGS="<a  target='_blank' href='../paybill/generatePensionRTGSReport/"+paybillGenerationTrnId+"' >"+RTGS+"</a>";
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
																	+ '">Change Statement Approved By AO</span>';
														}
														else if (status == 4) {
															/*change1=paybillGenerationTrnId;*/
															inner5=paybillGenerationTrnId;
															
															isActive = '<span class="label label-success text-center" data="'
																	+ paybillGenerationTrnId
																	+ '">Change Statement Rejected</span>';
														}
														else if (status == 5) {
															/*change1=paybillGenerationTrnId;*/
															//inner5=paybillGenerationTrnId;
															
															inner5=paybillGenerationTrnId;
															isActive = '<span class="label label-success text-center" data="'
																	+ paybillGenerationTrnId
																	+ '">Change Statement Approved By DYCAO1</span>';
														}
														else if (status == 6) {
															/*change1=paybillGenerationTrnId;*/
															//inner5=paybillGenerationTrnId;
															inner5=paybillGenerationTrnId;
															isActive = '<span class="label label-success text-center" data="'
																	+ paybillGenerationTrnId
																	+ '">Change Statement Rejected By DYCAO1</span>';
														}
														else if (status == 7) {
															/*change1=paybillGenerationTrnId;
															inner5=paybillGenerationTrnId;*/
															
															isActive = '<span class="label label-success text-center" data="'
																	+ paybillGenerationTrnId
																	+ '">Pension Bill Generated</span>';
														}
														else if (status == 8) {
															/*change1=paybillGenerationTrnId;
															inner5=paybillGenerationTrnId;*/
															
															isActive = '<span class="label label-success text-center" data="'
																	+ paybillGenerationTrnId
																	+ '">Pension Bill Delete</span>';
														}
														else if (status == 9) {
															/*change1=paybillGenerationTrnId;
															inner5=paybillGenerationTrnId;*/
															
															isActive = '<span class="label label-success text-center" data="'
																+ paybillGenerationTrnId
																+ '">Pension Bill Approved By AO</span>';
														}
														else if (status == 10) {
															/*change1=paybillGenerationTrnId;
															inner5=paybillGenerationTrnId;*/
															
															isActive = '<span class="label label-success text-center" data="'
																+ paybillGenerationTrnId
																+ '">Pension Bill Rejected By AO</span>';
														}
														else if (status == 11) {
															/*change1=paybillGenerationTrnId;
															inner5=paybillGenerationTrnId;
*/															
															isActive = '<span class="label label-success text-center" data="'
																+ paybillGenerationTrnId
																+ '">Pension Bill Approved By DYCAO1</span>';
														}
														else if (status == 12) {
															/*change1=paybillGenerationTrnId;*/
															//inner5=paybillGenerationTrnId;
															
															isActive = '<span class="label label-success text-center" data="'
																+ paybillGenerationTrnId
																+ '">Pension Bill Rejected By DYCAO1</span>';
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
																			//billDescription,
																				noOfEmployee,
																				billGrossAmt,
																				billNetAmt,
																				RTGS,
																				isActive]);
														
														
														
														 $('#tblShowPayBill tr').each(function(row, tr){
														        $(tr).find('td:eq(4)').text();
														        $(tr).find('td:eq(5)').text();
														        $(tr).find('td:eq(4)').text(toPlainString($(tr).find('td:eq(4)').text()));
														        $(tr).find('td:eq(5)').text(toPlainString($(tr).find('td:eq(5)').text()));
														    });         	


														    function toPlainString(num) {
														      return (''+ +num).replace(/(-?)(\d*)\.?(\d*)e([+-]\d+)/,
														        function(a,b,c,d,e) {
														          return e < 0
														            ? b + '0.' + Array(1-e-c.length).join(0) + c + d
														            : b + c + d + Array(e-d.length+1).join(0);
														        });
														    } 
													});
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
			
			var paybillGenerationTrnId = $('#radioval').val();
			var voucherNo=$("#voucherNo").val();
			var voucherDate=$("#voucherDate").val();
			var chqNo=$("#chqNo").val();
			var chqDate=$("#chqDate").val();
			var accNo=$("#accNo").val();
			var ifscCode=$("#ifscCode").val();
			
			/*chqNo=chqNo==''?"0":chqNo;
			chqDate=chqDate==''?"0":chqDate;
			accNo=accNo==''?"0":accNo;
			ifscCode=ifscCode==''?"0":ifscCode;*/
			
			
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
				$
				.ajax({
					type : "GET",
					url : "../paybill/updateVoucherEntry/"+paybillGenerationTrnId+ "/" + voucherNo + "/" + voucherDate + "/" + chqNo + "/" + chqDate + "/" + accNo + "/" + ifscCode,
					async : false,
					error : function(data) {
						console.log(data);
						$("#loaderMainNew").hide();
					},
					 success: function(data){
				    	  console.log(data);
				    		$("#loaderMainNew").hide();
							 swal("Voucher entry Updated Successfully");
							 setTimeout(
										function() {
											location
													.reload(true);
										}, 1000);
							
						}
				
				});
				}
		});