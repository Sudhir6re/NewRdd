
function ConfirmDeleteRecord(subDepartmentId,isActive) {
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
					      url: "../master/deleteSubDept/"+subDepartmentId,
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



$('#btnCancel').click(
	    function(){
	   	 var deptShortName=$('#subDepartmentNameEn').val('');
	   	 var deptNameEnglish=$('#subDepartmentShortName').val('');
	    });


var warningError;
if($('#language').val()=="en"){
	departmentName="Please Select the Department Name !!"
	subdeptNameError="Please Enter Sub Department Name !!";
	subdeptShortNameError="Please Enter Sub Department short name !!";
	subdeptNameMrError="Please Enter Sub Department Marathi name !!";
	warning="warning !";
}else{
	departmentName="कृपया विभागाचे नाव निवडा  !!";
	subdeptNameError="कृपया विभागाचे नाव इंग्रजीमध्ये प्रविष्ट करा  !!";
	subdeptShortNameError="कृपया विभाग लहान नाव प्रविष्ट करा  !!";
	subdeptNameMrError="कृपया उपविभाग मराठी नाव प्रविष्ट करा !!";
	warning="चेतावणी !";
}
/*
$('#btnSave1').click(
	    function(){
	    	Swal.fire("Record Update Successfully.");
	    });*/


function validateSubDepartmentName() {
	// alert('inside validateUIDUniqe');
	var subdeptname = document.getElementById("subDepartmentNameEn").value;
	
	
	
		if (subdeptname != '') {
			$
					.ajax({
						type : "GET",
						url : "validateSubDepartmentName/"+subdeptname,
						async : false,
						contentType : 'application/json',
						error : function(data) {
						},
						success : function(data) {
							var len = data.length;
							var checkFlag = data;
							if (checkFlag == 0) {
								$('#subDepartmentNameEn').val(subdeptname);
								status = true;
							} else if (checkFlag > 0) {

								swal(subdeptname + ' Already Present in the system, Please enter the Different name !!!');
								document.getElementById("subDepartmentNameEn").value = "";
								status = false;
							}
							return status;
						}
					});
		}
	}
	
	

//validate subdepartment short name

function validateSubDepartmentShortName() {
	// alert('inside validateUIDUniqe');
	var subdeptshortname = document.getElementById("subDepartmentShortName").value;
	
	//alert("subdeptshortname"+subdeptshortname);
	
		if (subdeptshortname != '') {
			$
					.ajax({
						type : "GET",
						url : "validateSubDepartmentShortName/"+subdeptshortname,
						async : false,
						contentType : 'application/json',
						error : function(data) {
						},
						success : function(data) {
							var len = data.length;
							var checkFlag = data;
							if (checkFlag == 0) {
								$('#subDepartmentShortName').val(subdeptshortname);
								status = true;
							} else if (checkFlag > 0) {

								swal(subdeptshortname + ' Already Present in the system, Please enter the Different name !!!');
								document.getElementById("subDepartmentShortNameEn").value = "";
								status = false;
							}
							return status;
						}
					});
		}
	}
	
$("form[name='mstCorporation']").validate({
    // Specify validation rules
    rules: {
    	departmentCode:{
        		required: true,
        		min:1
        	},
    		subDepartmentNameEn: "required",
    		subDepartmentShortName: "required",
    		subDepartmentNameMr: "required",
  
    },
    // Specify validation error messages
    messages: {
    	departmentCode: "Please Select Department Name",
    	subDepartmentNameEn: "Please Enter Corporation Name in English ",
    	subDepartmentShortName: "Please Enter Corporation Short Name ",
    	subDepartmentNameMr: "Please Enter Corporation Name in Marathi",
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
      $("#loaderMainNew").show();
    }
  });




/*<button id="btncancel2" class="float-left submit-button" ></button>
<script type="text/javascript">
document.getElementById("btncancel2").onclick = function () {
    location.href = "master/mstSubDepartment";
};
</script>
*/






