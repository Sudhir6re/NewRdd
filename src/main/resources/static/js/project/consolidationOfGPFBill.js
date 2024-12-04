								$('body').on('change','.checkbox',function(){
								if (this.checked) {
									var id = $(this).val();

									$('#radioval').val(id);
									$('#radioid').val(id);

									 console.log($(this).attr("data"));
                                     var status=$(this).attr("data");
                                     
                                     
                                     
                                     if(status==14){
                                     $("#btnVoucherEntry").attr("disabled", true);  //generate bill   3
                                     $("#approve").attr("disabled", true);  //generate bill   3
                                     $("#Delete").attr("disabled", true);  //generate bill   3
                                     }  else if(status==9)  {
                                    	 $("#btnVoucherEntry").attr("disabled", false);  //generate bill   3
                                         $("#approve").attr("disabled", true);  //generate bill   3
                                         $("#Delete").attr("disabled", true);  //generate bill   3
                                     }else if(status==8)  {
                                    	 $("#btnVoucherEntry").attr("disabled", false);  //generate bill   3
                                         $("#approve").attr("disabled", true);  //generate bill   3
                                         $("#Delete").attr("disabled", false);  //generate bill   3
                                     }
                                     else if(isActive==7){
                                 		$("#saveAndForward").prop("disabled",true);
                                		$("#approve").prop("disabled",false);
                                		$("#btnVoucherEntry").prop("disabled",true);
                                	}else if(status==11)
                                     {
                                    	 $("#btnVoucherEntry").attr("disabled", false);  //generate bill   3
                                         $("#approve").attr("disabled", true);  //generate bill   3
                                         $("#Delete").attr("disabled", true);  //generate bill   3
                                     }
                                     else{
                                    	 $("#btnVoucherEntry").attr("disabled", true);  //generate bill   3
                                         $("#approve").attr("disabled", true);  //generate bill   3
                                         $("#Delete").attr("disabled", true);  //generate bill   3
                                     }
                                     
								
									
								}
							});



$("#appType").change(function(e) {
	// var billNumber= $("#schemeBillGroupId").val();
		var appType = $("#appType").val();
		 if (appType == "" || appType == "0") {
			e.preventDefault();
			swal.fire("Please select Application Type");
		}
		else{
			    var test=window.location.href;
				var pathArray = test.split("/master");
				window.location.href=pathArray[0]+"/master/ConsolidationofGPFBILL?appType="+appType;
			}
	});




$("#select_all").change(function(e) {
	  if($(this).prop("checked")){
		  $(".checkbox").prop("checked",true);
	  }else{
		  $(".checkbox").prop("checked",false);
	  }
	});








$('#AbstractReport').click(function() {
	var consBillId = $("input:radio:checked").val();
    if (consBillId != '' &&  consBillId!=undefined ) {  
    	  $("#AbstractReport").attr("href", "../master/GPFAbstractData/"+consBillId); 
    }else{
    	swal("Please select atleast one Bill !!!");
    }
});

/*$('#saveAndForward').click(function() {
	var status="4";
	var ConsolidateId = $("input:checkbox:checked").val();
	if (ConsolidateId != '' &&  ConsolidateId!=undefined ) {  
		$("#AbstractReport").attr("href", "../master/ForwardToBeamsThroughWs/"+ConsolidateId+"/"+status); 
	}else{
		swal("Please select atleast one Bill !!!");
	}
});
*/



$('#submit').click(function() {
var status="4";
var ConsolidateId = $("input:checkbox:checked").val();
if (ConsolidateId != '' &&  ConsolidateId!=undefined ) {  
	//$("#AbstractReport").attr("href", "../master/ForwardToBeamsThroughWs/"+ConsolidateId+"/"+status); 
}else{
	swal("Please select atleast one Bill !!!");
}
});





