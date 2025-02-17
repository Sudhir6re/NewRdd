jQuery(document)
		.ready(
				function($) {
				 $('#tblShowPayBill').hide();
				 $('#btnViewDetails').attr("disabled", true);
                 $('#btnAbstractReport').attr("disabled", true); 
                 $('#btnForwardToBeams').attr("disabled", true);  
                 $('#ApproveBill').attr("disabled", true);  
});

var pid;

$("#btnSearch")
.click(
		function(e) {
			// e.preventDefault();
//			var schemeCode = $("#schemeCode").val();
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
			
			 /*else if (schemeCode == "" || schemeCode == "0") {
				e.preventDefault();
				swal("Please select scheme code");
			} */
			 
			 else  {
					var urlCall;
					
					
					urlCall="../ddo/findAllConsolidatedPaybillListWithoutFilter/"+yearName+"/"+monthName;
					
				/*	if(schemeCode == "" || schemeCode == "0" || schemeCode == undefined){
						alert("In findPayBillByMonthYear");
						 urlCall="../paybill/findAllConsolidatedPaybillListWithoutFilter/"+yearName+"/"+monthName;
					}*/
					/*else{
						 urlCall="../paybill/findAllConsolidatedPaybillListUsingFilter/"+schemeCode+"/"+yearName+"/"+monthName;
					}*/
			 
					$("#loaderMainNew").show();
			
				$
						.ajax({
							type : "GET",
							url : urlCall,
							async : false,
							error : function(data) {
								console.log(data);
							},
							success : function(data) {
								$("#loaderMainNew").hide();
								$('#tblDataTable').show();
								$('#tblDataTable_wrapper').show();
								$('#tblDataTable tbody').html('');
								if (parseInt(data.length) > 0) {
									console.log("code updated");
									//alert(data.length);
									console.log(data);
							 $("#tblDataTable").dataTable().fnClearTable();
//									var paybillGenerationTrnId,status,billDescription, schemeCode, schemeName, billGrossAmt, billNetAmt, isActive,ddoCode;
									var paybillGenerationTrnId,status,billDescription,  billGrossAmt, billNetAmt, isActive,ddoCode,authNo;
									$
											.each(
													data,
													function(i, result) {
														paybillGenerationTrnId = result[0],
														//billDescription = result.billDescription;
														console.log(result[0]);
														schemeCode = result[1];
														schemeName = result[2];
														billGrossAmt = result[3];
														billNetAmt = result[4];
														//ddoCode = result[5];
                                                        status= result[5];
                                                        authNo= result[6];
                                                        console.log(status);
														var chk = "<input type='radio' name='consolidatedPaybillTrnId' class='payBillId' data-pid='"
																+ paybillGenerationTrnId
																+ "' data-status='"
																+ status
																+ "'  value='"
																+ paybillGenerationTrnId
																+ "'>";
														if (status == "9") {
															isActive = '<span class="label label-success text-center payBillTrnId" data="'
																	+ paybillGenerationTrnId
																	+ '"> Pending</span>';
															/*+ '">Consolidated Paybill Forward to BEAMS</span>';
*/															paybillGenerationTrnId='<a class="consolidatePayBillTrnId" >'+paybillGenerationTrnId+'</a>';

                                                         $("#btnConsolidatePaybill").attr("disabled", true);
                                                         
                                                         $('#ApproveBill').attr("disabled", false);  

															
														}else if (status == "5" || status == "6" ) {
															isActive = '<span class="label label-success text-center payBillTrnId" data="'
																+ paybillGenerationTrnId
																+ '"> Paybill Consolidated</span>';
														/*+ '">Consolidated Paybill Forward to BEAMS</span>';
*/															paybillGenerationTrnId='<a class="consolidatePayBillTrnId" >'+paybillGenerationTrnId+'</a>';


                                                        
													      }
														else if (status == "10") {
															isActive = '<span class="label label-success text-center" data="'
																	+ paybillGenerationTrnId
																	+ '">Forwarded to Level 2</span>';
														}
														else if (status == "11") {
															isActive = '<span class="label label-success text-center payBillTrnId" data="'
																+ paybillGenerationTrnId
																+ '"> Consolidated Paybill Approved</span>';
														/*+ '">Consolidated Paybill Forward to BEAMS</span>';
*/															paybillGenerationTrnId='<a class="consolidatePayBillTrnId" >'+paybillGenerationTrnId+'</a>';
															$("#btnDeleteBill").attr("disabled", true);
															
														    $("#btnConsolidatePaybill").attr("disabled", true);
														    
														    
														 //   $('#ApproveBill').attr("disabled", false);  
															
															/*isActive = '<span class="label label-success text-center" data="'
																	+ paybillGenerationTrnId
																	+ '">Consolidated Paybill Approve from  Beams</span>';*/
														}
														else if (status == "12") {
															isActive = '<span class="label label-success text-center payBillTrnId" data="'
																+ paybillGenerationTrnId
																+ '"> Consolidated Paybill Rejected</span>';
														/*+ '">Consolidated Paybill Forward to BEAMS</span>';
*/															paybillGenerationTrnId='<a class="consolidatePayBillTrnId" >'+paybillGenerationTrnId+'</a>';
															$("#btnForwardToBeams").attr("disabled", true);
															
														    $("#btnConsolidatePaybill").attr("disabled", true);
														}
														else if (status == "13") {
															isActive = '<span class="label label-success text-center" data="'
																	+ paybillGenerationTrnId
																	+ '">Consolidated Paybill Deleted</span>';
															
														    $("#btnConsolidatePaybill").attr("disabled", true);
														    
														}
														else if (status == "14") {
															isActive = '<span class="label label-success text-center payBillTrnId" data="'
																+ paybillGenerationTrnId
																+ '"> Voucher Entry Done</span>';
														/*+ '">Consolidated Paybill Forward to BEAMS</span>';
*/															paybillGenerationTrnId='<a class="consolidatePayBillTrnId" >'+paybillGenerationTrnId+'</a>';

															$("#btnDeleteBill").attr("disabled", true);
															$("#btnForwardToBeams").attr("disabled", true);
															
														    $("#btnConsolidatePaybill").attr("disabled", true);
														
													}
														console.log(isActive);
														var b="--";
														
														
													   var eKuberCount=result[7];
													   var cmpDownloadStatus=result[8];
													   
													   
													
													   
													   if (authNo === null || authNo === '') {
													           authNo = '--';  
													       } else {
													           authNo = `<a href="/ddo/viewAuthSlip/${authNo}" class="authNo" target="_blank"><span>${authNo}</span></a>`;  
													       }
													   
													   
													        var downloadText = '';
													        if (authNo !== '--' && eKuberCount > 0 && (cmpDownloadStatus === 'EK' || cmpDownloadStatus === null)) {
													            downloadText = `<a href="#" class="downloadEkuberTextFile beneficiaryCount" data-authNo="${authNo}" data-schemeCode="${schemeCode}" data-consolidatePayBillTrnId="${paybillGenerationTrnId}">
													                                <span>${entry[5]}</span> </a>`;
													        } else {
													            downloadText = '0';
													        }

													        var downloadJson = '';
													        if (cmpDownloadStatus !== null && eKuberCount > 0 && cmpDownloadStatus === 'EK') {
													            downloadJson = `<a href="#" class="downloadEkuberJsonFile" data-authNo="${authNo}" data-schemeCode="${schemeCode}" data-consolidatePayBillTrnId="${paybillGenerationTrnId}">
													                                <span>${entry[8]}</span></a>`;
													        } else {
													            downloadJson = '-';
													        }

													   
														
														
														
														$(
																"#tblDataTable")
																.dataTable()
																.fnAddData(
																		[
																				chk,
																				paybillGenerationTrnId,
																				schemeCode,
																				schemeName,
																				billGrossAmt,
																				billNetAmt,
																				authNo,
																				downloadText,
																				downloadJson,
																				isActive]);
													});
								}
							}
						});
			}
		});

