jQuery(document).ready(function($) {
	$('.mobile').attr("readonly", true); 
	
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
		row.find(".mobile").prop("readonly",false);
	  }else{
		  row.find(".mobile").prop("readonly",true);
	  }
	
	  
	});

$("form[name='UpdateMobNo']").validate(
		{

			ignore : ".ignore",

			// Specify validation rules
			rules : {
				
				mobNo : "required",
				
				checkboxid:
	    		{
	    		required:true,
	    		min:1
	    		},

			},

			messages : {

				mobNo : " Please enter Mobile No",
				checkboxid : "Please select check box"

			},
			submitHandler : function(form) {
				form.submit();
			}
		});