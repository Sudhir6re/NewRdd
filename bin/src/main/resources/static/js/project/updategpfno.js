jQuery(document).ready(function($) {
	$('.GPFNO').attr("readonly", true); 
	var varMessage = $("#message").val();
	if(varMessage != "" && varMessage != undefined) {
		swal(''+varMessage, {
  	      icon: "success",
  	  });
	}
});

$(document).ready(function() {
$("table").on("change", ".checkboxid", function() {
	  var row = $(this).closest("tr");
	  var chk =row.find(".checkboxid").prop("checked");
	 
	  
	  if(chk== true){
		row.find(".GPFNO").prop("readonly",false);
	  }else{
		  row.find(".GPFNO").prop("readonly",true);
	  }
	
	  
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
				var GPFNO=$("#GPFNO"+i).val();
				if(GPFNO==''){
					addErrorClass($("#GPFNO"+i),"Please Enter GPF Number");
					flag=1;
				}
			}else{
				removeErrorClass($("#GPFNO"+i));
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
	row.find(".GPFNO").prop("readonly",false);
  }else{
	  row.find(".GPFNO").prop("readonly",true);
  }

  
  row.find(".checkboxorg").val(chk);
});