$('#btnAbstractReport').click(function() {
	var paybillGenerationTrnId = $('#radioval').val();
	var consolidatedId=$("#consolidatedId").val();
//  $("#consolidatedId").val($(this).text());
	
	$("#loaderMainNew").show();
		    if (consolidatedId != '') {   
				$.ajax({
				      type: "GET",
				      url: "../ddo/viewabstractReport/"+consolidatedId,
				      async: true,
				      success: function(data){
				    		$("#loaderMainNew").hide();
							 console.log(data);
							 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
							 var win = window.open("","",urlstyle);
					            win.document.write("");
					            win.document.write(data);
					            win.focus();
						}
				 });
		     }
//		})
	});







$('#ApproveBill').click(function() {
	var paybillGenerationTrnId = $('#radioval').val();
	var consolidatedId=$("#consolidatedId").val();
	var userId=$("#userId").val();
//  $("#consolidatedId").val($(this).text());
	$("#loaderMainNew").show();
	
		    if (consolidatedId != '') {   
				$.ajax({
				      type: "POST",
				    //  contentType : 'application/json',
				      dataType : 'json',
				      url: "../ddo/approveBill/"+consolidatedId+"/"+userId,
				      async: true,
				      error : function(data) {
							console.log(data);
							$("#loaderMainNew").hide();
							
							swal("Approved Successfully ");
							
							 setTimeout(
										function() {
											location
													.reload(true);
										}, 2000);
							 
					},
				      success: function(data){
				    	  
				    		$("#loaderMainNew").hide();
				    	 // var data;
							
							 
							//var newData=
							 swal(" " + data, {
					    	      icon: "success",
					    	    
					    	  });
							 setTimeout(
										function() {
											location
													.reload(true);
										}, 2000);
							
						}
				 });
		     }
//		})
	});




