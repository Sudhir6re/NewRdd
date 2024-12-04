$(document).ready(function(){
	
	
	var selecteditems  = [];
	
	
	     
     $("#btnSave").click(function(){
    	 
   	 	 var ddoCode = $('#ddo_code').val();
   	 	 var sevaarthId = $('#sevaarthId').val(); 
   	
    	 
    		 $.ajax({
			      type: "GET",
			      url: "../mapping/mapEmployee/"+sevaarthId+"/"+ddoCode,
			      async: true,
			      contentType:'application/json',
			      error: function(data){
			    	  console.log(data);
			    	  alert(data);
			      },
			      success: function(data){
			   
			    	 location.reload(true);
			    	 swal("Employee Attach Successfuly to DDO");
			    	
			    	 
			    }
			 });	
    		 
    	 
    	  });
   
    /* 
     */
     $(".delete").click(function(){
    	   var sevaarthId=$(this).attr('value');
    	   alert("hi" + sevaarthId);
    	   swal({
    		   title: "Are you sure?",
    		   text: " to Dettach Employee!",
    		   icon: "warning",
    		   buttons: true,
    		   dangerMode: true,
    		 })
    		 .then((willDelete) => {
    		   if (willDelete) {
    			   
    			   
    			     $.ajax({
    				      type: "GET",
    				      url: "../mapping/dettachEmployee/"+sevaarthId,
    				      async: true,
    				      error: function(data){
    				    	  console.log(data);
    				    	 /* alert("eror is"+data);*/
    				      },
    				      success: function(data){
    				    	   swal("Dettach Successfully....!", {
    				    	       icon: "success",
    				    	     });
    				    	   setTimeout(function() {
    							    location.reload(true);
    							}, 3000);
    				      }
    				 });
    		   } else {
    		     swal("Dettach Cancelled...!");
    		   }
    		 });
    	   
    	   
    	  });

 });

$("form[name='empmap']").validate({
    // Specify validation rules
    rules: {
    	ddoCode:{
    		required: true,
    		min:1
    	},
    	sevaarthId:{
    		required: true,
    		min:1
    	},
    	
  
    },
    // Specify validation error messages
    messages: {
    	ddoCode: "Please Select Ddo Code",
    	sevaarthId: "Please Select Sevaarth Id",
    	
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });