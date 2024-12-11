jQuery(document).ready(function($) {
	$('.aadhar').attr("readonly", true); 
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
		row.find(".aadhar").prop("readonly",false);
	  }else{
		  row.find(".aadhar").prop("readonly",true);
	  }
	
	  
	});

