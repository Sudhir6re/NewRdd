jQuery(document).ready(function($) {
	$(".LockOn").hide();
	
	
	var varMessage = $("#message").val();
	var varErrorCode = $("#errorCode").val();
	
	if (varMessage != "" && varMessage != undefined && varErrorCode != "" && varErrorCode == 'SUCCESS') {
		swal('' + varMessage, {
			icon : "success",
		});
	}
	if (varMessage != "" && varMessage != undefined && varErrorCode != "" && varErrorCode == 'ERROR') {
		swal('' + varMessage, {
			icon : "error",
		});
	}
});


$('#newPassword').change(function () {
	var oldPassword = $("#password").val();
	var newPassword = $("#newPassword").val();
	if(oldPassword == newPassword){
		$("#newPassword").val("");
		swal("New password must be different than old password.");
	}
    if ($('#newPassword').val()) {
        if ($('#newPassword').val().length < 8 || $('#newPassword').val().length > 15) {
            swal({
            	title: "Password Length should be minimum 8 characters and maximum 15 characters.",
            	type: "warning",
            	icon: "warning",
            	timer: 5000
            });
            $('#newPassword').focus();
            $('#newPassword').val('');
            return false;
        }
        else if (validPassword($('#newPassword').val()) == false) {
            swal({
            	title: "Password must contain atleast 1 UpperCase Alphabet, 1 LowerCase Alphabet, 1 Number and 1 Special Character.",
            	type: "warning",
            	icon: "warning",
            	timer: 5000
            });
            $('#newPassword').focus();
            $('#newPassword').val('');
            return false;
        }
        else if ($('#newPasswordConfirm').val()) {
            if ($('#newPassword').val() != $('#newPasswordConfirm').val()) {
                swal({
	            	title: "New Password and Confirm Password does not match.",
	            	type: "warning",
	            	icon: "warning",
	            	timer: 5000
	            });
                $('#newPasswordConfirm').val('');
                $('#newPasswordConfirm').focus();
                return false;
            }
        }
    }
});
///////////////////////////////////////////////////////////////////
$('#newPasswordConfirm').change(function () {
	var oldPassword = $("#newPasswordConfirm").val();
	var newPassword = $("#newPassword").val();
	if(oldPassword != newPassword){
		$("#newPasswordConfirm").val("");
		swal("New and confirm password are mismatch, re-enter confirm password.");
	}
    if ($('#newPasswordConfirm').val()) {
        if ($('#newPasswordConfirm').val().length < 8 || $('#newPasswordConfirm').val().length > 15) {
             swal({
	            	title: "Password Length should be minimum 8 characters and maximum 15 characters.",
	            	type: "warning",
	            	icon: "warning",
	            	timer: 5000
	            });
            $('#newPasswordConfirm').focus();
            $('#newPasswordConfirm').val('');
            return false;
        }
        else if (validPassword($('#newPasswordConfirm').val()) == false) {
            swal({
            	title: "Password must contain atleast 1 UpperCase Alphabet, 1 LowerCase Alphabet, 1 Number and 1 Special Character.",
            	type: "warning",
            	icon: "warning",
            	timer: 5000
            });
            $('#newPasswordConfirm').focus();
            $('#newPasswordConfirm').val('');
            return false;
        }
        else if ($('#newPassword').val()) {
            if ($('#newPassword').val() != $('#newPasswordConfirm').val()) {
                swal({
	            	title: "New Password and Confirm Password does not match.",
	            	type: "warning",
	            	icon: "warning",
	            	timer: 5000
	            });
                $('#newPasswordConfirm').val('');
                $('#newPasswordConfirm').focus();
                return false;
            }
        }
    }
});

function validPassword(val) {
    var txt = val; if (txt.match(/^^.*(?=.{8,})(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*]).*$/)) { return true; }
    else { return false; }
}

$('#btnUpdatePass').click(function (e) {
	//alert("okay na");
	//e.preventDefault(); 
	if ($('#newPassword').val()=='') {
		swal({
        	title: "Please enter new password.",
        	type: "warning",
        	icon: "warning",
        	timer: 5000
        });
		e.preventDefault(); 
	}
	else if ($('#newPasswordConfirm').val()=='') {
		swal({
        	title: "Please enter confirm password.",
        	type: "warning",
        	icon: "warning",
        	timer: 5000
        });
		e.preventDefault(); 	
	}
	else if ($('#password').val()=='') {
		swal({
        	title: "Please enter password.",
        	type: "warning",
        	icon: "warning",
        	timer: 5000
        });
		e.preventDefault(); 	
	}else{
		
			$(".loaderMainNew").css("display"," block");
			
			
			var newPassword = document.getElementById("newPassword").value;
  			
  			//$("#newPassword").val(aesUtil.encrypt(data,newPassword));
  			
  			var newPasswordConfirm = document.getElementById("newPasswordConfirm").value;
  			
  			//$("#newPasswordConfirm").val(aesUtil.encrypt(data,newPasswordConfirm));
  			
  			
  			
  			
  			var oldPassword = $("#password").val();
  			
  			 
  			//
  			$("#password").val(aesUtil.encrypt(data,oldPassword));
		
			$("#chnagePassForm").submit();
//			swal("Password change successfully.");
		 
	}
});


$('#password').blur(function () {
	var ddoCode = $("#ddoCode").val();
	var oldPassword = $("#password").val();
	
	// oldPassword = aesUtil.encrypt(data,oldPassword);
	 
	if(oldPassword!=""){
		$("#loaderMainNew").show();
		$
		.ajax({
			type : "POST",
			url : "../master/checkOldPassword/"+oldPassword,
			async : false,
			error : function(data) {
				console.log(data);
				$("#loaderMainNew").hide();
			},
			 success: function(data){
		    	  console.log(data);
		    	  $("#loaderMainNew").hide();
		    		if(data==0){
		    			$('#btnUpdatePass').prop("disable",false);
		    		}else{
		    			$("#password").val("");
		    			$('#btnUpdatePass').prop("disable",true);
		    			swal("Invalid old password, re-enter old password");
		    		}
				}

			});
	}

});