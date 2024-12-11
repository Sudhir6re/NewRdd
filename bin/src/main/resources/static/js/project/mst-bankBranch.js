function ConfirmDeleteRecord(bankBranchId,isActive) {
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
					      url: "../master/deleteBranch/"+bankBranchId,
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
    	  
        var bankBranchId=$(this).attr("data");
        var isActive=$(this).attr("data-val");
        // alert(deptId+"-"+isActive);
        ConfirmDeleteRecord(bankBranchId,isActive);
  }); 
});  

$('#btnCancel').click(
	    function(){
	   	 var bankBranchName=$('#bankBranchName').val('');
	   	 var bankBranchShortName=$('#bankBranchShortName').val('');
	   	 var ifscCode=$('#ifscCode').val('');
	   	 var micrCode=$('#micrCode').val('');
	   	 var bankBranchAddress=$('#bankBranchAddress').val('');
	    });



var warningError;
if($('#language').val()=="en"){
	bankname="Please select bank name !!!";
	branchname="Please enter branch name !!!";
	branchshortname="Please enter bank short name  !!!";
	ifsccode="Please enter IFSC code !!!";
	micrnumber="Please enter MICR number !!!";
	branchaddres="Please enter Bank Branch Address !!!";
	warning="warning !";
}else{
	bankname="कृपया बँकेचे नाव निवडा  !!!";
	branchname="कृपया शाखेचे नाव प्रविष्ट करा !!!";
	branchshortname="कृपया बँक शॉर्ट नाव प्रविष्ट करा !!!";
	ifsccode="कृपया आयएफएससी कोड प्रविष्ट करा !!!";
	micrnumber="कृपया एमआयसीआर क्रमांक प्रविष्ट करा !!!";
	branchaddres="कृपया बँकेच्या शाखेचा पत्ता प्रविष्ट करा!!!";
	warning="चेतावणी !";
}

jQuery(document).ready(function($) {
	// alert("qwe");
	var varMessage = $("#message").val();
	// alert("varMessage in if----"+varMessage);

	if (varMessage != "" && varMessage != undefined) {
		swal.fire("Record Updated Successfully..!!");
	}
});

$("form[name='addBranch']").validate({
    // Specify validation rules
    rules: {
    	bankid:{
    		required:true,
    		min:1
    		},
    	bankCode:
    		{
    		required:true,
    		min:1
    		},
    		bankBranchName: "required",
    		bankBranchShortName: "required",
    		ifscCode: "required",
    		micrCode: "required",
    		bankBranchAddress: "required",
    		
  
    },
    // Specify validation error messages
    messages: {
    	bankid: "Please Select Bank",
    	bankCode: "Please Select Bank",
    	bankBranchName: "Please Enter Branch Name",
    	bankBranchShortName: "Please Enter Branch Short Name",
    	ifscCode: "Please Enter IFSC Code",
    	micrCode: "Please Enter MICR Code",
    	bankBranchAddress: "Please Enter Branch Address",
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });
 
function validateBankBranchName1() {
	// alert('inside validateUIDUniqe');
	var bankbranchname = document.getElementById("bankBranchName").value;
	var bankcode = document.getElementById("bankCode").value;
	
	
	
		if (bankbranchname != '') {
			$
					.ajax({
						type : "GET",
						url : "validateBankBranchName/"+bankbranchname+"/"+bankcode,
						async : false,
						contentType : 'application/json',
						error : function(data) {
						},
						success : function(data) {
							var len = data.length;
							var checkFlag = data;
							if (checkFlag == 0) {
								$('#bankBranchName').val(bankbranchname);
								status = true;
							} else if (checkFlag > 0) {

								swal(bankbranchname + ' Already Present in the system, Please enter the Different name !!!');

								document.getElementById("bankBranchName").value = "";
								status = false;
							}
							return status;
						}
					});
		}
	}

function validateIFSCCode() {
	// alert('inside validateUIDUniqe');
	var bankcode = document.getElementById("bankid").value;
	var ifscCode = document.getElementById("ifscCode").value;
	
	
	
		if (ifscCode != '') {
			$
					.ajax({
						type : "GET",
						url : "validateIFSCCode/"+bankcode+"/"+ifscCode,
						async : false,
						contentType : 'application/json',
						error : function(data) {
						},
						success : function(data) {
							var len = data.length;
							var checkFlag = data;
							if (checkFlag == 0) {
								$('#ifscCode').val(ifscCode);
								status = true;
							} else if (checkFlag > 0) {

								swal(ifscCode + ' Already Present in the system, Please enter the Different IFSC !!!');

								document.getElementById("ifscCode").value = "";
								status = false;
							}
							return status;
						}
					});
		}
	}