$('#btnForwardToBeams').click(function() {
	var paybillGenerationTrnId = $('#radioval').val();
	var consolidatePaybillTrnId=$("#consolidatedId").val();
//  $("#consolidatedId").val($(this).text());
	$("#loaderMainNew").show();
	
		    if (consolidatedId != '') {   
				$.ajax({
				      type: "POST",
				      contentType : 'application/json',
				      dataType : 'json',
				      url: "../ddo/beams/frwdbilldatabeams/"+consolidatePaybillTrnId,
				      async: true,
				      error : function(data) {
							console.log(data);
							$("#loaderMainNew").hide();
					},
				      success: function(data){
				    	  
				    		$("#loaderMainNew").hide();
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
							  
							  
							/* setTimeout(
										function() {
											location
													.reload(true);
										}, 2000);*/
							
						}
				 });
		     }
//		})
	});


$('body').on('click','.payBillId',function(){
	// alert("hello1");
	 //  var consolidatePayBillTrnId=$(this).text();
	 pid=$(this).attr("data-pid");
	 
	 var status=$(this).attr("data-status"); 
	 
	 $('#radioval').val($(this).attr("data-pid"));
	 
	 //alert(pid);
	   $("#consolidatedId").val($(this).val());
	   
	   $('#btnViewDetails').attr("disabled", false);
       $('#btnAbstractReport').attr("disabled", false); 
       $('#btnForwardToBeams').attr("disabled", false);
       
      if(status=="9"){
    	    $('#ApproveBill').attr("disabled", false);
       }else{
    	   $('#ApproveBill').attr("disabled", true);
       }
       
       
       
});






$('body').on('click','.consolidatePayBillTrnId',function(){
	  // alert("hello");
	   var consolidatePayBillTrnId=$(this).text();
	
		$("#loaderMainNew").show();
		 if (consolidatePayBillTrnId != '') {
			 $.ajax({
				 type : "GET",
				 url : "../ddo/consolidatePayBillTrnReport/"+consolidatePayBillTrnId,
					 async : true,
					 error : function(data) {
						 console.log(data);
					 },
					 success : function(data) {
							$("#loaderMainNew").hide();
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

///delete consolidate paybill

$('#btnDeleteBill')
.click(
		function() {
			var consPaybillGenerationTrnId =pid;
			$("#loaderMainNew").show();
			 //alert(consPaybillGenerationTrnId);
			if (consPaybillGenerationTrnId != '') {
				$
						.ajax({
							type : "GET",
							url : "../ddo/deleteConsolidateBill/"
									+ consPaybillGenerationTrnId, 
							async : true,
							contentType : 'application/json',
							error : function(data) {
								console.log(data);
							},
							success : function(data) {
								console.log(data);
								// alert(data);
								$("#loaderMainNew").hide();
								if ($("#is_changed")
										.val() == 1) {

									swal(
											"Consolidate Paybill has been deleted successfully",
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
											"Consolidate Paybill has been deleted successfully",
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
		});


$('#btnViewDetails').click(function() {
	var paybillGenerationTrnId = $('#radioval').val();
	var consolidatedId=$("#consolidatedId").val();
	$("#loaderMainNew").show();
	
//		    if (consolidatePayBillTrnId != '') {   
				$.ajax({
				      type: "GET",
				      url: "../paybill/viewDetailsReport/"+ consolidatedId,
				      async: true,
				      success: function(data){
				    	  $("#loaderMainNew").hide();
							 console.log(data);
							 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
							 var win = window.open("","",urlstyle);
					            win.document.write("");
					            win.document.write(data);
					            win.focus();
						}
				 });
//		     }
//		})
	});

