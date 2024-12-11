jQuery(document).ready(function($) {
	var varMessage = $("#message").val();
	if(varMessage != "" && varMessage != undefined) {
		swal(''+varMessage, {
  	      icon: "success",
  	  });
	}
});  


$(document).ready(function()
		   {
	   
	   // alert($("#isActive1").val());
	   
	   $('#isActive').val($("#isActive1").val());
	   
        $("#department_id")
		.change(
				function() {
					var departmentId = $("#department_id")
							.val();
					var checkAlreadyExists=checkIsDepartmentAlreadyExists(departmentId);
					
					//alert("in checkAlreadyExists"+checkAlreadyExists);
					if(checkAlreadyExists=='0'){
					 // alert("departmentId "+departmentId);
					if (department_id != '') {
						//alert("inside ifff !!");
						$
								.ajax({
									type : "GET",
									url : "../mstWorkFlowCharter/"
											+ departmentId,
									async : false,
									contentType : 'application/json',
									error : function(data) {
										// console.log(data);
									},
									success : function(data) {
										// console.log(data);
										// alert(data);
										var len = data.length;
										if (len != 0) {
											 console.log(data);
										// $('#btnSave').hide();
											   $('#btnSave').prop('disabled', true);
											   swal("Level already present please update it");
											
										} 
									
										else 
										{
											// $('#btnSave').show();
											   $('#btnSave').prop('disabled', false);
										}
									}
								});
					}
					}
					else 
					{
						//alert("inside else !!");
						swal("Already Saved !!");
						//$('#btnSave').hide();
						 /*  $('#btnSave').prop('disabled', true);*/
					}

				});

    });
   
   
   function ConfirmDeleteRecord(deptDDOApprID,isActive) {
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
						      url: "../master/deleteWorkFlowCharter/"+deptDDOApprID,
						      async: true,
						      error: function(data){
						    	  swal("Deleted successfully !", {
						    	      icon: "success",
						    	  });
						    	  setTimeout(function() {
									    location.reload(true);
									}, 3000);
						      },
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
   
   // added for edit

		   $("#editWorkFlowCharter").click(function(e) {
			var deptDDOApprID=$(this).attr("data");
			var isDataExists=0;
			//alert("In editWorkFlowCharter");
				$.ajax({
					type : "GET",
					url : "../master/EditWorkCharter/" + deptDDOApprID,
					async : true,
					contentType:'application/json',
					error : function(data) {
						console.log(data);
						alert(data);
					},
					success : function(data) {
						isDataExists=data.length;
					}
				  });
				
				if(isDataExists=="0")
					{
					swal("DDO is Already Mapped !");
					return false;
					
					//alert("In If condition");
					
					}
				else{
					//alert("In else condition");
					return true;
				}
				});
		   //For checking department already saved
		   function checkIsDepartmentAlreadyExists(departmentId) {
			   //alert("In checkIsDepartmentAlreadyExists function");
				var flag=0;
				$
				.ajax({
					type : "GET",
					url : "../master/checkIsDepartmentAlreadyExists/" + departmentId,
					async : false,
					contentType:'application/json',
					error : function(data) {
						console.log(data);
						alert(data);
					},
					success : function(data) {
						flag=data.length;
					}
				});
				return flag;
			}
		
		   $("form[name='mstWorkFlow']").validate({
			    // Specify validation rules
			    rules: {
			    	department_code:
			    	{
			    		required:true,
			    		min:1
			    	},
			    	level:
			    	{
			    		required:true,
			    		min:1
			    	},
			    	
			    },
			    // Specify validation error messages
			    messages: {
			    	department_code: "Please Select Department",
			    	level: "Please Select NO of Levels",
			    },
			    // Make sure the form is submitted to the destination defined
			    // in the "action" attribute of the form when valid
			    submitHandler: function(form) {
			      form.submit();
			    }
			  });
		
		   
		