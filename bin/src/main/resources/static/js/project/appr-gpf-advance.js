
	$.validator.addMethod('maxpattern', function (value, element) {
		  return (parseInt(value)>0)
	}, 'File size must be less than {0} MB');
	
	$.validator.addMethod('minpattern', function (value, element) {
		  return (parseInt(value)>0)
	}, 'File size must be less than {0} MB');
	

$('form[id="apprGPFAdvanceForm"]').validate({  
	ignore:".ignore",
	    rules: {  
	      inwardNo: {
	    	  required: Number($("#roleId").val())==8, 
	      },
	      inwardDate:{
	    	  required: Number($("#roleId").val())==8,
	      },
	      sancAmtByAo: {
	    	  required: Number($("#roleId").val())==8, 
	    	  min:1,
	      },
	      aoRemark:{
	    	  required: Number($("#roleId").val())==8,
	      },
	      sancAmtByClerk: {  
//	    	  required:(Number($("#roleId").val())==9 && (Number($("#sancAmtByClerk").val())>Number($("#maximumAmtAdmissible").val()) || Number($("#sancAmtByClerk").val())<=0 || $("#sancAmtByClerk").val()!="0.0")),   
//	    	  max:Number($("#maximumAmtAdmissible").val()),
	    	  required:Number($("#roleId").val())==9,
	    	  min:1,
	      },  
	      
	      clerkRemark: {  
	    	  required:Number($("#roleId").val())==9,  
	      },  
	      sancAmtByAccountant: {  
//	    	  required:(Number($("#roleId").val())==10 && (Number($("#sancAmtByAccountant").val())>Number($("#maximumAmtAdmissible").val()) || Number($("#sancAmtByAccountant").val())<=0 || $("#sancAmtByAccountant").val()!="0.0")),  
//	    	  max:Number($("#maximumAmtAdmissible").val()),
	    	  required:Number($("#roleId").val())==10,
	    	  min:1,
	      },  
	      accountantRemark: {  
	    	  required: Number($("#roleId").val())==10,  
	      },  
	      sancAmtByFc: {  
	    	  required:(Number($("#roleId").val())==19 && (Number($("#sancAmtByFc").val())>Number($("#maximumAmtAdmissible").val()) || Number($("#sancAmtByFc").val())<=0 || $("#sancAmtByFc").val()!="0.0")),  
	    	  max:Number($("#maximumAmtAdmissible").val()),
	    	  min:1,
	      },  
	      fcRemark: {  
	    	  required: Number($("#roleId").val())==19,  
	      },  
	      SrclerkRemark: {  
	    	  required: Number($("#roleId").val())==18,  
	      },  
	      sancAmtBySuperitendent: { 
	    	  required:Number($("#roleId").val())==11,
	    	//  required: (Number($("#roleId").val())==11 && (Number($("#sancAmtBySuperitendent").val())>Number($("#maximumAmtAdmissible").val()) || Number($("#sancAmtBySuperitendent").val())<=0 || $("#sancAmtBySuperitendent").val()!="0.0")  && $("#isApprove").val()=="APPROVE"),  
	    	  //maxpattern:$("#isApprove").val()=="APPROVE"?Number($("#maximumAmtAdmissible").val()):0,
	    	 // minpattern:$("#isApprove").val()=="APPROVE"?1:0,
	    	  //  max:$("#isApprove").val()=="APPROVE"?Number($("#maximumAmtAdmissible").val()):0,
	    	  //min:$("#isApprove").val()=="APPROVE"?1:0,
	    	  min:1,
	      },  
	      superitendentRemark: {  
	    	  required: Number($("#roleId").val())==11,    
	      }, 
	    },  
	    messages: {  
	      inwardNo: 'Please Enter Inward Number',  
	      inwardDate: 'Please Select Inward Date',  
	      sancAmtByClerk: 'Please Enter Sanction Amount',  
	      clerkRemark: 'Please Enter remark',  
	      aoRemark: 'Please Enter remark',  
	      sancAmtByAccountant: 'Please Enter Sanction Amount',   
	      sancAmtByFc: 'Please Enter Sanction Amount',   
	      sancAmtByAo: 'Please Enter Sanction Amount',   
	      accountantRemark:'Please Enter remark',
	      sancAmtBySuperitendent: {
	    	  required:'Please Enter Sanction Amount',  
	    	//  minpattern:'min error',
	    	  //maxpattern:'max error',
	      },
	      superitendentRemark:{
	    	  required:'Please Enter remark',
	      },
	      fcRemark:{
	    	  required:'Please Enter remark',
	      },
	      SrclerkRemark:{
	    	  required:'Please Enter remark',
	      },
	    },  
	    submitHandler: function(form,event) {
	      var roleId=Number($("#roleId").val());
	      var flag=0;
	    switch(roleId){
	      case 9:
	    	 /* if(Number($("#sancAmtByClerk").val())>Number($("#maximumAmtAdmissible").val()) || Number($("#sancAmtByClerk").val())<=0 || $("#sancAmtByClerk").val()=="0.0"){
	    		  flag=1;
	    		  addErrorClass($("#sancAmtByClerk"),"Please enter Valid Sanction Amount");
	    	  }else{
	    		  removeErrorClass($("#sancAmtByClerk")); 
	    	  }*/
	    	  break;
	      case 10:
	    	 /* if(Number($("#sancAmtByAccountant").val())>Number($("#maximumAmtAdmissible").val()) || Number($("#sancAmtByAccountant").val())<=0 || $("#sancAmtByAccountant").val()=="0.0"){
	    		  flag=1;
	    		  addErrorClass($("#sancAmtByAccountant"),"Please enter Valid Sanction Amount");
	    	  }else{
	    		  removeErrorClass($("#sancAmtByAccountant")); 
	    	  }*/
	    	  break;
	      case 11:
	    	  /*if(Number($("#sancAmtBySuperitendent").val())>Number($("#maximumAmtAdmissible").val())){
	    		  flag=1;
	    		  addErrorClass($("#sancAmtBySuperitendent"),"Please enter Valid Sanction Amount");
	    	  }else if((Number($("#sancAmtBySuperitendent").val())<=0 || $("#sancAmtBySuperitendent").val()=="0.0") && $("#isApprove").val()=="APPROVE"){
	    		  flag=1;
	    		  addErrorClass($("#sancAmtBySuperitendent"),"Please enter Valid Sanction Amount");
	    	  }
	    	  else{
	    		  removeErrorClass($("#sancAmtBySuperitendent")); 
	    	  }*/
	    	  break;
	    }
	    if(flag==0){
	    	
	    	if($("#isApprove").val()=="REJECT"){
	    		ConfirmBeforeSubRecord("Want to Reject This Application  !",form,);
	    	}if($("#isApprove").val()=="APPROVE"){
	    		ConfirmBeforeSubRecord("Want to Forward To Next Level !",form,);
	    	}
	    	//form.submit();
	    }else{
	    	event.preventDefault();
	    }
	    }   
	  }); 


/*
function addErrorClass(element,msg){
	  var elementId=$(element).attr('id');
var errorMessageVisible = $("#"+elementId+"-error").is(":visible");
if (errorMessageVisible === false) {
	  element.after("<br><label id='"+elementId+"-error'  class='error' >"+msg+".</label>");
	  element.css("border-color", "red");
    console.log("can't find error");
  }
}

function removeErrorClass(element){
	var elementId=$(element).attr('id');
	 element.css("border-color", "");
	     var errorMessageVisible =  $("#"+elementId+"-error").is(":visible");
	     if (errorMessageVisible){
	        $("#"+elementId+"-error").remove();
	        console.log("can't find error");
	     }
}
*/


$(".checkSanctionAmt").blur(function(){
		/*if(Number($(this).val())>Number($("#maximumAmtAdmissible").val())){
			//alert("Maximum Admissible amount is less then or equal to requested amount");
			addErrorClass($(this),"Sanction amount should be less then or equal to Maximum Admissible amount !!!");
			$(this).val("");
		}else{
			removeErrorClass($(this));
		}*/
});

$("#reject").click(function(){
	
	
	$("#isApprove").val("REJECT");
	$("#sancAmtBySuperitendent").val("0.0");
	$("#sancAmtBySuperitendent").prop("readonly",true);
	
	$("#sancAmtByAo").val("0.0");
	$("#sancAmtByAo").prop("readonly",true);
	
	$("#sancAmtByFc").val("0.0");
	$("#sancAmtByFc").prop("readonly",true);
	
	$("#sancAmtByClerk").val("0.0");
	$("#sancAmtByClerk").prop("readonly",true);
	
	$("#sancAmtByAccountant").val("0.0");
	$("#sancAmtByAccountant").prop("readonly",true);
	
	
	
	$('#sancAmtByClerk').addClass("ignore");
	$('#sancAmtByAccountant').addClass("ignore");
	$('#sancAmtByAo').addClass("ignore");
	$('#sancAmtBySuperitendent').addClass("ignore");
	$('#payscalelevel').addClass("ignore");
	$('#payscalelevel').addClass("ignore");
	
	

		
	
	
});


$("#forwardToDDO").click(function(){
	$("#isApprove").val("APPROVE");
	$("#sancAmtBySuperitendent").prop("readonly",false);
	
/*
	$('#sancAmtByClerk').removeClass("ignore");
	$('#sancAmtByAccountant').removeClass("ignore");
	$('#sancAmtByAo').removeClass("ignore");
	$('#sancAmtBySuperitendent').removeClass("ignore");
	$('#payscalelevel').removeClass("ignore");
	$('#payscalelevel').removeClass("ignore");
	*/
	
});



$('#btnreftononref')
.click(
		function() {
			var gpfAdvanceid = $('#gpfAdvanceid').val();
			var sevaarthId=$('#sevaarthId').val();
			var context=$('#context').val();

			// alert(paybillGenerationTrnId);
			if (employeeId != '' && sevaarthId !='') {
				$
						.ajax({
							type : "GET",
							url : context+"/master/reftononref/"
									+ gpfAdvanceid + "/" + sevaarthId,
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
											"Refundable to Non-Refundable Successfully",
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
											"Refundable to Non-Refundable Successfully",
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


$(".sancAmtByClerkCheck").keyup(function(){
	var amo=$("#sancAmtByClerk").val();
	var maxval=$("#maximumAmtAdmissible").val();
	if(Number(amo)>Number(maxval)){
		$("#sancAmtByClerk").val("");
		 addErrorClass($("#sancAmtByClerk"),"Please enter Valid Sanction Amount");
	}else{
		removeErrorClass($(this));
	}
});
$(".sancAmtByFcupdate").keyup(function(){
	var amo=$("#sancAmtByFc").val();
	var maxval=$("#maximumAmtAdmissible").val();
	if(Number(amo)>Number(maxval)){
		$("#sancAmtByFc").val("");
		addErrorClass($("#sancAmtByFc"),"Please enter Valid Sanction Amount");
	}else{
		removeErrorClass($(this));
	}
});

	
	 
	  	  
	      

