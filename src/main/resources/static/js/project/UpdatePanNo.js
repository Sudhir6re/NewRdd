jQuery(document).ready(function($) {
	$('.pan').attr("readonly", true); 
	
	var varMessage = $("#message").val();
	if(varMessage != "" && varMessage != undefined) {
		swal(''+varMessage, {
  	      icon: "success",
  	  });
	}
	
	
/*	$(".checkbox").click(function() {
		
		if($(this).prop("checked")){
			$(this).prop("checked", true);
		}
		else{
			$(this).prop("checked", false);
		}
		
		
	});*/
	$("#btnsubmit").click(function(event) {
		var flag=0;
		var i=0;
		
		if ($(".checkbox:checked").length==0) {
			event.preventDefault();
			swal("Please Select any Record");
		}
		else{
			$(".checkbox").each(function(){
				if(this.value=="true"){
					var PANNO=$("#panNo"+i).val();
					if(PANNO==''){
						addErrorClass($("#panNo"+i),"Please Enter Pan Number");
						flag=1;
					}
				}else{
					removeErrorClass($("#panNo"+i));
				}
				i++;
			});
			
			
			if(flag==1)
				event.preventDefault();
			
		
				}
		});
	
});

$("table").on("change", ".checkbox", function() {
	  var row = $(this).closest("tr");
	  var chk =row.find(".checkbox").prop("checked");
	 
	  
	  if(chk== true){
		row.find(".pan").prop("readonly",false);
	  }else{
		  row.find(".pan").prop("readonly",true);
	  }
	
	  
	});




/*$("form[name='updatePAN']").validate({
    // Specify validation rules
    rules: {
    	 panNo:{
		    	required: true,
		    	//digits: false,
		    	pattern: /^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$/,
		    	//regex: /^[+-]{1}[0-9]{1,3}\-[0-9]{10}$/,
		    	minlength: 10,
		    	maxlength: 10, 
		    },
  
    },
    // Specify validation error messages
    messages: {
    	 panNo:{
		    	required : "Please enter Pan No",
				minlength : "Pan No should be atleast 10 Digit long.",
				pattern : "Please Enter Valid Pan No ",
		    },
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
    	      form.submit();
    
    }
  });*/

$(".pan").blur(function(){
	
	var panNo=$(this).val();
	var panRules =  /^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$/;
		if(!panRules.test(panNo)){
			swal("Please enter valid PAN No");
		}
});
