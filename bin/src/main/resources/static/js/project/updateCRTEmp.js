jQuery(document).ready(function($) {
/*	$('.crtId').attr("readonly", true); */
	
	var varMessage = $("#message").val();
	if(varMessage != "" && varMessage != undefined) {
		swal(''+varMessage, {
  	      icon: "success",
  	  });
	}
});




$(document).ready(function() {
	
	/*
	$("table").on("change", ".checkbox", function() {
		  var row = $(this).closest("tr");
		  var chk =row.find(".checkbox").prop("checked");
		  
		  if(chk== true){
				row.find(".crtId").prop("readonly",false);
			  }else{
				  row.find(".crtId").prop("readonly",true);
			  }
		  
		  
		  row.find(".checkboxorg").val(chk);
		});
	*/
	
	
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
    				var CRTNO=$("#crtId"+i).val();
    				if(CRTNO=='' || CRTNO==undefined ){
    					addErrorClass($("#crtId"+i),"Please Select Employee Type");
    					flag=1;
    				}
    			}else{
    				removeErrorClass($("#crtId"+i));
    			}
    			i++;
    		});
			
			
			if(flag==1)
				event.preventDefault();
			
		
				}
    	});
});
