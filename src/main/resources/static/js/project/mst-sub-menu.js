jQuery(document).ready(function($) {
	
	$('#role_key').attr("disabled", true); 
	$('#menu_key').attr("disabled", true); 
	
	var varMessage = $("#message").val();
	if(varMessage != "" && varMessage != undefined) {
		swal(''+varMessage, {
  	      icon: "success",
  	  });
	}
});

function ConfirmDeleteRecord(subMenuId,menuCode,roleId,isActive) {
	if(isActive==1){
		swal({
			  title: "Are you sure?",
			  text: "Status of this record will be InActive !",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			}).then((willDelete) => {
			    if (willDelete) {   
					$.ajax({
					      type: "GET",
					      url: "/master/deleteSubMenu/"+subMenuId+"/"+menuCode+"/"+roleId,
					      async: true,
					      success: function(data){
					    	  swal("Deleted successfully !", {
					    	      icon: "success",
					    	  });
					    	  setTimeout(function() {
								    location.reload(true);
								}, 3000);
					      }
					 });
			     }
		})
	} else if(isActive==0) {
		 swal({
	    	  title: 'Not allowed !',
	    	  text: 'This record is already deleted',
			  icon: "warning",
	    });
	}
}


$("form[name='mstSubMenu']").validate({
    // Specify validation rules
    rules: {
    	roleName:{
    		required: true,
    		min:1
    	},
    	menuName:{
    		required: true,
    		min:1
    	},
    	subMenuEnglish: "required",
    	subMenuMarathi: "required",
    	controllerName: "required",
    	linkName: "required",
  
    },
    // Specify validation error messages
    messages: {
    	roleName: "Please Select Role",
    	menuName: "Please Select Menu",
    	subMenuEnglish: "Please Enter Menu Name English",
    	subMenuMarathi: "Please Enter Menu Name Marathi",
    	controllerName: "Please Enter Controller Name",
    	linkName: "Please Enter Link Name",
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });
  
  
  
  
  
  
  
  
  
  
  $("#roleName").change(function(){
	var roleId = $("#roleName").val();
	GetUrl(roleId);
	});


  $("#roleId").change(function(){
  	var roleId = $("#roleId").val();
	GetUrl(roleId)
	});
  	
	function GetUrl(roleId){
		var context = $("#appRootPath").val();
		$( "#loaderMainNew").show();
		  	$.ajax({
		  	      type: "POST",
		  	      url: context+"/master/findRoleWiseUrl/"+roleId,
		  	      async: false,
		  	      contentType:'application/json',
		  	      error: function(data){
		  	    	  console.log(data);
		  	      },
		  		  	beforeSend : function(){
		  				$( "#loaderMainNew").show();
		  				},
		  			complete : function(data){
		  				$( "#loaderMainNew").hide();
		  			},	
		  	      success: function(data){
		  			console.log(data);
		  		if(data){
		  			$('#url').text(data.roleDescription);
					
		  		}
		  			
		  	     }
		  	 });
	}
	
	
	
	$("#url").click(function(){
		$("#linkName").val($("#url").text());
		});
  	
