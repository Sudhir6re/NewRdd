$(document).ready(function() {

	/*$("#bankBranchId").hide();
	$("#bankId").change(function() {
		$("#bankBranchId").hide();

		$(".bankId").val($("#bankId option:selected").val());
		var bankId = $("#bankId option:selected").val();
		if (bankId == "1") {
			$("#bankBranchId").show();
		} else {
			$("#bankBranchId").hide();
		}
	});*/
/*	 $('#bankBranchId').empty();
	 $("#bankId").change(function() 
    		 {
    		 	  var bankId = $("#bankId").val();
    		 //alert("bank  is "+bankId);
    		     	 if (bankId != '') 
    		     	 {
    		     		 $.ajax({
    		 			      type: "GET",
    		 			      url: "mstBank/"+bankId,
    		 			      async: true,
    		 			      contentType:'application/json',
    		 			      error: function(data){
    		 			    	 //console.log(data);
    		 			      },
    		 			      success: function(data){
    		 			    	 //console.log(data);
    		 			    	  //alert(data);
    		 			    	 var len=data.length;
    		 			    	  if(len!=0)
    		 			    		  {
    		 			    		//console.log(data);
    		 			    		 $('#bankBranchId').empty();
    		 			    		$('#bankBranchId').append("<option value='0'>Please Select</option>");
    		 				    	 var temp = data;
    		 				   		  $.each( temp, function( index, value ){
    		 					    		console.log( value[3] ); 
    		 					    		 $('#bankBranchId').append("<option  data-id="+value[6]+" value="+value[0]+">" + value[3] + "</option>");
    		 				    		});
    		 			    		  }
    		 			    	  else
    		 			    		  {
    		 			    		 $('#bankBranchId').empty();
    		 			    		 $('#bankBranchId').append("<option value='0'>Please Select</option>");
    		 			    		//  swal("Record not found !!!");
    		 			    		  }
    		 			    	}
    		 			 });	
    		     	 }
    		     	 
    		     	
    		     		
    		 });*/
     /*
     $("#bankBranchId").change(function() 
    		 {
    	 var ifsc=$('option:selected', this).attr('data-id');
    	    $('#ifscCode').val(ifsc);
    		 });
*/






$("#tblDataTable").on("change", ".bankId", function() {
	  var row = $(this).closest("tr");
	  var bankid =row.find(".bankId").val();

	  
	 var context=$("#context").val(); 
	  
		row.find(".bankBranchId").empty();
		
	 if (bankid != '') {
			$
					.ajax({
						type : "GET",
						//url : "fetchbankbranch/" + bankid,
					    url: context+"/master/mstBank/"+bankid,
						async : false,
						contentType : 'application/json',
						error : function(data) {
							// console.log(data);
						},
						success : function(data) { 
							var len = data.length;
							if (len != 0) {
								// console.log(data);
						
								row.find(".bankBranchId").append(
												"<option value='0'>Please Select</option>");
								var temp = data;
								$
										.each(
												temp,
												function(index,
														value) {
													console
															.log(value[1]);
													row.find(".bankBranchId").append("<option  data-id="+value[6]+" value="+value[0]+">" + value[3] + "</option>");
												});
						}
						}
					});
		} else {
			row.find(".bankBranchId").empty();
			row.find(".bankBranchId").append(
							"<option value='0'>Please Select</option>");
			swal("Record not found !!!");
		}
	});




//$(".bankBranchId").change(function() {
	
	$(document).on('change','.bankBranchId', function(event){
	  var row = $(this).closest("tr");
	var obj=row.find(".bankBranchId");
	 var ifsc=$('option:selected', obj).attr('data-id');
	    row.find(".ifscCode").val(ifsc);
	});

/*$("#btnsubmit").click(function(e) {
	alert("clicked");
			e.preventDefault();
			//var bankId = $('.bankId').val();
			var bankId = row.find(".bankId").val()
			var bankBranchId = $('.bankBranchId').val();
			var accountNumber = $('.accountNumber').val();
			
			if(bankId==0 || bankId==null){
				swal("plese enter bank details");
			}
			else if(bankBranchId==0 || bankBranchId==null){
				swal("plese enter branch details");
			}
			else if(accountNumber=='' || accountNumber==undefined ){
				swal("plese enter Account details");
			}
			else{
				$("#updatebankfm").submit();
			}
			
			
			
			   
			   

	 	 		  
   });

*/




$("form[name='updatebankfm']").validate({
    // Specify validation rules
    submitHandler: function(form,e) {
    	e.preventDefault();
    	var flag=0;
    	var i=0;
    	if($(".checkbox:checked").length==0){
    		swal("Please Select atleast 1 Record");
    		flag=1;
    	}else{
    		$(".checkbox").each(function(){
    			if(this.checked){
    				var bankId=$("#bankId"+i).val();
    				var bankBranchId=$("#bankBranchId"+i).val();
    				var accountNumber=$("#accountNumber"+i).val();
    				if(bankId=='0'){
    					addErrorClass($("#bankId"+i),"Please select bank");
    					flag=1;
    				}
    				if(bankBranchId=='0'){
    					addErrorClass($("#bankBranchId"+i),"Please select branch");
    					flag=1;
    				}
    				if(accountNumber==''){
    					addErrorClass($("#accountNumber"+i),"Please enter the account number");
    					flag=1;
    				}
    			}else{
    				removeErrorClass($("#bankId"+i));
    				removeErrorClass($("#bankBranchId"+i));
    				removeErrorClass($("#accountNumber"+i));
    			}
    			i++;
    		});
    	}
    	if(flag==0)
    		ConfirmBeforeSubRecord("Want to Forward To Next Level !",form);
    }
  });




});
/*
$("#bankBranchId").change(function(){
	var branchId=$(this).val();
		$.ajax({
			type : "GET",
			url : "../master/getIfscCodeByBranchId/"
					+ branchId,
			async : true,
			contentType : 'application/json',
			error : function(data) {
				 console.log(data);
			},
			success : function(data) {
				 console.log(data);
				var len = data.length;
				if (len != 0) { 
					$("#ifscCode").val(data[0].ifscCode);
			   }
			}
		});
     });
*/



jQuery(document).ready(function($) {
	$('.bankId').attr("readonly", true);
	$('.bankBranchId').attr("readonly", true);
	$('.accountNumber').attr("readonly", true);
	
	var varMessage = $("#message").val();
	if(varMessage != "" && varMessage != undefined) {
		swal(''+varMessage, {
  	      icon: "success",
  	  });
	}
});

$("table").on("change", "#checkboxid", function() {
	  var row = $(this).closest("tr");
	  var chk =row.find("#checkboxid").prop("checked");
	  
	  if(chk== true){
		row.find(".bankId").removeAttr('readonly');
		row.find(".bankBranchId").removeAttr('readonly');
		row.find(".accountNumber").prop("readonly",false);
	  }else{
		  row.find(".bankId").attr('readonly','readonly');
		  row.find(".bankBranchId").attr('readonly','readonly');
		  row.find(".accountNumber").prop("readonly",true);
	  }
});



$(document).on('blur','.page-link', function(event){
	  $(".bankId").attr('readonly','readonly');
	  $(".bankBranchId").attr('readonly','readonly');
	  $(".accountNumber").prop("readonly",true);
});


