function ConfirmDeleteRecord(departmentId,isActive) {
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
					      url: "../master/deleteDept/"+departmentId,
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






$(document).ready(function(){
    // Find and remove selected table rows
    $(document).on('click', '.delete', function(){ 
    	  
        var deptId=$(this).attr("data");
        var isActive=$(this).attr("data-val");
        //alert(deptId+"-"+isActive);
        ConfirmDeleteRecord(deptId,isActive);
  }); 
});  

$('#btnCancel').click(
	    function(){
	   	 var deptShortName=$('#departmentShortName').val('');
	   	 var deptNameEnglish=$('#departmentNameEn').val('');
	   	 var deptNameMarathi=$('#departmentNameMr').val('');
	    });

var countryNameError;
var stateNameError;
var districtNameError;
var talukaNameError;
var villageNameError;
var addressNameError;
var pinCodeError;
var locationNameError;

var warningError;
if($('#language').val()=="en"){
	deptCodeError="Please enter department code !!!";
	deptShortNameError="Please enter department short Code !!!";
	deptNameEnglishError="Please enter department name in english !!!";
	deptNameMarathiError="Please enter department name in marathi !!!";
	warning="warning !";
}else{
	deptCodeError="कृपया विभाग कोड प्रविष्ट करा  !!!";
	deptShortNameError="कृपया विभाग लहान नाव प्रविष्ट करा !!!";
	deptNameEnglishError="कृपया विभागाचे नाव इंग्रजीमध्ये प्रविष्ट करा !!!";
	deptNameMarathiError="कृपया मराठी मध्ये विभाग नाव प्रविष्ट करा !!!";
	warning="चेतावणी !";
}
function validateDepartmentName(deptNameEnglish) {
	 //alert("Method validate master department");
	if (deptNameEnglish!=null) {
		validateDepartmentName1();
		return (true)
	}
	document.getElementById("deptNameEnglish").value = "";k
	swal("You have entered an invalid Department Name ");
	return (false)
}


function validateDepartmentName1() {
	// alert('inside validateUIDUniqe');
	var deptname = document.getElementById("deptNameEnglish").value;
	
	
	
		if (deptname != '') {
			$
					.ajax({
						type : "GET",
						url : "validateDepartmentName/"+deptname,
						async : false,
						contentType : 'application/json',
						error : function(data) {
						},
						success : function(data) {
							var len = data.length;
							var checkFlag = data;
							if (checkFlag == 0) {
								$('#deptNameEnglish').val(deptname);
								status = true;
							} else if (checkFlag > 0) {

								swal(deptname + ' Already Present in the system, Please enter the Different name !!!');

								document.getElementById("deptNameEnglish").value = "";
								status = false;
							}
							return status;
						}
					});
		}
	}




function validateDepartmentName() {
	// alert('inside validateUIDUniqe');
	var deptname = document.getElementById("deptNameEnglish").value;
	
	
	
	if (deptname != '') {
		$
		.ajax({
			type : "GET",
			url : "../validateDepartmentName/"+deptname,
			async : false,
			contentType : 'application/json',
			error : function(data) {
			},
			success : function(data) {
				var len = data.length;
				var checkFlag = data;
				if (checkFlag == 0) {
					$('#deptNameEnglish').val(deptname);
					status = true;
				} else if (checkFlag > 0) {
					
					swal(deptname + ' Already Present in the system, Please enter the Different name !!!');
					
					document.getElementById("deptNameEnglish").value = "";
					status = false;
				}
				return status;
			}
		});
	}
}


$("form[name='mstDept']").validate({
    // Specify validation rules
    rules: {
    	departmentShortName: "required",
    	departmentNameMr: "required",
    	departmentNameEn: "required",
  
    },
    // Specify validation error messages
    messages: {
    	departmentShortName: "Please Enter Department Short Name",
    	departmentNameMr: "Please Enter Department Name in Marathi",
    	departmentNameEn: "Please Enter Department Name in English",
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });





