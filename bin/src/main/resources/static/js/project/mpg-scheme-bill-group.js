$(document)
		.ready(
				function() {
					
					
					var varMessage = $("#message").val();
					

					if (varMessage != "" 
							&& varMessage != undefined && varMessage=="SUCCESS") {
						swal("Record Mapped Successfully!", {
							icon : "success",
						});
					}
 
				

function ConfirmDeleteRecord(schemebillGroupId,isActive) {
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
					      url: "../master/deleteMpgSchemeBillGroup/"+schemebillGroupId,
					      async: true,
					      success: function(data){
					    	  swal("Deleted successfully !", {
					    	      icon: "success",
					    	  });
					    	  setTimeout(function() {
								    location.reload(true);
								}, 300);
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
$("form[name='mstSchemeMpg']").validate({
    // Specify validation rules
    rules: {
    	billGroupId:
    		{
    		required:true,
    		min:1
    		},
    		//billDescription:"required",
    		/*schemeId:
    			{
    			required:true,
        		min:1
    			},*/
    			/*schemeCode:
    				{
    				required:true,
    				//min:1
    				},*/
    				ddoMapId:
    					{
    					required:true,
    					min:1
    					},
    					
    },
    // Specify validation error messages
    messages: {
    	billGroupId: "Please Select Bill Group Name",
    	//billDescription: "Please Enter Bill Description ",
    //	schemeId: "Please Select Scheme Name ",
    	//schemeCode:"Please Enter Scheme Code",
    	ddoMapId:"Please Select DDO Workflow Charter",
    	
    	
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
     
    }
  });
$('#btnReset').click(
	    function(){
	    	$("#billGroupid").select2("val","0");
	    	$("#schemeName").select2("val","0");
	    });
});



