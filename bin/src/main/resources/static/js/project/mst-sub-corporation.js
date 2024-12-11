
function ConfirmDeleteRecord(subCorporationId,isActive) {
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
					      url: "../master/deleteSubCop/"+subCorporationId,
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
$(document).ready(function(){
    $(document).on('click', '.delete', function(){ 
    	 var subDepartmentId=$(this).attr("data");
         var isActive=$(this).attr("data-val");
         //alert(deptId+"-"+isActive);
         ConfirmDeleteRecord(subDepartmentId,isActive);
   });
});


$("form[name='mstSubCorporation']").validate({
    // Specify validation rules
    rules: {
    	subdepartmentCode:
    		{
    		required:true,
    		min:1
    		},
    		subCorporationNameEn:"required",
    		subCorporationNameMr:"required",
    		subCorporationShortName:"required",
  
    },
    // Specify validation error messages
    messages: {
    	subdepartmentCode: "Please Select Corporation Name",
    	subCorporationNameEn: "Please Enter Sub Corporation Name in English ",
    	subCorporationNameMr: "Please Enter Sub Corporation Name in Marathi",
    	subCorporationShortName:"Please Enter Sub Corporation Short  Name",
    	
    	
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });

function validateSubDepartmentName() {
	// alert('inside validateUIDUniqe');
	var subcorporation = document.getElementById("subCorporationNameEn").value;
	
	
	
	
		if (subcorporation != '') {
			$
					.ajax({
						type : "GET",
						url : "validateSubDepartmentName1/"+subcorporation,
						async : false,
						contentType : 'application/json',
						error : function(data) {
						},
						success : function(data) {
							var len = data.length;
							var checkFlag = data;
							if (checkFlag == 0) {
								$('#subCorporationNameEn').val(subcorporation);
								status = true;
							} else if (checkFlag > 0) {

								swal(subcorporation + ' Already Present in the system, Please enter the Different name !!!');

								document.getElementById("subCorporationNameEn").value = "";
								status = false;
							}
							return status;
						}
					});
		}
	}