jQuery(document).ready(function($) {
	var varMessage = $("#message").val();
	if(varMessage != "" && varMessage != undefined) {
		swal(''+varMessage, {
  	      icon: "success",
  	  });
	}
});

function ConfirmDeleteRecord(key,isActive) {
	if(isActive==1) {
		$.ajax({
		        type: "GET",
			    url: "../master/checkSubMenuExists/"+key,
			    async: false,
			   
		        success: function (data) {
		        if(parseInt(data) > 0) {
        		swal({
        			 title: "Not Allowed ! ",
       			  	 text: "Sub Menu Found Against This Menu !",
       			  	 icon: "warning",
	  				 timer: 3000
		  			});
	        }  else {
	        	$.ajax({
	        	    type: "GET",
	        	    url: "../master/checkMenuRoleMappingExists/"+key,
	        	    async: false,
	        	   
	        	    success: function (data) {
	        	    if(parseInt(data) > 0) {
	        		swal({
	        			 title: "Not Allowed ! ",
	        			  	 text: "Menu And Role Mapping Found Against This Menu !",
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
		    					      url: "../master/deleteMenu/"+key,
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



$("form[name='mstMenu']").validate({
    // Specify validation rules
    rules: {
    	menuNameEnglish: "required",
    	menuNameMarathi: "required",
  
    },
    // Specify validation error messages
    messages: {
    	menuNameEnglish: "Please Enter Menu Name",
    	menuNameMarathi: "Please Enter Menu Name Marathi",
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });
