jQuery(document).ready(function($) {
	$('.dcps').attr("readonly", true); 
	
	var varMessage = $("#message").val();
	if(varMessage != "" && varMessage != undefined) {
		swal(''+varMessage, {
  	      icon: "success",
  	  });
	}
});




$(document).ready(function() {
	
	
	$("table").on("change", ".checkbox", function() {
		  var row = $(this).closest("tr");
		  var chk =row.find(".checkbox").prop("checked");
		  
		  if(chk== true){
				row.find(".dcps").prop("readonly",false);
			  }else{
				  row.find(".dcps").prop("readonly",true);
			  }
		  
		  
		  row.find(".checkboxorg").val(chk);
		});
	
	
	
	$(".checkbox").click(function() {
		$(".checkboxorg").prop("checked", true);
	});
	$("#btnsubmit").click(function(event) {
		var flag=0;
    	var i=0;
		
		if ($(".checkbox:checked").length==0) {
			event.preventDefault();
			swal("Please Select any Record");
		}
		else{
			$(".checkboxorg").each(function(){
    			if(this.value=="true"){
    				var GPFNO=$("#dcpsNo"+i).val();
    				if(GPFNO==''){
    					addErrorClass($("#dcpsNo"+i),"Please Enter DCPS Number");
    					flag=1;
    				}
    			}else{
    				removeErrorClass($("#dcpsNo"+i));
    			}
    			i++;
    		});
			
			
			if(flag==1)
				event.preventDefault();
			
		
				}
    	});
});






$("table").on("change", ".allowCheckBox", function() {
	  var row = $(this).closest("tr");
	  var chk =row.find(".allowCheckBox").prop("checked");
	
	  
	  row.find(".checkbox").val(chk);
	});




/*
$("table").on("change", "#checkboxid", function() {
	  var row = $(this).closest("tr");
	  var chk =row.find("#checkboxid").prop("checked");
	 
	  
	  if(chk== true){
		row.find(".dcps").prop("readonly",false);
	  }else{
		  row.find(".dcps").prop("readonly",true);
	  }
	
	  
	});*/
$("form[name='updateDcpsNO']").validate(
		{

			ignore : ".ignore",

			// Specify validation rules
			rules : {
				
				dcpsNo : "required",
				
				checkboxid:
	    		{
	    		required:true,
	    		min:1
	    		},

			},

			messages : {

				dcpsNo : " Please enter Dcps No",
				checkboxid : "Please select check box"

			},
			submitHandler : function(form) {
				form.submit();
			}
		});