$('#approve').click(function() {
	var ConsolidateId = $("input:radio:checked").val();
	
	var status="4";
	
		    if (ConsolidateId != '' || ConsolidateId!=undefined) {   
				$.ajax({
				      type: "POST",
				     // contentType : 'application/json',
				      dataType : 'json',
				      url: "../master/approveConsolidatedGpfBill/"+ConsolidateId+"/"+status,
				      async: true,
				      error : function(data) {
							console.log(data);
							//var newData=
							 swal("Approved Successfully", {
					    	      icon: "success",
					    	    
					    	  });
							 setTimeout(
										function() {
											location
													.reload(true);
										}, 1000);
					},
				      success: function(data){
				    	  console.log(data);
				    	 
							 
							//var newData=
							 swal("Approved Successfully", {
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
	});



$('#saveAndForward').click(function() {
	var ConsolidateId = $("input:radio:checked").val();
	
	var status="4";
	
		    if (ConsolidateId != '' || ConsolidateId!=undefined) {   
				$.ajax({
				      type: "POST",
				      contentType : 'application/json',
				      dataType : 'json',
				      url: "../master/ForwardToBeamsThroughWs/"+ConsolidateId+"/"+status,
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




$('#Delete').click(function() {
	var ConsolidateId = $("input:radio:checked").val();
	
	var status="12";
	
		    if (ConsolidateId != '' || ConsolidateId!=undefined) {
		    	swal({
		    		  title: "Are you sure?",
		    		  text: "to delete",
		    		  icon: "warning",
		    		  buttons: true,
		    		  dangerMode: true,
		    		})
		    		.then((willDelete) => {
		    		  if (willDelete) {
		    			  deleteBill(ConsolidateId,status);
		    		  } else {
		    		  //  swal("Your imaginary file is safe!");
		    		  }
		    		});
		    }
	});







function deleteBill(ConsolidateId,status){
	   
	$.ajax({
	      type: "POST",
	      contentType : 'application/json',
	      dataType : 'json',
	      url: "../master/ForwardToBeamsThroughWs/"+ConsolidateId+"/"+status,
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






$("#btnVoucherEntry").click(function() {
	
	var consBillId = $("input:radio:checked").val();
		    if (consBillId != '' &&  consBillId!=undefined ) {  
	$("#myModal").modal('show');
		    }else{
		    	 swal("Please select atleast one row", {
		    	      icon: "warning",
		    	    
		    	  });
		    }
});



$("#btnUpdate").click(function(e) {
			var consBillId =$("input:radio:checked").val();
			var voucherNo=$("#voucherNo").val();
			var voucherDate=$("#voucherDate").val();
			
			if (voucherNo == "" || voucherNo == "0") {
				e.preventDefault();
				swal("Please Enter Voucher Number");
			} else if (voucherDate == "" || voucherDate == "0") {
				e.preventDefault();
				swal("Please Enter Voucher Date");
			} 
			else
				{
				$
				.ajax({
					type : "GET",
					url : "../master/addVoucherDtls/"+consBillId+ "/" + voucherNo + "/" + voucherDate,
					async : false,
					error : function(data) {
						console.log(data);
					},
					 success: function(data){
				    	  console.log(data);
				    	  $("#myModal").modal('hide');
							 swal("Voucher Number and Voucher Date Updated Successfully !!!");
							 setTimeout(
										function() {
											location
													.reload(true);
										}, 1000);
							
						}
				
				});
				}
		});





$(".checkbox").click(function(){
	var isActive=$(this).attr("data");
	if(isActive=="7"){
		$("#saveAndForward").prop("disabled",true);
		$("#approve").prop("disabled",false);
		$("#btnVoucherEntry").prop("disabled",true);
	}else if(isActive=="9"){
		$("#btnVoucherEntry").prop("disabled",false);
		$("#approve").prop("disabled",true);
	}else if(isActive=="14"){
		$("#btnVoucherEntry").prop("disabled",true);
		$("#approve").prop("disabled",true);
	}/*else{
		$("#AbstractReport").prop("disabled",false);
	}*/
});



//search
$("#go").click(function(){
	var paybillYear=$("#paybillYear").val();
	var paybillMonth=$("#paybillMonth").val();
	var ddocode =$("#logUser").text().trim();
	var status=$("#status").val();
	
	
	if(paybillYear!='0'  && paybillMonth!='0' && status!='0'){
		
		$("#loaderMainNew").show();
		
	$.ajax({
		type : "GET",
		url : "../master/searchConsolidatedBillByYear/"+paybillYear+ "/" + paybillMonth + "/" + status + "/" +ddocode,
		async : false,
		error : function(data) {
			console.log(data);
			$("#loaderMainNew").hide();
		},
		success: function(data){
	    	  console.log(data);
	    	  
	    	  var dataTable=$('#save_data').dataTable();
	    	  dataTable.fnClearTable();
	    	
	    	  
	    	  $("#loaderMainNew").hide();
	    	  if(data.length>0){
	    		  for(var i=0;i<data.length;i++){
	    			  
	    			  var gpfConsBillId=data[i].gpfConsBillId;
	    			  var isActive=data[i].isActive;
	    			  var appType=data[i].appType;
	    			  var sancAmtBySuperitendent=data[i].sancAmtBySuperitendent;
	    			  var month=data[i].month;
	    			  var year=data[i].year;
	    			  
	    			  var chkBox='<input type="radio"  class="checkbox" id="checkboxid"  value="'+gpfConsBillId+'"  name="checkbox" data="'+isActive+'" />';
  		              var link='<a style="font-size: 12px;" target="_blank"  href="../master/MTR52/'+gpfConsBillId+'"  text="'+gpfConsBillId+'" id="conBIllId" >'+gpfConsBillId+'</a>';
  		              var appName="";
  		              if(appType=="1"){
  		            	appName="ADVANCE";
  		              }else if(appType=="2"){
  		            	appName="WITHDRAWAL";
  		              }else if(appType=="3"){
  		            	appName="FINAL WITHDRAWAL";
  		              }
  		              
  		              
  		              
  		              var label=""
						 
						var authNo="---";
						var Status="";
						 if(isActive=="7"){
							 Status="Consolidated Bill";
		  		              }else if(isActive=="8"){
		  		            	Status="Bill Deleted";
		  		              }else if(isActive=="9"){
		  		            	Status="Bill Approve";
		  		              }else if(isActive=="14"){
		  		            	Status="Bill Voucher Entry Done";
		  		           }
  		  
  		                    dataTable.fnAddData([chkBox,link,appName,sancAmtBySuperitendent,month,year,authNo,Status]);
	    			  
	    		  }
	    	  }
		}
	});
	}
	
});





