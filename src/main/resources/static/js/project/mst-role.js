$('#btnCancel').click(
	    function(){
	   	 var roleName=$('#roleName').val('');
	   	 var roleid=$('#roleId').val('');
	   	
	    });

var warningError;
if($('#language').val()=="en"){
	roleNameError="Please enter Role Name !!!";
	roleidError="Please enter role id !!!";
	warning="warning !";
}else{
	roleNameError="कृपया भूमिका का नाम दर्ज करें  !!!";
	roleidError="कृपया भूमिका आईडी दर्ज करें   !!!";
	warning="चेतावणी !";
}

$("form[name='mstRole']").validate({
    // Specify validation rules
    rules: {
    	roleName: "required",
    	roleDescription: "required",
  
    },
    // Specify validation error messages
    messages: {
    	roleName: "Please Enter Role Name",
    	roleDescription: "Please Enter Role Description",
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });

