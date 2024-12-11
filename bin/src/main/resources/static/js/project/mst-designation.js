$(".alphacharspace").keyup(function(e) {
	var str = $(this).val();
	var replacestr = str.replace(/[^a-zA-Z\s]+/g, '');
	$(this).val(replacestr);
	});
$("#desgCode" ).keypress(function(e) {
    var key = e.keyCode;
    if (event.keyCode < 48 || event.keyCode > 57 && event.keyCode > 36 || event.keyCode < 46) {
        event.preventDefault(); 
    }
});

$("#cadreGroup").hide();
jQuery(document).ready(function($) {
	var varMessage = $("#message").val();
	if(varMessage != "" && varMessage != undefined) {
		swal(''+varMessage, {
  	      icon: "success",
  	  });
	}
});


$("#cadreCode").change(function(){
	var cadreCode=$(this).val();
	if(parseInt(cadreCode)==2){
		$("#cadreGroup").show();
	}else{
		$("#cadreGroup").hide();
	}
});



function ConfirmDeleteRecord(designationId,isActive) {
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
					      url: "../mdc/deleteDesg/"+designationId,
					      async: true,
					      error: function(data){
					    	  console.log(data);
					    	  swal("Deleted successfully error !", {
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






$('#btnCancel').click(
	    function(){
	   	 var deptShortName=$('#designation').val('');
	   	 var deptNameEnglish=$('#designationShortName').val('');
	    });


var warningError;
if($('#language').val()=="en"){
	//departmentName="Please Select the Department Name !!"
	designationNameError="Please Enter Designation Name !!";
	designationShortNameError="Please Enter Designation short name !!";
	warning="warning !";
}else{
	//departmentName="कृपया विभागाचे नाव निवडा  !!";
	subdeptShortNameError="कृपया पदनाम प्रविष्ट करा !!";
	subdeptNameError="कृपया पदनाम  लहान नाव प्रविष्ट करा  !!";
	warning="चेतावणी !";
}
function validateDesignationName() {
	// alert('inside validateDesignationName');
	var desgname = document.getElementById("designation").value;
	
	
	
		if (desgname != '') {
			$
					.ajax({
						type : "GET",
						url : "validateDesignationName/"+desgname,
						async : false,
						contentType : 'application/json',
						error : function(data) {
							swal("error");
						},
						success : function(data) {
							var len = data.length;
							var checkFlag = data;
							if (checkFlag == 0) {
								$('#designation').val(desgname);
								status = true;
							} else if (checkFlag > 0) {

								swal(desgname + ' Already Present in the system, Please enter the Different name !!!');

								document.getElementById("designation").value = "";
								status = false;
							}
							return status;
						}
					});
		}
	}


$("form[name='mstDesg']").validate({
    // Specify validation rules
    rules: {
    	cadreCode:{
    		required: true,
    		min:1
    	},
    	designation: "required",
    	designationShortName: "required",
  
    },
    // Specify validation error messages
    messages: {
    	cadreCode:"Please Select Cadre",
    	designation: "Please Enter Designation Name",
    	designationShortName: "Please Enter Designation Short Name",
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });






