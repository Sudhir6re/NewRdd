jQuery(document).ready(function($) {
	var varMessage = $("#message").val();
	if(varMessage != "" && varMessage != undefined) {
		swal(''+varMessage, {
  	      icon: "success",
  	  });
	}
});

function ConfirmDeleteRecord(id) {
	if(id != null) {
		    		swal({
		    			  title: "Are You Sure ?",
		    			  text: "you want to delete it from record !",
		    			  icon: "warning",
		    			  buttons: true,
		    			  dangerMode: true,
		    			}).then((willDelete) => {
		    			    if (willDelete) {   
		    					$.ajax({
		    					      type: "POST",
		    					      url: "../mdc/deleteComponentParameter/"+id,
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
	        	}else {
		 swal({
	    	  title: 'Not allowed !',
	    	  text: 'This record is already deleted',
			  icon: "warning",
	    });
	        	    }
	     }

$("form[name='componentParameterMst']").validate({
    // Specify validation rules
    rules: {
    	componentName: "required", 
    },
    // Specify validation error messages
    messages: {
    	componentName: "Please Enter ComponentParameter Name",

    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });