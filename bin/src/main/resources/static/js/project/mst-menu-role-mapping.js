jQuery(document).ready(function($) {
	
	var varMessage = $("#message").val();
	var varAlreadyPresent = $("#alreadyPresent").val();
	if(varAlreadyPresent != "" && varAlreadyPresent=='ALLREADYEXISTS') {
		swal(''+varMessage, {
  	      icon: "warning",
  	  });
	}
	if(varMessage != "" && varMessage != undefined && varAlreadyPresent != 'ALLREADYEXISTS') {
		swal(''+varMessage, {
			icon: "success",
		});
	}
});

function ConfirmDeleteRecord(menuKey,roleKey,isActive) {
	if(isActive==1) {
		$.ajax({
		        type: "GET",
			    url: "../master/checkSubMenuExistsByMenuAndRoleKey/"+menuKey+"/"+roleKey,
			    async: false,
			   
		        success: function (data) {
		        if(parseInt(data) > 0) {
        		swal({
        			 title: "Not Allowed ! ",
       			  	 text: "Sub Menu Found Against This Menu And Role !",
       			  	 icon: "warning",
	  				 timer: 3000
		  			});
	        }  else {
	    		swal({
	    			  title: "Are You Sure ?",
	    			  text: "Status of this record will be InActive !",
	    			  icon: "warning",
	    			  buttons: true,
	    			  dangerMode: true,
	    			}).then((willDelete) => {
	    			    if (willDelete) {   
	    					$.ajax({
	    					      type: "GET",
	    					      url: "../master/deleteMenuAndRoleMapping/"+menuKey+"/"+roleKey,
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
      	}
		        }
		});
	} else {
		 swal({
	    	  title: 'Not allowed !',
	    	  text: 'This record is already deleted',
			  icon: "warning",
	    });
	}
}


$("form[name='mstMenuRole']").validate({
    // Specify validation rules
    rules: {
    	menuName:{
    		required: true,
    		min:1
    	},
    	roleName:{
    		required: true,
    		min:1
    	},
  
    },
    // Specify validation error messages
    messages: {
    	roleName: "Please select Role Name",
    	menuName: "Please select Menu Name",
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });