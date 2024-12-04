var contextPath = $("#appRootPath").val();
jQuery(document).ready(function() {
	
	$("#selectAll").change(function(){
		if($(this).prop("checked")){
			  $(".employeeIds").prop("checked",true);
		}else{
			  $(".employeeIds").prop("checked",false);
		}
	});

	
	
$('#deleteDraftCase')
.click(
		function() {
			 var employeeIds = $('.employeeIds:checked').map(function() {
                 return $(this).val();
             }).get();

			 
		     if (employeeIds.length > 0) {
		    	 swal({
					  title: "Are you sure?",
					  text: "Delete Case Form !",
					  icon: "warning",
					  buttons: true,
					  dangerMode: true,
					}).then((willDelete) => {
	    if (willDelete) {   
				if (employeeIds.length > 0) {
					$( "#loaderMainNew").show();
					$
							.ajax({
								type : "DELETE",
								url : contextPath+"/ddoast/deleteDraftCase/",
								async : true,
								contentType : 'application/json',
								data: JSON.stringify(employeeIds),
								error : function(data) {
									console.log(data);
									$( "#loaderMainNew").hide();
								},
								success : function(data) {
									console.log(data);
									$( "#loaderMainNew").hide();
									 if (parseInt(data)==1) {
											swal(
													"Draft Case  has been deleted successfully",
													{
														icon : "success",
													});
											setTimeout(
													function() {
														location
																.reload(true);
													}, 2000);

				                        } else {
				                            alert("Employee Not belong to this ddo  ");
				                        }
								}
							});
				}
	    }
					})
			 }else{
				swal("Please select atleast one case for delete !!!"); 
			 }
		});


$('#viewDraftCase')
.click(
		function() {
			 var employeeIds = $('.employeeIds:checked').map(function() {
                 return $(this).val();
             }).get();
		     if (employeeIds.length > 1) {
		    	 swal("Please select only one case for View/Update Darft case !!!"); 
			 }else if (employeeIds.length==0) {
				swal("Please select atleast one case for delete !!!"); 
			 }else{
				  var link = $("#emp" + parseInt(employeeIds[0]));
			        if (link.length) {
			            window.location.href = link.attr('href');
			        } else {
			            swal("The selected case does not exist.");
			        }
			 }
		});
});

